package com.facebook.imagepipeline.memory;

import android.annotation.SuppressLint;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Throwables;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.NotThreadSafe;

public abstract class BasePool<V> implements Pool<V> {
    private final Class<?> TAG = getClass();
    private boolean mAllowNewBuckets;
    @VisibleForTesting
    final SparseArray<Bucket<V>> mBuckets;
    @GuardedBy("this")
    @VisibleForTesting
    final Counter mFree;
    @VisibleForTesting
    final Set<V> mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    @GuardedBy("this")
    @VisibleForTesting
    final Counter mUsed;

    @VisibleForTesting
    @NotThreadSafe
    static class Counter {
        private static final String TAG = "com.facebook.imagepipeline.memory.BasePool.Counter";
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void increment(int numBytes) {
            this.mCount++;
            this.mNumBytes += numBytes;
        }

        public void decrement(int numBytes) {
            if (this.mNumBytes < numBytes || this.mCount <= 0) {
                FLog.wtf(TAG, "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(numBytes), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
                return;
            }
            this.mCount--;
            this.mNumBytes -= numBytes;
        }

        public void reset() {
            this.mCount = 0;
            this.mNumBytes = 0;
        }
    }

    public static class InvalidSizeException extends RuntimeException {
        public InvalidSizeException(Object size) {
            super("Invalid size: " + size.toString());
        }
    }

    public static class InvalidValueException extends RuntimeException {
        public InvalidValueException(Object value) {
            super("Invalid value: " + value.toString());
        }
    }

    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int hardCap, int usedBytes, int freeBytes, int allocSize) {
            super("Pool hard cap violation? Hard cap = " + hardCap + " Used size = " + usedBytes + " Free size = " + freeBytes + " Request size = " + allocSize);
        }
    }

    public static class SizeTooLargeException extends InvalidSizeException {
        public SizeTooLargeException(Object size) {
            super(size);
        }
    }

    protected abstract V alloc(int i);

    @VisibleForTesting
    protected abstract void free(V v);

    protected abstract int getBucketedSize(int i);

    protected abstract int getBucketedSizeForValue(V v);

    protected abstract int getSizeInBytes(int i);

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.mMemoryTrimmableRegistry = (MemoryTrimmableRegistry) Preconditions.checkNotNull(memoryTrimmableRegistry);
        this.mPoolParams = (PoolParams) Preconditions.checkNotNull(poolParams);
        this.mPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = new SparseArray();
        initBuckets(new SparseIntArray(0));
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    protected void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    public V get(int size) {
        V value;
        ensurePoolSizeInvariant();
        int bucketedSize = getBucketedSize(size);
        synchronized (this) {
            int sizeInBytes;
            Bucket<V> bucket = getBucket(bucketedSize);
            if (bucket != null) {
                value = bucket.get();
                if (value != null) {
                    Preconditions.checkState(this.mInUseValues.add(value));
                    bucketedSize = getBucketedSizeForValue(value);
                    sizeInBytes = getSizeInBytes(bucketedSize);
                    this.mUsed.increment(sizeInBytes);
                    this.mFree.decrement(sizeInBytes);
                    this.mPoolStatsTracker.onValueReuse(sizeInBytes);
                    logStats();
                    if (FLog.isLoggable(2)) {
                        FLog.m133v(this.TAG, "get (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                    }
                }
            }
            sizeInBytes = getSizeInBytes(bucketedSize);
            if (canAllocate(sizeInBytes)) {
                this.mUsed.increment(sizeInBytes);
                if (bucket != null) {
                    bucket.incrementInUseCount();
                }
                value = null;
                try {
                    value = alloc(bucketedSize);
                } catch (Throwable e) {
                    synchronized (this) {
                        this.mUsed.decrement(sizeInBytes);
                        bucket = getBucket(bucketedSize);
                        if (bucket != null) {
                            bucket.decrementInUseCount();
                        }
                        Throwables.propagateIfPossible(e);
                    }
                    return value;
                }
                synchronized (this) {
                    Preconditions.checkState(this.mInUseValues.add(value));
                    trimToSoftCap();
                    this.mPoolStatsTracker.onAlloc(sizeInBytes);
                    logStats();
                    if (FLog.isLoggable(2)) {
                        FLog.m133v(this.TAG, "get (alloc) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                    }
                }
            } else {
                throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, sizeInBytes);
            }
        }
        return value;
    }

    public void release(V value) {
        Preconditions.checkNotNull(value);
        int bucketedSize = getBucketedSizeForValue(value);
        int sizeInBytes = getSizeInBytes(bucketedSize);
        synchronized (this) {
            Bucket<V> bucket = getBucket(bucketedSize);
            if (!this.mInUseValues.remove(value)) {
                FLog.m109e(this.TAG, "release (free, value unrecognized) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                free(value);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else if (bucket == null || bucket.isMaxLengthExceeded() || isMaxSizeSoftCapExceeded() || !isReusable(value)) {
                if (bucket != null) {
                    bucket.decrementInUseCount();
                }
                if (FLog.isLoggable(2)) {
                    FLog.m133v(this.TAG, "release (free) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                }
                free(value);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onFree(sizeInBytes);
            } else {
                bucket.release(value);
                this.mFree.increment(sizeInBytes);
                this.mUsed.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueRelease(sizeInBytes);
                if (FLog.isLoggable(2)) {
                    FLog.m133v(this.TAG, "release (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSize));
                }
            }
            logStats();
        }
    }

    public void trim(MemoryTrimType memoryTrimType) {
        trimToNothing();
    }

    protected void onParamsChanged() {
    }

    protected boolean isReusable(V value) {
        Preconditions.checkNotNull(value);
        return true;
    }

    private synchronized void ensurePoolSizeInvariant() {
        boolean z = !isMaxSizeSoftCapExceeded() || this.mFree.mNumBytes == 0;
        Preconditions.checkState(z);
    }

    private synchronized void initBuckets(SparseIntArray inUseCounts) {
        Preconditions.checkNotNull(inUseCounts);
        this.mBuckets.clear();
        SparseIntArray bucketSizes = this.mPoolParams.bucketSizes;
        if (bucketSizes != null) {
            for (int i = 0; i < bucketSizes.size(); i++) {
                int bucketSize = bucketSizes.keyAt(i);
                this.mBuckets.put(bucketSize, new Bucket(getSizeInBytes(bucketSize), bucketSizes.valueAt(i), inUseCounts.get(bucketSize, 0)));
            }
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    @VisibleForTesting
    void trimToNothing() {
        List<Bucket<V>> bucketsToTrim = new ArrayList(this.mBuckets.size());
        SparseIntArray inUseCounts = new SparseIntArray();
        synchronized (this) {
            int i;
            for (i = 0; i < this.mBuckets.size(); i++) {
                Bucket<V> bucket = (Bucket) this.mBuckets.valueAt(i);
                if (bucket.getFreeListSize() > 0) {
                    bucketsToTrim.add(bucket);
                }
                inUseCounts.put(this.mBuckets.keyAt(i), bucket.getInUseCount());
            }
            initBuckets(inUseCounts);
            this.mFree.reset();
            logStats();
        }
        onParamsChanged();
        for (i = 0; i < bucketsToTrim.size(); i++) {
            bucket = (Bucket) bucketsToTrim.get(i);
            while (true) {
                V item = bucket.pop();
                if (item == null) {
                    break;
                }
                free(item);
            }
        }
    }

    @VisibleForTesting
    synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }

    @VisibleForTesting
    synchronized void trimToSize(int targetSize) {
        int bytesToFree = Math.min((this.mUsed.mNumBytes + this.mFree.mNumBytes) - targetSize, this.mFree.mNumBytes);
        if (bytesToFree > 0) {
            if (FLog.isLoggable(2)) {
                FLog.m134v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(targetSize), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), Integer.valueOf(bytesToFree));
            }
            logStats();
            for (int i = 0; i < this.mBuckets.size() && bytesToFree > 0; i++) {
                Bucket<V> bucket = (Bucket) this.mBuckets.valueAt(i);
                while (bytesToFree > 0) {
                    V value = bucket.pop();
                    if (value == null) {
                        break;
                    }
                    free(value);
                    bytesToFree -= bucket.mItemSize;
                    this.mFree.decrement(bucket.mItemSize);
                }
            }
            logStats();
            if (FLog.isLoggable(2)) {
                FLog.m133v(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(targetSize), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes));
            }
        }
    }

    @VisibleForTesting
    synchronized Bucket<V> getBucket(int bucketedSize) {
        Bucket<V> newBucket;
        Bucket<V> bucket = (Bucket) this.mBuckets.get(bucketedSize);
        if (bucket == null && this.mAllowNewBuckets) {
            if (FLog.isLoggable(2)) {
                FLog.m132v(this.TAG, "creating new bucket %s", Integer.valueOf(bucketedSize));
            }
            newBucket = newBucket(bucketedSize);
            this.mBuckets.put(bucketedSize, newBucket);
        } else {
            newBucket = bucket;
        }
        return newBucket;
    }

    Bucket<V> newBucket(int bucketedSize) {
        return new Bucket(getSizeInBytes(bucketedSize), Integer.MAX_VALUE, 0);
    }

    @VisibleForTesting
    synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean isMaxSizeSoftCapExceeded;
        isMaxSizeSoftCapExceeded = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (isMaxSizeSoftCapExceeded) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return isMaxSizeSoftCapExceeded;
    }

    @VisibleForTesting
    synchronized boolean canAllocate(int sizeInBytes) {
        boolean z = false;
        synchronized (this) {
            int hardCap = this.mPoolParams.maxSizeHardCap;
            if (sizeInBytes > hardCap - this.mUsed.mNumBytes) {
                this.mPoolStatsTracker.onHardCapReached();
            } else {
                int softCap = this.mPoolParams.maxSizeSoftCap;
                if (sizeInBytes > softCap - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
                    trimToSize(softCap - sizeInBytes);
                }
                if (sizeInBytes > hardCap - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
                    this.mPoolStatsTracker.onHardCapReached();
                } else {
                    z = true;
                }
            }
        }
        return z;
    }

    @SuppressLint({"InvalidAccessToGuardedField"})
    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.m135v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.mUsed.mCount), Integer.valueOf(this.mUsed.mNumBytes), Integer.valueOf(this.mFree.mCount), Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    public synchronized Map<String, Integer> getStats() {
        Map<String, Integer> stats;
        stats = new HashMap();
        for (int i = 0; i < this.mBuckets.size(); i++) {
            stats.put(PoolStatsTracker.BUCKETS_USED_PREFIX + getSizeInBytes(this.mBuckets.keyAt(i)), Integer.valueOf(((Bucket) this.mBuckets.valueAt(i)).getInUseCount()));
        }
        stats.put(PoolStatsTracker.SOFT_CAP, Integer.valueOf(this.mPoolParams.maxSizeSoftCap));
        stats.put(PoolStatsTracker.HARD_CAP, Integer.valueOf(this.mPoolParams.maxSizeHardCap));
        stats.put(PoolStatsTracker.USED_COUNT, Integer.valueOf(this.mUsed.mCount));
        stats.put(PoolStatsTracker.USED_BYTES, Integer.valueOf(this.mUsed.mNumBytes));
        stats.put(PoolStatsTracker.FREE_COUNT, Integer.valueOf(this.mFree.mCount));
        stats.put(PoolStatsTracker.FREE_BYTES, Integer.valueOf(this.mFree.mNumBytes));
        return stats;
    }
}

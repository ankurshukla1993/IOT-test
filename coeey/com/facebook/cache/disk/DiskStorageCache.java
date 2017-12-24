package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.CacheEventListener.EvictionReason;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.MultiCacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage.DiskDumpInfo;
import com.facebook.cache.disk.DiskStorage.Entry;
import com.facebook.cache.disk.DiskStorage.Inserter;
import com.facebook.common.disk.DiskTrimmable;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.logging.FLog;
import com.facebook.common.statfs.StatFsHelper;
import com.facebook.common.statfs.StatFsHelper.StorageType;
import com.facebook.common.time.Clock;
import com.facebook.common.time.SystemClock;
import com.facebook.common.util.SecureHashUtil;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class DiskStorageCache implements FileCache, DiskTrimmable {
    private static final long FILECACHE_SIZE_UPDATE_PERIOD_MS = TimeUnit.MINUTES.toMillis(30);
    private static final long FUTURE_TIMESTAMP_THRESHOLD_MS = TimeUnit.HOURS.toMillis(2);
    public static final int START_OF_VERSIONING = 1;
    private static final Class<?> TAG = DiskStorageCache.class;
    private static final double TRIMMING_LOWER_BOUND = 0.02d;
    private static final long UNINITIALIZED = -1;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    @GuardedBy("mLock")
    private long mCacheSizeLastUpdateTime;
    private long mCacheSizeLimit;
    private final long mCacheSizeLimitMinimum;
    private final CacheStats mCacheStats;
    private final Clock mClock;
    private final long mDefaultCacheSizeLimit;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    @GuardedBy("mLock")
    @VisibleForTesting
    final Map<CacheKey, String> mIndex;
    private final Object mLock = new Object();
    private final long mLowDiskSpaceCacheSizeLimit;
    private final StatFsHelper mStatFsHelper;
    private final DiskStorage mStorage;

    @VisibleForTesting
    static class CacheStats {
        private long mCount = -1;
        private boolean mInitialized = false;
        private long mSize = -1;

        CacheStats() {
        }

        public synchronized boolean isInitialized() {
            return this.mInitialized;
        }

        public synchronized void reset() {
            this.mInitialized = false;
            this.mCount = -1;
            this.mSize = -1;
        }

        public synchronized void set(long size, long count) {
            this.mCount = count;
            this.mSize = size;
            this.mInitialized = true;
        }

        public synchronized void increment(long sizeIncrement, long countIncrement) {
            if (this.mInitialized) {
                this.mSize += sizeIncrement;
                this.mCount += countIncrement;
            }
        }

        public synchronized long getSize() {
            return this.mSize;
        }

        public synchronized long getCount() {
            return this.mCount;
        }
    }

    public static class Params {
        public final long mCacheSizeLimitMinimum;
        public final long mDefaultCacheSizeLimit;
        public final long mLowDiskSpaceCacheSizeLimit;

        public Params(long cacheSizeLimitMinimum, long lowDiskSpaceCacheSizeLimit, long defaultCacheSizeLimit) {
            this.mCacheSizeLimitMinimum = cacheSizeLimitMinimum;
            this.mLowDiskSpaceCacheSizeLimit = lowDiskSpaceCacheSizeLimit;
            this.mDefaultCacheSizeLimit = defaultCacheSizeLimit;
        }
    }

    public DiskStorageCache(DiskStorage diskStorage, EntryEvictionComparatorSupplier entryEvictionComparatorSupplier, Params params, CacheEventListener cacheEventListener, CacheErrorLogger cacheErrorLogger, @Nullable DiskTrimmableRegistry diskTrimmableRegistry) {
        this.mLowDiskSpaceCacheSizeLimit = params.mLowDiskSpaceCacheSizeLimit;
        this.mDefaultCacheSizeLimit = params.mDefaultCacheSizeLimit;
        this.mCacheSizeLimit = params.mDefaultCacheSizeLimit;
        this.mStatFsHelper = StatFsHelper.getInstance();
        this.mStorage = diskStorage;
        this.mEntryEvictionComparatorSupplier = entryEvictionComparatorSupplier;
        this.mCacheSizeLastUpdateTime = -1;
        this.mCacheEventListener = cacheEventListener;
        this.mCacheSizeLimitMinimum = params.mCacheSizeLimitMinimum;
        this.mCacheErrorLogger = cacheErrorLogger;
        this.mCacheStats = new CacheStats();
        if (diskTrimmableRegistry != null) {
            diskTrimmableRegistry.registerDiskTrimmable(this);
        }
        this.mClock = SystemClock.get();
        this.mIndex = new HashMap();
    }

    public DiskDumpInfo getDumpInfo() throws IOException {
        return this.mStorage.getDumpInfo();
    }

    public boolean isEnabled() {
        return this.mStorage.isEnabled();
    }

    public BinaryResource getResource(CacheKey key) {
        String resourceId = null;
        SettableCacheEvent cacheEvent = new SettableCacheEvent().setCacheKey(key);
        try {
            BinaryResource binaryResource;
            synchronized (this.mLock) {
                binaryResource = null;
                if (!this.mIndex.containsKey(key)) {
                    List<String> resourceIds = getResourceIds(key);
                    for (int i = 0; i < resourceIds.size(); i++) {
                        resourceId = (String) resourceIds.get(i);
                        cacheEvent.setResourceId(resourceId);
                        binaryResource = this.mStorage.getResource(resourceId, key);
                        if (binaryResource != null) {
                            break;
                        }
                    }
                } else {
                    resourceId = (String) this.mIndex.get(key);
                    cacheEvent.setResourceId(resourceId);
                    binaryResource = this.mStorage.getResource(resourceId, key);
                }
                if (binaryResource == null) {
                    this.mCacheEventListener.onMiss(cacheEvent);
                    this.mIndex.remove(key);
                } else {
                    this.mCacheEventListener.onHit(cacheEvent);
                    this.mIndex.put(key, resourceId);
                }
            }
            return binaryResource;
        } catch (IOException ioe) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.GENERIC_IO, TAG, "getResource", ioe);
            cacheEvent.setException(ioe);
            this.mCacheEventListener.onReadException(cacheEvent);
            return null;
        }
    }

    public boolean probe(CacheKey key) {
        String resourceId = null;
        try {
            boolean z;
            synchronized (this.mLock) {
                z = false;
                if (!this.mIndex.containsKey(key)) {
                    List<String> resourceIds = getResourceIds(key);
                    for (int i = 0; i < resourceIds.size(); i++) {
                        resourceId = (String) resourceIds.get(i);
                        z = this.mStorage.touch(resourceId, key);
                        if (z) {
                            break;
                        }
                    }
                } else {
                    resourceId = (String) this.mIndex.get(key);
                    z = this.mStorage.touch(resourceId, key);
                }
                if (z) {
                    this.mIndex.put(key, resourceId);
                }
            }
            return z;
        } catch (IOException e) {
            this.mCacheEventListener.onReadException(new SettableCacheEvent().setCacheKey(key).setResourceId(null).setException(e));
            return false;
        }
    }

    private Inserter startInsert(String resourceId, CacheKey key) throws IOException {
        maybeEvictFilesInCacheDir();
        return this.mStorage.insert(resourceId, key);
    }

    private BinaryResource endInsert(Inserter inserter, CacheKey key, String resourceId) throws IOException {
        BinaryResource resource;
        synchronized (this.mLock) {
            resource = inserter.commit(key);
            this.mCacheStats.increment(resource.size(), 1);
            this.mIndex.put(key, resourceId);
        }
        return resource;
    }

    public BinaryResource insert(CacheKey key, WriterCallback callback) throws IOException {
        String resourceId;
        SettableCacheEvent cacheEvent = new SettableCacheEvent().setCacheKey(key);
        this.mCacheEventListener.onWriteAttempt(cacheEvent);
        synchronized (this.mLock) {
            if (this.mIndex.containsKey(key)) {
                resourceId = (String) this.mIndex.get(key);
            } else {
                resourceId = getFirstResourceId(key);
            }
        }
        cacheEvent.setResourceId(resourceId);
        Inserter inserter;
        try {
            inserter = startInsert(resourceId, key);
            inserter.writeData(callback, key);
            BinaryResource resource = endInsert(inserter, key, resourceId);
            cacheEvent.setItemSize(resource.size()).setCacheSize(this.mCacheStats.getSize());
            this.mCacheEventListener.onWriteSuccess(cacheEvent);
            if (!inserter.cleanUp()) {
                FLog.m107e(TAG, "Failed to delete temp file");
            }
            return resource;
        } catch (Throwable ioe) {
            cacheEvent.setException(ioe);
            this.mCacheEventListener.onWriteException(cacheEvent);
            FLog.m108e(TAG, "Failed inserting a file into the cache", ioe);
            throw ioe;
        } catch (Throwable th) {
            if (!inserter.cleanUp()) {
                FLog.m107e(TAG, "Failed to delete temp file");
            }
        }
    }

    public void remove(CacheKey key) {
        synchronized (this.mLock) {
            try {
                if (this.mIndex.containsKey(key)) {
                    this.mStorage.remove((String) this.mIndex.get(key));
                } else {
                    List<String> resourceIds = getResourceIds(key);
                    for (int i = 0; i < resourceIds.size(); i++) {
                        this.mStorage.remove((String) resourceIds.get(i));
                    }
                }
                this.mIndex.remove(key);
            } catch (IOException e) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.DELETE_FILE, TAG, "delete: " + e.getMessage(), e);
            }
        }
    }

    public long clearOldEntries(long cacheExpirationMs) {
        long oldestRemainingEntryAgeMs = 0;
        synchronized (this.mLock) {
            try {
                long now = this.mClock.now();
                Collection<Entry> allEntries = this.mStorage.getEntries();
                long cacheSizeBeforeClearance = this.mCacheStats.getSize();
                int itemsRemovedCount = 0;
                long itemsRemovedSize = 0;
                for (Entry entry : allEntries) {
                    long entryAgeMs = Math.max(1, Math.abs(now - entry.getTimestamp()));
                    if (entryAgeMs >= cacheExpirationMs) {
                        long entryRemovedSize = this.mStorage.remove(entry);
                        this.mIndex.values().remove(entry.getId());
                        if (entryRemovedSize > 0) {
                            itemsRemovedCount++;
                            itemsRemovedSize += entryRemovedSize;
                            this.mCacheEventListener.onEviction(new SettableCacheEvent().setResourceId(entry.getId()).setEvictionReason(EvictionReason.CONTENT_STALE).setItemSize(entryRemovedSize).setCacheSize(cacheSizeBeforeClearance - itemsRemovedSize));
                        }
                    } else {
                        oldestRemainingEntryAgeMs = Math.max(oldestRemainingEntryAgeMs, entryAgeMs);
                    }
                }
                this.mStorage.purgeUnexpectedResources();
                if (itemsRemovedCount > 0) {
                    maybeUpdateFileCacheSize();
                    this.mCacheStats.increment(-itemsRemovedSize, (long) (-itemsRemovedCount));
                }
            } catch (IOException ioe) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.EVICTION, TAG, "clearOldEntries: " + ioe.getMessage(), ioe);
            }
        }
        return oldestRemainingEntryAgeMs;
    }

    private void maybeEvictFilesInCacheDir() throws IOException {
        synchronized (this.mLock) {
            boolean calculatedRightNow = maybeUpdateFileCacheSize();
            updateFileCacheSizeLimit();
            long cacheSize = this.mCacheStats.getSize();
            if (cacheSize > this.mCacheSizeLimit && !calculatedRightNow) {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
            }
            if (cacheSize > this.mCacheSizeLimit) {
                evictAboveSize((this.mCacheSizeLimit * 9) / 10, EvictionReason.CACHE_FULL);
            }
        }
    }

    @GuardedBy("mLock")
    private void evictAboveSize(long desiredSize, EvictionReason reason) throws IOException {
        try {
            Collection<Entry> entries = getSortedEntries(this.mStorage.getEntries());
            long cacheSizeBeforeClearance = this.mCacheStats.getSize();
            long deleteSize = cacheSizeBeforeClearance - desiredSize;
            int itemCount = 0;
            long sumItemSizes = 0;
            for (Entry entry : entries) {
                if (sumItemSizes > deleteSize) {
                    break;
                }
                long deletedSize = this.mStorage.remove(entry);
                this.mIndex.values().remove(entry.getId());
                if (deletedSize > 0) {
                    itemCount++;
                    sumItemSizes += deletedSize;
                    this.mCacheEventListener.onEviction(new SettableCacheEvent().setResourceId(entry.getId()).setEvictionReason(reason).setItemSize(deletedSize).setCacheSize(cacheSizeBeforeClearance - sumItemSizes).setCacheLimit(desiredSize));
                }
            }
            this.mCacheStats.increment(-sumItemSizes, (long) (-itemCount));
            this.mStorage.purgeUnexpectedResources();
        } catch (IOException ioe) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.EVICTION, TAG, "evictAboveSize: " + ioe.getMessage(), ioe);
            throw ioe;
        }
    }

    private Collection<Entry> getSortedEntries(Collection<Entry> allEntries) {
        long threshold = this.mClock.now() + FUTURE_TIMESTAMP_THRESHOLD_MS;
        ArrayList<Entry> sortedList = new ArrayList(allEntries.size());
        ArrayList<Entry> listToSort = new ArrayList(allEntries.size());
        for (Entry entry : allEntries) {
            if (entry.getTimestamp() > threshold) {
                sortedList.add(entry);
            } else {
                listToSort.add(entry);
            }
        }
        Collections.sort(listToSort, this.mEntryEvictionComparatorSupplier.get());
        sortedList.addAll(listToSort);
        return sortedList;
    }

    @GuardedBy("mLock")
    private void updateFileCacheSizeLimit() {
        if (this.mStatFsHelper.testLowDiskSpace(StorageType.INTERNAL, this.mDefaultCacheSizeLimit - this.mCacheStats.getSize())) {
            this.mCacheSizeLimit = this.mLowDiskSpaceCacheSizeLimit;
        } else {
            this.mCacheSizeLimit = this.mDefaultCacheSizeLimit;
        }
    }

    public long getSize() {
        return this.mCacheStats.getSize();
    }

    public void clearAll() {
        synchronized (this.mLock) {
            try {
                this.mStorage.clearAll();
                this.mIndex.clear();
            } catch (IOException ioe) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.EVICTION, TAG, "clearAll: " + ioe.getMessage(), ioe);
            }
            this.mCacheStats.reset();
        }
    }

    public boolean hasKeySync(CacheKey key) {
        boolean containsKey;
        synchronized (this.mLock) {
            containsKey = this.mIndex.containsKey(key);
        }
        return containsKey;
    }

    public boolean hasKey(CacheKey key) {
        boolean z;
        synchronized (this.mLock) {
            if (hasKeySync(key)) {
                z = true;
            } else {
                String str = null;
                z = false;
                try {
                    if (!this.mIndex.containsKey(key)) {
                        List<String> resourceIds = getResourceIds(key);
                        for (int i = 0; i < resourceIds.size(); i++) {
                            str = (String) resourceIds.get(i);
                            z = this.mStorage.contains(str, key);
                            if (z) {
                                break;
                            }
                        }
                    } else {
                        str = (String) this.mIndex.get(key);
                        z = this.mStorage.contains(str, key);
                    }
                    if (z) {
                        this.mIndex.put(key, str);
                    } else {
                        this.mIndex.remove(key);
                    }
                } catch (IOException e) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void trimToMinimum() {
        /*
        r12 = this;
        r8 = 0;
        r5 = r12.mLock;
        monitor-enter(r5);
        r12.maybeUpdateFileCacheSize();	 Catch:{ all -> 0x0037 }
        r4 = r12.mCacheStats;	 Catch:{ all -> 0x0037 }
        r0 = r4.getSize();	 Catch:{ all -> 0x0037 }
        r6 = r12.mCacheSizeLimitMinimum;	 Catch:{ all -> 0x0037 }
        r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r4 <= 0) goto L_0x001e;
    L_0x0014:
        r4 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r4 <= 0) goto L_0x001e;
    L_0x0018:
        r6 = r12.mCacheSizeLimitMinimum;	 Catch:{ all -> 0x0037 }
        r4 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
        if (r4 >= 0) goto L_0x0020;
    L_0x001e:
        monitor-exit(r5);	 Catch:{ all -> 0x0037 }
    L_0x001f:
        return;
    L_0x0020:
        r6 = 4607182418800017408; // 0x3ff0000000000000 float:0.0 double:1.0;
        r8 = r12.mCacheSizeLimitMinimum;	 Catch:{ all -> 0x0037 }
        r8 = (double) r8;	 Catch:{ all -> 0x0037 }
        r10 = (double) r0;	 Catch:{ all -> 0x0037 }
        r8 = r8 / r10;
        r2 = r6 - r8;
        r6 = 4581421828931458171; // 0x3f947ae147ae147b float:89128.96 double:0.02;
        r4 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1));
        if (r4 <= 0) goto L_0x0035;
    L_0x0032:
        r12.trimBy(r2);	 Catch:{ all -> 0x0037 }
    L_0x0035:
        monitor-exit(r5);	 Catch:{ all -> 0x0037 }
        goto L_0x001f;
    L_0x0037:
        r4 = move-exception;
        monitor-exit(r5);	 Catch:{ all -> 0x0037 }
        throw r4;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.cache.disk.DiskStorageCache.trimToMinimum():void");
    }

    public void trimToNothing() {
        clearAll();
    }

    private void trimBy(double trimRatio) {
        synchronized (this.mLock) {
            try {
                this.mCacheStats.reset();
                maybeUpdateFileCacheSize();
                long cacheSize = this.mCacheStats.getSize();
                evictAboveSize(cacheSize - ((long) (((double) cacheSize) * trimRatio)), EvictionReason.CACHE_MANAGER_TRIMMED);
            } catch (IOException ioe) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.EVICTION, TAG, "trimBy: " + ioe.getMessage(), ioe);
            }
        }
    }

    @GuardedBy("mLock")
    private boolean maybeUpdateFileCacheSize() {
        long now = this.mClock.now();
        if (this.mCacheStats.isInitialized() && this.mCacheSizeLastUpdateTime != -1 && now - this.mCacheSizeLastUpdateTime <= FILECACHE_SIZE_UPDATE_PERIOD_MS) {
            return false;
        }
        calcFileCacheSize();
        this.mCacheSizeLastUpdateTime = now;
        return true;
    }

    @GuardedBy("mLock")
    private void calcFileCacheSize() {
        long size = 0;
        int count = 0;
        boolean foundFutureTimestamp = false;
        int numFutureFiles = 0;
        int sizeFutureFiles = 0;
        long maxTimeDelta = -1;
        long now = this.mClock.now();
        long timeThreshold = now + FUTURE_TIMESTAMP_THRESHOLD_MS;
        try {
            for (Entry entry : this.mStorage.getEntries()) {
                count++;
                size += entry.getSize();
                if (entry.getTimestamp() > timeThreshold) {
                    foundFutureTimestamp = true;
                    numFutureFiles++;
                    sizeFutureFiles = (int) (((long) sizeFutureFiles) + entry.getSize());
                    maxTimeDelta = Math.max(entry.getTimestamp() - now, maxTimeDelta);
                }
            }
            if (foundFutureTimestamp) {
                this.mCacheErrorLogger.logError(CacheErrorCategory.READ_INVALID_ENTRY, TAG, "Future timestamp found in " + numFutureFiles + " files , with a total size of " + sizeFutureFiles + " bytes, and a maximum time delta of " + maxTimeDelta + "ms", null);
            }
            this.mCacheStats.set(size, (long) count);
        } catch (IOException ioe) {
            this.mCacheErrorLogger.logError(CacheErrorCategory.GENERIC_IO, TAG, "calcFileCacheSize: " + ioe.getMessage(), ioe);
        }
    }

    private static List<String> getResourceIds(CacheKey key) {
        try {
            List<String> ids;
            if (key instanceof MultiCacheKey) {
                List<CacheKey> keys = ((MultiCacheKey) key).getCacheKeys();
                ids = new ArrayList(keys.size());
                for (int i = 0; i < keys.size(); i++) {
                    ids.add(secureHashKey((CacheKey) keys.get(i)));
                }
                return ids;
            }
            ids = new ArrayList(1);
            ids.add(secureHashKey(key));
            return ids;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @VisibleForTesting
    static String getFirstResourceId(CacheKey key) {
        try {
            if (key instanceof MultiCacheKey) {
                return secureHashKey((CacheKey) ((MultiCacheKey) key).getCacheKeys().get(0));
            }
            return secureHashKey(key);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String secureHashKey(CacheKey key) throws UnsupportedEncodingException {
        return SecureHashUtil.makeSHA1HashBase64(key.toString().getBytes("UTF-8"));
    }
}

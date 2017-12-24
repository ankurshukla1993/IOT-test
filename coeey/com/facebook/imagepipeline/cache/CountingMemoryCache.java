package com.facebook.imagepipeline.cache;

import android.os.SystemClock;
import com.android.internal.util.Predicate;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmable;
import com.facebook.common.references.CloseableReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class CountingMemoryCache<K, V> implements MemoryCache<K, V>, MemoryTrimmable {
    @VisibleForTesting
    static final long PARAMS_INTERCHECK_INTERVAL_MS = TimeUnit.MINUTES.toMillis(5);
    private final CacheTrimStrategy mCacheTrimStrategy;
    @GuardedBy("this")
    @VisibleForTesting
    final CountingLruMap<K, Entry<K, V>> mCachedEntries;
    @GuardedBy("this")
    @VisibleForTesting
    final CountingLruMap<K, Entry<K, V>> mExclusiveEntries;
    @GuardedBy("this")
    private long mLastCacheParamsCheck = SystemClock.uptimeMillis();
    @GuardedBy("this")
    protected MemoryCacheParams mMemoryCacheParams = ((MemoryCacheParams) this.mMemoryCacheParamsSupplier.get());
    private final Supplier<MemoryCacheParams> mMemoryCacheParamsSupplier;
    private final ValueDescriptor<V> mValueDescriptor;

    @VisibleForTesting
    static class Entry<K, V> {
        public int clientCount = 0;
        public boolean isOrphan = false;
        public final K key;
        @Nullable
        public final EntryStateObserver<K> observer;
        public final CloseableReference<V> valueRef;

        private Entry(K key, CloseableReference<V> valueRef, @Nullable EntryStateObserver<K> observer) {
            this.key = Preconditions.checkNotNull(key);
            this.valueRef = (CloseableReference) Preconditions.checkNotNull(CloseableReference.cloneOrNull((CloseableReference) valueRef));
            this.observer = observer;
        }

        @VisibleForTesting
        static <K, V> Entry<K, V> of(K key, CloseableReference<V> valueRef, @Nullable EntryStateObserver<K> observer) {
            return new Entry(key, valueRef, observer);
        }
    }

    public CountingMemoryCache(ValueDescriptor<V> valueDescriptor, CacheTrimStrategy cacheTrimStrategy, Supplier<MemoryCacheParams> memoryCacheParamsSupplier) {
        this.mValueDescriptor = valueDescriptor;
        this.mExclusiveEntries = new CountingLruMap(wrapValueDescriptor(valueDescriptor));
        this.mCachedEntries = new CountingLruMap(wrapValueDescriptor(valueDescriptor));
        this.mCacheTrimStrategy = cacheTrimStrategy;
        this.mMemoryCacheParamsSupplier = memoryCacheParamsSupplier;
    }

    private ValueDescriptor<Entry<K, V>> wrapValueDescriptor(ValueDescriptor<V> evictableValueDescriptor) {
        return new 1(this, evictableValueDescriptor);
    }

    public CloseableReference<V> cache(K key, CloseableReference<V> valueRef) {
        return cache(key, valueRef, null);
    }

    public CloseableReference<V> cache(K key, CloseableReference<V> valueRef, EntryStateObserver<K> observer) {
        Entry oldExclusive;
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(valueRef);
        maybeUpdateCacheParams();
        CloseableReference oldRefToClose = null;
        CloseableReference<V> clientRef = null;
        synchronized (this) {
            oldExclusive = (Entry) this.mExclusiveEntries.remove(key);
            Entry<K, V> oldEntry = (Entry) this.mCachedEntries.remove(key);
            if (oldEntry != null) {
                makeOrphan(oldEntry);
                oldRefToClose = referenceToClose(oldEntry);
            }
            if (canCacheNewValue(valueRef.get())) {
                Entry<K, V> newEntry = Entry.of(key, valueRef, observer);
                this.mCachedEntries.put(key, newEntry);
                clientRef = newClientReference(newEntry);
            }
        }
        CloseableReference.closeSafely(oldRefToClose);
        maybeNotifyExclusiveEntryRemoval(oldExclusive);
        maybeEvictEntries();
        return clientRef;
    }

    private synchronized boolean canCacheNewValue(V value) {
        boolean z;
        int newValueSize = this.mValueDescriptor.getSizeInBytes(value);
        z = newValueSize <= this.mMemoryCacheParams.maxCacheEntrySize && getInUseCount() <= this.mMemoryCacheParams.maxCacheEntries - 1 && getInUseSizeInBytes() <= this.mMemoryCacheParams.maxCacheSize - newValueSize;
        return z;
    }

    @Nullable
    public CloseableReference<V> get(K key) {
        Entry oldExclusive;
        Preconditions.checkNotNull(key);
        CloseableReference<V> clientRef = null;
        synchronized (this) {
            oldExclusive = (Entry) this.mExclusiveEntries.remove(key);
            Entry<K, V> entry = (Entry) this.mCachedEntries.get(key);
            if (entry != null) {
                clientRef = newClientReference(entry);
            }
        }
        maybeNotifyExclusiveEntryRemoval(oldExclusive);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return clientRef;
    }

    private synchronized CloseableReference<V> newClientReference(Entry<K, V> entry) {
        increaseClientCount(entry);
        return CloseableReference.of(entry.valueRef.get(), new 2(this, entry));
    }

    private void releaseClientReference(Entry<K, V> entry) {
        CloseableReference oldRefToClose;
        Preconditions.checkNotNull(entry);
        synchronized (this) {
            decreaseClientCount(entry);
            boolean isExclusiveAdded = maybeAddToExclusives(entry);
            oldRefToClose = referenceToClose(entry);
        }
        CloseableReference.closeSafely(oldRefToClose);
        if (!isExclusiveAdded) {
            entry = null;
        }
        maybeNotifyExclusiveEntryInsertion(entry);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    private synchronized boolean maybeAddToExclusives(Entry<K, V> entry) {
        boolean z;
        if (entry.isOrphan || entry.clientCount != 0) {
            z = false;
        } else {
            this.mExclusiveEntries.put(entry.key, entry);
            z = true;
        }
        return z;
    }

    @Nullable
    public CloseableReference<V> reuse(K key) {
        Preconditions.checkNotNull(key);
        CloseableReference<V> clientRef = null;
        boolean removed = false;
        synchronized (this) {
            Entry oldExclusive = (Entry) this.mExclusiveEntries.remove(key);
            if (oldExclusive != null) {
                Entry<K, V> entry = (Entry) this.mCachedEntries.remove(key);
                Preconditions.checkNotNull(entry);
                Preconditions.checkState(entry.clientCount == 0);
                clientRef = entry.valueRef;
                removed = true;
            }
        }
        if (removed) {
            maybeNotifyExclusiveEntryRemoval(oldExclusive);
        }
        return clientRef;
    }

    public int removeAll(Predicate<K> predicate) {
        ArrayList oldExclusives;
        ArrayList<Entry<K, V>> oldEntries;
        synchronized (this) {
            oldExclusives = this.mExclusiveEntries.removeAll(predicate);
            oldEntries = this.mCachedEntries.removeAll(predicate);
            makeOrphans(oldEntries);
        }
        maybeClose(oldEntries);
        maybeNotifyExclusiveEntryRemoval(oldExclusives);
        maybeUpdateCacheParams();
        maybeEvictEntries();
        return oldEntries.size();
    }

    public void clear() {
        ArrayList oldExclusives;
        ArrayList<Entry<K, V>> oldEntries;
        synchronized (this) {
            oldExclusives = this.mExclusiveEntries.clear();
            oldEntries = this.mCachedEntries.clear();
            makeOrphans(oldEntries);
        }
        maybeClose(oldEntries);
        maybeNotifyExclusiveEntryRemoval(oldExclusives);
        maybeUpdateCacheParams();
    }

    public synchronized boolean contains(Predicate<K> predicate) {
        return !this.mCachedEntries.getMatchingEntries(predicate).isEmpty();
    }

    public void trim(MemoryTrimType trimType) {
        ArrayList oldEntries;
        double trimRatio = this.mCacheTrimStrategy.getTrimRatio(trimType);
        synchronized (this) {
            oldEntries = trimExclusivelyOwnedEntries(Integer.MAX_VALUE, Math.max(0, ((int) (((double) this.mCachedEntries.getSizeInBytes()) * (1.0d - trimRatio))) - getInUseSizeInBytes()));
            makeOrphans(oldEntries);
        }
        maybeClose(oldEntries);
        maybeNotifyExclusiveEntryRemoval(oldEntries);
        maybeUpdateCacheParams();
        maybeEvictEntries();
    }

    private synchronized void maybeUpdateCacheParams() {
        if (this.mLastCacheParamsCheck + PARAMS_INTERCHECK_INTERVAL_MS <= SystemClock.uptimeMillis()) {
            this.mLastCacheParamsCheck = SystemClock.uptimeMillis();
            this.mMemoryCacheParams = (MemoryCacheParams) this.mMemoryCacheParamsSupplier.get();
        }
    }

    private void maybeEvictEntries() {
        ArrayList oldEntries;
        synchronized (this) {
            oldEntries = trimExclusivelyOwnedEntries(Math.min(this.mMemoryCacheParams.maxEvictionQueueEntries, this.mMemoryCacheParams.maxCacheEntries - getInUseCount()), Math.min(this.mMemoryCacheParams.maxEvictionQueueSize, this.mMemoryCacheParams.maxCacheSize - getInUseSizeInBytes()));
            makeOrphans(oldEntries);
        }
        maybeClose(oldEntries);
        maybeNotifyExclusiveEntryRemoval(oldEntries);
    }

    @Nullable
    private synchronized ArrayList<Entry<K, V>> trimExclusivelyOwnedEntries(int count, int size) {
        ArrayList<Entry<K, V>> oldEntries;
        count = Math.max(count, 0);
        size = Math.max(size, 0);
        if (this.mExclusiveEntries.getCount() > count || this.mExclusiveEntries.getSizeInBytes() > size) {
            oldEntries = new ArrayList();
            while (true) {
                if (this.mExclusiveEntries.getCount() <= count && this.mExclusiveEntries.getSizeInBytes() <= size) {
                    break;
                }
                K key = this.mExclusiveEntries.getFirstKey();
                this.mExclusiveEntries.remove(key);
                oldEntries.add(this.mCachedEntries.remove(key));
            }
        } else {
            oldEntries = null;
        }
        return oldEntries;
    }

    private void maybeClose(@Nullable ArrayList<Entry<K, V>> oldEntries) {
        if (oldEntries != null) {
            Iterator it = oldEntries.iterator();
            while (it.hasNext()) {
                CloseableReference.closeSafely(referenceToClose((Entry) it.next()));
            }
        }
    }

    private void maybeNotifyExclusiveEntryRemoval(@Nullable ArrayList<Entry<K, V>> entries) {
        if (entries != null) {
            Iterator it = entries.iterator();
            while (it.hasNext()) {
                maybeNotifyExclusiveEntryRemoval((Entry) it.next());
            }
        }
    }

    private static <K, V> void maybeNotifyExclusiveEntryRemoval(@Nullable Entry<K, V> entry) {
        if (entry != null && entry.observer != null) {
            entry.observer.onExclusivityChanged(entry.key, false);
        }
    }

    private static <K, V> void maybeNotifyExclusiveEntryInsertion(@Nullable Entry<K, V> entry) {
        if (entry != null && entry.observer != null) {
            entry.observer.onExclusivityChanged(entry.key, true);
        }
    }

    private synchronized void makeOrphans(@Nullable ArrayList<Entry<K, V>> oldEntries) {
        if (oldEntries != null) {
            Iterator it = oldEntries.iterator();
            while (it.hasNext()) {
                makeOrphan((Entry) it.next());
            }
        }
    }

    private synchronized void makeOrphan(Entry<K, V> entry) {
        boolean z = true;
        synchronized (this) {
            Preconditions.checkNotNull(entry);
            if (entry.isOrphan) {
                z = false;
            }
            Preconditions.checkState(z);
            entry.isOrphan = true;
        }
    }

    private synchronized void increaseClientCount(Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(!entry.isOrphan);
        entry.clientCount++;
    }

    private synchronized void decreaseClientCount(Entry<K, V> entry) {
        Preconditions.checkNotNull(entry);
        Preconditions.checkState(entry.clientCount > 0);
        entry.clientCount--;
    }

    @Nullable
    private synchronized CloseableReference<V> referenceToClose(Entry<K, V> entry) {
        CloseableReference<V> closeableReference;
        Preconditions.checkNotNull(entry);
        closeableReference = (entry.isOrphan && entry.clientCount == 0) ? entry.valueRef : null;
        return closeableReference;
    }

    public synchronized int getCount() {
        return this.mCachedEntries.getCount();
    }

    public synchronized int getSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes();
    }

    public synchronized int getInUseCount() {
        return this.mCachedEntries.getCount() - this.mExclusiveEntries.getCount();
    }

    public synchronized int getInUseSizeInBytes() {
        return this.mCachedEntries.getSizeInBytes() - this.mExclusiveEntries.getSizeInBytes();
    }

    public synchronized int getEvictionQueueCount() {
        return this.mExclusiveEntries.getCount();
    }

    public synchronized int getEvictionQueueSizeInBytes() {
        return this.mExclusiveEntries.getSizeInBytes();
    }
}

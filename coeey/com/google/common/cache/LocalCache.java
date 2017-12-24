package com.google.common.cache;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Equivalence;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.base.Ticker;
import com.google.common.cache.AbstractCache.SimpleStatsCounter;
import com.google.common.cache.AbstractCache.StatsCounter;
import com.google.common.cache.CacheBuilder.NullListener;
import com.google.common.cache.CacheBuilder.OneWeigher;
import com.google.common.cache.CacheLoader.InvalidCacheLoadException;
import com.google.common.cache.CacheLoader.UnsupportedLoadingOperationException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.ExecutionError;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.google.common.util.concurrent.Uninterruptibles;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@GwtCompatible(emulated = true)
class LocalCache<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V> {
    static final int CONTAINS_VALUE_RETRIES = 3;
    static final Queue<? extends Object> DISCARDING_QUEUE = new 2();
    static final int DRAIN_MAX = 16;
    static final int DRAIN_THRESHOLD = 63;
    static final int MAXIMUM_CAPACITY = 1073741824;
    static final int MAX_SEGMENTS = 65536;
    static final ValueReference<Object, Object> UNSET = new 1();
    static final Logger logger = Logger.getLogger(LocalCache.class.getName());
    final int concurrencyLevel;
    @Nullable
    final CacheLoader<? super K, V> defaultLoader;
    final EntryFactory entryFactory;
    Set<Entry<K, V>> entrySet;
    final long expireAfterAccessNanos;
    final long expireAfterWriteNanos;
    final StatsCounter globalStatsCounter;
    final Equivalence<Object> keyEquivalence;
    Set<K> keySet;
    final Strength keyStrength;
    final long maxWeight;
    final long refreshNanos;
    final RemovalListener<K, V> removalListener;
    final Queue<RemovalNotification<K, V>> removalNotificationQueue;
    final int segmentMask;
    final int segmentShift;
    final Segment<K, V>[] segments;
    final Ticker ticker;
    final Equivalence<Object> valueEquivalence;
    final Strength valueStrength;
    Collection<V> values;
    final Weigher<K, V> weigher;

    static class LocalManualCache<K, V> implements Cache<K, V>, Serializable {
        private static final long serialVersionUID = 1;
        final LocalCache<K, V> localCache;

        LocalManualCache(CacheBuilder<? super K, ? super V> builder) {
            this(new LocalCache(builder, null));
        }

        private LocalManualCache(LocalCache<K, V> localCache) {
            this.localCache = localCache;
        }

        @Nullable
        public V getIfPresent(Object key) {
            return this.localCache.getIfPresent(key);
        }

        public V get(K key, Callable<? extends V> valueLoader) throws ExecutionException {
            Preconditions.checkNotNull(valueLoader);
            return this.localCache.get(key, new 1(this, valueLoader));
        }

        public ImmutableMap<K, V> getAllPresent(Iterable<?> keys) {
            return this.localCache.getAllPresent(keys);
        }

        public void put(K key, V value) {
            this.localCache.put(key, value);
        }

        public void putAll(Map<? extends K, ? extends V> m) {
            this.localCache.putAll(m);
        }

        public void invalidate(Object key) {
            Preconditions.checkNotNull(key);
            this.localCache.remove(key);
        }

        public void invalidateAll(Iterable<?> keys) {
            this.localCache.invalidateAll(keys);
        }

        public void invalidateAll() {
            this.localCache.clear();
        }

        public long size() {
            return this.localCache.longSize();
        }

        public ConcurrentMap<K, V> asMap() {
            return this.localCache;
        }

        public CacheStats stats() {
            SimpleStatsCounter aggregator = new SimpleStatsCounter();
            aggregator.incrementBy(this.localCache.globalStatsCounter);
            for (Segment<K, V> segment : this.localCache.segments) {
                aggregator.incrementBy(segment.statsCounter);
            }
            return aggregator.snapshot();
        }

        public void cleanUp() {
            this.localCache.cleanUp();
        }

        Object writeReplace() {
            return new ManualSerializationProxy(this.localCache);
        }
    }

    interface ReferenceEntry<K, V> {
        long getAccessTime();

        int getHash();

        @Nullable
        K getKey();

        @Nullable
        ReferenceEntry<K, V> getNext();

        ReferenceEntry<K, V> getNextInAccessQueue();

        ReferenceEntry<K, V> getNextInWriteQueue();

        ReferenceEntry<K, V> getPreviousInAccessQueue();

        ReferenceEntry<K, V> getPreviousInWriteQueue();

        ValueReference<K, V> getValueReference();

        long getWriteTime();

        void setAccessTime(long j);

        void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry);

        void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry);

        void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry);

        void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry);

        void setValueReference(ValueReference<K, V> valueReference);

        void setWriteTime(long j);
    }

    static class Segment<K, V> extends ReentrantLock {
        @GuardedBy("this")
        final Queue<ReferenceEntry<K, V>> accessQueue;
        volatile int count;
        final ReferenceQueue<K> keyReferenceQueue;
        final LocalCache<K, V> map;
        final long maxSegmentWeight;
        int modCount;
        final AtomicInteger readCount = new AtomicInteger();
        final Queue<ReferenceEntry<K, V>> recencyQueue;
        final StatsCounter statsCounter;
        volatile AtomicReferenceArray<ReferenceEntry<K, V>> table;
        int threshold;
        @GuardedBy("this")
        long totalWeight;
        final ReferenceQueue<V> valueReferenceQueue;
        @GuardedBy("this")
        final Queue<ReferenceEntry<K, V>> writeQueue;

        Segment(LocalCache<K, V> map, int initialCapacity, long maxSegmentWeight, StatsCounter statsCounter) {
            ReferenceQueue referenceQueue = null;
            this.map = map;
            this.maxSegmentWeight = maxSegmentWeight;
            this.statsCounter = (StatsCounter) Preconditions.checkNotNull(statsCounter);
            initTable(newEntryArray(initialCapacity));
            this.keyReferenceQueue = map.usesKeyReferences() ? new ReferenceQueue() : null;
            if (map.usesValueReferences()) {
                referenceQueue = new ReferenceQueue();
            }
            this.valueReferenceQueue = referenceQueue;
            this.recencyQueue = map.usesAccessQueue() ? new ConcurrentLinkedQueue() : LocalCache.discardingQueue();
            this.writeQueue = map.usesWriteQueue() ? new WriteQueue() : LocalCache.discardingQueue();
            this.accessQueue = map.usesAccessQueue() ? new AccessQueue() : LocalCache.discardingQueue();
        }

        AtomicReferenceArray<ReferenceEntry<K, V>> newEntryArray(int size) {
            return new AtomicReferenceArray(size);
        }

        void initTable(AtomicReferenceArray<ReferenceEntry<K, V>> newTable) {
            this.threshold = (newTable.length() * 3) / 4;
            if (!this.map.customWeigher() && ((long) this.threshold) == this.maxSegmentWeight) {
                this.threshold++;
            }
            this.table = newTable;
        }

        @GuardedBy("this")
        ReferenceEntry<K, V> newEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return this.map.entryFactory.newEntry(this, Preconditions.checkNotNull(key), hash, next);
        }

        @GuardedBy("this")
        ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            if (original.getKey() == null) {
                return null;
            }
            ValueReference<K, V> valueReference = original.getValueReference();
            V value = valueReference.get();
            if (value == null && valueReference.isActive()) {
                return null;
            }
            ReferenceEntry<K, V> newEntry = this.map.entryFactory.copyEntry(this, original, newNext);
            newEntry.setValueReference(valueReference.copyFor(this.valueReferenceQueue, value, newEntry));
            return newEntry;
        }

        @GuardedBy("this")
        void setValue(ReferenceEntry<K, V> entry, K key, V value, long now) {
            ValueReference<K, V> previous = entry.getValueReference();
            int weight = this.map.weigher.weigh(key, value);
            Preconditions.checkState(weight >= 0, "Weights must be non-negative");
            entry.setValueReference(this.map.valueStrength.referenceValue(this, entry, value, weight));
            recordWrite(entry, weight, now);
            previous.notifyNewValue(value);
        }

        V get(K key, int hash, CacheLoader<? super K, V> loader) throws ExecutionException {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(loader);
            try {
                V scheduleRefresh;
                if (this.count != 0) {
                    ReferenceEntry<K, V> e = getEntry(key, hash);
                    if (e != null) {
                        long now = this.map.ticker.read();
                        V value = getLiveValue(e, now);
                        if (value != null) {
                            recordRead(e, now);
                            this.statsCounter.recordHits(1);
                            scheduleRefresh = scheduleRefresh(e, key, hash, value, now, loader);
                            postReadCleanup();
                        } else {
                            ValueReference<K, V> valueReference = e.getValueReference();
                            if (valueReference.isLoading()) {
                                scheduleRefresh = waitForLoadingValue(e, key, valueReference);
                                postReadCleanup();
                            }
                        }
                        return scheduleRefresh;
                    }
                }
                scheduleRefresh = lockedGetOrLoad(key, hash, loader);
                postReadCleanup();
                return scheduleRefresh;
            } catch (ExecutionException ee) {
                Throwable cause = ee.getCause();
                if (cause instanceof Error) {
                    throw new ExecutionError((Error) cause);
                } else if (cause instanceof RuntimeException) {
                    throw new UncheckedExecutionException(cause);
                } else {
                    throw ee;
                }
            } catch (Throwable th) {
                postReadCleanup();
            }
        }

        V lockedGetOrLoad(K key, int hash, CacheLoader<? super K, V> loader) throws ExecutionException {
            LoadingValueReference<K, V> loadingValueReference;
            Throwable th;
            ValueReference<K, V> valueReference = null;
            LoadingValueReference<K, V> loadingValueReference2 = null;
            boolean createNewEntry = true;
            lock();
            try {
                V v;
                long now = this.map.ticker.read();
                preWriteCleanup(now);
                int newCount = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & (table.length() - 1);
                ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
                ReferenceEntry<K, V> e = first;
                while (e != null) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        valueReference = e.getValueReference();
                        if (valueReference.isLoading()) {
                            createNewEntry = false;
                        } else {
                            v = valueReference.get();
                            if (v == null) {
                                enqueueNotification(entryKey, hash, valueReference, RemovalCause.COLLECTED);
                            } else if (this.map.isExpired(e, now)) {
                                enqueueNotification(entryKey, hash, valueReference, RemovalCause.EXPIRED);
                            } else {
                                recordLockedRead(e, now);
                                this.statsCounter.recordHits(1);
                                unlock();
                                postWriteCleanup();
                                return v;
                            }
                            this.writeQueue.remove(e);
                            this.accessQueue.remove(e);
                            this.count = newCount;
                        }
                        if (createNewEntry) {
                            loadingValueReference = new LoadingValueReference();
                            if (e != null) {
                                try {
                                    e = newEntry(key, hash, first);
                                    e.setValueReference(loadingValueReference);
                                    table.set(index, e);
                                    loadingValueReference2 = loadingValueReference;
                                } catch (Throwable th2) {
                                    th = th2;
                                    loadingValueReference2 = loadingValueReference;
                                    unlock();
                                    postWriteCleanup();
                                    throw th;
                                }
                            }
                            e.setValueReference(loadingValueReference);
                            loadingValueReference2 = loadingValueReference;
                        }
                        unlock();
                        postWriteCleanup();
                        if (createNewEntry) {
                            return waitForLoadingValue(e, key, valueReference);
                        }
                        try {
                            synchronized (e) {
                                v = loadSync(key, hash, loadingValueReference2, loader);
                            }
                            return v;
                        } finally {
                            this.statsCounter.recordMisses(1);
                        }
                    } else {
                        e = e.getNext();
                    }
                }
                if (createNewEntry) {
                    loadingValueReference = new LoadingValueReference();
                    if (e != null) {
                        e.setValueReference(loadingValueReference);
                        loadingValueReference2 = loadingValueReference;
                    } else {
                        e = newEntry(key, hash, first);
                        e.setValueReference(loadingValueReference);
                        table.set(index, e);
                        loadingValueReference2 = loadingValueReference;
                    }
                }
                unlock();
                postWriteCleanup();
                if (createNewEntry) {
                    return waitForLoadingValue(e, key, valueReference);
                }
                synchronized (e) {
                    v = loadSync(key, hash, loadingValueReference2, loader);
                }
                return v;
            } catch (Throwable th3) {
                th = th3;
            }
        }

        V waitForLoadingValue(ReferenceEntry<K, V> e, K key, ValueReference<K, V> valueReference) throws ExecutionException {
            if (valueReference.isLoading()) {
                Preconditions.checkState(!Thread.holdsLock(e), "Recursive load of: %s", new Object[]{key});
                try {
                    V value = valueReference.waitForValue();
                    if (value == null) {
                        String valueOf = String.valueOf(String.valueOf(key));
                        throw new InvalidCacheLoadException(new StringBuilder(valueOf.length() + 35).append("CacheLoader returned null for key ").append(valueOf).append(".").toString());
                    }
                    recordRead(e, this.map.ticker.read());
                    return value;
                } finally {
                    this.statsCounter.recordMisses(1);
                }
            } else {
                throw new AssertionError();
            }
        }

        V loadSync(K key, int hash, LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> loader) throws ExecutionException {
            return getAndRecordStats(key, hash, loadingValueReference, loadingValueReference.loadFuture(key, loader));
        }

        ListenableFuture<V> loadAsync(K key, int hash, LoadingValueReference<K, V> loadingValueReference, CacheLoader<? super K, V> loader) {
            ListenableFuture<V> loadingFuture = loadingValueReference.loadFuture(key, loader);
            loadingFuture.addListener(new 1(this, key, hash, loadingValueReference, loadingFuture), MoreExecutors.directExecutor());
            return loadingFuture;
        }

        V getAndRecordStats(K key, int hash, LoadingValueReference<K, V> loadingValueReference, ListenableFuture<V> newValue) throws ExecutionException {
            V v = null;
            try {
                v = Uninterruptibles.getUninterruptibly(newValue);
                if (v == null) {
                    String valueOf = String.valueOf(String.valueOf(key));
                    throw new InvalidCacheLoadException(new StringBuilder(valueOf.length() + 35).append("CacheLoader returned null for key ").append(valueOf).append(".").toString());
                }
                this.statsCounter.recordLoadSuccess(loadingValueReference.elapsedNanos());
                storeLoadedValue(key, hash, loadingValueReference, v);
                return v;
            } finally {
                if (v == null) {
                    this.statsCounter.recordLoadException(loadingValueReference.elapsedNanos());
                    removeLoadingValue(key, hash, loadingValueReference);
                }
            }
        }

        V scheduleRefresh(ReferenceEntry<K, V> entry, K key, int hash, V oldValue, long now, CacheLoader<? super K, V> loader) {
            if (this.map.refreshes() && now - entry.getWriteTime() > this.map.refreshNanos && !entry.getValueReference().isLoading()) {
                V newValue = refresh(key, hash, loader, true);
                if (newValue != null) {
                    return newValue;
                }
            }
            return oldValue;
        }

        @Nullable
        V refresh(K key, int hash, CacheLoader<? super K, V> loader, boolean checkTime) {
            V v = null;
            LoadingValueReference<K, V> loadingValueReference = insertLoadingValueReference(key, hash, checkTime);
            if (loadingValueReference != null) {
                ListenableFuture<V> result = loadAsync(key, hash, loadingValueReference, loader);
                if (result.isDone()) {
                    try {
                        v = Uninterruptibles.getUninterruptibly(result);
                    } catch (Throwable th) {
                    }
                }
            }
            return v;
        }

        @Nullable
        LoadingValueReference<K, V> insertLoadingValueReference(K key, int hash, boolean checkTime) {
            LoadingValueReference<K, V> loadingValueReference;
            lock();
            long now = this.map.ticker.read();
            preWriteCleanup(now);
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & (table.length() - 1);
            ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
            ReferenceEntry<K, V> e = first;
            while (e != null) {
                K entryKey = e.getKey();
                if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                    ValueReference<K, V> valueReference = e.getValueReference();
                    if (valueReference.isLoading() || (checkTime && now - e.getWriteTime() < this.map.refreshNanos)) {
                        unlock();
                        postWriteCleanup();
                        return null;
                    }
                    this.modCount++;
                    loadingValueReference = new LoadingValueReference(valueReference);
                    e.setValueReference(loadingValueReference);
                    unlock();
                    postWriteCleanup();
                    return loadingValueReference;
                }
                try {
                    e = e.getNext();
                } catch (Throwable th) {
                    unlock();
                    postWriteCleanup();
                }
            }
            this.modCount++;
            loadingValueReference = new LoadingValueReference();
            e = newEntry(key, hash, first);
            e.setValueReference(loadingValueReference);
            table.set(index, e);
            unlock();
            postWriteCleanup();
            return loadingValueReference;
        }

        void tryDrainReferenceQueues() {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                } finally {
                    unlock();
                }
            }
        }

        @GuardedBy("this")
        void drainReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                drainKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                drainValueReferenceQueue();
            }
        }

        @GuardedBy("this")
        void drainKeyReferenceQueue() {
            int i = 0;
            do {
                Reference<? extends K> ref = this.keyReferenceQueue.poll();
                if (ref != null) {
                    this.map.reclaimKey((ReferenceEntry) ref);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        @GuardedBy("this")
        void drainValueReferenceQueue() {
            int i = 0;
            do {
                Reference<? extends V> ref = this.valueReferenceQueue.poll();
                if (ref != null) {
                    this.map.reclaimValue((ValueReference) ref);
                    i++;
                } else {
                    return;
                }
            } while (i != 16);
        }

        void clearReferenceQueues() {
            if (this.map.usesKeyReferences()) {
                clearKeyReferenceQueue();
            }
            if (this.map.usesValueReferences()) {
                clearValueReferenceQueue();
            }
        }

        void clearKeyReferenceQueue() {
            do {
            } while (this.keyReferenceQueue.poll() != null);
        }

        void clearValueReferenceQueue() {
            do {
            } while (this.valueReferenceQueue.poll() != null);
        }

        void recordRead(ReferenceEntry<K, V> entry, long now) {
            if (this.map.recordsAccess()) {
                entry.setAccessTime(now);
            }
            this.recencyQueue.add(entry);
        }

        @GuardedBy("this")
        void recordLockedRead(ReferenceEntry<K, V> entry, long now) {
            if (this.map.recordsAccess()) {
                entry.setAccessTime(now);
            }
            this.accessQueue.add(entry);
        }

        @GuardedBy("this")
        void recordWrite(ReferenceEntry<K, V> entry, int weight, long now) {
            drainRecencyQueue();
            this.totalWeight += (long) weight;
            if (this.map.recordsAccess()) {
                entry.setAccessTime(now);
            }
            if (this.map.recordsWrite()) {
                entry.setWriteTime(now);
            }
            this.accessQueue.add(entry);
            this.writeQueue.add(entry);
        }

        @GuardedBy("this")
        void drainRecencyQueue() {
            while (true) {
                ReferenceEntry<K, V> e = (ReferenceEntry) this.recencyQueue.poll();
                if (e == null) {
                    return;
                }
                if (this.accessQueue.contains(e)) {
                    this.accessQueue.add(e);
                }
            }
        }

        void tryExpireEntries(long now) {
            if (tryLock()) {
                try {
                    expireEntries(now);
                } finally {
                    unlock();
                }
            }
        }

        @GuardedBy("this")
        void expireEntries(long now) {
            drainRecencyQueue();
            ReferenceEntry<K, V> e;
            do {
                e = (ReferenceEntry) this.writeQueue.peek();
                if (e == null || !this.map.isExpired(e, now)) {
                    do {
                        e = (ReferenceEntry) this.accessQueue.peek();
                        if (e == null || !this.map.isExpired(e, now)) {
                            return;
                        }
                    } while (removeEntry(e, e.getHash(), RemovalCause.EXPIRED));
                    throw new AssertionError();
                }
            } while (removeEntry(e, e.getHash(), RemovalCause.EXPIRED));
            throw new AssertionError();
        }

        @GuardedBy("this")
        void enqueueNotification(ReferenceEntry<K, V> entry, RemovalCause cause) {
            enqueueNotification(entry.getKey(), entry.getHash(), entry.getValueReference(), cause);
        }

        @GuardedBy("this")
        void enqueueNotification(@Nullable K key, int hash, ValueReference<K, V> valueReference, RemovalCause cause) {
            this.totalWeight -= (long) valueReference.getWeight();
            if (cause.wasEvicted()) {
                this.statsCounter.recordEviction();
            }
            if (this.map.removalNotificationQueue != LocalCache.DISCARDING_QUEUE) {
                this.map.removalNotificationQueue.offer(new RemovalNotification(key, valueReference.get(), cause));
            }
        }

        @GuardedBy("this")
        void evictEntries() {
            if (this.map.evictsBySize()) {
                drainRecencyQueue();
                while (this.totalWeight > this.maxSegmentWeight) {
                    ReferenceEntry<K, V> e = getNextEvictable();
                    if (!removeEntry(e, e.getHash(), RemovalCause.SIZE)) {
                        throw new AssertionError();
                    }
                }
            }
        }

        @GuardedBy("this")
        ReferenceEntry<K, V> getNextEvictable() {
            for (ReferenceEntry<K, V> e : this.accessQueue) {
                if (e.getValueReference().getWeight() > 0) {
                    return e;
                }
            }
            throw new AssertionError();
        }

        ReferenceEntry<K, V> getFirst(int hash) {
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            return (ReferenceEntry) table.get((table.length() - 1) & hash);
        }

        @Nullable
        ReferenceEntry<K, V> getEntry(Object key, int hash) {
            for (ReferenceEntry<K, V> e = getFirst(hash); e != null; e = e.getNext()) {
                if (e.getHash() == hash) {
                    K entryKey = e.getKey();
                    if (entryKey == null) {
                        tryDrainReferenceQueues();
                    } else if (this.map.keyEquivalence.equivalent(key, entryKey)) {
                        return e;
                    }
                }
            }
            return null;
        }

        @Nullable
        ReferenceEntry<K, V> getLiveEntry(Object key, int hash, long now) {
            ReferenceEntry<K, V> e = getEntry(key, hash);
            if (e == null) {
                return null;
            }
            if (!this.map.isExpired(e, now)) {
                return e;
            }
            tryExpireEntries(now);
            return null;
        }

        V getLiveValue(ReferenceEntry<K, V> entry, long now) {
            if (entry.getKey() == null) {
                tryDrainReferenceQueues();
                return null;
            }
            V value = entry.getValueReference().get();
            if (value == null) {
                tryDrainReferenceQueues();
                return null;
            } else if (!this.map.isExpired(entry, now)) {
                return value;
            } else {
                tryExpireEntries(now);
                return null;
            }
        }

        @Nullable
        V get(Object key, int hash) {
            V v = null;
            try {
                if (this.count != 0) {
                    long now = this.map.ticker.read();
                    ReferenceEntry<K, V> e = getLiveEntry(key, hash, now);
                    if (e != null) {
                        V value = e.getValueReference().get();
                        if (value != null) {
                            recordRead(e, now);
                            v = scheduleRefresh(e, e.getKey(), hash, value, now, this.map.defaultLoader);
                            postReadCleanup();
                        } else {
                            tryDrainReferenceQueues();
                        }
                    }
                    return v;
                }
                postReadCleanup();
                return v;
            } finally {
                postReadCleanup();
            }
        }

        boolean containsKey(Object key, int hash) {
            boolean z = false;
            try {
                if (this.count != 0) {
                    ReferenceEntry<K, V> e = getLiveEntry(key, hash, this.map.ticker.read());
                    if (e != null) {
                        if (e.getValueReference().get() != null) {
                            z = true;
                        }
                        postReadCleanup();
                    }
                } else {
                    postReadCleanup();
                }
                return z;
            } finally {
                postReadCleanup();
            }
        }

        @VisibleForTesting
        boolean containsValue(Object value) {
            try {
                if (this.count != 0) {
                    long now = this.map.ticker.read();
                    AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                    int length = table.length();
                    for (int i = 0; i < length; i++) {
                        for (ReferenceEntry<K, V> e = (ReferenceEntry) table.get(i); e != null; e = e.getNext()) {
                            V entryValue = getLiveValue(e, now);
                            if (entryValue != null && this.map.valueEquivalence.equivalent(value, entryValue)) {
                                return true;
                            }
                        }
                    }
                }
                postReadCleanup();
                return false;
            } finally {
                postReadCleanup();
            }
        }

        @Nullable
        V put(K key, int hash, V value, boolean onlyIfAbsent) {
            lock();
            try {
                int newCount;
                long now = this.map.ticker.read();
                preWriteCleanup(now);
                if (this.count + 1 > this.threshold) {
                    expand();
                    newCount = this.count + 1;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & (table.length() - 1);
                ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        ValueReference<K, V> valueReference = e.getValueReference();
                        V v = valueReference.get();
                        if (v == null) {
                            this.modCount++;
                            if (valueReference.isActive()) {
                                enqueueNotification(key, hash, valueReference, RemovalCause.COLLECTED);
                                setValue(e, key, value, now);
                                newCount = this.count;
                            } else {
                                setValue(e, key, value, now);
                                newCount = this.count + 1;
                            }
                            this.count = newCount;
                            evictEntries();
                            v = null;
                            return v;
                        } else if (onlyIfAbsent) {
                            recordLockedRead(e, now);
                            unlock();
                            postWriteCleanup();
                            return v;
                        } else {
                            this.modCount++;
                            enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
                            setValue(e, key, value, now);
                            evictEntries();
                            unlock();
                            postWriteCleanup();
                            return v;
                        }
                    }
                }
                this.modCount++;
                ReferenceEntry<K, V> newEntry = newEntry(key, hash, first);
                setValue(newEntry, key, value, now);
                table.set(index, newEntry);
                this.count++;
                evictEntries();
                unlock();
                postWriteCleanup();
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        @GuardedBy("this")
        void expand() {
            AtomicReferenceArray<ReferenceEntry<K, V>> oldTable = this.table;
            int oldCapacity = oldTable.length();
            if (oldCapacity < 1073741824) {
                int newCount = this.count;
                AtomicReferenceArray<ReferenceEntry<K, V>> newTable = newEntryArray(oldCapacity << 1);
                this.threshold = (newTable.length() * 3) / 4;
                int newMask = newTable.length() - 1;
                for (int oldIndex = 0; oldIndex < oldCapacity; oldIndex++) {
                    ReferenceEntry<K, V> head = (ReferenceEntry) oldTable.get(oldIndex);
                    if (head != null) {
                        ReferenceEntry<K, V> next = head.getNext();
                        int headIndex = head.getHash() & newMask;
                        if (next == null) {
                            newTable.set(headIndex, head);
                        } else {
                            ReferenceEntry<K, V> e;
                            int newIndex;
                            ReferenceEntry<K, V> tail = head;
                            int tailIndex = headIndex;
                            for (e = next; e != null; e = e.getNext()) {
                                newIndex = e.getHash() & newMask;
                                if (newIndex != tailIndex) {
                                    tailIndex = newIndex;
                                    tail = e;
                                }
                            }
                            newTable.set(tailIndex, tail);
                            for (e = head; e != tail; e = e.getNext()) {
                                newIndex = e.getHash() & newMask;
                                ReferenceEntry<K, V> newFirst = copyEntry(e, (ReferenceEntry) newTable.get(newIndex));
                                if (newFirst != null) {
                                    newTable.set(newIndex, newFirst);
                                } else {
                                    removeCollectedEntry(e);
                                    newCount--;
                                }
                            }
                        }
                    }
                }
                this.table = newTable;
                this.count = newCount;
            }
        }

        boolean replace(K key, int hash, V oldValue, V newValue) {
            lock();
            try {
                long now = this.map.ticker.read();
                preWriteCleanup(now);
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & (table.length() - 1);
                ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        ValueReference<K, V> valueReference = e.getValueReference();
                        V entryValue = valueReference.get();
                        if (entryValue == null) {
                            if (valueReference.isActive()) {
                                int newCount = this.count - 1;
                                this.modCount++;
                                newCount = this.count - 1;
                                table.set(index, removeValueFromChain(first, e, entryKey, hash, valueReference, RemovalCause.COLLECTED));
                                this.count = newCount;
                            }
                            unlock();
                            postWriteCleanup();
                            return false;
                        } else if (this.map.valueEquivalence.equivalent(oldValue, entryValue)) {
                            this.modCount++;
                            enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
                            setValue(e, key, newValue, now);
                            evictEntries();
                            unlock();
                            postWriteCleanup();
                            return true;
                        } else {
                            recordLockedRead(e, now);
                            unlock();
                            postWriteCleanup();
                            return false;
                        }
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } catch (Throwable th) {
                unlock();
                postWriteCleanup();
            }
        }

        @Nullable
        V replace(K key, int hash, V newValue) {
            lock();
            try {
                long now = this.map.ticker.read();
                preWriteCleanup(now);
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & (table.length() - 1);
                ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        ValueReference<K, V> valueReference = e.getValueReference();
                        V v = valueReference.get();
                        if (v == null) {
                            if (valueReference.isActive()) {
                                int newCount = this.count - 1;
                                this.modCount++;
                                newCount = this.count - 1;
                                table.set(index, removeValueFromChain(first, e, entryKey, hash, valueReference, RemovalCause.COLLECTED));
                                this.count = newCount;
                            }
                            unlock();
                            postWriteCleanup();
                            return null;
                        }
                        this.modCount++;
                        enqueueNotification(key, hash, valueReference, RemovalCause.REPLACED);
                        setValue(e, key, newValue, now);
                        evictEntries();
                        unlock();
                        postWriteCleanup();
                        return v;
                    }
                }
                unlock();
                postWriteCleanup();
                return null;
            } catch (Throwable th) {
                unlock();
                postWriteCleanup();
            }
        }

        @Nullable
        V remove(Object key, int hash) {
            lock();
            try {
                preWriteCleanup(this.map.ticker.read());
                int newCount = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & (table.length() - 1);
                ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        RemovalCause cause;
                        ValueReference<K, V> valueReference = e.getValueReference();
                        V entryValue = valueReference.get();
                        if (entryValue != null) {
                            cause = RemovalCause.EXPLICIT;
                        } else if (valueReference.isActive()) {
                            cause = RemovalCause.COLLECTED;
                        } else {
                            unlock();
                            postWriteCleanup();
                            return null;
                        }
                        this.modCount++;
                        newCount = this.count - 1;
                        table.set(index, removeValueFromChain(first, e, entryKey, hash, valueReference, cause));
                        this.count = newCount;
                        return entryValue;
                    }
                }
                unlock();
                postWriteCleanup();
                return null;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        boolean storeLoadedValue(K key, int hash, LoadingValueReference<K, V> oldValueReference, V newValue) {
            lock();
            try {
                long now = this.map.ticker.read();
                preWriteCleanup(now);
                int newCount = this.count + 1;
                if (newCount > this.threshold) {
                    expand();
                    newCount = this.count + 1;
                }
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & (table.length() - 1);
                ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        ValueReference<K, V> valueReference = e.getValueReference();
                        V entryValue = valueReference.get();
                        if (oldValueReference == valueReference || (entryValue == null && valueReference != LocalCache.UNSET)) {
                            this.modCount++;
                            if (oldValueReference.isActive()) {
                                enqueueNotification(key, hash, oldValueReference, entryValue == null ? RemovalCause.COLLECTED : RemovalCause.REPLACED);
                                newCount--;
                            }
                            setValue(e, key, newValue, now);
                            this.count = newCount;
                            evictEntries();
                            return true;
                        }
                        enqueueNotification(key, hash, new WeightedStrongValueReference(newValue, 0), RemovalCause.REPLACED);
                        unlock();
                        postWriteCleanup();
                        return false;
                    }
                }
                this.modCount++;
                ReferenceEntry<K, V> newEntry = newEntry(key, hash, first);
                setValue(newEntry, key, newValue, now);
                table.set(index, newEntry);
                this.count = newCount;
                evictEntries();
                unlock();
                postWriteCleanup();
                return true;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        boolean remove(Object key, int hash, Object value) {
            lock();
            try {
                preWriteCleanup(this.map.ticker.read());
                int newCount = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & (table.length() - 1);
                ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        RemovalCause cause;
                        ValueReference<K, V> valueReference = e.getValueReference();
                        V entryValue = valueReference.get();
                        if (this.map.valueEquivalence.equivalent(value, entryValue)) {
                            cause = RemovalCause.EXPLICIT;
                        } else {
                            if (entryValue == null) {
                                if (valueReference.isActive()) {
                                    cause = RemovalCause.COLLECTED;
                                }
                            }
                            unlock();
                            postWriteCleanup();
                            return false;
                        }
                        this.modCount++;
                        newCount = this.count - 1;
                        table.set(index, removeValueFromChain(first, e, entryKey, hash, valueReference, cause));
                        this.count = newCount;
                        boolean z = cause == RemovalCause.EXPLICIT;
                        unlock();
                        postWriteCleanup();
                        return z;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } catch (Throwable th) {
                unlock();
                postWriteCleanup();
            }
        }

        void clear() {
            if (this.count != 0) {
                lock();
                try {
                    int i;
                    AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                    for (i = 0; i < table.length(); i++) {
                        for (ReferenceEntry<K, V> e = (ReferenceEntry) table.get(i); e != null; e = e.getNext()) {
                            if (e.getValueReference().isActive()) {
                                enqueueNotification(e, RemovalCause.EXPLICIT);
                            }
                        }
                    }
                    for (i = 0; i < table.length(); i++) {
                        table.set(i, null);
                    }
                    clearReferenceQueues();
                    this.writeQueue.clear();
                    this.accessQueue.clear();
                    this.readCount.set(0);
                    this.modCount++;
                    this.count = 0;
                } finally {
                    unlock();
                    postWriteCleanup();
                }
            }
        }

        @GuardedBy("this")
        @Nullable
        ReferenceEntry<K, V> removeValueFromChain(ReferenceEntry<K, V> first, ReferenceEntry<K, V> entry, @Nullable K key, int hash, ValueReference<K, V> valueReference, RemovalCause cause) {
            enqueueNotification(key, hash, valueReference, cause);
            this.writeQueue.remove(entry);
            this.accessQueue.remove(entry);
            if (!valueReference.isLoading()) {
                return removeEntryFromChain(first, entry);
            }
            valueReference.notifyNewValue(null);
            return first;
        }

        @GuardedBy("this")
        @Nullable
        ReferenceEntry<K, V> removeEntryFromChain(ReferenceEntry<K, V> first, ReferenceEntry<K, V> entry) {
            int newCount = this.count;
            ReferenceEntry<K, V> newFirst = entry.getNext();
            for (ReferenceEntry<K, V> e = first; e != entry; e = e.getNext()) {
                ReferenceEntry<K, V> next = copyEntry(e, newFirst);
                if (next != null) {
                    newFirst = next;
                } else {
                    removeCollectedEntry(e);
                    newCount--;
                }
            }
            this.count = newCount;
            return newFirst;
        }

        @GuardedBy("this")
        void removeCollectedEntry(ReferenceEntry<K, V> entry) {
            enqueueNotification(entry, RemovalCause.COLLECTED);
            this.writeQueue.remove(entry);
            this.accessQueue.remove(entry);
        }

        boolean reclaimKey(ReferenceEntry<K, V> entry, int hash) {
            lock();
            try {
                int newCount = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & (table.length() - 1);
                ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    if (e == entry) {
                        this.modCount++;
                        newCount = this.count - 1;
                        table.set(index, removeValueFromChain(first, e, e.getKey(), hash, e.getValueReference(), RemovalCause.COLLECTED));
                        this.count = newCount;
                        return true;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } finally {
                unlock();
                postWriteCleanup();
            }
        }

        boolean reclaimValue(K key, int hash, ValueReference<K, V> valueReference) {
            boolean z = false;
            lock();
            try {
                int newCount = this.count - 1;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & (table.length() - 1);
                ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
                for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                    K entryKey = e.getKey();
                    if (e.getHash() == hash && entryKey != null && this.map.keyEquivalence.equivalent(key, entryKey)) {
                        if (e.getValueReference() == valueReference) {
                            this.modCount++;
                            newCount = this.count - 1;
                            table.set(index, removeValueFromChain(first, e, entryKey, hash, valueReference, RemovalCause.COLLECTED));
                            this.count = newCount;
                            z = true;
                        } else {
                            unlock();
                            if (!isHeldByCurrentThread()) {
                                postWriteCleanup();
                            }
                        }
                        return z;
                    }
                }
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
                return z;
            } finally {
                unlock();
                if (!isHeldByCurrentThread()) {
                    postWriteCleanup();
                }
            }
        }

        boolean removeLoadingValue(K key, int hash, LoadingValueReference<K, V> valueReference) {
            lock();
            try {
                AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
                int index = hash & (table.length() - 1);
                ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
                ReferenceEntry<K, V> e = first;
                while (e != null) {
                    K entryKey = e.getKey();
                    if (e.getHash() != hash || entryKey == null || !this.map.keyEquivalence.equivalent(key, entryKey)) {
                        e = e.getNext();
                    } else if (e.getValueReference() == valueReference) {
                        if (valueReference.isActive()) {
                            e.setValueReference(valueReference.getOldValue());
                        } else {
                            table.set(index, removeEntryFromChain(first, e));
                        }
                        unlock();
                        postWriteCleanup();
                        return true;
                    } else {
                        unlock();
                        postWriteCleanup();
                        return false;
                    }
                }
                unlock();
                postWriteCleanup();
                return false;
            } catch (Throwable th) {
                unlock();
                postWriteCleanup();
            }
        }

        @GuardedBy("this")
        boolean removeEntry(ReferenceEntry<K, V> entry, int hash, RemovalCause cause) {
            int newCount = this.count - 1;
            AtomicReferenceArray<ReferenceEntry<K, V>> table = this.table;
            int index = hash & (table.length() - 1);
            ReferenceEntry<K, V> first = (ReferenceEntry) table.get(index);
            for (ReferenceEntry<K, V> e = first; e != null; e = e.getNext()) {
                if (e == entry) {
                    this.modCount++;
                    newCount = this.count - 1;
                    table.set(index, removeValueFromChain(first, e, e.getKey(), hash, e.getValueReference(), cause));
                    this.count = newCount;
                    return true;
                }
            }
            return false;
        }

        void postReadCleanup() {
            if ((this.readCount.incrementAndGet() & 63) == 0) {
                cleanUp();
            }
        }

        @GuardedBy("this")
        void preWriteCleanup(long now) {
            runLockedCleanup(now);
        }

        void postWriteCleanup() {
            runUnlockedCleanup();
        }

        void cleanUp() {
            runLockedCleanup(this.map.ticker.read());
            runUnlockedCleanup();
        }

        void runLockedCleanup(long now) {
            if (tryLock()) {
                try {
                    drainReferenceQueues();
                    expireEntries(now);
                    this.readCount.set(0);
                } finally {
                    unlock();
                }
            }
        }

        void runUnlockedCleanup() {
            if (!isHeldByCurrentThread()) {
                this.map.processPendingNotifications();
            }
        }
    }

    interface ValueReference<K, V> {
        ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, @Nullable V v, ReferenceEntry<K, V> referenceEntry);

        @Nullable
        V get();

        @Nullable
        ReferenceEntry<K, V> getEntry();

        int getWeight();

        boolean isActive();

        boolean isLoading();

        void notifyNewValue(@Nullable V v);

        V waitForValue() throws ExecutionException;
    }

    LocalCache(CacheBuilder<? super K, ? super V> builder, @Nullable CacheLoader<? super K, V> loader) {
        Queue discardingQueue;
        this.concurrencyLevel = Math.min(builder.getConcurrencyLevel(), 65536);
        this.keyStrength = builder.getKeyStrength();
        this.valueStrength = builder.getValueStrength();
        this.keyEquivalence = builder.getKeyEquivalence();
        this.valueEquivalence = builder.getValueEquivalence();
        this.maxWeight = builder.getMaximumWeight();
        this.weigher = builder.getWeigher();
        this.expireAfterAccessNanos = builder.getExpireAfterAccessNanos();
        this.expireAfterWriteNanos = builder.getExpireAfterWriteNanos();
        this.refreshNanos = builder.getRefreshNanos();
        this.removalListener = builder.getRemovalListener();
        if (this.removalListener == NullListener.INSTANCE) {
            discardingQueue = discardingQueue();
        } else {
            discardingQueue = new ConcurrentLinkedQueue();
        }
        this.removalNotificationQueue = discardingQueue;
        this.ticker = builder.getTicker(recordsTime());
        this.entryFactory = EntryFactory.getFactory(this.keyStrength, usesAccessEntries(), usesWriteEntries());
        this.globalStatsCounter = (StatsCounter) builder.getStatsCounterSupplier().get();
        this.defaultLoader = loader;
        int initialCapacity = Math.min(builder.getInitialCapacity(), 1073741824);
        if (evictsBySize() && !customWeigher()) {
            initialCapacity = Math.min(initialCapacity, (int) this.maxWeight);
        }
        int segmentShift = 0;
        int segmentCount = 1;
        while (segmentCount < this.concurrencyLevel && (!evictsBySize() || ((long) (segmentCount * 20)) <= this.maxWeight)) {
            segmentShift++;
            segmentCount <<= 1;
        }
        this.segmentShift = 32 - segmentShift;
        this.segmentMask = segmentCount - 1;
        this.segments = newSegmentArray(segmentCount);
        int segmentCapacity = initialCapacity / segmentCount;
        if (segmentCapacity * segmentCount < initialCapacity) {
            segmentCapacity++;
        }
        int segmentSize = 1;
        while (segmentSize < segmentCapacity) {
            segmentSize <<= 1;
        }
        int i;
        if (evictsBySize()) {
            long maxSegmentWeight = (this.maxWeight / ((long) segmentCount)) + 1;
            long remainder = this.maxWeight % ((long) segmentCount);
            for (i = 0; i < this.segments.length; i++) {
                if (((long) i) == remainder) {
                    maxSegmentWeight--;
                }
                this.segments[i] = createSegment(segmentSize, maxSegmentWeight, (StatsCounter) builder.getStatsCounterSupplier().get());
            }
            return;
        }
        for (i = 0; i < this.segments.length; i++) {
            this.segments[i] = createSegment(segmentSize, -1, (StatsCounter) builder.getStatsCounterSupplier().get());
        }
    }

    boolean evictsBySize() {
        return this.maxWeight >= 0;
    }

    boolean customWeigher() {
        return this.weigher != OneWeigher.INSTANCE;
    }

    boolean expires() {
        return expiresAfterWrite() || expiresAfterAccess();
    }

    boolean expiresAfterWrite() {
        return this.expireAfterWriteNanos > 0;
    }

    boolean expiresAfterAccess() {
        return this.expireAfterAccessNanos > 0;
    }

    boolean refreshes() {
        return this.refreshNanos > 0;
    }

    boolean usesAccessQueue() {
        return expiresAfterAccess() || evictsBySize();
    }

    boolean usesWriteQueue() {
        return expiresAfterWrite();
    }

    boolean recordsWrite() {
        return expiresAfterWrite() || refreshes();
    }

    boolean recordsAccess() {
        return expiresAfterAccess();
    }

    boolean recordsTime() {
        return recordsWrite() || recordsAccess();
    }

    boolean usesWriteEntries() {
        return usesWriteQueue() || recordsWrite();
    }

    boolean usesAccessEntries() {
        return usesAccessQueue() || recordsAccess();
    }

    boolean usesKeyReferences() {
        return this.keyStrength != Strength.STRONG;
    }

    boolean usesValueReferences() {
        return this.valueStrength != Strength.STRONG;
    }

    static <K, V> ValueReference<K, V> unset() {
        return UNSET;
    }

    static <K, V> ReferenceEntry<K, V> nullEntry() {
        return NullEntry.INSTANCE;
    }

    static <E> Queue<E> discardingQueue() {
        return DISCARDING_QUEUE;
    }

    static int rehash(int h) {
        h += (h << 15) ^ -12931;
        h ^= h >>> 10;
        h += h << 3;
        h ^= h >>> 6;
        h += (h << 2) + (h << 14);
        return (h >>> 16) ^ h;
    }

    @VisibleForTesting
    ReferenceEntry<K, V> newEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
        Segment<K, V> segment = segmentFor(hash);
        segment.lock();
        try {
            ReferenceEntry<K, V> newEntry = segment.newEntry(key, hash, next);
            return newEntry;
        } finally {
            segment.unlock();
        }
    }

    @VisibleForTesting
    ReferenceEntry<K, V> copyEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
        return segmentFor(original.getHash()).copyEntry(original, newNext);
    }

    @VisibleForTesting
    ValueReference<K, V> newValueReference(ReferenceEntry<K, V> entry, V value, int weight) {
        return this.valueStrength.referenceValue(segmentFor(entry.getHash()), entry, Preconditions.checkNotNull(value), weight);
    }

    int hash(@Nullable Object key) {
        return rehash(this.keyEquivalence.hash(key));
    }

    void reclaimValue(ValueReference<K, V> valueReference) {
        ReferenceEntry<K, V> entry = valueReference.getEntry();
        int hash = entry.getHash();
        segmentFor(hash).reclaimValue(entry.getKey(), hash, valueReference);
    }

    void reclaimKey(ReferenceEntry<K, V> entry) {
        int hash = entry.getHash();
        segmentFor(hash).reclaimKey(entry, hash);
    }

    @VisibleForTesting
    boolean isLive(ReferenceEntry<K, V> entry, long now) {
        return segmentFor(entry.getHash()).getLiveValue(entry, now) != null;
    }

    Segment<K, V> segmentFor(int hash) {
        return this.segments[(hash >>> this.segmentShift) & this.segmentMask];
    }

    Segment<K, V> createSegment(int initialCapacity, long maxSegmentWeight, StatsCounter statsCounter) {
        return new Segment(this, initialCapacity, maxSegmentWeight, statsCounter);
    }

    @Nullable
    V getLiveValue(ReferenceEntry<K, V> entry, long now) {
        if (entry.getKey() == null) {
            return null;
        }
        V value = entry.getValueReference().get();
        if (value == null) {
            return null;
        }
        if (isExpired(entry, now)) {
            return null;
        }
        return value;
    }

    boolean isExpired(ReferenceEntry<K, V> entry, long now) {
        Preconditions.checkNotNull(entry);
        if (expiresAfterAccess() && now - entry.getAccessTime() >= this.expireAfterAccessNanos) {
            return true;
        }
        if (!expiresAfterWrite() || now - entry.getWriteTime() < this.expireAfterWriteNanos) {
            return false;
        }
        return true;
    }

    static <K, V> void connectAccessOrder(ReferenceEntry<K, V> previous, ReferenceEntry<K, V> next) {
        previous.setNextInAccessQueue(next);
        next.setPreviousInAccessQueue(previous);
    }

    static <K, V> void nullifyAccessOrder(ReferenceEntry<K, V> nulled) {
        ReferenceEntry<K, V> nullEntry = nullEntry();
        nulled.setNextInAccessQueue(nullEntry);
        nulled.setPreviousInAccessQueue(nullEntry);
    }

    static <K, V> void connectWriteOrder(ReferenceEntry<K, V> previous, ReferenceEntry<K, V> next) {
        previous.setNextInWriteQueue(next);
        next.setPreviousInWriteQueue(previous);
    }

    static <K, V> void nullifyWriteOrder(ReferenceEntry<K, V> nulled) {
        ReferenceEntry<K, V> nullEntry = nullEntry();
        nulled.setNextInWriteQueue(nullEntry);
        nulled.setPreviousInWriteQueue(nullEntry);
    }

    void processPendingNotifications() {
        while (true) {
            RemovalNotification<K, V> notification = (RemovalNotification) this.removalNotificationQueue.poll();
            if (notification != null) {
                try {
                    this.removalListener.onRemoval(notification);
                } catch (Throwable e) {
                    logger.log(Level.WARNING, "Exception thrown by removal listener", e);
                }
            } else {
                return;
            }
        }
    }

    final Segment<K, V>[] newSegmentArray(int ssize) {
        return new Segment[ssize];
    }

    public void cleanUp() {
        for (Segment<?, ?> segment : this.segments) {
            segment.cleanUp();
        }
    }

    public boolean isEmpty() {
        int i;
        long sum = 0;
        Segment<K, V>[] segments = this.segments;
        for (i = 0; i < segments.length; i++) {
            if (segments[i].count != 0) {
                return false;
            }
            sum += (long) segments[i].modCount;
        }
        if (sum != 0) {
            for (i = 0; i < segments.length; i++) {
                if (segments[i].count != 0) {
                    return false;
                }
                sum -= (long) segments[i].modCount;
            }
            if (sum != 0) {
                return false;
            }
        }
        return true;
    }

    long longSize() {
        long sum = 0;
        for (Segment segment : this.segments) {
            sum += (long) segment.count;
        }
        return sum;
    }

    public int size() {
        return Ints.saturatedCast(longSize());
    }

    @Nullable
    public V get(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key);
        return segmentFor(hash).get(key, hash);
    }

    @Nullable
    public V getIfPresent(Object key) {
        int hash = hash(Preconditions.checkNotNull(key));
        V value = segmentFor(hash).get(key, hash);
        if (value == null) {
            this.globalStatsCounter.recordMisses(1);
        } else {
            this.globalStatsCounter.recordHits(1);
        }
        return value;
    }

    V get(K key, CacheLoader<? super K, V> loader) throws ExecutionException {
        int hash = hash(Preconditions.checkNotNull(key));
        return segmentFor(hash).get(key, hash, loader);
    }

    V getOrLoad(K key) throws ExecutionException {
        return get(key, this.defaultLoader);
    }

    ImmutableMap<K, V> getAllPresent(Iterable<?> keys) {
        int hits = 0;
        int misses = 0;
        Map<K, V> result = Maps.newLinkedHashMap();
        for (K key : keys) {
            V value = get(key);
            if (value == null) {
                misses++;
            } else {
                result.put(key, value);
                hits++;
            }
        }
        this.globalStatsCounter.recordHits(hits);
        this.globalStatsCounter.recordMisses(misses);
        return ImmutableMap.copyOf(result);
    }

    ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
        int hits = 0;
        int misses = 0;
        Map<K, V> result = Maps.newLinkedHashMap();
        Set<K> keysToLoad = Sets.newLinkedHashSet();
        for (K key : keys) {
            V value = get(key);
            if (!result.containsKey(key)) {
                result.put(key, value);
                if (value == null) {
                    misses++;
                    keysToLoad.add(key);
                } else {
                    hits++;
                }
            }
        }
        try {
            if (!keysToLoad.isEmpty()) {
                Map<K, V> newEntries = loadAll(keysToLoad, this.defaultLoader);
                for (K key2 : keysToLoad) {
                    value = newEntries.get(key2);
                    if (value == null) {
                        String valueOf = String.valueOf(String.valueOf(key2));
                        throw new InvalidCacheLoadException(new StringBuilder(valueOf.length() + 37).append("loadAll failed to return a value for ").append(valueOf).toString());
                    }
                    result.put(key2, value);
                }
            }
        } catch (UnsupportedLoadingOperationException e) {
            for (K key22 : keysToLoad) {
                misses--;
                result.put(key22, get(key22, this.defaultLoader));
            }
        } catch (Throwable th) {
            this.globalStatsCounter.recordHits(hits);
            this.globalStatsCounter.recordMisses(misses);
        }
        ImmutableMap<K, V> copyOf = ImmutableMap.copyOf(result);
        this.globalStatsCounter.recordHits(hits);
        this.globalStatsCounter.recordMisses(misses);
        return copyOf;
    }

    @Nullable
    Map<K, V> loadAll(Set<? extends K> keys, CacheLoader<? super K, V> loader) throws ExecutionException {
        Preconditions.checkNotNull(loader);
        Preconditions.checkNotNull(keys);
        Stopwatch stopwatch = Stopwatch.createStarted();
        boolean success = false;
        try {
            Map<K, V> result = loader.loadAll(keys);
            if (!true) {
                this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
            }
            String valueOf;
            if (result == null) {
                this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
                valueOf = String.valueOf(String.valueOf(loader));
                throw new InvalidCacheLoadException(new StringBuilder(valueOf.length() + 31).append(valueOf).append(" returned null map from loadAll").toString());
            }
            stopwatch.stop();
            boolean nullsPresent = false;
            for (Entry<K, V> entry : result.entrySet()) {
                K key = entry.getKey();
                V value = entry.getValue();
                if (key == null || value == null) {
                    nullsPresent = true;
                } else {
                    put(key, value);
                }
            }
            if (nullsPresent) {
                this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
                valueOf = String.valueOf(String.valueOf(loader));
                throw new InvalidCacheLoadException(new StringBuilder(valueOf.length() + 42).append(valueOf).append(" returned null keys or values from loadAll").toString());
            }
            this.globalStatsCounter.recordLoadSuccess(stopwatch.elapsed(TimeUnit.NANOSECONDS));
            return result;
        } catch (UnsupportedLoadingOperationException e) {
            success = true;
            throw e;
        } catch (InterruptedException e2) {
            Thread.currentThread().interrupt();
            throw new ExecutionException(e2);
        } catch (RuntimeException e3) {
            throw new UncheckedExecutionException(e3);
        } catch (Exception e4) {
            throw new ExecutionException(e4);
        } catch (Error e5) {
            throw new ExecutionError(e5);
        } catch (Throwable th) {
            if (!success) {
                this.globalStatsCounter.recordLoadException(stopwatch.elapsed(TimeUnit.NANOSECONDS));
            }
        }
    }

    ReferenceEntry<K, V> getEntry(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key);
        return segmentFor(hash).getEntry(key, hash);
    }

    void refresh(K key) {
        int hash = hash(Preconditions.checkNotNull(key));
        segmentFor(hash).refresh(key, hash, this.defaultLoader, false);
    }

    public boolean containsKey(@Nullable Object key) {
        if (key == null) {
            return false;
        }
        int hash = hash(key);
        return segmentFor(hash).containsKey(key, hash);
    }

    public boolean containsValue(@Nullable Object value) {
        if (value == null) {
            return false;
        }
        long now = this.ticker.read();
        Segment<K, V>[] segments = this.segments;
        long last = -1;
        for (int i = 0; i < 3; i++) {
            long sum = 0;
            for (Segment<K, V> segment : segments) {
                int c = segment.count;
                AtomicReferenceArray<ReferenceEntry<K, V>> table = segment.table;
                for (int j = 0; j < table.length(); j++) {
                    for (ReferenceEntry<K, V> e = (ReferenceEntry) table.get(j); e != null; e = e.getNext()) {
                        V v = segment.getLiveValue(e, now);
                        if (v != null && this.valueEquivalence.equivalent(value, v)) {
                            return true;
                        }
                    }
                }
                sum += (long) segment.modCount;
            }
            if (sum == last) {
                break;
            }
            last = sum;
        }
        return false;
    }

    public V put(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = hash(key);
        return segmentFor(hash).put(key, hash, value, false);
    }

    public V putIfAbsent(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = hash(key);
        return segmentFor(hash).put(key, hash, value, true);
    }

    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> e : m.entrySet()) {
            put(e.getKey(), e.getValue());
        }
    }

    public V remove(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        int hash = hash(key);
        return segmentFor(hash).remove(key, hash);
    }

    public boolean remove(@Nullable Object key, @Nullable Object value) {
        if (key == null || value == null) {
            return false;
        }
        int hash = hash(key);
        return segmentFor(hash).remove(key, hash, value);
    }

    public boolean replace(K key, @Nullable V oldValue, V newValue) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(newValue);
        if (oldValue == null) {
            return false;
        }
        int hash = hash(key);
        return segmentFor(hash).replace(key, hash, oldValue, newValue);
    }

    public V replace(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        int hash = hash(key);
        return segmentFor(hash).replace(key, hash, value);
    }

    public void clear() {
        for (Segment<K, V> segment : this.segments) {
            segment.clear();
        }
    }

    void invalidateAll(Iterable<?> keys) {
        for (Object key : keys) {
            remove(key);
        }
    }

    public Set<K> keySet() {
        Set<K> ks = this.keySet;
        if (ks != null) {
            return ks;
        }
        ks = new KeySet(this, this);
        this.keySet = ks;
        return ks;
    }

    public Collection<V> values() {
        Collection<V> vs = this.values;
        if (vs != null) {
            return vs;
        }
        vs = new Values(this, this);
        this.values = vs;
        return vs;
    }

    @GwtIncompatible("Not supported.")
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> es = this.entrySet;
        if (es != null) {
            return es;
        }
        es = new EntrySet(this, this);
        this.entrySet = es;
        return es;
    }
}

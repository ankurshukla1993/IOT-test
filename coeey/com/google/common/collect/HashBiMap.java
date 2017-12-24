package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true)
public final class HashBiMap<K, V> extends AbstractMap<K, V> implements BiMap<K, V>, Serializable {
    private static final double LOAD_FACTOR = 1.0d;
    @GwtIncompatible("Not needed in emulated source")
    private static final long serialVersionUID = 0;
    private transient BiEntry<K, V>[] hashTableKToV;
    private transient BiEntry<K, V>[] hashTableVToK;
    private transient BiMap<V, K> inverse;
    private transient int mask;
    private transient int modCount;
    private transient int size;

    private static final class BiEntry<K, V> extends ImmutableEntry<K, V> {
        final int keyHash;
        @Nullable
        BiEntry<K, V> nextInKToVBucket;
        @Nullable
        BiEntry<K, V> nextInVToKBucket;
        final int valueHash;

        BiEntry(K key, int keyHash, V value, int valueHash) {
            super(key, value);
            this.keyHash = keyHash;
            this.valueHash = valueHash;
        }
    }

    public static <K, V> HashBiMap<K, V> create() {
        return create(16);
    }

    public static <K, V> HashBiMap<K, V> create(int expectedSize) {
        return new HashBiMap(expectedSize);
    }

    public static <K, V> HashBiMap<K, V> create(Map<? extends K, ? extends V> map) {
        HashBiMap<K, V> bimap = create(map.size());
        bimap.putAll(map);
        return bimap;
    }

    private HashBiMap(int expectedSize) {
        init(expectedSize);
    }

    private void init(int expectedSize) {
        CollectPreconditions.checkNonnegative(expectedSize, "expectedSize");
        int tableSize = Hashing.closedTableSize(expectedSize, LOAD_FACTOR);
        this.hashTableKToV = createTable(tableSize);
        this.hashTableVToK = createTable(tableSize);
        this.mask = tableSize - 1;
        this.modCount = 0;
        this.size = 0;
    }

    private void delete(BiEntry<K, V> entry) {
        BiEntry<K, V> bucketEntry;
        int keyBucket = entry.keyHash & this.mask;
        BiEntry<K, V> prevBucketEntry = null;
        for (bucketEntry = this.hashTableKToV[keyBucket]; bucketEntry != entry; bucketEntry = bucketEntry.nextInKToVBucket) {
            prevBucketEntry = bucketEntry;
        }
        if (prevBucketEntry == null) {
            this.hashTableKToV[keyBucket] = entry.nextInKToVBucket;
        } else {
            prevBucketEntry.nextInKToVBucket = entry.nextInKToVBucket;
        }
        int valueBucket = entry.valueHash & this.mask;
        prevBucketEntry = null;
        for (bucketEntry = this.hashTableVToK[valueBucket]; bucketEntry != entry; bucketEntry = bucketEntry.nextInVToKBucket) {
            prevBucketEntry = bucketEntry;
        }
        if (prevBucketEntry == null) {
            this.hashTableVToK[valueBucket] = entry.nextInVToKBucket;
        } else {
            prevBucketEntry.nextInVToKBucket = entry.nextInVToKBucket;
        }
        this.size--;
        this.modCount++;
    }

    private void insert(BiEntry<K, V> entry) {
        int keyBucket = entry.keyHash & this.mask;
        entry.nextInKToVBucket = this.hashTableKToV[keyBucket];
        this.hashTableKToV[keyBucket] = entry;
        int valueBucket = entry.valueHash & this.mask;
        entry.nextInVToKBucket = this.hashTableVToK[valueBucket];
        this.hashTableVToK[valueBucket] = entry;
        this.size++;
        this.modCount++;
    }

    private static int hash(@Nullable Object o) {
        return Hashing.smear(o == null ? 0 : o.hashCode());
    }

    private BiEntry<K, V> seekByKey(@Nullable Object key, int keyHash) {
        BiEntry<K, V> entry = this.hashTableKToV[this.mask & keyHash];
        while (entry != null) {
            if (keyHash == entry.keyHash && Objects.equal(key, entry.key)) {
                return entry;
            }
            entry = entry.nextInKToVBucket;
        }
        return null;
    }

    private BiEntry<K, V> seekByValue(@Nullable Object value, int valueHash) {
        BiEntry<K, V> entry = this.hashTableVToK[this.mask & valueHash];
        while (entry != null) {
            if (valueHash == entry.valueHash && Objects.equal(value, entry.value)) {
                return entry;
            }
            entry = entry.nextInVToKBucket;
        }
        return null;
    }

    public boolean containsKey(@Nullable Object key) {
        return seekByKey(key, hash(key)) != null;
    }

    public boolean containsValue(@Nullable Object value) {
        return seekByValue(value, hash(value)) != null;
    }

    @Nullable
    public V get(@Nullable Object key) {
        BiEntry<K, V> entry = seekByKey(key, hash(key));
        return entry == null ? null : entry.value;
    }

    public V put(@Nullable K key, @Nullable V value) {
        return put(key, value, false);
    }

    public V forcePut(@Nullable K key, @Nullable V value) {
        return put(key, value, true);
    }

    private V put(@Nullable K key, @Nullable V value, boolean force) {
        int keyHash = hash(key);
        int valueHash = hash(value);
        BiEntry<K, V> oldEntryForKey = seekByKey(key, keyHash);
        if (oldEntryForKey != null && valueHash == oldEntryForKey.valueHash && Objects.equal(value, oldEntryForKey.value)) {
            return value;
        }
        V v;
        BiEntry<K, V> oldEntryForValue = seekByValue(value, valueHash);
        if (oldEntryForValue != null) {
            if (force) {
                delete(oldEntryForValue);
            } else {
                String valueOf = String.valueOf(String.valueOf(value));
                throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 23).append("value already present: ").append(valueOf).toString());
            }
        }
        if (oldEntryForKey != null) {
            delete(oldEntryForKey);
        }
        insert(new BiEntry(key, keyHash, value, valueHash));
        rehashIfNecessary();
        if (oldEntryForKey == null) {
            v = null;
        } else {
            v = oldEntryForKey.value;
        }
        return v;
    }

    @Nullable
    private K putInverse(@Nullable V value, @Nullable K key, boolean force) {
        int valueHash = hash(value);
        int keyHash = hash(key);
        BiEntry<K, V> oldEntryForValue = seekByValue(value, valueHash);
        if (oldEntryForValue != null && keyHash == oldEntryForValue.keyHash && Objects.equal(key, oldEntryForValue.key)) {
            return key;
        }
        K k;
        BiEntry<K, V> oldEntryForKey = seekByKey(key, keyHash);
        if (oldEntryForKey != null) {
            if (force) {
                delete(oldEntryForKey);
            } else {
                String valueOf = String.valueOf(String.valueOf(key));
                throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 23).append("value already present: ").append(valueOf).toString());
            }
        }
        if (oldEntryForValue != null) {
            delete(oldEntryForValue);
        }
        insert(new BiEntry(key, keyHash, value, valueHash));
        rehashIfNecessary();
        if (oldEntryForValue == null) {
            k = null;
        } else {
            k = oldEntryForValue.key;
        }
        return k;
    }

    private void rehashIfNecessary() {
        BiEntry<K, V>[] oldKToV = this.hashTableKToV;
        if (Hashing.needsResizing(this.size, oldKToV.length, LOAD_FACTOR)) {
            int newTableSize = oldKToV.length * 2;
            this.hashTableKToV = createTable(newTableSize);
            this.hashTableVToK = createTable(newTableSize);
            this.mask = newTableSize - 1;
            this.size = 0;
            for (BiEntry<K, V> entry : oldKToV) {
                BiEntry<K, V> entry2;
                while (entry2 != null) {
                    BiEntry<K, V> nextEntry = entry2.nextInKToVBucket;
                    insert(entry2);
                    entry2 = nextEntry;
                }
            }
            this.modCount++;
        }
    }

    private BiEntry<K, V>[] createTable(int length) {
        return new BiEntry[length];
    }

    public V remove(@Nullable Object key) {
        BiEntry<K, V> entry = seekByKey(key, hash(key));
        if (entry == null) {
            return null;
        }
        delete(entry);
        return entry.value;
    }

    public void clear() {
        this.size = 0;
        Arrays.fill(this.hashTableKToV, null);
        Arrays.fill(this.hashTableVToK, null);
        this.modCount++;
    }

    public int size() {
        return this.size;
    }

    public Set<K> keySet() {
        return new KeySet(this);
    }

    public Set<V> values() {
        return inverse().keySet();
    }

    public Set<Entry<K, V>> entrySet() {
        return new EntrySet(this, null);
    }

    public BiMap<V, K> inverse() {
        if (this.inverse != null) {
            return this.inverse;
        }
        BiMap<V, K> inverse = new Inverse(this, null);
        this.inverse = inverse;
        return inverse;
    }

    @GwtIncompatible("java.io.ObjectOutputStream")
    private void writeObject(ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        Serialization.writeMap(this, stream);
    }

    @GwtIncompatible("java.io.ObjectInputStream")
    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        int size = Serialization.readCount(stream);
        init(size);
        Serialization.populateMap(this, stream, size);
    }
}

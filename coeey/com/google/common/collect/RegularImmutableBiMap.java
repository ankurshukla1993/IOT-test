package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import java.util.Map.Entry;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
class RegularImmutableBiMap<K, V> extends ImmutableBiMap<K, V> {
    static final double MAX_LOAD_FACTOR = 1.2d;
    private final transient ImmutableMapEntry<K, V>[] entries;
    private final transient int hashCode;
    private transient ImmutableBiMap<V, K> inverse;
    private final transient ImmutableMapEntry<K, V>[] keyTable;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] valueTable;

    private static final class NonTerminalBiMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        @Nullable
        private final ImmutableMapEntry<K, V> nextInKeyBucket;
        @Nullable
        private final ImmutableMapEntry<K, V> nextInValueBucket;

        NonTerminalBiMapEntry(K key, V value, @Nullable ImmutableMapEntry<K, V> nextInKeyBucket, @Nullable ImmutableMapEntry<K, V> nextInValueBucket) {
            super(key, value);
            this.nextInKeyBucket = nextInKeyBucket;
            this.nextInValueBucket = nextInValueBucket;
        }

        NonTerminalBiMapEntry(ImmutableMapEntry<K, V> contents, @Nullable ImmutableMapEntry<K, V> nextInKeyBucket, @Nullable ImmutableMapEntry<K, V> nextInValueBucket) {
            super(contents);
            this.nextInKeyBucket = nextInKeyBucket;
            this.nextInValueBucket = nextInValueBucket;
        }

        @Nullable
        ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }

        @Nullable
        ImmutableMapEntry<K, V> getNextInValueBucket() {
            return this.nextInValueBucket;
        }
    }

    RegularImmutableBiMap(TerminalEntry<?, ?>... entriesToAdd) {
        this(entriesToAdd.length, entriesToAdd);
    }

    RegularImmutableBiMap(int n, TerminalEntry<?, ?>[] entriesToAdd) {
        int tableSize = Hashing.closedTableSize(n, MAX_LOAD_FACTOR);
        this.mask = tableSize - 1;
        ImmutableMapEntry<K, V>[] keyTable = createEntryArray(tableSize);
        ImmutableMapEntry<K, V>[] valueTable = createEntryArray(tableSize);
        ImmutableMapEntry<K, V>[] entries = createEntryArray(n);
        int hashCode = 0;
        for (int i = 0; i < n; i++) {
            ImmutableMapEntry<K, V> entry = entriesToAdd[i];
            K key = entry.getKey();
            V value = entry.getValue();
            int keyHash = key.hashCode();
            int valueHash = value.hashCode();
            int keyBucket = Hashing.smear(keyHash) & this.mask;
            int valueBucket = Hashing.smear(valueHash) & this.mask;
            ImmutableMapEntry<K, V> nextInKeyBucket = keyTable[keyBucket];
            for (ImmutableMapEntry<K, V> keyEntry = nextInKeyBucket; keyEntry != null; keyEntry = keyEntry.getNextInKeyBucket()) {
                ImmutableMap.checkNoConflict(!key.equals(keyEntry.getKey()), "key", entry, keyEntry);
            }
            ImmutableMapEntry<K, V> nextInValueBucket = valueTable[valueBucket];
            for (ImmutableMapEntry<K, V> valueEntry = nextInValueBucket; valueEntry != null; valueEntry = valueEntry.getNextInValueBucket()) {
                ImmutableMap.checkNoConflict(!value.equals(valueEntry.getValue()), "value", entry, valueEntry);
            }
            ImmutableMapEntry<K, V> newEntry = (nextInKeyBucket == null && nextInValueBucket == null) ? entry : new NonTerminalBiMapEntry(entry, nextInKeyBucket, nextInValueBucket);
            keyTable[keyBucket] = newEntry;
            valueTable[valueBucket] = newEntry;
            entries[i] = newEntry;
            hashCode += keyHash ^ valueHash;
        }
        this.keyTable = keyTable;
        this.valueTable = valueTable;
        this.entries = entries;
        this.hashCode = hashCode;
    }

    RegularImmutableBiMap(Entry<?, ?>[] entriesToAdd) {
        int n = entriesToAdd.length;
        int tableSize = Hashing.closedTableSize(n, MAX_LOAD_FACTOR);
        this.mask = tableSize - 1;
        ImmutableMapEntry<K, V>[] keyTable = createEntryArray(tableSize);
        ImmutableMapEntry<K, V>[] valueTable = createEntryArray(tableSize);
        ImmutableMapEntry<K, V>[] entries = createEntryArray(n);
        int hashCode = 0;
        for (int i = 0; i < n; i++) {
            Entry<K, V> entry = entriesToAdd[i];
            K key = entry.getKey();
            V value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int keyHash = key.hashCode();
            int valueHash = value.hashCode();
            int keyBucket = Hashing.smear(keyHash) & this.mask;
            int valueBucket = Hashing.smear(valueHash) & this.mask;
            ImmutableMapEntry<K, V> nextInKeyBucket = keyTable[keyBucket];
            for (ImmutableMapEntry<K, V> keyEntry = nextInKeyBucket; keyEntry != null; keyEntry = keyEntry.getNextInKeyBucket()) {
                ImmutableMap.checkNoConflict(!key.equals(keyEntry.getKey()), "key", entry, keyEntry);
            }
            ImmutableMapEntry<K, V> nextInValueBucket = valueTable[valueBucket];
            for (ImmutableMapEntry<K, V> valueEntry = nextInValueBucket; valueEntry != null; valueEntry = valueEntry.getNextInValueBucket()) {
                ImmutableMap.checkNoConflict(!value.equals(valueEntry.getValue()), "value", entry, valueEntry);
            }
            ImmutableMapEntry<K, V> newEntry = (nextInKeyBucket == null && nextInValueBucket == null) ? new TerminalEntry(key, value) : new NonTerminalBiMapEntry(key, value, nextInKeyBucket, nextInValueBucket);
            keyTable[keyBucket] = newEntry;
            valueTable[valueBucket] = newEntry;
            entries[i] = newEntry;
            hashCode += keyHash ^ valueHash;
        }
        this.keyTable = keyTable;
        this.valueTable = valueTable;
        this.entries = entries;
        this.hashCode = hashCode;
    }

    private static <K, V> ImmutableMapEntry<K, V>[] createEntryArray(int length) {
        return new ImmutableMapEntry[length];
    }

    @Nullable
    public V get(@Nullable Object key) {
        if (key == null) {
            return null;
        }
        for (ImmutableMapEntry<K, V> entry = this.keyTable[Hashing.smear(key.hashCode()) & this.mask]; entry != null; entry = entry.getNextInKeyBucket()) {
            if (key.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }

    ImmutableSet<Entry<K, V>> createEntrySet() {
        return new 1(this);
    }

    boolean isPartialView() {
        return false;
    }

    public int size() {
        return this.entries.length;
    }

    public ImmutableBiMap<V, K> inverse() {
        ImmutableBiMap<V, K> immutableBiMap = this.inverse;
        if (immutableBiMap != null) {
            return immutableBiMap;
        }
        immutableBiMap = new Inverse(this, null);
        this.inverse = immutableBiMap;
        return immutableBiMap;
    }
}

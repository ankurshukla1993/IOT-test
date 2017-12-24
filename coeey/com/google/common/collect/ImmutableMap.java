package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.EnumMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
public abstract class ImmutableMap<K, V> implements Map<K, V>, Serializable {
    private static final Entry<?, ?>[] EMPTY_ENTRY_ARRAY = new Entry[0];
    private transient ImmutableSet<Entry<K, V>> entrySet;
    private transient ImmutableSet<K> keySet;
    private transient ImmutableSetMultimap<K, V> multimapView;
    private transient ImmutableCollection<V> values;

    abstract ImmutableSet<Entry<K, V>> createEntrySet();

    public abstract V get(@Nullable Object obj);

    abstract boolean isPartialView();

    public static <K, V> ImmutableMap<K, V> of() {
        return ImmutableBiMap.of();
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1) {
        return ImmutableBiMap.of(k1, v1);
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2) {
        return new RegularImmutableMap(new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2)});
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3) {
        return new RegularImmutableMap(new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3)});
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) {
        return new RegularImmutableMap(new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4)});
    }

    public static <K, V> ImmutableMap<K, V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) {
        return new RegularImmutableMap(new TerminalEntry[]{entryOf(k1, v1), entryOf(k2, v2), entryOf(k3, v3), entryOf(k4, v4), entryOf(k5, v5)});
    }

    static <K, V> TerminalEntry<K, V> entryOf(K key, V value) {
        CollectPreconditions.checkEntryNotNull(key, value);
        return new TerminalEntry(key, value);
    }

    public static <K, V> Builder<K, V> builder() {
        return new Builder();
    }

    static void checkNoConflict(boolean safe, String conflictDescription, Entry<?, ?> entry1, Entry<?, ?> entry2) {
        if (!safe) {
            String valueOf = String.valueOf(String.valueOf(conflictDescription));
            String valueOf2 = String.valueOf(String.valueOf(entry1));
            String valueOf3 = String.valueOf(String.valueOf(entry2));
            throw new IllegalArgumentException(new StringBuilder(((valueOf.length() + 34) + valueOf2.length()) + valueOf3.length()).append("Multiple entries with same ").append(valueOf).append(": ").append(valueOf2).append(" and ").append(valueOf3).toString());
        }
    }

    public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> map) {
        if ((map instanceof ImmutableMap) && !(map instanceof ImmutableSortedMap)) {
            ImmutableMap<K, V> kvMap = (ImmutableMap) map;
            if (!kvMap.isPartialView()) {
                return kvMap;
            }
        } else if (map instanceof EnumMap) {
            return copyOfEnumMapUnsafe(map);
        }
        Entry[] entries = (Entry[]) map.entrySet().toArray(EMPTY_ENTRY_ARRAY);
        switch (entries.length) {
            case 0:
                return of();
            case 1:
                Entry<K, V> onlyEntry = entries[0];
                return of(onlyEntry.getKey(), onlyEntry.getValue());
            default:
                return new RegularImmutableMap(entries);
        }
    }

    private static <K, V> ImmutableMap<K, V> copyOfEnumMapUnsafe(Map<? extends K, ? extends V> map) {
        return copyOfEnumMap((EnumMap) map);
    }

    private static <K extends Enum<K>, V> ImmutableMap<K, V> copyOfEnumMap(Map<K, ? extends V> original) {
        EnumMap<K, V> copy = new EnumMap(original);
        for (Entry<?, ?> entry : copy.entrySet()) {
            CollectPreconditions.checkEntryNotNull(entry.getKey(), entry.getValue());
        }
        return ImmutableEnumMap.asImmutable(copy);
    }

    ImmutableMap() {
    }

    @Deprecated
    public final V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final V remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void clear() {
        throw new UnsupportedOperationException();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean containsKey(@Nullable Object key) {
        return get(key) != null;
    }

    public boolean containsValue(@Nullable Object value) {
        return values().contains(value);
    }

    public ImmutableSet<Entry<K, V>> entrySet() {
        ImmutableSet<Entry<K, V>> immutableSet = this.entrySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        immutableSet = createEntrySet();
        this.entrySet = immutableSet;
        return immutableSet;
    }

    public ImmutableSet<K> keySet() {
        ImmutableSet<K> immutableSet = this.keySet;
        if (immutableSet != null) {
            return immutableSet;
        }
        immutableSet = createKeySet();
        this.keySet = immutableSet;
        return immutableSet;
    }

    ImmutableSet<K> createKeySet() {
        return new ImmutableMapKeySet(this);
    }

    public ImmutableCollection<V> values() {
        ImmutableCollection<V> immutableCollection = this.values;
        if (immutableCollection != null) {
            return immutableCollection;
        }
        immutableCollection = new ImmutableMapValues(this);
        this.values = immutableCollection;
        return immutableCollection;
    }

    @Beta
    public ImmutableSetMultimap<K, V> asMultimap() {
        ImmutableSetMultimap<K, V> immutableSetMultimap = this.multimapView;
        if (immutableSetMultimap != null) {
            return immutableSetMultimap;
        }
        immutableSetMultimap = createMultimapView();
        this.multimapView = immutableSetMultimap;
        return immutableSetMultimap;
    }

    private ImmutableSetMultimap<K, V> createMultimapView() {
        ImmutableMap<K, ImmutableSet<V>> map = viewMapValuesAsSingletonSets();
        return new ImmutableSetMultimap(map, map.size(), null);
    }

    private ImmutableMap<K, ImmutableSet<V>> viewMapValuesAsSingletonSets() {
        return new MapViewOfValuesAsSingletonSets(this);
    }

    public boolean equals(@Nullable Object object) {
        return Maps.equalsImpl(this, object);
    }

    public int hashCode() {
        return entrySet().hashCode();
    }

    public String toString() {
        return Maps.toStringImpl(this);
    }

    Object writeReplace() {
        return new SerializedForm(this);
    }
}

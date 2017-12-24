package com.google.common.collect;

import com.google.common.collect.ImmutableMapEntry.TerminalEntry;
import java.util.Map;
import java.util.Map.Entry;

public class ImmutableMap$Builder<K, V> {
    TerminalEntry<K, V>[] entries;
    int size;

    public ImmutableMap$Builder() {
        this(4);
    }

    ImmutableMap$Builder(int initialCapacity) {
        this.entries = new TerminalEntry[initialCapacity];
        this.size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > this.entries.length) {
            this.entries = (TerminalEntry[]) ObjectArrays.arraysCopyOf(this.entries, ImmutableCollection$Builder.expandedCapacity(this.entries.length, minCapacity));
        }
    }

    public ImmutableMap$Builder<K, V> put(K key, V value) {
        ensureCapacity(this.size + 1);
        TerminalEntry<K, V> entry = ImmutableMap.entryOf(key, value);
        TerminalEntry[] terminalEntryArr = this.entries;
        int i = this.size;
        this.size = i + 1;
        terminalEntryArr[i] = entry;
        return this;
    }

    public ImmutableMap$Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
        return put(entry.getKey(), entry.getValue());
    }

    public ImmutableMap$Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.size + map.size());
        for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry);
        }
        return this;
    }

    public ImmutableMap<K, V> build() {
        switch (this.size) {
            case 0:
                return ImmutableMap.of();
            case 1:
                return ImmutableMap.of(this.entries[0].getKey(), this.entries[0].getValue());
            default:
                return new RegularImmutableMap(this.size, this.entries);
        }
    }
}

package com.google.common.collect;

import javax.annotation.Nullable;

final class RegularImmutableMap$NonTerminalMapEntry<K, V> extends ImmutableMapEntry<K, V> {
    private final ImmutableMapEntry<K, V> nextInKeyBucket;

    RegularImmutableMap$NonTerminalMapEntry(K key, V value, ImmutableMapEntry<K, V> nextInKeyBucket) {
        super(key, value);
        this.nextInKeyBucket = nextInKeyBucket;
    }

    RegularImmutableMap$NonTerminalMapEntry(ImmutableMapEntry<K, V> contents, ImmutableMapEntry<K, V> nextInKeyBucket) {
        super(contents);
        this.nextInKeyBucket = nextInKeyBucket;
    }

    ImmutableMapEntry<K, V> getNextInKeyBucket() {
        return this.nextInKeyBucket;
    }

    @Nullable
    ImmutableMapEntry<K, V> getNextInValueBucket() {
        return null;
    }
}

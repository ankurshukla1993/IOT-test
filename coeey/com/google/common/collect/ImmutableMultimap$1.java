package com.google.common.collect;

import java.util.Map.Entry;

class ImmutableMultimap$1 extends ImmutableMultimap$Itr<Entry<K, V>> {
    final /* synthetic */ ImmutableMultimap this$0;

    ImmutableMultimap$1(ImmutableMultimap immutableMultimap) {
        this.this$0 = immutableMultimap;
        super(immutableMultimap);
    }

    Entry<K, V> output(K key, V value) {
        return Maps.immutableEntry(key, value);
    }
}

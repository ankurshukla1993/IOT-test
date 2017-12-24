package com.google.common.collect;

import java.util.Map.Entry;

class RegularImmutableBiMap$1 extends ImmutableMapEntrySet<K, V> {
    final /* synthetic */ RegularImmutableBiMap this$0;

    RegularImmutableBiMap$1(RegularImmutableBiMap regularImmutableBiMap) {
        this.this$0 = regularImmutableBiMap;
    }

    ImmutableMap<K, V> map() {
        return this.this$0;
    }

    public UnmodifiableIterator<Entry<K, V>> iterator() {
        return asList().iterator();
    }

    ImmutableList<Entry<K, V>> createAsList() {
        return new RegularImmutableAsList((ImmutableCollection) this, RegularImmutableBiMap.access$000(this.this$0));
    }

    boolean isHashCodeFast() {
        return true;
    }

    public int hashCode() {
        return RegularImmutableBiMap.access$100(this.this$0);
    }
}

package com.google.common.collect;

import java.util.Map.Entry;
import javax.annotation.Nullable;

final class RegularImmutableBiMap$Inverse extends ImmutableBiMap<V, K> {
    final /* synthetic */ RegularImmutableBiMap this$0;

    final class InverseEntrySet extends ImmutableMapEntrySet<V, K> {

        class C17081 extends ImmutableAsList<Entry<V, K>> {
            C17081() {
            }

            public Entry<V, K> get(int index) {
                Entry<K, V> entry = RegularImmutableBiMap.access$000(RegularImmutableBiMap$Inverse.this.this$0)[index];
                return Maps.immutableEntry(entry.getValue(), entry.getKey());
            }

            ImmutableCollection<Entry<V, K>> delegateCollection() {
                return InverseEntrySet.this;
            }
        }

        InverseEntrySet() {
        }

        ImmutableMap<V, K> map() {
            return RegularImmutableBiMap$Inverse.this;
        }

        boolean isHashCodeFast() {
            return true;
        }

        public int hashCode() {
            return RegularImmutableBiMap.access$100(RegularImmutableBiMap$Inverse.this.this$0);
        }

        public UnmodifiableIterator<Entry<V, K>> iterator() {
            return asList().iterator();
        }

        ImmutableList<Entry<V, K>> createAsList() {
            return new C17081();
        }
    }

    private RegularImmutableBiMap$Inverse(RegularImmutableBiMap regularImmutableBiMap) {
        this.this$0 = regularImmutableBiMap;
    }

    public int size() {
        return inverse().size();
    }

    public ImmutableBiMap<K, V> inverse() {
        return this.this$0;
    }

    public K get(@Nullable Object value) {
        if (value == null) {
            return null;
        }
        for (ImmutableMapEntry<K, V> entry = RegularImmutableBiMap.access$400(this.this$0)[Hashing.smear(value.hashCode()) & RegularImmutableBiMap.access$300(this.this$0)]; entry != null; entry = entry.getNextInValueBucket()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    ImmutableSet<Entry<V, K>> createEntrySet() {
        return new InverseEntrySet();
    }

    boolean isPartialView() {
        return false;
    }

    Object writeReplace() {
        return new RegularImmutableBiMap$InverseSerializedForm(this.this$0);
    }
}

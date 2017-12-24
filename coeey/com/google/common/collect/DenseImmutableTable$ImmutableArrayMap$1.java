package com.google.common.collect;

import com.google.common.collect.DenseImmutableTable.ImmutableArrayMap;
import java.util.Map.Entry;

class DenseImmutableTable$ImmutableArrayMap$1 extends ImmutableMapEntrySet<K, V> {
    final /* synthetic */ ImmutableArrayMap this$0;

    class C16001 extends AbstractIterator<Entry<K, V>> {
        private int index = -1;
        private final int maxIndex = DenseImmutableTable$ImmutableArrayMap$1.this.this$0.keyToIndex().size();

        C16001() {
        }

        protected Entry<K, V> computeNext() {
            this.index++;
            while (this.index < this.maxIndex) {
                V value = DenseImmutableTable$ImmutableArrayMap$1.this.this$0.getValue(this.index);
                if (value != null) {
                    return Maps.immutableEntry(DenseImmutableTable$ImmutableArrayMap$1.this.this$0.getKey(this.index), value);
                }
                this.index++;
            }
            return (Entry) endOfData();
        }
    }

    DenseImmutableTable$ImmutableArrayMap$1(ImmutableArrayMap immutableArrayMap) {
        this.this$0 = immutableArrayMap;
    }

    ImmutableMap<K, V> map() {
        return this.this$0;
    }

    public UnmodifiableIterator<Entry<K, V>> iterator() {
        return new C16001();
    }
}

package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashBiMap.BiEntry;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

final class HashBiMap$EntrySet extends Maps$EntrySet<K, V> {
    final /* synthetic */ HashBiMap this$0;

    class C16101 extends HashBiMap$Itr<Entry<K, V>> {

        class MapEntry extends AbstractMapEntry<K, V> {
            BiEntry<K, V> delegate;

            MapEntry(BiEntry<K, V> entry) {
                this.delegate = entry;
            }

            public K getKey() {
                return this.delegate.key;
            }

            public V getValue() {
                return this.delegate.value;
            }

            public V setValue(V value) {
                V oldValue = this.delegate.value;
                int valueHash = HashBiMap.access$300(value);
                if (valueHash == this.delegate.valueHash && Objects.equal(value, oldValue)) {
                    return value;
                }
                boolean z;
                if (HashBiMap.access$600(HashBiMap$EntrySet.this.this$0, value, valueHash) == null) {
                    z = true;
                } else {
                    z = false;
                }
                Preconditions.checkArgument(z, "value already present: %s", value);
                HashBiMap.access$200(HashBiMap$EntrySet.this.this$0, this.delegate);
                BiEntry<K, V> newEntry = new BiEntry(this.delegate.key, this.delegate.keyHash, value, valueHash);
                HashBiMap.access$700(HashBiMap$EntrySet.this.this$0, newEntry);
                C16101.this.expectedModCount = HashBiMap.access$000(HashBiMap$EntrySet.this.this$0);
                if (C16101.this.toRemove == this.delegate) {
                    C16101.this.toRemove = newEntry;
                }
                this.delegate = newEntry;
                return oldValue;
            }
        }

        C16101() {
            super(HashBiMap$EntrySet.this.this$0);
        }

        Entry<K, V> output(BiEntry<K, V> entry) {
            return new MapEntry(entry);
        }
    }

    private HashBiMap$EntrySet(HashBiMap hashBiMap) {
        this.this$0 = hashBiMap;
    }

    Map<K, V> map() {
        return this.this$0;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new C16101();
    }
}

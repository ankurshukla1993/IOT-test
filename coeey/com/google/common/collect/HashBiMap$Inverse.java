package com.google.common.collect;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashBiMap.BiEntry;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

final class HashBiMap$Inverse extends AbstractMap<V, K> implements BiMap<V, K>, Serializable {
    final /* synthetic */ HashBiMap this$0;

    class C16121 extends Maps$EntrySet<V, K> {

        class C16111 extends HashBiMap$Itr<Entry<V, K>> {

            class InverseEntry extends AbstractMapEntry<V, K> {
                BiEntry<K, V> delegate;

                InverseEntry(BiEntry<K, V> entry) {
                    this.delegate = entry;
                }

                public V getKey() {
                    return this.delegate.value;
                }

                public K getValue() {
                    return this.delegate.key;
                }

                public K setValue(K key) {
                    K oldKey = this.delegate.key;
                    int keyHash = HashBiMap.access$300(key);
                    if (keyHash == this.delegate.keyHash && Objects.equal(key, oldKey)) {
                        return key;
                    }
                    boolean z;
                    if (HashBiMap.access$400(HashBiMap$Inverse.this.this$0, key, keyHash) == null) {
                        z = true;
                    } else {
                        z = false;
                    }
                    Preconditions.checkArgument(z, "value already present: %s", key);
                    HashBiMap.access$200(HashBiMap$Inverse.this.this$0, this.delegate);
                    HashBiMap.access$700(HashBiMap$Inverse.this.this$0, new BiEntry(key, keyHash, this.delegate.value, this.delegate.valueHash));
                    C16111.this.expectedModCount = HashBiMap.access$000(HashBiMap$Inverse.this.this$0);
                    return oldKey;
                }
            }

            C16111() {
                super(HashBiMap$Inverse.this.this$0);
            }

            Entry<V, K> output(BiEntry<K, V> entry) {
                return new InverseEntry(entry);
            }
        }

        C16121() {
        }

        Map<V, K> map() {
            return HashBiMap$Inverse.this;
        }

        public Iterator<Entry<V, K>> iterator() {
            return new C16111();
        }
    }

    private final class InverseKeySet extends Maps$KeySet<V, K> {

        class C16131 extends HashBiMap$Itr<V> {
            C16131() {
                super(HashBiMap$Inverse.this.this$0);
            }

            V output(BiEntry<K, V> entry) {
                return entry.value;
            }
        }

        InverseKeySet() {
            super(HashBiMap$Inverse.this);
        }

        public boolean remove(@Nullable Object o) {
            BiEntry<K, V> entry = HashBiMap.access$600(HashBiMap$Inverse.this.this$0, o, HashBiMap.access$300(o));
            if (entry == null) {
                return false;
            }
            HashBiMap.access$200(HashBiMap$Inverse.this.this$0, entry);
            return true;
        }

        public Iterator<V> iterator() {
            return new C16131();
        }
    }

    private HashBiMap$Inverse(HashBiMap hashBiMap) {
        this.this$0 = hashBiMap;
    }

    BiMap<K, V> forward() {
        return this.this$0;
    }

    public int size() {
        return HashBiMap.access$900(this.this$0);
    }

    public void clear() {
        forward().clear();
    }

    public boolean containsKey(@Nullable Object value) {
        return forward().containsValue(value);
    }

    public K get(@Nullable Object value) {
        BiEntry<K, V> entry = HashBiMap.access$600(this.this$0, value, HashBiMap.access$300(value));
        return entry == null ? null : entry.key;
    }

    public K put(@Nullable V value, @Nullable K key) {
        return HashBiMap.access$1000(this.this$0, value, key, false);
    }

    public K forcePut(@Nullable V value, @Nullable K key) {
        return HashBiMap.access$1000(this.this$0, value, key, true);
    }

    public K remove(@Nullable Object value) {
        BiEntry<K, V> entry = HashBiMap.access$600(this.this$0, value, HashBiMap.access$300(value));
        if (entry == null) {
            return null;
        }
        HashBiMap.access$200(this.this$0, entry);
        return entry.key;
    }

    public BiMap<K, V> inverse() {
        return forward();
    }

    public Set<V> keySet() {
        return new InverseKeySet();
    }

    public Set<K> values() {
        return forward().keySet();
    }

    public Set<Entry<V, K>> entrySet() {
        return new C16121();
    }

    Object writeReplace() {
        return new HashBiMap$InverseSerializedForm(this.this$0);
    }
}

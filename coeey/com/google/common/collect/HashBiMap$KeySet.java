package com.google.common.collect;

import com.google.common.collect.HashBiMap.BiEntry;
import java.util.Iterator;
import javax.annotation.Nullable;

final class HashBiMap$KeySet extends Maps$KeySet<K, V> {
    final /* synthetic */ HashBiMap this$0;

    class C16141 extends HashBiMap$Itr<K> {
        C16141() {
            super(HashBiMap$KeySet.this.this$0);
        }

        K output(BiEntry<K, V> entry) {
            return entry.key;
        }
    }

    HashBiMap$KeySet(HashBiMap hashBiMap) {
        this.this$0 = hashBiMap;
        super(hashBiMap);
    }

    public Iterator<K> iterator() {
        return new C16141();
    }

    public boolean remove(@Nullable Object o) {
        BiEntry<K, V> entry = HashBiMap.access$400(this.this$0, o, HashBiMap.access$300(o));
        if (entry == null) {
            return false;
        }
        HashBiMap.access$200(this.this$0, entry);
        return true;
    }
}

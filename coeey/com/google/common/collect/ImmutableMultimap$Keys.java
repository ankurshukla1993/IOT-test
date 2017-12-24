package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

class ImmutableMultimap$Keys extends ImmutableMultiset<K> {
    final /* synthetic */ ImmutableMultimap this$0;

    ImmutableMultimap$Keys(ImmutableMultimap immutableMultimap) {
        this.this$0 = immutableMultimap;
    }

    public boolean contains(@Nullable Object object) {
        return this.this$0.containsKey(object);
    }

    public int count(@Nullable Object element) {
        Collection<V> values = (Collection) this.this$0.map.get(element);
        return values == null ? 0 : values.size();
    }

    public Set<K> elementSet() {
        return this.this$0.keySet();
    }

    public int size() {
        return this.this$0.size();
    }

    Entry<K> getEntry(int index) {
        Map.Entry<K, ? extends Collection<V>> entry = (Map.Entry) this.this$0.map.entrySet().asList().get(index);
        return Multisets.immutableEntry(entry.getKey(), ((Collection) entry.getValue()).size());
    }

    boolean isPartialView() {
        return true;
    }
}

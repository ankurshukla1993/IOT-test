package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map.Entry;

abstract class ImmutableMultimap$Itr<T> extends UnmodifiableIterator<T> {
    K key;
    final Iterator<Entry<K, Collection<V>>> mapIterator;
    final /* synthetic */ ImmutableMultimap this$0;
    Iterator<V> valueIterator;

    abstract T output(K k, V v);

    private ImmutableMultimap$Itr(ImmutableMultimap immutableMultimap) {
        this.this$0 = immutableMultimap;
        this.mapIterator = this.this$0.asMap().entrySet().iterator();
        this.key = null;
        this.valueIterator = Iterators.emptyIterator();
    }

    public boolean hasNext() {
        return this.mapIterator.hasNext() || this.valueIterator.hasNext();
    }

    public T next() {
        if (!this.valueIterator.hasNext()) {
            Entry<K, Collection<V>> mapEntry = (Entry) this.mapIterator.next();
            this.key = mapEntry.getKey();
            this.valueIterator = ((Collection) mapEntry.getValue()).iterator();
        }
        return output(this.key, this.valueIterator.next());
    }
}

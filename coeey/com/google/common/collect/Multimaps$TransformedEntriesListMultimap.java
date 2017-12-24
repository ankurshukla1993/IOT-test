package com.google.common.collect;

import java.util.Collection;
import java.util.List;

final class Multimaps$TransformedEntriesListMultimap<K, V1, V2> extends Multimaps$TransformedEntriesMultimap<K, V1, V2> implements ListMultimap<K, V2> {
    Multimaps$TransformedEntriesListMultimap(ListMultimap<K, V1> fromMultimap, Maps$EntryTransformer<? super K, ? super V1, V2> transformer) {
        super(fromMultimap, transformer);
    }

    List<V2> transform(K key, Collection<V1> values) {
        return Lists.transform((List) values, Maps.asValueToValueFunction(this.transformer, key));
    }

    public List<V2> get(K key) {
        return transform((Object) key, this.fromMultimap.get(key));
    }

    public List<V2> removeAll(Object key) {
        return transform(key, this.fromMultimap.removeAll(key));
    }

    public List<V2> replaceValues(K k, Iterable<? extends V2> iterable) {
        throw new UnsupportedOperationException();
    }
}

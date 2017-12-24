package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Map.Entry;

class Maps$11 implements Function<Entry<K, V1>, Entry<K, V2>> {
    final /* synthetic */ Maps$EntryTransformer val$transformer;

    Maps$11(Maps$EntryTransformer maps$EntryTransformer) {
        this.val$transformer = maps$EntryTransformer;
    }

    public Entry<K, V2> apply(Entry<K, V1> entry) {
        return Maps.transformEntry(this.val$transformer, entry);
    }
}

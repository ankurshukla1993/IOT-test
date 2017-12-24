package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Map.Entry;

class Maps$9 implements Function<Entry<K, V1>, V2> {
    final /* synthetic */ Maps$EntryTransformer val$transformer;

    Maps$9(Maps$EntryTransformer maps$EntryTransformer) {
        this.val$transformer = maps$EntryTransformer;
    }

    public V2 apply(Entry<K, V1> entry) {
        return this.val$transformer.transformEntry(entry.getKey(), entry.getValue());
    }
}

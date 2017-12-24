package com.google.common.collect;

import com.google.common.base.Function;
import javax.annotation.Nullable;

class Maps$8 implements Function<V1, V2> {
    final /* synthetic */ Object val$key;
    final /* synthetic */ Maps$EntryTransformer val$transformer;

    Maps$8(Maps$EntryTransformer maps$EntryTransformer, Object obj) {
        this.val$transformer = maps$EntryTransformer;
        this.val$key = obj;
    }

    public V2 apply(@Nullable V1 v1) {
        return this.val$transformer.transformEntry(this.val$key, v1);
    }
}

package com.google.common.collect;

import com.google.common.base.Function;

class Maps$7 implements Maps$EntryTransformer<K, V1, V2> {
    final /* synthetic */ Function val$function;

    Maps$7(Function function) {
        this.val$function = function;
    }

    public V2 transformEntry(K k, V1 value) {
        return this.val$function.apply(value);
    }
}

package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Iterator;
import java.util.Map.Entry;

class Maps$2 extends TransformedIterator<K, Entry<K, V>> {
    final /* synthetic */ Function val$function;

    Maps$2(Iterator x0, Function function) {
        this.val$function = function;
        super(x0);
    }

    Entry<K, V> transform(K key) {
        return Maps.immutableEntry(key, this.val$function.apply(key));
    }
}

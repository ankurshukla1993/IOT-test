package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Iterator;

class Iterators$8 extends TransformedIterator<F, T> {
    final /* synthetic */ Function val$function;

    Iterators$8(Iterator x0, Function function) {
        this.val$function = function;
        super(x0);
    }

    T transform(F from) {
        return this.val$function.apply(from);
    }
}

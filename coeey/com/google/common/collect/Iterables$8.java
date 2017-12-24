package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Iterator;

class Iterables$8 extends FluentIterable<T> {
    final /* synthetic */ Iterable val$fromIterable;
    final /* synthetic */ Function val$function;

    Iterables$8(Iterable iterable, Function function) {
        this.val$fromIterable = iterable;
        this.val$function = function;
    }

    public Iterator<T> iterator() {
        return Iterators.transform(this.val$fromIterable.iterator(), this.val$function);
    }
}

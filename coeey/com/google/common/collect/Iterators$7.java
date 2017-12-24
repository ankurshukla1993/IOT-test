package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Iterator;

class Iterators$7 extends AbstractIterator<T> {
    final /* synthetic */ Predicate val$predicate;
    final /* synthetic */ Iterator val$unfiltered;

    Iterators$7(Iterator it, Predicate predicate) {
        this.val$unfiltered = it;
        this.val$predicate = predicate;
    }

    protected T computeNext() {
        while (this.val$unfiltered.hasNext()) {
            T element = this.val$unfiltered.next();
            if (this.val$predicate.apply(element)) {
                return element;
            }
        }
        return endOfData();
    }
}

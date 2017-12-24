package com.google.common.collect;

import java.util.Iterator;
import java.util.List;

class Iterables$9 extends FluentIterable<T> {
    final /* synthetic */ List val$list;
    final /* synthetic */ int val$numberToSkip;

    Iterables$9(List list, int i) {
        this.val$list = list;
        this.val$numberToSkip = i;
    }

    public Iterator<T> iterator() {
        return this.val$list.subList(Math.min(this.val$list.size(), this.val$numberToSkip), this.val$list.size()).iterator();
    }
}

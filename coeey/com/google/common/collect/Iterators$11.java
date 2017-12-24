package com.google.common.collect;

class Iterators$11 extends AbstractIndexedListIterator<T> {
    final /* synthetic */ Object[] val$array;
    final /* synthetic */ int val$offset;

    Iterators$11(int x0, int x1, Object[] objArr, int i) {
        this.val$array = objArr;
        this.val$offset = i;
        super(x0, x1);
    }

    protected T get(int index) {
        return this.val$array[this.val$offset + index];
    }
}

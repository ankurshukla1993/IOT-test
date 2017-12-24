package com.google.common.collect;

class ImmutableList$1 extends AbstractIndexedListIterator<E> {
    final /* synthetic */ ImmutableList this$0;

    ImmutableList$1(ImmutableList immutableList, int x0, int x1) {
        this.this$0 = immutableList;
        super(x0, x1);
    }

    protected E get(int index) {
        return this.this$0.get(index);
    }
}

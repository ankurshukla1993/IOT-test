package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.ListIterator;

class ImmutableList$SubList extends ImmutableList<E> {
    final transient int length;
    final transient int offset;
    final /* synthetic */ ImmutableList this$0;

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public /* bridge */ /* synthetic */ ListIterator listIterator() {
        return super.listIterator();
    }

    public /* bridge */ /* synthetic */ ListIterator listIterator(int x0) {
        return super.listIterator(x0);
    }

    ImmutableList$SubList(ImmutableList immutableList, int offset, int length) {
        this.this$0 = immutableList;
        this.offset = offset;
        this.length = length;
    }

    public int size() {
        return this.length;
    }

    public E get(int index) {
        Preconditions.checkElementIndex(index, this.length);
        return this.this$0.get(this.offset + index);
    }

    public ImmutableList<E> subList(int fromIndex, int toIndex) {
        Preconditions.checkPositionIndexes(fromIndex, toIndex, this.length);
        return this.this$0.subList(this.offset + fromIndex, this.offset + toIndex);
    }

    boolean isPartialView() {
        return true;
    }
}

package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.ListIterator;
import javax.annotation.Nullable;

class ImmutableList$ReverseImmutableList<E> extends ImmutableList<E> {
    private final transient ImmutableList<E> forwardList;

    public /* bridge */ /* synthetic */ Iterator iterator() {
        return super.iterator();
    }

    public /* bridge */ /* synthetic */ ListIterator listIterator() {
        return super.listIterator();
    }

    public /* bridge */ /* synthetic */ ListIterator listIterator(int x0) {
        return super.listIterator(x0);
    }

    ImmutableList$ReverseImmutableList(ImmutableList<E> backingList) {
        this.forwardList = backingList;
    }

    private int reverseIndex(int index) {
        return (size() - 1) - index;
    }

    private int reversePosition(int index) {
        return size() - index;
    }

    public ImmutableList<E> reverse() {
        return this.forwardList;
    }

    public boolean contains(@Nullable Object object) {
        return this.forwardList.contains(object);
    }

    public int indexOf(@Nullable Object object) {
        int index = this.forwardList.lastIndexOf(object);
        return index >= 0 ? reverseIndex(index) : -1;
    }

    public int lastIndexOf(@Nullable Object object) {
        int index = this.forwardList.indexOf(object);
        return index >= 0 ? reverseIndex(index) : -1;
    }

    public ImmutableList<E> subList(int fromIndex, int toIndex) {
        Preconditions.checkPositionIndexes(fromIndex, toIndex, size());
        return this.forwardList.subList(reversePosition(toIndex), reversePosition(fromIndex)).reverse();
    }

    public E get(int index) {
        Preconditions.checkElementIndex(index, size());
        return this.forwardList.get(reverseIndex(index));
    }

    public int size() {
        return this.forwardList.size();
    }

    boolean isPartialView() {
        return this.forwardList.isPartialView();
    }
}

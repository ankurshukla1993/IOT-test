package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Collection;

abstract class ImmutableCollection$ArrayBasedBuilder<E> extends ImmutableCollection$Builder<E> {
    Object[] contents;
    int size = 0;

    ImmutableCollection$ArrayBasedBuilder(int initialCapacity) {
        CollectPreconditions.checkNonnegative(initialCapacity, "initialCapacity");
        this.contents = new Object[initialCapacity];
    }

    private void ensureCapacity(int minCapacity) {
        if (this.contents.length < minCapacity) {
            this.contents = ObjectArrays.arraysCopyOf(this.contents, ImmutableCollection$Builder.expandedCapacity(this.contents.length, minCapacity));
        }
    }

    public ImmutableCollection$ArrayBasedBuilder<E> add(E element) {
        Preconditions.checkNotNull(element);
        ensureCapacity(this.size + 1);
        Object[] objArr = this.contents;
        int i = this.size;
        this.size = i + 1;
        objArr[i] = element;
        return this;
    }

    public ImmutableCollection$Builder<E> add(E... elements) {
        ObjectArrays.checkElementsNotNull(elements);
        ensureCapacity(this.size + elements.length);
        System.arraycopy(elements, 0, this.contents, this.size, elements.length);
        this.size += elements.length;
        return this;
    }

    public ImmutableCollection$Builder<E> addAll(Iterable<? extends E> elements) {
        if (elements instanceof Collection) {
            ensureCapacity(this.size + ((Collection) elements).size());
        }
        super.addAll((Iterable) elements);
        return this;
    }
}

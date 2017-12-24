package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.io.Serializable;
import java.util.Comparator;

final class ImmutableSortedMultiset$SerializedForm<E> implements Serializable {
    Comparator<? super E> comparator;
    int[] counts;
    E[] elements;

    ImmutableSortedMultiset$SerializedForm(SortedMultiset<E> multiset) {
        this.comparator = multiset.comparator();
        int n = multiset.entrySet().size();
        this.elements = new Object[n];
        this.counts = new int[n];
        int i = 0;
        for (Entry<E> entry : multiset.entrySet()) {
            this.elements[i] = entry.getElement();
            this.counts[i] = entry.getCount();
            i++;
        }
    }

    Object readResolve() {
        int n = this.elements.length;
        ImmutableSortedMultiset$Builder<E> builder = new ImmutableSortedMultiset$Builder(this.comparator);
        for (int i = 0; i < n; i++) {
            builder.addCopies(this.elements[i], this.counts[i]);
        }
        return builder.build();
    }
}

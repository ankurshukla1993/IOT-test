package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.io.Serializable;

class ImmutableMultiset$SerializedForm implements Serializable {
    private static final long serialVersionUID = 0;
    final int[] counts;
    final Object[] elements;

    ImmutableMultiset$SerializedForm(Multiset<?> multiset) {
        int distinct = multiset.entrySet().size();
        this.elements = new Object[distinct];
        this.counts = new int[distinct];
        int i = 0;
        for (Entry<?> entry : multiset.entrySet()) {
            this.elements[i] = entry.getElement();
            this.counts[i] = entry.getCount();
            i++;
        }
    }

    Object readResolve() {
        LinkedHashMultiset<Object> multiset = LinkedHashMultiset.create(this.elements.length);
        for (int i = 0; i < this.elements.length; i++) {
            multiset.add(this.elements[i], this.counts[i]);
        }
        return ImmutableMultiset.copyOf(multiset);
    }
}

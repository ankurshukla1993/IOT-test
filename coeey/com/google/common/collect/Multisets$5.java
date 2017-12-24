package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import com.google.common.primitives.Ints;

class Multisets$5 extends Ordering<Entry<?>> {
    Multisets$5() {
    }

    public int compare(Entry<?> entry1, Entry<?> entry2) {
        return Ints.compare(entry2.getCount(), entry1.getCount());
    }
}

package com.google.common.hash;

import java.io.Serializable;

class BloomFilter$SerialForm<T> implements Serializable {
    private static final long serialVersionUID = 1;
    final long[] data;
    final Funnel<? super T> funnel;
    final int numHashFunctions;
    final BloomFilter$Strategy strategy;

    BloomFilter$SerialForm(BloomFilter<T> bf) {
        this.data = BloomFilter.access$000(bf).data;
        this.numHashFunctions = BloomFilter.access$100(bf);
        this.funnel = BloomFilter.access$200(bf);
        this.strategy = BloomFilter.access$300(bf);
    }

    Object readResolve() {
        return new BloomFilter(new BitArray(this.data), this.numHashFunctions, this.funnel, this.strategy, null);
    }
}

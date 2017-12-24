package com.google.common.hash;

import java.io.Serializable;

interface BloomFilter$Strategy extends Serializable {
    <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, BitArray bitArray);

    int ordinal();

    <T> boolean put(T t, Funnel<? super T> funnel, int i, BitArray bitArray);
}

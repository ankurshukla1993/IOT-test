package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.Arrays;
import java.util.BitSet;

class CharMatcher$11 extends CharMatcher {
    final /* synthetic */ char[] val$chars;

    CharMatcher$11(String x0, char[] cArr) {
        this.val$chars = cArr;
        super(x0);
    }

    public /* bridge */ /* synthetic */ boolean apply(Object x0) {
        return super.apply((Character) x0);
    }

    public boolean matches(char c) {
        return Arrays.binarySearch(this.val$chars, c) >= 0;
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet table) {
        for (char c : this.val$chars) {
            table.set(c);
        }
    }
}

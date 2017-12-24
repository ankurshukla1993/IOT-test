package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$13 extends CharMatcher$FastMatcher {
    final /* synthetic */ char val$endInclusive;
    final /* synthetic */ char val$startInclusive;

    CharMatcher$13(String x0, char c, char c2) {
        this.val$startInclusive = c;
        this.val$endInclusive = c2;
        super(x0);
    }

    public boolean matches(char c) {
        return this.val$startInclusive <= c && c <= this.val$endInclusive;
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet table) {
        table.set(this.val$startInclusive, this.val$endInclusive + 1);
    }
}

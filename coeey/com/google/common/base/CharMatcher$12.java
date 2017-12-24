package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$12 extends CharMatcher$FastMatcher {
    final /* synthetic */ char val$match1;
    final /* synthetic */ char val$match2;

    CharMatcher$12(String x0, char c, char c2) {
        this.val$match1 = c;
        this.val$match2 = c2;
        super(x0);
    }

    public boolean matches(char c) {
        return c == this.val$match1 || c == this.val$match2;
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet table) {
        table.set(this.val$match1);
        table.set(this.val$match2);
    }
}

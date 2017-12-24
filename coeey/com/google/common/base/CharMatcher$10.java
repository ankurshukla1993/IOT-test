package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$10 extends CharMatcher$FastMatcher {
    final /* synthetic */ char val$match;

    CharMatcher$10(String x0, char c) {
        this.val$match = c;
        super(x0);
    }

    public boolean matches(char c) {
        return c != this.val$match;
    }

    public CharMatcher and(CharMatcher other) {
        return other.matches(this.val$match) ? super.and(other) : other;
    }

    public CharMatcher or(CharMatcher other) {
        return other.matches(this.val$match) ? ANY : this;
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet table) {
        table.set(0, this.val$match);
        table.set(this.val$match + 1, 65536);
    }

    public CharMatcher negate() {
        return is(this.val$match);
    }
}

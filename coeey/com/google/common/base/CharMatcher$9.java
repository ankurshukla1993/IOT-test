package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$9 extends CharMatcher$FastMatcher {
    final /* synthetic */ char val$match;

    CharMatcher$9(String x0, char c) {
        this.val$match = c;
        super(x0);
    }

    public boolean matches(char c) {
        return c == this.val$match;
    }

    public String replaceFrom(CharSequence sequence, char replacement) {
        return sequence.toString().replace(this.val$match, replacement);
    }

    public CharMatcher and(CharMatcher other) {
        return other.matches(this.val$match) ? this : NONE;
    }

    public CharMatcher or(CharMatcher other) {
        return other.matches(this.val$match) ? other : super.or(other);
    }

    public CharMatcher negate() {
        return isNot(this.val$match);
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet table) {
        table.set(this.val$match);
    }
}

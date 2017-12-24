package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$NegatedMatcher extends CharMatcher {
    final CharMatcher original;

    public /* bridge */ /* synthetic */ boolean apply(Object x0) {
        return super.apply((Character) x0);
    }

    CharMatcher$NegatedMatcher(String toString, CharMatcher original) {
        super(toString);
        this.original = original;
    }

    CharMatcher$NegatedMatcher(CharMatcher original) {
        String valueOf = String.valueOf(String.valueOf(original));
        this(new StringBuilder(valueOf.length() + 9).append(valueOf).append(".negate()").toString(), original);
    }

    public boolean matches(char c) {
        return !this.original.matches(c);
    }

    public boolean matchesAllOf(CharSequence sequence) {
        return this.original.matchesNoneOf(sequence);
    }

    public boolean matchesNoneOf(CharSequence sequence) {
        return this.original.matchesAllOf(sequence);
    }

    public int countIn(CharSequence sequence) {
        return sequence.length() - this.original.countIn(sequence);
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet table) {
        BitSet tmp = new BitSet();
        this.original.setBits(tmp);
        tmp.flip(0, 65536);
        table.or(tmp);
    }

    public CharMatcher negate() {
        return this.original;
    }

    CharMatcher withToString(String description) {
        return new CharMatcher$NegatedMatcher(description, this.original);
    }
}

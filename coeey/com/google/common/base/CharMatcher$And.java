package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$And extends CharMatcher {
    final CharMatcher first;
    final CharMatcher second;

    public /* bridge */ /* synthetic */ boolean apply(Object x0) {
        return super.apply((Character) x0);
    }

    CharMatcher$And(CharMatcher a, CharMatcher b) {
        String valueOf = String.valueOf(String.valueOf(a));
        String valueOf2 = String.valueOf(String.valueOf(b));
        this(a, b, new StringBuilder((valueOf.length() + 19) + valueOf2.length()).append("CharMatcher.and(").append(valueOf).append(", ").append(valueOf2).append(")").toString());
    }

    CharMatcher$And(CharMatcher a, CharMatcher b, String description) {
        super(description);
        this.first = (CharMatcher) Preconditions.checkNotNull(a);
        this.second = (CharMatcher) Preconditions.checkNotNull(b);
    }

    public boolean matches(char c) {
        return this.first.matches(c) && this.second.matches(c);
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet table) {
        BitSet tmp1 = new BitSet();
        this.first.setBits(tmp1);
        BitSet tmp2 = new BitSet();
        this.second.setBits(tmp2);
        tmp1.and(tmp2);
        table.or(tmp1);
    }

    CharMatcher withToString(String description) {
        return new CharMatcher$And(this.first, this.second, description);
    }
}

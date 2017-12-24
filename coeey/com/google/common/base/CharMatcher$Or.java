package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$Or extends CharMatcher {
    final CharMatcher first;
    final CharMatcher second;

    public /* bridge */ /* synthetic */ boolean apply(Object x0) {
        return super.apply((Character) x0);
    }

    CharMatcher$Or(CharMatcher a, CharMatcher b, String description) {
        super(description);
        this.first = (CharMatcher) Preconditions.checkNotNull(a);
        this.second = (CharMatcher) Preconditions.checkNotNull(b);
    }

    CharMatcher$Or(CharMatcher a, CharMatcher b) {
        String valueOf = String.valueOf(String.valueOf(a));
        String valueOf2 = String.valueOf(String.valueOf(b));
        this(a, b, new StringBuilder((valueOf.length() + 18) + valueOf2.length()).append("CharMatcher.or(").append(valueOf).append(", ").append(valueOf2).append(")").toString());
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet table) {
        this.first.setBits(table);
        this.second.setBits(table);
    }

    public boolean matches(char c) {
        return this.first.matches(c) || this.second.matches(c);
    }

    CharMatcher withToString(String description) {
        return new CharMatcher$Or(this.first, this.second, description);
    }
}

package com.google.common.base;

class CharMatcher$3 extends CharMatcher {
    CharMatcher$3(String x0) {
        super(x0);
    }

    public /* bridge */ /* synthetic */ boolean apply(Object x0) {
        return super.apply((Character) x0);
    }

    public boolean matches(char c) {
        return Character.isLetter(c);
    }
}

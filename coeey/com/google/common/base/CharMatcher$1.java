package com.google.common.base;

class CharMatcher$1 extends CharMatcher {
    CharMatcher$1() {
    }

    public /* bridge */ /* synthetic */ boolean apply(Object x0) {
        return super.apply((Character) x0);
    }

    public boolean matches(char c) {
        switch (c) {
            case '\t':
            case '\n':
            case '\u000b':
            case '\f':
            case '\r':
            case ' ':
            case '':
            case ' ':
            case ' ':
            case ' ':
            case ' ':
            case '　':
                return true;
            case ' ':
                return false;
            default:
                if (c < ' ' || c > ' ') {
                    return false;
                }
                return true;
        }
    }

    public String toString() {
        return "CharMatcher.BREAKING_WHITESPACE";
    }
}

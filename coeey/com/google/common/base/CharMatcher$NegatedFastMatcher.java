package com.google.common.base;

final class CharMatcher$NegatedFastMatcher extends CharMatcher$NegatedMatcher {
    CharMatcher$NegatedFastMatcher(CharMatcher original) {
        super(original);
    }

    CharMatcher$NegatedFastMatcher(String toString, CharMatcher original) {
        super(toString, original);
    }

    public final CharMatcher precomputed() {
        return this;
    }

    CharMatcher withToString(String description) {
        return new CharMatcher$NegatedFastMatcher(description, this.original);
    }
}

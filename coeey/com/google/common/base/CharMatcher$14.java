package com.google.common.base;

class CharMatcher$14 extends CharMatcher {
    final /* synthetic */ Predicate val$predicate;

    CharMatcher$14(String x0, Predicate predicate) {
        this.val$predicate = predicate;
        super(x0);
    }

    public boolean matches(char c) {
        return this.val$predicate.apply(Character.valueOf(c));
    }

    public boolean apply(Character character) {
        return this.val$predicate.apply(Preconditions.checkNotNull(character));
    }
}

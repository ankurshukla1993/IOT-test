package com.google.common.base;

import java.util.Arrays;

class CharMatcher$RangesMatcher extends CharMatcher {
    private final char[] rangeEnds;
    private final char[] rangeStarts;

    public /* bridge */ /* synthetic */ boolean apply(Object x0) {
        return super.apply((Character) x0);
    }

    CharMatcher$RangesMatcher(String description, char[] rangeStarts, char[] rangeEnds) {
        boolean z;
        super(description);
        this.rangeStarts = rangeStarts;
        this.rangeEnds = rangeEnds;
        if (rangeStarts.length == rangeEnds.length) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        for (int i = 0; i < rangeStarts.length; i++) {
            if (rangeStarts[i] <= rangeEnds[i]) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z);
            if (i + 1 < rangeStarts.length) {
                if (rangeEnds[i] < rangeStarts[i + 1]) {
                    z = true;
                } else {
                    z = false;
                }
                Preconditions.checkArgument(z);
            }
        }
    }

    public boolean matches(char c) {
        int index = Arrays.binarySearch(this.rangeStarts, c);
        if (index >= 0) {
            return true;
        }
        index = (index ^ -1) - 1;
        if (index < 0 || c > this.rangeEnds[index]) {
            return false;
        }
        return true;
    }
}

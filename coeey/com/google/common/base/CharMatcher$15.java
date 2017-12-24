package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$15 extends CharMatcher$FastMatcher {
    CharMatcher$15(String x0) {
        super(x0);
    }

    public boolean matches(char c) {
        return " 　\r   　 \u000b　   　 \t     \f 　 　　 \n 　".charAt((1682554634 * c) >>> WHITESPACE_SHIFT) == c;
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet table) {
        for (int i = 0; i < " 　\r   　 \u000b　   　 \t     \f 　 　　 \n 　".length(); i++) {
            table.set(" 　\r   　 \u000b　   　 \t     \f 　 　　 \n 　".charAt(i));
        }
    }
}

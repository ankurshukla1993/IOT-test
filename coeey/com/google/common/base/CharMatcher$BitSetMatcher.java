package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

@GwtIncompatible("java.util.BitSet")
class CharMatcher$BitSetMatcher extends CharMatcher$FastMatcher {
    private final BitSet table;

    private CharMatcher$BitSetMatcher(BitSet table, String description) {
        super(description);
        if (table.length() + 64 < table.size()) {
            table = (BitSet) table.clone();
        }
        this.table = table;
    }

    public boolean matches(char c) {
        return this.table.get(c);
    }

    void setBits(BitSet bitSet) {
        bitSet.or(this.table);
    }
}

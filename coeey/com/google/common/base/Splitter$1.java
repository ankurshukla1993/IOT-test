package com.google.common.base;

class Splitter$1 implements Splitter$Strategy {
    final /* synthetic */ CharMatcher val$separatorMatcher;

    Splitter$1(CharMatcher charMatcher) {
        this.val$separatorMatcher = charMatcher;
    }

    public Splitter$SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
        return new Splitter$SplittingIterator(splitter, toSplit) {
            int separatorStart(int start) {
                return Splitter$1.this.val$separatorMatcher.indexIn(this.toSplit, start);
            }

            int separatorEnd(int separatorPosition) {
                return separatorPosition + 1;
            }
        };
    }
}

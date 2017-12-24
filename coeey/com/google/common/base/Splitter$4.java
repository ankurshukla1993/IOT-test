package com.google.common.base;

class Splitter$4 implements Splitter$Strategy {
    final /* synthetic */ int val$length;

    Splitter$4(int i) {
        this.val$length = i;
    }

    public Splitter$SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
        return new Splitter$SplittingIterator(splitter, toSplit) {
            public int separatorStart(int start) {
                int nextChunkStart = start + Splitter$4.this.val$length;
                return nextChunkStart < this.toSplit.length() ? nextChunkStart : -1;
            }

            public int separatorEnd(int separatorPosition) {
                return separatorPosition;
            }
        };
    }
}

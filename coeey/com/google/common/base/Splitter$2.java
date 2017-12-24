package com.google.common.base;

class Splitter$2 implements Splitter$Strategy {
    final /* synthetic */ String val$separator;

    Splitter$2(String str) {
        this.val$separator = str;
    }

    public Splitter$SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
        return new Splitter$SplittingIterator(splitter, toSplit) {
            public int separatorStart(int start) {
                int separatorLength = Splitter$2.this.val$separator.length();
                int p = start;
                int last = this.toSplit.length() - separatorLength;
                while (p <= last) {
                    int i = 0;
                    while (i < separatorLength) {
                        if (this.toSplit.charAt(i + p) != Splitter$2.this.val$separator.charAt(i)) {
                            p++;
                        } else {
                            i++;
                        }
                    }
                    return p;
                }
                return -1;
            }

            public int separatorEnd(int separatorPosition) {
                return Splitter$2.this.val$separator.length() + separatorPosition;
            }
        };
    }
}

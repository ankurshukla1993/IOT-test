package com.google.common.base;

abstract class Splitter$SplittingIterator extends AbstractIterator<String> {
    int limit;
    int offset = 0;
    final boolean omitEmptyStrings;
    final CharSequence toSplit;
    final CharMatcher trimmer;

    abstract int separatorEnd(int i);

    abstract int separatorStart(int i);

    protected Splitter$SplittingIterator(Splitter splitter, CharSequence toSplit) {
        this.trimmer = Splitter.access$200(splitter);
        this.omitEmptyStrings = Splitter.access$300(splitter);
        this.limit = Splitter.access$400(splitter);
        this.toSplit = toSplit;
    }

    protected String computeNext() {
        int nextStart = this.offset;
        while (this.offset != -1) {
            int end;
            int start = nextStart;
            int separatorPosition = separatorStart(this.offset);
            if (separatorPosition == -1) {
                end = this.toSplit.length();
                this.offset = -1;
            } else {
                end = separatorPosition;
                this.offset = separatorEnd(separatorPosition);
            }
            if (this.offset == nextStart) {
                this.offset++;
                if (this.offset >= this.toSplit.length()) {
                    this.offset = -1;
                }
            } else {
                while (start < end && this.trimmer.matches(this.toSplit.charAt(start))) {
                    start++;
                }
                while (end > start && this.trimmer.matches(this.toSplit.charAt(end - 1))) {
                    end--;
                }
                if (this.omitEmptyStrings && start == end) {
                    nextStart = this.offset;
                } else {
                    if (this.limit == 1) {
                        end = this.toSplit.length();
                        this.offset = -1;
                        while (end > start && this.trimmer.matches(this.toSplit.charAt(end - 1))) {
                            end--;
                        }
                    } else {
                        this.limit--;
                    }
                    return this.toSplit.subSequence(start, end).toString();
                }
            }
        }
        return (String) endOfData();
    }
}

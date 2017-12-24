package com.google.common.collect;

class Range$3 extends Ordering<Range<?>> {
    Range$3() {
    }

    public int compare(Range<?> left, Range<?> right) {
        return ComparisonChain.start().compare(left.lowerBound, right.lowerBound).compare(left.upperBound, right.upperBound).result();
    }
}

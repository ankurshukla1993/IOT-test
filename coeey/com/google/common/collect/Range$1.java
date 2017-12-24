package com.google.common.collect;

import com.google.common.base.Function;

class Range$1 implements Function<Range, Cut> {
    Range$1() {
    }

    public Cut apply(Range range) {
        return range.lowerBound;
    }
}

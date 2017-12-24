package com.google.common.collect;

import com.google.common.base.Function;

class Range$2 implements Function<Range, Cut> {
    Range$2() {
    }

    public Cut apply(Range range) {
        return range.upperBound;
    }
}

package com.google.common.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Splitter$3 implements Splitter$Strategy {
    final /* synthetic */ Pattern val$separatorPattern;

    Splitter$3(Pattern pattern) {
        this.val$separatorPattern = pattern;
    }

    public Splitter$SplittingIterator iterator(Splitter splitter, CharSequence toSplit) {
        final Matcher matcher = this.val$separatorPattern.matcher(toSplit);
        return new Splitter$SplittingIterator(splitter, toSplit) {
            public int separatorStart(int start) {
                return matcher.find(start) ? matcher.start() : -1;
            }

            public int separatorEnd(int separatorPosition) {
                return matcher.end();
            }
        };
    }
}

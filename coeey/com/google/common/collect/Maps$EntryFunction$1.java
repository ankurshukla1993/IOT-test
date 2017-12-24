package com.google.common.collect;

import com.google.common.collect.Maps.EntryFunction;
import java.util.Map.Entry;
import javax.annotation.Nullable;

enum Maps$EntryFunction$1 extends EntryFunction {
    Maps$EntryFunction$1(String str, int i) {
        super(str, i, null);
    }

    @Nullable
    public Object apply(Entry<?, ?> entry) {
        return entry.getKey();
    }
}

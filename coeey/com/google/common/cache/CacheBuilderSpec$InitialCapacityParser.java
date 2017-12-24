package com.google.common.cache;

import com.google.common.base.Preconditions;

class CacheBuilderSpec$InitialCapacityParser extends CacheBuilderSpec$IntegerParser {
    CacheBuilderSpec$InitialCapacityParser() {
    }

    protected void parseInteger(CacheBuilderSpec spec, int value) {
        boolean z;
        if (spec.initialCapacity == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "initial capacity was already set to ", spec.initialCapacity);
        spec.initialCapacity = Integer.valueOf(value);
    }
}

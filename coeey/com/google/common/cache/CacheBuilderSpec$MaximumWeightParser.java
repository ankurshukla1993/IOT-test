package com.google.common.cache;

import com.google.common.base.Preconditions;

class CacheBuilderSpec$MaximumWeightParser extends CacheBuilderSpec$LongParser {
    CacheBuilderSpec$MaximumWeightParser() {
    }

    protected void parseLong(CacheBuilderSpec spec, long value) {
        boolean z;
        Preconditions.checkArgument(spec.maximumWeight == null, "maximum weight was already set to ", spec.maximumWeight);
        if (spec.maximumSize == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "maximum size was already set to ", spec.maximumSize);
        spec.maximumWeight = Long.valueOf(value);
    }
}

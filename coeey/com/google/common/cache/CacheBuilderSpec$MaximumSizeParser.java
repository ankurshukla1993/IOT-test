package com.google.common.cache;

import com.google.common.base.Preconditions;

class CacheBuilderSpec$MaximumSizeParser extends CacheBuilderSpec$LongParser {
    CacheBuilderSpec$MaximumSizeParser() {
    }

    protected void parseLong(CacheBuilderSpec spec, long value) {
        boolean z;
        Preconditions.checkArgument(spec.maximumSize == null, "maximum size was already set to ", spec.maximumSize);
        if (spec.maximumWeight == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "maximum weight was already set to ", spec.maximumWeight);
        spec.maximumSize = Long.valueOf(value);
    }
}

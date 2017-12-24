package com.google.common.cache;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

class CacheBuilderSpec$RecordStatsParser implements CacheBuilderSpec$ValueParser {
    CacheBuilderSpec$RecordStatsParser() {
    }

    public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
        boolean z;
        boolean z2 = false;
        if (value == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "recordStats does not take values");
        if (spec.recordStats == null) {
            z2 = true;
        }
        Preconditions.checkArgument(z2, "recordStats already set");
        spec.recordStats = Boolean.valueOf(true);
    }
}

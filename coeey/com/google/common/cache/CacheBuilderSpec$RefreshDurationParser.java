package com.google.common.cache;

import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;

class CacheBuilderSpec$RefreshDurationParser extends CacheBuilderSpec$DurationParser {
    CacheBuilderSpec$RefreshDurationParser() {
    }

    protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
        Preconditions.checkArgument(spec.refreshTimeUnit == null, "refreshAfterWrite already set");
        spec.refreshDuration = duration;
        spec.refreshTimeUnit = unit;
    }
}

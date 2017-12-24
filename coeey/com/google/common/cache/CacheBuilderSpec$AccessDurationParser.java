package com.google.common.cache;

import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;

class CacheBuilderSpec$AccessDurationParser extends CacheBuilderSpec$DurationParser {
    CacheBuilderSpec$AccessDurationParser() {
    }

    protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
        Preconditions.checkArgument(spec.accessExpirationTimeUnit == null, "expireAfterAccess already set");
        spec.accessExpirationDuration = duration;
        spec.accessExpirationTimeUnit = unit;
    }
}

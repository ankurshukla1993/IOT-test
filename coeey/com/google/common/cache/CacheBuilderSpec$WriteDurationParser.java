package com.google.common.cache;

import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;

class CacheBuilderSpec$WriteDurationParser extends CacheBuilderSpec$DurationParser {
    CacheBuilderSpec$WriteDurationParser() {
    }

    protected void parseDuration(CacheBuilderSpec spec, long duration, TimeUnit unit) {
        Preconditions.checkArgument(spec.writeExpirationTimeUnit == null, "expireAfterWrite already set");
        spec.writeExpirationDuration = duration;
        spec.writeExpirationTimeUnit = unit;
    }
}

package com.google.common.cache;

import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;

abstract class CacheBuilderSpec$DurationParser implements CacheBuilderSpec$ValueParser {
    protected abstract void parseDuration(CacheBuilderSpec cacheBuilderSpec, long j, TimeUnit timeUnit);

    CacheBuilderSpec$DurationParser() {
    }

    public void parse(CacheBuilderSpec spec, String key, String value) {
        boolean z;
        if (value == null || value.isEmpty()) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "value of key %s omitted", key);
        try {
            TimeUnit timeUnit;
            switch (value.charAt(value.length() - 1)) {
                case 'd':
                    timeUnit = TimeUnit.DAYS;
                    break;
                case 'h':
                    timeUnit = TimeUnit.HOURS;
                    break;
                case 'm':
                    timeUnit = TimeUnit.MINUTES;
                    break;
                case 's':
                    timeUnit = TimeUnit.SECONDS;
                    break;
                default:
                    throw new IllegalArgumentException(String.format("key %s invalid format.  was %s, must end with one of [dDhHmMsS]", new Object[]{key, value}));
            }
            parseDuration(spec, Long.parseLong(value.substring(0, value.length() - 1)), timeUnit);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[]{key, value}));
        }
    }
}

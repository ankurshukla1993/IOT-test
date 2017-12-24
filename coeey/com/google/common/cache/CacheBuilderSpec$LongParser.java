package com.google.common.cache;

import com.google.common.base.Preconditions;

abstract class CacheBuilderSpec$LongParser implements CacheBuilderSpec$ValueParser {
    protected abstract void parseLong(CacheBuilderSpec cacheBuilderSpec, long j);

    CacheBuilderSpec$LongParser() {
    }

    public void parse(CacheBuilderSpec spec, String key, String value) {
        boolean z = (value == null || value.isEmpty()) ? false : true;
        Preconditions.checkArgument(z, "value of key %s omitted", key);
        try {
            parseLong(spec, Long.parseLong(value));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[]{key, value}), e);
        }
    }
}

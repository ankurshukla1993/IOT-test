package com.google.common.cache;

import com.google.common.base.Preconditions;

abstract class CacheBuilderSpec$IntegerParser implements CacheBuilderSpec$ValueParser {
    protected abstract void parseInteger(CacheBuilderSpec cacheBuilderSpec, int i);

    CacheBuilderSpec$IntegerParser() {
    }

    public void parse(CacheBuilderSpec spec, String key, String value) {
        boolean z = (value == null || value.isEmpty()) ? false : true;
        Preconditions.checkArgument(z, "value of key %s omitted", key);
        try {
            parseInteger(spec, Integer.parseInt(value));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(String.format("key %s value set to %s, must be integer", new Object[]{key, value}), e);
        }
    }
}

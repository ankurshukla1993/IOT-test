package com.google.common.cache;

import com.google.common.base.Preconditions;
import javax.annotation.Nullable;

class CacheBuilderSpec$KeyStrengthParser implements CacheBuilderSpec$ValueParser {
    private final LocalCache$Strength strength;

    public CacheBuilderSpec$KeyStrengthParser(LocalCache$Strength strength) {
        this.strength = strength;
    }

    public void parse(CacheBuilderSpec spec, String key, @Nullable String value) {
        boolean z;
        if (value == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "key %s does not take values", key);
        if (spec.keyStrength == null) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z, "%s was already set to %s", key, spec.keyStrength);
        spec.keyStrength = this.strength;
    }
}

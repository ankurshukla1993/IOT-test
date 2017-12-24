package com.bumptech.glide;

import com.google.android.flexbox.FlexItem;

public enum MemoryCategory {
    LOW(0.5f),
    NORMAL(FlexItem.FLEX_SHRINK_DEFAULT),
    HIGH(1.5f);
    
    private float multiplier;

    private MemoryCategory(float multiplier) {
        this.multiplier = multiplier;
    }

    public float getMultiplier() {
        return this.multiplier;
    }
}

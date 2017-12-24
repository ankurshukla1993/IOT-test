package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaUnit {
    UNDEFINED(0),
    PIXEL(1),
    PERCENT(2);
    
    private int mIntValue;

    private YogaUnit(int intValue) {
        this.mIntValue = intValue;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaUnit fromInt(int value) {
        switch (value) {
            case 0:
                return UNDEFINED;
            case 1:
                return PIXEL;
            case 2:
                return PERCENT;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}

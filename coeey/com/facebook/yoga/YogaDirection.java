package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaDirection {
    INHERIT(0),
    LTR(1),
    RTL(2);
    
    private int mIntValue;

    private YogaDirection(int intValue) {
        this.mIntValue = intValue;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaDirection fromInt(int value) {
        switch (value) {
            case 0:
                return INHERIT;
            case 1:
                return LTR;
            case 2:
                return RTL;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}

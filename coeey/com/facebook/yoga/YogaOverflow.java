package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaOverflow {
    VISIBLE(0),
    HIDDEN(1),
    SCROLL(2);
    
    private int mIntValue;

    private YogaOverflow(int intValue) {
        this.mIntValue = intValue;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaOverflow fromInt(int value) {
        switch (value) {
            case 0:
                return VISIBLE;
            case 1:
                return HIDDEN;
            case 2:
                return SCROLL;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}

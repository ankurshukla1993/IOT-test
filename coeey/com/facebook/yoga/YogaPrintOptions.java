package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaPrintOptions {
    LAYOUT(1),
    STYLE(2),
    CHILDREN(4);
    
    private int mIntValue;

    private YogaPrintOptions(int intValue) {
        this.mIntValue = intValue;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaPrintOptions fromInt(int value) {
        switch (value) {
            case 1:
                return LAYOUT;
            case 2:
                return STYLE;
            case 4:
                return CHILDREN;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}

package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaFlexDirection {
    COLUMN(0),
    COLUMN_REVERSE(1),
    ROW(2),
    ROW_REVERSE(3);
    
    private int mIntValue;

    private YogaFlexDirection(int intValue) {
        this.mIntValue = intValue;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaFlexDirection fromInt(int value) {
        switch (value) {
            case 0:
                return COLUMN;
            case 1:
                return COLUMN_REVERSE;
            case 2:
                return ROW;
            case 3:
                return ROW_REVERSE;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}

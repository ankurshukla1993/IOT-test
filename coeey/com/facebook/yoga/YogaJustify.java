package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaJustify {
    FLEX_START(0),
    CENTER(1),
    FLEX_END(2),
    SPACE_BETWEEN(3),
    SPACE_AROUND(4);
    
    private int mIntValue;

    private YogaJustify(int intValue) {
        this.mIntValue = intValue;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaJustify fromInt(int value) {
        switch (value) {
            case 0:
                return FLEX_START;
            case 1:
                return CENTER;
            case 2:
                return FLEX_END;
            case 3:
                return SPACE_BETWEEN;
            case 4:
                return SPACE_AROUND;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}

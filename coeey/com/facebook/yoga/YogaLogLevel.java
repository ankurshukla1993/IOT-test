package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public enum YogaLogLevel {
    ERROR(0),
    WARN(1),
    INFO(2),
    DEBUG(3),
    VERBOSE(4);
    
    private int mIntValue;

    private YogaLogLevel(int intValue) {
        this.mIntValue = intValue;
    }

    public int intValue() {
        return this.mIntValue;
    }

    public static YogaLogLevel fromInt(int value) {
        switch (value) {
            case 0:
                return ERROR;
            case 1:
                return WARN;
            case 2:
                return INFO;
            case 3:
                return DEBUG;
            case 4:
                return VERBOSE;
            default:
                throw new IllegalArgumentException("Unknown enum value: " + value);
        }
    }
}

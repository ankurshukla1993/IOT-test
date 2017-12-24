package com.dnurse.DnurseDevTestDef;

import java.util.Locale;

public class DnurseConstant {
    public static final int COMMUNICATING = 1;
    public static final int DATA_DATETIME = 2;
    public static final int DATA_VALUE = 1;
    public static final int DEVICE_RECOGNIZED = 3;
    public static final int DEVICE_RECOGNIZING = 2;
    public static final int DEVICE_SLEEP = 9;
    public static final int DEVICE_SN = 3;
    public static final int ERR_NON_CALIBRATE = 11;
    public static final int ERR_PLAY_AUDIO = 14;
    public static final int ERR_RECOGNIZE = 16;
    public static final int ERR_RECORD_AUDIO = 15;
    public static final int ERR_TEMPERATURE_HIGH = 13;
    public static final int ERR_TEMPERATURE_LOW = 12;
    public static final int ERR_TIME_OUT = 17;
    public static final int ERR_VOLTAGE_LOW = 10;
    public static final float MAX_VALUE = 33.3f;
    public static final float MIN_VALUE = 1.1f;
    public static final int NOT_INSERTED = 0;
    public static final int OLD_TEST_PAPER_INSERTED = 5;
    public static final int START_TEST = 7;
    public static final int TEST_FINISH = 8;
    public static final int TEST_PAPER_INSERTED = 4;
    public static final int TEST_PAPER_REMOVED = 6;

    public static String formatValue(float f) {
        if (Float.compare(f, 0.0f) == 0) {
            return "";
        }
        if (isLowValue(f)) {
            return "LOW";
        }
        if (isHighValue(f)) {
            return "HIGH";
        }
        return String.format(Locale.US, "%.1f", new Object[]{Float.valueOf(f)});
    }

    public static boolean isHighValue(float f) {
        return Float.compare(f, 33.3f) > 0;
    }

    public static boolean isLowValue(float f) {
        return Float.compare(f, 1.1f) < 0;
    }

    public static boolean isWorking(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return true;
            default:
                return false;
        }
    }
}

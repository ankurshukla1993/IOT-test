package com.facebook.react.modules.systeminfo;

import android.os.Build;
import android.os.Build.VERSION;

public class AndroidInfoHelpers {
    public static final String DEVICE_LOCALHOST = "localhost:8081";
    public static final String EMULATOR_LOCALHOST = "10.0.2.2:8081";
    public static final String GENYMOTION_LOCALHOST = "10.0.3.2:8081";

    private static boolean isRunningOnGenymotion() {
        return Build.FINGERPRINT.contains("vbox");
    }

    private static boolean isRunningOnStockEmulator() {
        return Build.FINGERPRINT.contains("generic");
    }

    public static String getServerHost() {
        if (isRunningOnGenymotion()) {
            return GENYMOTION_LOCALHOST;
        }
        if (isRunningOnStockEmulator()) {
            return EMULATOR_LOCALHOST;
        }
        return DEVICE_LOCALHOST;
    }

    public static String getFriendlyDeviceName() {
        if (isRunningOnGenymotion()) {
            return Build.MODEL;
        }
        return Build.MODEL + " - " + VERSION.RELEASE + " - API " + VERSION.SDK_INT;
    }
}

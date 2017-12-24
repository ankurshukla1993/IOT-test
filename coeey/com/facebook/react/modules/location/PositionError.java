package com.facebook.react.modules.location;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;

public class PositionError {
    public static int PERMISSION_DENIED = 1;
    public static int POSITION_UNAVAILABLE = 2;
    public static int TIMEOUT = 3;

    public static WritableMap buildError(int code, String message) {
        WritableMap error = Arguments.createMap();
        error.putInt("code", code);
        if (message != null) {
            error.putString("message", message);
        }
        return error;
    }
}

package com.facebook.react.modules.storage;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import javax.annotation.Nullable;

public class AsyncStorageErrorUtil {
    static WritableMap getError(@Nullable String key, String errorMessage) {
        WritableMap errorMap = Arguments.createMap();
        errorMap.putString("message", errorMessage);
        if (key != null) {
            errorMap.putString("key", key);
        }
        return errorMap;
    }

    static WritableMap getInvalidKeyError(@Nullable String key) {
        return getError(key, "Invalid key");
    }

    static WritableMap getInvalidValueError(@Nullable String key) {
        return getError(key, "Invalid Value");
    }

    static WritableMap getDBError(@Nullable String key) {
        return getError(key, "Database Error");
    }
}

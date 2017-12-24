package com.oney.WebRTCModule;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;

public class ReactBridgeUtil {

    static /* synthetic */ class C22961 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$bridge$ReadableType = new int[ReadableType.values().length];

        static {
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Boolean.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.Number.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$facebook$react$bridge$ReadableType[ReadableType.String.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static String getMapStrValue(ReadableMap map, String key) {
        if (!map.hasKey(key)) {
            return null;
        }
        switch (C22961.$SwitchMap$com$facebook$react$bridge$ReadableType[map.getType(key).ordinal()]) {
            case 1:
                return String.valueOf(map.getBoolean(key));
            case 2:
                return String.valueOf(map.getDouble(key));
            case 3:
                return map.getString(key);
            default:
                return null;
        }
    }
}

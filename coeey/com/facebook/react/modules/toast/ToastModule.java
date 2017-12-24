package com.facebook.react.modules.toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Map;

@ReactModule(name = "ToastAndroid")
public class ToastModule extends ReactContextBaseJavaModule {
    private static final String DURATION_LONG_KEY = "LONG";
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String GRAVITY_BOTTOM_KEY = "BOTTOM";
    private static final String GRAVITY_CENTER = "CENTER";
    private static final String GRAVITY_TOP_KEY = "TOP";

    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "ToastAndroid";
    }

    public Map<String, Object> getConstants() {
        Map<String, Object> constants = MapBuilder.newHashMap();
        constants.put(DURATION_SHORT_KEY, Integer.valueOf(0));
        constants.put(DURATION_LONG_KEY, Integer.valueOf(1));
        constants.put(GRAVITY_TOP_KEY, Integer.valueOf(49));
        constants.put(GRAVITY_BOTTOM_KEY, Integer.valueOf(81));
        constants.put(GRAVITY_CENTER, Integer.valueOf(17));
        return constants;
    }

    @ReactMethod
    public void show(String message, int duration) {
        UiThreadUtil.runOnUiThread(new 1(this, message, duration));
    }

    @ReactMethod
    public void showWithGravity(String message, int duration, int gravity) {
        UiThreadUtil.runOnUiThread(new 2(this, message, duration, gravity));
    }
}

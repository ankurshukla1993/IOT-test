package com.corbt.keepawake;

import android.app.Activity;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class KCKeepAwake extends ReactContextBaseJavaModule {
    public KCKeepAwake(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "KCKeepAwake";
    }

    @ReactMethod
    public void activate() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            activity.runOnUiThread(new 1(this, activity));
        }
    }

    @ReactMethod
    public void deactivate() {
        Activity activity = getCurrentActivity();
        if (activity != null) {
            activity.runOnUiThread(new 2(this, activity));
        }
    }
}

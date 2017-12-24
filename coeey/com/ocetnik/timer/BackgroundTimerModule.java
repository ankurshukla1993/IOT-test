package com.ocetnik.timer;

import android.os.Handler;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

public class BackgroundTimerModule extends ReactContextBaseJavaModule {
    private Handler handler;
    private ReactContext reactContext;
    private Runnable runnable;

    public BackgroundTimerModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    public String getName() {
        return "RNBackgroundTimer";
    }

    @ReactMethod
    public void start(int delay) {
        this.handler = new Handler();
        this.runnable = new 1(this, delay);
        this.handler.post(this.runnable);
    }

    @ReactMethod
    public void stop() {
        if (this.handler != null) {
            this.handler.removeCallbacks(this.runnable);
        }
    }

    private void sendEvent(ReactContext reactContext, String eventName) {
        ((RCTDeviceEventEmitter) reactContext.getJSModule(RCTDeviceEventEmitter.class)).emit(eventName, null);
    }

    @ReactMethod
    public void setTimeout(int id, int timeout) {
        new Handler().postDelayed(new 2(this, id), (long) timeout);
    }
}

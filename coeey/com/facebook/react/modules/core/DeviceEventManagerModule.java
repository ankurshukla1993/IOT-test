package com.facebook.react.modules.core;

import android.net.Uri;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.SupportsWebWorkers;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import javax.annotation.Nullable;

@ReactModule(name = "DeviceEventManager")
public class DeviceEventManagerModule extends ReactContextBaseJavaModule {
    private final Runnable mInvokeDefaultBackPressRunnable;

    @SupportsWebWorkers
    public interface RCTDeviceEventEmitter extends JavaScriptModule {
        void emit(String str, @Nullable Object obj);
    }

    public DeviceEventManagerModule(ReactApplicationContext reactContext, DefaultHardwareBackBtnHandler backBtnHandler) {
        super(reactContext);
        this.mInvokeDefaultBackPressRunnable = new 1(this, backBtnHandler);
    }

    public void emitHardwareBackPressed() {
        ((RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(RCTDeviceEventEmitter.class)).emit("hardwareBackPress", null);
    }

    public void emitNewIntentReceived(Uri uri) {
        WritableMap map = Arguments.createMap();
        map.putString("url", uri.toString());
        ((RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(RCTDeviceEventEmitter.class)).emit("url", map);
    }

    @ReactMethod
    public void invokeDefaultBackPressHandler() {
        getReactApplicationContext().runOnUiQueueThread(this.mInvokeDefaultBackPressRunnable);
    }

    public String getName() {
        return "DeviceEventManager";
    }
}

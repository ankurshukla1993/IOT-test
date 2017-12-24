package com.facebook.react.modules.appstate;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

@ReactModule(name = "AppState")
public class AppStateModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    public static final String APP_STATE_ACTIVE = "active";
    public static final String APP_STATE_BACKGROUND = "background";
    protected static final String NAME = "AppState";
    private String mAppState = "uninitialized";

    public AppStateModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return NAME;
    }

    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    @ReactMethod
    public void getCurrentAppState(Callback success, Callback error) {
        success.invoke(new Object[]{createAppStateEventMap()});
    }

    public void onHostResume() {
        this.mAppState = APP_STATE_ACTIVE;
        sendAppStateChangeEvent();
    }

    public void onHostPause() {
        this.mAppState = APP_STATE_BACKGROUND;
        sendAppStateChangeEvent();
    }

    public void onHostDestroy() {
    }

    private WritableMap createAppStateEventMap() {
        WritableMap appState = Arguments.createMap();
        appState.putString("app_state", this.mAppState);
        return appState;
    }

    private void sendAppStateChangeEvent() {
        ((RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(RCTDeviceEventEmitter.class)).emit("appStateDidChange", createAppStateEventMap());
    }
}

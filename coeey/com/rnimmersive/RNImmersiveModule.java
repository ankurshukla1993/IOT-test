package com.rnimmersive;

import android.app.Activity;
import android.os.Build.VERSION;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

public class RNImmersiveModule extends ReactContextBaseJavaModule {
    private static final String ERROR_NO_ACTIVITY = "E_NO_ACTIVITY";
    private static final String ERROR_NO_ACTIVITY_MESSAGE = "Tried to set immersive while not attached to an Activity";
    private static RNImmersiveModule SINGLETON = null;
    private static final int UI_FLAG_IMMERSIVE = 5894;
    private boolean _isImmersiveOn = false;
    private ReactContext _reactContext = null;

    public static RNImmersiveModule getInstance() {
        return SINGLETON;
    }

    public RNImmersiveModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this._reactContext = reactContext;
        SINGLETON = this;
    }

    public void onCatalystInstanceDestroy() {
        this._reactContext = null;
        SINGLETON = null;
    }

    public String getName() {
        return "RNImmersive";
    }

    @ReactMethod
    public void setImmersive(boolean isOn, Promise res) {
        _setImmersive(isOn, res);
    }

    @ReactMethod
    public void getImmersive(Promise res) {
        _getImmersive(res);
    }

    @ReactMethod
    public void addImmersiveListener() {
        _addImmersiveListener();
    }

    public void emitImmersiveStateChangeEvent() {
        if (this._reactContext != null) {
            ((RCTDeviceEventEmitter) this._reactContext.getJSModule(RCTDeviceEventEmitter.class)).emit("@@IMMERSIVE_STATE_CHANGED", null);
        }
    }

    private void _setImmersive(boolean isOn, Promise res) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            res.reject(ERROR_NO_ACTIVITY, ERROR_NO_ACTIVITY_MESSAGE);
        } else if (VERSION.SDK_INT >= 19) {
            UiThreadUtil.runOnUiThread(new 1(this, isOn, activity, res));
        }
    }

    private void _getImmersive(Promise res) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            res.reject(ERROR_NO_ACTIVITY, ERROR_NO_ACTIVITY_MESSAGE);
        } else if (VERSION.SDK_INT >= 19) {
            UiThreadUtil.runOnUiThread(new 2(this, activity, res));
        }
    }

    private void _addImmersiveListener() {
        Activity activity = getCurrentActivity();
        if (activity != null && VERSION.SDK_INT >= 19) {
            UiThreadUtil.runOnUiThread(new 3(this, activity));
        }
    }
}

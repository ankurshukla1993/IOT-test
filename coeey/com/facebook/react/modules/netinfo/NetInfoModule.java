package com.facebook.react.modules.netinfo;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.net.ConnectivityManagerCompat;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

@ReactModule(name = "NetInfo")
public class NetInfoModule extends ReactContextBaseJavaModule implements LifecycleEventListener {
    private static final String CONNECTION_TYPE_NONE = "NONE";
    private static final String CONNECTION_TYPE_UNKNOWN = "UNKNOWN";
    private static final String ERROR_MISSING_PERMISSION = "E_MISSING_PERMISSION";
    private static final String MISSING_PERMISSION_MESSAGE = "To use NetInfo on Android, add the following to your AndroidManifest.xml:\n<uses-permission android:name=\"android.permission.ACCESS_NETWORK_STATE\" />";
    private String mConnectivity = "";
    private final ConnectivityBroadcastReceiver mConnectivityBroadcastReceiver;
    private final ConnectivityManager mConnectivityManager;
    private boolean mNoNetworkPermission = false;

    public NetInfoModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.mConnectivityManager = (ConnectivityManager) reactContext.getSystemService("connectivity");
        this.mConnectivityBroadcastReceiver = new ConnectivityBroadcastReceiver(this, null);
    }

    public void onHostResume() {
        registerReceiver();
    }

    public void onHostPause() {
        unregisterReceiver();
    }

    public void onHostDestroy() {
    }

    public void initialize() {
        getReactApplicationContext().addLifecycleEventListener(this);
    }

    public String getName() {
        return "NetInfo";
    }

    @ReactMethod
    public void getCurrentConnectivity(Promise promise) {
        if (this.mNoNetworkPermission) {
            promise.reject(ERROR_MISSING_PERMISSION, MISSING_PERMISSION_MESSAGE, null);
        } else {
            promise.resolve(createConnectivityEventMap());
        }
    }

    @ReactMethod
    public void isConnectionMetered(Promise promise) {
        if (this.mNoNetworkPermission) {
            promise.reject(ERROR_MISSING_PERMISSION, MISSING_PERMISSION_MESSAGE, null);
        } else {
            promise.resolve(Boolean.valueOf(ConnectivityManagerCompat.isActiveNetworkMetered(this.mConnectivityManager)));
        }
    }

    private void registerReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        getReactApplicationContext().registerReceiver(this.mConnectivityBroadcastReceiver, filter);
        this.mConnectivityBroadcastReceiver.setRegistered(true);
    }

    private void unregisterReceiver() {
        if (this.mConnectivityBroadcastReceiver.isRegistered()) {
            getReactApplicationContext().unregisterReceiver(this.mConnectivityBroadcastReceiver);
            this.mConnectivityBroadcastReceiver.setRegistered(false);
        }
    }

    private void updateAndSendConnectionType() {
        String currentConnectivity = getCurrentConnectionType();
        if (!currentConnectivity.equalsIgnoreCase(this.mConnectivity)) {
            this.mConnectivity = currentConnectivity;
            sendConnectivityChangedEvent();
        }
    }

    private String getCurrentConnectionType() {
        try {
            NetworkInfo networkInfo = this.mConnectivityManager.getActiveNetworkInfo();
            if (networkInfo == null || !networkInfo.isConnected()) {
                return CONNECTION_TYPE_NONE;
            }
            if (ConnectivityManager.isNetworkTypeValid(networkInfo.getType())) {
                return networkInfo.getTypeName().toUpperCase();
            }
            return CONNECTION_TYPE_UNKNOWN;
        } catch (SecurityException e) {
            this.mNoNetworkPermission = true;
            return CONNECTION_TYPE_UNKNOWN;
        }
    }

    private void sendConnectivityChangedEvent() {
        ((RCTDeviceEventEmitter) getReactApplicationContext().getJSModule(RCTDeviceEventEmitter.class)).emit("networkStatusDidChange", createConnectivityEventMap());
    }

    private WritableMap createConnectivityEventMap() {
        WritableMap event = new WritableNativeMap();
        event.putString("network_info", this.mConnectivity);
        return event;
    }
}

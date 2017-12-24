package org.jitsi.meet.sdk;

import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;

class ProximityModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "Proximity";
    private static final int PROXIMITY_SCREEN_OFF_WAKE_LOCK = 32;
    private final WakeLock wakeLock;

    public ProximityModule(ReactApplicationContext reactContext) {
        WakeLock wakeLock;
        super(reactContext);
        try {
            wakeLock = ((PowerManager) reactContext.getSystemService("power")).newWakeLock(32, MODULE_NAME);
        } catch (Throwable th) {
            wakeLock = null;
        }
        this.wakeLock = wakeLock;
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void setEnabled(boolean enabled) {
        if (this.wakeLock != null) {
            UiThreadUtil.runOnUiThread(new 1(this, enabled));
        }
    }
}

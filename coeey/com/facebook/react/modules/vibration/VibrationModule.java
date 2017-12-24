package com.facebook.react.modules.vibration;

import android.os.Vibrator;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "Vibration")
public class VibrationModule extends ReactContextBaseJavaModule {
    public VibrationModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "Vibration";
    }

    @ReactMethod
    public void vibrate(int duration) {
        Vibrator v = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (v != null) {
            v.vibrate((long) duration);
        }
    }

    @ReactMethod
    public void vibrateByPattern(ReadableArray pattern, int repeat) {
        long[] patternLong = new long[pattern.size()];
        for (int i = 0; i < pattern.size(); i++) {
            patternLong[i] = (long) pattern.getInt(i);
        }
        Vibrator v = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (v != null) {
            v.vibrate(patternLong, repeat);
        }
    }

    @ReactMethod
    public void cancel() {
        Vibrator v = (Vibrator) getReactApplicationContext().getSystemService("vibrator");
        if (v != null) {
            v.cancel();
        }
    }
}

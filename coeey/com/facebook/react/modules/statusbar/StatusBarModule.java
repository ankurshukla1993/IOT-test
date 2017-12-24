package com.facebook.react.modules.statusbar;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "StatusBarManager")
public class StatusBarModule extends ReactContextBaseJavaModule {
    private static final String HEIGHT_KEY = "HEIGHT";

    public StatusBarModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "StatusBarManager";
    }

    @Nullable
    public Map<String, Object> getConstants() {
        Context context = getReactApplicationContext();
        int heightResId = context.getResources().getIdentifier("status_bar_height", "dimen", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        return MapBuilder.of(HEIGHT_KEY, Float.valueOf(heightResId > 0 ? PixelUtil.toDIPFromPixel((float) context.getResources().getDimensionPixelSize(heightResId)) : 0.0f));
    }

    @ReactMethod
    public void setColor(int color, boolean animated) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (VERSION.SDK_INT >= 21) {
            UiThreadUtil.runOnUiThread(new 1(this, animated, activity, color));
        }
    }

    @ReactMethod
    public void setTranslucent(boolean translucent) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (VERSION.SDK_INT >= 21) {
            UiThreadUtil.runOnUiThread(new 2(this, activity, translucent));
        }
    }

    @ReactMethod
    public void setHidden(boolean hidden) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else {
            UiThreadUtil.runOnUiThread(new 3(this, hidden, activity));
        }
    }

    @ReactMethod
    public void setStyle(String style) {
        Activity activity = getCurrentActivity();
        if (activity == null) {
            FLog.w(ReactConstants.TAG, "StatusBarModule: Ignored status bar change, current activity is null.");
        } else if (VERSION.SDK_INT >= 23) {
            UiThreadUtil.runOnUiThread(new 4(this, activity, style));
        }
    }
}

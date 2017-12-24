package com.facebook.react.modules.clipboard;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Build.VERSION;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "Clipboard")
public class ClipboardModule extends ReactContextBaseJavaModule {
    public ClipboardModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "Clipboard";
    }

    private ClipboardManager getClipboardService() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        getReactApplicationContext();
        return (ClipboardManager) reactApplicationContext.getSystemService("clipboard");
    }

    @ReactMethod
    public void getString(Promise promise) {
        try {
            ClipboardManager clipboard = getClipboardService();
            ClipData clipData = clipboard.getPrimaryClip();
            if (clipData == null) {
                promise.resolve("");
            } else if (clipData.getItemCount() >= 1) {
                promise.resolve("" + clipboard.getPrimaryClip().getItemAt(0).getText());
            } else {
                promise.resolve("");
            }
        } catch (Throwable e) {
            promise.reject(e);
        }
    }

    @SuppressLint({"DeprecatedMethod"})
    @ReactMethod
    public void setString(String text) {
        ReactApplicationContext reactContext = getReactApplicationContext();
        if (VERSION.SDK_INT >= 11) {
            getClipboardService().setPrimaryClip(ClipData.newPlainText(null, text));
            return;
        }
        getClipboardService().setText(text);
    }
}

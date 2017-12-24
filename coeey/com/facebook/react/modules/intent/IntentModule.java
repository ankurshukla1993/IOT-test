package com.facebook.react.modules.intent;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "IntentAndroid")
public class IntentModule extends ReactContextBaseJavaModule {
    public IntentModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "IntentAndroid";
    }

    @ReactMethod
    public void getInitialURL(Promise promise) {
        try {
            Activity currentActivity = getCurrentActivity();
            String initialURL = null;
            if (currentActivity != null) {
                Intent intent = currentActivity.getIntent();
                String action = intent.getAction();
                Uri uri = intent.getData();
                if ("android.intent.action.VIEW".equals(action) && uri != null) {
                    initialURL = uri.toString();
                }
            }
            promise.resolve(initialURL);
        } catch (Exception e) {
            promise.reject(new JSApplicationIllegalArgumentException("Could not get the initial URL : " + e.getMessage()));
        }
    }

    @ReactMethod
    public void openURL(String url, Promise promise) {
        if (url == null || url.isEmpty()) {
            promise.reject(new JSApplicationIllegalArgumentException("Invalid URL: " + url));
            return;
        }
        try {
            Activity currentActivity = getCurrentActivity();
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            String selfPackageName = getReactApplicationContext().getPackageName();
            ComponentName componentName = intent.resolveActivity(getReactApplicationContext().getPackageManager());
            String otherPackageName = componentName != null ? componentName.getPackageName() : "";
            if (currentActivity == null || !selfPackageName.equals(otherPackageName)) {
                intent.addFlags(268435456);
            }
            if (currentActivity != null) {
                currentActivity.startActivity(intent);
            } else {
                getReactApplicationContext().startActivity(intent);
            }
            promise.resolve(Boolean.valueOf(true));
        } catch (Exception e) {
            promise.reject(new JSApplicationIllegalArgumentException("Could not open URL '" + url + "': " + e.getMessage()));
        }
    }

    @ReactMethod
    public void canOpenURL(String url, Promise promise) {
        if (url == null || url.isEmpty()) {
            promise.reject(new JSApplicationIllegalArgumentException("Invalid URL: " + url));
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            intent.addFlags(268435456);
            promise.resolve(Boolean.valueOf(intent.resolveActivity(getReactApplicationContext().getPackageManager()) != null));
        } catch (Exception e) {
            promise.reject(new JSApplicationIllegalArgumentException("Could not check if URL '" + url + "' can be opened: " + e.getMessage()));
        }
    }
}

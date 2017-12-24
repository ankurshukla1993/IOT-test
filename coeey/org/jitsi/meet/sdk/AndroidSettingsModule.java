package org.jitsi.meet.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

class AndroidSettingsModule extends ReactContextBaseJavaModule {
    private static final String MODULE_NAME = "AndroidSettings";

    public AndroidSettingsModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void open() {
        Context context = getReactApplicationContext();
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", context.getPackageName(), null));
        context.startActivity(intent);
    }
}

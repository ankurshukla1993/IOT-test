package com.facebook.react.modules.camera;

import android.os.AsyncTask;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.io.Closeable;
import java.io.IOException;

@ReactModule(name = "ImageStoreManager")
public class ImageStoreManager extends ReactContextBaseJavaModule {
    private static final int BUFFER_SIZE = 8192;

    public ImageStoreManager(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return "ImageStoreManager";
    }

    @ReactMethod
    public void getBase64ForTag(String uri, Callback success, Callback error) {
        new GetBase64Task(this, getReactApplicationContext(), uri, success, error, null).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
        }
    }
}

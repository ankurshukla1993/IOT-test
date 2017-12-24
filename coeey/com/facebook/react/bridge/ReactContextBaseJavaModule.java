package com.facebook.react.bridge;

import android.app.Activity;
import javax.annotation.Nullable;

public abstract class ReactContextBaseJavaModule extends BaseJavaModule {
    private final ReactApplicationContext mReactApplicationContext;

    public ReactContextBaseJavaModule(ReactApplicationContext reactContext) {
        this.mReactApplicationContext = reactContext;
    }

    protected final ReactApplicationContext getReactApplicationContext() {
        return this.mReactApplicationContext;
    }

    @Nullable
    protected final Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }
}

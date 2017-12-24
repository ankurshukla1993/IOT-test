package com.facebook.react.uimanager;

import android.app.Activity;
import android.content.Context;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import javax.annotation.Nullable;

public class ThemedReactContext extends ReactContext {
    private final ReactApplicationContext mReactApplicationContext;

    public ThemedReactContext(ReactApplicationContext reactApplicationContext, Context base) {
        super(base);
        initializeWithInstance(reactApplicationContext.getCatalystInstance());
        this.mReactApplicationContext = reactApplicationContext;
    }

    public void addLifecycleEventListener(LifecycleEventListener listener) {
        this.mReactApplicationContext.addLifecycleEventListener(listener);
    }

    public void removeLifecycleEventListener(LifecycleEventListener listener) {
        this.mReactApplicationContext.removeLifecycleEventListener(listener);
    }

    public boolean hasCurrentActivity() {
        return this.mReactApplicationContext.hasCurrentActivity();
    }

    @Nullable
    public Activity getCurrentActivity() {
        return this.mReactApplicationContext.getCurrentActivity();
    }
}

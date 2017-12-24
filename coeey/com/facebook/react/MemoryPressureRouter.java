package com.facebook.react;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import com.facebook.react.bridge.MemoryPressure;
import com.facebook.react.bridge.MemoryPressureListener;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class MemoryPressureRouter {
    private static final String ACTION_TRIM_MEMORY_CRITICAL = "com.facebook.rnfeed.ACTION_TRIM_MEMORY_CRITICAL";
    private static final String ACTION_TRIM_MEMORY_MODERATE = "com.facebook.rnfeed.ACTION_TRIM_MEMORY_MODERATE";
    private static final String ACTION_TRIM_MEMORY_UI_HIDDEN = "com.facebook.rnfeed.ACTION_TRIM_MEMORY_UI_HIDDEN";
    private final ComponentCallbacks2 mCallbacks = new C12731();
    private final Set<MemoryPressureListener> mListeners = Collections.synchronizedSet(new LinkedHashSet());

    class C12731 implements ComponentCallbacks2 {
        C12731() {
        }

        public void onTrimMemory(int level) {
            MemoryPressureRouter.this.trimMemory(level);
        }

        public void onConfigurationChanged(Configuration newConfig) {
        }

        public void onLowMemory() {
        }
    }

    @TargetApi(16)
    public static boolean handleDebugIntent(Application application, String action) {
        boolean z = true;
        switch (action.hashCode()) {
            case -742131484:
                if (action.equals(ACTION_TRIM_MEMORY_UI_HIDDEN)) {
                    z = false;
                    break;
                }
                break;
            case -611253616:
                if (action.equals(ACTION_TRIM_MEMORY_CRITICAL)) {
                    z = true;
                    break;
                }
                break;
            case 1112705012:
                if (action.equals(ACTION_TRIM_MEMORY_MODERATE)) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
                simulateTrimMemory(application, 20);
                break;
            case true:
                simulateTrimMemory(application, 60);
                break;
            case true:
                simulateTrimMemory(application, 80);
                return false;
            default:
                return false;
        }
        return true;
    }

    MemoryPressureRouter(Context context) {
        context.getApplicationContext().registerComponentCallbacks(this.mCallbacks);
    }

    public void addMemoryPressureListener(MemoryPressureListener listener) {
        this.mListeners.add(listener);
    }

    public void removeMemoryPressureListener(MemoryPressureListener listener) {
        this.mListeners.remove(listener);
    }

    public void destroy(Context context) {
        context.getApplicationContext().unregisterComponentCallbacks(this.mCallbacks);
    }

    private void trimMemory(int level) {
        if (level >= 80) {
            dispatchMemoryPressure(MemoryPressure.CRITICAL);
        } else if (level >= 40 || level == 15) {
            dispatchMemoryPressure(MemoryPressure.MODERATE);
        } else if (level == 20) {
            dispatchMemoryPressure(MemoryPressure.UI_HIDDEN);
        }
    }

    private void dispatchMemoryPressure(MemoryPressure level) {
        for (MemoryPressureListener listener : (MemoryPressureListener[]) this.mListeners.toArray(new MemoryPressureListener[this.mListeners.size()])) {
            listener.handleMemoryPressure(level);
        }
    }

    private static void simulateTrimMemory(Application application, int level) {
        application.onTrimMemory(level);
    }
}

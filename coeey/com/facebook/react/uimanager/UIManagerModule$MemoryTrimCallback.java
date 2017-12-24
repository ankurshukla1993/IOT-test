package com.facebook.react.uimanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

class UIManagerModule$MemoryTrimCallback implements ComponentCallbacks2 {
    final /* synthetic */ UIManagerModule this$0;

    private UIManagerModule$MemoryTrimCallback(UIManagerModule uIManagerModule) {
        this.this$0 = uIManagerModule;
    }

    public void onTrimMemory(int level) {
        if (level >= 60) {
            YogaNodePool.get().clear();
        }
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void onLowMemory() {
    }
}

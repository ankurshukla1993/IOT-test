package com.facebook.react.flat;

import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;

abstract class VirtualViewManager<C extends FlatShadowNode> extends ViewManager<View, C> {
    VirtualViewManager() {
    }

    protected View createViewInstance(ThemedReactContext reactContext) {
        throw new RuntimeException(getName() + " doesn't map to a View");
    }

    public void updateExtraData(View root, Object extraData) {
    }
}

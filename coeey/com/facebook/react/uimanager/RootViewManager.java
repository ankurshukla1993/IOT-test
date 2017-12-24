package com.facebook.react.uimanager;

import android.view.ViewGroup;

public class RootViewManager extends ViewGroupManager<ViewGroup> {
    public static final String REACT_CLASS = "RootView";

    public String getName() {
        return REACT_CLASS;
    }

    protected ViewGroup createViewInstance(ThemedReactContext reactContext) {
        return new SizeMonitoringFrameLayout(reactContext);
    }
}

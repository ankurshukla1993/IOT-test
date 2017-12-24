package com.facebook.react.flat;

import android.view.ViewGroup;
import com.facebook.react.uimanager.RootViewManager;

class FlatRootViewManager extends RootViewManager {
    FlatRootViewManager() {
    }

    public void removeAllViews(ViewGroup parent) {
        parent.removeAllViewsInLayout();
    }
}

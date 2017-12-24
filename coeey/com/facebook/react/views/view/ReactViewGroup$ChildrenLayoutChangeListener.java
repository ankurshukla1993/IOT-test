package com.facebook.react.views.view;

import android.view.View;
import android.view.View.OnLayoutChangeListener;

final class ReactViewGroup$ChildrenLayoutChangeListener implements OnLayoutChangeListener {
    private final ReactViewGroup mParent;

    private ReactViewGroup$ChildrenLayoutChangeListener(ReactViewGroup parent) {
        this.mParent = parent;
    }

    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        if (this.mParent.getRemoveClippedSubviews()) {
            ReactViewGroup.access$000(this.mParent, v);
        }
    }
}

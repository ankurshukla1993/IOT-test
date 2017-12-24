package com.facebook.react.uimanager;

import android.view.View;

public abstract class SimpleViewManager<T extends View> extends BaseViewManager<T, LayoutShadowNode> {
    public LayoutShadowNode createShadowNodeInstance() {
        return new LayoutShadowNode();
    }

    public Class<LayoutShadowNode> getShadowNodeClass() {
        return LayoutShadowNode.class;
    }

    public void updateExtraData(T t, Object extraData) {
    }
}

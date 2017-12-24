package com.facebook.react.flat;

import com.facebook.react.views.text.ReactTextViewManager;

public final class RCTTextManager extends FlatViewManager {
    public /* bridge */ /* synthetic */ void removeAllViews(FlatViewGroup flatViewGroup) {
        super.removeAllViews(flatViewGroup);
    }

    public /* bridge */ /* synthetic */ void setBackgroundColor(FlatViewGroup flatViewGroup, int i) {
        super.setBackgroundColor(flatViewGroup, i);
    }

    public String getName() {
        return ReactTextViewManager.REACT_CLASS;
    }

    public RCTText createShadowNodeInstance() {
        return new RCTText();
    }

    public Class<RCTText> getShadowNodeClass() {
        return RCTText.class;
    }
}

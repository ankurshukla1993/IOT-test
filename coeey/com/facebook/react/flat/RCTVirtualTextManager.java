package com.facebook.react.flat;

import android.view.View;
import com.facebook.react.views.text.ReactVirtualTextViewManager;

public final class RCTVirtualTextManager extends VirtualViewManager<RCTVirtualText> {
    public /* bridge */ /* synthetic */ void updateExtraData(View view, Object obj) {
        super.updateExtraData(view, obj);
    }

    public String getName() {
        return ReactVirtualTextViewManager.REACT_CLASS;
    }

    public RCTVirtualText createShadowNodeInstance() {
        return new RCTVirtualText();
    }

    public Class<RCTVirtualText> getShadowNodeClass() {
        return RCTVirtualText.class;
    }
}

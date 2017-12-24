package com.facebook.react.flat;

import android.view.View;
import com.facebook.react.views.text.ReactRawTextManager;

public final class RCTRawTextManager extends VirtualViewManager<RCTRawText> {
    public /* bridge */ /* synthetic */ void updateExtraData(View view, Object obj) {
        super.updateExtraData(view, obj);
    }

    public String getName() {
        return ReactRawTextManager.REACT_CLASS;
    }

    public RCTRawText createShadowNodeInstance() {
        return new RCTRawText();
    }

    public Class<RCTRawText> getShadowNodeClass() {
        return RCTRawText.class;
    }
}

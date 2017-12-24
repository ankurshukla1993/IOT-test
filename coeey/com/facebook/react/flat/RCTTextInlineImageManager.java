package com.facebook.react.flat;

import android.view.View;

public final class RCTTextInlineImageManager extends VirtualViewManager<RCTTextInlineImage> {
    public /* bridge */ /* synthetic */ void updateExtraData(View view, Object obj) {
        super.updateExtraData(view, obj);
    }

    public String getName() {
        return "RCTTextInlineImage";
    }

    public RCTTextInlineImage createShadowNodeInstance() {
        return new RCTTextInlineImage();
    }

    public Class<RCTTextInlineImage> getShadowNodeClass() {
        return RCTTextInlineImage.class;
    }
}

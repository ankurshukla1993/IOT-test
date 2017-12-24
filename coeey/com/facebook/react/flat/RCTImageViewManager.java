package com.facebook.react.flat;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import javax.annotation.Nullable;

public final class RCTImageViewManager extends FlatViewManager {
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private AbstractDraweeControllerBuilder mDraweeControllerBuilder;

    public /* bridge */ /* synthetic */ void removeAllViews(FlatViewGroup flatViewGroup) {
        super.removeAllViews(flatViewGroup);
    }

    public /* bridge */ /* synthetic */ void setBackgroundColor(FlatViewGroup flatViewGroup, int i) {
        super.setBackgroundColor(flatViewGroup, i);
    }

    public RCTImageViewManager() {
        this(null, null);
    }

    public RCTImageViewManager(AbstractDraweeControllerBuilder draweeControllerBuilder, Object callerContext) {
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mCallerContext = callerContext;
    }

    public String getName() {
        return "RCTImageView";
    }

    public RCTImageView createShadowNodeInstance() {
        return new RCTImageView(new DrawImageWithDrawee());
    }

    public Class<RCTImageView> getShadowNodeClass() {
        return RCTImageView.class;
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return this.mDraweeControllerBuilder;
    }

    public Object getCallerContext() {
        return this.mCallerContext;
    }
}

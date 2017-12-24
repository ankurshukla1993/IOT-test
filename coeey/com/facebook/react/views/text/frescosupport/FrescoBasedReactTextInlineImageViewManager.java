package com.facebook.react.views.text.frescosupport;

import android.view.View;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;
import javax.annotation.Nullable;

@ReactModule(name = "RCTTextInlineImage")
public class FrescoBasedReactTextInlineImageViewManager extends ViewManager<View, FrescoBasedReactTextInlineImageShadowNode> {
    protected static final String REACT_CLASS = "RCTTextInlineImage";
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;

    public FrescoBasedReactTextInlineImageViewManager() {
        this(null, null);
    }

    public FrescoBasedReactTextInlineImageViewManager(@Nullable AbstractDraweeControllerBuilder draweeControllerBuilder, @Nullable Object callerContext) {
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mCallerContext = callerContext;
    }

    public String getName() {
        return REACT_CLASS;
    }

    public View createViewInstance(ThemedReactContext context) {
        throw new IllegalStateException("RCTTextInlineImage doesn't map into a native view");
    }

    public FrescoBasedReactTextInlineImageShadowNode createShadowNodeInstance() {
        AbstractDraweeControllerBuilder abstractDraweeControllerBuilder;
        if (this.mDraweeControllerBuilder != null) {
            abstractDraweeControllerBuilder = this.mDraweeControllerBuilder;
        } else {
            abstractDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return new FrescoBasedReactTextInlineImageShadowNode(abstractDraweeControllerBuilder, this.mCallerContext);
    }

    public Class<FrescoBasedReactTextInlineImageShadowNode> getShadowNodeClass() {
        return FrescoBasedReactTextInlineImageShadowNode.class;
    }

    public void updateExtraData(View root, Object extraData) {
    }
}

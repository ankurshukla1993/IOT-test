package com.facebook.react.flat;

import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.image.ImageResizeMode;
import javax.annotation.Nullable;

class RCTImageView<T extends AbstractDrawCommand & DrawImage> extends FlatShadowNode {
    static Object sCallerContext = RCTImageView.class;
    private T mDrawImage;

    static void setCallerContext(Object callerContext) {
        sCallerContext = callerContext;
    }

    static Object getCallerContext() {
        return sCallerContext;
    }

    RCTImageView(T drawImage) {
        this.mDrawImage = drawImage;
    }

    protected void collectState(StateBuilder stateBuilder, float left, float top, float right, float bottom, float clipLeft, float clipTop, float clipRight, float clipBottom) {
        super.collectState(stateBuilder, left, top, right, bottom, clipLeft, clipTop, clipRight, clipBottom);
        if (((DrawImage) this.mDrawImage).hasImageRequest()) {
            this.mDrawImage = this.mDrawImage.updateBoundsAndFreeze(left, top, right, bottom, clipLeft, clipTop, clipRight, clipBottom);
            stateBuilder.addDrawCommand(this.mDrawImage);
            stateBuilder.addAttachDetachListener((AttachDetachListener) this.mDrawImage);
        }
    }

    boolean doesDraw() {
        return ((DrawImage) this.mDrawImage).hasImageRequest() || super.doesDraw();
    }

    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setShouldNotifyLoadEvents(boolean shouldNotifyLoadEvents) {
        ((DrawImage) getMutableDrawImage()).setReactTag(shouldNotifyLoadEvents ? getReactTag() : 0);
    }

    @ReactProp(name = "src")
    public void setSource(@Nullable ReadableArray sources) {
        ((DrawImage) getMutableDrawImage()).setSource(getThemedContext(), sources);
    }

    @ReactProp(name = "tintColor")
    public void setTintColor(int tintColor) {
        ((DrawImage) getMutableDrawImage()).setTintColor(tintColor);
    }

    @ReactProp(name = "resizeMode")
    public void setResizeMode(@Nullable String resizeMode) {
        ScaleType scaleType = ImageResizeMode.toScaleType(resizeMode);
        if (((DrawImage) this.mDrawImage).getScaleType() != scaleType) {
            ((DrawImage) getMutableDrawImage()).setScaleType(scaleType);
        }
    }

    @ReactProp(customType = "Color", name = "borderColor")
    public void setBorderColor(int borderColor) {
        if (((DrawImage) this.mDrawImage).getBorderColor() != borderColor) {
            ((DrawImage) getMutableDrawImage()).setBorderColor(borderColor);
        }
    }

    public void setBorder(int spacingType, float borderWidth) {
        super.setBorder(spacingType, borderWidth);
        if (spacingType == 8 && ((DrawImage) this.mDrawImage).getBorderWidth() != borderWidth) {
            ((DrawImage) getMutableDrawImage()).setBorderWidth(borderWidth);
        }
    }

    @ReactProp(name = "borderRadius")
    public void setBorderRadius(float borderRadius) {
        if (((DrawImage) this.mDrawImage).getBorderRadius() != borderRadius) {
            ((DrawImage) getMutableDrawImage()).setBorderRadius(PixelUtil.toPixelFromDIP(borderRadius));
        }
    }

    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(int durationMs) {
        ((DrawImage) getMutableDrawImage()).setFadeDuration(durationMs);
    }

    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(boolean enabled) {
        ((DrawImage) getMutableDrawImage()).setProgressiveRenderingEnabled(enabled);
    }

    private T getMutableDrawImage() {
        if (this.mDrawImage.isFrozen()) {
            this.mDrawImage = this.mDrawImage.mutableCopy();
            invalidate();
        }
        return this.mDrawImage;
    }
}

package com.facebook.react.flat;

import android.graphics.Rect;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactClippingViewGroupHelper;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import javax.annotation.Nullable;

final class RCTView extends FlatShadowNode {
    private static final int[] SPACING_TYPES = new int[]{8, 0, 2, 1, 3};
    @Nullable
    private DrawBorder mDrawBorder;
    @Nullable
    private Rect mHitSlop;
    boolean mHorizontal;
    boolean mRemoveClippedSubviews;

    RCTView() {
    }

    void handleUpdateProperties(ReactStylesDiffMap styles) {
        boolean z = false;
        boolean z2 = this.mRemoveClippedSubviews || (styles.hasKey(ReactClippingViewGroupHelper.PROP_REMOVE_CLIPPED_SUBVIEWS) && styles.getBoolean(ReactClippingViewGroupHelper.PROP_REMOVE_CLIPPED_SUBVIEWS, false));
        this.mRemoveClippedSubviews = z2;
        if (this.mRemoveClippedSubviews) {
            if (this.mHorizontal || (styles.hasKey("horizontal") && styles.getBoolean("horizontal", false))) {
                z = true;
            }
            this.mHorizontal = z;
        }
        super.handleUpdateProperties(styles);
    }

    protected void collectState(StateBuilder stateBuilder, float left, float top, float right, float bottom, float clipLeft, float clipTop, float clipRight, float clipBottom) {
        super.collectState(stateBuilder, left, top, right, bottom, clipLeft, clipTop, clipRight, clipBottom);
        if (this.mDrawBorder != null) {
            this.mDrawBorder = (DrawBorder) this.mDrawBorder.updateBoundsAndFreeze(left, top, right, bottom, clipLeft, clipTop, clipRight, clipBottom);
            stateBuilder.addDrawCommand(this.mDrawBorder);
        }
    }

    boolean doesDraw() {
        return this.mDrawBorder != null || super.doesDraw();
    }

    public void setBackgroundColor(int backgroundColor) {
        getMutableBorder().setBackgroundColor(backgroundColor);
    }

    public void setBorderWidths(int index, float borderWidth) {
        super.setBorderWidths(index, borderWidth);
        getMutableBorder().setBorderWidth(SPACING_TYPES[index], PixelUtil.toPixelFromDIP(borderWidth));
    }

    @ReactProp(name = "nativeBackgroundAndroid")
    public void setHotspot(@Nullable ReadableMap bg) {
        if (bg != null) {
            forceMountToView();
        }
    }

    @ReactPropGroup(customType = "Color", defaultDouble = Double.NaN, names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(int index, double color) {
        int type = SPACING_TYPES[index];
        if (Double.isNaN(color)) {
            getMutableBorder().resetBorderColor(type);
        } else {
            getMutableBorder().setBorderColor(type, (int) color);
        }
    }

    @ReactProp(name = "borderRadius")
    public void setBorderRadius(float borderRadius) {
        this.mClipRadius = borderRadius;
        if (this.mClipToBounds && borderRadius > 0.5f) {
            forceMountToView();
        }
        getMutableBorder().setBorderRadius(PixelUtil.toPixelFromDIP(borderRadius));
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(@Nullable String borderStyle) {
        getMutableBorder().setBorderStyle(borderStyle);
    }

    @ReactProp(name = "hitSlop")
    public void setHitSlop(@Nullable ReadableMap hitSlop) {
        if (hitSlop == null) {
            this.mHitSlop = null;
        } else {
            this.mHitSlop = new Rect((int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.LEFT)), (int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.TOP)), (int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.RIGHT)), (int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.BOTTOM)));
        }
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(@Nullable String pointerEventsStr) {
        forceMountToView();
    }

    void updateNodeRegion(float left, float top, float right, float bottom, boolean isVirtual) {
        if (!getNodeRegion().matches(left, top, right, bottom, isVirtual)) {
            NodeRegion nodeRegion;
            if (this.mHitSlop == null) {
                nodeRegion = new NodeRegion(left, top, right, bottom, getReactTag(), isVirtual);
            } else {
                nodeRegion = new HitSlopNodeRegion(this.mHitSlop, left, top, right, bottom, getReactTag(), isVirtual);
            }
            setNodeRegion(nodeRegion);
        }
    }

    private DrawBorder getMutableBorder() {
        if (this.mDrawBorder == null) {
            this.mDrawBorder = new DrawBorder();
        } else if (this.mDrawBorder.isFrozen()) {
            this.mDrawBorder = (DrawBorder) this.mDrawBorder.mutableCopy();
        }
        invalidate();
        return this.mDrawBorder;
    }

    public boolean clipsSubviews() {
        return this.mRemoveClippedSubviews;
    }
}

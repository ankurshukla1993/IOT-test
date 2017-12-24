package com.facebook.react.views.view;

import android.annotation.TargetApi;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaConstants;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "RCTView")
public class ReactViewManager extends ViewGroupManager<ReactViewGroup> {
    private static final int CMD_HOTSPOT_UPDATE = 1;
    private static final int CMD_SET_PRESSED = 2;
    @VisibleForTesting
    public static final String REACT_CLASS = "RCTView";
    private static final int[] SPACING_TYPES = new int[]{8, 0, 2, 1, 3};

    @ReactProp(name = "accessible")
    public void setAccessible(ReactViewGroup view, boolean accessible) {
        view.setFocusable(accessible);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius"})
    public void setBorderRadius(ReactViewGroup view, int index, float borderRadius) {
        if (!YogaConstants.isUndefined(borderRadius)) {
            borderRadius = PixelUtil.toPixelFromDIP(borderRadius);
        }
        if (index == 0) {
            view.setBorderRadius(borderRadius);
        } else {
            view.setBorderRadius(borderRadius, index - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactViewGroup view, @Nullable String borderStyle) {
        view.setBorderStyle(borderStyle);
    }

    @ReactProp(name = "hitSlop")
    public void setHitSlop(ReactViewGroup view, @Nullable ReadableMap hitSlop) {
        int i = 0;
        if (hitSlop == null) {
            view.setHitSlopRect(null);
            return;
        }
        int toPixelFromDIP;
        int toPixelFromDIP2;
        int toPixelFromDIP3;
        if (hitSlop.hasKey(ViewProps.LEFT)) {
            toPixelFromDIP = (int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.LEFT));
        } else {
            toPixelFromDIP = 0;
        }
        if (hitSlop.hasKey(ViewProps.TOP)) {
            toPixelFromDIP2 = (int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.TOP));
        } else {
            toPixelFromDIP2 = 0;
        }
        if (hitSlop.hasKey(ViewProps.RIGHT)) {
            toPixelFromDIP3 = (int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.RIGHT));
        } else {
            toPixelFromDIP3 = 0;
        }
        if (hitSlop.hasKey(ViewProps.BOTTOM)) {
            i = (int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.BOTTOM));
        }
        view.setHitSlopRect(new Rect(toPixelFromDIP, toPixelFromDIP2, toPixelFromDIP3, i));
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(ReactViewGroup view, @Nullable String pointerEventsStr) {
        if (pointerEventsStr == null) {
            view.setPointerEvents(PointerEvents.AUTO);
        } else {
            view.setPointerEvents(PointerEvents.valueOf(pointerEventsStr.toUpperCase(Locale.US).replace("-", EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)));
        }
    }

    @ReactProp(name = "nativeBackgroundAndroid")
    public void setNativeBackground(ReactViewGroup view, @Nullable ReadableMap bg) {
        Drawable drawable;
        if (bg == null) {
            drawable = null;
        } else {
            drawable = ReactDrawableHelper.createDrawableFromJSDescription(view.getContext(), bg);
        }
        view.setTranslucentBackgroundDrawable(drawable);
    }

    @ReactProp(name = "nativeForegroundAndroid")
    @TargetApi(23)
    public void setNativeForeground(ReactViewGroup view, @Nullable ReadableMap fg) {
        Drawable drawable;
        if (fg == null) {
            drawable = null;
        } else {
            drawable = ReactDrawableHelper.createDrawableFromJSDescription(view.getContext(), fg);
        }
        view.setForeground(drawable);
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(ReactViewGroup view, boolean removeClippedSubviews) {
        view.setRemoveClippedSubviews(removeClippedSubviews);
    }

    @ReactProp(name = "needsOffscreenAlphaCompositing")
    public void setNeedsOffscreenAlphaCompositing(ReactViewGroup view, boolean needsOffscreenAlphaCompositing) {
        view.setNeedsOffscreenAlphaCompositing(needsOffscreenAlphaCompositing);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {"borderWidth", "borderLeftWidth", "borderRightWidth", "borderTopWidth", "borderBottomWidth"})
    public void setBorderWidth(ReactViewGroup view, int index, float width) {
        if (!YogaConstants.isUndefined(width)) {
            width = PixelUtil.toPixelFromDIP(width);
        }
        view.setBorderWidth(SPACING_TYPES[index], width);
    }

    @ReactPropGroup(customType = "Color", names = {"borderColor", "borderLeftColor", "borderRightColor", "borderTopColor", "borderBottomColor"})
    public void setBorderColor(ReactViewGroup view, int index, Integer color) {
        float alphaComponent = YogaConstants.UNDEFINED;
        float rgbComponent = color == null ? YogaConstants.UNDEFINED : (float) (color.intValue() & 16777215);
        if (color != null) {
            alphaComponent = (float) (color.intValue() >>> 24);
        }
        view.setBorderColor(SPACING_TYPES[index], rgbComponent, alphaComponent);
    }

    @ReactProp(name = "collapsable")
    public void setCollapsable(ReactViewGroup view, boolean collapsable) {
    }

    public String getName() {
        return "RCTView";
    }

    public ReactViewGroup createViewInstance(ThemedReactContext context) {
        return new ReactViewGroup(context);
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("hotspotUpdate", Integer.valueOf(1), "setPressed", Integer.valueOf(2));
    }

    public void receiveCommand(ReactViewGroup root, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case 1:
                if (args == null || args.size() != 2) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'updateHotspot' command");
                } else if (VERSION.SDK_INT >= 21) {
                    root.drawableHotspotChanged(PixelUtil.toPixelFromDIP(args.getDouble(0)), PixelUtil.toPixelFromDIP(args.getDouble(1)));
                    return;
                } else {
                    return;
                }
            case 2:
                if (args == null || args.size() != 1) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'setPressed' command");
                }
                root.setPressed(args.getBoolean(0));
                return;
            default:
                return;
        }
    }

    public void addView(ReactViewGroup parent, View child, int index) {
        if (parent.getRemoveClippedSubviews()) {
            parent.addViewWithSubviewClippingEnabled(child, index);
        } else {
            parent.addView(child, index);
        }
        reorderChildrenByZIndex(parent);
    }

    public int getChildCount(ReactViewGroup parent) {
        if (parent.getRemoveClippedSubviews()) {
            return parent.getAllChildrenCount();
        }
        return parent.getChildCount();
    }

    public View getChildAt(ReactViewGroup parent, int index) {
        if (parent.getRemoveClippedSubviews()) {
            return parent.getChildAtWithSubviewClippingEnabled(index);
        }
        return parent.getChildAt(index);
    }

    public void removeViewAt(ReactViewGroup parent, int index) {
        if (parent.getRemoveClippedSubviews()) {
            View child = getChildAt(parent, index);
            if (child.getParent() != null) {
                parent.removeView(child);
            }
            parent.removeViewWithSubviewClippingEnabled(child);
            return;
        }
        parent.removeViewAt(index);
    }

    public void removeAllViews(ReactViewGroup parent) {
        if (parent.getRemoveClippedSubviews()) {
            parent.removeAllViewsWithSubviewClippingEnabled();
        } else {
            parent.removeAllViews();
        }
    }
}

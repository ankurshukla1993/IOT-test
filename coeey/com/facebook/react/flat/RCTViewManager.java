package com.facebook.react.flat;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.PointerEvents;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.react.views.view.ReactDrawableHelper;
import io.reactivex.annotations.SchedulerSupport;
import java.util.Map;
import javax.annotation.Nullable;

public final class RCTViewManager extends FlatViewManager {
    private static final int CMD_HOTSPOT_UPDATE = 1;
    private static final int CMD_SET_PRESSED = 2;
    private static final int[] TMP_INT_ARRAY = new int[2];

    public /* bridge */ /* synthetic */ void removeAllViews(FlatViewGroup flatViewGroup) {
        super.removeAllViews(flatViewGroup);
    }

    public /* bridge */ /* synthetic */ void setBackgroundColor(FlatViewGroup flatViewGroup, int i) {
        super.setBackgroundColor(flatViewGroup, i);
    }

    public String getName() {
        return "RCTView";
    }

    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("hotspotUpdate", Integer.valueOf(1), "setPressed", Integer.valueOf(2));
    }

    public RCTView createShadowNodeInstance() {
        return new RCTView();
    }

    public Class<RCTView> getShadowNodeClass() {
        return RCTView.class;
    }

    @ReactProp(name = "nativeBackgroundAndroid")
    public void setHotspot(FlatViewGroup view, @Nullable ReadableMap bg) {
        Drawable drawable;
        if (bg == null) {
            drawable = null;
        } else {
            drawable = ReactDrawableHelper.createDrawableFromJSDescription(view.getContext(), bg);
        }
        view.setHotspot(drawable);
    }

    public void receiveCommand(FlatViewGroup view, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case 1:
                if (args == null || args.size() != 2) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'updateHotspot' command");
                } else if (VERSION.SDK_INT >= 21) {
                    view.getLocationOnScreen(TMP_INT_ARRAY);
                    view.drawableHotspotChanged(PixelUtil.toPixelFromDIP(args.getDouble(0)) - ((float) TMP_INT_ARRAY[0]), PixelUtil.toPixelFromDIP(args.getDouble(1)) - ((float) TMP_INT_ARRAY[1]));
                    return;
                } else {
                    return;
                }
            case 2:
                if (args == null || args.size() != 1) {
                    throw new JSApplicationIllegalArgumentException("Illegal number of arguments for 'setPressed' command");
                }
                view.setPressed(args.getBoolean(0));
                return;
            default:
                return;
        }
    }

    @ReactProp(name = "needsOffscreenAlphaCompositing")
    public void setNeedsOffscreenAlphaCompositing(FlatViewGroup view, boolean needsOffscreenAlphaCompositing) {
        view.setNeedsOffscreenAlphaCompositing(needsOffscreenAlphaCompositing);
    }

    @ReactProp(name = "pointerEvents")
    public void setPointerEvents(FlatViewGroup view, @Nullable String pointerEventsStr) {
        view.setPointerEvents(parsePointerEvents(pointerEventsStr));
    }

    @ReactProp(name = "removeClippedSubviews")
    public void setRemoveClippedSubviews(FlatViewGroup view, boolean removeClippedSubviews) {
        view.setRemoveClippedSubviews(removeClippedSubviews);
    }

    private static PointerEvents parsePointerEvents(@Nullable String pointerEventsStr) {
        if (pointerEventsStr != null) {
            Object obj = -1;
            switch (pointerEventsStr.hashCode()) {
                case -2089141766:
                    if (pointerEventsStr.equals("box-none")) {
                        obj = 2;
                        break;
                    }
                    break;
                case -2089112978:
                    if (pointerEventsStr.equals("box-only")) {
                        obj = 3;
                        break;
                    }
                    break;
                case 3005871:
                    if (pointerEventsStr.equals(ReactScrollViewHelper.AUTO)) {
                        obj = 1;
                        break;
                    }
                    break;
                case 3387192:
                    if (pointerEventsStr.equals(SchedulerSupport.NONE)) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    return PointerEvents.NONE;
                case 1:
                    return PointerEvents.AUTO;
                case 2:
                    return PointerEvents.BOX_NONE;
                case 3:
                    return PointerEvents.BOX_ONLY;
            }
        }
        return PointerEvents.AUTO;
    }

    @ReactProp(name = "hitSlop")
    public void setHitSlop(FlatViewGroup view, @Nullable ReadableMap hitSlop) {
        if (hitSlop == null) {
            view.setHitSlopRect(null);
        } else {
            view.setHitSlopRect(new Rect((int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.LEFT)), (int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.TOP)), (int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.RIGHT)), (int) PixelUtil.toPixelFromDIP(hitSlop.getDouble(ViewProps.BOTTOM))));
        }
    }
}

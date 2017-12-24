package com.facebook.react.views.drawer;

import android.os.Build.VERSION;
import android.support.v4.view.GravityCompat;
import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.drawer.events.DrawerClosedEvent;
import com.facebook.react.views.drawer.events.DrawerOpenedEvent;
import com.facebook.react.views.drawer.events.DrawerSlideEvent;
import com.facebook.react.views.drawer.events.DrawerStateChangedEvent;
import java.util.Map;
import javax.annotation.Nullable;

@ReactModule(name = "AndroidDrawerLayout")
public class ReactDrawerLayoutManager extends ViewGroupManager<ReactDrawerLayout> {
    public static final int CLOSE_DRAWER = 2;
    public static final int OPEN_DRAWER = 1;
    protected static final String REACT_CLASS = "AndroidDrawerLayout";

    public String getName() {
        return REACT_CLASS;
    }

    protected void addEventEmitters(ThemedReactContext reactContext, ReactDrawerLayout view) {
        view.setDrawerListener(new DrawerEventEmitter(view, ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher()));
    }

    protected ReactDrawerLayout createViewInstance(ThemedReactContext context) {
        return new ReactDrawerLayout(context);
    }

    @ReactProp(defaultInt = 8388611, name = "drawerPosition")
    public void setDrawerPosition(ReactDrawerLayout view, int drawerPosition) {
        if (GravityCompat.START == drawerPosition || GravityCompat.END == drawerPosition) {
            view.setDrawerPosition(drawerPosition);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Unknown drawerPosition " + drawerPosition);
    }

    @ReactProp(defaultFloat = Float.NaN, name = "drawerWidth")
    public void getDrawerWidth(ReactDrawerLayout view, float width) {
        int widthInPx;
        if (Float.isNaN(width)) {
            widthInPx = -1;
        } else {
            widthInPx = Math.round(PixelUtil.toPixelFromDIP(width));
        }
        view.setDrawerWidth(widthInPx);
    }

    @ReactProp(name = "drawerLockMode")
    public void setDrawerLockMode(ReactDrawerLayout view, @Nullable String drawerLockMode) {
        if (drawerLockMode == null || "unlocked".equals(drawerLockMode)) {
            view.setDrawerLockMode(0);
        } else if ("locked-closed".equals(drawerLockMode)) {
            view.setDrawerLockMode(1);
        } else if ("locked-open".equals(drawerLockMode)) {
            view.setDrawerLockMode(2);
        } else {
            throw new JSApplicationIllegalArgumentException("Unknown drawerLockMode " + drawerLockMode);
        }
    }

    public void setElevation(ReactDrawerLayout view, float elevation) {
        if (VERSION.SDK_INT >= 21) {
            try {
                ReactDrawerLayout.class.getMethod("setDrawerElevation", new Class[]{Float.TYPE}).invoke(view, new Object[]{Float.valueOf(PixelUtil.toPixelFromDIP(elevation))});
            } catch (Exception ex) {
                FLog.w(ReactConstants.TAG, "setDrawerElevation is not available in this version of the support lib.", ex);
            }
        }
    }

    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("openDrawer", Integer.valueOf(1), "closeDrawer", Integer.valueOf(2));
    }

    public void receiveCommand(ReactDrawerLayout root, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case 1:
                root.openDrawer();
                return;
            case 2:
                root.closeDrawer();
                return;
            default:
                return;
        }
    }

    @Nullable
    public Map getExportedViewConstants() {
        return MapBuilder.of("DrawerPosition", MapBuilder.of("Left", Integer.valueOf(GravityCompat.START), "Right", Integer.valueOf(GravityCompat.END)));
    }

    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(DrawerSlideEvent.EVENT_NAME, MapBuilder.of("registrationName", "onDrawerSlide"), DrawerOpenedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onDrawerOpen"), DrawerClosedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onDrawerClose"), DrawerStateChangedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onDrawerStateChanged"));
    }

    public void addView(ReactDrawerLayout parent, View child, int index) {
        if (getChildCount(parent) >= 2) {
            throw new JSApplicationIllegalArgumentException("The Drawer cannot have more than two children");
        } else if (index == 0 || index == 1) {
            parent.addView(child, index);
            parent.setDrawerProperties();
        } else {
            throw new JSApplicationIllegalArgumentException("The only valid indices for drawer's child are 0 or 1. Got " + index + " instead.");
        }
    }
}

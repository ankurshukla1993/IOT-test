package com.thefinestartist.utils.ui;

import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import com.thefinestartist.enums.Rotation;
import com.thefinestartist.utils.content.ResourcesUtil;
import com.thefinestartist.utils.content.ThemeUtil;
import com.thefinestartist.utils.content.TypedValueUtil;
import com.thefinestartist.utils.etc.APILevel;
import com.thefinestartist.utils.service.WindowManagerUtil;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

public class DisplayUtil {
    private DisplayUtil() {
    }

    public static int getWidth() {
        Display display = WindowManagerUtil.getDefaultDisplay();
        if (!APILevel.require(13)) {
            return display.getWidth();
        }
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getHeight() {
        Display display = WindowManagerUtil.getDefaultDisplay();
        if (!APILevel.require(13)) {
            return display.getHeight();
        }
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static Rotation getRotation() {
        if (APILevel.require(8)) {
            return Rotation.fromValue(WindowManagerUtil.getDefaultDisplay().getRotation());
        }
        return Rotation.fromValue(WindowManagerUtil.getDefaultDisplay().getOrientation());
    }

    public static boolean isPortrait() {
        Rotation rotation = getRotation();
        return rotation == Rotation.DEGREES_0 || rotation == Rotation.DEGREES_180;
    }

    public static boolean isLandscape() {
        Rotation rotation = getRotation();
        return rotation == Rotation.DEGREES_90 || rotation == Rotation.DEGREES_270;
    }

    public static int getStatusBarHeight() {
        int resourceId = ResourcesUtil.getIdentifier("status_bar_height", "dimen", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        return resourceId > 0 ? ResourcesUtil.getDimensionPixelSize(resourceId) : 0;
    }

    public static int getToolbarHeight() {
        return getActionBarHeight();
    }

    public static int getActionBarHeight() {
        TypedValue tv = new TypedValue();
        return ThemeUtil.resolveAttribute(16843499, tv, true) ? TypedValueUtil.complexToDimensionPixelSize(tv.data) : 0;
    }

    public static int getNavigationBarHeight() {
        int resourceId = ResourcesUtil.getIdentifier("navigation_bar_height", "dimen", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        return resourceId > 0 ? ResourcesUtil.getDimensionPixelSize(resourceId) : 0;
    }
}

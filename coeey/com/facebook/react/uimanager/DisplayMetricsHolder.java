package com.facebook.react.uimanager;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.facebook.infer.annotation.Assertions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

public class DisplayMetricsHolder {
    @Nullable
    private static DisplayMetrics sScreenDisplayMetrics;
    @Nullable
    private static DisplayMetrics sWindowDisplayMetrics;

    public static void setWindowDisplayMetrics(DisplayMetrics displayMetrics) {
        sWindowDisplayMetrics = displayMetrics;
    }

    public static void initDisplayMetricsIfNotInitialized(Context context) {
        if (getScreenDisplayMetrics() == null) {
            initDisplayMetrics(context);
        }
    }

    public static void initDisplayMetrics(Context context) {
        ReflectiveOperationException e;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        setWindowDisplayMetrics(displayMetrics);
        DisplayMetrics screenDisplayMetrics = new DisplayMetrics();
        screenDisplayMetrics.setTo(displayMetrics);
        WindowManager wm = (WindowManager) context.getSystemService("window");
        Assertions.assertNotNull(wm, "WindowManager is null!");
        Display display = wm.getDefaultDisplay();
        if (VERSION.SDK_INT >= 17) {
            display.getRealMetrics(screenDisplayMetrics);
        } else {
            try {
                Method mGetRawH = Display.class.getMethod("getRawHeight", new Class[0]);
                screenDisplayMetrics.widthPixels = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(display, new Object[0])).intValue();
                screenDisplayMetrics.heightPixels = ((Integer) mGetRawH.invoke(display, new Object[0])).intValue();
            } catch (InvocationTargetException e2) {
                e = e2;
                throw new RuntimeException("Error getting real dimensions for API level < 17", e);
            } catch (IllegalAccessException e3) {
                e = e3;
                throw new RuntimeException("Error getting real dimensions for API level < 17", e);
            } catch (NoSuchMethodException e4) {
                e = e4;
                throw new RuntimeException("Error getting real dimensions for API level < 17", e);
            }
        }
        setScreenDisplayMetrics(screenDisplayMetrics);
    }

    @Deprecated
    public static DisplayMetrics getWindowDisplayMetrics() {
        return sWindowDisplayMetrics;
    }

    public static void setScreenDisplayMetrics(DisplayMetrics screenDisplayMetrics) {
        sScreenDisplayMetrics = screenDisplayMetrics;
    }

    public static DisplayMetrics getScreenDisplayMetrics() {
        return sScreenDisplayMetrics;
    }
}

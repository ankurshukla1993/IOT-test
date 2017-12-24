package com.thefinestartist.utils.service;

import android.view.Display;
import android.view.View;

public class WindowManagerUtil {
    private WindowManagerUtil() {
    }

    public static Display getDefaultDisplay() {
        return ServiceUtil.getWindowManager().getDefaultDisplay();
    }

    public static void removeViewImmediate(View view) {
        ServiceUtil.getWindowManager().removeViewImmediate(view);
    }
}

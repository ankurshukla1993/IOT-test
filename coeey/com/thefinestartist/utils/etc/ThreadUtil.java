package com.thefinestartist.utils.etc;

import android.os.Looper;

public class ThreadUtil {
    private ThreadUtil() {
    }

    public static boolean isMain() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}

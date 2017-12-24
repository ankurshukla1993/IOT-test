package com.thefinestartist;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

public class Base {
    private static Context context;

    private Base() {
    }

    public static void initialize(@NonNull Context context) {
        context = context;
    }

    public static Context getContext() {
        Context applicationContext;
        synchronized (Base.class) {
            if (context == null) {
                throw new NullPointerException("Call Base.initialize(context) within your Application onCreate() method.");
            }
            applicationContext = context.getApplicationContext();
        }
        return applicationContext;
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static Theme getTheme() {
        return getContext().getTheme();
    }

    public static AssetManager getAssets() {
        return getContext().getAssets();
    }

    public static Configuration getConfiguration() {
        return getResources().getConfiguration();
    }

    public static DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }
}

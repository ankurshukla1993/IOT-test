package com.thefinestartist.binders;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public class ExtrasBinder {
    static final String SUFFIX = "$$ExtraBinder";

    private ExtrasBinder() {
    }

    public static void bind(Activity activity) {
        if (activity != null) {
            bindObject(activity);
        }
    }

    public static void bind(Fragment fragment) {
        if (fragment != null) {
            bindObject(fragment);
        }
    }

    public static void bind(android.app.Fragment fragment) {
        if (fragment != null) {
            bindObject(fragment);
        }
    }

    private static void bindObject(@NonNull Object object) {
        try {
            Class.forName(object.getClass().getName() + SUFFIX).getMethod("bind", new Class[]{object.getClass()}).invoke(null, new Object[]{object});
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception e3) {
            throw new RuntimeException("Unable to bind extras for " + object, e3);
        }
    }
}

package com.thefinestartist.utils.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.View;
import com.thefinestartist.Base;
import com.thefinestartist.converters.UnitConverter;
import com.thefinestartist.utils.etc.ThreadUtil;
import com.thefinestartist.utils.service.ServiceUtil;

public class KeyboardUtil {
    public static final int DEFAULT_KEYBOARD_HEIGHT = 200;
    public static final String KEYBOARD_HEIGHT = "KEYBOARD_HEIGHT";
    public static final String KEYBOARD_UTIL_PREF = "KEYBOARD_UTIL_PREF";
    public static int height = 0;

    private KeyboardUtil() {
    }

    public static void show(final View view) {
        if (view != null) {
            view.postDelayed(new Runnable() {
                public void run() {
                    KeyboardUtil.showInMainThread(view);
                }
            }, 200);
        }
    }

    public static void showImmediately(final View view) {
        if (view != null) {
            if (ThreadUtil.isMain()) {
                showInMainThread(view);
            } else {
                view.post(new Runnable() {
                    public void run() {
                        KeyboardUtil.showInMainThread(view);
                    }
                });
            }
        }
    }

    private static void showInMainThread(View view) {
        if (view != null) {
            view.requestFocus();
            ServiceUtil.getInputMethodManager().showSoftInput(view, 1);
        }
    }

    public static void hide(Fragment fragment) {
        if (fragment != null && fragment.getActivity() != null) {
            hide(fragment.getActivity());
        }
    }

    @TargetApi(11)
    public static void hide(android.app.Fragment fragment) {
        if (fragment != null && fragment.getActivity() != null) {
            hide(fragment.getActivity());
        }
    }

    public static void hide(Activity activity) {
        if (activity != null) {
            hide(activity.getCurrentFocus());
        }
    }

    public static void hide(Dialog dialog) {
        if (dialog != null) {
            hide(dialog.getCurrentFocus());
        }
    }

    public static void hide(View view) {
        if (view != null) {
            view.clearFocus();
            ServiceUtil.getInputMethodManager().hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static int getHeight() {
        if (height <= 0) {
            height = Base.getContext().getSharedPreferences(KEYBOARD_UTIL_PREF, 0).getInt(KEYBOARD_HEIGHT, UnitConverter.dpToPx(200));
        }
        return height;
    }

    public static void setHeight(int height) {
        height = height;
        Base.getContext().getSharedPreferences(KEYBOARD_UTIL_PREF, 0).edit().putInt(KEYBOARD_HEIGHT, height).apply();
    }
}

package com.arlib.floatingsearchview.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

public class Util {
    private static final String TAG = "Util";

    public static void showSoftKeyboard(final Context context, final EditText editText) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                ((InputMethodManager) context.getSystemService("input_method")).showSoftInput(editText, 2);
            }
        }, 100);
    }

    public static void closeSoftKeyboard(Activity activity) {
        View currentFocusView = activity.getCurrentFocus();
        if (currentFocusView != null) {
            ((InputMethodManager) activity.getSystemService("input_method")).hideSoftInputFromWindow(currentFocusView.getWindowToken(), 0);
        }
    }

    public static int dpToPx(int dp) {
        return (int) (((float) dp) * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px) {
        return (int) (((float) px) / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int spToPx(int sp) {
        return (int) TypedValue.applyDimension(2, (float) sp, Resources.getSystem().getDisplayMetrics());
    }

    public static int pxToSp(int px) {
        return px / ((int) Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    public static int getScreenWidth(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    public static void setIconColor(ImageView iconHolder, int color) {
        Drawable wrappedDrawable = DrawableCompat.wrap(iconHolder.getDrawable());
        DrawableCompat.setTint(wrappedDrawable, color);
        iconHolder.setImageDrawable(wrappedDrawable);
        iconHolder.invalidate();
    }

    public static Drawable getWrappedDrawable(Context context, @DrawableRes int resId) throws NotFoundException {
        return DrawableCompat.wrap(ResourcesCompat.getDrawable(context.getResources(), resId, null));
    }

    public static int getColor(Context context, @ColorRes int resId) throws NotFoundException {
        return ContextCompat.getColor(context, resId);
    }

    public static void removeGlobalLayoutObserver(View view, OnGlobalLayoutListener layoutListener) {
        if (VERSION.SDK_INT < 16) {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(layoutListener);
        } else {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(layoutListener);
        }
    }

    public static Activity getHostActivity(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }
}

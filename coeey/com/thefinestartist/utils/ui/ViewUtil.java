package com.thefinestartist.utils.ui;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.view.View;
import com.thefinestartist.Base;
import com.thefinestartist.utils.etc.APILevel;

public class ViewUtil {
    private ViewUtil() {
    }

    public static void setBackground(View view, Drawable drawable) {
        if (view != null) {
            if (APILevel.require(16)) {
                view.setBackground(drawable);
            } else {
                view.setBackgroundDrawable(drawable);
            }
        }
    }

    public static void setBackground(View view, @DrawableRes int drawableRes) {
        if (view != null) {
            if (APILevel.require(16)) {
                view.setBackground(Base.getResources().getDrawable(drawableRes));
            } else {
                view.setBackgroundDrawable(Base.getResources().getDrawable(drawableRes));
            }
        }
    }
}

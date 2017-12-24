package co.ceryle.segmentedbutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.view.View;

class RippleHelper {
    RippleHelper() {
    }

    static void setSelectableItemBackground(Context context, View view) {
        TypedArray ta = context.obtainStyledAttributes(new int[]{16843534});
        Drawable drawableFromTheme = ta.getDrawable(0);
        ta.recycle();
        if (VERSION.SDK_INT >= 21) {
            view.setBackground(drawableFromTheme);
        } else {
            view.setBackgroundDrawable(drawableFromTheme);
        }
    }

    static void setRipple(View view, int pressedColor) {
        if (VERSION.SDK_INT >= 21) {
            view.setBackground(getPressedColorRippleDrawable(pressedColor));
        } else {
            view.setBackgroundDrawable(getStateListDrawable(pressedColor));
        }
    }

    static Drawable createRipple(int normalColor, int pressedColor) {
        if (VERSION.SDK_INT >= 21) {
            return getPressedColorRippleDrawable(pressedColor);
        }
        return getStateListDrawable(pressedColor);
    }

    private static StateListDrawable getStateListDrawable(int pressedColor) {
        StateListDrawable states = new StateListDrawable();
        states.addState(new int[]{16842919}, new ColorDrawable(pressedColor));
        states.addState(new int[]{16842908}, new ColorDrawable(pressedColor));
        states.addState(new int[]{16843518}, new ColorDrawable(pressedColor));
        return states;
    }

    @TargetApi(21)
    private static Drawable getPressedColorRippleDrawable(int pressedColor) {
        return new RippleDrawable(getPressedColorSelector(pressedColor), null, new ShapeDrawable());
    }

    private static ColorStateList getPressedColorSelector(int pressedColor) {
        return new ColorStateList(new int[][]{new int[0]}, new int[]{pressedColor});
    }

    private static ColorDrawable getColorDrawableFromColor(int color) {
        return new ColorDrawable(color);
    }
}

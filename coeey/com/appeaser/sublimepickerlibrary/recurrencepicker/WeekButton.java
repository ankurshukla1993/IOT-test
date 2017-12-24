package com.appeaser.sublimepickerlibrary.recurrencepicker;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ToggleButton;
import com.appeaser.sublimepickerlibrary.drawables.CheckableDrawable;
import com.appeaser.sublimepickerlibrary.drawables.CheckableDrawable.OnAnimationDone;

public class WeekButton extends ToggleButton {
    private static int mCheckedTextColor;
    private static int mDefaultTextColor;
    private OnAnimationDone mCallback = new C05981();
    private CheckableDrawable mDrawable;
    private boolean noAnimate = false;

    class C05981 implements OnAnimationDone {
        C05981() {
        }

        public void animationIsDone() {
            WeekButton.this.setTextColor(WeekButton.this.isChecked() ? WeekButton.mCheckedTextColor : WeekButton.mDefaultTextColor);
            WeekButton.this.mDrawable.setChecked(WeekButton.this.isChecked());
        }

        public void animationHasBeenCancelled() {
            WeekButton.this.setTextColor(WeekButton.this.isChecked() ? WeekButton.mCheckedTextColor : WeekButton.mDefaultTextColor);
            WeekButton.this.mDrawable.setChecked(WeekButton.this.isChecked());
        }
    }

    public WeekButton(Context context) {
        super(context);
    }

    public WeekButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WeekButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setCheckedNoAnimate(boolean checked) {
        this.noAnimate = true;
        setChecked(checked);
        this.noAnimate = false;
    }

    public void setChecked(boolean checked) {
        super.setChecked(checked);
        if (this.mDrawable == null) {
            return;
        }
        if (this.noAnimate) {
            this.mDrawable.setChecked(checked);
            setTextColor(isChecked() ? mCheckedTextColor : mDefaultTextColor);
            return;
        }
        setTextColor(mCheckedTextColor);
        this.mDrawable.setCheckedOnClick(isChecked(), this.mCallback);
    }

    public void setBackgroundDrawable(Drawable d) {
        super.setBackgroundDrawable(d);
        if (d instanceof CheckableDrawable) {
            this.mDrawable = (CheckableDrawable) d;
        } else {
            this.mDrawable = null;
        }
    }

    public static void setStateColors(int defaultColor, int checkedColor) {
        mDefaultTextColor = defaultColor;
        mCheckedTextColor = checkedColor;
    }
}

package chatkit.commons;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.cooey.maya.R;

public abstract class Style {
    protected AttributeSet attrs;
    protected Context context;
    protected Resources resources;

    protected Style(Context context, AttributeSet attrs) {
        this.context = context;
        this.resources = context.getResources();
        this.attrs = attrs;
    }

    protected final int getSystemAccentColor() {
        return getSystemColor(R.attr.colorAccent);
    }

    protected final int getSystemPrimaryColor() {
        return getSystemColor(R.attr.colorPrimary);
    }

    protected final int getSystemPrimaryDarkColor() {
        return getSystemColor(R.attr.colorPrimaryDark);
    }

    protected final int getSystemPrimaryTextColor() {
        return getSystemColor(16842806);
    }

    protected final int getSystemHintColor() {
        return getSystemColor(16842906);
    }

    protected final int getSystemColor(@AttrRes int attr) {
        TypedValue typedValue = new TypedValue();
        TypedArray a = this.context.obtainStyledAttributes(typedValue.data, new int[]{attr});
        int color = a.getColor(0, 0);
        a.recycle();
        return color;
    }

    protected final int getDimension(@DimenRes int dimen) {
        return this.resources.getDimensionPixelSize(dimen);
    }

    protected final int getColor(@ColorRes int color) {
        return ContextCompat.getColor(this.context, color);
    }

    protected final Drawable getDrawable(@DrawableRes int drawable) {
        return ContextCompat.getDrawable(this.context, drawable);
    }

    protected final Drawable getVectorDrawable(@DrawableRes int drawable) {
        return this.resources.getDrawable(drawable);
    }
}

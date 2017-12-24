package com.facebook.react.views.text;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;

public final class DefaultStyleValuesUtil {
    private DefaultStyleValuesUtil() {
        throw new AssertionError("Never invoke this for an Utility class!");
    }

    public static ColorStateList getDefaultTextColorHint(Context context) {
        return getDefaultTextAttribute(context, 16842906);
    }

    public static ColorStateList getDefaultTextColor(Context context) {
        return getDefaultTextAttribute(context, 16842904);
    }

    public static int getDefaultTextColorHighlight(Context context) {
        return getDefaultTextAttribute(context, 16842905).getDefaultColor();
    }

    private static ColorStateList getDefaultTextAttribute(Context context, int attribute) {
        TypedArray textAppearances = null;
        try {
            textAppearances = context.getTheme().obtainStyledAttributes(new int[]{attribute});
            ColorStateList textColor = textAppearances.getColorStateList(0);
            return textColor;
        } finally {
            if (textAppearances != null) {
                textAppearances.recycle();
            }
        }
    }
}

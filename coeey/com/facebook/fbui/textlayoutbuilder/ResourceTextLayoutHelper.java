package com.facebook.fbui.textlayoutbuilder;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.AttrRes;
import android.support.annotation.StyleRes;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;

public class ResourceTextLayoutHelper {
    private static final int DEFAULT_TEXT_SIZE_PX = 15;

    public static void updateFromStyleResource(TextLayoutBuilder builder, Context context, @StyleRes int styleRes) {
        updateFromStyleResource(builder, context, 0, styleRes);
    }

    public static void updateFromStyleResource(TextLayoutBuilder builder, Context context, @AttrRes int styleAttr, @StyleRes int styleRes) {
        updateFromStyleResource(builder, context, null, styleAttr, styleRes);
    }

    public static void updateFromStyleResource(TextLayoutBuilder builder, Context context, AttributeSet attrs, @AttrRes int styleAttr, @StyleRes int styleRes) {
        TypedArray customAttrs = context.obtainStyledAttributes(attrs, C1139R.styleable.TextStyle, styleAttr, styleRes);
        int textAppearanceId = customAttrs.getResourceId(C1139R.styleable.TextStyle_android_textAppearance, -1);
        if (textAppearanceId > 0) {
            setTextAppearance(builder, context, textAppearanceId);
        }
        ColorStateList textColor = customAttrs.getColorStateList(C1139R.styleable.TextStyle_android_textColor);
        int textSize = customAttrs.getDimensionPixelSize(C1139R.styleable.TextStyle_android_textSize, 15);
        int shadowColor = customAttrs.getInt(C1139R.styleable.TextStyle_android_shadowColor, 0);
        float dx = customAttrs.getFloat(C1139R.styleable.TextStyle_android_shadowDx, 0.0f);
        float dy = customAttrs.getFloat(C1139R.styleable.TextStyle_android_shadowDy, 0.0f);
        float radius = customAttrs.getFloat(C1139R.styleable.TextStyle_android_shadowRadius, 0.0f);
        int textStyle = customAttrs.getInt(C1139R.styleable.TextStyle_android_textStyle, -1);
        int ellipsize = customAttrs.getInt(C1139R.styleable.TextStyle_android_ellipsize, 0);
        boolean singleLine = customAttrs.getBoolean(C1139R.styleable.TextStyle_android_singleLine, false);
        int maxLines = customAttrs.getInt(C1139R.styleable.TextStyle_android_maxLines, Integer.MAX_VALUE);
        customAttrs.recycle();
        builder.setTextColor(textColor);
        builder.setTextSize(textSize);
        builder.setShadowLayer(radius, dx, dy, shadowColor);
        if (textStyle != -1) {
            builder.setTypeface(Typeface.defaultFromStyle(textStyle));
        } else {
            builder.setTypeface(null);
        }
        if (ellipsize <= 0 || ellipsize >= 4) {
            builder.setEllipsize(null);
        } else {
            builder.setEllipsize(TruncateAt.values()[ellipsize - 1]);
        }
        builder.setSingleLine(singleLine);
        builder.setMaxLines(maxLines);
    }

    public static void setTextAppearance(TextLayoutBuilder builder, Context context, @StyleRes int resId) {
        TypedArray customAttrs = context.obtainStyledAttributes(resId, C1139R.styleable.TextAppearance);
        ColorStateList textColor = customAttrs.getColorStateList(C1139R.styleable.TextAppearance_android_textColor);
        int textSize = customAttrs.getDimensionPixelSize(C1139R.styleable.TextAppearance_android_textSize, 0);
        int shadowColor = customAttrs.getInt(C1139R.styleable.TextAppearance_android_shadowColor, 0);
        if (shadowColor != 0) {
            builder.setShadowLayer(customAttrs.getFloat(C1139R.styleable.TextAppearance_android_shadowRadius, 0.0f), customAttrs.getFloat(C1139R.styleable.TextAppearance_android_shadowDx, 0.0f), customAttrs.getFloat(C1139R.styleable.TextAppearance_android_shadowDy, 0.0f), shadowColor);
        }
        int textStyle = customAttrs.getInt(C1139R.styleable.TextAppearance_android_textStyle, -1);
        customAttrs.recycle();
        if (textColor != null) {
            builder.setTextColor(textColor);
        }
        if (textSize != 0) {
            builder.setTextSize(textSize);
        }
        if (textStyle != -1) {
            builder.setTypeface(Typeface.defaultFromStyle(textStyle));
        }
    }
}

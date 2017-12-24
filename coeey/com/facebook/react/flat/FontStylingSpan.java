package com.facebook.react.flat;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import javax.annotation.Nullable;

final class FontStylingSpan extends MetricAffectingSpan {
    static final FontStylingSpan INSTANCE = new FontStylingSpan(-1.6777216E7d, 0, -1, -1, -1, false, false, null, true);
    private int mBackgroundColor;
    @Nullable
    private String mFontFamily;
    private int mFontSize;
    private int mFontStyle;
    private int mFontWeight;
    private boolean mFrozen;
    private boolean mHasStrikeThrough;
    private boolean mHasUnderline;
    private double mTextColor;

    FontStylingSpan() {
    }

    private FontStylingSpan(double textColor, int backgroundColor, int fontSize, int fontStyle, int fontWeight, boolean hasUnderline, boolean hasStrikeThrough, @Nullable String fontFamily, boolean frozen) {
        this.mTextColor = textColor;
        this.mBackgroundColor = backgroundColor;
        this.mFontSize = fontSize;
        this.mFontStyle = fontStyle;
        this.mFontWeight = fontWeight;
        this.mHasUnderline = hasUnderline;
        this.mHasStrikeThrough = hasStrikeThrough;
        this.mFontFamily = fontFamily;
        this.mFrozen = frozen;
    }

    FontStylingSpan mutableCopy() {
        return new FontStylingSpan(this.mTextColor, this.mBackgroundColor, this.mFontSize, this.mFontStyle, this.mFontWeight, this.mHasUnderline, this.mHasStrikeThrough, this.mFontFamily, false);
    }

    boolean isFrozen() {
        return this.mFrozen;
    }

    void freeze() {
        this.mFrozen = true;
    }

    double getTextColor() {
        return this.mTextColor;
    }

    void setTextColor(double textColor) {
        this.mTextColor = textColor;
    }

    int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    void setBackgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
    }

    int getFontSize() {
        return this.mFontSize;
    }

    void setFontSize(int fontSize) {
        this.mFontSize = fontSize;
    }

    int getFontStyle() {
        return this.mFontStyle;
    }

    void setFontStyle(int fontStyle) {
        this.mFontStyle = fontStyle;
    }

    int getFontWeight() {
        return this.mFontWeight;
    }

    void setFontWeight(int fontWeight) {
        this.mFontWeight = fontWeight;
    }

    @Nullable
    String getFontFamily() {
        return this.mFontFamily;
    }

    void setFontFamily(@Nullable String fontFamily) {
        this.mFontFamily = fontFamily;
    }

    boolean hasUnderline() {
        return this.mHasUnderline;
    }

    void setHasUnderline(boolean hasUnderline) {
        this.mHasUnderline = hasUnderline;
    }

    boolean hasStrikeThrough() {
        return this.mHasStrikeThrough;
    }

    void setHasStrikeThrough(boolean hasStrikeThrough) {
        this.mHasStrikeThrough = hasStrikeThrough;
    }

    public void updateDrawState(TextPaint ds) {
        if (!Double.isNaN(this.mTextColor)) {
            ds.setColor((int) this.mTextColor);
        }
        ds.bgColor = this.mBackgroundColor;
        ds.setUnderlineText(this.mHasUnderline);
        ds.setStrikeThruText(this.mHasStrikeThrough);
        updateMeasureState(ds);
    }

    public void updateMeasureState(TextPaint ds) {
        if (this.mFontSize != -1) {
            ds.setTextSize((float) this.mFontSize);
        }
        updateTypeface(ds);
    }

    private int getNewStyle(int oldStyle) {
        int newStyle = oldStyle;
        if (this.mFontStyle != -1) {
            newStyle = (newStyle & -3) | this.mFontStyle;
        }
        if (this.mFontWeight != -1) {
            return (newStyle & -2) | this.mFontWeight;
        }
        return newStyle;
    }

    private void updateTypeface(TextPaint ds) {
        Typeface typeface = ds.getTypeface();
        int oldStyle = typeface == null ? 0 : typeface.getStyle();
        int newStyle = getNewStyle(oldStyle);
        if (oldStyle != newStyle || this.mFontFamily != null) {
            if (this.mFontFamily != null) {
                typeface = TypefaceCache.getTypeface(this.mFontFamily, newStyle);
            } else {
                typeface = TypefaceCache.getTypeface(typeface, newStyle);
            }
            ds.setTypeface(typeface);
        }
    }
}

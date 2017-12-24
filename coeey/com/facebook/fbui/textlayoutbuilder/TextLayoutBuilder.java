package com.facebook.fbui.textlayoutbuilder;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.Px;
import android.support.annotation.VisibleForTesting;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.support.v4.util.LruCache;
import android.support.v4.view.ViewCompat;
import android.text.BoringLayout;
import android.text.BoringLayout.Metrics;
import android.text.Layout;
import android.text.Layout.Alignment;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.style.ClickableSpan;
import android.util.Log;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class TextLayoutBuilder {
    public static final int DEFAULT_MAX_LINES = Integer.MAX_VALUE;
    public static final int MEASURE_MODE_AT_MOST = 2;
    public static final int MEASURE_MODE_EXACTLY = 1;
    public static final int MEASURE_MODE_UNSPECIFIED = 0;
    @VisibleForTesting
    static final LruCache<Integer, Layout> sCache = new LruCache(100);
    private GlyphWarmer mGlyphWarmer;
    @VisibleForTesting
    final Params mParams = new Params();
    private Layout mSavedLayout = null;
    private boolean mShouldCacheLayout = true;
    private boolean mShouldWarmText = false;

    private static class ComparableTextPaint extends TextPaint {
        private int mShadowColor;
        private float mShadowDx;
        private float mShadowDy;
        private float mShadowRadius;

        public ComparableTextPaint(int flags) {
            super(flags);
        }

        public ComparableTextPaint(Paint p) {
            super(p);
        }

        public void setShadowLayer(float radius, float dx, float dy, int color) {
            this.mShadowRadius = radius;
            this.mShadowDx = dx;
            this.mShadowDy = dy;
            this.mShadowColor = color;
            super.setShadowLayer(radius, dx, dy, color);
        }

        public int hashCode() {
            Typeface tf = getTypeface();
            int hashCode = ((((((((((((((getColor() + 31) * 31) + Float.floatToIntBits(getTextSize())) * 31) + (tf != null ? tf.hashCode() : 0)) * 31) + Float.floatToIntBits(this.mShadowDx)) * 31) + Float.floatToIntBits(this.mShadowDy)) * 31) + Float.floatToIntBits(this.mShadowRadius)) * 31) + this.mShadowColor) * 31) + this.linkColor;
            if (this.drawableState == null) {
                return (hashCode * 31) + 0;
            }
            for (int i : this.drawableState) {
                hashCode = (hashCode * 31) + i;
            }
            return hashCode;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface MeasureMode {
    }

    @VisibleForTesting
    static class Params {
        Alignment alignment = Alignment.ALIGN_NORMAL;
        ColorStateList color;
        TruncateAt ellipsize = null;
        boolean includePadding = true;
        boolean mForceNewPaint = false;
        int maxLines = Integer.MAX_VALUE;
        int measureMode;
        TextPaint paint = new ComparableTextPaint(1);
        boolean singleLine = false;
        float spacingAdd = 0.0f;
        float spacingMult = FlexItem.FLEX_SHRINK_DEFAULT;
        CharSequence text;
        TextDirectionHeuristicCompat textDirection = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        int width;

        Params() {
        }

        void createNewPaintIfNeeded() {
            if (this.mForceNewPaint) {
                this.paint = new ComparableTextPaint(this.paint);
                this.mForceNewPaint = false;
            }
        }

        public int hashCode() {
            int i;
            int i2 = 1;
            int i3 = 0;
            int hashCode = ((((((((((this.paint != null ? this.paint.hashCode() : 0) + 31) * 31) + this.width) * 31) + this.measureMode) * 31) + Float.floatToIntBits(this.spacingMult)) * 31) + Float.floatToIntBits(this.spacingAdd)) * 31;
            if (this.includePadding) {
                i = 1;
            } else {
                i = 0;
            }
            hashCode = (hashCode + i) * 31;
            if (this.ellipsize != null) {
                i = this.ellipsize.hashCode();
            } else {
                i = 0;
            }
            i = (hashCode + i) * 31;
            if (!this.singleLine) {
                i2 = 0;
            }
            i2 = (((i + i2) * 31) + this.maxLines) * 31;
            if (this.alignment != null) {
                i = this.alignment.hashCode();
            } else {
                i = 0;
            }
            i2 = (i2 + i) * 31;
            if (this.textDirection != null) {
                i = this.textDirection.hashCode();
            } else {
                i = 0;
            }
            i = (i2 + i) * 31;
            if (this.text != null) {
                i3 = this.text.hashCode();
            }
            return i + i3;
        }
    }

    public TextLayoutBuilder setWidth(@Px int width) {
        return setWidth(width, width <= 0 ? 0 : 1);
    }

    public TextLayoutBuilder setWidth(@Px int width, int measureMode) {
        if (!(this.mParams.width == width && this.mParams.measureMode == measureMode)) {
            this.mParams.width = width;
            this.mParams.measureMode = measureMode;
            this.mSavedLayout = null;
        }
        return this;
    }

    public CharSequence getText() {
        return this.mParams.text;
    }

    public TextLayoutBuilder setText(CharSequence text) {
        if (text != this.mParams.text && (text == null || this.mParams.text == null || !text.equals(this.mParams.text))) {
            this.mParams.text = text;
            this.mSavedLayout = null;
        }
        return this;
    }

    public float getTextSize() {
        return this.mParams.paint.getTextSize();
    }

    public TextLayoutBuilder setTextSize(int size) {
        if (this.mParams.paint.getTextSize() != ((float) size)) {
            this.mParams.createNewPaintIfNeeded();
            this.mParams.paint.setTextSize((float) size);
            this.mSavedLayout = null;
        }
        return this;
    }

    @ColorInt
    public int getTextColor() {
        return this.mParams.paint.getColor();
    }

    public TextLayoutBuilder setTextColor(@ColorInt int color) {
        this.mParams.createNewPaintIfNeeded();
        this.mParams.color = null;
        this.mParams.paint.setColor(color);
        this.mSavedLayout = null;
        return this;
    }

    public TextLayoutBuilder setTextColor(ColorStateList colorStateList) {
        this.mParams.createNewPaintIfNeeded();
        this.mParams.color = colorStateList;
        this.mParams.paint.setColor(this.mParams.color != null ? this.mParams.color.getDefaultColor() : ViewCompat.MEASURED_STATE_MASK);
        this.mSavedLayout = null;
        return this;
    }

    @ColorInt
    public int getLinkColor() {
        return this.mParams.paint.linkColor;
    }

    public TextLayoutBuilder setLinkColor(@ColorInt int linkColor) {
        if (this.mParams.paint.linkColor != linkColor) {
            this.mParams.createNewPaintIfNeeded();
            this.mParams.paint.linkColor = linkColor;
            this.mSavedLayout = null;
        }
        return this;
    }

    public float getTextSpacingExtra() {
        return this.mParams.spacingAdd;
    }

    public TextLayoutBuilder setTextSpacingExtra(float spacingExtra) {
        if (this.mParams.spacingAdd != spacingExtra) {
            this.mParams.spacingAdd = spacingExtra;
            this.mSavedLayout = null;
        }
        return this;
    }

    public float getTextSpacingMultiplier() {
        return this.mParams.spacingMult;
    }

    public TextLayoutBuilder setTextSpacingMultiplier(float spacingMultiplier) {
        if (this.mParams.spacingMult != spacingMultiplier) {
            this.mParams.spacingMult = spacingMultiplier;
            this.mSavedLayout = null;
        }
        return this;
    }

    public boolean getIncludeFontPadding() {
        return this.mParams.includePadding;
    }

    public TextLayoutBuilder setIncludeFontPadding(boolean shouldInclude) {
        if (this.mParams.includePadding != shouldInclude) {
            this.mParams.includePadding = shouldInclude;
            this.mSavedLayout = null;
        }
        return this;
    }

    public Alignment getAlignment() {
        return this.mParams.alignment;
    }

    public TextLayoutBuilder setAlignment(Alignment alignment) {
        if (this.mParams.alignment != alignment) {
            this.mParams.alignment = alignment;
            this.mSavedLayout = null;
        }
        return this;
    }

    public TextDirectionHeuristicCompat getTextDirection() {
        return this.mParams.textDirection;
    }

    public TextLayoutBuilder setTextDirection(TextDirectionHeuristicCompat textDirection) {
        if (this.mParams.textDirection != textDirection) {
            this.mParams.textDirection = textDirection;
            this.mSavedLayout = null;
        }
        return this;
    }

    public TextLayoutBuilder setShadowLayer(float radius, float dx, float dy, @ColorInt int color) {
        this.mParams.createNewPaintIfNeeded();
        this.mParams.paint.setShadowLayer(radius, dx, dy, color);
        this.mSavedLayout = null;
        return this;
    }

    public TextLayoutBuilder setTextStyle(int style) {
        return setTypeface(Typeface.defaultFromStyle(style));
    }

    public Typeface getTypeface() {
        return this.mParams.paint.getTypeface();
    }

    public TextLayoutBuilder setTypeface(Typeface typeface) {
        if (this.mParams.paint.getTypeface() != typeface) {
            this.mParams.createNewPaintIfNeeded();
            this.mParams.paint.setTypeface(typeface);
            this.mSavedLayout = null;
        }
        return this;
    }

    public int[] getDrawableState() {
        return this.mParams.paint.drawableState;
    }

    public TextLayoutBuilder setDrawableState(int[] drawableState) {
        this.mParams.createNewPaintIfNeeded();
        this.mParams.paint.drawableState = drawableState;
        if (this.mParams.color != null && this.mParams.color.isStateful()) {
            this.mParams.paint.setColor(this.mParams.color.getColorForState(drawableState, 0));
            this.mSavedLayout = null;
        }
        return this;
    }

    public TruncateAt getEllipsize() {
        return this.mParams.ellipsize;
    }

    public TextLayoutBuilder setEllipsize(TruncateAt ellipsize) {
        if (this.mParams.ellipsize != ellipsize) {
            this.mParams.ellipsize = ellipsize;
            this.mSavedLayout = null;
        }
        return this;
    }

    public boolean getSingleLine() {
        return this.mParams.singleLine;
    }

    public TextLayoutBuilder setSingleLine(boolean singleLine) {
        if (this.mParams.singleLine != singleLine) {
            this.mParams.singleLine = singleLine;
            this.mSavedLayout = null;
        }
        return this;
    }

    public int getMaxLines() {
        return this.mParams.maxLines;
    }

    public TextLayoutBuilder setMaxLines(int maxLines) {
        if (this.mParams.maxLines != maxLines) {
            this.mParams.maxLines = maxLines;
            this.mSavedLayout = null;
        }
        return this;
    }

    public boolean getShouldCacheLayout() {
        return this.mShouldCacheLayout;
    }

    public TextLayoutBuilder setShouldCacheLayout(boolean shouldCacheLayout) {
        this.mShouldCacheLayout = shouldCacheLayout;
        return this;
    }

    public boolean getShouldWarmText() {
        return this.mShouldWarmText;
    }

    public TextLayoutBuilder setShouldWarmText(boolean shouldWarmText) {
        this.mShouldWarmText = shouldWarmText;
        return this;
    }

    public GlyphWarmer getGlyphWarmer() {
        return this.mGlyphWarmer;
    }

    public TextLayoutBuilder setGlyphWarmer(GlyphWarmer glyphWarmer) {
        this.mGlyphWarmer = glyphWarmer;
        return this;
    }

    public Layout build() {
        if (this.mShouldCacheLayout && this.mSavedLayout != null) {
            return this.mSavedLayout;
        }
        if (TextUtils.isEmpty(this.mParams.text)) {
            return null;
        }
        int numLines;
        int width;
        Layout layout;
        boolean hasClickableSpans = false;
        int hashCode = -1;
        if (this.mShouldCacheLayout && (this.mParams.text instanceof Spannable)) {
            hasClickableSpans = ((ClickableSpan[]) ((Spannable) this.mParams.text).getSpans(0, this.mParams.text.length() + -1, ClickableSpan.class)).length > 0;
        }
        if (this.mShouldCacheLayout && !hasClickableSpans) {
            hashCode = this.mParams.hashCode();
            Layout cachedLayout = (Layout) sCache.get(Integer.valueOf(hashCode));
            if (cachedLayout != null) {
                return cachedLayout;
            }
        }
        Metrics metrics = null;
        if (this.mParams.singleLine) {
            numLines = 1;
        } else {
            numLines = this.mParams.maxLines;
        }
        if (numLines == 1) {
            metrics = BoringLayout.isBoring(this.mParams.text, this.mParams.paint);
        }
        switch (this.mParams.measureMode) {
            case 0:
                width = (int) Math.ceil((double) Layout.getDesiredWidth(this.mParams.text, this.mParams.paint));
                break;
            case 1:
                width = this.mParams.width;
                break;
            case 2:
                width = Math.min((int) Math.ceil((double) Layout.getDesiredWidth(this.mParams.text, this.mParams.paint)), this.mParams.width);
                break;
            default:
                throw new IllegalStateException("Unexpected measure mode " + this.mParams.measureMode);
        }
        if (metrics != null) {
            layout = BoringLayout.make(this.mParams.text, this.mParams.paint, width, this.mParams.alignment, this.mParams.spacingMult, this.mParams.spacingAdd, metrics, this.mParams.includePadding, this.mParams.ellipsize, width);
        } else {
            while (true) {
                try {
                    layout = StaticLayoutHelper.make(this.mParams.text, 0, this.mParams.text.length(), this.mParams.paint, width, this.mParams.alignment, this.mParams.spacingMult, this.mParams.spacingAdd, this.mParams.includePadding, this.mParams.ellipsize, width, numLines, this.mParams.textDirection);
                    break;
                } catch (Throwable e) {
                    if (this.mParams.text instanceof String) {
                        throw e;
                    }
                    Log.e("TextLayoutBuilder", "Hit bug #35412, retrying with Spannables removed", e);
                    this.mParams.text = this.mParams.text.toString();
                }
            }
        }
        if (this.mShouldCacheLayout && !hasClickableSpans) {
            this.mSavedLayout = layout;
            sCache.put(Integer.valueOf(hashCode), layout);
        }
        this.mParams.mForceNewPaint = true;
        if (this.mShouldWarmText && this.mGlyphWarmer != null) {
            this.mGlyphWarmer.warmLayout(layout);
        }
        return layout;
    }
}

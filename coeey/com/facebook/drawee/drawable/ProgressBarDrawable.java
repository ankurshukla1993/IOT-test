package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import io.fabric.sdk.android.services.common.AbstractSpiCall;

public class ProgressBarDrawable extends Drawable {
    private int mBackgroundColor = Integer.MIN_VALUE;
    private int mBarWidth = 20;
    private int mColor = -2147450625;
    private boolean mHideWhenZero = false;
    private int mLevel = 0;
    private int mPadding = 10;
    private final Paint mPaint = new Paint(1);

    public void setColor(int color) {
        if (this.mColor != color) {
            this.mColor = color;
            invalidateSelf();
        }
    }

    public int getColor() {
        return this.mColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        if (this.mBackgroundColor != backgroundColor) {
            this.mBackgroundColor = backgroundColor;
            invalidateSelf();
        }
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setPadding(int padding) {
        if (this.mPadding != padding) {
            this.mPadding = padding;
            invalidateSelf();
        }
    }

    public boolean getPadding(Rect padding) {
        padding.set(this.mPadding, this.mPadding, this.mPadding, this.mPadding);
        return this.mPadding != 0;
    }

    public void setBarWidth(int barWidth) {
        if (this.mBarWidth != barWidth) {
            this.mBarWidth = barWidth;
            invalidateSelf();
        }
    }

    public int getBarWidth() {
        return this.mBarWidth;
    }

    public void setHideWhenZero(boolean hideWhenZero) {
        this.mHideWhenZero = hideWhenZero;
    }

    public boolean getHideWhenZero() {
        return this.mHideWhenZero;
    }

    protected boolean onLevelChange(int level) {
        this.mLevel = level;
        invalidateSelf();
        return true;
    }

    public void setAlpha(int alpha) {
        this.mPaint.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
    }

    public int getOpacity() {
        return DrawableUtils.getOpacityFromColor(this.mPaint.getColor());
    }

    public void draw(Canvas canvas) {
        if (!this.mHideWhenZero || this.mLevel != 0) {
            drawBar(canvas, AbstractSpiCall.DEFAULT_TIMEOUT, this.mBackgroundColor);
            drawBar(canvas, this.mLevel, this.mColor);
        }
    }

    private void drawBar(Canvas canvas, int level, int color) {
        Rect bounds = getBounds();
        int length = ((bounds.width() - (this.mPadding * 2)) * level) / AbstractSpiCall.DEFAULT_TIMEOUT;
        int xpos = bounds.left + this.mPadding;
        int ypos = (bounds.bottom - this.mPadding) - this.mBarWidth;
        this.mPaint.setColor(color);
        canvas.drawRect((float) xpos, (float) ypos, (float) (xpos + length), (float) (this.mBarWidth + ypos), this.mPaint);
    }
}

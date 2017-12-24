package com.facebook.drawee.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import javax.annotation.Nullable;

public class LightBitmapDrawable extends Drawable {
    private static final int DEFAULT_PAINT_FLAGS = 6;
    @Nullable
    private Bitmap mBitmap;
    private int mBitmapHeight;
    private int mBitmapWidth;
    private final Paint mPaint;
    private int mTargetDensity;

    public LightBitmapDrawable(Resources res, Bitmap bitmap) {
        this(res, bitmap, null);
    }

    public LightBitmapDrawable(Resources res, Bitmap bitmap, Paint paint) {
        this.mBitmap = null;
        this.mTargetDensity = 160;
        this.mPaint = new Paint(6);
        if (paint != null) {
            this.mPaint.set(paint);
        }
        this.mBitmap = bitmap;
        this.mTargetDensity = res.getDisplayMetrics().densityDpi;
        computeBitmapSize();
    }

    public Paint getPaint() {
        return this.mPaint;
    }

    @Nullable
    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    private void computeBitmapSize() {
        if (this.mBitmap != null) {
            this.mBitmapWidth = this.mBitmap.getScaledWidth(this.mTargetDensity);
            this.mBitmapHeight = this.mBitmap.getScaledHeight(this.mTargetDensity);
            return;
        }
        this.mBitmapHeight = -1;
        this.mBitmapWidth = -1;
    }

    public void setBitmap(Bitmap bitmap) {
        if (this.mBitmap != bitmap) {
            this.mBitmap = bitmap;
            computeBitmapSize();
            invalidateSelf();
        }
    }

    public void setAntiAlias(boolean antiAlias) {
        this.mPaint.setAntiAlias(antiAlias);
        invalidateSelf();
    }

    public boolean hasAntiAlias() {
        return this.mPaint.isAntiAlias();
    }

    public void setFilterBitmap(boolean filter) {
        this.mPaint.setFilterBitmap(filter);
        invalidateSelf();
    }

    public void setDither(boolean dither) {
        this.mPaint.setDither(dither);
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        if (this.mBitmap != null) {
            canvas.drawBitmap(this.mBitmap, null, getBounds(), this.mPaint);
        }
    }

    public void setAlpha(int alpha) {
        if (alpha != this.mPaint.getAlpha()) {
            this.mPaint.setAlpha(alpha);
            invalidateSelf();
        }
    }

    public int getAlpha() {
        return this.mPaint.getAlpha();
    }

    public void setColorFilter(ColorFilter cf) {
        this.mPaint.setColorFilter(cf);
        invalidateSelf();
    }

    public ColorFilter getColorFilter() {
        return this.mPaint.getColorFilter();
    }

    public int getIntrinsicWidth() {
        return this.mBitmapWidth;
    }

    public int getIntrinsicHeight() {
        return this.mBitmapHeight;
    }

    public int getOpacity() {
        return (this.mBitmap == null || this.mBitmap.hasAlpha() || this.mPaint.getAlpha() < 255) ? -3 : -1;
    }
}

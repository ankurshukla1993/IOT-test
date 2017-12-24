package com.facebook.drawee.drawable;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;

public class ForwardingDrawable extends Drawable implements Callback, TransformCallback, TransformAwareDrawable, DrawableParent {
    private static final Matrix sTempTransform = new Matrix();
    private Drawable mCurrentDelegate;
    private final DrawableProperties mDrawableProperties = new DrawableProperties();
    protected TransformCallback mTransformCallback;

    public ForwardingDrawable(Drawable drawable) {
        this.mCurrentDelegate = drawable;
        DrawableUtils.setCallbacks(this.mCurrentDelegate, this, this);
    }

    public Drawable setCurrent(Drawable newDelegate) {
        Drawable previousDelegate = setCurrentWithoutInvalidate(newDelegate);
        invalidateSelf();
        return previousDelegate;
    }

    protected Drawable setCurrentWithoutInvalidate(Drawable newDelegate) {
        Drawable previousDelegate = this.mCurrentDelegate;
        DrawableUtils.setCallbacks(previousDelegate, null, null);
        DrawableUtils.setCallbacks(newDelegate, null, null);
        DrawableUtils.setDrawableProperties(newDelegate, this.mDrawableProperties);
        DrawableUtils.copyProperties(newDelegate, this);
        DrawableUtils.setCallbacks(newDelegate, this, this);
        this.mCurrentDelegate = newDelegate;
        return previousDelegate;
    }

    public int getOpacity() {
        return this.mCurrentDelegate.getOpacity();
    }

    public void setAlpha(int alpha) {
        this.mDrawableProperties.setAlpha(alpha);
        this.mCurrentDelegate.setAlpha(alpha);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.mDrawableProperties.setColorFilter(colorFilter);
        this.mCurrentDelegate.setColorFilter(colorFilter);
    }

    public void setDither(boolean dither) {
        this.mDrawableProperties.setDither(dither);
        this.mCurrentDelegate.setDither(dither);
    }

    public void setFilterBitmap(boolean filterBitmap) {
        this.mDrawableProperties.setFilterBitmap(filterBitmap);
        this.mCurrentDelegate.setFilterBitmap(filterBitmap);
    }

    public boolean setVisible(boolean visible, boolean restart) {
        super.setVisible(visible, restart);
        return this.mCurrentDelegate.setVisible(visible, restart);
    }

    protected void onBoundsChange(Rect bounds) {
        this.mCurrentDelegate.setBounds(bounds);
    }

    public boolean isStateful() {
        return this.mCurrentDelegate.isStateful();
    }

    protected boolean onStateChange(int[] state) {
        return this.mCurrentDelegate.setState(state);
    }

    protected boolean onLevelChange(int level) {
        return this.mCurrentDelegate.setLevel(level);
    }

    public void draw(Canvas canvas) {
        this.mCurrentDelegate.draw(canvas);
    }

    public int getIntrinsicWidth() {
        return this.mCurrentDelegate.getIntrinsicWidth();
    }

    public int getIntrinsicHeight() {
        return this.mCurrentDelegate.getIntrinsicHeight();
    }

    public boolean getPadding(Rect padding) {
        return this.mCurrentDelegate.getPadding(padding);
    }

    public Drawable mutate() {
        this.mCurrentDelegate.mutate();
        return this;
    }

    public Drawable getCurrent() {
        return this.mCurrentDelegate;
    }

    public Drawable setDrawable(Drawable newDrawable) {
        return setCurrent(newDrawable);
    }

    public Drawable getDrawable() {
        return getCurrent();
    }

    public void invalidateDrawable(Drawable who) {
        invalidateSelf();
    }

    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        scheduleSelf(what, when);
    }

    public void unscheduleDrawable(Drawable who, Runnable what) {
        unscheduleSelf(what);
    }

    public void setTransformCallback(TransformCallback transformCallback) {
        this.mTransformCallback = transformCallback;
    }

    protected void getParentTransform(Matrix transform) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getTransform(transform);
        } else {
            transform.reset();
        }
    }

    public void getTransform(Matrix transform) {
        getParentTransform(transform);
    }

    public void getRootBounds(RectF bounds) {
        if (this.mTransformCallback != null) {
            this.mTransformCallback.getRootBounds(bounds);
        } else {
            bounds.set(getBounds());
        }
    }

    public void getTransformedBounds(RectF outBounds) {
        getParentTransform(sTempTransform);
        outBounds.set(getBounds());
        sTempTransform.mapRect(outBounds);
    }

    @TargetApi(21)
    public void setHotspot(float x, float y) {
        this.mCurrentDelegate.setHotspot(x, y);
    }
}

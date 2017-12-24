package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;

public class ScaleTypeDrawable extends ForwardingDrawable {
    @VisibleForTesting
    Matrix mDrawMatrix;
    @VisibleForTesting
    PointF mFocusPoint = null;
    @VisibleForTesting
    ScalingUtils$ScaleType mScaleType;
    @VisibleForTesting
    Object mScaleTypeState;
    private Matrix mTempMatrix = new Matrix();
    @VisibleForTesting
    int mUnderlyingHeight = 0;
    @VisibleForTesting
    int mUnderlyingWidth = 0;

    public ScaleTypeDrawable(Drawable drawable, ScalingUtils$ScaleType scaleType) {
        super((Drawable) Preconditions.checkNotNull(drawable));
        this.mScaleType = scaleType;
    }

    public Drawable setCurrent(Drawable newDelegate) {
        Drawable previousDelegate = super.setCurrent(newDelegate);
        configureBounds();
        return previousDelegate;
    }

    public ScalingUtils$ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void setScaleType(ScalingUtils$ScaleType scaleType) {
        this.mScaleType = scaleType;
        this.mScaleTypeState = null;
        configureBounds();
        invalidateSelf();
    }

    public PointF getFocusPoint() {
        return this.mFocusPoint;
    }

    public void setFocusPoint(PointF focusPoint) {
        if (this.mFocusPoint == null) {
            this.mFocusPoint = new PointF();
        }
        this.mFocusPoint.set(focusPoint);
        configureBounds();
        invalidateSelf();
    }

    public void draw(Canvas canvas) {
        configureBoundsIfUnderlyingChanged();
        if (this.mDrawMatrix != null) {
            int saveCount = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.mDrawMatrix);
            super.draw(canvas);
            canvas.restoreToCount(saveCount);
            return;
        }
        super.draw(canvas);
    }

    protected void onBoundsChange(Rect bounds) {
        configureBounds();
    }

    private void configureBoundsIfUnderlyingChanged() {
        boolean underlyingChanged;
        boolean scaleTypeChanged = false;
        if (this.mScaleType instanceof ScalingUtils$StatefulScaleType) {
            Object state = ((ScalingUtils$StatefulScaleType) this.mScaleType).getState();
            if (state == null || !state.equals(this.mScaleTypeState)) {
                scaleTypeChanged = true;
            } else {
                scaleTypeChanged = false;
            }
            this.mScaleTypeState = state;
        }
        if (this.mUnderlyingWidth == getCurrent().getIntrinsicWidth() && this.mUnderlyingHeight == getCurrent().getIntrinsicHeight()) {
            underlyingChanged = false;
        } else {
            underlyingChanged = true;
        }
        if (underlyingChanged || scaleTypeChanged) {
            configureBounds();
        }
    }

    @VisibleForTesting
    void configureBounds() {
        float f = 0.5f;
        Drawable underlyingDrawable = getCurrent();
        Rect bounds = getBounds();
        int viewWidth = bounds.width();
        int viewHeight = bounds.height();
        int underlyingWidth = underlyingDrawable.getIntrinsicWidth();
        this.mUnderlyingWidth = underlyingWidth;
        int underlyingHeight = underlyingDrawable.getIntrinsicHeight();
        this.mUnderlyingHeight = underlyingHeight;
        if (underlyingWidth <= 0 || underlyingHeight <= 0) {
            underlyingDrawable.setBounds(bounds);
            this.mDrawMatrix = null;
        } else if (underlyingWidth == viewWidth && underlyingHeight == viewHeight) {
            underlyingDrawable.setBounds(bounds);
            this.mDrawMatrix = null;
        } else if (this.mScaleType == ScalingUtils$ScaleType.FIT_XY) {
            underlyingDrawable.setBounds(bounds);
            this.mDrawMatrix = null;
        } else {
            underlyingDrawable.setBounds(0, 0, underlyingWidth, underlyingHeight);
            ScalingUtils$ScaleType scalingUtils$ScaleType = this.mScaleType;
            Matrix matrix = this.mTempMatrix;
            float f2 = this.mFocusPoint != null ? this.mFocusPoint.x : 0.5f;
            if (this.mFocusPoint != null) {
                f = this.mFocusPoint.y;
            }
            scalingUtils$ScaleType.getTransform(matrix, bounds, underlyingWidth, underlyingHeight, f2, f);
            this.mDrawMatrix = this.mTempMatrix;
        }
    }

    public void getTransform(Matrix transform) {
        getParentTransform(transform);
        configureBoundsIfUnderlyingChanged();
        if (this.mDrawMatrix != null) {
            transform.preConcat(this.mDrawMatrix);
        }
    }
}

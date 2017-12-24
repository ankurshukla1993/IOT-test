package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;

public class MatrixDrawable extends ForwardingDrawable {
    private Matrix mDrawMatrix;
    private Matrix mMatrix;
    private int mUnderlyingHeight = 0;
    private int mUnderlyingWidth = 0;

    public MatrixDrawable(Drawable drawable, Matrix matrix) {
        super((Drawable) Preconditions.checkNotNull(drawable));
        this.mMatrix = matrix;
    }

    public Drawable setCurrent(Drawable newDelegate) {
        Drawable previousDelegate = super.setCurrent(newDelegate);
        configureBounds();
        return previousDelegate;
    }

    public Matrix getMatrix() {
        return this.mMatrix;
    }

    public void setMatrix(Matrix matrix) {
        this.mMatrix = matrix;
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
        super.onBoundsChange(bounds);
        configureBounds();
    }

    private void configureBoundsIfUnderlyingChanged() {
        if (this.mUnderlyingWidth != getCurrent().getIntrinsicWidth() || this.mUnderlyingHeight != getCurrent().getIntrinsicHeight()) {
            configureBounds();
        }
    }

    private void configureBounds() {
        Drawable underlyingDrawable = getCurrent();
        Rect bounds = getBounds();
        int underlyingWidth = underlyingDrawable.getIntrinsicWidth();
        this.mUnderlyingWidth = underlyingWidth;
        int underlyingHeight = underlyingDrawable.getIntrinsicHeight();
        this.mUnderlyingHeight = underlyingHeight;
        if (underlyingWidth <= 0 || underlyingHeight <= 0) {
            underlyingDrawable.setBounds(bounds);
            this.mDrawMatrix = null;
            return;
        }
        underlyingDrawable.setBounds(0, 0, underlyingWidth, underlyingHeight);
        this.mDrawMatrix = this.mMatrix;
    }

    public void getTransform(Matrix transform) {
        super.getTransform(transform);
        if (this.mDrawMatrix != null) {
            transform.preConcat(this.mDrawMatrix);
        }
    }
}

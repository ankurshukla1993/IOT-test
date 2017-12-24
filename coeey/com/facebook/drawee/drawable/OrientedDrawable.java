package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;

public class OrientedDrawable extends ForwardingDrawable {
    private int mRotationAngle;
    @VisibleForTesting
    final Matrix mRotationMatrix;
    private final Matrix mTempMatrix = new Matrix();
    private final RectF mTempRectF = new RectF();

    public OrientedDrawable(Drawable drawable, int rotationAngle) {
        super(drawable);
        Preconditions.checkArgument(rotationAngle % 90 == 0);
        this.mRotationMatrix = new Matrix();
        this.mRotationAngle = rotationAngle;
    }

    public void draw(Canvas canvas) {
        if (this.mRotationAngle <= 0) {
            super.draw(canvas);
            return;
        }
        int saveCount = canvas.save();
        canvas.concat(this.mRotationMatrix);
        super.draw(canvas);
        canvas.restoreToCount(saveCount);
    }

    public int getIntrinsicWidth() {
        return this.mRotationAngle % 180 == 0 ? super.getIntrinsicWidth() : super.getIntrinsicHeight();
    }

    public int getIntrinsicHeight() {
        return this.mRotationAngle % 180 == 0 ? super.getIntrinsicHeight() : super.getIntrinsicWidth();
    }

    protected void onBoundsChange(Rect bounds) {
        Drawable underlyingDrawable = getCurrent();
        if (this.mRotationAngle > 0) {
            this.mRotationMatrix.setRotate((float) this.mRotationAngle, (float) bounds.centerX(), (float) bounds.centerY());
            this.mTempMatrix.reset();
            this.mRotationMatrix.invert(this.mTempMatrix);
            this.mTempRectF.set(bounds);
            this.mTempMatrix.mapRect(this.mTempRectF);
            underlyingDrawable.setBounds((int) this.mTempRectF.left, (int) this.mTempRectF.top, (int) this.mTempRectF.right, (int) this.mTempRectF.bottom);
            return;
        }
        underlyingDrawable.setBounds(bounds);
    }

    public void getTransform(Matrix transform) {
        getParentTransform(transform);
        if (!this.mRotationMatrix.isIdentity()) {
            transform.preConcat(this.mRotationMatrix);
        }
    }
}

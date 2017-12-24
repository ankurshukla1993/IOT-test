package com.facebook.drawee.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Path.FillType;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.util.Arrays;

public class RoundedCornersDrawable extends ForwardingDrawable implements Rounded {
    private int mBorderColor = 0;
    private final Path mBorderPath = new Path();
    @VisibleForTesting
    final float[] mBorderRadii = new float[8];
    private float mBorderWidth = 0.0f;
    private boolean mIsCircle = false;
    private int mOverlayColor = 0;
    private float mPadding = 0.0f;
    @VisibleForTesting
    final Paint mPaint = new Paint(1);
    private final Path mPath = new Path();
    private final float[] mRadii = new float[8];
    private final RectF mTempRectangle = new RectF();
    @VisibleForTesting
    Type mType = Type.OVERLAY_COLOR;

    public enum Type {
        OVERLAY_COLOR,
        CLIPPING
    }

    public RoundedCornersDrawable(Drawable drawable) {
        super((Drawable) Preconditions.checkNotNull(drawable));
    }

    public void setType(Type type) {
        this.mType = type;
        invalidateSelf();
    }

    public void setCircle(boolean isCircle) {
        this.mIsCircle = isCircle;
        updatePath();
        invalidateSelf();
    }

    public boolean isCircle() {
        return this.mIsCircle;
    }

    public void setRadius(float radius) {
        Arrays.fill(this.mRadii, radius);
        updatePath();
        invalidateSelf();
    }

    public void setRadii(float[] radii) {
        if (radii == null) {
            Arrays.fill(this.mRadii, 0.0f);
        } else {
            boolean z;
            if (radii.length == 8) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "radii should have exactly 8 values");
            System.arraycopy(radii, 0, this.mRadii, 0, 8);
        }
        updatePath();
        invalidateSelf();
    }

    public float[] getRadii() {
        return this.mRadii;
    }

    public void setOverlayColor(int overlayColor) {
        this.mOverlayColor = overlayColor;
        invalidateSelf();
    }

    public int getOverlayColor() {
        return this.mOverlayColor;
    }

    public void setBorder(int color, float width) {
        this.mBorderColor = color;
        this.mBorderWidth = width;
        updatePath();
        invalidateSelf();
    }

    public int getBorderColor() {
        return this.mBorderColor;
    }

    public float getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setPadding(float padding) {
        this.mPadding = padding;
        updatePath();
        invalidateSelf();
    }

    public float getPadding() {
        return this.mPadding;
    }

    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        updatePath();
    }

    private void updatePath() {
        this.mPath.reset();
        this.mBorderPath.reset();
        this.mTempRectangle.set(getBounds());
        this.mTempRectangle.inset(this.mPadding, this.mPadding);
        if (this.mIsCircle) {
            this.mPath.addCircle(this.mTempRectangle.centerX(), this.mTempRectangle.centerY(), Math.min(this.mTempRectangle.width(), this.mTempRectangle.height()) / 2.0f, Direction.CW);
        } else {
            this.mPath.addRoundRect(this.mTempRectangle, this.mRadii, Direction.CW);
        }
        this.mTempRectangle.inset(-this.mPadding, -this.mPadding);
        this.mTempRectangle.inset(this.mBorderWidth / 2.0f, this.mBorderWidth / 2.0f);
        if (this.mIsCircle) {
            this.mBorderPath.addCircle(this.mTempRectangle.centerX(), this.mTempRectangle.centerY(), Math.min(this.mTempRectangle.width(), this.mTempRectangle.height()) / 2.0f, Direction.CW);
        } else {
            for (int i = 0; i < this.mBorderRadii.length; i++) {
                this.mBorderRadii[i] = (this.mRadii[i] + this.mPadding) - (this.mBorderWidth / 2.0f);
            }
            this.mBorderPath.addRoundRect(this.mTempRectangle, this.mBorderRadii, Direction.CW);
        }
        this.mTempRectangle.inset((-this.mBorderWidth) / 2.0f, (-this.mBorderWidth) / 2.0f);
    }

    public void draw(Canvas canvas) {
        Rect bounds = getBounds();
        switch (this.mType) {
            case CLIPPING:
                int saveCount = canvas.save();
                this.mPath.setFillType(FillType.EVEN_ODD);
                canvas.clipPath(this.mPath);
                super.draw(canvas);
                canvas.restoreToCount(saveCount);
                break;
            case OVERLAY_COLOR:
                super.draw(canvas);
                this.mPaint.setColor(this.mOverlayColor);
                this.mPaint.setStyle(Style.FILL);
                this.mPath.setFillType(FillType.INVERSE_EVEN_ODD);
                canvas.drawPath(this.mPath, this.mPaint);
                if (this.mIsCircle) {
                    float paddingH = (((float) (bounds.width() - bounds.height())) + this.mBorderWidth) / 2.0f;
                    float paddingV = (((float) (bounds.height() - bounds.width())) + this.mBorderWidth) / 2.0f;
                    if (paddingH > 0.0f) {
                        canvas.drawRect((float) bounds.left, (float) bounds.top, ((float) bounds.left) + paddingH, (float) bounds.bottom, this.mPaint);
                        canvas.drawRect(((float) bounds.right) - paddingH, (float) bounds.top, (float) bounds.right, (float) bounds.bottom, this.mPaint);
                    }
                    if (paddingV > 0.0f) {
                        canvas.drawRect((float) bounds.left, (float) bounds.top, (float) bounds.right, ((float) bounds.top) + paddingV, this.mPaint);
                        canvas.drawRect((float) bounds.left, ((float) bounds.bottom) - paddingV, (float) bounds.right, (float) bounds.bottom, this.mPaint);
                        break;
                    }
                }
                break;
        }
        if (this.mBorderColor != 0) {
            this.mPaint.setStyle(Style.STROKE);
            this.mPaint.setColor(this.mBorderColor);
            this.mPaint.setStrokeWidth(this.mBorderWidth);
            this.mPath.setFillType(FillType.EVEN_ODD);
            canvas.drawPath(this.mBorderPath, this.mPaint);
        }
    }
}

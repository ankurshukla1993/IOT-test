package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import javax.annotation.Nullable;

abstract class AbstractDrawBorder extends AbstractDrawCommand {
    private static final int BORDER_PATH_DIRTY = 1;
    private static final Paint PAINT = new Paint(1);
    private static final RectF TMP_RECT = new RectF();
    private int mBorderColor = ViewCompat.MEASURED_STATE_MASK;
    private float mBorderRadius;
    private float mBorderWidth;
    @Nullable
    private Path mPathForBorderRadius;
    private int mSetPropertiesFlag;

    AbstractDrawBorder() {
    }

    static {
        PAINT.setStyle(Style.STROKE);
    }

    public final void setBorderWidth(float borderWidth) {
        this.mBorderWidth = borderWidth;
        setFlag(1);
    }

    public final float getBorderWidth() {
        return this.mBorderWidth;
    }

    public void setBorderRadius(float borderRadius) {
        this.mBorderRadius = borderRadius;
        setFlag(1);
    }

    public final float getBorderRadius() {
        return this.mBorderRadius;
    }

    public final void setBorderColor(int borderColor) {
        this.mBorderColor = borderColor;
    }

    public final int getBorderColor() {
        return this.mBorderColor;
    }

    protected void onBoundsChanged() {
        setFlag(1);
    }

    protected final void drawBorders(Canvas canvas) {
        if (this.mBorderWidth >= 0.5f && this.mBorderColor != 0) {
            PAINT.setColor(this.mBorderColor);
            PAINT.setStrokeWidth(this.mBorderWidth);
            PAINT.setPathEffect(getPathEffectForBorderStyle());
            canvas.drawPath(getPathForBorderRadius(), PAINT);
        }
    }

    protected final void updatePath(Path path, float correction) {
        path.reset();
        TMP_RECT.set(getLeft() + correction, getTop() + correction, getRight() - correction, getBottom() - correction);
        path.addRoundRect(TMP_RECT, this.mBorderRadius, this.mBorderRadius, Direction.CW);
    }

    @Nullable
    protected PathEffect getPathEffectForBorderStyle() {
        return null;
    }

    protected final boolean isFlagSet(int mask) {
        return (this.mSetPropertiesFlag & mask) == mask;
    }

    protected final void setFlag(int mask) {
        this.mSetPropertiesFlag |= mask;
    }

    protected final void resetFlag(int mask) {
        this.mSetPropertiesFlag &= mask ^ -1;
    }

    protected final Path getPathForBorderRadius() {
        if (isFlagSet(1)) {
            if (this.mPathForBorderRadius == null) {
                this.mPathForBorderRadius = new Path();
            }
            updatePath(this.mPathForBorderRadius, this.mBorderWidth * 0.5f);
            resetFlag(1);
        }
        return this.mPathForBorderRadius;
    }
}

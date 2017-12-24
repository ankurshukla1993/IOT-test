package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import javax.annotation.Nullable;

final class DrawView extends AbstractDrawCommand {
    public static final DrawView[] EMPTY_ARRAY = new DrawView[0];
    static final float MINIMUM_ROUNDED_CLIPPING_VALUE = 0.5f;
    private final RectF TMP_RECT = new RectF();
    private float mClipRadius;
    float mLogicalBottom;
    float mLogicalLeft;
    float mLogicalRight;
    float mLogicalTop;
    @Nullable
    private Path mPath;
    boolean mWasMounted;
    final int reactTag;

    public DrawView(int reactTag) {
        this.reactTag = reactTag;
    }

    public DrawView collectDrawView(float left, float top, float right, float bottom, float logicalLeft, float logicalTop, float logicalRight, float logicalBottom, float clipLeft, float clipTop, float clipRight, float clipBottom, float clipRadius) {
        if (isFrozen()) {
            boolean boundsMatch = boundsMatch(left, top, right, bottom);
            boolean clipBoundsMatch = clipBoundsMatch(clipLeft, clipTop, clipRight, clipBottom);
            boolean clipRadiusMatch = this.mClipRadius == clipRadius;
            boolean logicalBoundsMatch = logicalBoundsMatch(logicalLeft, logicalTop, logicalRight, logicalBottom);
            if (boundsMatch && clipBoundsMatch && clipRadiusMatch && logicalBoundsMatch) {
                return this;
            }
            DrawView drawView = (DrawView) mutableCopy();
            if (!boundsMatch) {
                drawView.setBounds(left, top, right, bottom);
            }
            if (!clipBoundsMatch) {
                drawView.setClipBounds(clipLeft, clipTop, clipRight, clipBottom);
            }
            if (!logicalBoundsMatch) {
                drawView.setLogicalBounds(logicalLeft, logicalTop, logicalRight, logicalBottom);
            }
            if (!(clipRadiusMatch && boundsMatch)) {
                drawView.setClipRadius(clipRadius);
            }
            drawView.mWasMounted = false;
            drawView.freeze();
            return drawView;
        }
        setBounds(left, top, right, bottom);
        setClipBounds(clipLeft, clipTop, clipRight, clipBottom);
        setClipRadius(clipRadius);
        setLogicalBounds(logicalLeft, logicalTop, logicalRight, logicalBottom);
        freeze();
        return this;
    }

    private boolean logicalBoundsMatch(float left, float top, float right, float bottom) {
        return left == this.mLogicalLeft && top == this.mLogicalTop && right == this.mLogicalRight && bottom == this.mLogicalBottom;
    }

    private void setLogicalBounds(float left, float top, float right, float bottom) {
        this.mLogicalLeft = left;
        this.mLogicalTop = top;
        this.mLogicalRight = right;
        this.mLogicalBottom = bottom;
    }

    public void draw(FlatViewGroup parent, Canvas canvas) {
        onPreDraw(parent, canvas);
        if (this.mNeedsClipping || this.mClipRadius > MINIMUM_ROUNDED_CLIPPING_VALUE) {
            canvas.save(2);
            applyClipping(canvas);
            parent.drawNextChild(canvas);
            canvas.restore();
            return;
        }
        parent.drawNextChild(canvas);
    }

    void setClipRadius(float clipRadius) {
        this.mClipRadius = clipRadius;
        if (clipRadius > MINIMUM_ROUNDED_CLIPPING_VALUE) {
            updateClipPath();
        } else {
            this.mPath = null;
        }
    }

    private void updateClipPath() {
        this.mPath = new Path();
        this.TMP_RECT.set(getLeft(), getTop(), getRight(), getBottom());
        this.mPath.addRoundRect(this.TMP_RECT, this.mClipRadius, this.mClipRadius, Direction.CW);
    }

    protected void applyClipping(Canvas canvas) {
        if (this.mClipRadius > MINIMUM_ROUNDED_CLIPPING_VALUE) {
            canvas.clipPath(this.mPath);
        } else {
            super.applyClipping(canvas);
        }
    }

    protected void onDraw(Canvas canvas) {
    }

    protected void onDebugDraw(FlatViewGroup parent, Canvas canvas) {
        parent.debugDrawNextChild(canvas);
    }

    protected void onDebugDrawHighlight(Canvas canvas) {
        if (this.mPath != null) {
            debugDrawWarningHighlight(canvas, "borderRadius: " + this.mClipRadius);
        } else if (!boundsMatch(this.mLogicalLeft, this.mLogicalTop, this.mLogicalRight, this.mLogicalBottom)) {
            StringBuilder warn = new StringBuilder("Overflow: { ");
            String[] names = new String[]{"left: ", "top: ", "right: ", "bottom: "};
            offsets = new float[4];
            int i = 0 + 1;
            offsets[0] = getLeft() - this.mLogicalLeft;
            int i2 = i + 1;
            offsets[i] = getTop() - this.mLogicalTop;
            i = i2 + 1;
            offsets[i2] = this.mLogicalRight - getRight();
            i2 = i + 1;
            offsets[i] = this.mLogicalBottom - getBottom();
            for (i2 = 0; i2 < 4; i2++) {
                if (offsets[i2] != 0.0f) {
                    warn.append(names[i2]);
                    warn.append(offsets[i2]);
                    warn.append(", ");
                }
            }
            warn.append("}");
            debugDrawCautionHighlight(canvas, warn.toString());
        }
    }
}

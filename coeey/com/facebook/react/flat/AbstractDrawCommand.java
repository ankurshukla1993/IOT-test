package com.facebook.react.flat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import com.ihealth.communication.manager.iHealthDevicesManager.ScanDevice;

abstract class AbstractDrawCommand extends DrawCommand implements Cloneable {
    private static Paint sDebugHighlightOverlayText;
    private static Paint sDebugHighlightRed;
    private static Paint sDebugHighlightYellow;
    private float mBottom;
    private float mClipBottom;
    private float mClipLeft;
    private float mClipRight;
    private float mClipTop;
    private boolean mFrozen;
    private float mLeft;
    protected boolean mNeedsClipping;
    private float mRight;
    private float mTop;

    protected abstract void onDraw(Canvas canvas);

    AbstractDrawCommand() {
    }

    public final boolean clipBoundsMatch(float clipLeft, float clipTop, float clipRight, float clipBottom) {
        return this.mClipLeft == clipLeft && this.mClipTop == clipTop && this.mClipRight == clipRight && this.mClipBottom == clipBottom;
    }

    protected final void setClipBounds(float clipLeft, float clipTop, float clipRight, float clipBottom) {
        this.mClipLeft = clipLeft;
        this.mClipTop = clipTop;
        this.mClipRight = clipRight;
        this.mClipBottom = clipBottom;
        this.mNeedsClipping = this.mClipLeft != Float.NEGATIVE_INFINITY;
    }

    public final float getClipLeft() {
        return this.mClipLeft;
    }

    public final float getClipTop() {
        return this.mClipTop;
    }

    public final float getClipRight() {
        return this.mClipRight;
    }

    public final float getClipBottom() {
        return this.mClipBottom;
    }

    protected void applyClipping(Canvas canvas) {
        canvas.clipRect(this.mClipLeft, this.mClipTop, this.mClipRight, this.mClipBottom);
    }

    public void draw(FlatViewGroup parent, Canvas canvas) {
        onPreDraw(parent, canvas);
        if (this.mNeedsClipping && shouldClip()) {
            canvas.save(2);
            applyClipping(canvas);
            onDraw(canvas);
            canvas.restore();
            return;
        }
        onDraw(canvas);
    }

    protected static int getDebugBorderColor() {
        return -16711681;
    }

    protected String getDebugName() {
        return getClass().getSimpleName().substring(4);
    }

    private void initDebugHighlightResources(FlatViewGroup parent) {
        if (sDebugHighlightRed == null) {
            sDebugHighlightRed = new Paint();
            sDebugHighlightRed.setARGB(75, 255, 0, 0);
        }
        if (sDebugHighlightYellow == null) {
            sDebugHighlightYellow = new Paint();
            sDebugHighlightYellow.setARGB(100, 255, ScanDevice.LINK_USB, 0);
        }
        if (sDebugHighlightOverlayText == null) {
            sDebugHighlightOverlayText = new Paint();
            sDebugHighlightOverlayText.setAntiAlias(true);
            sDebugHighlightOverlayText.setARGB(200, 50, 50, 50);
            sDebugHighlightOverlayText.setTextAlign(Align.RIGHT);
            sDebugHighlightOverlayText.setTypeface(Typeface.MONOSPACE);
            sDebugHighlightOverlayText.setTextSize((float) parent.dipsToPixels(9));
        }
    }

    private void debugDrawHighlightRect(Canvas canvas, Paint paint, String text) {
        canvas.drawRect(getLeft(), getTop(), getRight(), getBottom(), paint);
        canvas.drawText(text, getRight() - 5.0f, getBottom() - 5.0f, sDebugHighlightOverlayText);
    }

    protected void debugDrawWarningHighlight(Canvas canvas, String text) {
        debugDrawHighlightRect(canvas, sDebugHighlightRed, text);
    }

    protected void debugDrawCautionHighlight(Canvas canvas, String text) {
        debugDrawHighlightRect(canvas, sDebugHighlightYellow, text);
    }

    public final void debugDraw(FlatViewGroup parent, Canvas canvas) {
        onDebugDraw(parent, canvas);
    }

    protected void onDebugDraw(FlatViewGroup parent, Canvas canvas) {
        parent.debugDrawNamedRect(canvas, getDebugBorderColor(), getDebugName(), this.mLeft, this.mTop, this.mRight, this.mBottom);
    }

    protected void onDebugDrawHighlight(Canvas canvas) {
    }

    protected void onPreDraw(FlatViewGroup parent, Canvas canvas) {
    }

    public AbstractDrawCommand updateBoundsAndFreeze(float left, float top, float right, float bottom, float clipLeft, float clipTop, float clipRight, float clipBottom) {
        if (this.mFrozen) {
            boolean boundsMatch = boundsMatch(left, top, right, bottom);
            boolean clipBoundsMatch = clipBoundsMatch(clipLeft, clipTop, clipRight, clipBottom);
            if (boundsMatch && clipBoundsMatch) {
                return this;
            }
            try {
                AbstractDrawCommand copy = (AbstractDrawCommand) clone();
                if (!boundsMatch) {
                    copy.setBounds(left, top, right, bottom);
                }
                if (!clipBoundsMatch) {
                    copy.setClipBounds(clipLeft, clipTop, clipRight, clipBottom);
                }
                return copy;
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        setBounds(left, top, right, bottom);
        setClipBounds(clipLeft, clipTop, clipRight, clipBottom);
        this.mFrozen = true;
        return this;
    }

    public final AbstractDrawCommand mutableCopy() {
        try {
            AbstractDrawCommand copy = (AbstractDrawCommand) super.clone();
            copy.mFrozen = false;
            return copy;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public final boolean isFrozen() {
        return this.mFrozen;
    }

    public final void freeze() {
        this.mFrozen = true;
    }

    public final float getLeft() {
        return this.mLeft;
    }

    public final float getTop() {
        return this.mTop;
    }

    public final float getRight() {
        return this.mRight;
    }

    public final float getBottom() {
        return this.mBottom;
    }

    protected boolean shouldClip() {
        return this.mLeft < getClipLeft() || this.mTop < getClipTop() || this.mRight > getClipRight() || this.mBottom > getClipBottom();
    }

    protected void onBoundsChanged() {
    }

    protected final void setBounds(float left, float top, float right, float bottom) {
        this.mLeft = left;
        this.mTop = top;
        this.mRight = right;
        this.mBottom = bottom;
        onBoundsChanged();
    }

    protected final boolean boundsMatch(float left, float top, float right, float bottom) {
        return this.mLeft == left && this.mTop == top && this.mRight == right && this.mBottom == bottom;
    }
}

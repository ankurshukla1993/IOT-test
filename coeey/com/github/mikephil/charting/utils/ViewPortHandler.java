package com.github.mikephil.charting.utils;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;
import com.flipboard.bottomsheet.BaseViewTransformer;
import com.google.android.flexbox.FlexItem;

public class ViewPortHandler {
    protected float mChartHeight = 0.0f;
    protected float mChartWidth = 0.0f;
    protected RectF mContentRect = new RectF();
    protected final Matrix mMatrixTouch = new Matrix();
    private float mMaxScaleX = AutoScrollHelper.NO_MAX;
    private float mMaxScaleY = AutoScrollHelper.NO_MAX;
    private float mMinScaleX = FlexItem.FLEX_SHRINK_DEFAULT;
    private float mMinScaleY = FlexItem.FLEX_SHRINK_DEFAULT;
    private float mScaleX = FlexItem.FLEX_SHRINK_DEFAULT;
    private float mScaleY = FlexItem.FLEX_SHRINK_DEFAULT;
    private float mTransOffsetX = 0.0f;
    private float mTransOffsetY = 0.0f;
    private float mTransX = 0.0f;
    private float mTransY = 0.0f;

    public void setChartDimens(float width, float height) {
        float offsetLeft = offsetLeft();
        float offsetTop = offsetTop();
        float offsetRight = offsetRight();
        float offsetBottom = offsetBottom();
        this.mChartHeight = height;
        this.mChartWidth = width;
        restrainViewPort(offsetLeft, offsetTop, offsetRight, offsetBottom);
    }

    public boolean hasChartDimens() {
        if (this.mChartHeight <= 0.0f || this.mChartWidth <= 0.0f) {
            return false;
        }
        return true;
    }

    public void restrainViewPort(float offsetLeft, float offsetTop, float offsetRight, float offsetBottom) {
        this.mContentRect.set(offsetLeft, offsetTop, this.mChartWidth - offsetRight, this.mChartHeight - offsetBottom);
    }

    public float offsetLeft() {
        return this.mContentRect.left;
    }

    public float offsetRight() {
        return this.mChartWidth - this.mContentRect.right;
    }

    public float offsetTop() {
        return this.mContentRect.top;
    }

    public float offsetBottom() {
        return this.mChartHeight - this.mContentRect.bottom;
    }

    public float contentTop() {
        return this.mContentRect.top;
    }

    public float contentLeft() {
        return this.mContentRect.left;
    }

    public float contentRight() {
        return this.mContentRect.right;
    }

    public float contentBottom() {
        return this.mContentRect.bottom;
    }

    public float contentWidth() {
        return this.mContentRect.width();
    }

    public float contentHeight() {
        return this.mContentRect.height();
    }

    public RectF getContentRect() {
        return this.mContentRect;
    }

    public PointF getContentCenter() {
        return new PointF(this.mContentRect.centerX(), this.mContentRect.centerY());
    }

    public float getChartHeight() {
        return this.mChartHeight;
    }

    public float getChartWidth() {
        return this.mChartWidth;
    }

    public Matrix zoomIn(float x, float y) {
        Matrix save = new Matrix();
        save.set(this.mMatrixTouch);
        save.postScale(1.4f, 1.4f, x, y);
        return save;
    }

    public Matrix zoomOut(float x, float y) {
        Matrix save = new Matrix();
        save.set(this.mMatrixTouch);
        save.postScale(BaseViewTransformer.MAX_DIM_ALPHA, BaseViewTransformer.MAX_DIM_ALPHA, x, y);
        return save;
    }

    public Matrix zoom(float scaleX, float scaleY, float x, float y) {
        Matrix save = new Matrix();
        save.set(this.mMatrixTouch);
        save.postScale(scaleX, scaleY, x, y);
        return save;
    }

    public Matrix fitScreen() {
        this.mMinScaleX = FlexItem.FLEX_SHRINK_DEFAULT;
        this.mMinScaleY = FlexItem.FLEX_SHRINK_DEFAULT;
        Matrix save = new Matrix();
        save.set(this.mMatrixTouch);
        float[] vals = new float[9];
        save.getValues(vals);
        vals[2] = 0.0f;
        vals[5] = 0.0f;
        vals[0] = FlexItem.FLEX_SHRINK_DEFAULT;
        vals[4] = FlexItem.FLEX_SHRINK_DEFAULT;
        save.setValues(vals);
        return save;
    }

    public synchronized void centerViewPort(float[] transformedPts, View view) {
        Matrix save = new Matrix();
        save.set(this.mMatrixTouch);
        save.postTranslate(-(transformedPts[0] - offsetLeft()), -(transformedPts[1] - offsetTop()));
        refresh(save, view, true);
    }

    public Matrix refresh(Matrix newMatrix, View chart, boolean invalidate) {
        this.mMatrixTouch.set(newMatrix);
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
        if (invalidate) {
            chart.invalidate();
        }
        newMatrix.set(this.mMatrixTouch);
        return newMatrix;
    }

    public void limitTransAndScale(Matrix matrix, RectF content) {
        float[] vals = new float[9];
        matrix.getValues(vals);
        float curTransX = vals[2];
        float curScaleX = vals[0];
        float curTransY = vals[5];
        float curScaleY = vals[4];
        this.mScaleX = Math.min(Math.max(this.mMinScaleX, curScaleX), this.mMaxScaleX);
        this.mScaleY = Math.min(Math.max(this.mMinScaleY, curScaleY), this.mMaxScaleY);
        float width = 0.0f;
        float height = 0.0f;
        if (content != null) {
            width = content.width();
            height = content.height();
        }
        this.mTransX = Math.min(Math.max(curTransX, ((-width) * (this.mScaleX - FlexItem.FLEX_SHRINK_DEFAULT)) - this.mTransOffsetX), this.mTransOffsetX);
        this.mTransY = Math.max(Math.min(curTransY, this.mTransOffsetY + (height * (this.mScaleY - FlexItem.FLEX_SHRINK_DEFAULT))), -this.mTransOffsetY);
        vals[2] = this.mTransX;
        vals[0] = this.mScaleX;
        vals[5] = this.mTransY;
        vals[4] = this.mScaleY;
        matrix.setValues(vals);
    }

    public void setMinimumScaleX(float xScale) {
        if (xScale < FlexItem.FLEX_SHRINK_DEFAULT) {
            xScale = FlexItem.FLEX_SHRINK_DEFAULT;
        }
        this.mMinScaleX = xScale;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMaximumScaleX(float xScale) {
        this.mMaxScaleX = xScale;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinMaxScaleX(float minScaleX, float maxScaleX) {
        if (minScaleX < FlexItem.FLEX_SHRINK_DEFAULT) {
            minScaleX = FlexItem.FLEX_SHRINK_DEFAULT;
        }
        this.mMinScaleX = minScaleX;
        this.mMaxScaleX = maxScaleX;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMinimumScaleY(float yScale) {
        if (yScale < FlexItem.FLEX_SHRINK_DEFAULT) {
            yScale = FlexItem.FLEX_SHRINK_DEFAULT;
        }
        this.mMinScaleY = yScale;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public void setMaximumScaleY(float yScale) {
        this.mMaxScaleY = yScale;
        limitTransAndScale(this.mMatrixTouch, this.mContentRect);
    }

    public Matrix getMatrixTouch() {
        return this.mMatrixTouch;
    }

    public boolean isInBoundsX(float x) {
        if (isInBoundsLeft(x) && isInBoundsRight(x)) {
            return true;
        }
        return false;
    }

    public boolean isInBoundsY(float y) {
        if (isInBoundsTop(y) && isInBoundsBottom(y)) {
            return true;
        }
        return false;
    }

    public boolean isInBounds(float x, float y) {
        if (isInBoundsX(x) && isInBoundsY(y)) {
            return true;
        }
        return false;
    }

    public boolean isInBoundsLeft(float x) {
        return this.mContentRect.left <= x;
    }

    public boolean isInBoundsRight(float x) {
        return this.mContentRect.right >= ((float) ((int) (x * 100.0f))) / 100.0f;
    }

    public boolean isInBoundsTop(float y) {
        return this.mContentRect.top <= y;
    }

    public boolean isInBoundsBottom(float y) {
        return this.mContentRect.bottom >= ((float) ((int) (y * 100.0f))) / 100.0f;
    }

    public float getScaleX() {
        return this.mScaleX;
    }

    public float getScaleY() {
        return this.mScaleY;
    }

    public float getTransX() {
        return this.mTransX;
    }

    public float getTransY() {
        return this.mTransY;
    }

    public boolean isFullyZoomedOut() {
        if (isFullyZoomedOutX() && isFullyZoomedOutY()) {
            return true;
        }
        return false;
    }

    public boolean isFullyZoomedOutY() {
        if (this.mScaleY > this.mMinScaleY || this.mMinScaleY > FlexItem.FLEX_SHRINK_DEFAULT) {
            return false;
        }
        return true;
    }

    public boolean isFullyZoomedOutX() {
        if (this.mScaleX > this.mMinScaleX || this.mMinScaleX > FlexItem.FLEX_SHRINK_DEFAULT) {
            return false;
        }
        return true;
    }

    public void setDragOffsetX(float offset) {
        this.mTransOffsetX = Utils.convertDpToPixel(offset);
    }

    public void setDragOffsetY(float offset) {
        this.mTransOffsetY = Utils.convertDpToPixel(offset);
    }

    public boolean hasNoDragOffset() {
        return this.mTransOffsetX <= 0.0f && this.mTransOffsetY <= 0.0f;
    }

    public boolean canZoomOutMoreX() {
        return this.mScaleX > this.mMinScaleX;
    }

    public boolean canZoomInMoreX() {
        return this.mScaleX < this.mMaxScaleX;
    }
}

package com.facebook.react.flat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.flat.FlatViewGroup.InvalidateCallback;
import com.facebook.yoga.YogaConstants;
import javax.annotation.Nullable;

final class InlineImageSpanWithPipeline extends ReplacementSpan implements AttachDetachListener, BitmapUpdateListener {
    private static final RectF TMP_RECT = new RectF();
    @Nullable
    private InvalidateCallback mCallback;
    private boolean mFrozen;
    private float mHeight;
    @Nullable
    private PipelineRequestHelper mRequestHelper;
    private float mWidth;

    InlineImageSpanWithPipeline() {
        this(null, YogaConstants.UNDEFINED, YogaConstants.UNDEFINED);
    }

    private InlineImageSpanWithPipeline(@Nullable PipelineRequestHelper requestHelper, float width, float height) {
        this.mRequestHelper = requestHelper;
        this.mWidth = width;
        this.mHeight = height;
    }

    InlineImageSpanWithPipeline mutableCopy() {
        return new InlineImageSpanWithPipeline(this.mRequestHelper, this.mWidth, this.mHeight);
    }

    boolean hasImageRequest() {
        return this.mRequestHelper != null;
    }

    void setImageRequest(@Nullable ImageRequest imageRequest) {
        if (imageRequest == null) {
            this.mRequestHelper = null;
        } else {
            this.mRequestHelper = new PipelineRequestHelper(imageRequest);
        }
    }

    float getWidth() {
        return this.mWidth;
    }

    void setWidth(float width) {
        this.mWidth = width;
    }

    float getHeight() {
        return this.mHeight;
    }

    void setHeight(float height) {
        this.mHeight = height;
    }

    void freeze() {
        this.mFrozen = true;
    }

    boolean isFrozen() {
        return this.mFrozen;
    }

    public void onSecondaryAttach(Bitmap bitmap) {
        ((InvalidateCallback) Assertions.assumeNotNull(this.mCallback)).invalidate();
    }

    public void onBitmapReady(Bitmap bitmap) {
        ((InvalidateCallback) Assertions.assumeNotNull(this.mCallback)).invalidate();
    }

    public void onImageLoadEvent(int imageLoadEvent) {
    }

    public void onAttached(InvalidateCallback callback) {
        this.mCallback = callback;
        if (this.mRequestHelper != null) {
            this.mRequestHelper.attach(this);
        }
    }

    public void onDetached() {
        if (this.mRequestHelper != null) {
            this.mRequestHelper.detach();
            if (this.mRequestHelper.isDetached()) {
                this.mCallback = null;
            }
        }
    }

    public int getSize(Paint paint, CharSequence text, int start, int end, FontMetricsInt fm) {
        if (fm != null) {
            fm.ascent = -Math.round(this.mHeight);
            fm.descent = 0;
            fm.top = fm.ascent;
            fm.bottom = 0;
        }
        return Math.round(this.mWidth);
    }

    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        if (this.mRequestHelper != null) {
            Bitmap bitmap = this.mRequestHelper.getBitmap();
            if (bitmap != null) {
                float bottomFloat = ((float) bottom) - ((float) paint.getFontMetricsInt().descent);
                TMP_RECT.set(x, bottomFloat - this.mHeight, this.mWidth + x, bottomFloat);
                canvas.drawBitmap(bitmap, null, TMP_RECT, paint);
            }
        }
    }
}

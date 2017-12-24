package com.facebook.react.views.text.frescosupport;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.TextView;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.react.views.text.TextInlineImageSpan;
import javax.annotation.Nullable;

public class FrescoBasedReactTextInlineImageSpan extends TextInlineImageSpan {
    @Nullable
    private final Object mCallerContext;
    @Nullable
    private Drawable mDrawable;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private final DraweeHolder<GenericDraweeHierarchy> mDraweeHolder;
    private int mHeight;
    @Nullable
    private TextView mTextView;
    private Uri mUri;
    private int mWidth;

    public FrescoBasedReactTextInlineImageSpan(Resources resources, int height, int width, @Nullable Uri uri, AbstractDraweeControllerBuilder draweeControllerBuilder, @Nullable Object callerContext) {
        this.mDraweeHolder = new DraweeHolder(GenericDraweeHierarchyBuilder.newInstance(resources).build());
        this.mDraweeControllerBuilder = draweeControllerBuilder;
        this.mCallerContext = callerContext;
        this.mHeight = height;
        this.mWidth = width;
        if (uri == null) {
            uri = Uri.EMPTY;
        }
        this.mUri = uri;
    }

    public void onDetachedFromWindow() {
        this.mDraweeHolder.onDetach();
    }

    public void onStartTemporaryDetach() {
        this.mDraweeHolder.onDetach();
    }

    public void onAttachedToWindow() {
        this.mDraweeHolder.onAttach();
    }

    public void onFinishTemporaryDetach() {
        this.mDraweeHolder.onAttach();
    }

    @Nullable
    public Drawable getDrawable() {
        return this.mDrawable;
    }

    public int getSize(Paint paint, CharSequence text, int start, int end, FontMetricsInt fm) {
        if (fm != null) {
            fm.ascent = -this.mHeight;
            fm.descent = 0;
            fm.top = fm.ascent;
            fm.bottom = 0;
        }
        return this.mWidth;
    }

    public void setTextView(TextView textView) {
        this.mTextView = textView;
    }

    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        if (this.mDrawable == null) {
            this.mDraweeHolder.setController(this.mDraweeControllerBuilder.reset().setOldController(this.mDraweeHolder.getController()).setCallerContext(this.mCallerContext).setImageRequest(ImageRequestBuilder.newBuilderWithSource(this.mUri).build()).build());
            this.mDrawable = this.mDraweeHolder.getTopLevelDrawable();
            this.mDrawable.setBounds(0, 0, this.mWidth, this.mHeight);
            this.mDrawable.setCallback(this.mTextView);
        }
        canvas.save();
        canvas.translate(x, (float) (y - this.mDrawable.getBounds().bottom));
        this.mDrawable.draw(canvas);
        canvas.restore();
    }

    public int getWidth() {
        return this.mWidth;
    }

    public int getHeight() {
        return this.mHeight;
    }
}

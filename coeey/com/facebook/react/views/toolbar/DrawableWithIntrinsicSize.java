package com.facebook.react.views.toolbar;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import com.facebook.drawee.drawable.ForwardingDrawable;
import com.facebook.imagepipeline.image.ImageInfo;

public class DrawableWithIntrinsicSize extends ForwardingDrawable implements Callback {
    private final ImageInfo mImageInfo;

    public DrawableWithIntrinsicSize(Drawable drawable, ImageInfo imageInfo) {
        super(drawable);
        this.mImageInfo = imageInfo;
    }

    public int getIntrinsicWidth() {
        return this.mImageInfo.getWidth();
    }

    public int getIntrinsicHeight() {
        return this.mImageInfo.getHeight();
    }
}

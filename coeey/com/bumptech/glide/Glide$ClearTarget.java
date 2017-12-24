package com.bumptech.glide;

import android.graphics.drawable.Drawable;
import android.view.View;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

class Glide$ClearTarget extends ViewTarget<View, Object> {
    public Glide$ClearTarget(View view) {
        super(view);
    }

    public void onLoadStarted(Drawable placeholder) {
    }

    public void onLoadFailed(Exception e, Drawable errorDrawable) {
    }

    public void onResourceReady(Object resource, GlideAnimation<? super Object> glideAnimation) {
    }

    public void onLoadCleared(Drawable placeholder) {
    }
}

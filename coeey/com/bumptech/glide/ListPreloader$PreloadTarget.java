package com.bumptech.glide;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.BaseTarget;
import com.bumptech.glide.request.target.SizeReadyCallback;

class ListPreloader$PreloadTarget extends BaseTarget<Object> {
    private int photoHeight;
    private int photoWidth;

    private ListPreloader$PreloadTarget() {
    }

    public void onResourceReady(Object resource, GlideAnimation<? super Object> glideAnimation) {
    }

    public void getSize(SizeReadyCallback cb) {
        cb.onSizeReady(this.photoWidth, this.photoHeight);
    }
}

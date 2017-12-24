package com.facebook.react.views.image;

import android.graphics.drawable.Animatable;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.react.uimanager.events.EventDispatcher;
import javax.annotation.Nullable;

class ReactImageView$1 extends BaseControllerListener<ImageInfo> {
    final /* synthetic */ ReactImageView this$0;
    final /* synthetic */ EventDispatcher val$mEventDispatcher;

    ReactImageView$1(ReactImageView this$0, EventDispatcher eventDispatcher) {
        this.this$0 = this$0;
        this.val$mEventDispatcher = eventDispatcher;
    }

    public void onSubmit(String id, Object callerContext) {
        this.val$mEventDispatcher.dispatchEvent(new ImageLoadEvent(this.this$0.getId(), 4));
    }

    public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
        if (imageInfo != null) {
            this.val$mEventDispatcher.dispatchEvent(new ImageLoadEvent(this.this$0.getId(), 2, ReactImageView.access$600(this.this$0).getSource(), imageInfo.getWidth(), imageInfo.getHeight()));
            this.val$mEventDispatcher.dispatchEvent(new ImageLoadEvent(this.this$0.getId(), 3));
        }
    }

    public void onFailure(String id, Throwable throwable) {
        this.val$mEventDispatcher.dispatchEvent(new ImageLoadEvent(this.this$0.getId(), 1));
        this.val$mEventDispatcher.dispatchEvent(new ImageLoadEvent(this.this$0.getId(), 3));
    }
}

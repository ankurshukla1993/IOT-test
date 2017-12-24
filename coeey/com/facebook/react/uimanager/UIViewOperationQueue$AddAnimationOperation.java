package com.facebook.react.uimanager;

import com.facebook.react.animation.Animation;
import com.facebook.react.bridge.Callback;

class UIViewOperationQueue$AddAnimationOperation extends UIViewOperationQueue$AnimationOperation {
    private final int mReactTag;
    private final Callback mSuccessCallback;
    final /* synthetic */ UIViewOperationQueue this$0;

    private UIViewOperationQueue$AddAnimationOperation(UIViewOperationQueue uIViewOperationQueue, int reactTag, int animationID, Callback successCallback) {
        this.this$0 = uIViewOperationQueue;
        super(animationID);
        this.mReactTag = reactTag;
        this.mSuccessCallback = successCallback;
    }

    public void execute() {
        Animation animation = UIViewOperationQueue.access$100(this.this$0).getAnimation(this.mAnimationID);
        if (animation != null) {
            UIViewOperationQueue.access$000(this.this$0).startAnimationForNativeView(this.mReactTag, animation, this.mSuccessCallback);
            return;
        }
        throw new IllegalViewOperationException("Animation with id " + this.mAnimationID + " was not found");
    }
}

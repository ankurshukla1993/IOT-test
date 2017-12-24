package com.facebook.react.uimanager;

import com.facebook.react.animation.Animation;

class UIViewOperationQueue$RegisterAnimationOperation extends UIViewOperationQueue$AnimationOperation {
    private final Animation mAnimation;
    final /* synthetic */ UIViewOperationQueue this$0;

    private UIViewOperationQueue$RegisterAnimationOperation(UIViewOperationQueue uIViewOperationQueue, Animation animation) {
        this.this$0 = uIViewOperationQueue;
        super(animation.getAnimationID());
        this.mAnimation = animation;
    }

    public void execute() {
        UIViewOperationQueue.access$100(this.this$0).registerAnimation(this.mAnimation);
    }
}

package com.facebook.react.uimanager;

import com.facebook.react.animation.Animation;

final class UIViewOperationQueue$RemoveAnimationOperation extends UIViewOperationQueue$AnimationOperation {
    final /* synthetic */ UIViewOperationQueue this$0;

    private UIViewOperationQueue$RemoveAnimationOperation(UIViewOperationQueue uIViewOperationQueue, int animationID) {
        this.this$0 = uIViewOperationQueue;
        super(animationID);
    }

    public void execute() {
        Animation animation = UIViewOperationQueue.access$100(this.this$0).getAnimation(this.mAnimationID);
        if (animation != null) {
            animation.cancel();
        }
    }
}

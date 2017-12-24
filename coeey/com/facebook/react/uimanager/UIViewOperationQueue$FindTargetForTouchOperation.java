package com.facebook.react.uimanager;

import com.facebook.react.bridge.Callback;

final class UIViewOperationQueue$FindTargetForTouchOperation implements UIViewOperationQueue$UIOperation {
    private final Callback mCallback;
    private final int mReactTag;
    private final float mTargetX;
    private final float mTargetY;
    final /* synthetic */ UIViewOperationQueue this$0;

    private UIViewOperationQueue$FindTargetForTouchOperation(UIViewOperationQueue uIViewOperationQueue, int reactTag, float targetX, float targetY, Callback callback) {
        this.this$0 = uIViewOperationQueue;
        this.mReactTag = reactTag;
        this.mTargetX = targetX;
        this.mTargetY = targetY;
        this.mCallback = callback;
    }

    public void execute() {
        try {
            UIViewOperationQueue.access$000(this.this$0).measure(this.mReactTag, UIViewOperationQueue.access$200(this.this$0));
            float containerX = (float) UIViewOperationQueue.access$200(this.this$0)[0];
            float containerY = (float) UIViewOperationQueue.access$200(this.this$0)[1];
            try {
                UIViewOperationQueue.access$000(this.this$0).measure(UIViewOperationQueue.access$000(this.this$0).findTargetTagForTouch(this.mReactTag, this.mTargetX, this.mTargetY), UIViewOperationQueue.access$200(this.this$0));
                float x = PixelUtil.toDIPFromPixel(((float) UIViewOperationQueue.access$200(this.this$0)[0]) - containerX);
                float y = PixelUtil.toDIPFromPixel(((float) UIViewOperationQueue.access$200(this.this$0)[1]) - containerY);
                float width = PixelUtil.toDIPFromPixel((float) UIViewOperationQueue.access$200(this.this$0)[2]);
                float height = PixelUtil.toDIPFromPixel((float) UIViewOperationQueue.access$200(this.this$0)[3]);
                this.mCallback.invoke(Integer.valueOf(touchTargetReactTag), Float.valueOf(x), Float.valueOf(y), Float.valueOf(width), Float.valueOf(height));
            } catch (IllegalViewOperationException e) {
                this.mCallback.invoke(new Object[0]);
            }
        } catch (IllegalViewOperationException e2) {
            this.mCallback.invoke(new Object[0]);
        }
    }
}

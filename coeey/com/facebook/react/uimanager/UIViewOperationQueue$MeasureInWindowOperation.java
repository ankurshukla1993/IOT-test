package com.facebook.react.uimanager;

import com.facebook.react.bridge.Callback;

final class UIViewOperationQueue$MeasureInWindowOperation implements UIViewOperationQueue$UIOperation {
    private final Callback mCallback;
    private final int mReactTag;
    final /* synthetic */ UIViewOperationQueue this$0;

    private UIViewOperationQueue$MeasureInWindowOperation(UIViewOperationQueue uIViewOperationQueue, int reactTag, Callback callback) {
        this.this$0 = uIViewOperationQueue;
        this.mReactTag = reactTag;
        this.mCallback = callback;
    }

    public void execute() {
        try {
            UIViewOperationQueue.access$000(this.this$0).measureInWindow(this.mReactTag, UIViewOperationQueue.access$200(this.this$0));
            float x = PixelUtil.toDIPFromPixel((float) UIViewOperationQueue.access$200(this.this$0)[0]);
            float y = PixelUtil.toDIPFromPixel((float) UIViewOperationQueue.access$200(this.this$0)[1]);
            float width = PixelUtil.toDIPFromPixel((float) UIViewOperationQueue.access$200(this.this$0)[2]);
            float height = PixelUtil.toDIPFromPixel((float) UIViewOperationQueue.access$200(this.this$0)[3]);
            this.mCallback.invoke(Float.valueOf(x), Float.valueOf(y), Float.valueOf(width), Float.valueOf(height));
        } catch (NoSuchNativeViewException e) {
            this.mCallback.invoke(new Object[0]);
        }
    }
}

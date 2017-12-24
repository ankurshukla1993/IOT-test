package com.facebook.react.uimanager;

import com.facebook.systrace.Systrace;

final class UIViewOperationQueue$UpdateLayoutOperation extends UIViewOperationQueue$ViewOperation {
    private final int mHeight;
    private final int mParentTag;
    private final int mWidth;
    private final int mX;
    private final int mY;
    final /* synthetic */ UIViewOperationQueue this$0;

    public UIViewOperationQueue$UpdateLayoutOperation(UIViewOperationQueue uIViewOperationQueue, int parentTag, int tag, int x, int y, int width, int height) {
        this.this$0 = uIViewOperationQueue;
        super(uIViewOperationQueue, tag);
        this.mParentTag = parentTag;
        this.mX = x;
        this.mY = y;
        this.mWidth = width;
        this.mHeight = height;
        Systrace.startAsyncFlow(0, "updateLayout", this.mTag);
    }

    public void execute() {
        Systrace.endAsyncFlow(0, "updateLayout", this.mTag);
        UIViewOperationQueue.access$000(this.this$0).updateLayout(this.mParentTag, this.mTag, this.mX, this.mY, this.mWidth, this.mHeight);
    }
}

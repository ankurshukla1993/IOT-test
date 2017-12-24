package com.facebook.react.uimanager;

final class UIViewOperationQueue$ChangeJSResponderOperation extends UIViewOperationQueue$ViewOperation {
    private final boolean mBlockNativeResponder;
    private final boolean mClearResponder;
    private final int mInitialTag;
    final /* synthetic */ UIViewOperationQueue this$0;

    public UIViewOperationQueue$ChangeJSResponderOperation(UIViewOperationQueue uIViewOperationQueue, int tag, int initialTag, boolean clearResponder, boolean blockNativeResponder) {
        this.this$0 = uIViewOperationQueue;
        super(uIViewOperationQueue, tag);
        this.mInitialTag = initialTag;
        this.mClearResponder = clearResponder;
        this.mBlockNativeResponder = blockNativeResponder;
    }

    public void execute() {
        if (this.mClearResponder) {
            UIViewOperationQueue.access$000(this.this$0).clearJSResponder();
        } else {
            UIViewOperationQueue.access$000(this.this$0).setJSResponder(this.mTag, this.mInitialTag, this.mBlockNativeResponder);
        }
    }
}

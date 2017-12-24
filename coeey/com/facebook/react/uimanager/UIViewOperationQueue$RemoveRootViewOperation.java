package com.facebook.react.uimanager;

final class UIViewOperationQueue$RemoveRootViewOperation extends UIViewOperationQueue$ViewOperation {
    final /* synthetic */ UIViewOperationQueue this$0;

    public UIViewOperationQueue$RemoveRootViewOperation(UIViewOperationQueue uIViewOperationQueue, int tag) {
        this.this$0 = uIViewOperationQueue;
        super(uIViewOperationQueue, tag);
    }

    public void execute() {
        UIViewOperationQueue.access$000(this.this$0).removeRootView(this.mTag);
    }
}

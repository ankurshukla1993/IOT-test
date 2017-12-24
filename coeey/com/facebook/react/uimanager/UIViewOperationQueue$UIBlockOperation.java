package com.facebook.react.uimanager;

class UIViewOperationQueue$UIBlockOperation implements UIViewOperationQueue$UIOperation {
    private final UIBlock mBlock;
    final /* synthetic */ UIViewOperationQueue this$0;

    public UIViewOperationQueue$UIBlockOperation(UIViewOperationQueue uIViewOperationQueue, UIBlock block) {
        this.this$0 = uIViewOperationQueue;
        this.mBlock = block;
    }

    public void execute() {
        this.mBlock.execute(UIViewOperationQueue.access$000(this.this$0));
    }
}

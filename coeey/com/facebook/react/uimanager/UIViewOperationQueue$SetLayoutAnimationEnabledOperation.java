package com.facebook.react.uimanager;

class UIViewOperationQueue$SetLayoutAnimationEnabledOperation implements UIViewOperationQueue$UIOperation {
    private final boolean mEnabled;
    final /* synthetic */ UIViewOperationQueue this$0;

    private UIViewOperationQueue$SetLayoutAnimationEnabledOperation(UIViewOperationQueue uIViewOperationQueue, boolean enabled) {
        this.this$0 = uIViewOperationQueue;
        this.mEnabled = enabled;
    }

    public void execute() {
        UIViewOperationQueue.access$000(this.this$0).setLayoutAnimationEnabled(this.mEnabled);
    }
}

package com.facebook.react.uimanager;

final class UIViewOperationQueue$SendAccessibilityEvent extends UIViewOperationQueue$ViewOperation {
    private final int mEventType;
    final /* synthetic */ UIViewOperationQueue this$0;

    private UIViewOperationQueue$SendAccessibilityEvent(UIViewOperationQueue uIViewOperationQueue, int tag, int eventType) {
        this.this$0 = uIViewOperationQueue;
        super(uIViewOperationQueue, tag);
        this.mEventType = eventType;
    }

    public void execute() {
        UIViewOperationQueue.access$000(this.this$0).sendAccessibilityEvent(this.mTag, this.mEventType);
    }
}

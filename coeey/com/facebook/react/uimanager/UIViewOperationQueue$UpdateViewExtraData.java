package com.facebook.react.uimanager;

final class UIViewOperationQueue$UpdateViewExtraData extends UIViewOperationQueue$ViewOperation {
    private final Object mExtraData;
    final /* synthetic */ UIViewOperationQueue this$0;

    public UIViewOperationQueue$UpdateViewExtraData(UIViewOperationQueue uIViewOperationQueue, int tag, Object extraData) {
        this.this$0 = uIViewOperationQueue;
        super(uIViewOperationQueue, tag);
        this.mExtraData = extraData;
    }

    public void execute() {
        UIViewOperationQueue.access$000(this.this$0).updateViewExtraData(this.mTag, this.mExtraData);
    }
}

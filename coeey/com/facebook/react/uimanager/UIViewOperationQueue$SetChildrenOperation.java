package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableArray;

final class UIViewOperationQueue$SetChildrenOperation extends UIViewOperationQueue$ViewOperation {
    private final ReadableArray mChildrenTags;
    final /* synthetic */ UIViewOperationQueue this$0;

    public UIViewOperationQueue$SetChildrenOperation(UIViewOperationQueue uIViewOperationQueue, int tag, ReadableArray childrenTags) {
        this.this$0 = uIViewOperationQueue;
        super(uIViewOperationQueue, tag);
        this.mChildrenTags = childrenTags;
    }

    public void execute() {
        UIViewOperationQueue.access$000(this.this$0).setChildren(this.mTag, this.mChildrenTags);
    }
}

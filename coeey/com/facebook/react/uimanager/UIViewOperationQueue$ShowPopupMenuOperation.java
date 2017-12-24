package com.facebook.react.uimanager;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableArray;

final class UIViewOperationQueue$ShowPopupMenuOperation extends UIViewOperationQueue$ViewOperation {
    private final ReadableArray mItems;
    private final Callback mSuccess;
    final /* synthetic */ UIViewOperationQueue this$0;

    public UIViewOperationQueue$ShowPopupMenuOperation(UIViewOperationQueue uIViewOperationQueue, int tag, ReadableArray items, Callback success) {
        this.this$0 = uIViewOperationQueue;
        super(uIViewOperationQueue, tag);
        this.mItems = items;
        this.mSuccess = success;
    }

    public void execute() {
        UIViewOperationQueue.access$000(this.this$0).showPopupMenu(this.mTag, this.mItems, this.mSuccess);
    }
}

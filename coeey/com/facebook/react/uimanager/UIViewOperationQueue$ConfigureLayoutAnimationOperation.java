package com.facebook.react.uimanager;

import com.facebook.react.bridge.ReadableMap;

class UIViewOperationQueue$ConfigureLayoutAnimationOperation implements UIViewOperationQueue$UIOperation {
    private final ReadableMap mConfig;
    final /* synthetic */ UIViewOperationQueue this$0;

    private UIViewOperationQueue$ConfigureLayoutAnimationOperation(UIViewOperationQueue uIViewOperationQueue, ReadableMap config) {
        this.this$0 = uIViewOperationQueue;
        this.mConfig = config;
    }

    public void execute() {
        UIViewOperationQueue.access$000(this.this$0).configureLayoutAnimation(this.mConfig);
    }
}

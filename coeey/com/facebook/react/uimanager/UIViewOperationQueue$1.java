package com.facebook.react.uimanager;

import java.util.concurrent.Semaphore;

class UIViewOperationQueue$1 implements Runnable {
    final /* synthetic */ UIViewOperationQueue this$0;
    final /* synthetic */ SizeMonitoringFrameLayout val$rootView;
    final /* synthetic */ Semaphore val$semaphore;
    final /* synthetic */ int val$tag;
    final /* synthetic */ ThemedReactContext val$themedRootContext;

    UIViewOperationQueue$1(UIViewOperationQueue this$0, int i, SizeMonitoringFrameLayout sizeMonitoringFrameLayout, ThemedReactContext themedReactContext, Semaphore semaphore) {
        this.this$0 = this$0;
        this.val$tag = i;
        this.val$rootView = sizeMonitoringFrameLayout;
        this.val$themedRootContext = themedReactContext;
        this.val$semaphore = semaphore;
    }

    public void run() {
        UIViewOperationQueue.access$000(this.this$0).addRootView(this.val$tag, this.val$rootView, this.val$themedRootContext);
        this.val$semaphore.release();
    }
}

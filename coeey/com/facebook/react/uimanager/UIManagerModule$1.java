package com.facebook.react.uimanager;

class UIManagerModule$1 implements SizeMonitoringFrameLayout$OnSizeChangedListener {
    final /* synthetic */ UIManagerModule this$0;
    final /* synthetic */ int val$tag;

    UIManagerModule$1(UIManagerModule this$0, int i) {
        this.this$0 = this$0;
        this.val$tag = i;
    }

    public void onSizeChanged(final int width, final int height, int oldW, int oldH) {
        UIManagerModule.access$100(this.this$0).runOnNativeModulesQueueThread(new Runnable() {
            public void run() {
                UIManagerModule$1.this.this$0.updateNodeSize(UIManagerModule$1.this.val$tag, width, height);
            }
        });
    }
}

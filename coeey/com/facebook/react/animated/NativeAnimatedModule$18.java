package com.facebook.react.animated;

class NativeAnimatedModule$18 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ String val$eventName;
    final /* synthetic */ int val$viewTag;

    NativeAnimatedModule$18(NativeAnimatedModule this$0, int i, String str) {
        this.this$0 = this$0;
        this.val$viewTag = i;
        this.val$eventName = str;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.removeAnimatedEventFromView(this.val$viewTag, this.val$eventName);
    }
}

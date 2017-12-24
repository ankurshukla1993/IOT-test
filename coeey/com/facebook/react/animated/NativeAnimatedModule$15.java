package com.facebook.react.animated;

class NativeAnimatedModule$15 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$animatedNodeTag;
    final /* synthetic */ int val$viewTag;

    NativeAnimatedModule$15(NativeAnimatedModule this$0, int i, int i2) {
        this.this$0 = this$0;
        this.val$animatedNodeTag = i;
        this.val$viewTag = i2;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.connectAnimatedNodeToView(this.val$animatedNodeTag, this.val$viewTag);
    }
}

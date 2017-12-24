package com.facebook.react.animated;

class NativeAnimatedModule$13 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$childNodeTag;
    final /* synthetic */ int val$parentNodeTag;

    NativeAnimatedModule$13(NativeAnimatedModule this$0, int i, int i2) {
        this.this$0 = this$0;
        this.val$parentNodeTag = i;
        this.val$childNodeTag = i2;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.connectAnimatedNodes(this.val$parentNodeTag, this.val$childNodeTag);
    }
}

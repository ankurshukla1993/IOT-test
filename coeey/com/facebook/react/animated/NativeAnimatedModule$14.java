package com.facebook.react.animated;

class NativeAnimatedModule$14 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$childNodeTag;
    final /* synthetic */ int val$parentNodeTag;

    NativeAnimatedModule$14(NativeAnimatedModule this$0, int i, int i2) {
        this.this$0 = this$0;
        this.val$parentNodeTag = i;
        this.val$childNodeTag = i2;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.disconnectAnimatedNodes(this.val$parentNodeTag, this.val$childNodeTag);
    }
}

package com.facebook.react.animated;

class NativeAnimatedModule$5 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$tag;

    NativeAnimatedModule$5(NativeAnimatedModule this$0, int i) {
        this.this$0 = this$0;
        this.val$tag = i;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.stopListeningToAnimatedNodeValue(this.val$tag);
    }
}

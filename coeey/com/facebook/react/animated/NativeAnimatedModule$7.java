package com.facebook.react.animated;

class NativeAnimatedModule$7 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$tag;
    final /* synthetic */ double val$value;

    NativeAnimatedModule$7(NativeAnimatedModule this$0, int i, double d) {
        this.this$0 = this$0;
        this.val$tag = i;
        this.val$value = d;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.setAnimatedNodeValue(this.val$tag, this.val$value);
    }
}

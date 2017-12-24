package com.facebook.react.animated;

class NativeAnimatedModule$4 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ AnimatedNodeValueListener val$listener;
    final /* synthetic */ int val$tag;

    NativeAnimatedModule$4(NativeAnimatedModule this$0, int i, AnimatedNodeValueListener animatedNodeValueListener) {
        this.this$0 = this$0;
        this.val$tag = i;
        this.val$listener = animatedNodeValueListener;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.startListeningToAnimatedNodeValue(this.val$tag, this.val$listener);
    }
}

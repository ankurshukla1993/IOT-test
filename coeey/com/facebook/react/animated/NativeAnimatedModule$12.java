package com.facebook.react.animated;

class NativeAnimatedModule$12 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$animationId;

    NativeAnimatedModule$12(NativeAnimatedModule this$0, int i) {
        this.this$0 = this$0;
        this.val$animationId = i;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.stopAnimation(this.val$animationId);
    }
}

package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

class NativeAnimatedModule$2 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ ReadableMap val$config;
    final /* synthetic */ int val$tag;

    NativeAnimatedModule$2(NativeAnimatedModule this$0, int i, ReadableMap readableMap) {
        this.this$0 = this$0;
        this.val$tag = i;
        this.val$config = readableMap;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.createAnimatedNode(this.val$tag, this.val$config);
    }
}

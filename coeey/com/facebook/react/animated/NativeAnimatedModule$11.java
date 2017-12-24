package com.facebook.react.animated;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;

class NativeAnimatedModule$11 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$animatedNodeTag;
    final /* synthetic */ ReadableMap val$animationConfig;
    final /* synthetic */ int val$animationId;
    final /* synthetic */ Callback val$endCallback;

    NativeAnimatedModule$11(NativeAnimatedModule this$0, int i, int i2, ReadableMap readableMap, Callback callback) {
        this.this$0 = this$0;
        this.val$animationId = i;
        this.val$animatedNodeTag = i2;
        this.val$animationConfig = readableMap;
        this.val$endCallback = callback;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.startAnimatingNode(this.val$animationId, this.val$animatedNodeTag, this.val$animationConfig, this.val$endCallback);
    }
}

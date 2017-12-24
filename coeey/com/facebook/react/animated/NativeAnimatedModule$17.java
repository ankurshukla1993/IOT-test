package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;

class NativeAnimatedModule$17 implements NativeAnimatedModule$UIThreadOperation {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ ReadableMap val$eventMapping;
    final /* synthetic */ String val$eventName;
    final /* synthetic */ int val$viewTag;

    NativeAnimatedModule$17(NativeAnimatedModule this$0, int i, String str, ReadableMap readableMap) {
        this.this$0 = this$0;
        this.val$viewTag = i;
        this.val$eventName = str;
        this.val$eventMapping = readableMap;
    }

    public void execute(NativeAnimatedNodesManager animatedNodesManager) {
        animatedNodesManager.addAnimatedEventToView(this.val$viewTag, this.val$eventName, this.val$eventMapping);
    }
}

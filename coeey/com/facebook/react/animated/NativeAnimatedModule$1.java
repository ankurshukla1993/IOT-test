package com.facebook.react.animated;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.GuardedChoreographerFrameCallback;
import com.facebook.react.uimanager.ReactChoreographer;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;
import java.util.ArrayList;

class NativeAnimatedModule$1 extends GuardedChoreographerFrameCallback {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ NativeAnimatedNodesManager val$nodesManager;

    NativeAnimatedModule$1(NativeAnimatedModule this$0, ReactContext reactContext, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.this$0 = this$0;
        this.val$nodesManager = nativeAnimatedNodesManager;
        super(reactContext);
    }

    protected void doFrameGuarded(long frameTimeNanos) {
        synchronized (NativeAnimatedModule.access$000(this.this$0)) {
            ArrayList<NativeAnimatedModule$UIThreadOperation> operations = NativeAnimatedModule.access$100(this.this$0);
            NativeAnimatedModule.access$102(this.this$0, null);
        }
        if (operations != null) {
            int size = operations.size();
            for (int i = 0; i < size; i++) {
                ((NativeAnimatedModule$UIThreadOperation) operations.get(i)).execute(this.val$nodesManager);
            }
        }
        if (this.val$nodesManager.hasActiveAnimations()) {
            this.val$nodesManager.runUpdates(frameTimeNanos);
        }
        ((ReactChoreographer) Assertions.assertNotNull(NativeAnimatedModule.access$300(this.this$0))).postFrameCallback(CallbackType.NATIVE_ANIMATED_MODULE, NativeAnimatedModule.access$200(this.this$0));
    }
}

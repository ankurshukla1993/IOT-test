package com.facebook.react.modules.core;

import android.view.Choreographer.FrameCallback;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.uimanager.ReactChoreographer;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;

class Timing$IdleFrameCallback implements FrameCallback {
    final /* synthetic */ Timing this$0;

    private Timing$IdleFrameCallback(Timing timing) {
        this.this$0 = timing;
    }

    public void doFrame(long frameTimeNanos) {
        if (!Timing.access$000(this.this$0).get() || Timing.access$100(this.this$0).get()) {
            if (Timing.access$1200(this.this$0) != null) {
                Timing.access$1200(this.this$0).cancel();
            }
            Timing.access$1202(this.this$0, new Timing$IdleCallbackRunnable(this.this$0, frameTimeNanos));
            Timing.access$1300(this.this$0).runOnJSQueueThread(Timing.access$1200(this.this$0));
            ((ReactChoreographer) Assertions.assertNotNull(Timing.access$1100(this.this$0))).postFrameCallback(CallbackType.IDLE_EVENT, this);
        }
    }
}

package com.facebook.react.modules.core;

import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.common.SystemClock;
import com.google.android.flexbox.FlexItem;

class Timing$IdleCallbackRunnable implements Runnable {
    private volatile boolean mCancelled = false;
    private final long mFrameStartTime;
    final /* synthetic */ Timing this$0;

    public Timing$IdleCallbackRunnable(Timing timing, long frameStartTime) {
        this.this$0 = timing;
        this.mFrameStartTime = frameStartTime;
    }

    public void run() {
        if (!this.mCancelled) {
            long frameTimeElapsed = SystemClock.uptimeMillis() - (this.mFrameStartTime / 1000000);
            long absoluteFrameStartTime = SystemClock.currentTimeMillis() - frameTimeElapsed;
            if (16.666666f - ((float) frameTimeElapsed) >= FlexItem.FLEX_SHRINK_DEFAULT) {
                Timing.access$1400(this.this$0).clear();
                synchronized (Timing.access$1500(this.this$0)) {
                    Timing.access$1400(this.this$0).addAll(Timing.access$1600(this.this$0));
                }
                for (ExecutorToken context : Timing.access$1400(this.this$0)) {
                    ((JSTimersExecution) Timing.access$1700(this.this$0).getJSModule(context, JSTimersExecution.class)).callIdleCallbacks((double) absoluteFrameStartTime);
                }
                Timing.access$1202(this.this$0, null);
            }
        }
    }

    public void cancel() {
        this.mCancelled = true;
    }
}

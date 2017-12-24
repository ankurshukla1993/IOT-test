package com.facebook.react.modules.core;

import android.util.SparseArray;
import android.view.Choreographer.FrameCallback;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.uimanager.ReactChoreographer;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;
import java.util.HashMap;
import java.util.Map.Entry;

class Timing$TimerFrameCallback implements FrameCallback {
    private final HashMap<ExecutorToken, WritableArray> mTimersToCall;
    final /* synthetic */ Timing this$0;

    private Timing$TimerFrameCallback(Timing timing) {
        this.this$0 = timing;
        this.mTimersToCall = new HashMap();
    }

    public void doFrame(long frameTimeNanos) {
        if (!Timing.access$000(this.this$0).get() || Timing.access$100(this.this$0).get()) {
            long frameTimeMillis = frameTimeNanos / 1000000;
            synchronized (Timing.access$200(this.this$0)) {
                while (!Timing.access$300(this.this$0).isEmpty() && ((Timing$Timer) Timing.access$300(this.this$0).peek()).mTargetTime < frameTimeMillis) {
                    Timing$Timer timer = (Timing$Timer) Timing.access$300(this.this$0).poll();
                    WritableArray timersForContext = (WritableArray) this.mTimersToCall.get(timer.mExecutorToken);
                    if (timersForContext == null) {
                        timersForContext = Arguments.createArray();
                        this.mTimersToCall.put(timer.mExecutorToken, timersForContext);
                    }
                    timersForContext.pushInt(timer.mCallbackID);
                    if (timer.mRepeat) {
                        timer.mTargetTime = ((long) timer.mInterval) + frameTimeMillis;
                        Timing.access$300(this.this$0).add(timer);
                    } else {
                        SparseArray<Timing$Timer> timers = (SparseArray) Timing.access$900(this.this$0).get(timer.mExecutorToken);
                        if (timers != null) {
                            timers.remove(timer.mCallbackID);
                            if (timers.size() == 0) {
                                Timing.access$900(this.this$0).remove(timer.mExecutorToken);
                            }
                        }
                    }
                }
            }
            for (Entry<ExecutorToken, WritableArray> entry : this.mTimersToCall.entrySet()) {
                ((JSTimersExecution) Timing.access$1000(this.this$0).getJSModule((ExecutorToken) entry.getKey(), JSTimersExecution.class)).callTimers((WritableArray) entry.getValue());
            }
            this.mTimersToCall.clear();
            ((ReactChoreographer) Assertions.assertNotNull(Timing.access$1100(this.this$0))).postFrameCallback(CallbackType.TIMERS_EVENTS, this);
        }
    }
}

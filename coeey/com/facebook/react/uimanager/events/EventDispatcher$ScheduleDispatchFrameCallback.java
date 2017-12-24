package com.facebook.react.uimanager.events;

import android.view.Choreographer.FrameCallback;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.uimanager.ReactChoreographer;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;
import com.facebook.systrace.Systrace;

class EventDispatcher$ScheduleDispatchFrameCallback implements FrameCallback {
    private volatile boolean mIsPosted;
    private boolean mShouldStop;
    final /* synthetic */ EventDispatcher this$0;

    class C13451 implements Runnable {
        C13451() {
        }

        public void run() {
            EventDispatcher$ScheduleDispatchFrameCallback.this.maybePost();
        }
    }

    private EventDispatcher$ScheduleDispatchFrameCallback(EventDispatcher eventDispatcher) {
        this.this$0 = eventDispatcher;
        this.mIsPosted = false;
        this.mShouldStop = false;
    }

    public void doFrame(long frameTimeNanos) {
        UiThreadUtil.assertOnUiThread();
        if (this.mShouldStop) {
            this.mIsPosted = false;
        } else {
            post();
        }
        Systrace.beginSection(0, "ScheduleDispatchFrameCallback");
        try {
            EventDispatcher.access$200(this.this$0);
            if (EventDispatcher.access$300(this.this$0) > 0 && !EventDispatcher.access$400(this.this$0)) {
                EventDispatcher.access$402(this.this$0, true);
                Systrace.startAsyncFlow(0, "ScheduleDispatchFrameCallback", EventDispatcher.access$500(this.this$0));
                EventDispatcher.access$700(this.this$0).runOnJSQueueThread(EventDispatcher.access$600(this.this$0));
            }
            Systrace.endSection(0);
        } catch (Throwable th) {
            Systrace.endSection(0);
        }
    }

    public void stop() {
        this.mShouldStop = true;
    }

    public void maybePost() {
        if (!this.mIsPosted) {
            this.mIsPosted = true;
            post();
        }
    }

    private void post() {
        ReactChoreographer.getInstance().postFrameCallback(CallbackType.TIMERS_EVENTS, EventDispatcher.access$800(this.this$0));
    }

    public void maybePostFromNonUI() {
        if (!this.mIsPosted) {
            if (EventDispatcher.access$700(this.this$0).isOnUiQueueThread()) {
                maybePost();
            } else {
                EventDispatcher.access$700(this.this$0).runOnUiQueueThread(new C13451());
            }
        }
    }
}

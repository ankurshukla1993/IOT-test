package com.facebook.react.uimanager;

import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import java.util.ArrayDeque;

public class ReactChoreographer {
    private static ReactChoreographer sInstance;
    private final ArrayDeque<FrameCallback>[] mCallbackQueues = new ArrayDeque[CallbackType.values().length];
    private final Choreographer mChoreographer = Choreographer.getInstance();
    private boolean mHasPostedCallback = false;
    private final ReactChoreographerDispatcher mReactChoreographerDispatcher = new ReactChoreographerDispatcher();
    private int mTotalCallbacks = 0;

    public enum CallbackType {
        PERF_MARKERS(0),
        DISPATCH_UI(1),
        NATIVE_ANIMATED_MODULE(2),
        TIMERS_EVENTS(3),
        IDLE_EVENT(4);
        
        private final int mOrder;

        private CallbackType(int order) {
            this.mOrder = order;
        }

        int getOrder() {
            return this.mOrder;
        }
    }

    private class ReactChoreographerDispatcher implements FrameCallback {
        private ReactChoreographerDispatcher() {
        }

        public void doFrame(long frameTimeNanos) {
            ReactChoreographer.this.mHasPostedCallback = false;
            for (int i = 0; i < ReactChoreographer.this.mCallbackQueues.length; i++) {
                int initialLength = ReactChoreographer.this.mCallbackQueues[i].size();
                for (int callback = 0; callback < initialLength; callback++) {
                    ((FrameCallback) ReactChoreographer.this.mCallbackQueues[i].removeFirst()).doFrame(frameTimeNanos);
                    ReactChoreographer.this.mTotalCallbacks = ReactChoreographer.this.mTotalCallbacks - 1;
                }
            }
            ReactChoreographer.this.maybeRemoveFrameCallback();
        }
    }

    public static ReactChoreographer getInstance() {
        UiThreadUtil.assertOnUiThread();
        if (sInstance == null) {
            sInstance = new ReactChoreographer();
        }
        return sInstance;
    }

    private ReactChoreographer() {
        for (int i = 0; i < this.mCallbackQueues.length; i++) {
            this.mCallbackQueues[i] = new ArrayDeque();
        }
    }

    public void postFrameCallback(CallbackType type, FrameCallback frameCallback) {
        UiThreadUtil.assertOnUiThread();
        this.mCallbackQueues[type.getOrder()].addLast(frameCallback);
        this.mTotalCallbacks++;
        Assertions.assertCondition(this.mTotalCallbacks > 0);
        if (!this.mHasPostedCallback) {
            this.mChoreographer.postFrameCallback(this.mReactChoreographerDispatcher);
            this.mHasPostedCallback = true;
        }
    }

    public void removeFrameCallback(CallbackType type, FrameCallback frameCallback) {
        UiThreadUtil.assertOnUiThread();
        if (this.mCallbackQueues[type.getOrder()].removeFirstOccurrence(frameCallback)) {
            this.mTotalCallbacks--;
            maybeRemoveFrameCallback();
            return;
        }
        FLog.m111e(ReactConstants.TAG, "Tried to remove non-existent frame callback");
    }

    private void maybeRemoveFrameCallback() {
        Assertions.assertCondition(this.mTotalCallbacks >= 0);
        if (this.mTotalCallbacks == 0 && this.mHasPostedCallback) {
            this.mChoreographer.removeFrameCallback(this.mReactChoreographerDispatcher);
            this.mHasPostedCallback = false;
        }
    }
}

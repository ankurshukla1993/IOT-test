package com.facebook.react.modules.core;

import android.util.SparseArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.OnExecutorUnregisteredListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.common.SystemClock;
import com.facebook.react.devsupport.DevSupportManager;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.ReactChoreographer;
import com.facebook.react.uimanager.ReactChoreographer.CallbackType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

@ReactModule(name = "Timing", supportsWebWorkers = true)
public final class Timing extends ReactContextBaseJavaModule implements LifecycleEventListener, OnExecutorUnregisteredListener, HeadlessJsTaskEventListener {
    private static final float FRAME_DURATION_MS = 16.666666f;
    private static final float IDLE_CALLBACK_FRAME_DEADLINE_MS = 1.0f;
    protected static final String NAME = "Timing";
    private final AtomicBoolean isPaused = new AtomicBoolean(true);
    private final AtomicBoolean isRunningTasks = new AtomicBoolean(false);
    @Nullable
    private IdleCallbackRunnable mCurrentIdleCallbackRunnable;
    private final DevSupportManager mDevSupportManager;
    private boolean mFrameCallbackPosted = false;
    private boolean mFrameIdleCallbackPosted = false;
    private final List<ExecutorToken> mIdleCallbackContextsToCall;
    private final Object mIdleCallbackGuard = new Object();
    private final IdleFrameCallback mIdleFrameCallback = new IdleFrameCallback(this, null);
    @Nullable
    private ReactChoreographer mReactChoreographer;
    private final Set<ExecutorToken> mSendIdleEventsExecutorTokens;
    private final TimerFrameCallback mTimerFrameCallback = new TimerFrameCallback(this, null);
    private final Object mTimerGuard = new Object();
    private final Map<ExecutorToken, SparseArray<Timer>> mTimerIdsToTimers;
    private final PriorityQueue<Timer> mTimers;

    public Timing(ReactApplicationContext reactContext, DevSupportManager devSupportManager) {
        super(reactContext);
        this.mDevSupportManager = devSupportManager;
        this.mTimers = new PriorityQueue(11, new 1(this));
        this.mTimerIdsToTimers = new HashMap();
        this.mSendIdleEventsExecutorTokens = new HashSet();
        this.mIdleCallbackContextsToCall = new ArrayList();
    }

    public void initialize() {
        this.mReactChoreographer = ReactChoreographer.getInstance();
        getReactApplicationContext().addLifecycleEventListener(this);
        HeadlessJsTaskContext.getInstance(getReactApplicationContext()).addTaskEventListener(this);
    }

    public void onHostPause() {
        this.isPaused.set(true);
        clearChoreographerCallback();
        maybeClearChoreographerIdleCallback();
    }

    public void onHostDestroy() {
        clearChoreographerCallback();
        maybeClearChoreographerIdleCallback();
    }

    public void onHostResume() {
        this.isPaused.set(false);
        setChoreographerCallback();
        maybeSetChoreographerIdleCallback();
    }

    public void onHeadlessJsTaskStart(int taskId) {
        if (!this.isRunningTasks.getAndSet(true)) {
            setChoreographerCallback();
            maybeSetChoreographerIdleCallback();
        }
    }

    public void onHeadlessJsTaskFinish(int taskId) {
        if (!HeadlessJsTaskContext.getInstance(getReactApplicationContext()).hasActiveTasks()) {
            this.isRunningTasks.set(false);
            clearChoreographerCallback();
            maybeClearChoreographerIdleCallback();
        }
    }

    public void onCatalystInstanceDestroy() {
        clearChoreographerCallback();
        clearChoreographerIdleCallback();
        HeadlessJsTaskContext.getInstance(getReactApplicationContext()).removeTaskEventListener(this);
    }

    private void maybeSetChoreographerIdleCallback() {
        synchronized (this.mIdleCallbackGuard) {
            if (this.mSendIdleEventsExecutorTokens.size() > 0) {
                setChoreographerIdleCallback();
            }
        }
    }

    private void maybeClearChoreographerIdleCallback() {
        if (this.isPaused.get() && !this.isRunningTasks.get()) {
            clearChoreographerCallback();
        }
    }

    private void setChoreographerCallback() {
        if (!this.mFrameCallbackPosted) {
            ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).postFrameCallback(CallbackType.TIMERS_EVENTS, this.mTimerFrameCallback);
            this.mFrameCallbackPosted = true;
        }
    }

    private void clearChoreographerCallback() {
        HeadlessJsTaskContext headlessJsTaskContext = HeadlessJsTaskContext.getInstance(getReactApplicationContext());
        if (this.mFrameCallbackPosted && this.isPaused.get() && !headlessJsTaskContext.hasActiveTasks()) {
            ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).removeFrameCallback(CallbackType.TIMERS_EVENTS, this.mTimerFrameCallback);
            this.mFrameCallbackPosted = false;
        }
    }

    private void setChoreographerIdleCallback() {
        if (!this.mFrameIdleCallbackPosted) {
            ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).postFrameCallback(CallbackType.IDLE_EVENT, this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = true;
        }
    }

    private void clearChoreographerIdleCallback() {
        if (this.mFrameIdleCallbackPosted) {
            ((ReactChoreographer) Assertions.assertNotNull(this.mReactChoreographer)).removeFrameCallback(CallbackType.IDLE_EVENT, this.mIdleFrameCallback);
            this.mFrameIdleCallbackPosted = false;
        }
    }

    public String getName() {
        return NAME;
    }

    public boolean supportsWebWorkers() {
        return true;
    }

    public void onExecutorDestroyed(ExecutorToken executorToken) {
        synchronized (this.mTimerGuard) {
            SparseArray<Timer> timersForContext = (SparseArray) this.mTimerIdsToTimers.remove(executorToken);
            if (timersForContext == null) {
                return;
            }
            for (int i = 0; i < timersForContext.size(); i++) {
                this.mTimers.remove((Timer) timersForContext.get(timersForContext.keyAt(i)));
            }
            synchronized (this.mIdleCallbackGuard) {
                this.mSendIdleEventsExecutorTokens.remove(executorToken);
            }
        }
    }

    @ReactMethod
    public void createTimer(ExecutorToken executorToken, int callbackID, int duration, double jsSchedulingTime, boolean repeat) {
        long deviceTime = SystemClock.currentTimeMillis();
        long remoteTime = (long) jsSchedulingTime;
        if (this.mDevSupportManager.getDevSupportEnabled() && Math.abs(remoteTime - deviceTime) > 60000) {
            ((JSTimersExecution) getReactApplicationContext().getJSModule(executorToken, JSTimersExecution.class)).emitTimeDriftWarning("Debugger and device times have drifted by more than 60s. Please correct this by running adb shell \"date `date +%m%d%H%M%Y.%S`\" on your debugger machine.");
        }
        long adjustedDuration = Math.max(0, (remoteTime - deviceTime) + ((long) duration));
        if (duration != 0 || repeat) {
            Timer timer = new Timer(executorToken, callbackID, (SystemClock.nanoTime() / 1000000) + adjustedDuration, duration, repeat, null);
            synchronized (this.mTimerGuard) {
                this.mTimers.add(timer);
                SparseArray<Timer> timersForContext = (SparseArray) this.mTimerIdsToTimers.get(executorToken);
                if (timersForContext == null) {
                    timersForContext = new SparseArray();
                    this.mTimerIdsToTimers.put(executorToken, timersForContext);
                }
                timersForContext.put(callbackID, timer);
            }
            return;
        }
        WritableArray timerToCall = Arguments.createArray();
        timerToCall.pushInt(callbackID);
        ((JSTimersExecution) getReactApplicationContext().getJSModule(executorToken, JSTimersExecution.class)).callTimers(timerToCall);
    }

    @ReactMethod
    public void deleteTimer(ExecutorToken executorToken, int timerId) {
        synchronized (this.mTimerGuard) {
            SparseArray<Timer> timersForContext = (SparseArray) this.mTimerIdsToTimers.get(executorToken);
            if (timersForContext == null) {
                return;
            }
            Timer timer = (Timer) timersForContext.get(timerId);
            if (timer == null) {
                return;
            }
            timersForContext.remove(timerId);
            if (timersForContext.size() == 0) {
                this.mTimerIdsToTimers.remove(executorToken);
            }
            this.mTimers.remove(timer);
        }
    }

    @ReactMethod
    public void setSendIdleEvents(ExecutorToken executorToken, boolean sendIdleEvents) {
        synchronized (this.mIdleCallbackGuard) {
            if (sendIdleEvents) {
                this.mSendIdleEventsExecutorTokens.add(executorToken);
            } else {
                this.mSendIdleEventsExecutorTokens.remove(executorToken);
            }
        }
        UiThreadUtil.runOnUiThread(new 2(this));
    }
}

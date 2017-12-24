package com.facebook.react.jstasks;

import android.os.Handler;
import android.util.SparseArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.uimanager.AppRegistry;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

public class HeadlessJsTaskContext {
    private static final WeakHashMap<ReactContext, HeadlessJsTaskContext> INSTANCES = new WeakHashMap();
    private final Set<Integer> mActiveTasks = new CopyOnWriteArraySet();
    private final Handler mHandler = new Handler();
    private final Set<HeadlessJsTaskEventListener> mHeadlessJsTaskEventListeners = new CopyOnWriteArraySet();
    private final AtomicInteger mLastTaskId = new AtomicInteger(0);
    private final WeakReference<ReactContext> mReactContext;
    private final SparseArray<Runnable> mTaskTimeouts = new SparseArray();

    public static HeadlessJsTaskContext getInstance(ReactContext context) {
        HeadlessJsTaskContext helper = (HeadlessJsTaskContext) INSTANCES.get(context);
        if (helper != null) {
            return helper;
        }
        helper = new HeadlessJsTaskContext(context);
        INSTANCES.put(context, helper);
        return helper;
    }

    private HeadlessJsTaskContext(ReactContext reactContext) {
        this.mReactContext = new WeakReference(reactContext);
    }

    public void addTaskEventListener(HeadlessJsTaskEventListener listener) {
        this.mHeadlessJsTaskEventListeners.add(listener);
    }

    public void removeTaskEventListener(HeadlessJsTaskEventListener listener) {
        this.mHeadlessJsTaskEventListeners.remove(listener);
    }

    public boolean hasActiveTasks() {
        return this.mActiveTasks.size() > 0;
    }

    public synchronized int startTask(HeadlessJsTaskConfig taskConfig) {
        int taskId;
        UiThreadUtil.assertOnUiThread();
        ReactContext reactContext = (ReactContext) Assertions.assertNotNull(this.mReactContext.get(), "Tried to start a task on a react context that has already been destroyed");
        if (reactContext.getLifecycleState() != LifecycleState.RESUMED || taskConfig.isAllowedInForeground()) {
            taskId = this.mLastTaskId.incrementAndGet();
            this.mActiveTasks.add(Integer.valueOf(taskId));
            ((AppRegistry) reactContext.getJSModule(AppRegistry.class)).startHeadlessTask(taskId, taskConfig.getTaskKey(), taskConfig.getData());
            if (taskConfig.getTimeout() > 0) {
                scheduleTaskTimeout(taskId, taskConfig.getTimeout());
            }
            for (HeadlessJsTaskEventListener listener : this.mHeadlessJsTaskEventListeners) {
                listener.onHeadlessJsTaskStart(taskId);
            }
        } else {
            throw new IllegalStateException("Tried to start task " + taskConfig.getTaskKey() + " while in foreground, but this is not allowed.");
        }
        return taskId;
    }

    public synchronized void finishTask(final int taskId) {
        Assertions.assertCondition(this.mActiveTasks.remove(Integer.valueOf(taskId)), "Tried to finish non-existent task with id " + taskId + ".");
        Runnable timeout = (Runnable) this.mTaskTimeouts.get(taskId);
        if (timeout != null) {
            this.mHandler.removeCallbacks(timeout);
            this.mTaskTimeouts.remove(taskId);
        }
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                for (HeadlessJsTaskEventListener listener : HeadlessJsTaskContext.this.mHeadlessJsTaskEventListeners) {
                    listener.onHeadlessJsTaskFinish(taskId);
                }
            }
        });
    }

    public synchronized boolean isTaskRunning(int taskId) {
        return this.mActiveTasks.contains(Integer.valueOf(taskId));
    }

    private void scheduleTaskTimeout(final int taskId, long timeout) {
        Runnable runnable = new Runnable() {
            public void run() {
                HeadlessJsTaskContext.this.finishTask(taskId);
            }
        };
        this.mTaskTimeouts.append(taskId, runnable);
        this.mHandler.postDelayed(runnable, timeout);
    }
}

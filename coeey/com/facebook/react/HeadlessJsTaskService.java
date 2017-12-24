package com.facebook.react;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.jstasks.HeadlessJsTaskConfig;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.jstasks.HeadlessJsTaskEventListener;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;

public abstract class HeadlessJsTaskService extends Service implements HeadlessJsTaskEventListener {
    @Nullable
    private static WakeLock sWakeLock;
    private final Set<Integer> mActiveTasks = new CopyOnWriteArraySet();

    public int onStartCommand(Intent intent, int flags, int startId) {
        HeadlessJsTaskConfig taskConfig = getTaskConfig(intent);
        if (taskConfig == null) {
            return 2;
        }
        startTask(taskConfig);
        return 3;
    }

    @Nullable
    protected HeadlessJsTaskConfig getTaskConfig(Intent intent) {
        return null;
    }

    public static void acquireWakeLockNow(Context context) {
        if (sWakeLock == null || !sWakeLock.isHeld()) {
            sWakeLock = ((PowerManager) Assertions.assertNotNull((PowerManager) context.getSystemService("power"))).newWakeLock(1, HeadlessJsTaskService.class.getSimpleName());
            sWakeLock.setReferenceCounted(false);
            sWakeLock.acquire();
        }
    }

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    protected void startTask(HeadlessJsTaskConfig taskConfig) {
        UiThreadUtil.assertOnUiThread();
        acquireWakeLockNow(this);
        ReactInstanceManager reactInstanceManager = getReactNativeHost().getReactInstanceManager();
        ReactContext reactContext = reactInstanceManager.getCurrentReactContext();
        if (reactContext == null) {
            reactInstanceManager.addReactInstanceEventListener(new 1(this, taskConfig, reactInstanceManager));
            if (!reactInstanceManager.hasStartedCreatingInitialContext()) {
                reactInstanceManager.createReactContextInBackground();
                return;
            }
            return;
        }
        invokeStartTask(reactContext, taskConfig);
    }

    private void invokeStartTask(ReactContext reactContext, HeadlessJsTaskConfig taskConfig) {
        HeadlessJsTaskContext headlessJsTaskContext = HeadlessJsTaskContext.getInstance(reactContext);
        headlessJsTaskContext.addTaskEventListener(this);
        this.mActiveTasks.add(Integer.valueOf(headlessJsTaskContext.startTask(taskConfig)));
    }

    public void onDestroy() {
        super.onDestroy();
        if (getReactNativeHost().hasInstance()) {
            ReactContext reactContext = getReactNativeHost().getReactInstanceManager().getCurrentReactContext();
            if (reactContext != null) {
                HeadlessJsTaskContext.getInstance(reactContext).removeTaskEventListener(this);
            }
        }
        if (sWakeLock != null) {
            sWakeLock.release();
        }
    }

    public void onHeadlessJsTaskStart(int taskId) {
    }

    public void onHeadlessJsTaskFinish(int taskId) {
        this.mActiveTasks.remove(Integer.valueOf(taskId));
        if (this.mActiveTasks.size() == 0) {
            stopSelf();
        }
    }

    protected ReactNativeHost getReactNativeHost() {
        return ((ReactApplication) getApplication()).getReactNativeHost();
    }
}

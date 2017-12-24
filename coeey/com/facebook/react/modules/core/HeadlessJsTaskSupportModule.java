package com.facebook.react.modules.core;

import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.jstasks.HeadlessJsTaskContext;
import com.facebook.react.module.annotations.ReactModule;

@ReactModule(name = "HeadlessJsTaskSupport")
public class HeadlessJsTaskSupportModule extends ReactContextBaseJavaModule {
    protected static final String MODULE_NAME = "HeadlessJsTaskSupport";

    public HeadlessJsTaskSupportModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void notifyTaskFinished(int taskId) {
        HeadlessJsTaskContext headlessJsTaskContext = HeadlessJsTaskContext.getInstance(getReactApplicationContext());
        if (headlessJsTaskContext.isTaskRunning(taskId)) {
            headlessJsTaskContext.finishTask(taskId);
            return;
        }
        FLog.w(HeadlessJsTaskSupportModule.class, "Tried to finish non-active task with id %d. Did it time out?", new Object[]{Integer.valueOf(taskId)});
    }
}

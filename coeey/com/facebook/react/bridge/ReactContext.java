package com.facebook.react.bridge;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.queue.MessageQueueThread;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.LifecycleState;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.annotation.Nullable;

public class ReactContext extends ContextWrapper {
    private static final String EARLY_JS_ACCESS_EXCEPTION_MESSAGE = "Tried to access a JS module before the React instance was fully set up. Calls to ReactContext#getJSModule should only happen once initialize() has been called on your native module.";
    private final CopyOnWriteArraySet<ActivityEventListener> mActivityEventListeners = new CopyOnWriteArraySet();
    @Nullable
    private CatalystInstance mCatalystInstance;
    @Nullable
    private WeakReference<Activity> mCurrentActivity;
    @Nullable
    private LayoutInflater mInflater;
    @Nullable
    private MessageQueueThread mJSMessageQueueThread;
    private final CopyOnWriteArraySet<LifecycleEventListener> mLifecycleEventListeners = new CopyOnWriteArraySet();
    private LifecycleState mLifecycleState = LifecycleState.BEFORE_CREATE;
    @Nullable
    private NativeModuleCallExceptionHandler mNativeModuleCallExceptionHandler;
    @Nullable
    private MessageQueueThread mNativeModulesMessageQueueThread;
    @Nullable
    private MessageQueueThread mUiMessageQueueThread;

    public ReactContext(Context base) {
        super(base);
    }

    public void initializeWithInstance(CatalystInstance catalystInstance) {
        if (catalystInstance == null) {
            throw new IllegalArgumentException("CatalystInstance cannot be null.");
        } else if (this.mCatalystInstance != null) {
            throw new IllegalStateException("ReactContext has been already initialized");
        } else {
            this.mCatalystInstance = catalystInstance;
            ReactQueueConfiguration queueConfig = catalystInstance.getReactQueueConfiguration();
            this.mUiMessageQueueThread = queueConfig.getUIQueueThread();
            this.mNativeModulesMessageQueueThread = queueConfig.getNativeModulesQueueThread();
            this.mJSMessageQueueThread = queueConfig.getJSQueueThread();
        }
    }

    public void setNativeModuleCallExceptionHandler(@Nullable NativeModuleCallExceptionHandler nativeModuleCallExceptionHandler) {
        this.mNativeModuleCallExceptionHandler = nativeModuleCallExceptionHandler;
    }

    public Object getSystemService(String name) {
        if (!"layout_inflater".equals(name)) {
            return getBaseContext().getSystemService(name);
        }
        if (this.mInflater == null) {
            this.mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.mInflater;
    }

    public <T extends JavaScriptModule> T getJSModule(Class<T> jsInterface) {
        if (this.mCatalystInstance != null) {
            return this.mCatalystInstance.getJSModule(jsInterface);
        }
        throw new RuntimeException(EARLY_JS_ACCESS_EXCEPTION_MESSAGE);
    }

    public <T extends JavaScriptModule> T getJSModule(ExecutorToken executorToken, Class<T> jsInterface) {
        if (this.mCatalystInstance != null) {
            return this.mCatalystInstance.getJSModule(executorToken, jsInterface);
        }
        throw new RuntimeException(EARLY_JS_ACCESS_EXCEPTION_MESSAGE);
    }

    public <T extends NativeModule> boolean hasNativeModule(Class<T> nativeModuleInterface) {
        if (this.mCatalystInstance != null) {
            return this.mCatalystInstance.hasNativeModule(nativeModuleInterface);
        }
        throw new RuntimeException("Trying to call native module before CatalystInstance has been set!");
    }

    public <T extends NativeModule> T getNativeModule(Class<T> nativeModuleInterface) {
        if (this.mCatalystInstance != null) {
            return this.mCatalystInstance.getNativeModule(nativeModuleInterface);
        }
        throw new RuntimeException("Trying to call native module before CatalystInstance has been set!");
    }

    public CatalystInstance getCatalystInstance() {
        return (CatalystInstance) Assertions.assertNotNull(this.mCatalystInstance);
    }

    public boolean hasActiveCatalystInstance() {
        return (this.mCatalystInstance == null || this.mCatalystInstance.isDestroyed()) ? false : true;
    }

    public LifecycleState getLifecycleState() {
        return this.mLifecycleState;
    }

    public void addLifecycleEventListener(LifecycleEventListener listener) {
        this.mLifecycleEventListeners.add(listener);
        if (hasActiveCatalystInstance()) {
            switch (2.$SwitchMap$com$facebook$react$common$LifecycleState[this.mLifecycleState.ordinal()]) {
                case 1:
                case 2:
                    return;
                case 3:
                    runOnUiQueueThread(new 1(this, listener));
                    return;
                default:
                    throw new RuntimeException("Unhandled lifecycle state.");
            }
        }
    }

    public void removeLifecycleEventListener(LifecycleEventListener listener) {
        this.mLifecycleEventListeners.remove(listener);
    }

    public Map<String, Map<String, Double>> getAllPerformanceCounters() {
        Map<String, Map<String, Double>> totalPerfMap = new HashMap();
        if (this.mCatalystInstance != null) {
            for (NativeModule nativeModule : this.mCatalystInstance.getNativeModules()) {
                if (nativeModule instanceof PerformanceCounter) {
                    totalPerfMap.put(nativeModule.getName(), ((PerformanceCounter) nativeModule).getPerformanceCounters());
                }
            }
        }
        return totalPerfMap;
    }

    public void addActivityEventListener(ActivityEventListener listener) {
        this.mActivityEventListeners.add(listener);
    }

    public void removeActivityEventListener(ActivityEventListener listener) {
        this.mActivityEventListeners.remove(listener);
    }

    public void onHostResume(@Nullable Activity activity) {
        UiThreadUtil.assertOnUiThread();
        this.mLifecycleState = LifecycleState.RESUMED;
        this.mCurrentActivity = new WeakReference(activity);
        this.mLifecycleState = LifecycleState.RESUMED;
        Iterator it = this.mLifecycleEventListeners.iterator();
        while (it.hasNext()) {
            ((LifecycleEventListener) it.next()).onHostResume();
        }
    }

    public void onNewIntent(@Nullable Activity activity, Intent intent) {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentActivity = new WeakReference(activity);
        Iterator it = this.mActivityEventListeners.iterator();
        while (it.hasNext()) {
            ((ActivityEventListener) it.next()).onNewIntent(intent);
        }
    }

    public void onHostPause() {
        UiThreadUtil.assertOnUiThread();
        this.mLifecycleState = LifecycleState.BEFORE_RESUME;
        Iterator it = this.mLifecycleEventListeners.iterator();
        while (it.hasNext()) {
            ((LifecycleEventListener) it.next()).onHostPause();
        }
    }

    public void onHostDestroy() {
        UiThreadUtil.assertOnUiThread();
        this.mLifecycleState = LifecycleState.BEFORE_CREATE;
        Iterator it = this.mLifecycleEventListeners.iterator();
        while (it.hasNext()) {
            ((LifecycleEventListener) it.next()).onHostDestroy();
        }
        this.mCurrentActivity = null;
    }

    public void destroy() {
        UiThreadUtil.assertOnUiThread();
        if (this.mCatalystInstance != null) {
            this.mCatalystInstance.destroy();
        }
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        Iterator it = this.mActivityEventListeners.iterator();
        while (it.hasNext()) {
            ((ActivityEventListener) it.next()).onActivityResult(activity, requestCode, resultCode, data);
        }
    }

    public void assertOnUiQueueThread() {
        ((MessageQueueThread) Assertions.assertNotNull(this.mUiMessageQueueThread)).assertIsOnThread();
    }

    public boolean isOnUiQueueThread() {
        return ((MessageQueueThread) Assertions.assertNotNull(this.mUiMessageQueueThread)).isOnThread();
    }

    public void runOnUiQueueThread(Runnable runnable) {
        ((MessageQueueThread) Assertions.assertNotNull(this.mUiMessageQueueThread)).runOnQueue(runnable);
    }

    public void assertOnNativeModulesQueueThread() {
        ((MessageQueueThread) Assertions.assertNotNull(this.mNativeModulesMessageQueueThread)).assertIsOnThread();
    }

    public boolean isOnNativeModulesQueueThread() {
        return ((MessageQueueThread) Assertions.assertNotNull(this.mNativeModulesMessageQueueThread)).isOnThread();
    }

    public void runOnNativeModulesQueueThread(Runnable runnable) {
        ((MessageQueueThread) Assertions.assertNotNull(this.mNativeModulesMessageQueueThread)).runOnQueue(runnable);
    }

    public void assertOnJSQueueThread() {
        ((MessageQueueThread) Assertions.assertNotNull(this.mJSMessageQueueThread)).assertIsOnThread();
    }

    public boolean isOnJSQueueThread() {
        return ((MessageQueueThread) Assertions.assertNotNull(this.mJSMessageQueueThread)).isOnThread();
    }

    public void runOnJSQueueThread(Runnable runnable) {
        ((MessageQueueThread) Assertions.assertNotNull(this.mJSMessageQueueThread)).runOnQueue(runnable);
    }

    public void handleException(RuntimeException e) {
        if (this.mCatalystInstance == null || this.mCatalystInstance.isDestroyed() || this.mNativeModuleCallExceptionHandler == null) {
            throw e;
        }
        this.mNativeModuleCallExceptionHandler.handleException(e);
    }

    public boolean hasCurrentActivity() {
        return (this.mCurrentActivity == null || this.mCurrentActivity.get() == null) ? false : true;
    }

    public boolean startActivityForResult(Intent intent, int code, Bundle bundle) {
        Activity activity = getCurrentActivity();
        Assertions.assertNotNull(activity);
        activity.startActivityForResult(intent, code, bundle);
        return true;
    }

    @Nullable
    public Activity getCurrentActivity() {
        if (this.mCurrentActivity == null) {
            return null;
        }
        return (Activity) this.mCurrentActivity.get();
    }

    public long getJavaScriptContext() {
        return this.mCatalystInstance.getJavaScriptContext();
    }
}

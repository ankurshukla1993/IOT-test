package com.facebook.react.bridge;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.common.annotations.VisibleForTesting;
import java.util.Collection;
import javax.annotation.Nullable;

@DoNotStrip
public interface CatalystInstance extends MemoryPressureListener {
    void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener);

    @DoNotStrip
    void callFunction(ExecutorToken executorToken, String str, String str2, NativeArray nativeArray);

    void destroy();

    <T extends JavaScriptModule> T getJSModule(ExecutorToken executorToken, Class<T> cls);

    <T extends JavaScriptModule> T getJSModule(Class<T> cls);

    long getJavaScriptContext();

    <T extends NativeModule> T getNativeModule(Class<T> cls);

    Collection<NativeModule> getNativeModules();

    ReactQueueConfiguration getReactQueueConfiguration();

    @Nullable
    String getSourceURL();

    <T extends NativeModule> boolean hasNativeModule(Class<T> cls);

    @VisibleForTesting
    void initialize();

    @DoNotStrip
    void invokeCallback(ExecutorToken executorToken, int i, NativeArray nativeArray);

    boolean isDestroyed();

    void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener);

    void runJSBundle();

    @VisibleForTesting
    void setGlobalVariable(String str, String str2);

    void startProfiler(String str);

    void stopProfiler(String str, String str2);

    boolean supportsProfiling();
}

package com.facebook.react.bridge.queue;

import android.os.Looper;
import com.facebook.react.common.MapBuilder;
import java.util.Map;

public class ReactQueueConfigurationImpl implements ReactQueueConfiguration {
    private final MessageQueueThreadImpl mJSQueueThread;
    private final MessageQueueThreadImpl mNativeModulesQueueThread;
    private final MessageQueueThreadImpl mUIQueueThread;

    private ReactQueueConfigurationImpl(MessageQueueThreadImpl uiQueueThread, MessageQueueThreadImpl nativeModulesQueueThread, MessageQueueThreadImpl jsQueueThread) {
        this.mUIQueueThread = uiQueueThread;
        this.mNativeModulesQueueThread = nativeModulesQueueThread;
        this.mJSQueueThread = jsQueueThread;
    }

    public MessageQueueThread getUIQueueThread() {
        return this.mUIQueueThread;
    }

    public MessageQueueThread getNativeModulesQueueThread() {
        return this.mNativeModulesQueueThread;
    }

    public MessageQueueThread getJSQueueThread() {
        return this.mJSQueueThread;
    }

    public void destroy() {
        if (this.mNativeModulesQueueThread.getLooper() != Looper.getMainLooper()) {
            this.mNativeModulesQueueThread.quitSynchronous();
        }
        if (this.mJSQueueThread.getLooper() != Looper.getMainLooper()) {
            this.mJSQueueThread.quitSynchronous();
        }
    }

    public static ReactQueueConfigurationImpl create(ReactQueueConfigurationSpec spec, QueueThreadExceptionHandler exceptionHandler) {
        Map<MessageQueueThreadSpec, MessageQueueThreadImpl> specsToThreads = MapBuilder.newHashMap();
        MessageQueueThreadSpec uiThreadSpec = MessageQueueThreadSpec.mainThreadSpec();
        MessageQueueThreadImpl uiThread = MessageQueueThreadImpl.create(uiThreadSpec, exceptionHandler);
        specsToThreads.put(uiThreadSpec, uiThread);
        MessageQueueThreadImpl jsThread = (MessageQueueThreadImpl) specsToThreads.get(spec.getJSQueueThreadSpec());
        if (jsThread == null) {
            jsThread = MessageQueueThreadImpl.create(spec.getJSQueueThreadSpec(), exceptionHandler);
        }
        MessageQueueThreadImpl nativeModulesThread = (MessageQueueThreadImpl) specsToThreads.get(spec.getNativeModulesQueueThreadSpec());
        if (nativeModulesThread == null) {
            nativeModulesThread = MessageQueueThreadImpl.create(spec.getNativeModulesQueueThreadSpec(), exceptionHandler);
        }
        return new ReactQueueConfigurationImpl(uiThread, nativeModulesThread, jsThread);
    }
}

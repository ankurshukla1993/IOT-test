package com.facebook.react.bridge.queue;

public interface ReactQueueConfiguration {
    MessageQueueThread getJSQueueThread();

    MessageQueueThread getNativeModulesQueueThread();

    MessageQueueThread getUIQueueThread();
}

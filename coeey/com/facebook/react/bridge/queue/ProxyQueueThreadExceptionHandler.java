package com.facebook.react.bridge.queue;

public class ProxyQueueThreadExceptionHandler implements QueueThreadExceptionHandler {
    private final MessageQueueThread mDelegateThread;

    public ProxyQueueThreadExceptionHandler(MessageQueueThread delegateThread) {
        this.mDelegateThread = delegateThread;
    }

    public void handleException(final Exception e) {
        this.mDelegateThread.runOnQueue(new Runnable() {
            public void run() {
                throw new RuntimeException(e);
            }
        });
    }
}

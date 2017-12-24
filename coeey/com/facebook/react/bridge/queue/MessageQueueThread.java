package com.facebook.react.bridge.queue;

import com.facebook.proguard.annotations.DoNotStrip;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@DoNotStrip
public interface MessageQueueThread {
    @DoNotStrip
    void assertIsOnThread();

    @DoNotStrip
    <T> Future<T> callOnQueue(Callable<T> callable);

    @DoNotStrip
    boolean isOnThread();

    @DoNotStrip
    void quitSynchronous();

    @DoNotStrip
    void runOnQueue(Runnable runnable);
}

package com.facebook.react.bridge.queue;

import android.os.Looper;
import android.os.Process;
import com.facebook.common.logging.FLog;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.futures.SimpleSettableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@DoNotStrip
public class MessageQueueThreadImpl implements MessageQueueThread {
    private final String mAssertionErrorMessage;
    private final MessageQueueThreadHandler mHandler;
    private volatile boolean mIsFinished = false;
    private final Looper mLooper;
    private final String mName;

    private MessageQueueThreadImpl(String name, Looper looper, QueueThreadExceptionHandler exceptionHandler) {
        this.mName = name;
        this.mLooper = looper;
        this.mHandler = new MessageQueueThreadHandler(looper, exceptionHandler);
        this.mAssertionErrorMessage = "Expected to be called from the '" + getName() + "' thread!";
    }

    @DoNotStrip
    public void runOnQueue(Runnable runnable) {
        if (this.mIsFinished) {
            FLog.m151w(ReactConstants.TAG, "Tried to enqueue runnable on already finished thread: '" + getName() + "... dropping Runnable.");
        }
        this.mHandler.post(runnable);
    }

    @DoNotStrip
    public <T> Future<T> callOnQueue(final Callable<T> callable) {
        final SimpleSettableFuture<T> future = new SimpleSettableFuture();
        runOnQueue(new Runnable() {
            public void run() {
                try {
                    future.set(callable.call());
                } catch (Exception e) {
                    future.setException(e);
                }
            }
        });
        return future;
    }

    @DoNotStrip
    public boolean isOnThread() {
        return this.mLooper.getThread() == Thread.currentThread();
    }

    @DoNotStrip
    public void assertIsOnThread() {
        SoftAssertions.assertCondition(isOnThread(), this.mAssertionErrorMessage);
    }

    @DoNotStrip
    public void quitSynchronous() {
        this.mIsFinished = true;
        this.mLooper.quit();
        if (this.mLooper.getThread() != Thread.currentThread()) {
            try {
                this.mLooper.getThread().join();
            } catch (InterruptedException e) {
                throw new RuntimeException("Got interrupted waiting to join thread " + this.mName);
            }
        }
    }

    public Looper getLooper() {
        return this.mLooper;
    }

    public String getName() {
        return this.mName;
    }

    public static MessageQueueThreadImpl create(MessageQueueThreadSpec spec, QueueThreadExceptionHandler exceptionHandler) {
        switch (spec.getThreadType()) {
            case MAIN_UI:
                return createForMainThread(spec.getName(), exceptionHandler);
            case NEW_BACKGROUND:
                return startNewBackgroundThread(spec.getName(), spec.getStackSize(), exceptionHandler);
            default:
                throw new RuntimeException("Unknown thread type: " + spec.getThreadType());
        }
    }

    private static MessageQueueThreadImpl createForMainThread(String name, QueueThreadExceptionHandler exceptionHandler) {
        final MessageQueueThreadImpl mqt = new MessageQueueThreadImpl(name, Looper.getMainLooper(), exceptionHandler);
        if (UiThreadUtil.isOnUiThread()) {
            Process.setThreadPriority(-4);
            MessageQueueThreadRegistry.register(mqt);
        } else {
            UiThreadUtil.runOnUiThread(new Runnable() {
                public void run() {
                    Process.setThreadPriority(-4);
                    MessageQueueThreadRegistry.register(mqt);
                }
            });
        }
        return mqt;
    }

    public static MessageQueueThreadImpl startNewBackgroundThread(String name, QueueThreadExceptionHandler exceptionHandler) {
        return startNewBackgroundThread(name, 0, exceptionHandler);
    }

    public static MessageQueueThreadImpl startNewBackgroundThread(String name, long stackSize, QueueThreadExceptionHandler exceptionHandler) {
        final SimpleSettableFuture<Looper> looperFuture = new SimpleSettableFuture();
        final SimpleSettableFuture<MessageQueueThread> mqtFuture = new SimpleSettableFuture();
        new Thread(null, new Runnable() {
            public void run() {
                Looper.prepare();
                looperFuture.set(Looper.myLooper());
                MessageQueueThreadRegistry.register((MessageQueueThread) mqtFuture.getOrThrow());
                Looper.loop();
            }
        }, "mqt_" + name, stackSize).start();
        MessageQueueThreadImpl mqt = new MessageQueueThreadImpl(name, (Looper) looperFuture.getOrThrow(), exceptionHandler);
        mqtFuture.set(mqt);
        return mqt;
    }
}

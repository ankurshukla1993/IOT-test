package com.facebook.imagepipeline.producers;

import com.facebook.common.logging.FLog;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class BaseConsumer<T> implements Consumer<T> {
    private boolean mIsFinished = false;

    protected abstract void onCancellationImpl();

    protected abstract void onFailureImpl(Throwable th);

    protected abstract void onNewResultImpl(T t, boolean z);

    public synchronized void onNewResult(@Nullable T newResult, boolean isLast) {
        if (!this.mIsFinished) {
            this.mIsFinished = isLast;
            try {
                onNewResultImpl(newResult, isLast);
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    public synchronized void onFailure(Throwable t) {
        if (!this.mIsFinished) {
            this.mIsFinished = true;
            try {
                onFailureImpl(t);
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    public synchronized void onCancellation() {
        if (!this.mIsFinished) {
            this.mIsFinished = true;
            try {
                onCancellationImpl();
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    public synchronized void onProgressUpdate(float progress) {
        if (!this.mIsFinished) {
            try {
                onProgressUpdateImpl(progress);
            } catch (Exception e) {
                onUnhandledException(e);
            }
        }
    }

    protected void onProgressUpdateImpl(float progress) {
    }

    protected void onUnhandledException(Exception e) {
        FLog.wtf(getClass(), "unhandled exception", (Throwable) e);
    }
}

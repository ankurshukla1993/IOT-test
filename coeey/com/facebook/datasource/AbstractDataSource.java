package com.facebook.datasource;

import android.util.Pair;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

public abstract class AbstractDataSource<T> implements DataSource<T> {
    @GuardedBy("this")
    private DataSourceStatus mDataSourceStatus = DataSourceStatus.IN_PROGRESS;
    @GuardedBy("this")
    private Throwable mFailureThrowable = null;
    @GuardedBy("this")
    private boolean mIsClosed = false;
    @GuardedBy("this")
    private float mProgress = 0.0f;
    @GuardedBy("this")
    @Nullable
    private T mResult = null;
    private final ConcurrentLinkedQueue<Pair<DataSubscriber<T>, Executor>> mSubscribers = new ConcurrentLinkedQueue();

    protected AbstractDataSource() {
    }

    public synchronized boolean isClosed() {
        return this.mIsClosed;
    }

    public synchronized boolean isFinished() {
        return this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS;
    }

    public synchronized boolean hasResult() {
        return this.mResult != null;
    }

    @Nullable
    public synchronized T getResult() {
        return this.mResult;
    }

    public synchronized boolean hasFailed() {
        return this.mDataSourceStatus == DataSourceStatus.FAILURE;
    }

    @Nullable
    public synchronized Throwable getFailureCause() {
        return this.mFailureThrowable;
    }

    public synchronized float getProgress() {
        return this.mProgress;
    }

    public boolean close() {
        boolean z = true;
        synchronized (this) {
            if (this.mIsClosed) {
                z = false;
            } else {
                this.mIsClosed = true;
                T resultToClose = this.mResult;
                this.mResult = null;
                if (resultToClose != null) {
                    closeResult(resultToClose);
                }
                if (!isFinished()) {
                    notifyDataSubscribers();
                }
                synchronized (this) {
                    this.mSubscribers.clear();
                }
            }
        }
        return z;
    }

    protected void closeResult(@Nullable T t) {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void subscribe(com.facebook.datasource.DataSubscriber<T> r4, java.util.concurrent.Executor r5) {
        /*
        r3 = this;
        com.facebook.common.internal.Preconditions.checkNotNull(r4);
        com.facebook.common.internal.Preconditions.checkNotNull(r5);
        monitor-enter(r3);
        r1 = r3.mIsClosed;	 Catch:{ all -> 0x0040 }
        if (r1 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r3);	 Catch:{ all -> 0x0040 }
    L_0x000c:
        return;
    L_0x000d:
        r1 = r3.mDataSourceStatus;	 Catch:{ all -> 0x0040 }
        r2 = com.facebook.datasource.AbstractDataSource.DataSourceStatus.IN_PROGRESS;	 Catch:{ all -> 0x0040 }
        if (r1 != r2) goto L_0x001c;
    L_0x0013:
        r1 = r3.mSubscribers;	 Catch:{ all -> 0x0040 }
        r2 = android.util.Pair.create(r4, r5);	 Catch:{ all -> 0x0040 }
        r1.add(r2);	 Catch:{ all -> 0x0040 }
    L_0x001c:
        r1 = r3.hasResult();	 Catch:{ all -> 0x0040 }
        if (r1 != 0) goto L_0x002e;
    L_0x0022:
        r1 = r3.isFinished();	 Catch:{ all -> 0x0040 }
        if (r1 != 0) goto L_0x002e;
    L_0x0028:
        r1 = r3.wasCancelled();	 Catch:{ all -> 0x0040 }
        if (r1 == 0) goto L_0x003e;
    L_0x002e:
        r0 = 1;
    L_0x002f:
        monitor-exit(r3);	 Catch:{ all -> 0x0040 }
        if (r0 == 0) goto L_0x000c;
    L_0x0032:
        r1 = r3.hasFailed();
        r2 = r3.wasCancelled();
        r3.notifyDataSubscriber(r4, r5, r1, r2);
        goto L_0x000c;
    L_0x003e:
        r0 = 0;
        goto L_0x002f;
    L_0x0040:
        r1 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0040 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.datasource.AbstractDataSource.subscribe(com.facebook.datasource.DataSubscriber, java.util.concurrent.Executor):void");
    }

    private void notifyDataSubscribers() {
        boolean isFailure = hasFailed();
        boolean isCancellation = wasCancelled();
        Iterator it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair<DataSubscriber<T>, Executor> pair = (Pair) it.next();
            notifyDataSubscriber((DataSubscriber) pair.first, (Executor) pair.second, isFailure, isCancellation);
        }
    }

    private void notifyDataSubscriber(DataSubscriber<T> dataSubscriber, Executor executor, boolean isFailure, boolean isCancellation) {
        executor.execute(new 1(this, isFailure, dataSubscriber, isCancellation));
    }

    private synchronized boolean wasCancelled() {
        boolean z;
        z = isClosed() && !isFinished();
        return z;
    }

    protected boolean setResult(@Nullable T value, boolean isLast) {
        boolean result = setResultInternal(value, isLast);
        if (result) {
            notifyDataSubscribers();
        }
        return result;
    }

    protected boolean setFailure(Throwable throwable) {
        boolean result = setFailureInternal(throwable);
        if (result) {
            notifyDataSubscribers();
        }
        return result;
    }

    protected boolean setProgress(float progress) {
        boolean result = setProgressInternal(progress);
        if (result) {
            notifyProgressUpdate();
        }
        return result;
    }

    private boolean setResultInternal(@Nullable T value, boolean isLast) {
        Object obj = null;
        try {
            boolean z;
            synchronized (this) {
                if (this.mIsClosed || this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS) {
                    obj = value;
                    z = false;
                } else {
                    if (isLast) {
                        this.mDataSourceStatus = DataSourceStatus.SUCCESS;
                        this.mProgress = FlexItem.FLEX_SHRINK_DEFAULT;
                    }
                    if (this.mResult != value) {
                        obj = this.mResult;
                        this.mResult = value;
                    }
                    z = true;
                    if (obj != null) {
                        closeResult(obj);
                    }
                }
            }
            return z;
        } finally {
            if (obj != null) {
                closeResult(obj);
            }
        }
    }

    private synchronized boolean setFailureInternal(Throwable throwable) {
        boolean z;
        if (this.mIsClosed || this.mDataSourceStatus != DataSourceStatus.IN_PROGRESS) {
            z = false;
        } else {
            this.mDataSourceStatus = DataSourceStatus.FAILURE;
            this.mFailureThrowable = throwable;
            z = true;
        }
        return z;
    }

    private synchronized boolean setProgressInternal(float progress) {
        boolean z = false;
        synchronized (this) {
            if (!this.mIsClosed && this.mDataSourceStatus == DataSourceStatus.IN_PROGRESS) {
                if (progress >= this.mProgress) {
                    this.mProgress = progress;
                    z = true;
                }
            }
        }
        return z;
    }

    protected void notifyProgressUpdate() {
        Iterator it = this.mSubscribers.iterator();
        while (it.hasNext()) {
            Pair<DataSubscriber<T>, Executor> pair = (Pair) it.next();
            pair.second.execute(new 2(this, pair.first));
        }
    }
}

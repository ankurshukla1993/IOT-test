package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.image.EncodedImage;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;

public class JobScheduler {
    static final String QUEUE_TIME_KEY = "queueTime";
    private final Runnable mDoJobRunnable = new C11841();
    @GuardedBy("this")
    @VisibleForTesting
    EncodedImage mEncodedImage = null;
    private final Executor mExecutor;
    @GuardedBy("this")
    @VisibleForTesting
    boolean mIsLast = false;
    private final JobRunnable mJobRunnable;
    @GuardedBy("this")
    @VisibleForTesting
    long mJobStartTime = 0;
    @GuardedBy("this")
    @VisibleForTesting
    JobState mJobState = JobState.IDLE;
    @GuardedBy("this")
    @VisibleForTesting
    long mJobSubmitTime = 0;
    private final int mMinimumJobIntervalMs;
    private final Runnable mSubmitJobRunnable = new C11852();

    public interface JobRunnable {
        void run(EncodedImage encodedImage, boolean z);
    }

    class C11841 implements Runnable {
        C11841() {
        }

        public void run() {
            JobScheduler.this.doJob();
        }
    }

    class C11852 implements Runnable {
        C11852() {
        }

        public void run() {
            JobScheduler.this.submitJob();
        }
    }

    @VisibleForTesting
    static class JobStartExecutorSupplier {
        private static ScheduledExecutorService sJobStarterExecutor;

        JobStartExecutorSupplier() {
        }

        static ScheduledExecutorService get() {
            if (sJobStarterExecutor == null) {
                sJobStarterExecutor = Executors.newSingleThreadScheduledExecutor();
            }
            return sJobStarterExecutor;
        }
    }

    @VisibleForTesting
    enum JobState {
        IDLE,
        QUEUED,
        RUNNING,
        RUNNING_AND_PENDING
    }

    public JobScheduler(Executor executor, JobRunnable jobRunnable, int minimumJobIntervalMs) {
        this.mExecutor = executor;
        this.mJobRunnable = jobRunnable;
        this.mMinimumJobIntervalMs = minimumJobIntervalMs;
    }

    public void clearJob() {
        EncodedImage oldEncodedImage;
        synchronized (this) {
            oldEncodedImage = this.mEncodedImage;
            this.mEncodedImage = null;
            this.mIsLast = false;
        }
        EncodedImage.closeSafely(oldEncodedImage);
    }

    public boolean updateJob(EncodedImage encodedImage, boolean isLast) {
        if (!shouldProcess(encodedImage, isLast)) {
            return false;
        }
        EncodedImage oldEncodedImage;
        synchronized (this) {
            oldEncodedImage = this.mEncodedImage;
            this.mEncodedImage = EncodedImage.cloneOrNull(encodedImage);
            this.mIsLast = isLast;
        }
        EncodedImage.closeSafely(oldEncodedImage);
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scheduleJob() {
        /*
        r10 = this;
        r0 = android.os.SystemClock.uptimeMillis();
        r4 = 0;
        r2 = 0;
        monitor-enter(r10);
        r3 = r10.mEncodedImage;	 Catch:{ all -> 0x003e }
        r6 = r10.mIsLast;	 Catch:{ all -> 0x003e }
        r3 = shouldProcess(r3, r6);	 Catch:{ all -> 0x003e }
        if (r3 != 0) goto L_0x0015;
    L_0x0012:
        r3 = 0;
        monitor-exit(r10);	 Catch:{ all -> 0x003e }
    L_0x0014:
        return r3;
    L_0x0015:
        r3 = com.facebook.imagepipeline.producers.JobScheduler.C11863.f170xca5c4655;	 Catch:{ all -> 0x003e }
        r6 = r10.mJobState;	 Catch:{ all -> 0x003e }
        r6 = r6.ordinal();	 Catch:{ all -> 0x003e }
        r3 = r3[r6];	 Catch:{ all -> 0x003e }
        switch(r3) {
            case 1: goto L_0x002c;
            case 2: goto L_0x0022;
            case 3: goto L_0x0041;
            default: goto L_0x0022;
        };	 Catch:{ all -> 0x003e }
    L_0x0022:
        monitor-exit(r10);	 Catch:{ all -> 0x003e }
        if (r2 == 0) goto L_0x002a;
    L_0x0025:
        r6 = r4 - r0;
        r10.enqueueJob(r6);
    L_0x002a:
        r3 = 1;
        goto L_0x0014;
    L_0x002c:
        r6 = r10.mJobStartTime;	 Catch:{ all -> 0x003e }
        r3 = r10.mMinimumJobIntervalMs;	 Catch:{ all -> 0x003e }
        r8 = (long) r3;	 Catch:{ all -> 0x003e }
        r6 = r6 + r8;
        r4 = java.lang.Math.max(r6, r0);	 Catch:{ all -> 0x003e }
        r2 = 1;
        r10.mJobSubmitTime = r0;	 Catch:{ all -> 0x003e }
        r3 = com.facebook.imagepipeline.producers.JobScheduler.JobState.QUEUED;	 Catch:{ all -> 0x003e }
        r10.mJobState = r3;	 Catch:{ all -> 0x003e }
        goto L_0x0022;
    L_0x003e:
        r3 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x003e }
        throw r3;
    L_0x0041:
        r3 = com.facebook.imagepipeline.producers.JobScheduler.JobState.RUNNING_AND_PENDING;	 Catch:{ all -> 0x003e }
        r10.mJobState = r3;	 Catch:{ all -> 0x003e }
        goto L_0x0022;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.JobScheduler.scheduleJob():boolean");
    }

    private void enqueueJob(long delay) {
        if (delay > 0) {
            JobStartExecutorSupplier.get().schedule(this.mSubmitJobRunnable, delay, TimeUnit.MILLISECONDS);
        } else {
            this.mSubmitJobRunnable.run();
        }
    }

    private void submitJob() {
        this.mExecutor.execute(this.mDoJobRunnable);
    }

    private void doJob() {
        long now = SystemClock.uptimeMillis();
        synchronized (this) {
            EncodedImage input = this.mEncodedImage;
            boolean isLast = this.mIsLast;
            this.mEncodedImage = null;
            this.mIsLast = false;
            this.mJobState = JobState.RUNNING;
            this.mJobStartTime = now;
        }
        try {
            if (shouldProcess(input, isLast)) {
                this.mJobRunnable.run(input, isLast);
            }
            EncodedImage.closeSafely(input);
            onJobFinished();
        } catch (Throwable th) {
            EncodedImage.closeSafely(input);
            onJobFinished();
        }
    }

    private void onJobFinished() {
        long now = SystemClock.uptimeMillis();
        long when = 0;
        boolean shouldEnqueue = false;
        synchronized (this) {
            if (this.mJobState == JobState.RUNNING_AND_PENDING) {
                when = Math.max(this.mJobStartTime + ((long) this.mMinimumJobIntervalMs), now);
                shouldEnqueue = true;
                this.mJobSubmitTime = now;
                this.mJobState = JobState.QUEUED;
            } else {
                this.mJobState = JobState.IDLE;
            }
        }
        if (shouldEnqueue) {
            enqueueJob(when - now);
        }
    }

    private static boolean shouldProcess(EncodedImage encodedImage, boolean isLast) {
        return isLast || EncodedImage.isValid(encodedImage);
    }

    public synchronized long getQueuedTime() {
        return this.mJobStartTime - this.mJobSubmitTime;
    }
}

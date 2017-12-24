package com.evernote.android.job;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.evernote.android.job.Job.Result;
import com.evernote.android.job.util.JobApi;
import com.evernote.android.job.util.JobUtil;
import java.util.Locale;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import net.vrallev.android.cat.CatLog;

public interface JobProxy {

    public static final class Common {
        private static final Object COMMON_MONITOR = new Object();
        public static final ThreadFactory COMMON_THREAD_FACTORY = new C10591();
        private final CatLog mCat;
        private final Context mContext;
        private final int mJobId;
        private final JobManager mJobManager;

        static class C10591 implements ThreadFactory {
            private final AtomicInteger mThreadNumber = new AtomicInteger();

            C10591() {
            }

            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "AndroidJob-" + this.mThreadNumber.incrementAndGet());
                if (thread.isDaemon()) {
                    thread.setDaemon(false);
                }
                if (thread.getPriority() != 5) {
                    thread.setPriority(5);
                }
                return thread;
            }
        }

        private static long checkedAdd(long a, long b) {
            int i = 1;
            long result = a + b;
            int i2 = (a ^ b) < 0 ? 1 : 0;
            if ((a ^ result) < 0) {
                i = 0;
            }
            return checkNoOverflow(result, i | i2);
        }

        private static long checkNoOverflow(long result, boolean condition) {
            return condition ? result : Long.MAX_VALUE;
        }

        public static long getStartMs(JobRequest request) {
            if (request.getFailureCount() > 0) {
                return request.getBackoffOffset();
            }
            return request.getStartMs();
        }

        public static long getEndMs(JobRequest request) {
            if (request.getFailureCount() > 0) {
                return request.getBackoffOffset();
            }
            return request.getEndMs();
        }

        public static long getAverageDelayMs(JobRequest request) {
            return checkedAdd(getStartMs(request), (getEndMs(request) - getStartMs(request)) / 2);
        }

        public static long getStartMsSupportFlex(JobRequest request) {
            return Math.max(1, request.getIntervalMs() - request.getFlexMs());
        }

        public static long getEndMsSupportFlex(JobRequest request) {
            return request.getIntervalMs();
        }

        public static long getAverageDelayMsSupportFlex(JobRequest request) {
            return checkedAdd(getStartMsSupportFlex(request), (getEndMsSupportFlex(request) - getStartMsSupportFlex(request)) / 2);
        }

        public static int getRescheduleCount(JobRequest request) {
            return request.getFailureCount();
        }

        public Common(@NonNull Service service, CatLog cat, int jobId) {
            this((Context) service, cat, jobId);
        }

        Common(@NonNull Context context, CatLog cat, int jobId) {
            JobManager manager;
            this.mContext = context;
            this.mJobId = jobId;
            this.mCat = cat;
            try {
                manager = JobManager.create(context);
            } catch (JobManagerCreateException e) {
                this.mCat.e(e);
                manager = null;
            }
            this.mJobManager = manager;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.evernote.android.job.JobRequest getPendingRequest(boolean r11, boolean r12) {
            /*
            r10 = this;
            r3 = 0;
            r4 = 0;
            r1 = 1;
            r5 = COMMON_MONITOR;
            monitor-enter(r5);
            r6 = r10.mJobManager;	 Catch:{ all -> 0x0108 }
            if (r6 != 0) goto L_0x000d;
        L_0x000a:
            monitor-exit(r5);	 Catch:{ all -> 0x0108 }
            r2 = r3;
        L_0x000c:
            return r2;
        L_0x000d:
            r6 = r10.mJobManager;	 Catch:{ all -> 0x0108 }
            r7 = r10.mJobId;	 Catch:{ all -> 0x0108 }
            r8 = 1;
            r2 = r6.getJobRequest(r7, r8);	 Catch:{ all -> 0x0108 }
            r6 = r10.mJobManager;	 Catch:{ all -> 0x0108 }
            r7 = r10.mJobId;	 Catch:{ all -> 0x0108 }
            r0 = r6.getJob(r7);	 Catch:{ all -> 0x0108 }
            if (r2 == 0) goto L_0x0047;
        L_0x0020:
            r6 = r2.isPeriodic();	 Catch:{ all -> 0x0108 }
            if (r6 == 0) goto L_0x0047;
        L_0x0026:
            if (r0 == 0) goto L_0x0049;
        L_0x0028:
            r4 = r0.isFinished();	 Catch:{ all -> 0x0108 }
            if (r4 != 0) goto L_0x0049;
        L_0x002e:
            r4 = r10.mCat;	 Catch:{ all -> 0x0108 }
            r6 = "Job %d is already running, %s";
            r7 = 2;
            r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0108 }
            r8 = 0;
            r9 = r10.mJobId;	 Catch:{ all -> 0x0108 }
            r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x0108 }
            r7[r8] = r9;	 Catch:{ all -> 0x0108 }
            r8 = 1;
            r7[r8] = r2;	 Catch:{ all -> 0x0108 }
            r4.d(r6, r7);	 Catch:{ all -> 0x0108 }
            monitor-exit(r5);	 Catch:{ all -> 0x0108 }
            r2 = r3;
            goto L_0x000c;
        L_0x0047:
            r1 = r4;
            goto L_0x0026;
        L_0x0049:
            if (r0 == 0) goto L_0x0069;
        L_0x004b:
            if (r1 != 0) goto L_0x0069;
        L_0x004d:
            r4 = r10.mCat;	 Catch:{ all -> 0x0108 }
            r6 = "Job %d already finished, %s";
            r7 = 2;
            r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0108 }
            r8 = 0;
            r9 = r10.mJobId;	 Catch:{ all -> 0x0108 }
            r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x0108 }
            r7[r8] = r9;	 Catch:{ all -> 0x0108 }
            r8 = 1;
            r7[r8] = r2;	 Catch:{ all -> 0x0108 }
            r4.d(r6, r7);	 Catch:{ all -> 0x0108 }
            r10.cleanUpOrphanedJob(r11);	 Catch:{ all -> 0x0108 }
            monitor-exit(r5);	 Catch:{ all -> 0x0108 }
            r2 = r3;
            goto L_0x000c;
        L_0x0069:
            if (r0 == 0) goto L_0x0094;
        L_0x006b:
            r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0108 }
            r8 = r0.getFinishedTimeStamp();	 Catch:{ all -> 0x0108 }
            r6 = r6 - r8;
            r8 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
            r4 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
            if (r4 >= 0) goto L_0x0094;
        L_0x007a:
            r4 = r10.mCat;	 Catch:{ all -> 0x0108 }
            r6 = "Job %d is periodic and just finished, %s";
            r7 = 2;
            r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0108 }
            r8 = 0;
            r9 = r10.mJobId;	 Catch:{ all -> 0x0108 }
            r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x0108 }
            r7[r8] = r9;	 Catch:{ all -> 0x0108 }
            r8 = 1;
            r7[r8] = r2;	 Catch:{ all -> 0x0108 }
            r4.d(r6, r7);	 Catch:{ all -> 0x0108 }
            monitor-exit(r5);	 Catch:{ all -> 0x0108 }
            r2 = r3;
            goto L_0x000c;
        L_0x0094:
            if (r2 == 0) goto L_0x00b6;
        L_0x0096:
            r4 = r2.isTransient();	 Catch:{ all -> 0x0108 }
            if (r4 == 0) goto L_0x00b6;
        L_0x009c:
            r4 = r10.mCat;	 Catch:{ all -> 0x0108 }
            r6 = "Request %d is transient, %s";
            r7 = 2;
            r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0108 }
            r8 = 0;
            r9 = r10.mJobId;	 Catch:{ all -> 0x0108 }
            r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x0108 }
            r7[r8] = r9;	 Catch:{ all -> 0x0108 }
            r8 = 1;
            r7[r8] = r2;	 Catch:{ all -> 0x0108 }
            r4.d(r6, r7);	 Catch:{ all -> 0x0108 }
            monitor-exit(r5);	 Catch:{ all -> 0x0108 }
            r2 = r3;
            goto L_0x000c;
        L_0x00b6:
            if (r2 == 0) goto L_0x00de;
        L_0x00b8:
            r4 = r10.mJobManager;	 Catch:{ all -> 0x0108 }
            r4 = r4.getJobExecutor();	 Catch:{ all -> 0x0108 }
            r4 = r4.isRequestStarting(r2);	 Catch:{ all -> 0x0108 }
            if (r4 == 0) goto L_0x00de;
        L_0x00c4:
            r4 = r10.mCat;	 Catch:{ all -> 0x0108 }
            r6 = "Request %d is in the queue to start, %s";
            r7 = 2;
            r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0108 }
            r8 = 0;
            r9 = r10.mJobId;	 Catch:{ all -> 0x0108 }
            r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x0108 }
            r7[r8] = r9;	 Catch:{ all -> 0x0108 }
            r8 = 1;
            r7[r8] = r2;	 Catch:{ all -> 0x0108 }
            r4.d(r6, r7);	 Catch:{ all -> 0x0108 }
            monitor-exit(r5);	 Catch:{ all -> 0x0108 }
            r2 = r3;
            goto L_0x000c;
        L_0x00de:
            if (r2 != 0) goto L_0x00fa;
        L_0x00e0:
            r4 = r10.mCat;	 Catch:{ all -> 0x0108 }
            r6 = "Request for ID %d was null";
            r7 = 1;
            r7 = new java.lang.Object[r7];	 Catch:{ all -> 0x0108 }
            r8 = 0;
            r9 = r10.mJobId;	 Catch:{ all -> 0x0108 }
            r9 = java.lang.Integer.valueOf(r9);	 Catch:{ all -> 0x0108 }
            r7[r8] = r9;	 Catch:{ all -> 0x0108 }
            r4.d(r6, r7);	 Catch:{ all -> 0x0108 }
            r10.cleanUpOrphanedJob(r11);	 Catch:{ all -> 0x0108 }
            monitor-exit(r5);	 Catch:{ all -> 0x0108 }
            r2 = r3;
            goto L_0x000c;
        L_0x00fa:
            if (r12 == 0) goto L_0x0105;
        L_0x00fc:
            r3 = r10.mJobManager;	 Catch:{ all -> 0x0108 }
            r3 = r3.getJobExecutor();	 Catch:{ all -> 0x0108 }
            r3.markJobRequestStarting(r2);	 Catch:{ all -> 0x0108 }
        L_0x0105:
            monitor-exit(r5);	 Catch:{ all -> 0x0108 }
            goto L_0x000c;
        L_0x0108:
            r3 = move-exception;
            monitor-exit(r5);	 Catch:{ all -> 0x0108 }
            throw r3;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.evernote.android.job.JobProxy.Common.getPendingRequest(boolean, boolean):com.evernote.android.job.JobRequest");
        }

        @NonNull
        public Result executeJobRequest(@NonNull JobRequest request) {
            String timeWindow;
            Result result;
            Exception e;
            long waited = System.currentTimeMillis() - request.getScheduledAt();
            if (request.isPeriodic()) {
                timeWindow = String.format(Locale.US, "interval %s, flex %s", new Object[]{JobUtil.timeToString(request.getIntervalMs()), JobUtil.timeToString(request.getFlexMs())});
            } else if (request.getJobApi().supportsExecutionWindow()) {
                timeWindow = String.format(Locale.US, "start %s, end %s", new Object[]{JobUtil.timeToString(getStartMs(request)), JobUtil.timeToString(getEndMs(request))});
            } else {
                timeWindow = "delay " + JobUtil.timeToString(getAverageDelayMs(request));
            }
            if (Looper.myLooper() == Looper.getMainLooper()) {
                this.mCat.w("Running JobRequest on a main thread, this could cause stutter or ANR in your app.");
            }
            this.mCat.d("Run job, %s, waited %s, %s", new Object[]{request, JobUtil.timeToString(waited), timeWindow});
            JobExecutor jobExecutor = this.mJobManager.getJobExecutor();
            Job job = null;
            try {
                job = this.mJobManager.getJobCreatorHolder().createJob(request.getTag());
                if (!request.isPeriodic()) {
                    request.setTransient(true);
                }
                Future<Result> future = jobExecutor.execute(this.mContext, request, job);
                if (future == null) {
                    result = Result.FAILURE;
                    if (!request.isPeriodic()) {
                        this.mJobManager.getJobStorage().remove(request);
                    } else if (request.isFlexSupport()) {
                        this.mJobManager.getJobStorage().remove(request);
                        request.reschedule(false, false);
                    }
                } else {
                    result = (Result) future.get();
                    this.mCat.d("Finished job, %s %s", new Object[]{request, result});
                    if (!request.isPeriodic()) {
                        this.mJobManager.getJobStorage().remove(request);
                    } else if (request.isFlexSupport()) {
                        this.mJobManager.getJobStorage().remove(request);
                        request.reschedule(false, false);
                    }
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    this.mCat.e(e);
                    if (job != null) {
                        job.cancel();
                        this.mCat.e("Canceled %s", new Object[]{request});
                    }
                    result = Result.FAILURE;
                    return result;
                } finally {
                    if (!request.isPeriodic()) {
                        this.mJobManager.getJobStorage().remove(request);
                    } else if (request.isFlexSupport()) {
                        this.mJobManager.getJobStorage().remove(request);
                        request.reschedule(false, false);
                    }
                }
            } catch (Exception e22) {
                e = e22;
                this.mCat.e(e);
                if (job != null) {
                    job.cancel();
                    this.mCat.e("Canceled %s", new Object[]{request});
                }
                result = Result.FAILURE;
                return result;
            }
            return result;
        }

        private void cleanUpOrphanedJob(boolean cleanUp) {
            if (cleanUp) {
                cleanUpOrphanedJob(this.mContext, this.mJobId);
            }
        }

        public static void cleanUpOrphanedJob(Context context, int jobId) {
            for (JobApi jobApi : JobApi.values()) {
                if (jobApi.isSupported(context)) {
                    try {
                        jobApi.getCachedProxy(context).cancel(jobId);
                    } catch (Exception e) {
                    }
                }
            }
        }

        public static ComponentName startWakefulService(Context context, Intent intent) {
            return WakeLockUtil.startWakefulService(context, intent);
        }

        public static boolean completeWakefulIntent(Intent intent) {
            return WakeLockUtil.completeWakefulIntent(intent);
        }
    }

    void cancel(int i);

    boolean isPlatformJobScheduled(JobRequest jobRequest);

    void plantOneOff(JobRequest jobRequest);

    void plantPeriodic(JobRequest jobRequest);

    void plantPeriodicFlexSupport(JobRequest jobRequest);
}

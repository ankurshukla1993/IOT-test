package com.evernote.android.job;

import android.content.Context;
import android.os.PowerManager.WakeLock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.LruCache;
import android.util.SparseArray;
import com.evernote.android.job.Job.Result;
import com.evernote.android.job.JobProxy.Common;
import com.evernote.android.job.util.JobCat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import net.vrallev.android.cat.CatLog;

class JobExecutor {
    private static final CatLog CAT = new JobCat("JobExecutor");
    private static final long WAKE_LOCK_TIMEOUT = TimeUnit.MINUTES.toMillis(3);
    private final ExecutorService mExecutorService = Executors.newCachedThreadPool(Common.COMMON_THREAD_FACTORY);
    private final LruCache<Integer, Job> mFinishedJobsCache = new LruCache(20);
    private final SparseArray<Job> mJobs = new SparseArray();
    private final Set<JobRequest> mStartingRequests = new HashSet();

    private final class JobCallable implements Callable<Result> {
        private final Job mJob;
        private final WakeLock mWakeLock;

        private JobCallable(Job job) {
            this.mJob = job;
            this.mWakeLock = WakeLockUtil.acquireWakeLock(this.mJob.getContext(), "JobExecutor", JobExecutor.WAKE_LOCK_TIMEOUT);
        }

        public Result call() throws Exception {
            try {
                WakeLockUtil.acquireWakeLock(this.mJob.getContext(), this.mWakeLock, JobExecutor.WAKE_LOCK_TIMEOUT);
                Result runJob = runJob();
                JobExecutor.this.markJobAsFinished(this.mJob);
                if (this.mWakeLock == null || !this.mWakeLock.isHeld()) {
                    JobExecutor.CAT.w("Wake lock was not held after job %s was done. The job took too long to complete. This could have unintended side effects on your app.", new Object[]{this.mJob});
                }
                WakeLockUtil.releaseWakeLock(this.mWakeLock);
                return runJob;
            } catch (Throwable th) {
                JobExecutor.this.markJobAsFinished(this.mJob);
                if (this.mWakeLock == null || !this.mWakeLock.isHeld()) {
                    JobExecutor.CAT.w("Wake lock was not held after job %s was done. The job took too long to complete. This could have unintended side effects on your app.", new Object[]{this.mJob});
                }
                WakeLockUtil.releaseWakeLock(this.mWakeLock);
            }
        }

        private Result runJob() {
            try {
                Result result = this.mJob.runJob();
                JobExecutor.CAT.i("Finished %s", new Object[]{this.mJob});
                handleResult(this.mJob, result);
                return result;
            } catch (Throwable t) {
                JobExecutor.CAT.e(t, "Crashed %s", new Object[]{this.mJob});
                return this.mJob.getResult();
            }
        }

        private void handleResult(Job job, Result result) {
            JobRequest request = this.mJob.getParams().getRequest();
            boolean incFailureCount = false;
            boolean updateLastRun = false;
            if (!request.isPeriodic() && Result.RESCHEDULE.equals(result)) {
                request = request.reschedule(true, true);
                this.mJob.onReschedule(request.getJobId());
                updateLastRun = true;
            } else if (request.isPeriodic()) {
                updateLastRun = true;
                if (!Result.SUCCESS.equals(result)) {
                    incFailureCount = true;
                }
            }
            if (!job.isDeleted()) {
                if (incFailureCount || updateLastRun) {
                    request.updateStats(incFailureCount, updateLastRun);
                }
            }
        }
    }

    public synchronized Future<Result> execute(@NonNull Context context, @NonNull JobRequest request, @Nullable Job job) {
        Future<Result> future = null;
        synchronized (this) {
            this.mStartingRequests.remove(request);
            if (job == null) {
                CAT.w("JobCreator returned null for tag %s", new Object[]{request.getTag()});
            } else if (job.isFinished()) {
                throw new IllegalStateException(String.format(Locale.ENGLISH, "Job for tag %s was already run, a creator should always create a new Job instance", new Object[]{request.getTag()}));
            } else {
                job.setContext(context).setRequest(request);
                CAT.i("Executing %s, context %s", new Object[]{request, context.getClass().getSimpleName()});
                this.mJobs.put(request.getJobId(), job);
                future = this.mExecutorService.submit(new JobCallable(job));
            }
        }
        return future;
    }

    public synchronized Job getJob(int jobId) {
        Job job;
        job = (Job) this.mJobs.get(jobId);
        if (job == null) {
            job = (Job) this.mFinishedJobsCache.get(Integer.valueOf(jobId));
        }
        return job;
    }

    public synchronized Set<Job> getAllJobs() {
        return getAllJobsForTag(null);
    }

    public synchronized Set<Job> getAllJobsForTag(String tag) {
        Set<Job> result;
        result = new HashSet();
        for (int i = 0; i < this.mJobs.size(); i++) {
            Job job = (Job) this.mJobs.valueAt(i);
            if (tag == null || tag.equals(job.getParams().getTag())) {
                result.add(job);
            }
        }
        for (Job job2 : this.mFinishedJobsCache.snapshot().values()) {
            if (tag == null || tag.equals(job2.getParams().getTag())) {
                result.add(job2);
            }
        }
        return result;
    }

    public synchronized void markJobRequestStarting(@NonNull JobRequest request) {
        this.mStartingRequests.add(request);
    }

    public synchronized boolean isRequestStarting(JobRequest request) {
        boolean z;
        if (request != null) {
            if (this.mStartingRequests.contains(request)) {
                z = true;
            }
        }
        z = false;
        return z;
    }

    private synchronized void markJobAsFinished(Job job) {
        int id = job.getParams().getId();
        this.mJobs.remove(id);
        this.mFinishedJobsCache.put(Integer.valueOf(id), job);
    }
}

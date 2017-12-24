package com.evernote.android.job;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.evernote.android.job.JobCreator.AddJobCreatorReceiver;
import com.evernote.android.job.JobProxy.Common;
import com.evernote.android.job.util.JobApi;
import com.evernote.android.job.util.JobCat;
import com.evernote.android.job.util.JobPreconditions;
import com.evernote.android.job.util.JobUtil;
import java.util.List;
import java.util.Set;
import net.vrallev.android.cat.CatLog;

public final class JobManager {
    private static final CatLog CAT = new JobCat("JobManager");
    @SuppressLint({"StaticFieldLeak"})
    private static volatile JobManager instance;
    private JobApi mApi;
    private final Config mConfig;
    private final Context mContext;
    private final JobCreatorHolder mJobCreatorHolder = new JobCreatorHolder();
    private final JobExecutor mJobExecutor;
    private final JobStorage mJobStorage;

    public static JobManager create(@NonNull Context context) throws JobManagerCreateException {
        if (instance == null) {
            synchronized (JobManager.class) {
                if (instance == null) {
                    JobPreconditions.checkNotNull(context, "Context cannot be null");
                    if (context.getApplicationContext() != null) {
                        context = context.getApplicationContext();
                    }
                    instance = new JobManager(context);
                    if (!JobUtil.hasWakeLockPermission(context)) {
                        CAT.w("No wake lock permission");
                    }
                    if (!JobUtil.hasBootPermission(context)) {
                        CAT.w("No boot permission");
                    }
                    sendAddJobCreatorIntent(context);
                }
            }
        }
        return instance;
    }

    @Deprecated
    public static JobManager create(Context context, JobCreator jobCreator) {
        synchronized (JobManager.class) {
            boolean addJobCreator = instance == null;
        }
        create(context);
        if (addJobCreator) {
            instance.addJobCreator(jobCreator);
        }
        return instance;
    }

    public static JobManager instance() {
        if (instance == null) {
            synchronized (JobManager.class) {
                if (instance == null) {
                    throw new IllegalStateException("You need to call create() at least once to create the singleton");
                }
            }
        }
        return instance;
    }

    private JobManager(Context context) {
        this.mContext = context;
        this.mJobStorage = new JobStorage(context);
        this.mJobExecutor = new JobExecutor();
        this.mConfig = new Config(this, null);
        JobApi api = JobApi.getDefault(this.mContext, this.mConfig.isGcmApiEnabled());
        if (api != JobApi.V_14 || api.isSupported(this.mContext)) {
            setJobProxy(api);
            JobRescheduleService.startService(this.mContext);
            return;
        }
        throw new JobManagerCreateException("All APIs are disabled, cannot schedule any job");
    }

    public Config getConfig() {
        return this.mConfig;
    }

    private void setJobProxy(JobApi api) {
        this.mApi = api;
    }

    public void schedule(@NonNull JobRequest request) {
        if (this.mJobCreatorHolder.isEmpty()) {
            CAT.w("you haven't registered a JobCreator with addJobCreator(), it's likely that your job never will be executed");
        }
        if (request.getScheduledAt() <= 0) {
            if (request.isUpdateCurrent()) {
                cancelAllForTag(request.getTag());
            }
            Common.cleanUpOrphanedJob(this.mContext, request.getJobId());
            JobApi jobApi = request.getJobApi();
            boolean periodic = request.isPeriodic();
            boolean flexSupport = periodic && jobApi.isFlexSupport() && request.getFlexMs() < request.getIntervalMs();
            if (jobApi == JobApi.GCM && !this.mConfig.isGcmApiEnabled()) {
                CAT.w("GCM API disabled, but used nonetheless");
            }
            request.setScheduledAt(System.currentTimeMillis());
            request.setFlexSupport(flexSupport);
            this.mJobStorage.put(request);
            try {
                scheduleWithApi(request, jobApi, periodic, flexSupport);
            } catch (JobProxyIllegalStateException e) {
                try {
                    jobApi.invalidateCachedProxy();
                    scheduleWithApi(request, jobApi, periodic, flexSupport);
                } catch (Exception e2) {
                    if (jobApi == JobApi.V_14 || jobApi == JobApi.V_19) {
                        this.mJobStorage.remove(request);
                        throw e2;
                    }
                    try {
                        scheduleWithApi(request, JobApi.V_19.isSupported(this.mContext) ? JobApi.V_19 : JobApi.V_14, periodic, flexSupport);
                    } catch (Exception e22) {
                        this.mJobStorage.remove(request);
                        throw e22;
                    }
                }
            } catch (Exception e222) {
                this.mJobStorage.remove(request);
                throw e222;
            }
        }
    }

    private void scheduleWithApi(JobRequest request, JobApi jobApi, boolean periodic, boolean flexSupport) {
        JobProxy proxy = getJobProxy(jobApi);
        if (!periodic) {
            proxy.plantOneOff(request);
        } else if (flexSupport) {
            proxy.plantPeriodicFlexSupport(request);
        } else {
            proxy.plantPeriodic(request);
        }
    }

    public JobRequest getJobRequest(int jobId) {
        return getJobRequest(jobId, false);
    }

    JobRequest getJobRequest(int jobId, boolean includeTransient) {
        JobRequest jobRequest = this.mJobStorage.get(jobId);
        if (includeTransient || jobRequest == null || !jobRequest.isTransient()) {
            return jobRequest;
        }
        return null;
    }

    @NonNull
    public Set<JobRequest> getAllJobRequests() {
        return this.mJobStorage.getAllJobRequests(null, false);
    }

    public Set<JobRequest> getAllJobRequestsForTag(@NonNull String tag) {
        return this.mJobStorage.getAllJobRequests(tag, false);
    }

    public Job getJob(int jobId) {
        return this.mJobExecutor.getJob(jobId);
    }

    @NonNull
    public Set<Job> getAllJobs() {
        return this.mJobExecutor.getAllJobs();
    }

    @NonNull
    public Set<Job> getAllJobsForTag(@NonNull String tag) {
        return this.mJobExecutor.getAllJobsForTag(tag);
    }

    public void forceApi(@NonNull JobApi api) {
        setJobProxy((JobApi) JobPreconditions.checkNotNull(api));
        CAT.w("Changed API to %s", new Object[]{api});
    }

    public JobApi getApi() {
        return this.mApi;
    }

    public boolean cancel(int jobId) {
        boolean result = cancelInner(getJobRequest(jobId, true)) | cancelInner(getJob(jobId));
        Common.cleanUpOrphanedJob(this.mContext, jobId);
        return result;
    }

    public int cancelAll() {
        return cancelAllInner(null);
    }

    public int cancelAllForTag(@NonNull String tag) {
        return cancelAllInner(tag);
    }

    private boolean cancelInner(@Nullable JobRequest request) {
        if (request == null) {
            return false;
        }
        CAT.i("Found pending job %s, canceling", new Object[]{request});
        getJobProxy(request.getJobApi()).cancel(request.getJobId());
        getJobStorage().remove(request);
        request.setScheduledAt(0);
        return true;
    }

    private boolean cancelInner(@Nullable Job job) {
        if (job == null || job.isFinished() || job.isCanceled()) {
            return false;
        }
        CAT.i("Cancel running %s", new Object[]{job});
        job.cancel(true);
        return true;
    }

    private synchronized int cancelAllInner(@Nullable String tag) {
        int canceled;
        canceled = 0;
        for (JobRequest request : this.mJobStorage.getAllJobRequests(tag, true)) {
            if (cancelInner(request)) {
                canceled++;
            }
        }
        for (Job job : TextUtils.isEmpty(tag) ? getAllJobs() : getAllJobsForTag(tag)) {
            if (cancelInner(job)) {
                canceled++;
            }
        }
        return canceled;
    }

    @Deprecated
    public void setVerbose(boolean verbose) {
        this.mConfig.setVerbose(verbose);
    }

    public void addJobCreator(JobCreator jobCreator) {
        this.mJobCreatorHolder.addJobCreator(jobCreator);
    }

    public void removeJobCreator(JobCreator jobCreator) {
        this.mJobCreatorHolder.removeJobCreator(jobCreator);
    }

    JobStorage getJobStorage() {
        return this.mJobStorage;
    }

    JobExecutor getJobExecutor() {
        return this.mJobExecutor;
    }

    JobCreatorHolder getJobCreatorHolder() {
        return this.mJobCreatorHolder;
    }

    Context getContext() {
        return this.mContext;
    }

    void destroy() {
        synchronized (JobManager.class) {
            instance = null;
            for (JobApi api : JobApi.values()) {
                api.invalidateCachedProxy();
            }
        }
    }

    JobProxy getJobProxy(JobApi api) {
        return api.getCachedProxy(this.mContext);
    }

    private static void sendAddJobCreatorIntent(@NonNull Context context) {
        List<ResolveInfo> resolveInfos = context.getPackageManager().queryBroadcastReceivers(new Intent(JobCreator.ACTION_ADD_JOB_CREATOR), 0);
        String myPackage = context.getPackageName();
        for (ResolveInfo resolveInfo : resolveInfos) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (!(activityInfo == null || activityInfo.exported || !myPackage.equals(activityInfo.packageName) || TextUtils.isEmpty(activityInfo.name))) {
                try {
                    ((AddJobCreatorReceiver) Class.forName(activityInfo.name).newInstance()).addJobCreator(context, instance);
                } catch (Exception e) {
                }
            }
        }
    }
}

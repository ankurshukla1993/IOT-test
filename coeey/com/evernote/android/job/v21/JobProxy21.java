package com.evernote.android.job.v21;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.annotation.NonNull;
import com.evernote.android.job.JobProxy;
import com.evernote.android.job.JobProxy.Common;
import com.evernote.android.job.JobProxyIllegalStateException;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.JobRequest.NetworkType;
import com.evernote.android.job.util.JobCat;
import com.evernote.android.job.util.JobUtil;
import com.facebook.GraphResponse;
import java.util.List;
import net.vrallev.android.cat.CatLog;

@TargetApi(21)
public class JobProxy21 implements JobProxy {
    private static final int ERROR_BOOT_PERMISSION = -123;
    private static final String TAG = "JobProxy21";
    protected final CatLog mCat;
    protected final Context mContext;

    public JobProxy21(Context context) {
        this(context, TAG);
    }

    protected JobProxy21(Context context, String logTag) {
        this.mContext = context;
        this.mCat = new JobCat(logTag);
    }

    public void plantOneOff(JobRequest request) {
        long startMs = Common.getStartMs(request);
        long endMs = Common.getEndMs(request);
        int scheduleResult = schedule(createBuilderOneOff(createBaseBuilder(request, true), startMs, endMs).build());
        if (scheduleResult == ERROR_BOOT_PERMISSION) {
            scheduleResult = schedule(createBuilderOneOff(createBaseBuilder(request, false), startMs, endMs).build());
        }
        this.mCat.d("Schedule one-off jobInfo %s, %s, start %s, end %s, reschedule count %d", new Object[]{scheduleResultToString(scheduleResult), request, JobUtil.timeToString(startMs), JobUtil.timeToString(endMs), Integer.valueOf(Common.getRescheduleCount(request))});
    }

    public void plantPeriodic(JobRequest request) {
        long intervalMs = request.getIntervalMs();
        long flexMs = request.getFlexMs();
        int scheduleResult = schedule(createBuilderPeriodic(createBaseBuilder(request, true), intervalMs, flexMs).build());
        if (scheduleResult == ERROR_BOOT_PERMISSION) {
            scheduleResult = schedule(createBuilderPeriodic(createBaseBuilder(request, false), intervalMs, flexMs).build());
        }
        this.mCat.d("Schedule periodic jobInfo %s, %s, interval %s, flex %s", new Object[]{scheduleResultToString(scheduleResult), request, JobUtil.timeToString(intervalMs), JobUtil.timeToString(flexMs)});
    }

    public void plantPeriodicFlexSupport(JobRequest request) {
        long startMs = Common.getStartMsSupportFlex(request);
        long endMs = Common.getEndMsSupportFlex(request);
        int scheduleResult = schedule(createBuilderOneOff(createBaseBuilder(request, true), startMs, endMs).build());
        if (scheduleResult == ERROR_BOOT_PERMISSION) {
            scheduleResult = schedule(createBuilderOneOff(createBaseBuilder(request, false), startMs, endMs).build());
        }
        this.mCat.d("Schedule periodic (flex support) jobInfo %s, %s, start %s, end %s, flex %s", new Object[]{scheduleResultToString(scheduleResult), request, JobUtil.timeToString(startMs), JobUtil.timeToString(endMs), JobUtil.timeToString(request.getFlexMs())});
    }

    public void cancel(int jobId) {
        try {
            getJobScheduler().cancel(jobId);
        } catch (Exception e) {
            this.mCat.e(e);
        }
    }

    public boolean isPlatformJobScheduled(JobRequest request) {
        try {
            List<JobInfo> pendingJobs = getJobScheduler().getAllPendingJobs();
            if (pendingJobs == null || pendingJobs.isEmpty()) {
                return false;
            }
            int requestId = request.getJobId();
            for (JobInfo info : pendingJobs) {
                if (info.getId() == requestId) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            this.mCat.e(e);
            return false;
        }
    }

    protected Builder createBaseBuilder(JobRequest request, boolean allowPersisting) {
        Builder requiredNetworkType = new Builder(request.getJobId(), new ComponentName(this.mContext, PlatformJobService.class)).setRequiresCharging(request.requiresCharging()).setRequiresDeviceIdle(request.requiresDeviceIdle()).setRequiredNetworkType(convertNetworkType(request.requiredNetworkType()));
        boolean z = allowPersisting && request.isPersisted();
        return requiredNetworkType.setPersisted(z);
    }

    protected Builder createBuilderOneOff(Builder builder, long startMs, long endMs) {
        return builder.setMinimumLatency(startMs).setOverrideDeadline(endMs);
    }

    protected Builder createBuilderPeriodic(Builder builder, long intervalMs, long flexMs) {
        return builder.setPeriodic(intervalMs);
    }

    protected int convertNetworkType(@NonNull NetworkType networkType) {
        switch (networkType) {
            case ANY:
                return 0;
            case CONNECTED:
                return 1;
            case UNMETERED:
            case NOT_ROAMING:
                return 2;
            default:
                throw new IllegalStateException("not implemented");
        }
    }

    protected final JobScheduler getJobScheduler() {
        return (JobScheduler) this.mContext.getSystemService("jobscheduler");
    }

    protected final int schedule(JobInfo jobInfo) {
        JobScheduler jobScheduler = getJobScheduler();
        if (jobScheduler == null) {
            throw new JobProxyIllegalStateException("JobScheduler is null");
        }
        try {
            return jobScheduler.schedule(jobInfo);
        } catch (Throwable e) {
            this.mCat.e(e);
            String message = e.getMessage();
            if (message != null && message.contains("RECEIVE_BOOT_COMPLETED")) {
                return ERROR_BOOT_PERMISSION;
            }
            if (message == null || !message.contains("No such service ComponentInfo")) {
                throw e;
            }
            throw new JobProxyIllegalStateException(e);
        }
    }

    protected static String scheduleResultToString(int scheduleResult) {
        return scheduleResult == 1 ? GraphResponse.SUCCESS_KEY : "failure";
    }
}

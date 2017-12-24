package com.evernote.android.job.gcm;

import android.content.Context;
import android.support.annotation.NonNull;
import com.evernote.android.job.JobProxy;
import com.evernote.android.job.JobProxy.Common;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.JobRequest.NetworkType;
import com.evernote.android.job.util.JobCat;
import com.evernote.android.job.util.JobUtil;
import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.OneoffTask.Builder;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import net.vrallev.android.cat.CatLog;

public class JobProxyGcm implements JobProxy {
    private static final CatLog CAT = new JobCat("JobProxyGcm");
    private final GcmNetworkManager mGcmNetworkManager;

    public JobProxyGcm(Context context) {
        this.mGcmNetworkManager = GcmNetworkManager.getInstance(context);
    }

    public void plantOneOff(JobRequest request) {
        long startSeconds = Common.getStartMs(request) / 1000;
        this.mGcmNetworkManager.schedule(((Builder) prepareBuilder(new Builder(), request)).setExecutionWindow(startSeconds, Math.max(Common.getEndMs(request) / 1000, 1 + startSeconds)).build());
        CAT.d("Scheduled OneoffTask, %s, start %s, end %s, reschedule count %d", new Object[]{request, JobUtil.timeToString(startMs), JobUtil.timeToString(endMs), Integer.valueOf(Common.getRescheduleCount(request))});
    }

    public void plantPeriodic(JobRequest request) {
        this.mGcmNetworkManager.schedule(((PeriodicTask.Builder) prepareBuilder(new PeriodicTask.Builder(), request)).setPeriod(request.getIntervalMs() / 1000).setFlex(request.getFlexMs() / 1000).build());
        CAT.d("Scheduled PeriodicTask, %s, interval %s, flex %s", new Object[]{request, JobUtil.timeToString(request.getIntervalMs()), JobUtil.timeToString(request.getFlexMs())});
    }

    public void plantPeriodicFlexSupport(JobRequest request) {
        CAT.w("plantPeriodicFlexSupport called although flex is supported");
        this.mGcmNetworkManager.schedule(((Builder) prepareBuilder(new Builder(), request)).setExecutionWindow(Common.getStartMsSupportFlex(request) / 1000, Common.getEndMsSupportFlex(request) / 1000).build());
        CAT.d("Scheduled periodic (flex support), %s, start %s, end %s, flex %s", new Object[]{request, JobUtil.timeToString(startMs), JobUtil.timeToString(endMs), JobUtil.timeToString(request.getFlexMs())});
    }

    public void cancel(int jobId) {
        this.mGcmNetworkManager.cancelTask(createTag(jobId), PlatformGcmService.class);
    }

    public boolean isPlatformJobScheduled(JobRequest request) {
        return true;
    }

    protected <T extends Task.Builder> T prepareBuilder(T builder, JobRequest request) {
        builder.setTag(createTag(request)).setService(PlatformGcmService.class).setUpdateCurrent(true).setRequiredNetwork(convertNetworkType(request.requiredNetworkType())).setPersisted(request.isPersisted()).setRequiresCharging(request.requiresCharging());
        return builder;
    }

    protected String createTag(JobRequest request) {
        return createTag(request.getJobId());
    }

    protected String createTag(int jobId) {
        return String.valueOf(jobId);
    }

    protected int convertNetworkType(@NonNull NetworkType networkType) {
        switch (networkType) {
            case ANY:
                return 2;
            case CONNECTED:
                return 0;
            case UNMETERED:
            case NOT_ROAMING:
                return 1;
            default:
                throw new IllegalStateException("not implemented");
        }
    }
}

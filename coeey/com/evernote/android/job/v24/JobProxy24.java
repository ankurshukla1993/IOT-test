package com.evernote.android.job.v24;

import android.annotation.TargetApi;
import android.app.job.JobInfo.Builder;
import android.content.Context;
import android.support.annotation.NonNull;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.JobRequest.NetworkType;
import com.evernote.android.job.v21.JobProxy21;

@TargetApi(24)
public class JobProxy24 extends JobProxy21 {
    private static final String TAG = "JobProxy24";

    public JobProxy24(Context context) {
        super(context, TAG);
    }

    public void plantPeriodicFlexSupport(JobRequest request) {
        this.mCat.w("plantPeriodicFlexSupport called although flex is supported");
        super.plantPeriodicFlexSupport(request);
    }

    public boolean isPlatformJobScheduled(JobRequest request) {
        try {
            return getJobScheduler().getPendingJob(request.getJobId()) != null;
        } catch (Exception e) {
            this.mCat.e(e);
            return false;
        }
    }

    protected Builder createBuilderPeriodic(Builder builder, long intervalMs, long flexMs) {
        return builder.setPeriodic(intervalMs, flexMs);
    }

    protected int convertNetworkType(@NonNull NetworkType networkType) {
        switch (networkType) {
            case NOT_ROAMING:
                return 3;
            default:
                return super.convertNetworkType(networkType);
        }
    }
}

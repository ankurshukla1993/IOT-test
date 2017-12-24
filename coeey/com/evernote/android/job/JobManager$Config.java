package com.evernote.android.job;

import android.os.Build.VERSION;
import com.evernote.android.job.util.JobApi;
import com.evernote.android.job.util.JobCat;

public final class JobManager$Config {
    private boolean mAllowSmallerIntervals;
    private boolean mGcmEnabled;
    final /* synthetic */ JobManager this$0;

    private JobManager$Config(JobManager this$0) {
        this.this$0 = this$0;
        this.mGcmEnabled = true;
        this.mAllowSmallerIntervals = false;
    }

    public boolean isVerbose() {
        return JobCat.isLogcatEnabled();
    }

    public void setVerbose(boolean verbose) {
        JobCat.setLogcatEnabled(verbose);
    }

    public boolean isGcmApiEnabled() {
        return this.mGcmEnabled;
    }

    public void setGcmApiEnabled(boolean enabled) {
        if (enabled != this.mGcmEnabled) {
            this.mGcmEnabled = enabled;
            JobApi defaultApi;
            if (enabled) {
                defaultApi = JobApi.getDefault(JobManager.access$100(this.this$0), true);
                if (!defaultApi.equals(this.this$0.getApi())) {
                    JobManager.access$200(this.this$0, defaultApi);
                    JobManager.access$300().i("Changed default proxy to %s after enabled the GCM API", new Object[]{defaultApi});
                    return;
                }
                return;
            }
            defaultApi = JobApi.getDefault(JobManager.access$100(this.this$0), false);
            if (JobApi.GCM == this.this$0.getApi()) {
                JobManager.access$200(this.this$0, defaultApi);
                JobManager.access$300().i("Changed default proxy to %s after disabling the GCM API", new Object[]{defaultApi});
            }
        }
    }

    public boolean isAllowSmallerIntervalsForMarshmallow() {
        return this.mAllowSmallerIntervals && VERSION.SDK_INT < 24;
    }

    public void setAllowSmallerIntervalsForMarshmallow(boolean allowSmallerIntervals) {
        if (!allowSmallerIntervals || VERSION.SDK_INT < 24) {
            this.mAllowSmallerIntervals = allowSmallerIntervals;
            return;
        }
        throw new IllegalStateException("This method is only allowed to call on Android M or earlier");
    }
}

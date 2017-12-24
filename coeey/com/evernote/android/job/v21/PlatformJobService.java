package com.evernote.android.job.v21;

import android.annotation.TargetApi;
import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import com.evernote.android.job.Job;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobProxy.Common;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.JobCat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.vrallev.android.cat.CatLog;

@TargetApi(21)
public class PlatformJobService extends JobService {
    private static final CatLog CAT = new JobCat("PlatformJobService");
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool(Common.COMMON_THREAD_FACTORY);

    public boolean onStartJob(final JobParameters params) {
        final Common common = new Common((Service) this, CAT, params.getJobId());
        final JobRequest request = common.getPendingRequest(true, true);
        if (request == null) {
            return false;
        }
        EXECUTOR_SERVICE.execute(new Runnable() {
            public void run() {
                try {
                    common.executeJobRequest(request);
                } finally {
                    PlatformJobService.this.jobFinished(params, false);
                }
            }
        });
        return true;
    }

    public boolean onStopJob(JobParameters params) {
        Job job = JobManager.instance().getJob(params.getJobId());
        if (job != null) {
            job.cancel();
            CAT.d("Called onStopJob for %s", new Object[]{job});
        } else {
            CAT.d("Called onStopJob, job %d not found", new Object[]{Integer.valueOf(params.getJobId())});
        }
        return false;
    }
}

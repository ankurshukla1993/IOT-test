package com.evernote.android.job.gcm;

import android.app.Service;
import com.evernote.android.job.Job.Result;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobManagerCreateException;
import com.evernote.android.job.JobProxy.Common;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.JobCat;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import net.vrallev.android.cat.CatLog;

public class PlatformGcmService extends GcmTaskService {
    private static final CatLog CAT = new JobCat("JobRequest");

    public int onRunTask(TaskParams taskParams) {
        Common common = new Common((Service) this, CAT, Integer.parseInt(taskParams.getTag()));
        JobRequest request = common.getPendingRequest(true, true);
        if (request == null) {
            return 2;
        }
        if (Result.SUCCESS.equals(common.executeJobRequest(request))) {
            return 0;
        }
        return 2;
    }

    public void onInitializeTasks() {
        super.onInitializeTasks();
        try {
            JobManager.create(getApplicationContext());
        } catch (JobManagerCreateException e) {
        }
    }
}

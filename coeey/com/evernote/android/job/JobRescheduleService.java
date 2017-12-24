package com.evernote.android.job;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.VisibleForTesting;
import com.evernote.android.job.util.JobCat;
import java.util.concurrent.CountDownLatch;
import net.vrallev.android.cat.CatLog;

public final class JobRescheduleService extends IntentService {
    private static final CatLog CAT = new JobCat(TAG);
    private static final String TAG = "JobRescheduleService";
    @VisibleForTesting
    static CountDownLatch latch;

    static void startService(Context context) {
        try {
            WakeLockUtil.startWakefulService(context, new Intent(context, JobRescheduleService.class));
            latch = new CountDownLatch(1);
        } catch (Exception e) {
            CAT.e(e);
        }
    }

    public JobRescheduleService() {
        super(TAG);
    }

    protected void onHandleIntent(Intent intent) {
        boolean exceptionThrown;
        CAT.d("Reschedule service started");
        SystemClock.sleep(10000);
        try {
            JobManager manager = JobManager.create(this);
            int rescheduledCount = 0;
            exceptionThrown = false;
            for (JobRequest request : manager.getJobStorage().getAllJobRequests(null, true)) {
                boolean reschedule;
                if (!request.isTransient()) {
                    reschedule = !manager.getJobProxy(request.getJobApi()).isPlatformJobScheduled(request);
                } else if (manager.getJob(request.getJobId()) == null) {
                    reschedule = true;
                } else {
                    reschedule = false;
                }
                if (reschedule) {
                    try {
                        request.cancelAndEdit().build().schedule();
                    } catch (Exception e) {
                        if (!exceptionThrown) {
                            CAT.e(e);
                            exceptionThrown = true;
                        }
                    } catch (Throwable th) {
                        WakeLockUtil.completeWakefulIntent(intent);
                    }
                    rescheduledCount++;
                }
            }
            CAT.d("Reschedule %d jobs of %d jobs", new Object[]{Integer.valueOf(rescheduledCount), Integer.valueOf(requests.size())});
            latch.countDown();
            WakeLockUtil.completeWakefulIntent(intent);
        } catch (JobManagerCreateException e2) {
            WakeLockUtil.completeWakefulIntent(intent);
        }
    }
}

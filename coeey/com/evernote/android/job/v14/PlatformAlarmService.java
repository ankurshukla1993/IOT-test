package com.evernote.android.job.v14;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.evernote.android.job.JobProxy.Common;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.JobCat;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.vrallev.android.cat.CatLog;

public final class PlatformAlarmService extends Service {
    private static final CatLog CAT = new JobCat("PlatformAlarmService");
    private volatile ExecutorService mExecutorService;
    private volatile int mLastStartId;
    private final Object mMonitor = new Object();
    private volatile Set<Integer> mStartIds;

    static Intent createIntent(Context context, int jobId) {
        Intent intent = new Intent(context, PlatformAlarmService.class);
        intent.putExtra("EXTRA_JOB_ID", jobId);
        return intent;
    }

    public void onCreate() {
        super.onCreate();
        this.mExecutorService = Executors.newCachedThreadPool(Common.COMMON_THREAD_FACTORY);
        this.mStartIds = new HashSet();
    }

    public int onStartCommand(@Nullable final Intent intent, int flags, final int startId) {
        synchronized (this.mMonitor) {
            this.mStartIds.add(Integer.valueOf(startId));
            this.mLastStartId = startId;
        }
        this.mExecutorService.execute(new Runnable() {
            public void run() {
                try {
                    PlatformAlarmService.this.runJob(intent);
                } finally {
                    Common.completeWakefulIntent(intent);
                    PlatformAlarmService.this.stopSelfIfNecessary(startId);
                }
            }
        });
        return 2;
    }

    public void onDestroy() {
        this.mExecutorService.shutdown();
        this.mExecutorService = null;
        synchronized (this.mMonitor) {
            this.mStartIds = null;
            this.mLastStartId = 0;
        }
    }

    public final IBinder onBind(Intent intent) {
        return null;
    }

    private void runJob(Intent intent) {
        if (intent == null) {
            CAT.i("Delivered intent is null");
            return;
        }
        Common common = new Common((Service) this, CAT, intent.getIntExtra("EXTRA_JOB_ID", -1));
        JobRequest request = common.getPendingRequest(true, true);
        if (request != null) {
            common.executeJobRequest(request);
        }
    }

    private void stopSelfIfNecessary(int startId) {
        synchronized (this.mMonitor) {
            Set<Integer> startIds = this.mStartIds;
            if (startIds != null) {
                startIds.remove(Integer.valueOf(startId));
                if (startIds.isEmpty()) {
                    stopSelfResult(this.mLastStartId);
                }
            }
        }
    }
}

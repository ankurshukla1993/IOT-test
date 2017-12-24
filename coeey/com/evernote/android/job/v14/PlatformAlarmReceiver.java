package com.evernote.android.job.v14;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import com.evernote.android.job.JobProxy.Common;
import com.evernote.android.job.util.JobCat;
import net.vrallev.android.cat.CatLog;

public class PlatformAlarmReceiver extends WakefulBroadcastReceiver {
    private static final CatLog CAT = new JobCat("PlatformAlarmReceiver");
    static final String EXTRA_JOB_ID = "EXTRA_JOB_ID";

    static Intent createIntent(Context context, int jobId) {
        return new Intent(context, PlatformAlarmReceiver.class).putExtra(EXTRA_JOB_ID, jobId);
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.hasExtra(EXTRA_JOB_ID)) {
            Common.startWakefulService(context, PlatformAlarmService.createIntent(context, intent.getIntExtra(EXTRA_JOB_ID, -1)));
        }
    }
}

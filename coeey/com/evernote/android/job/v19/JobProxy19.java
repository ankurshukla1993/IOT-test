package com.evernote.android.job.v19;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import com.evernote.android.job.JobProxy.Common;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.JobUtil;
import com.evernote.android.job.v14.JobProxy14;

@TargetApi(19)
public class JobProxy19 extends JobProxy14 {
    private static final String TAG = "JobProxy19";

    public JobProxy19(Context context) {
        super(context, TAG);
    }

    protected void plantOneOffInexact(JobRequest request, AlarmManager alarmManager, PendingIntent pendingIntent) {
        alarmManager.setWindow(1, System.currentTimeMillis() + Common.getStartMs(request), Common.getEndMs(request) - Common.getStartMs(request), pendingIntent);
        this.mCat.d("Schedule alarm, %s, start %s, end %s", new Object[]{request, JobUtil.timeToString(Common.getStartMs(request)), JobUtil.timeToString(Common.getEndMs(request))});
    }

    protected void plantOneOffFlexSupport(JobRequest request, AlarmManager alarmManager, PendingIntent pendingIntent) {
        alarmManager.setWindow(1, System.currentTimeMillis() + Common.getStartMsSupportFlex(request), Common.getEndMsSupportFlex(request) - Common.getStartMsSupportFlex(request), pendingIntent);
        this.mCat.d("Scheduled repeating alarm (flex support), %s, start %s, end %s, flex %s", new Object[]{request, JobUtil.timeToString(Common.getStartMsSupportFlex(request)), JobUtil.timeToString(Common.getEndMsSupportFlex(request)), JobUtil.timeToString(request.getFlexMs())});
    }
}

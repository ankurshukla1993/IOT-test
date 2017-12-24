package com.evernote.android.job.v14;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import com.evernote.android.job.JobProxy;
import com.evernote.android.job.JobProxy.Common;
import com.evernote.android.job.JobRequest;
import com.evernote.android.job.util.JobCat;
import com.evernote.android.job.util.JobUtil;
import com.google.common.primitives.Ints;
import net.vrallev.android.cat.CatLog;

public class JobProxy14 implements JobProxy {
    private static final String TAG = "JobProxy14";
    private AlarmManager mAlarmManager;
    protected final CatLog mCat;
    protected final Context mContext;

    public JobProxy14(Context context) {
        this(context, TAG);
    }

    protected JobProxy14(Context context, String logTag) {
        this.mContext = context;
        this.mCat = new JobCat(logTag);
    }

    public void plantOneOff(JobRequest request) {
        PendingIntent pendingIntent = getPendingIntent(request, false);
        AlarmManager alarmManager = getAlarmManager();
        if (alarmManager != null) {
            try {
                if (request.isExact()) {
                    plantOneOffExact(request, alarmManager, pendingIntent);
                } else {
                    plantOneOffInexact(request, alarmManager, pendingIntent);
                }
            } catch (Exception e) {
                this.mCat.e(e);
            }
        }
    }

    protected void plantOneOffInexact(JobRequest request, AlarmManager alarmManager, PendingIntent pendingIntent) {
        alarmManager.set(1, getTriggerAtMillis(request), pendingIntent);
        logScheduled(request);
    }

    protected void plantOneOffExact(JobRequest request, AlarmManager alarmManager, PendingIntent pendingIntent) {
        long triggerAtMillis = getTriggerAtMillis(request);
        if (VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(0, triggerAtMillis, pendingIntent);
        } else if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, triggerAtMillis, pendingIntent);
        } else {
            alarmManager.set(0, triggerAtMillis, pendingIntent);
        }
        logScheduled(request);
    }

    protected void plantOneOffFlexSupport(JobRequest request, AlarmManager alarmManager, PendingIntent pendingIntent) {
        alarmManager.set(1, System.currentTimeMillis() + Common.getAverageDelayMsSupportFlex(request), pendingIntent);
        this.mCat.d("Scheduled repeating alarm (flex support), %s, interval %s, flex %s", new Object[]{request, JobUtil.timeToString(request.getIntervalMs()), JobUtil.timeToString(request.getFlexMs())});
    }

    protected long getTriggerAtMillis(JobRequest request) {
        return System.currentTimeMillis() + Common.getAverageDelayMs(request);
    }

    private void logScheduled(JobRequest request) {
        this.mCat.d("Scheduled alarm, %s, delay %s, exact %b, reschedule count %d", new Object[]{request, JobUtil.timeToString(Common.getAverageDelayMs(request)), Boolean.valueOf(request.isExact()), Integer.valueOf(Common.getRescheduleCount(request))});
    }

    public void plantPeriodic(JobRequest request) {
        PendingIntent pendingIntent = getPendingIntent(request, true);
        AlarmManager alarmManager = getAlarmManager();
        if (alarmManager != null) {
            alarmManager.setRepeating(0, System.currentTimeMillis() + request.getIntervalMs(), request.getIntervalMs(), pendingIntent);
        }
        this.mCat.d("Scheduled repeating alarm, %s, interval %s", new Object[]{request, JobUtil.timeToString(request.getIntervalMs())});
    }

    public void plantPeriodicFlexSupport(JobRequest request) {
        PendingIntent pendingIntent = getPendingIntent(request, false);
        AlarmManager alarmManager = getAlarmManager();
        if (alarmManager != null) {
            try {
                plantOneOffFlexSupport(request, alarmManager, pendingIntent);
            } catch (Exception e) {
                this.mCat.e(e);
            }
        }
    }

    public void cancel(int jobId) {
        AlarmManager alarmManager = getAlarmManager();
        if (alarmManager != null) {
            try {
                alarmManager.cancel(getPendingIntent(jobId, createPendingIntentFlags(true)));
                alarmManager.cancel(getPendingIntent(jobId, createPendingIntentFlags(false)));
            } catch (Exception e) {
                this.mCat.e(e);
            }
        }
    }

    public boolean isPlatformJobScheduled(JobRequest request) {
        return getPendingIntent(request, 536870912) != null;
    }

    protected int createPendingIntentFlags(boolean repeating) {
        if (repeating) {
            return 134217728;
        }
        return 134217728 | Ints.MAX_POWER_OF_TWO;
    }

    protected PendingIntent getPendingIntent(JobRequest request, boolean repeating) {
        return getPendingIntent(request, createPendingIntentFlags(repeating));
    }

    protected PendingIntent getPendingIntent(JobRequest request, int flags) {
        return getPendingIntent(request.getJobId(), flags);
    }

    protected PendingIntent getPendingIntent(int jobId, int flags) {
        try {
            return PendingIntent.getBroadcast(this.mContext, jobId, PlatformAlarmReceiver.createIntent(this.mContext, jobId), flags);
        } catch (Exception e) {
            this.mCat.e(e);
            return null;
        }
    }

    @Nullable
    protected AlarmManager getAlarmManager() {
        if (this.mAlarmManager == null) {
            this.mAlarmManager = (AlarmManager) this.mContext.getSystemService(NotificationCompat.CATEGORY_ALARM);
        }
        if (this.mAlarmManager == null) {
            this.mCat.e("AlarmManager is null");
        }
        return this.mAlarmManager;
    }
}

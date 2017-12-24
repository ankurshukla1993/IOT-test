package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.support.v4.app.NotificationCompat;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.util.zzd;

public final class zzcki extends zzcii {
    private final AlarmManager zzdsw = ((AlarmManager) getContext().getSystemService(NotificationCompat.CATEGORY_ALARM));
    private Integer zzdsx;
    private final zzcfp zzjgl;

    protected zzcki(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
        this.zzjgl = new zzckj(this, com_google_android_gms_internal_zzchj);
    }

    private final int getJobId() {
        if (this.zzdsx == null) {
            String str = "measurement";
            String valueOf = String.valueOf(getContext().getPackageName());
            this.zzdsx = Integer.valueOf((valueOf.length() != 0 ? str.concat(valueOf) : new String(str)).hashCode());
        }
        return this.zzdsx.intValue();
    }

    @TargetApi(24)
    private final void zzbal() {
        JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
        zzawm().zzayx().zzj("Cancelling job. JobID", Integer.valueOf(getJobId()));
        jobScheduler.cancel(getJobId());
    }

    private final PendingIntent zzyu() {
        Intent className = new Intent().setClassName(getContext(), "com.google.android.gms.measurement.AppMeasurementReceiver");
        className.setAction("com.google.android.gms.measurement.UPLOAD");
        return PendingIntent.getBroadcast(getContext(), 0, className, 0);
    }

    public final void cancel() {
        zzwu();
        this.zzdsw.cancel(zzyu());
        this.zzjgl.cancel();
        if (VERSION.SDK_INT >= 24) {
            zzbal();
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ void zzavw() {
        super.zzavw();
    }

    public final /* bridge */ /* synthetic */ void zzavx() {
        super.zzavx();
    }

    public final /* bridge */ /* synthetic */ zzcfa zzavy() {
        return super.zzavy();
    }

    public final /* bridge */ /* synthetic */ zzcfh zzavz() {
        return super.zzavz();
    }

    public final /* bridge */ /* synthetic */ zzcik zzawa() {
        return super.zzawa();
    }

    public final /* bridge */ /* synthetic */ zzcge zzawb() {
        return super.zzawb();
    }

    public final /* bridge */ /* synthetic */ zzcfr zzawc() {
        return super.zzawc();
    }

    public final /* bridge */ /* synthetic */ zzcjd zzawd() {
        return super.zzawd();
    }

    public final /* bridge */ /* synthetic */ zzciz zzawe() {
        return super.zzawe();
    }

    public final /* bridge */ /* synthetic */ zzcgf zzawf() {
        return super.zzawf();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzawg() {
        return super.zzawg();
    }

    public final /* bridge */ /* synthetic */ zzcgh zzawh() {
        return super.zzawh();
    }

    public final /* bridge */ /* synthetic */ zzckn zzawi() {
        return super.zzawi();
    }

    public final /* bridge */ /* synthetic */ zzchd zzawj() {
        return super.zzawj();
    }

    public final /* bridge */ /* synthetic */ zzckc zzawk() {
        return super.zzawk();
    }

    public final /* bridge */ /* synthetic */ zzche zzawl() {
        return super.zzawl();
    }

    public final /* bridge */ /* synthetic */ zzcgj zzawm() {
        return super.zzawm();
    }

    public final /* bridge */ /* synthetic */ zzcgu zzawn() {
        return super.zzawn();
    }

    public final /* bridge */ /* synthetic */ zzcfk zzawo() {
        return super.zzawo();
    }

    protected final boolean zzaxn() {
        this.zzdsw.cancel(zzyu());
        if (VERSION.SDK_INT >= 24) {
            zzbal();
        }
        return false;
    }

    public final void zzr(long j) {
        zzwu();
        if (!zzcha.zzbi(getContext())) {
            zzawm().zzayw().log("Receiver not registered/enabled");
        }
        if (!zzcjx.zzk(getContext(), false)) {
            zzawm().zzayw().log("Service not registered/enabled");
        }
        cancel();
        long elapsedRealtime = zzwh().elapsedRealtime() + j;
        if (j < Math.max(0, ((Long) zzcfz.zziyf.get()).longValue()) && !this.zzjgl.zzdr()) {
            zzawm().zzayx().log("Scheduling upload with DelayedRunnable");
            this.zzjgl.zzr(j);
        }
        if (VERSION.SDK_INT >= 24) {
            zzawm().zzayx().log("Scheduling upload with JobScheduler");
            JobScheduler jobScheduler = (JobScheduler) getContext().getSystemService("jobscheduler");
            Builder builder = new Builder(getJobId(), new ComponentName(getContext(), "com.google.android.gms.measurement.AppMeasurementJobService"));
            builder.setMinimumLatency(j);
            builder.setOverrideDeadline(j << 1);
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "com.google.android.gms.measurement.UPLOAD");
            builder.setExtras(persistableBundle);
            JobInfo build = builder.build();
            zzawm().zzayx().zzj("Scheduling job. JobID", Integer.valueOf(getJobId()));
            jobScheduler.schedule(build);
            return;
        }
        zzawm().zzayx().log("Scheduling upload with AlarmManager");
        this.zzdsw.setInexactRepeating(2, elapsedRealtime, Math.max(((Long) zzcfz.zziya.get()).longValue(), j), zzyu());
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }
}

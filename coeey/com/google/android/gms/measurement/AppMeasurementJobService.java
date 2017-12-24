package com.google.android.gms.measurement;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.internal.zzcjx;
import com.google.android.gms.internal.zzckb;

@TargetApi(24)
public final class AppMeasurementJobService extends JobService implements zzckb {
    private zzcjx<AppMeasurementJobService> zzitv;

    private final zzcjx<AppMeasurementJobService> zzavv() {
        if (this.zzitv == null) {
            this.zzitv = new zzcjx(this);
        }
        return this.zzitv;
    }

    public final boolean callServiceStopSelfResult(int i) {
        throw new UnsupportedOperationException();
    }

    @MainThread
    public final void onCreate() {
        super.onCreate();
        zzavv().onCreate();
    }

    @MainThread
    public final void onDestroy() {
        zzavv().onDestroy();
        super.onDestroy();
    }

    @MainThread
    public final void onRebind(Intent intent) {
        zzavv().onRebind(intent);
    }

    public final boolean onStartJob(JobParameters jobParameters) {
        return zzavv().onStartJob(jobParameters);
    }

    public final boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        return zzavv().onUnbind(intent);
    }

    @TargetApi(24)
    public final void zza(JobParameters jobParameters, boolean z) {
        jobFinished(jobParameters, false);
    }

    public final void zzm(Intent intent) {
    }
}

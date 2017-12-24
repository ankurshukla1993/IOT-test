package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.support.annotation.MainThread;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.internal.zzbq;

public final class zzcjx<T extends Context & zzckb> {
    private final T zzdvw;

    public zzcjx(T t) {
        zzbq.checkNotNull(t);
        this.zzdvw = t;
    }

    private final zzcgj zzawm() {
        return zzchj.zzdu(this.zzdvw).zzawm();
    }

    private final void zzk(Runnable runnable) {
        zzchj zzdu = zzchj.zzdu(this.zzdvw);
        zzdu.zzawm();
        zzdu.zzawl().zzg(new zzcka(this, zzdu, runnable));
    }

    public static boolean zzk(Context context, boolean z) {
        zzbq.checkNotNull(context);
        return VERSION.SDK_INT >= 24 ? zzckn.zzt(context, "com.google.android.gms.measurement.AppMeasurementJobService") : zzckn.zzt(context, "com.google.android.gms.measurement.AppMeasurementService");
    }

    @MainThread
    public final IBinder onBind(Intent intent) {
        if (intent == null) {
            zzawm().zzayr().log("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzcho(zzchj.zzdu(this.zzdvw));
        }
        zzawm().zzayt().zzj("onBind received unknown action", action);
        return null;
    }

    @MainThread
    public final void onCreate() {
        zzchj.zzdu(this.zzdvw).zzawm().zzayx().log("Local AppMeasurementService is starting up");
    }

    @MainThread
    public final void onDestroy() {
        zzchj.zzdu(this.zzdvw).zzawm().zzayx().log("Local AppMeasurementService is shutting down");
    }

    @MainThread
    public final void onRebind(Intent intent) {
        if (intent == null) {
            zzawm().zzayr().log("onRebind called with null intent");
            return;
        }
        zzawm().zzayx().zzj("onRebind called. action", intent.getAction());
    }

    @MainThread
    public final int onStartCommand(Intent intent, int i, int i2) {
        zzcgj zzawm = zzchj.zzdu(this.zzdvw).zzawm();
        if (intent == null) {
            zzawm.zzayt().log("AppMeasurementService started with null intent");
        } else {
            String action = intent.getAction();
            zzawm.zzayx().zze("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
            if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
                zzk(new zzcjy(this, i2, zzawm, intent));
            }
        }
        return 2;
    }

    @TargetApi(24)
    @MainThread
    public final boolean onStartJob(JobParameters jobParameters) {
        zzcgj zzawm = zzchj.zzdu(this.zzdvw).zzawm();
        String string = jobParameters.getExtras().getString(NativeProtocol.WEB_DIALOG_ACTION);
        zzawm.zzayx().zzj("Local AppMeasurementJobService called. action", string);
        if ("com.google.android.gms.measurement.UPLOAD".equals(string)) {
            zzk(new zzcjz(this, zzawm, jobParameters));
        }
        return true;
    }

    @MainThread
    public final boolean onUnbind(Intent intent) {
        if (intent == null) {
            zzawm().zzayr().log("onUnbind called with null intent");
        } else {
            zzawm().zzayx().zzj("onUnbind called for intent. action", intent.getAction());
        }
        return true;
    }

    final /* synthetic */ void zza(int i, zzcgj com_google_android_gms_internal_zzcgj, Intent intent) {
        if (((zzckb) this.zzdvw).callServiceStopSelfResult(i)) {
            com_google_android_gms_internal_zzcgj.zzayx().zzj("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzawm().zzayx().log("Completed wakeful intent.");
            ((zzckb) this.zzdvw).zzm(intent);
        }
    }

    final /* synthetic */ void zza(zzcgj com_google_android_gms_internal_zzcgj, JobParameters jobParameters) {
        com_google_android_gms_internal_zzcgj.zzayx().log("AppMeasurementJobService processed last upload request.");
        ((zzckb) this.zzdvw).zza(jobParameters, false);
    }
}

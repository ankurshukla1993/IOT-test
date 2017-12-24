package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.WorkerThread;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.google.android.gms.common.util.zzd;

public final class zzckc extends zzcii {
    private Handler mHandler;
    private long zzjgh = zzwh().elapsedRealtime();
    private final zzcfp zzjgi = new zzckd(this, this.zzitk);
    private final zzcfp zzjgj = new zzcke(this, this.zzitk);

    zzckc(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    private final void zzbaj() {
        synchronized (this) {
            if (this.mHandler == null) {
                this.mHandler = new Handler(Looper.getMainLooper());
            }
        }
    }

    @WorkerThread
    private final void zzbak() {
        zzut();
        zzbr(false);
        zzavy().zzai(zzwh().elapsedRealtime());
    }

    @WorkerThread
    private final void zzbd(long j) {
        zzut();
        zzbaj();
        this.zzjgi.cancel();
        this.zzjgj.cancel();
        zzawm().zzayx().zzj("Activity resumed, time", Long.valueOf(j));
        this.zzjgh = j;
        if (zzwh().currentTimeMillis() - zzawn().zzjak.get() > zzawn().zzjam.get()) {
            zzawn().zzjal.set(true);
            zzawn().zzjan.set(0);
        }
        if (zzawn().zzjal.get()) {
            this.zzjgi.zzr(Math.max(0, zzawn().zzjaj.get() - zzawn().zzjan.get()));
        } else {
            this.zzjgj.zzr(Math.max(0, 3600000 - zzawn().zzjan.get()));
        }
    }

    @WorkerThread
    private final void zzbe(long j) {
        zzut();
        zzbaj();
        this.zzjgi.cancel();
        this.zzjgj.cancel();
        zzawm().zzayx().zzj("Activity paused, time", Long.valueOf(j));
        if (this.zzjgh != 0) {
            zzawn().zzjan.set(zzawn().zzjan.get() + (j - this.zzjgh));
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
        return false;
    }

    @WorkerThread
    public final boolean zzbr(boolean z) {
        zzut();
        zzwu();
        long elapsedRealtime = zzwh().elapsedRealtime();
        zzawn().zzjam.set(zzwh().currentTimeMillis());
        long j = elapsedRealtime - this.zzjgh;
        if (z || j >= 1000) {
            zzawn().zzjan.set(j);
            zzawm().zzayx().zzj("Recording user engagement, ms", Long.valueOf(j));
            Bundle bundle = new Bundle();
            bundle.putLong("_et", j);
            zzciz.zza(zzawe().zzbac(), bundle);
            zzawa().zzc(ReactScrollViewHelper.AUTO, "_e", bundle);
            this.zzjgh = elapsedRealtime;
            this.zzjgj.cancel();
            this.zzjgj.zzr(Math.max(0, 3600000 - zzawn().zzjan.get()));
            return true;
        }
        zzawm().zzayx().zzj("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(j));
        return false;
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }
}

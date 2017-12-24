package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement$zzb;
import java.util.Map;

public final class zzcfa extends zzcih {
    private final Map<String, Long> zzitw = new ArrayMap();
    private final Map<String, Integer> zzitx = new ArrayMap();
    private long zzity;

    public zzcfa(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    @WorkerThread
    private final void zza(long j, AppMeasurement$zzb appMeasurement$zzb) {
        if (appMeasurement$zzb == null) {
            zzawm().zzayx().log("Not logging ad exposure. No active activity");
        } else if (j < 1000) {
            zzawm().zzayx().zzj("Not logging ad exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putLong("_xt", j);
            zzciz.zza(appMeasurement$zzb, bundle);
            zzawa().zzc("am", "_xa", bundle);
        }
    }

    @WorkerThread
    private final void zza(String str, long j, AppMeasurement$zzb appMeasurement$zzb) {
        if (appMeasurement$zzb == null) {
            zzawm().zzayx().log("Not logging ad unit exposure. No active activity");
        } else if (j < 1000) {
            zzawm().zzayx().zzj("Not logging ad unit exposure. Less than 1000 ms. exposure", Long.valueOf(j));
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("_ai", str);
            bundle.putLong("_xt", j);
            zzciz.zza(appMeasurement$zzb, bundle);
            zzawa().zzc("am", "_xu", bundle);
        }
    }

    @WorkerThread
    private final void zzaj(long j) {
        for (String put : this.zzitw.keySet()) {
            this.zzitw.put(put, Long.valueOf(j));
        }
        if (!this.zzitw.isEmpty()) {
            this.zzity = j;
        }
    }

    @WorkerThread
    private final void zze(String str, long j) {
        zzut();
        zzbq.zzgh(str);
        if (this.zzitx.isEmpty()) {
            this.zzity = j;
        }
        Integer num = (Integer) this.zzitx.get(str);
        if (num != null) {
            this.zzitx.put(str, Integer.valueOf(num.intValue() + 1));
        } else if (this.zzitx.size() >= 100) {
            zzawm().zzayt().log("Too many ads visible");
        } else {
            this.zzitx.put(str, Integer.valueOf(1));
            this.zzitw.put(str, Long.valueOf(j));
        }
    }

    @WorkerThread
    private final void zzf(String str, long j) {
        zzut();
        zzbq.zzgh(str);
        Integer num = (Integer) this.zzitx.get(str);
        if (num != null) {
            AppMeasurement$zzb zzbac = zzawe().zzbac();
            int intValue = num.intValue() - 1;
            if (intValue == 0) {
                this.zzitx.remove(str);
                Long l = (Long) this.zzitw.get(str);
                if (l == null) {
                    zzawm().zzayr().log("First ad unit exposure time was never set");
                } else {
                    long longValue = j - l.longValue();
                    this.zzitw.remove(str);
                    zza(str, longValue, zzbac);
                }
                if (!this.zzitx.isEmpty()) {
                    return;
                }
                if (this.zzity == 0) {
                    zzawm().zzayr().log("First ad exposure time was never set");
                    return;
                }
                zza(j - this.zzity, zzbac);
                this.zzity = 0;
                return;
            }
            this.zzitx.put(str, Integer.valueOf(intValue));
            return;
        }
        zzawm().zzayr().zzj("Call to endAdUnitExposure for unknown ad unit id", str);
    }

    public final void beginAdUnitExposure(String str) {
        if (str == null || str.length() == 0) {
            zzawm().zzayr().log("Ad unit id must be a non-empty string");
            return;
        }
        zzawl().zzg(new zzcfb(this, str, zzwh().elapsedRealtime()));
    }

    public final void endAdUnitExposure(String str) {
        if (str == null || str.length() == 0) {
            zzawm().zzayr().log("Ad unit id must be a non-empty string");
            return;
        }
        zzawl().zzg(new zzcfc(this, str, zzwh().elapsedRealtime()));
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    public final void zzai(long j) {
        AppMeasurement$zzb zzbac = zzawe().zzbac();
        for (String str : this.zzitw.keySet()) {
            zza(str, j - ((Long) this.zzitw.get(str)).longValue(), zzbac);
        }
        if (!this.zzitw.isEmpty()) {
            zza(j - this.zzity, zzbac);
        }
        zzaj(j);
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

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }
}

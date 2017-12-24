package com.google.android.gms.internal;

import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbq;

public final class zzcgy {
    private final long zzdvt;
    private /* synthetic */ zzcgu zzjar;
    private String zzjat;
    private final String zzjau;
    private final String zzjav;

    private zzcgy(zzcgu com_google_android_gms_internal_zzcgu, String str, long j) {
        this.zzjar = com_google_android_gms_internal_zzcgu;
        zzbq.zzgh(str);
        zzbq.checkArgument(j > 0);
        this.zzjat = String.valueOf(str).concat(":start");
        this.zzjau = String.valueOf(str).concat(":count");
        this.zzjav = String.valueOf(str).concat(":value");
        this.zzdvt = j;
    }

    @WorkerThread
    private final void zzzr() {
        this.zzjar.zzut();
        long currentTimeMillis = this.zzjar.zzwh().currentTimeMillis();
        Editor edit = this.zzjar.zzayz().edit();
        edit.remove(this.zzjau);
        edit.remove(this.zzjav);
        edit.putLong(this.zzjat, currentTimeMillis);
        edit.apply();
    }

    @WorkerThread
    private final long zzzt() {
        return this.zzjar.zzayz().getLong(this.zzjat, 0);
    }

    @WorkerThread
    public final void zzg(String str, long j) {
        this.zzjar.zzut();
        if (zzzt() == 0) {
            zzzr();
        }
        if (str == null) {
            str = "";
        }
        long j2 = this.zzjar.zzayz().getLong(this.zzjau, 0);
        if (j2 <= 0) {
            Editor edit = this.zzjar.zzayz().edit();
            edit.putString(this.zzjav, str);
            edit.putLong(this.zzjau, 1);
            edit.apply();
            return;
        }
        Object obj = (this.zzjar.zzawi().zzban().nextLong() & Long.MAX_VALUE) < Long.MAX_VALUE / (j2 + 1) ? 1 : null;
        Editor edit2 = this.zzjar.zzayz().edit();
        if (obj != null) {
            edit2.putString(this.zzjav, str);
        }
        edit2.putLong(this.zzjau, j2 + 1);
        edit2.apply();
    }

    @WorkerThread
    public final Pair<String, Long> zzzs() {
        this.zzjar.zzut();
        this.zzjar.zzut();
        long zzzt = zzzt();
        if (zzzt == 0) {
            zzzr();
            zzzt = 0;
        } else {
            zzzt = Math.abs(zzzt - this.zzjar.zzwh().currentTimeMillis());
        }
        if (zzzt < this.zzdvt) {
            return null;
        }
        if (zzzt > (this.zzdvt << 1)) {
            zzzr();
            return null;
        }
        String string = this.zzjar.zzayz().getString(this.zzjav, null);
        long j = this.zzjar.zzayz().getLong(this.zzjau, 0);
        zzzr();
        return (string == null || j <= 0) ? zzcgu.zzizu : new Pair(string, Long.valueOf(j));
    }
}

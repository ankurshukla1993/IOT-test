package com.google.android.gms.internal;

import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import java.util.ArrayList;
import java.util.TimeZone;

public final class zzbcx {
    private String zzfgj;
    private int zzfgk;
    private String zzfgl;
    private String zzfgm;
    private int zzfgo;
    private final zzbcz zzfgt;
    private ArrayList<Integer> zzfgu;
    private ArrayList<String> zzfgv;
    private ArrayList<Integer> zzfgw;
    private ArrayList<zzcsv> zzfgx;
    private ArrayList<byte[]> zzfgy;
    private boolean zzfgz;
    private final zzfii zzfha;
    private boolean zzfhb;
    private /* synthetic */ zzbcv zzfhc;

    private zzbcx(zzbcv com_google_android_gms_internal_zzbcv, byte[] bArr) {
        this(com_google_android_gms_internal_zzbcv, bArr, null);
    }

    private zzbcx(zzbcv com_google_android_gms_internal_zzbcv, byte[] bArr, zzbcz com_google_android_gms_internal_zzbcz) {
        this.zzfhc = com_google_android_gms_internal_zzbcv;
        this.zzfgk = zzbcv.zza(this.zzfhc);
        this.zzfgj = zzbcv.zzb(this.zzfhc);
        zzbcv com_google_android_gms_internal_zzbcv2 = this.zzfhc;
        this.zzfgl = null;
        com_google_android_gms_internal_zzbcv2 = this.zzfhc;
        this.zzfgm = null;
        this.zzfgo = 0;
        this.zzfgu = null;
        this.zzfgv = null;
        this.zzfgw = null;
        this.zzfgx = null;
        this.zzfgy = null;
        this.zzfgz = true;
        this.zzfha = new zzfii();
        this.zzfhb = false;
        this.zzfgl = null;
        this.zzfgm = null;
        this.zzfha.zzpkg = zzbcv.zzc(com_google_android_gms_internal_zzbcv).currentTimeMillis();
        this.zzfha.zzpkh = zzbcv.zzc(com_google_android_gms_internal_zzbcv).elapsedRealtime();
        zzfii com_google_android_gms_internal_zzfii = this.zzfha;
        zzbcv.zzd(com_google_android_gms_internal_zzbcv);
        com_google_android_gms_internal_zzfii.zzpks = (long) (TimeZone.getDefault().getOffset(this.zzfha.zzpkg) / 1000);
        if (bArr != null) {
            this.zzfha.zzpkn = bArr;
        }
        this.zzfgt = null;
    }

    public final void zzbf() {
        if (this.zzfhb) {
            throw new IllegalStateException("do not reuse LogEventBuilder");
        }
        this.zzfhb = true;
        zzbde com_google_android_gms_internal_zzbde = new zzbde(new zzbdt(zzbcv.zzf(this.zzfhc), zzbcv.zzg(this.zzfhc), this.zzfgk, this.zzfgj, this.zzfgl, this.zzfgm, zzbcv.zze(this.zzfhc), 0), this.zzfha, null, null, zzbcv.zzc(null), null, zzbcv.zzc(null), null, null, this.zzfgz);
        zzbdt com_google_android_gms_internal_zzbdt = com_google_android_gms_internal_zzbde.zzfhf;
        if (zzbcv.zzh(this.zzfhc).zzg(com_google_android_gms_internal_zzbdt.zzfgj, com_google_android_gms_internal_zzbdt.zzfgk)) {
            zzbcv.zzi(this.zzfhc).zza(com_google_android_gms_internal_zzbde);
        } else {
            PendingResults.immediatePendingResult(Status.zzfko);
        }
    }
}

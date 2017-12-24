package com.google.android.gms.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import java.util.Iterator;

public final class zzcfs {
    final String mAppId;
    final String mName;
    private String mOrigin;
    final long zzffr;
    final long zziwn;
    final zzcfu zziwo;

    zzcfs(zzchj com_google_android_gms_internal_zzchj, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzbq.zzgh(str2);
        zzbq.zzgh(str3);
        this.mAppId = str2;
        this.mName = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.mOrigin = str;
        this.zzffr = j;
        this.zziwn = j2;
        if (this.zziwn != 0 && this.zziwn > this.zzffr) {
            com_google_android_gms_internal_zzchj.zzawm().zzayt().zzj("Event created with reverse previous/current timestamps. appId", zzcgj.zzje(str2));
        }
        this.zziwo = zza(com_google_android_gms_internal_zzchj, bundle);
    }

    private zzcfs(zzchj com_google_android_gms_internal_zzchj, String str, String str2, String str3, long j, long j2, zzcfu com_google_android_gms_internal_zzcfu) {
        zzbq.zzgh(str2);
        zzbq.zzgh(str3);
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfu);
        this.mAppId = str2;
        this.mName = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.mOrigin = str;
        this.zzffr = j;
        this.zziwn = j2;
        if (this.zziwn != 0 && this.zziwn > this.zzffr) {
            com_google_android_gms_internal_zzchj.zzawm().zzayt().zzj("Event created with reverse previous/current timestamps. appId", zzcgj.zzje(str2));
        }
        this.zziwo = com_google_android_gms_internal_zzcfu;
    }

    private static zzcfu zza(zzchj com_google_android_gms_internal_zzchj, Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return new zzcfu(new Bundle());
        }
        Bundle bundle2 = new Bundle(bundle);
        Iterator it = bundle2.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                com_google_android_gms_internal_zzchj.zzawm().zzayr().log("Param name can't be null");
                it.remove();
            } else {
                Object zzk = com_google_android_gms_internal_zzchj.zzawi().zzk(str, bundle2.get(str));
                if (zzk == null) {
                    com_google_android_gms_internal_zzchj.zzawm().zzayt().zzj("Param value can't be null", com_google_android_gms_internal_zzchj.zzawh().zzjc(str));
                    it.remove();
                } else {
                    com_google_android_gms_internal_zzchj.zzawi().zza(bundle2, str, zzk);
                }
            }
        }
        return new zzcfu(bundle2);
    }

    public final String toString() {
        String str = this.mAppId;
        String str2 = this.mName;
        String valueOf = String.valueOf(this.zziwo);
        return new StringBuilder(((String.valueOf(str).length() + 33) + String.valueOf(str2).length()) + String.valueOf(valueOf).length()).append("Event{appId='").append(str).append("', name='").append(str2).append("', params=").append(valueOf).append("}").toString();
    }

    final zzcfs zza(zzchj com_google_android_gms_internal_zzchj, long j) {
        return new zzcfs(com_google_android_gms_internal_zzchj, this.mOrigin, this.mAppId, this.mName, this.zzffr, j, this.zziwo);
    }
}

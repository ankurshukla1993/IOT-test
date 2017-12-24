package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzdb;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import java.util.Map;

public class zzdtp<O extends zzdtq> {
    private static zzbfd zzdze = new zzbfd("BiChannelGoogleApi", "FirebaseAuth: ");
    private GoogleApi<O> zzlzr;
    private GoogleApi<O> zzlzs;
    private zzdts zzlzt;
    private O zzlzu;
    private Integer zzlzv;
    private Integer zzlzw;
    private zzdtv zzlzx;

    private zzdtp(@NonNull Context context, @NonNull Api<O> api, @NonNull O o, @NonNull zzdb com_google_android_gms_common_api_internal_zzdb) {
        this.zzlzu = o;
        this.zzlzw = Integer.valueOf(DynamiteModule.zzac(context, "com.google.android.gms.firebase_auth"));
        this.zzlzv = Integer.valueOf(DynamiteModule.zzab(context, "com.google.firebase.auth"));
        if (this.zzlzw.intValue() != 0) {
            zzdtq com_google_android_gms_internal_zzdtq = (zzdtq) this.zzlzu.clone();
            com_google_android_gms_internal_zzdtq.zzlzy = false;
            this.zzlzr = new zzdtt(context, api, com_google_android_gms_internal_zzdtq, com_google_android_gms_common_api_internal_zzdb);
        } else {
            zzdze.zze("No Gms module; NOT initializing GMS implementation", new Object[0]);
            this.zzlzr = null;
        }
        if (this.zzlzv.intValue() != 0) {
            this.zzlzt = new zzdts(this, context, api, com_google_android_gms_common_api_internal_zzdb);
        } else {
            zzdze.zze("No Fallback module; NOT setting up for lazy initialization", new Object[0]);
        }
    }

    public zzdtp(@NonNull Context context, Api<O> api, O o, zzdb com_google_android_gms_common_api_internal_zzdb, int i, int i2, Map<String, Integer> map) {
        this(context, api, o, com_google_android_gms_common_api_internal_zzdb);
        this.zzlzx = new zzdtr(i, i2, map, this.zzlzw.intValue() != 0);
    }

    private final GoogleApi zzc(zzdtu com_google_android_gms_internal_zzdtu) {
        if (this.zzlzx.zzd(com_google_android_gms_internal_zzdtu)) {
            zzdze.zzf("getGoogleApiForMethod() returned Fallback", new Object[0]);
            if (this.zzlzs == null && this.zzlzt != null) {
                zzdts com_google_android_gms_internal_zzdts = this.zzlzt;
                zzdtq com_google_android_gms_internal_zzdtq = (zzdtq) this.zzlzu.clone();
                com_google_android_gms_internal_zzdtq.zzlzy = true;
                this.zzlzs = com_google_android_gms_internal_zzdts.zza(com_google_android_gms_internal_zzdtq);
            }
            return this.zzlzs;
        }
        zzdze.zzf("getGoogleApiForMethod() returned Gms", new Object[0]);
        return this.zzlzr;
    }

    public final <TResult, A extends zzb> Task<TResult> zza(zzdtu<A, TResult> com_google_android_gms_internal_zzdtu_A__TResult) {
        return zzc(com_google_android_gms_internal_zzdtu_A__TResult).zza(com_google_android_gms_internal_zzdtu_A__TResult);
    }

    public final <TResult, A extends zzb> Task<TResult> zzb(zzdtu<A, TResult> com_google_android_gms_internal_zzdtu_A__TResult) {
        return zzc(com_google_android_gms_internal_zzdtu_A__TResult).zzb(com_google_android_gms_internal_zzdtu_A__TResult);
    }
}

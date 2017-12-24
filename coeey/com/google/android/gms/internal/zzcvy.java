package com.google.android.gms.internal;

import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.Scope;

public final class zzcvy {
    public static final Api<zzcwc> API = new Api("SignIn.API", zzdyi, zzdyh);
    private static zzf<zzcwl> zzdyh = new zzf();
    public static final zza<zzcwl, zzcwc> zzdyi = new zzcvz();
    private static Scope zzeei = new Scope(Scopes.PROFILE);
    private static Scope zzeej = new Scope("email");
    private static Api<Object> zzggh = new Api("SignIn.INTERNAL_API", zzjyy, zzjyx);
    private static zzf<zzcwl> zzjyx = new zzf();
    private static zza<zzcwl, Object> zzjyy = new zzcwa();
}

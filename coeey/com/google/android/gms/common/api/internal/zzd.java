package com.google.android.gms.common.api.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzd extends zzb<Void> {
    private zzct<zzb, ?> zzfkw;
    private zzdp<zzb, ?> zzfkx;

    public zzd(zzcu com_google_android_gms_common_api_internal_zzcu, TaskCompletionSource<Void> taskCompletionSource) {
        super(3, taskCompletionSource);
        this.zzfkw = com_google_android_gms_common_api_internal_zzcu.zzfkw;
        this.zzfkx = com_google_android_gms_common_api_internal_zzcu.zzfkx;
    }

    public final /* bridge */ /* synthetic */ void zza(@NonNull zzah com_google_android_gms_common_api_internal_zzah, boolean z) {
    }

    public final void zzb(zzbr<?> com_google_android_gms_common_api_internal_zzbr_) throws RemoteException {
        this.zzfkw.zzb(com_google_android_gms_common_api_internal_zzbr_.zzahd(), this.zzeay);
        if (this.zzfkw.zzajc() != null) {
            com_google_android_gms_common_api_internal_zzbr_.zzaim().put(this.zzfkw.zzajc(), new zzcu(this.zzfkw, this.zzfkx));
        }
    }

    public final /* bridge */ /* synthetic */ void zzs(@NonNull Status status) {
        super.zzs(status);
    }
}

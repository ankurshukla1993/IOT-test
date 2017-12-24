package com.google.android.gms.internal;

import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

final class zzaug extends zzauc {
    private /* synthetic */ zzauf zzedf;

    zzaug(zzauf com_google_android_gms_internal_zzauf) {
        this.zzedf = com_google_android_gms_internal_zzauf;
    }

    public final void zza(Status status, Credential credential) {
        this.zzedf.setResult(new zzaud(status, credential));
    }

    public final void zze(Status status) {
        this.zzedf.setResult(zzaud.zzf(status));
    }
}

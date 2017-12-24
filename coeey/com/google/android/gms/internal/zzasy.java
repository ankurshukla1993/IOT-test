package com.google.android.gms.internal;

import android.accounts.Account;
import com.google.android.gms.common.api.Status;

final class zzasy extends zzatb {
    private /* synthetic */ zzasx zzdzu;

    zzasy(zzasx com_google_android_gms_internal_zzasx) {
        this.zzdzu = com_google_android_gms_internal_zzasx;
    }

    public final void zzd(Account account) {
        this.zzdzu.setResult(new zzatc(account != null ? Status.zzfko : zzasv.zzdzt, account));
    }
}

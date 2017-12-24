package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.zzats;
import com.google.android.gms.internal.zzatw;

final class zze extends zzb<byte[]> {
    private /* synthetic */ zzatw zzear;

    zze(AccountTransferClient accountTransferClient, zzatw com_google_android_gms_internal_zzatw) {
        this.zzear = com_google_android_gms_internal_zzatw;
        super();
    }

    protected final void zza(zzats com_google_android_gms_internal_zzats) throws RemoteException {
        com_google_android_gms_internal_zzats.zza(new zzf(this, this), this.zzear);
    }
}

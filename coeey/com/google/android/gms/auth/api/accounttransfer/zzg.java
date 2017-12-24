package com.google.android.gms.auth.api.accounttransfer;

import android.os.RemoteException;
import com.google.android.gms.internal.zzato;
import com.google.android.gms.internal.zzats;

final class zzg extends zzb<DeviceMetaData> {
    private /* synthetic */ zzato zzeat;

    zzg(AccountTransferClient accountTransferClient, zzato com_google_android_gms_internal_zzato) {
        this.zzeat = com_google_android_gms_internal_zzato;
        super();
    }

    protected final void zza(zzats com_google_android_gms_internal_zzats) throws RemoteException {
        com_google_android_gms_internal_zzats.zza(new zzh(this, this), this.zzeat);
    }
}

package com.google.android.gms.auth;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.zzeh;
import java.io.IOException;
import java.util.List;

final class zzg implements zzj<List<AccountChangeEvent>> {
    private /* synthetic */ String zzdzi;
    private /* synthetic */ int zzdzj;

    zzg(String str, int i) {
        this.zzdzi = str;
        this.zzdzj = i;
    }

    public final /* synthetic */ Object zzab(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        return ((AccountChangeEventsResponse) zzd.zzp(zzeh.zza(iBinder).zza(new AccountChangeEventsRequest().setAccountName(this.zzdzi).setEventIndex(this.zzdzj)))).getEvents();
    }
}

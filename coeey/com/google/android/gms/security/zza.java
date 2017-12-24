package com.google.android.gms.security;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.zze;
import com.google.android.gms.security.ProviderInstaller.ProviderInstallListener;
import com.ihealth.communication.control.PoProfile;

final class zza extends AsyncTask<Void, Void, Integer> {
    private /* synthetic */ Context val$context;
    private /* synthetic */ ProviderInstallListener zzjyw;

    zza(Context context, ProviderInstallListener providerInstallListener) {
        this.val$context = context;
        this.zzjyw = providerInstallListener;
    }

    private final Integer zzb(Void... voidArr) {
        try {
            ProviderInstaller.installIfNeeded(this.val$context);
            return Integer.valueOf(0);
        } catch (GooglePlayServicesRepairableException e) {
            return Integer.valueOf(e.getConnectionStatusCode());
        } catch (GooglePlayServicesNotAvailableException e2) {
            return Integer.valueOf(e2.errorCode);
        }
    }

    protected final /* synthetic */ Object doInBackground(Object[] objArr) {
        return zzb((Void[]) objArr);
    }

    protected final /* synthetic */ void onPostExecute(Object obj) {
        Integer num = (Integer) obj;
        if (num.intValue() == 0) {
            this.zzjyw.onProviderInstalled();
            return;
        }
        ProviderInstaller.zzjyu;
        this.zzjyw.onProviderInstallFailed(num.intValue(), zze.zza(this.val$context, num.intValue(), PoProfile.PI_PO));
    }
}

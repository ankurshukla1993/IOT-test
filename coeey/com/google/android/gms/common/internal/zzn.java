package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

public final class zzn extends zze {
    private /* synthetic */ zzd zzfwg;
    private IBinder zzfwk;

    @BinderThread
    public zzn(zzd com_google_android_gms_common_internal_zzd, int i, IBinder iBinder, Bundle bundle) {
        this.zzfwg = com_google_android_gms_common_internal_zzd;
        super(com_google_android_gms_common_internal_zzd, i, bundle);
        this.zzfwk = iBinder;
    }

    protected final boolean zzakf() {
        try {
            String interfaceDescriptor = this.zzfwk.getInterfaceDescriptor();
            if (this.zzfwg.zzhg().equals(interfaceDescriptor)) {
                IInterface zzd = this.zzfwg.zzd(this.zzfwk);
                if (zzd == null) {
                    return false;
                }
                if (!this.zzfwg.zza(2, 4, zzd) && !this.zzfwg.zza(3, 4, zzd)) {
                    return false;
                }
                this.zzfwg.zzfwb = null;
                Bundle zzaew = this.zzfwg.zzaew();
                if (this.zzfwg.zzfvx != null) {
                    this.zzfwg.zzfvx.onConnected(zzaew);
                }
                return true;
            }
            String zzhg = this.zzfwg.zzhg();
            Log.e("GmsClient", new StringBuilder((String.valueOf(zzhg).length() + 34) + String.valueOf(interfaceDescriptor).length()).append("service descriptor mismatch: ").append(zzhg).append(" vs. ").append(interfaceDescriptor).toString());
            return false;
        } catch (RemoteException e) {
            Log.w("GmsClient", "service probably died");
            return false;
        }
    }

    protected final void zzj(ConnectionResult connectionResult) {
        if (this.zzfwg.zzfvy != null) {
            this.zzfwg.zzfvy.onConnectionFailed(connectionResult);
        }
        this.zzfwg.onConnectionFailed(connectionResult);
    }
}

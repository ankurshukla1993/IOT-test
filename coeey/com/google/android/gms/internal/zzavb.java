package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyRequest;

public final class zzavb extends zzed implements zzava {
    zzavb(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.internal.IAuthService");
    }

    public final void zza(zzauy com_google_android_gms_internal_zzauy, ProxyRequest proxyRequest) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzauy);
        zzef.zza(zzaz, (Parcelable) proxyRequest);
        zzb(1, zzaz);
    }
}

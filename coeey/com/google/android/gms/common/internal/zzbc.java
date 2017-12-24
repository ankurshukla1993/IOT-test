package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.zzm;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzbc extends zzed implements zzba {
    zzbc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.IGoogleCertificatesApi");
    }

    public final boolean zza(zzm com_google_android_gms_common_zzm, IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_common_zzm);
        zzef.zza(zzaz, (IInterface) iObjectWrapper);
        zzaz = zza(5, zzaz);
        boolean zza = zzef.zza(zzaz);
        zzaz.recycle();
        return zza;
    }
}

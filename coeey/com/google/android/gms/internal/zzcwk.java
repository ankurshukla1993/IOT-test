package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzan;

public final class zzcwk extends zzed implements zzcwj {
    zzcwk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.signin.internal.ISignInService");
    }

    public final void zza(zzan com_google_android_gms_common_internal_zzan, int i, boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_common_internal_zzan);
        zzaz.writeInt(i);
        zzef.zza(zzaz, z);
        zzb(9, zzaz);
    }

    public final void zza(zzcwm com_google_android_gms_internal_zzcwm, zzcwh com_google_android_gms_internal_zzcwh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcwm);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzcwh);
        zzb(12, zzaz);
    }

    public final void zzeh(int i) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeInt(i);
        zzb(7, zzaz);
    }
}

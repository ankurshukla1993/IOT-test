package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

public final class zzaut extends zzed implements zzaus {
    zzaut(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    public final void zza(zzauq com_google_android_gms_internal_zzauq) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzauq);
        zzb(4, zzaz);
    }

    public final void zza(zzauq com_google_android_gms_internal_zzauq, CredentialRequest credentialRequest) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzauq);
        zzef.zza(zzaz, (Parcelable) credentialRequest);
        zzb(1, zzaz);
    }

    public final void zza(zzauq com_google_android_gms_internal_zzauq, zzauo com_google_android_gms_internal_zzauo) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzauq);
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzauo);
        zzb(3, zzaz);
    }

    public final void zza(zzauq com_google_android_gms_internal_zzauq, zzauu com_google_android_gms_internal_zzauu) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzauq);
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzauu);
        zzb(2, zzaz);
    }
}

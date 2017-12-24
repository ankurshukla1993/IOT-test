package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import java.util.List;

public final class zzcgd extends zzed implements zzcgb {
    zzcgd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public final List<zzckk> zza(zzcff com_google_android_gms_internal_zzcff, boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcff);
        zzef.zza(zzaz, z);
        zzaz = zza(7, zzaz);
        List createTypedArrayList = zzaz.createTypedArrayList(zzckk.CREATOR);
        zzaz.recycle();
        return createTypedArrayList;
    }

    public final List<zzcfi> zza(String str, String str2, zzcff com_google_android_gms_internal_zzcff) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcff);
        zzaz = zza(16, zzaz);
        List createTypedArrayList = zzaz.createTypedArrayList(zzcfi.CREATOR);
        zzaz.recycle();
        return createTypedArrayList;
    }

    public final List<zzckk> zza(String str, String str2, String str3, boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzaz.writeString(str3);
        zzef.zza(zzaz, z);
        zzaz = zza(15, zzaz);
        List createTypedArrayList = zzaz.createTypedArrayList(zzckk.CREATOR);
        zzaz.recycle();
        return createTypedArrayList;
    }

    public final List<zzckk> zza(String str, String str2, boolean z, zzcff com_google_android_gms_internal_zzcff) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzef.zza(zzaz, z);
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcff);
        zzaz = zza(14, zzaz);
        List createTypedArrayList = zzaz.createTypedArrayList(zzckk.CREATOR);
        zzaz.recycle();
        return createTypedArrayList;
    }

    public final void zza(long j, String str, String str2, String str3) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeLong(j);
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzaz.writeString(str3);
        zzb(10, zzaz);
    }

    public final void zza(zzcff com_google_android_gms_internal_zzcff) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcff);
        zzb(4, zzaz);
    }

    public final void zza(zzcfi com_google_android_gms_internal_zzcfi, zzcff com_google_android_gms_internal_zzcff) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcfi);
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcff);
        zzb(12, zzaz);
    }

    public final void zza(zzcfx com_google_android_gms_internal_zzcfx, zzcff com_google_android_gms_internal_zzcff) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcfx);
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcff);
        zzb(1, zzaz);
    }

    public final void zza(zzcfx com_google_android_gms_internal_zzcfx, String str, String str2) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcfx);
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzb(5, zzaz);
    }

    public final void zza(zzckk com_google_android_gms_internal_zzckk, zzcff com_google_android_gms_internal_zzcff) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzckk);
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcff);
        zzb(2, zzaz);
    }

    public final byte[] zza(zzcfx com_google_android_gms_internal_zzcfx, String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcfx);
        zzaz.writeString(str);
        zzaz = zza(9, zzaz);
        byte[] createByteArray = zzaz.createByteArray();
        zzaz.recycle();
        return createByteArray;
    }

    public final void zzb(zzcff com_google_android_gms_internal_zzcff) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcff);
        zzb(6, zzaz);
    }

    public final void zzb(zzcfi com_google_android_gms_internal_zzcfi) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcfi);
        zzb(13, zzaz);
    }

    public final String zzc(zzcff com_google_android_gms_internal_zzcff) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcff);
        zzaz = zza(11, zzaz);
        String readString = zzaz.readString();
        zzaz.recycle();
        return readString;
    }

    public final void zzd(zzcff com_google_android_gms_internal_zzcff) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzcff);
        zzb(18, zzaz);
    }

    public final List<zzcfi> zzj(String str, String str2, String str3) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzaz.writeString(str3);
        zzaz = zza(17, zzaz);
        List createTypedArrayList = zzaz.createTypedArrayList(zzcfi.CREATOR);
        zzaz.recycle();
        return createTypedArrayList;
    }
}

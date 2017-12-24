package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.dynamite.DynamiteModule;

public final class zzdxv {
    private static zzdxv zzmec;
    private Context mContext;

    private zzdxv() {
    }

    public static synchronized zzdxv zzbqt() {
        zzdxv com_google_android_gms_internal_zzdxv;
        synchronized (zzdxv.class) {
            if (zzmec == null) {
                zzmec = new zzdxv();
            }
            com_google_android_gms_internal_zzdxv = zzmec;
        }
        return com_google_android_gms_internal_zzdxv;
    }

    public final zzdxt zzbqu() throws zzdxx {
        try {
            DynamiteModule zza = DynamiteModule.zza(this.mContext, DynamiteModule.zzgue, "com.google.android.gms.crash");
            zzbq.checkNotNull(zza);
            IBinder zzgw = zza.zzgw("com.google.firebase.crash.internal.api.FirebaseCrashApiImpl");
            if (zzgw == null) {
                return null;
            }
            IInterface queryLocalInterface = zzgw.queryLocalInterface("com.google.firebase.crash.internal.IFirebaseCrashApi");
            return queryLocalInterface instanceof zzdxt ? (zzdxt) queryLocalInterface : new zzdxu(zzgw);
        } catch (Throwable e) {
            zzf.zza(this.mContext, e);
            throw new zzdxx(e);
        }
    }

    public final void zzce(Context context) {
        this.mContext = context;
    }
}

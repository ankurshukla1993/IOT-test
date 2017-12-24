package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.zzc;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.flags.ModuleDescriptor;

public final class zzbzu {
    private boolean zzaqh = false;
    private zzbzv zzhfz = null;

    public final void initialize(Context context) {
        Throwable e;
        synchronized (this) {
            if (this.zzaqh) {
                return;
            }
            try {
                this.zzhfz = zzbzw.asInterface(DynamiteModule.zza(context, DynamiteModule.zzguh, ModuleDescriptor.MODULE_ID).zzgw("com.google.android.gms.flags.impl.FlagProviderImpl"));
                this.zzhfz.init(zzn.zzy(context));
                this.zzaqh = true;
            } catch (zzc e2) {
                e = e2;
                Log.w("FlagValueProvider", "Failed to initialize flags module.", e);
            } catch (RemoteException e3) {
                e = e3;
                Log.w("FlagValueProvider", "Failed to initialize flags module.", e);
            }
        }
    }

    public final <T> T zzb(zzbzn<T> com_google_android_gms_internal_zzbzn_T) {
        synchronized (this) {
            if (this.zzaqh) {
                return com_google_android_gms_internal_zzbzn_T.zza(this.zzhfz);
            }
            T zzip = com_google_android_gms_internal_zzbzn_T.zzip();
            return zzip;
        }
    }
}

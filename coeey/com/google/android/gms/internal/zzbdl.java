package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public final class zzbdl extends zzab<zzbdp> {
    public zzbdl(Context context, Looper looper, zzr com_google_android_gms_common_internal_zzr, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 40, com_google_android_gms_common_internal_zzr, connectionCallbacks, onConnectionFailedListener);
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.clearcut.internal.IClearcutLoggerService");
        return queryLocalInterface instanceof zzbdp ? (zzbdp) queryLocalInterface : new zzbdq(iBinder);
    }

    protected final String zzhf() {
        return "com.google.android.gms.clearcut.service.START";
    }

    protected final String zzhg() {
        return "com.google.android.gms.clearcut.internal.IClearcutLoggerService";
    }
}

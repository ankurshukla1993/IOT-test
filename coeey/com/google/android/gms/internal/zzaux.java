package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.auth.api.zzd;
import com.google.android.gms.auth.api.zzf;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public final class zzaux extends zzab<zzava> {
    private final Bundle zzebu;

    public zzaux(Context context, Looper looper, zzr com_google_android_gms_common_internal_zzr, zzf com_google_android_gms_auth_api_zzf, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 16, com_google_android_gms_common_internal_zzr, connectionCallbacks, onConnectionFailedListener);
        if (com_google_android_gms_auth_api_zzf == null) {
            this.zzebu = new Bundle();
            return;
        }
        throw new NoSuchMethodError();
    }

    protected final Bundle zzaae() {
        return this.zzebu;
    }

    public final boolean zzaam() {
        zzr zzakv = zzakv();
        return (TextUtils.isEmpty(zzakv.getAccountName()) || zzakv.zzc(zzd.API).isEmpty()) ? false : true;
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.internal.IAuthService");
        return queryLocalInterface instanceof zzava ? (zzava) queryLocalInterface : new zzavb(iBinder);
    }

    protected final String zzhf() {
        return "com.google.android.gms.auth.service.START";
    }

    protected final String zzhg() {
        return "com.google.android.gms.auth.api.internal.IAuthService";
    }
}

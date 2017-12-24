package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.Auth.AuthCredentialsOptions;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzr;

public final class zzaun extends zzab<zzaus> {
    @Nullable
    private final AuthCredentialsOptions zzedi;

    public zzaun(Context context, Looper looper, zzr com_google_android_gms_common_internal_zzr, AuthCredentialsOptions authCredentialsOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 68, com_google_android_gms_common_internal_zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzedi = authCredentialsOptions;
    }

    protected final Bundle zzaae() {
        return this.zzedi == null ? new Bundle() : this.zzedi.toBundle();
    }

    final AuthCredentialsOptions zzaal() {
        return this.zzedi;
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
        return queryLocalInterface instanceof zzaus ? (zzaus) queryLocalInterface : new zzaut(iBinder);
    }

    protected final String zzhf() {
        return "com.google.android.gms.auth.api.credentials.service.START";
    }

    protected final String zzhg() {
        return "com.google.android.gms.auth.api.credentials.internal.ICredentialsService";
    }
}

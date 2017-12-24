package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import java.util.Set;

public abstract class zzab<T extends IInterface> extends zzd<T> implements zze, zzaf {
    private final Account zzdzb;
    private final Set<Scope> zzees;
    private final zzr zzfnd;

    protected zzab(Context context, Looper looper, int i, zzr com_google_android_gms_common_internal_zzr, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, zzag.zzcl(context), GoogleApiAvailability.getInstance(), i, com_google_android_gms_common_internal_zzr, (ConnectionCallbacks) zzbq.checkNotNull(connectionCallbacks), (OnConnectionFailedListener) zzbq.checkNotNull(onConnectionFailedListener));
    }

    private zzab(Context context, Looper looper, zzag com_google_android_gms_common_internal_zzag, GoogleApiAvailability googleApiAvailability, int i, zzr com_google_android_gms_common_internal_zzr, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, com_google_android_gms_common_internal_zzag, googleApiAvailability, i, connectionCallbacks == null ? null : new zzac(connectionCallbacks), onConnectionFailedListener == null ? null : new zzad(onConnectionFailedListener), com_google_android_gms_common_internal_zzr.zzakn());
        this.zzfnd = com_google_android_gms_common_internal_zzr;
        this.zzdzb = com_google_android_gms_common_internal_zzr.getAccount();
        Set zzakk = com_google_android_gms_common_internal_zzr.zzakk();
        Set<Scope> zzb = zzb(zzakk);
        for (Scope contains : zzb) {
            if (!zzakk.contains(contains)) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        this.zzees = zzb;
    }

    public final Account getAccount() {
        return this.zzdzb;
    }

    public zzc[] zzajz() {
        return new zzc[0];
    }

    protected final Set<Scope> zzakd() {
        return this.zzees;
    }

    protected final zzr zzakv() {
        return this.zzfnd;
    }

    @NonNull
    protected Set<Scope> zzb(@NonNull Set<Scope> set) {
        return set;
    }
}

package com.google.android.gms.common.api.internal;

import android.os.DeadObjectException;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;

public abstract class zzm<R extends Result, A extends zzb> extends zzs<R> implements zzn<R> {
    private final Api<?> zzffv;
    private final zzc<A> zzflp;

    @Deprecated
    protected zzm(zzc<A> com_google_android_gms_common_api_Api_zzc_A, GoogleApiClient googleApiClient) {
        super((GoogleApiClient) zzbq.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
        this.zzflp = (zzc) zzbq.checkNotNull(com_google_android_gms_common_api_Api_zzc_A);
        this.zzffv = null;
    }

    protected zzm(Api<?> api, GoogleApiClient googleApiClient) {
        super((GoogleApiClient) zzbq.checkNotNull(googleApiClient, "GoogleApiClient must not be null"));
        this.zzflp = api.zzaft();
        this.zzffv = api;
    }

    private final void zzc(RemoteException remoteException) {
        zzu(new Status(8, remoteException.getLocalizedMessage(), null));
    }

    public /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Result) obj);
    }

    protected abstract void zza(A a) throws RemoteException;

    public final zzc<A> zzaft() {
        return this.zzflp;
    }

    public final Api<?> zzafy() {
        return this.zzffv;
    }

    public final void zzb(A a) throws DeadObjectException {
        try {
            zza(a);
        } catch (RemoteException e) {
            zzc(e);
            throw e;
        } catch (RemoteException e2) {
            zzc(e2);
        }
    }

    public final void zzu(Status status) {
        zzbq.checkArgument(!status.isSuccess(), "Failed result must not be success");
        setResult(zzb(status));
    }
}

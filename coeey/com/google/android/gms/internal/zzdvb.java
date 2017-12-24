package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.UserProfileChangeRequest;

final class zzdvb extends zzdvr<Void, zzdwu> {
    @NonNull
    private final UserProfileChangeRequest zzman;

    public zzdvb(UserProfileChangeRequest userProfileChangeRequest) {
        super(2);
        this.zzman = (UserProfileChangeRequest) zzbq.checkNotNull(userProfileChangeRequest, "request cannot be null");
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmav.zzbpo(), this.zzman, this.zzmau);
    }

    public final void zzbpt() {
        ((zzdwu) this.zzmax).zza(this.zzmbe, zzdtw.zza(this.zzlyo, this.zzmbf));
        zzbc(null);
    }
}

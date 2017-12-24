package com.google.android.gms.internal;

import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.PhoneAuthCredential;

final class zzdvu extends zzdvi {
    final /* synthetic */ zzdvr zzmbo;

    private zzdvu(zzdvr com_google_android_gms_internal_zzdvr) {
        this.zzmbo = com_google_android_gms_internal_zzdvr;
    }

    private final void zza(zzdwa com_google_android_gms_internal_zzdwa) {
        this.zzmbo.zzmbc.execute(new zzdvz(this, com_google_android_gms_internal_zzdwa));
    }

    public final void onFailure(@NonNull Status status) throws RemoteException {
        if (this.zzmbo.zzmat == 8) {
            this.zzmbo.zzkrv = true;
            this.zzmbo.zzmbk = false;
            zza(new zzdvy(this, status));
            return;
        }
        this.zzmbo.zzaq(status);
        this.zzmbo.zzap(status);
    }

    public final void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        zzbq.zza(this.zzmbo.zzmat == 8, "Unexpected response type " + this.zzmbo.zzmat);
        this.zzmbo.zzkrv = true;
        this.zzmbo.zzmbk = true;
        zza(new zzdvw(this, phoneAuthCredential));
    }

    public final void zza(@NonNull Status status, @NonNull PhoneAuthCredential phoneAuthCredential) throws RemoteException {
        if (this.zzmbo.zzmbd != null) {
            this.zzmbo.zzkrv = true;
            this.zzmbo.zzmbd.zza(status, phoneAuthCredential);
            return;
        }
        onFailure(status);
    }

    public final void zza(@NonNull zzdwc com_google_android_gms_internal_zzdwc) throws RemoteException {
        zzbq.zza(this.zzmbo.zzmat == 3, "Unexpected response type " + this.zzmbo.zzmat);
        this.zzmbo.zzmbg = com_google_android_gms_internal_zzdwc;
        this.zzmbo.zzbqa();
    }

    public final void zza(@NonNull zzdwg com_google_android_gms_internal_zzdwg, @NonNull zzdwe com_google_android_gms_internal_zzdwe) throws RemoteException {
        zzbq.zza(this.zzmbo.zzmat == 2, "Unexpected response type: " + this.zzmbo.zzmat);
        this.zzmbo.zzmbe = com_google_android_gms_internal_zzdwg;
        this.zzmbo.zzmbf = com_google_android_gms_internal_zzdwe;
        this.zzmbo.zzbqa();
    }

    public final void zza(@Nullable zzdwm com_google_android_gms_internal_zzdwm) throws RemoteException {
        zzbq.zza(this.zzmbo.zzmat == 4, "Unexpected response type " + this.zzmbo.zzmat);
        this.zzmbo.zzmbh = com_google_android_gms_internal_zzdwm;
        this.zzmbo.zzbqa();
    }

    public final void zzb(@NonNull zzdwg com_google_android_gms_internal_zzdwg) throws RemoteException {
        boolean z = true;
        if (this.zzmbo.zzmat != 1) {
            z = false;
        }
        zzbq.zza(z, "Unexpected response type: " + this.zzmbo.zzmat);
        this.zzmbo.zzmbe = com_google_android_gms_internal_zzdwg;
        this.zzmbo.zzbqa();
    }

    public final void zzbpv() throws RemoteException {
        zzbq.zza(this.zzmbo.zzmat == 5, "Unexpected response type " + this.zzmbo.zzmat);
        this.zzmbo.zzbqa();
    }

    public final void zzbpw() throws RemoteException {
        zzbq.zza(this.zzmbo.zzmat == 6, "Unexpected response type " + this.zzmbo.zzmat);
        this.zzmbo.zzbqa();
    }

    public final void zzbpx() throws RemoteException {
        zzbq.zza(this.zzmbo.zzmat == 9, "Unexpected response type " + this.zzmbo.zzmat);
        this.zzmbo.zzbqa();
    }

    public final void zzog(@NonNull String str) throws RemoteException {
        zzbq.zza(this.zzmbo.zzmat == 7, "Unexpected response type " + this.zzmbo.zzmat);
        this.zzmbo.zzmbi = str;
        this.zzmbo.zzbqa();
    }

    public final void zzoh(@NonNull String str) throws RemoteException {
        zzbq.zza(this.zzmbo.zzmat == 8, "Unexpected response type " + this.zzmbo.zzmat);
        this.zzmbo.zzlzg = str;
        zza(new zzdvv(this, str));
    }

    public final void zzoi(@NonNull String str) throws RemoteException {
        zzbq.zza(this.zzmbo.zzmat == 8, "Unexpected response type " + this.zzmbo.zzmat);
        this.zzmbo.zzlzg = str;
        this.zzmbo.zzkrv = true;
        this.zzmbo.zzmbk = true;
        zza(new zzdvx(this, str));
    }
}

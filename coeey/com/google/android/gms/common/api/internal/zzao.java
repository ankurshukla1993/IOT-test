package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbz;

public final class zzao implements zzbk {
    private final zzbl zzfob;
    private boolean zzfoc = false;

    public zzao(zzbl com_google_android_gms_common_api_internal_zzbl) {
        this.zzfob = com_google_android_gms_common_api_internal_zzbl;
    }

    public final void begin() {
    }

    public final void connect() {
        if (this.zzfoc) {
            this.zzfoc = false;
            this.zzfob.zza(new zzaq(this, this));
        }
    }

    public final boolean disconnect() {
        if (this.zzfoc) {
            return false;
        }
        if (this.zzfob.zzfmo.zzahz()) {
            this.zzfoc = true;
            for (zzdi zzajg : this.zzfob.zzfmo.zzfpm) {
                zzajg.zzajg();
            }
            return false;
        }
        this.zzfob.zzg(null);
        return true;
    }

    public final void onConnected(Bundle bundle) {
    }

    public final void onConnectionSuspended(int i) {
        this.zzfob.zzg(null);
        this.zzfob.zzfqa.zzf(i, this.zzfoc);
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
    }

    final void zzaho() {
        if (this.zzfoc) {
            this.zzfoc = false;
            this.zzfob.zzfmo.zzfpn.release();
            disconnect();
        }
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(T t) {
        return zze(t);
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(T t) {
        try {
            this.zzfob.zzfmo.zzfpn.zzb(t);
            zzbd com_google_android_gms_common_api_internal_zzbd = this.zzfob.zzfmo;
            zzb com_google_android_gms_common_api_Api_zzb = (zze) com_google_android_gms_common_api_internal_zzbd.zzfph.get(t.zzaft());
            zzbq.checkNotNull(com_google_android_gms_common_api_Api_zzb, "Appropriate Api was not requested.");
            if (com_google_android_gms_common_api_Api_zzb.isConnected() || !this.zzfob.zzfpw.containsKey(t.zzaft())) {
                if (com_google_android_gms_common_api_Api_zzb instanceof zzbz) {
                    com_google_android_gms_common_api_Api_zzb = zzbz.zzalg();
                }
                t.zzb(com_google_android_gms_common_api_Api_zzb);
                return t;
            }
            t.zzu(new Status(17));
            return t;
        } catch (DeadObjectException e) {
            this.zzfob.zza(new zzap(this, this));
        }
    }
}

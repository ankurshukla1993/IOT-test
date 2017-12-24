package com.google.android.gms.internal;

import android.app.Activity;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

abstract class zzdvr<SuccessT, CallbackT> {
    private boolean zzkrv;
    protected FirebaseApp zzlyo;
    protected String zzlzg;
    protected final int zzmat;
    protected final zzdvu zzmau = new zzdvu();
    protected FirebaseUser zzmav;
    protected zzdvj zzmaw;
    protected CallbackT zzmax;
    protected zzdxg zzmay;
    protected zzdvq<SuccessT> zzmaz;
    protected final List<OnVerificationStateChangedCallbacks> zzmba = new ArrayList();
    private Activity zzmbb;
    protected Executor zzmbc;
    protected zzdvt zzmbd;
    protected zzdwg zzmbe;
    protected zzdwe zzmbf;
    protected zzdwc zzmbg;
    protected zzdwm zzmbh;
    protected String zzmbi;
    protected PhoneAuthCredential zzmbj;
    boolean zzmbk;
    private SuccessT zzmbl;
    private Status zzmbm;

    static class zza extends LifecycleCallback {
        private List<OnVerificationStateChangedCallbacks> zzmbn;

        private zza(zzci com_google_android_gms_common_api_internal_zzci, List<OnVerificationStateChangedCallbacks> list) {
            super(com_google_android_gms_common_api_internal_zzci);
            this.zzfrj.zza("PhoneAuthActivityStopCallback", (LifecycleCallback) this);
            this.zzmbn = list;
        }

        public static void zza(Activity activity, List<OnVerificationStateChangedCallbacks> list) {
            zzci zzn = LifecycleCallback.zzn(activity);
            if (((zza) zzn.zza("PhoneAuthActivityStopCallback", zza.class)) == null) {
                zza com_google_android_gms_internal_zzdvr_zza = new zza(zzn, list);
            }
        }

        @MainThread
        public final void onStop() {
            synchronized (this.zzmbn) {
                this.zzmbn.clear();
            }
        }
    }

    public zzdvr(int i) {
        this.zzmat = i;
    }

    private final void zzaq(Status status) {
        if (this.zzmay != null) {
            this.zzmay.onError(status);
        }
    }

    private final void zzbqa() {
        zzbpt();
        zzbq.zza(this.zzkrv, (Object) "no success or failure set on method implementation");
    }

    protected abstract void dispatch() throws RemoteException;

    public final zzdvr<SuccessT, CallbackT> zza(zzdxg com_google_android_gms_internal_zzdxg) {
        this.zzmay = (zzdxg) zzbq.checkNotNull(com_google_android_gms_internal_zzdxg, "external failure callback cannot be null");
        return this;
    }

    public final zzdvr<SuccessT, CallbackT> zza(OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Activity activity, Executor executor) {
        synchronized (this.zzmba) {
            this.zzmba.add((OnVerificationStateChangedCallbacks) zzbq.checkNotNull(onVerificationStateChangedCallbacks));
        }
        this.zzmbb = activity;
        if (this.zzmbb != null) {
            zza.zza(activity, this.zzmba);
        }
        this.zzmbc = (Executor) zzbq.checkNotNull(executor);
        return this;
    }

    public final void zzap(Status status) {
        this.zzkrv = true;
        this.zzmbk = false;
        this.zzmbm = status;
        this.zzmaz.zza(null, status);
    }

    public final zzdvr<SuccessT, CallbackT> zzbb(CallbackT callbackT) {
        this.zzmax = zzbq.checkNotNull(callbackT, "external callback cannot be null");
        return this;
    }

    public final void zzbc(SuccessT successT) {
        this.zzkrv = true;
        this.zzmbk = true;
        this.zzmbl = successT;
        this.zzmaz.zza(successT, null);
    }

    public abstract void zzbpt();

    public final zzdvr<SuccessT, CallbackT> zzc(FirebaseApp firebaseApp) {
        this.zzlyo = (FirebaseApp) zzbq.checkNotNull(firebaseApp, "firebaseApp cannot be null");
        return this;
    }

    public final zzdvr<SuccessT, CallbackT> zze(FirebaseUser firebaseUser) {
        this.zzmav = (FirebaseUser) zzbq.checkNotNull(firebaseUser, "firebaseUser cannot be null");
        return this;
    }
}

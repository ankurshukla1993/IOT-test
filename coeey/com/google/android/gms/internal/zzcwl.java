package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.zzz;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbr;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.internal.zzr;

public final class zzcwl extends zzab<zzcwj> implements zzcwb {
    private final zzr zzfnd;
    private Integer zzfwp;
    private final boolean zzjzg;
    private final Bundle zzjzh;

    private zzcwl(Context context, Looper looper, boolean z, zzr com_google_android_gms_common_internal_zzr, Bundle bundle, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, com_google_android_gms_common_internal_zzr, connectionCallbacks, onConnectionFailedListener);
        this.zzjzg = true;
        this.zzfnd = com_google_android_gms_common_internal_zzr;
        this.zzjzh = bundle;
        this.zzfwp = com_google_android_gms_common_internal_zzr.zzakq();
    }

    public zzcwl(Context context, Looper looper, boolean z, zzr com_google_android_gms_common_internal_zzr, zzcwc com_google_android_gms_internal_zzcwc, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, true, com_google_android_gms_common_internal_zzr, zza(com_google_android_gms_common_internal_zzr), connectionCallbacks, onConnectionFailedListener);
    }

    public static Bundle zza(zzr com_google_android_gms_common_internal_zzr) {
        zzcwc zzakp = com_google_android_gms_common_internal_zzr.zzakp();
        Integer zzakq = com_google_android_gms_common_internal_zzr.zzakq();
        Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", com_google_android_gms_common_internal_zzr.getAccount());
        if (zzakq != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", zzakq.intValue());
        }
        if (zzakp != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", zzakp.zzbcq());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", zzakp.isIdTokenRequested());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", zzakp.getServerClientId());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", zzakp.zzbcr());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", zzakp.zzbcs());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", zzakp.zzbct());
            if (zzakp.zzbcu() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", zzakp.zzbcu().longValue());
            }
            if (zzakp.zzbcv() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", zzakp.zzbcv().longValue());
            }
        }
        return bundle;
    }

    public final void connect() {
        zza(new zzm(this));
    }

    public final void zza(zzan com_google_android_gms_common_internal_zzan, boolean z) {
        try {
            ((zzcwj) zzakb()).zza(com_google_android_gms_common_internal_zzan, this.zzfwp.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public final void zza(zzcwh com_google_android_gms_internal_zzcwh) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcwh, "Expecting a valid ISignInCallbacks");
        try {
            Account zzakh = this.zzfnd.zzakh();
            GoogleSignInAccount googleSignInAccount = null;
            if ("<<default account>>".equals(zzakh.name)) {
                googleSignInAccount = zzz.zzbr(getContext()).zzabg();
            }
            ((zzcwj) zzakb()).zza(new zzcwm(new zzbr(zzakh, this.zzfwp.intValue(), googleSignInAccount)), com_google_android_gms_internal_zzcwh);
        } catch (Throwable e) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                com_google_android_gms_internal_zzcwh.zzb(new zzcwo(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    protected final Bundle zzaae() {
        if (!getContext().getPackageName().equals(this.zzfnd.zzakm())) {
            this.zzjzh.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzfnd.zzakm());
        }
        return this.zzjzh;
    }

    public final boolean zzaam() {
        return this.zzjzg;
    }

    public final void zzbcp() {
        try {
            ((zzcwj) zzakb()).zzeh(this.zzfwp.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    protected final /* synthetic */ IInterface zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.signin.internal.ISignInService");
        return queryLocalInterface instanceof zzcwj ? (zzcwj) queryLocalInterface : new zzcwk(iBinder);
    }

    protected final String zzhf() {
        return "com.google.android.gms.signin.service.START";
    }

    protected final String zzhg() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }
}

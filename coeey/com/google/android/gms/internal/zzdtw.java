package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.ActionCodeResult;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.FirebaseAuthProvider;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.zzb;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public final class zzdtw extends zzdtp<zzdvn> {
    zzdtw(@NonNull Context context, @NonNull zzdvn com_google_android_gms_internal_zzdvn) {
        super(context, zzdvl.zzmas, com_google_android_gms_internal_zzdvn, new zzb(), DynamiteModule.zzac(context, "com.google.android.gms.firebase_auth"), DynamiteModule.zzab(context, "com.google.firebase.auth"), Collections.EMPTY_MAP);
    }

    private static <ResultT, CallbackT> zzduh<ResultT, CallbackT> zza(zzdvr<ResultT, CallbackT> com_google_android_gms_internal_zzdvr_ResultT__CallbackT, String str) {
        return new zzduh(com_google_android_gms_internal_zzdvr_ResultT__CallbackT, str);
    }

    @NonNull
    private static zzdxb zza(@NonNull FirebaseApp firebaseApp, @NonNull zzdwe com_google_android_gms_internal_zzdwe) {
        return zza(firebaseApp, com_google_android_gms_internal_zzdwe, false);
    }

    @NonNull
    private static zzdxb zza(@NonNull FirebaseApp firebaseApp, @NonNull zzdwe com_google_android_gms_internal_zzdwe, boolean z) {
        zzbq.checkNotNull(firebaseApp);
        zzbq.checkNotNull(com_google_android_gms_internal_zzdwe);
        List arrayList = new ArrayList();
        arrayList.add(new zzdwz(com_google_android_gms_internal_zzdwe, FirebaseAuthProvider.PROVIDER_ID));
        List zzbqb = com_google_android_gms_internal_zzdwe.zzbqb();
        if (!(zzbqb == null || zzbqb.isEmpty())) {
            for (int i = 0; i < zzbqb.size(); i++) {
                arrayList.add(new zzdwz((zzdwi) zzbqb.get(i)));
            }
        }
        FirebaseUser com_google_android_gms_internal_zzdxb = new zzdxb(firebaseApp, arrayList);
        com_google_android_gms_internal_zzdxb.zzcc(z);
        com_google_android_gms_internal_zzdxb.zza(new zzdxc(com_google_android_gms_internal_zzdwe.getLastSignInTimestamp(), com_google_android_gms_internal_zzdwe.getCreationTimestamp()));
        com_google_android_gms_internal_zzdxb.zzcf(com_google_android_gms_internal_zzdwe.isNewUser());
        return com_google_android_gms_internal_zzdxb;
    }

    @NonNull
    public final Task<Void> setFirebaseUIVersion(@NonNull String str) {
        return zzb(zza(new zzduq(str), "setFirebaseUIVersion"));
    }

    public final Task<AuthResult> zza(@NonNull FirebaseApp firebaseApp, @NonNull zzdwu com_google_android_gms_internal_zzdwu) {
        return zzb(zza(new zzdur().zzc(firebaseApp).zzbb(com_google_android_gms_internal_zzdwu), "signInAnonymously"));
    }

    public final Task<Void> zza(@NonNull FirebaseApp firebaseApp, @NonNull ActionCodeSettings actionCodeSettings, @NonNull String str) {
        return zzb(zza(new zzduo(str, actionCodeSettings).zzc(firebaseApp), "sendEmailVerification"));
    }

    public final Task<AuthResult> zza(@NonNull FirebaseApp firebaseApp, @NonNull AuthCredential authCredential, @NonNull zzdwu com_google_android_gms_internal_zzdwu) {
        return zzb(zza(new zzdus(authCredential).zzc(firebaseApp).zzbb(com_google_android_gms_internal_zzdwu), "signInWithCredential"));
    }

    @NonNull
    public final Task<Void> zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zza(zza(new zzdun().zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "reload"));
    }

    public final Task<Void> zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zzb(zza(new zzdui(authCredential).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "reauthenticateWithCredential"));
    }

    public final Task<Void> zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull PhoneAuthCredential phoneAuthCredential, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zzb(zza(new zzdva(phoneAuthCredential).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "updatePhoneNumber"));
    }

    public final Task<Void> zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull UserProfileChangeRequest userProfileChangeRequest, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zzb(zza(new zzdvb(userProfileChangeRequest).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "updateProfile"));
    }

    public final Task<GetTokenResult> zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull String str, @NonNull zzdwu com_google_android_gms_internal_zzdwu) {
        return zza(zza(new zzdud(str).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdwu), "getAccessToken"));
    }

    public final Task<Void> zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull String str, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zzb(zza(new zzduy(str).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "updateEmail"));
    }

    public final Task<Void> zza(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull String str, @NonNull String str2, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zzb(zza(new zzduk(str, str2).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "reauthenticateWithEmailPassword"));
    }

    public final Task<AuthResult> zza(@NonNull FirebaseApp firebaseApp, @NonNull PhoneAuthCredential phoneAuthCredential, @NonNull zzdwu com_google_android_gms_internal_zzdwu) {
        return zzb(zza(new zzduv(phoneAuthCredential).zzc(firebaseApp).zzbb(com_google_android_gms_internal_zzdwu), "signInWithPhoneNumber"));
    }

    public final Task<ProviderQueryResult> zza(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        return zza(zza(new zzduc(str).zzc(firebaseApp), "fetchProvidersForEmail"));
    }

    public final Task<AuthResult> zza(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull zzdwu com_google_android_gms_internal_zzdwu) {
        return zzb(zza(new zzdut(str).zzc(firebaseApp).zzbb(com_google_android_gms_internal_zzdwu), "signInWithCustomToken"));
    }

    public final Task<Void> zza(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull ActionCodeSettings actionCodeSettings) {
        return zzb(zza(new zzdup(str, actionCodeSettings).zzc(firebaseApp), "sendPasswordResetEmail"));
    }

    public final Task<Void> zza(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull String str2) {
        return zzb(zza(new zzdtz(str, str2).zzc(firebaseApp), "confirmPasswordReset"));
    }

    public final Task<AuthResult> zza(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull String str2, @NonNull zzdwu com_google_android_gms_internal_zzdwu) {
        return zzb(zza(new zzdua(str, str2).zzc(firebaseApp).zzbb(com_google_android_gms_internal_zzdwu), "createUserWithEmailAndPassword"));
    }

    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull zzdxf com_google_android_gms_internal_zzdxf) {
        return zzb(zza(new zzdub().zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxf).zza((zzdxg) com_google_android_gms_internal_zzdxf), "delete"));
    }

    public final void zza(@NonNull FirebaseApp firebaseApp, @NonNull zzdwo com_google_android_gms_internal_zzdwo, @NonNull OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable Activity activity, @NonNull Executor executor) {
        zzb(zza(new zzdvd(com_google_android_gms_internal_zzdwo).zzc(firebaseApp).zza(onVerificationStateChangedCallbacks, activity, executor), "verifyPhoneNumber"));
    }

    public final Task<AuthResult> zzb(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zzb(zza(new zzduj(authCredential).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "reauthenticateWithCredentialWithData"));
    }

    public final Task<Void> zzb(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull PhoneAuthCredential phoneAuthCredential, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zzb(zza(new zzdum(phoneAuthCredential).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "reauthenticateWithPhoneCredential"));
    }

    public final Task<Void> zzb(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull String str, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zzb(zza(new zzduz(str).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "updatePassword"));
    }

    public final Task<AuthResult> zzb(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull String str, @NonNull String str2, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zzb(zza(new zzdul(str, str2).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "reauthenticateWithEmailPasswordWithData"));
    }

    public final Task<ActionCodeResult> zzb(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        return zzb(zza(new zzdty(str).zzc(firebaseApp), "checkActionCode"));
    }

    public final Task<AuthResult> zzb(@NonNull FirebaseApp firebaseApp, @NonNull String str, @NonNull String str2, @NonNull zzdwu com_google_android_gms_internal_zzdwu) {
        return zzb(zza(new zzduu(str, str2).zzc(firebaseApp).zzbb(com_google_android_gms_internal_zzdwu), "signInWithEmailAndPassword"));
    }

    public final Task<AuthResult> zzc(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        return zzb(zza(new zzduj(authCredential).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "reauthenticateWithPhoneCredentialWithData"));
    }

    public final Task<AuthResult> zzc(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull String str, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        zzbq.checkNotNull(firebaseApp);
        zzbq.zzgh(str);
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(com_google_android_gms_internal_zzdxk);
        List providers = firebaseUser.getProviders();
        if ((providers != null && !providers.contains(str)) || firebaseUser.isAnonymous()) {
            return Tasks.forException(zzdvg.zzao(new Status(17016, str)));
        }
        Object obj = -1;
        switch (str.hashCode()) {
            case 1216985755:
                if (str.equals("password")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                return zzb(zza(new zzduw().zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "unlinkEmailCredential"));
            default:
                return zzb(zza(new zzdux(str).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "unlinkFederatedCredential"));
        }
    }

    public final Task<Void> zzc(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        return zzb(zza(new zzdtx(str).zzc(firebaseApp), "applyActionCode"));
    }

    public final Task<AuthResult> zzd(@NonNull FirebaseApp firebaseApp, @NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential, @NonNull zzdxk com_google_android_gms_internal_zzdxk) {
        zzbq.checkNotNull(firebaseApp);
        zzbq.checkNotNull(authCredential);
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(com_google_android_gms_internal_zzdxk);
        List providers = firebaseUser.getProviders();
        if (providers != null && providers.contains(authCredential.getProvider())) {
            return Tasks.forException(zzdvg.zzao(new Status(17015)));
        }
        if (authCredential instanceof EmailAuthCredential) {
            return zzb(zza(new zzdue((EmailAuthCredential) authCredential).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "linkEmailAuthCredential"));
        }
        if (authCredential instanceof PhoneAuthCredential) {
            return zzb(zza(new zzdug((PhoneAuthCredential) authCredential).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "linkPhoneAuthCredential"));
        }
        zzbq.checkNotNull(firebaseApp);
        zzbq.checkNotNull(authCredential);
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(com_google_android_gms_internal_zzdxk);
        return zzb(zza(new zzduf(authCredential).zzc(firebaseApp).zze(firebaseUser).zzbb(com_google_android_gms_internal_zzdxk).zza((zzdxg) com_google_android_gms_internal_zzdxk), "linkFederatedCredential"));
    }

    public final Task<String> zzd(@NonNull FirebaseApp firebaseApp, @NonNull String str) {
        return zzb(zza(new zzdvc(str).zzc(firebaseApp), "verifyPasswordResetCode"));
    }
}

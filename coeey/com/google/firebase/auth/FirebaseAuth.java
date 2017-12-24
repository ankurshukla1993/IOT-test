package com.google.firebase.auth;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.internal.zzdtw;
import com.google.android.gms.internal.zzdvg;
import com.google.android.gms.internal.zzdvl;
import com.google.android.gms.internal.zzdvo;
import com.google.android.gms.internal.zzdvp;
import com.google.android.gms.internal.zzdwg;
import com.google.android.gms.internal.zzdwo;
import com.google.android.gms.internal.zzdwu;
import com.google.android.gms.internal.zzdwy;
import com.google.android.gms.internal.zzdxa;
import com.google.android.gms.internal.zzdxb;
import com.google.android.gms.internal.zzdxg;
import com.google.android.gms.internal.zzdxi;
import com.google.android.gms.internal.zzdxj;
import com.google.android.gms.internal.zzeui;
import com.google.android.gms.internal.zzeuj;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class FirebaseAuth implements zzeui {
    private static Map<String, FirebaseAuth> zzick = new ArrayMap();
    private static FirebaseAuth zzlyw;
    private List<IdTokenListener> zzlwn;
    private FirebaseApp zzlyo;
    private List<AuthStateListener> zzlyp;
    private zzdtw zzlyq;
    private FirebaseUser zzlyr;
    private final Object zzlys;
    private String zzlyt;
    private zzdxi zzlyu;
    private zzdxj zzlyv;

    public interface AuthStateListener {
        void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    public interface IdTokenListener {
        void onIdTokenChanged(@NonNull FirebaseAuth firebaseAuth);
    }

    class zza implements zzdwu {
        private /* synthetic */ FirebaseAuth zzlyy;

        zza(FirebaseAuth firebaseAuth) {
            this.zzlyy = firebaseAuth;
        }

        public final void zza(@NonNull zzdwg com_google_android_gms_internal_zzdwg, @NonNull FirebaseUser firebaseUser) {
            zzbq.checkNotNull(com_google_android_gms_internal_zzdwg);
            zzbq.checkNotNull(firebaseUser);
            firebaseUser.zza(com_google_android_gms_internal_zzdwg);
            this.zzlyy.zza(firebaseUser, com_google_android_gms_internal_zzdwg, true);
        }
    }

    class zzb extends zza implements zzdwu, zzdxg {
        private /* synthetic */ FirebaseAuth zzlyy;

        zzb(FirebaseAuth firebaseAuth) {
            this.zzlyy = firebaseAuth;
            super(firebaseAuth);
        }

        public final void onError(Status status) {
            if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005) {
                this.zzlyy.signOut();
            }
        }
    }

    public FirebaseAuth(FirebaseApp firebaseApp) {
        this(firebaseApp, zzdvl.zza(firebaseApp.getApplicationContext(), new zzdvo(firebaseApp.getOptions().getApiKey()).zzbpy()), new zzdxi(firebaseApp.getApplicationContext(), firebaseApp.zzboy()));
    }

    private FirebaseAuth(FirebaseApp firebaseApp, zzdtw com_google_android_gms_internal_zzdtw, zzdxi com_google_android_gms_internal_zzdxi) {
        this.zzlys = new Object();
        this.zzlyo = (FirebaseApp) zzbq.checkNotNull(firebaseApp);
        this.zzlyq = (zzdtw) zzbq.checkNotNull(com_google_android_gms_internal_zzdtw);
        this.zzlyu = (zzdxi) zzbq.checkNotNull(com_google_android_gms_internal_zzdxi);
        this.zzlwn = new CopyOnWriteArrayList();
        this.zzlyp = new CopyOnWriteArrayList();
        this.zzlyv = zzdxj.zzbql();
        this.zzlyr = this.zzlyu.zzbqk();
        if (this.zzlyr != null) {
            zzdwg zzg = this.zzlyu.zzg(this.zzlyr);
            if (zzg != null) {
                zza(this.zzlyr, zzg, false);
            }
        }
    }

    @Keep
    public static FirebaseAuth getInstance() {
        return zzb(FirebaseApp.getInstance());
    }

    @Keep
    public static FirebaseAuth getInstance(@NonNull FirebaseApp firebaseApp) {
        return zzb(firebaseApp);
    }

    private final void zza(@Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            Log.d("FirebaseAuth", new StringBuilder(String.valueOf(uid).length() + 45).append("Notifying id token listeners about user ( ").append(uid).append(" ).").toString());
        } else {
            Log.d("FirebaseAuth", "Notifying id token listeners about a sign-out event.");
        }
        this.zzlyv.execute(new zzj(this, new zzeuj(firebaseUser != null ? firebaseUser.zzbpp() : null)));
    }

    private static synchronized FirebaseAuth zzb(@NonNull FirebaseApp firebaseApp) {
        FirebaseAuth firebaseAuth;
        synchronized (FirebaseAuth.class) {
            firebaseAuth = (FirebaseAuth) zzick.get(firebaseApp.zzboy());
            if (firebaseAuth == null) {
                firebaseAuth = new zzdxa(firebaseApp);
                firebaseApp.zza((zzeui) firebaseAuth);
                if (zzlyw == null) {
                    zzlyw = firebaseAuth;
                }
                zzick.put(firebaseApp.zzboy(), firebaseAuth);
            }
        }
        return firebaseAuth;
    }

    private final void zzb(@Nullable FirebaseUser firebaseUser) {
        if (firebaseUser != null) {
            String uid = firebaseUser.getUid();
            Log.d("FirebaseAuth", new StringBuilder(String.valueOf(uid).length() + 47).append("Notifying auth state listeners about user ( ").append(uid).append(" ).").toString());
        } else {
            Log.d("FirebaseAuth", "Notifying auth state listeners about a sign-out event.");
        }
        this.zzlyv.execute(new zzk(this));
    }

    public void addAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzlyp.add(authStateListener);
        this.zzlyv.execute(new zzi(this, authStateListener));
    }

    public void addIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzlwn.add(idTokenListener);
        this.zzlyv.execute(new zzh(this, idTokenListener));
    }

    @NonNull
    public Task<Void> applyActionCode(@NonNull String str) {
        zzbq.zzgh(str);
        return this.zzlyq.zzc(this.zzlyo, str);
    }

    @NonNull
    public Task<ActionCodeResult> checkActionCode(@NonNull String str) {
        zzbq.zzgh(str);
        return this.zzlyq.zzb(this.zzlyo, str);
    }

    @NonNull
    public Task<Void> confirmPasswordReset(@NonNull String str, @NonNull String str2) {
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        return this.zzlyq.zza(this.zzlyo, str, str2);
    }

    @NonNull
    public Task<AuthResult> createUserWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        return this.zzlyq.zza(this.zzlyo, str, str2, new zza(this));
    }

    @NonNull
    public Task<ProviderQueryResult> fetchProvidersForEmail(@NonNull String str) {
        zzbq.zzgh(str);
        return this.zzlyq.zza(this.zzlyo, str);
    }

    @Nullable
    public FirebaseUser getCurrentUser() {
        return this.zzlyr;
    }

    @Nullable
    public String getLanguageCode() {
        String str;
        synchronized (this.zzlys) {
            str = this.zzlyt;
        }
        return str;
    }

    @Nullable
    public final String getUid() {
        return this.zzlyr == null ? null : this.zzlyr.getUid();
    }

    public void removeAuthStateListener(@NonNull AuthStateListener authStateListener) {
        this.zzlyp.remove(authStateListener);
    }

    public void removeIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        this.zzlwn.remove(idTokenListener);
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str) {
        zzbq.zzgh(str);
        return sendPasswordResetEmail(str, null);
    }

    @NonNull
    public Task<Void> sendPasswordResetEmail(@NonNull String str, @Nullable ActionCodeSettings actionCodeSettings) {
        zzbq.zzgh(str);
        if (this.zzlyt != null) {
            if (actionCodeSettings == null) {
                actionCodeSettings = ActionCodeSettings.newBuilder().build();
            }
            actionCodeSettings.zzoe(this.zzlyt);
        }
        return this.zzlyq.zza(this.zzlyo, str, actionCodeSettings);
    }

    public Task<Void> setFirebaseUIVersion(@Nullable String str) {
        return this.zzlyq.setFirebaseUIVersion(str);
    }

    public void setLanguageCode(@NonNull String str) {
        zzbq.zzgh(str);
        synchronized (this.zzlys) {
            this.zzlyt = str;
        }
    }

    @NonNull
    public Task<AuthResult> signInAnonymously() {
        if (this.zzlyr == null || !this.zzlyr.isAnonymous()) {
            return this.zzlyq.zza(this.zzlyo, new zza(this));
        }
        zzdxb com_google_android_gms_internal_zzdxb = (zzdxb) this.zzlyr;
        com_google_android_gms_internal_zzdxb.zzcf(false);
        return Tasks.forResult(new zzdwy(com_google_android_gms_internal_zzdxb));
    }

    @NonNull
    public Task<AuthResult> signInWithCredential(@NonNull AuthCredential authCredential) {
        zzbq.checkNotNull(authCredential);
        if (authCredential instanceof EmailAuthCredential) {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            return this.zzlyq.zzb(this.zzlyo, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), new zza(this));
        } else if (!(authCredential instanceof PhoneAuthCredential)) {
            return this.zzlyq.zza(this.zzlyo, authCredential, new zza(this));
        } else {
            return this.zzlyq.zza(this.zzlyo, (PhoneAuthCredential) authCredential, new zza(this));
        }
    }

    @NonNull
    public Task<AuthResult> signInWithCustomToken(@NonNull String str) {
        zzbq.zzgh(str);
        return this.zzlyq.zza(this.zzlyo, str, new zza(this));
    }

    @NonNull
    public Task<AuthResult> signInWithEmailAndPassword(@NonNull String str, @NonNull String str2) {
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        return this.zzlyq.zzb(this.zzlyo, str, str2, new zza(this));
    }

    public void signOut() {
        zzbpk();
    }

    public void useAppLanguage() {
        synchronized (this.zzlys) {
            this.zzlyt = zzdvp.zzbpz();
        }
    }

    @NonNull
    public Task<String> verifyPasswordResetCode(@NonNull String str) {
        zzbq.zzgh(str);
        return this.zzlyq.zzd(this.zzlyo, str);
    }

    @NonNull
    public final Task<Void> zza(@NonNull ActionCodeSettings actionCodeSettings, @NonNull String str) {
        zzbq.zzgh(str);
        if (this.zzlyt != null) {
            if (actionCodeSettings == null) {
                actionCodeSettings = ActionCodeSettings.newBuilder().build();
            }
            actionCodeSettings.zzoe(this.zzlyt);
        }
        return this.zzlyq.zza(this.zzlyo, actionCodeSettings, str);
    }

    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(authCredential);
        if (!EmailAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            return authCredential instanceof PhoneAuthCredential ? this.zzlyq.zzb(this.zzlyo, firebaseUser, (PhoneAuthCredential) authCredential, new zzb(this)) : this.zzlyq.zza(this.zzlyo, firebaseUser, authCredential, new zzb(this));
        } else {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            return this.zzlyq.zza(this.zzlyo, firebaseUser, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), new zzb(this));
        }
    }

    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull PhoneAuthCredential phoneAuthCredential) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(phoneAuthCredential);
        return this.zzlyq.zza(this.zzlyo, firebaseUser, phoneAuthCredential, new zzb(this));
    }

    @NonNull
    public final Task<Void> zza(@NonNull FirebaseUser firebaseUser, @NonNull UserProfileChangeRequest userProfileChangeRequest) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(userProfileChangeRequest);
        return this.zzlyq.zza(this.zzlyo, firebaseUser, userProfileChangeRequest, new zzb(this));
    }

    @NonNull
    public final Task<AuthResult> zza(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        zzbq.zzgh(str);
        zzbq.checkNotNull(firebaseUser);
        return this.zzlyq.zzc(this.zzlyo, firebaseUser, str, new zzb(this));
    }

    @NonNull
    public final Task<GetTokenResult> zza(@Nullable FirebaseUser firebaseUser, boolean z) {
        if (firebaseUser == null) {
            return Tasks.forException(zzdvg.zzao(new Status(17495)));
        }
        zzdwg zzbpn = this.zzlyr.zzbpn();
        return (!zzbpn.isValid() || z) ? this.zzlyq.zza(this.zzlyo, firebaseUser, zzbpn.zzbqc(), new zzl(this)) : Tasks.forResult(new GetTokenResult(zzbpn.getAccessToken()));
    }

    public final void zza(@NonNull FirebaseUser firebaseUser, @NonNull zzdwg com_google_android_gms_internal_zzdwg, boolean z) {
        Object obj;
        Object obj2 = 1;
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(com_google_android_gms_internal_zzdwg);
        if (this.zzlyr == null) {
            obj = 1;
        } else {
            obj = !this.zzlyr.zzbpn().getAccessToken().equals(com_google_android_gms_internal_zzdwg.getAccessToken()) ? 1 : null;
            boolean equals = this.zzlyr.getUid().equals(firebaseUser.getUid());
            if (equals && obj == null) {
                obj = null;
            } else {
                int i = 1;
            }
            if (equals) {
                obj2 = null;
            }
        }
        zzbq.checkNotNull(firebaseUser);
        if (this.zzlyr == null) {
            this.zzlyr = firebaseUser;
        } else {
            this.zzlyr.zzcc(firebaseUser.isAnonymous());
            this.zzlyr.zzap(firebaseUser.getProviderData());
        }
        if (z) {
            this.zzlyu.zzf(this.zzlyr);
        }
        if (obj != null) {
            if (this.zzlyr != null) {
                this.zzlyr.zza(com_google_android_gms_internal_zzdwg);
            }
            zza(this.zzlyr);
        }
        if (obj2 != null) {
            zzb(this.zzlyr);
        }
        if (z) {
            this.zzlyu.zza(firebaseUser, com_google_android_gms_internal_zzdwg);
        }
    }

    @NonNull
    public final void zza(@NonNull String str, long j, TimeUnit timeUnit, @NonNull OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable Activity activity, @NonNull Executor executor, boolean z) {
        String str2 = null;
        Context applicationContext = this.zzlyo.getApplicationContext();
        zzbq.checkNotNull(applicationContext);
        zzbq.zzgh(str);
        TelephonyManager telephonyManager = (TelephonyManager) applicationContext.getSystemService(PhoneAuthProvider.PROVIDER_ID);
        Object simCountryIso = telephonyManager != null ? telephonyManager.getSimCountryIso() : null;
        if (TextUtils.isEmpty(simCountryIso)) {
            Locale locale = Locale.getDefault();
            simCountryIso = locale != null ? locale.getCountry() : null;
        }
        String toUpperCase = TextUtils.isEmpty(simCountryIso) ? "US" : simCountryIso.toUpperCase(Locale.US);
        String stripSeparators = PhoneNumberUtils.stripSeparators(str);
        if (zzq.zzamb()) {
            str2 = PhoneNumberUtils.formatNumberToE164(stripSeparators, toUpperCase);
            if (TextUtils.isEmpty(str2)) {
                str2 = stripSeparators;
            }
        } else if (!"US".equals(toUpperCase)) {
            str2 = stripSeparators;
        } else if (stripSeparators != null) {
            int length = stripSeparators.length();
            if (!stripSeparators.startsWith("+")) {
                if (length == 11 && stripSeparators.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
                    str2 = "+".concat(stripSeparators);
                } else if (length == 10) {
                    str2 = "+1".concat(stripSeparators);
                }
            }
            str2 = stripSeparators;
        }
        long convert = TimeUnit.SECONDS.convert(j, timeUnit);
        if (convert < 0 || convert > 120) {
            throw new IllegalArgumentException("We only support 0-120 seconds for sms-auto-retrieval timeout");
        }
        this.zzlyq.zza(this.zzlyo, new zzdwo(str2, convert, z, this.zzlyt), onVerificationStateChangedCallbacks, activity, executor);
    }

    public final Task<AuthResult> zzb(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.checkNotNull(authCredential);
        if (!EmailAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            return authCredential instanceof PhoneAuthCredential ? this.zzlyq.zzc(this.zzlyo, firebaseUser, authCredential, new zzb(this)) : this.zzlyq.zzb(this.zzlyo, firebaseUser, authCredential, new zzb(this));
        } else {
            EmailAuthCredential emailAuthCredential = (EmailAuthCredential) authCredential;
            return this.zzlyq.zzb(this.zzlyo, firebaseUser, emailAuthCredential.getEmail(), emailAuthCredential.getPassword(), new zzb(this));
        }
    }

    @NonNull
    public final Task<Void> zzb(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.zzgh(str);
        return this.zzlyq.zza(this.zzlyo, firebaseUser, str, new zzb(this));
    }

    public final void zzbpk() {
        if (this.zzlyr != null) {
            zzdxi com_google_android_gms_internal_zzdxi = this.zzlyu;
            zzbq.checkNotNull(this.zzlyr);
            com_google_android_gms_internal_zzdxi.clear(String.format("com.google.firebase.auth.GET_TOKEN_RESPONSE.%s", new Object[]{r1.getUid()}));
            this.zzlyr = null;
        }
        this.zzlyu.clear("com.google.firebase.auth.FIREBASE_USER");
        zza(null);
        zzb(null);
    }

    @NonNull
    public final Task<Void> zzc(@NonNull FirebaseUser firebaseUser) {
        zzbq.checkNotNull(firebaseUser);
        return this.zzlyq.zza(this.zzlyo, firebaseUser, new zzb(this));
    }

    @NonNull
    public final Task<AuthResult> zzc(@NonNull FirebaseUser firebaseUser, @NonNull AuthCredential authCredential) {
        zzbq.checkNotNull(authCredential);
        zzbq.checkNotNull(firebaseUser);
        return this.zzlyq.zzd(this.zzlyo, firebaseUser, authCredential, new zzb(this));
    }

    @NonNull
    public final Task<Void> zzc(@NonNull FirebaseUser firebaseUser, @NonNull String str) {
        zzbq.checkNotNull(firebaseUser);
        zzbq.zzgh(str);
        return this.zzlyq.zzb(this.zzlyo, firebaseUser, str, new zzb(this));
    }

    @NonNull
    public final Task<GetTokenResult> zzcb(boolean z) {
        return zza(this.zzlyr, z);
    }

    @NonNull
    public final Task<Void> zzd(@NonNull FirebaseUser firebaseUser) {
        zzbq.checkNotNull(firebaseUser);
        return this.zzlyq.zza(firebaseUser, new zzm(this, firebaseUser));
    }

    @NonNull
    public final Task<Void> zzof(@NonNull String str) {
        zzbq.zzgh(str);
        return this.zzlyq.zza(this.zzlyo, null, str);
    }
}

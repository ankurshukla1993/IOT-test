package com.google.firebase.auth;

import android.app.Activity;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import com.google.android.gms.internal.zzbfd;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class PhoneAuthProvider {
    public static final String PROVIDER_ID = "phone";
    private FirebaseAuth zzlzl;

    public static class ForceResendingToken extends zzbej {
        public static final Creator<ForceResendingToken> CREATOR = new zzc();

        ForceResendingToken() {
        }

        public static ForceResendingToken zzbpq() {
            return new ForceResendingToken();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzbem.zzai(parcel, zzbem.zze(parcel));
        }
    }

    public static abstract class OnVerificationStateChangedCallbacks {
        private static final zzbfd zzeez = new zzbfd("PhoneAuthProvider", new String[0]);

        public void onCodeAutoRetrievalTimeOut(String str) {
            zzeez.zze("Sms auto retrieval timed-out.", new Object[0]);
        }

        public void onCodeSent(String str, ForceResendingToken forceResendingToken) {
        }

        public abstract void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential);

        public abstract void onVerificationFailed(FirebaseException firebaseException);
    }

    private PhoneAuthProvider(FirebaseAuth firebaseAuth) {
        this.zzlzl = firebaseAuth;
    }

    public static PhoneAuthCredential getCredential(@NonNull String str, @NonNull String str2) {
        return new PhoneAuthCredential(str, str2, false, null, true, null);
    }

    public static PhoneAuthProvider getInstance() {
        return new PhoneAuthProvider(FirebaseAuth.getInstance(FirebaseApp.getInstance()));
    }

    public static PhoneAuthProvider getInstance(FirebaseAuth firebaseAuth) {
        return new PhoneAuthProvider(firebaseAuth);
    }

    private final void zza(String str, long j, TimeUnit timeUnit, Activity activity, Executor executor, OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, ForceResendingToken forceResendingToken) {
        this.zzlzl.zza(str, j, timeUnit, onVerificationStateChangedCallbacks, activity, executor, forceResendingToken != null);
    }

    public void verifyPhoneNumber(@NonNull String str, long j, TimeUnit timeUnit, @NonNull Activity activity, @NonNull OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        zza(zzbq.zzgh(str), j, timeUnit, (Activity) zzbq.checkNotNull(activity), TaskExecutors.MAIN_THREAD, (OnVerificationStateChangedCallbacks) zzbq.checkNotNull(onVerificationStateChangedCallbacks), null);
    }

    public void verifyPhoneNumber(@NonNull String str, long j, TimeUnit timeUnit, @NonNull Activity activity, @NonNull OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable ForceResendingToken forceResendingToken) {
        zza(zzbq.zzgh(str), j, timeUnit, (Activity) zzbq.checkNotNull(activity), TaskExecutors.MAIN_THREAD, (OnVerificationStateChangedCallbacks) zzbq.checkNotNull(onVerificationStateChangedCallbacks), forceResendingToken);
    }

    public void verifyPhoneNumber(@NonNull String str, long j, TimeUnit timeUnit, @NonNull Executor executor, @NonNull OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks) {
        zza(zzbq.zzgh(str), j, timeUnit, null, (Executor) zzbq.checkNotNull(executor), (OnVerificationStateChangedCallbacks) zzbq.checkNotNull(onVerificationStateChangedCallbacks), null);
    }

    public void verifyPhoneNumber(@NonNull String str, long j, TimeUnit timeUnit, @NonNull Executor executor, @NonNull OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, @Nullable ForceResendingToken forceResendingToken) {
        zza(zzbq.zzgh(str), j, timeUnit, null, (Executor) zzbq.checkNotNull(executor), (OnVerificationStateChangedCallbacks) zzbq.checkNotNull(onVerificationStateChangedCallbacks), forceResendingToken);
    }
}

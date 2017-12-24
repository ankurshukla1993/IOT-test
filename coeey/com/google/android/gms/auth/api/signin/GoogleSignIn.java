package com.google.android.gms.auth.api.signin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder;
import com.google.android.gms.auth.api.signin.internal.zze;
import com.google.android.gms.auth.api.signin.internal.zzo;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public final class GoogleSignIn {
    private GoogleSignIn() {
    }

    public static GoogleSignInClient getClient(@NonNull Activity activity, @NonNull GoogleSignInOptions googleSignInOptions) {
        return new GoogleSignInClient(activity, (GoogleSignInOptions) zzbq.checkNotNull(googleSignInOptions));
    }

    public static GoogleSignInClient getClient(@NonNull Context context, @NonNull GoogleSignInOptions googleSignInOptions) {
        return new GoogleSignInClient(context, (GoogleSignInOptions) zzbq.checkNotNull(googleSignInOptions));
    }

    @Nullable
    public static GoogleSignInAccount getLastSignedInAccount(Context context) {
        return zzo.zzbp(context).zzaay();
    }

    public static Task<GoogleSignInAccount> getSignedInAccountFromIntent(@Nullable Intent intent) {
        GoogleSignInResult signInResultFromIntent = zze.getSignInResultFromIntent(intent);
        return signInResultFromIntent == null ? Tasks.forException(zzb.zzy(Status.zzfkq)) : (!signInResultFromIntent.getStatus().isSuccess() || signInResultFromIntent.getSignInAccount() == null) ? Tasks.forException(zzb.zzy(signInResultFromIntent.getStatus())) : Tasks.forResult(signInResultFromIntent.getSignInAccount());
    }

    public static boolean hasPermissions(@Nullable GoogleSignInAccount googleSignInAccount, @NonNull GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        zzbq.checkNotNull(googleSignInOptionsExtension, "Please provide a non-null GoogleSignInOptionsExtension");
        return hasPermissions(googleSignInAccount, zzv(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static boolean hasPermissions(@Nullable GoogleSignInAccount googleSignInAccount, @NonNull Scope... scopeArr) {
        if (googleSignInAccount == null) {
            return false;
        }
        Collection hashSet = new HashSet();
        Collections.addAll(hashSet, scopeArr);
        return googleSignInAccount.getGrantedScopes().containsAll(hashSet);
    }

    public static void requestPermissions(@NonNull Activity activity, int i, @Nullable GoogleSignInAccount googleSignInAccount, @NonNull GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        zzbq.checkNotNull(activity, "Please provide a non-null Activity");
        zzbq.checkNotNull(googleSignInOptionsExtension, "Please provide a non-null GoogleSignInOptionsExtension");
        requestPermissions(activity, i, googleSignInAccount, zzv(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static void requestPermissions(@NonNull Activity activity, int i, @Nullable GoogleSignInAccount googleSignInAccount, @NonNull Scope... scopeArr) {
        zzbq.checkNotNull(activity, "Please provide a non-null Activity");
        activity.startActivityForResult(zza(activity, googleSignInAccount, scopeArr), i);
    }

    public static void requestPermissions(@NonNull Fragment fragment, int i, @Nullable GoogleSignInAccount googleSignInAccount, @NonNull GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        zzbq.checkNotNull(fragment, "Please provide a non-null Fragment");
        zzbq.checkNotNull(googleSignInOptionsExtension, "Please provide a non-null GoogleSignInOptionsExtension");
        requestPermissions(fragment, i, googleSignInAccount, zzv(googleSignInOptionsExtension.getImpliedScopes()));
    }

    public static void requestPermissions(@NonNull Fragment fragment, int i, @Nullable GoogleSignInAccount googleSignInAccount, @NonNull Scope... scopeArr) {
        zzbq.checkNotNull(fragment, "Please provide a non-null Fragment");
        fragment.startActivityForResult(zza(fragment.getActivity(), googleSignInAccount, scopeArr), i);
    }

    @NonNull
    private static Intent zza(@NonNull Activity activity, @Nullable GoogleSignInAccount googleSignInAccount, @NonNull Scope... scopeArr) {
        Builder builder = new Builder();
        if (scopeArr.length > 0) {
            builder.requestScopes(scopeArr[0], scopeArr);
        }
        if (!(googleSignInAccount == null || TextUtils.isEmpty(googleSignInAccount.getEmail()))) {
            builder.setAccountName(googleSignInAccount.getEmail());
        }
        return new GoogleSignInClient(activity, builder.build()).getSignInIntent();
    }

    @NonNull
    private static Scope[] zzv(@Nullable List<Scope> list) {
        return list == null ? new Scope[0] : (Scope[]) list.toArray(new Scope[list.size()]);
    }
}

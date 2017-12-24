package com.biz.health.cooey_app.account;

import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.auth.FirebaseUser;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "firebaseAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "onAuthStateChanged"}, k = 3, mv = {1, 1, 7})
/* compiled from: CooeyLoginActivity.kt */
final class CooeyLoginActivity$onCreate$3 implements AuthStateListener {
    final /* synthetic */ CooeyLoginActivity this$0;

    CooeyLoginActivity$onCreate$3(CooeyLoginActivity cooeyLoginActivity) {
        this.this$0 = cooeyLoginActivity;
    }

    public final void onAuthStateChanged(@NotNull FirebaseAuth firebaseAuth) {
        Intrinsics.checkParameterIsNotNull(firebaseAuth, "firebaseAuth");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            Log.d(this.this$0.TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
            Log.d(this.this$0.TAG, "onAuthStateChanged:signed_out");
        }
    }
}

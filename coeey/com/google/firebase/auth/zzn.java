package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzn implements Continuation<GetTokenResult, Task<Void>> {
    private /* synthetic */ FirebaseUser zzlze;

    zzn(FirebaseUser firebaseUser) {
        this.zzlze = firebaseUser;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        return FirebaseAuth.getInstance(this.zzlze.zzbpm()).zzof(((GetTokenResult) task.getResult()).getToken());
    }
}

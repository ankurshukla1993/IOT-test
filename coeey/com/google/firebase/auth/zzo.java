package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;

final class zzo implements Continuation<GetTokenResult, Task<Void>> {
    private /* synthetic */ FirebaseUser zzlze;
    private /* synthetic */ ActionCodeSettings zzlzf;

    zzo(FirebaseUser firebaseUser, ActionCodeSettings actionCodeSettings) {
        this.zzlze = firebaseUser;
        this.zzlzf = actionCodeSettings;
    }

    public final /* synthetic */ Object then(@NonNull Task task) throws Exception {
        return FirebaseAuth.getInstance(this.zzlze.zzbpm()).zza(this.zzlzf, ((GetTokenResult) task.getResult()).getToken());
    }
}

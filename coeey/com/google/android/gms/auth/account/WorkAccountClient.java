package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi$zza;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.internal.zzasv;
import com.google.android.gms.tasks.Task;

public class WorkAccountClient extends GoogleApi<NoOptions> {
    private final WorkAccountApi zzdzs = new zzasv();

    WorkAccountClient(@NonNull Activity activity) {
        super(activity, WorkAccount.API, null, GoogleApi$zza.zzfjp);
    }

    WorkAccountClient(@NonNull Context context) {
        super(context, WorkAccount.API, null, GoogleApi$zza.zzfjp);
    }

    public Task<Account> addWorkAccount(String str) {
        return zzbj.zza(this.zzdzs.addWorkAccount(zzagb(), str), new zzg(this));
    }

    public Task<Void> removeWorkAccount(Account account) {
        return zzbj.zzb(this.zzdzs.removeWorkAccount(zzagb(), account));
    }

    public Task<Void> setWorkAuthenticatorEnabled(boolean z) {
        return zzbj.zzb(this.zzdzs.setWorkAuthenticatorEnabledWithResult(zzagb(), z));
    }
}

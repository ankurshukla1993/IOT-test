package com.google.android.gms.internal;

import android.accounts.Account;
import com.google.android.gms.auth.account.WorkAccountApi$AddAccountResult;
import com.google.android.gms.common.api.Status;

final class zzatc implements WorkAccountApi$AddAccountResult {
    private final Status mStatus;
    private final Account zzdzb;

    public zzatc(Status status, Account account) {
        this.mStatus = status;
        this.zzdzb = account;
    }

    public final Account getAccount() {
        return this.zzdzb;
    }

    public final Status getStatus() {
        return this.mStatus;
    }
}

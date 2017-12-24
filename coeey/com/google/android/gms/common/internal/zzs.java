package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcwc;
import java.util.Collection;

public final class zzs {
    private String zzdyu;
    private Account zzdzb;
    private int zzfjw = 0;
    private String zzfjy;
    private zzcwc zzfwo = zzcwc.zzjyz;
    private ArraySet<Scope> zzfwq;

    public final zzr zzakr() {
        return new zzr(this.zzdzb, this.zzfwq, null, 0, null, this.zzdyu, this.zzfjy, this.zzfwo);
    }

    public final zzs zze(Account account) {
        this.zzdzb = account;
        return this;
    }

    public final zzs zze(Collection<Scope> collection) {
        if (this.zzfwq == null) {
            this.zzfwq = new ArraySet();
        }
        this.zzfwq.addAll(collection);
        return this;
    }

    public final zzs zzga(String str) {
        this.zzdyu = str;
        return this;
    }

    public final zzs zzgb(String str) {
        this.zzfjy = str;
        return this;
    }
}

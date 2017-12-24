package com.google.android.gms.auth.api.signin.internal;

public final class zzp {
    private static int zzefj = 31;
    private int zzefk = 1;

    public final int zzaba() {
        return this.zzefk;
    }

    public final zzp zzaq(boolean z) {
        this.zzefk = (z ? 1 : 0) + (this.zzefk * zzefj);
        return this;
    }

    public final zzp zzr(Object obj) {
        this.zzefk = (obj == null ? 0 : obj.hashCode()) + (this.zzefk * zzefj);
        return this;
    }
}

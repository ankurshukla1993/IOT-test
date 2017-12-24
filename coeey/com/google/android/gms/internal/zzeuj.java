package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzeuj {
    private String zzdzn;

    public zzeuj(@Nullable String str) {
        this.zzdzn = str;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzeuj)) {
            return false;
        }
        return zzbg.equal(this.zzdzn, ((zzeuj) obj).zzdzn);
    }

    @Nullable
    public final String getToken() {
        return this.zzdzn;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzdzn});
    }

    public final String toString() {
        return zzbg.zzw(this).zzg("token", this.zzdzn).toString();
    }
}

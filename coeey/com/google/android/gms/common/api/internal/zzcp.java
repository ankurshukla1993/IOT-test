package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzcp {
    private final Set<zzcl<?>> zzewx = Collections.newSetFromMap(new WeakHashMap());

    public static <L> zzcl<L> zzb(@NonNull L l, @NonNull Looper looper, @NonNull String str) {
        zzbq.checkNotNull(l, "Listener must not be null");
        zzbq.checkNotNull(looper, "Looper must not be null");
        zzbq.checkNotNull(str, "Listener type must not be null");
        return new zzcl(looper, l, str);
    }

    public static <L> zzcn<L> zzb(@NonNull L l, @NonNull String str) {
        zzbq.checkNotNull(l, "Listener must not be null");
        zzbq.checkNotNull(str, "Listener type must not be null");
        zzbq.zzh(str, "Listener type must not be empty");
        return new zzcn(l, str);
    }

    public final void release() {
        for (zzcl clear : this.zzewx) {
            clear.clear();
        }
        this.zzewx.clear();
    }

    public final <L> zzcl<L> zza(@NonNull L l, @NonNull Looper looper, @NonNull String str) {
        zzcl<L> zzb = zzb(l, looper, str);
        this.zzewx.add(zzb);
        return zzb;
    }
}

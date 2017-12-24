package com.google.android.gms.internal;

import java.lang.ref.ReferenceQueue;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

final class zzdth {
    private final ConcurrentHashMap<zzdti, List<Throwable>> zzlwb = new ConcurrentHashMap(16, 0.75f, 10);
    private final ReferenceQueue<Throwable> zzlwc = new ReferenceQueue();

    zzdth() {
    }

    public final List<Throwable> zza(Throwable th, boolean z) {
        Object poll = this.zzlwc.poll();
        while (poll != null) {
            this.zzlwb.remove(poll);
            poll = this.zzlwc.poll();
        }
        return (List) this.zzlwb.get(new zzdti(th, null));
    }
}

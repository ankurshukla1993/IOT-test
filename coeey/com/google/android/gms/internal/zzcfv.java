package com.google.android.gms.internal;

import java.util.Iterator;

final class zzcfv implements Iterator<String> {
    private Iterator<String> zziww = this.zziwx.zzdyg.keySet().iterator();
    private /* synthetic */ zzcfu zziwx;

    zzcfv(zzcfu com_google_android_gms_internal_zzcfu) {
        this.zziwx = com_google_android_gms_internal_zzcfu;
    }

    public final boolean hasNext() {
        return this.zziww.hasNext();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zziww.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}

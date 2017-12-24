package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzfga implements Iterator<Entry<K, V>> {
    private int pos;
    private /* synthetic */ zzffu zzped;
    private boolean zzpee;
    private Iterator<Entry<K, V>> zzpef;

    private zzfga(zzffu com_google_android_gms_internal_zzffu) {
        this.zzped = com_google_android_gms_internal_zzffu;
        this.pos = -1;
    }

    private final Iterator<Entry<K, V>> zzcwp() {
        if (this.zzpef == null) {
            this.zzpef = this.zzped.zzpdx.entrySet().iterator();
        }
        return this.zzpef;
    }

    public final boolean hasNext() {
        return this.pos + 1 < this.zzped.zzpdw.size() || zzcwp().hasNext();
    }

    public final /* synthetic */ Object next() {
        this.zzpee = true;
        int i = this.pos + 1;
        this.pos = i;
        return i < this.zzped.zzpdw.size() ? (Entry) this.zzped.zzpdw.get(this.pos) : (Entry) zzcwp().next();
    }

    public final void remove() {
        if (this.zzpee) {
            this.zzpee = false;
            this.zzped.zzcwl();
            if (this.pos < this.zzped.zzpdw.size()) {
                zzffu com_google_android_gms_internal_zzffu = this.zzped;
                int i = this.pos;
                this.pos = i - 1;
                com_google_android_gms_internal_zzffu.zzlr(i);
                return;
            }
            zzcwp().remove();
            return;
        }
        throw new IllegalStateException("remove() was called before next()");
    }
}

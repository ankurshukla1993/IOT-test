package com.google.android.gms.internal;

import java.util.Iterator;

final class zzdzj implements Iterator<zzdzk> {
    private int zzmhz = (this.zzmia.length - 1);
    private /* synthetic */ zzdzi zzmia;

    zzdzj(zzdzi com_google_android_gms_internal_zzdzi) {
        this.zzmia = com_google_android_gms_internal_zzdzi;
    }

    public final boolean hasNext() {
        return this.zzmhz >= 0;
    }

    public final /* synthetic */ Object next() {
        boolean z = true;
        long zzb = this.zzmia.value & ((long) (1 << this.zzmhz));
        zzdzk com_google_android_gms_internal_zzdzk = new zzdzk();
        if (zzb != 0) {
            z = false;
        }
        com_google_android_gms_internal_zzdzk.zzmib = z;
        com_google_android_gms_internal_zzdzk.zzmic = (int) Math.pow(2.0d, (double) this.zzmhz);
        this.zzmhz--;
        return com_google_android_gms_internal_zzdzk;
    }

    public final void remove() {
    }
}

package com.google.android.gms.dynamic;

import java.util.Iterator;

final class zzb implements zzo<T> {
    private /* synthetic */ zza zzgtn;

    zzb(zza com_google_android_gms_dynamic_zza) {
        this.zzgtn = com_google_android_gms_dynamic_zza;
    }

    public final void zza(T t) {
        this.zzgtn.zzgtj = t;
        Iterator it = this.zzgtn.zzgtl.iterator();
        while (it.hasNext()) {
            ((zzi) it.next()).zzb(this.zzgtn.zzgtj);
        }
        this.zzgtn.zzgtl.clear();
        this.zzgtn.zzgtk = null;
    }
}

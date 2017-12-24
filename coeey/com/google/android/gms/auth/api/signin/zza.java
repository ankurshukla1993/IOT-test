package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Scope;
import java.util.Comparator;

final /* synthetic */ class zza implements Comparator {
    static final Comparator zzedz = new zza();

    private zza() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((Scope) obj).zzagj().compareTo(((Scope) obj2).zzagj());
    }
}

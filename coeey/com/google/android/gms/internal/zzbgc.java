package com.google.android.gms.internal;

import android.content.Context;

public final class zzbgc {
    private static zzbgc zzgcn = new zzbgc();
    private zzbgb zzgcm = null;

    private final synchronized zzbgb zzcx(Context context) {
        if (this.zzgcm == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.zzgcm = new zzbgb(context);
        }
        return this.zzgcm;
    }

    public static zzbgb zzcy(Context context) {
        return zzgcn.zzcx(context);
    }
}

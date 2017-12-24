package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzc implements Callable<Boolean> {
    private /* synthetic */ SharedPreferences zzhgd;
    private /* synthetic */ String zzhge;
    private /* synthetic */ Boolean zzhgf;

    zzc(SharedPreferences sharedPreferences, String str, Boolean bool) {
        this.zzhgd = sharedPreferences;
        this.zzhge = str;
        this.zzhgf = bool;
    }

    public final /* synthetic */ Object call() throws Exception {
        return Boolean.valueOf(this.zzhgd.getBoolean(this.zzhge, this.zzhgf.booleanValue()));
    }
}

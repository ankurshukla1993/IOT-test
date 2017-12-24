package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzg implements Callable<Long> {
    private /* synthetic */ SharedPreferences zzhgd;
    private /* synthetic */ String zzhge;
    private /* synthetic */ Long zzhgh;

    zzg(SharedPreferences sharedPreferences, String str, Long l) {
        this.zzhgd = sharedPreferences;
        this.zzhge = str;
        this.zzhgh = l;
    }

    public final /* synthetic */ Object call() throws Exception {
        return Long.valueOf(this.zzhgd.getLong(this.zzhge, this.zzhgh.longValue()));
    }
}

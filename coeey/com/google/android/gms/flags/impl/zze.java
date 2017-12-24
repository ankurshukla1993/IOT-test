package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zze implements Callable<Integer> {
    private /* synthetic */ SharedPreferences zzhgd;
    private /* synthetic */ String zzhge;
    private /* synthetic */ Integer zzhgg;

    zze(SharedPreferences sharedPreferences, String str, Integer num) {
        this.zzhgd = sharedPreferences;
        this.zzhge = str;
        this.zzhgg = num;
    }

    public final /* synthetic */ Object call() throws Exception {
        return Integer.valueOf(this.zzhgd.getInt(this.zzhge, this.zzhgg.intValue()));
    }
}

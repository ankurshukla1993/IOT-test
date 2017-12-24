package com.google.android.gms.flags.impl;

import android.content.SharedPreferences;
import java.util.concurrent.Callable;

final class zzi implements Callable<String> {
    private /* synthetic */ SharedPreferences zzhgd;
    private /* synthetic */ String zzhge;
    private /* synthetic */ String zzhgi;

    zzi(SharedPreferences sharedPreferences, String str, String str2) {
        this.zzhgd = sharedPreferences;
        this.zzhge = str;
        this.zzhgi = str2;
    }

    public final /* synthetic */ Object call() throws Exception {
        return this.zzhgd.getString(this.zzhge, this.zzhgi);
    }
}

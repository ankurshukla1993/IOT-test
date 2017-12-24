package com.google.android.gms.flags.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.internal.zzbzw;

@DynamiteApi
public class FlagProviderImpl extends zzbzw {
    private boolean zzaqh = false;
    private SharedPreferences zzbfu;

    public boolean getBooleanFlagValue(String str, boolean z, int i) {
        return !this.zzaqh ? z : zzb.zza(this.zzbfu, str, Boolean.valueOf(z)).booleanValue();
    }

    public int getIntFlagValue(String str, int i, int i2) {
        return !this.zzaqh ? i : zzd.zza(this.zzbfu, str, Integer.valueOf(i)).intValue();
    }

    public long getLongFlagValue(String str, long j, int i) {
        return !this.zzaqh ? j : zzf.zza(this.zzbfu, str, Long.valueOf(j)).longValue();
    }

    public String getStringFlagValue(String str, String str2, int i) {
        return !this.zzaqh ? str2 : zzh.zza(this.zzbfu, str, str2);
    }

    public void init(IObjectWrapper iObjectWrapper) {
        Context context = (Context) zzn.zzx(iObjectWrapper);
        if (!this.zzaqh) {
            try {
                this.zzbfu = zzj.zzdf(context.createPackageContext("com.google.android.gms", 0));
                this.zzaqh = true;
            } catch (NameNotFoundException e) {
            } catch (Exception e2) {
                String str = "FlagProviderImpl";
                String str2 = "Could not retrieve sdk flags, continuing with defaults: ";
                String valueOf = String.valueOf(e2.getMessage());
                Log.w(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
        }
    }
}

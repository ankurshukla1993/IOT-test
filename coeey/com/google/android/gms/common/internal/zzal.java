package com.google.android.gms.common.internal;

import android.util.Log;

public final class zzal {
    private static int zzfyc = 15;
    private static final String zzfyd = null;
    private final String zzfye;
    private final String zzfyf;

    public zzal(String str) {
        this(str, null);
    }

    public zzal(String str, String str2) {
        zzbq.checkNotNull(str, "log tag cannot be null");
        zzbq.zzb(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, Integer.valueOf(23));
        this.zzfye = str;
        if (str2 == null || str2.length() <= 0) {
            this.zzfyf = null;
        } else {
            this.zzfyf = str2;
        }
    }

    private final boolean zzch(int i) {
        return Log.isLoggable(this.zzfye, i);
    }

    private final String zzgg(String str) {
        return this.zzfyf == null ? str : this.zzfyf.concat(str);
    }

    private final String zzh(String str, Object... objArr) {
        String format = String.format(str, objArr);
        return this.zzfyf == null ? format : this.zzfyf.concat(format);
    }

    public final void zzb(String str, String str2, Throwable th) {
        if (zzch(4)) {
            Log.i(str, zzgg(str2), th);
        }
    }

    public final void zzb(String str, String str2, Object... objArr) {
        if (zzch(3)) {
            Log.d(str, zzh(str2, objArr));
        }
    }

    public final void zzc(String str, String str2, Throwable th) {
        if (zzch(5)) {
            Log.w(str, zzgg(str2), th);
        }
    }

    public final void zzc(String str, String str2, Object... objArr) {
        if (zzch(5)) {
            Log.w(this.zzfye, zzh(str2, objArr));
        }
    }

    public final void zzd(String str, String str2, Throwable th) {
        if (zzch(6)) {
            Log.e(str, zzgg(str2), th);
        }
    }

    public final void zzd(String str, String str2, Object... objArr) {
        if (zzch(6)) {
            Log.e(str, zzh(str2, objArr));
        }
    }

    public final void zzu(String str, String str2) {
        if (zzch(3)) {
            Log.d(str, zzgg(str2));
        }
    }

    public final void zzv(String str, String str2) {
        if (zzch(5)) {
            Log.w(str, zzgg(str2));
        }
    }

    public final void zzw(String str, String str2) {
        if (zzch(6)) {
            Log.e(str, zzgg(str2));
        }
    }
}

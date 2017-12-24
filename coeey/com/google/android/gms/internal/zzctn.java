package com.google.android.gms.internal;

import android.net.Uri;

public final class zzctn {
    private final String zzjuq;
    private final Uri zzjur;
    private final String zzjus;
    private final String zzjut;
    private final boolean zzjuu;
    private final boolean zzjuv;

    public zzctn(Uri uri) {
        this(null, uri, "", "", false, false);
    }

    private zzctn(String str, Uri uri, String str2, String str3, boolean z, boolean z2) {
        this.zzjuq = str;
        this.zzjur = uri;
        this.zzjus = str2;
        this.zzjut = str3;
        this.zzjuu = z;
        this.zzjuv = z2;
    }

    private final String zzkq(String str) {
        if (this.zzjuu) {
            return null;
        }
        String valueOf = String.valueOf(this.zzjus);
        String valueOf2 = String.valueOf(str);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    public final zzctg<String> zzaw(String str, String str2) {
        return zzctg.zza(this, str, null);
    }

    public final zzctn zzko(String str) {
        if (this.zzjuu) {
            throw new IllegalStateException("Cannot set GServices prefix and skip GServices");
        }
        return new zzctn(this.zzjuq, this.zzjur, str, this.zzjut, this.zzjuu, this.zzjuv);
    }

    public final zzctn zzkp(String str) {
        return new zzctn(this.zzjuq, this.zzjur, this.zzjus, str, this.zzjuu, this.zzjuv);
    }
}

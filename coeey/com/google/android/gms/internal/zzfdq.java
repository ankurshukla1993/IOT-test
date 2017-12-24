package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;

public abstract class zzfdq {
    private static volatile boolean zzpav = true;
    int zzpar;
    int zzpas;
    int zzpat;
    private boolean zzpau;

    private zzfdq() {
        this.zzpas = 100;
        this.zzpat = Integer.MAX_VALUE;
        this.zzpau = false;
    }

    static zzfdq zzb(byte[] bArr, int i, int i2, boolean z) {
        zzfdq com_google_android_gms_internal_zzfds = new zzfds(bArr, i, i2, z, null);
        try {
            com_google_android_gms_internal_zzfds.zzki(i2);
            return com_google_android_gms_internal_zzfds;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static zzfdq zzba(byte[] bArr) {
        return zzb(bArr, 0, bArr.length, false);
    }

    public static long zzcr(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }

    public static zzfdq zzg(byte[] bArr, int i, int i2) {
        return zzb(bArr, i, i2, false);
    }

    public static zzfdq zzi(InputStream inputStream) {
        if (inputStream != null) {
            return new zzfdt(inputStream, 4096, null);
        }
        byte[] bArr = zzfer.EMPTY_BYTE_ARRAY;
        return zzb(bArr, 0, bArr.length, false);
    }

    public static int zzkl(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public abstract double readDouble() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract String readString() throws IOException;

    public abstract <T extends zzfee<T, ?>> T zza(T t, zzfea com_google_android_gms_internal_zzfea) throws IOException;

    public abstract void zza(zzffj com_google_android_gms_internal_zzffj, zzfea com_google_android_gms_internal_zzfea) throws IOException;

    public abstract int zzcts() throws IOException;

    public abstract long zzctt() throws IOException;

    public abstract long zzctu() throws IOException;

    public abstract int zzctv() throws IOException;

    public abstract long zzctw() throws IOException;

    public abstract int zzctx() throws IOException;

    public abstract boolean zzcty() throws IOException;

    public abstract String zzctz() throws IOException;

    public abstract zzfdh zzcua() throws IOException;

    public abstract int zzcub() throws IOException;

    public abstract int zzcuc() throws IOException;

    public abstract int zzcud() throws IOException;

    public abstract long zzcue() throws IOException;

    public abstract int zzcuf() throws IOException;

    public abstract long zzcug() throws IOException;

    public abstract int zzcuh() throws IOException;

    abstract long zzcui() throws IOException;

    public abstract int zzcuj();

    public abstract boolean zzcuk() throws IOException;

    public abstract int zzcul();

    public abstract void zzkf(int i) throws zzfew;

    public abstract boolean zzkg(int i) throws IOException;

    public final int zzkh(int i) {
        int i2 = this.zzpat;
        this.zzpat = Integer.MAX_VALUE;
        return i2;
    }

    public abstract int zzki(int i) throws zzfew;

    public abstract void zzkj(int i);

    public abstract void zzkk(int i) throws IOException;
}

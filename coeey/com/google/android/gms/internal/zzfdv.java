package com.google.android.gms.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class zzfdv extends zzfdg {
    private static final Logger logger = Logger.getLogger(zzfdv.class.getName());
    private static final boolean zzpbf = zzfgj.zzcww();

    private zzfdv() {
    }

    public static int zza(zzffc com_google_android_gms_internal_zzffc) {
        int zzhl = com_google_android_gms_internal_zzffc.zzhl();
        return zzhl + zzky(zzhl);
    }

    public static int zzad(int i, int i2) {
        return zzkw(i) + zzkx(i2);
    }

    public static int zzae(int i, int i2) {
        return zzkw(i) + zzky(i2);
    }

    public static int zzaf(int i, int i2) {
        return zzkw(i) + 4;
    }

    public static int zzag(int i, int i2) {
        return zzkw(i) + zzkx(i2);
    }

    public static int zzan(zzfdh com_google_android_gms_internal_zzfdh) {
        int size = com_google_android_gms_internal_zzfdh.size();
        return size + zzky(size);
    }

    public static int zzb(int i, double d) {
        return zzkw(i) + 8;
    }

    public static int zzb(int i, zzfdh com_google_android_gms_internal_zzfdh) {
        int zzkw = zzkw(i);
        int size = com_google_android_gms_internal_zzfdh.size();
        return zzkw + (size + zzky(size));
    }

    public static int zzb(int i, zzffi com_google_android_gms_internal_zzffi) {
        return zzkw(i) + zze(com_google_android_gms_internal_zzffi);
    }

    public static zzfdv zzb(OutputStream outputStream, int i) {
        return new zzd(outputStream, i);
    }

    public static zzfdv zzbb(byte[] bArr) {
        return zzh(bArr, 0, bArr.length);
    }

    public static int zzbc(byte[] bArr) {
        int length = bArr.length;
        return length + zzky(length);
    }

    public static int zzc(int i, long j) {
        return zzkw(i) + zzcw(j);
    }

    public static int zzcv(long j) {
        return zzcw(j);
    }

    public static int zzcw(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        long j2;
        int i = 2;
        if ((-34359738368L & j) != 0) {
            i = 6;
            j2 = j >>> 28;
        } else {
            j2 = j;
        }
        if ((-2097152 & j2) != 0) {
            i += 2;
            j2 >>>= 14;
        }
        return (j2 & -16384) != 0 ? i + 1 : i;
    }

    public static int zzcx(long j) {
        return zzcw(zzda(j));
    }

    public static int zzcy(long j) {
        return 8;
    }

    public static int zzcz(long j) {
        return 8;
    }

    public static int zzd(int i, long j) {
        return zzkw(i) + zzcw(j);
    }

    public static int zzda(boolean z) {
        return 1;
    }

    private static long zzda(long j) {
        return (j << 1) ^ (j >> 63);
    }

    public static int zze(int i, long j) {
        return zzkw(i) + 8;
    }

    public static int zze(zzffi com_google_android_gms_internal_zzffi) {
        int zzhl = com_google_android_gms_internal_zzffi.zzhl();
        return zzhl + zzky(zzhl);
    }

    public static int zzf(float f) {
        return 4;
    }

    @Deprecated
    public static int zzf(zzffi com_google_android_gms_internal_zzffi) {
        return com_google_android_gms_internal_zzffi.zzhl();
    }

    public static zzfdv zzh(byte[] bArr, int i, int i2) {
        return new zzb(bArr, i, i2);
    }

    static int zzkr(int i) {
        return i > 4096 ? 4096 : i;
    }

    public static int zzkw(int i) {
        return zzky(i << 3);
    }

    public static int zzkx(int i) {
        return i >= 0 ? zzky(i) : 10;
    }

    public static int zzky(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzkz(int i) {
        return zzky(zzle(i));
    }

    public static int zzla(int i) {
        return 4;
    }

    public static int zzlb(int i) {
        return 4;
    }

    public static int zzlc(int i) {
        return zzkx(i);
    }

    static int zzld(int i) {
        return zzky(i) + i;
    }

    private static int zzle(int i) {
        return (i << 1) ^ (i >> 31);
    }

    public static int zzm(int i, boolean z) {
        return zzkw(i) + 1;
    }

    public static int zzn(double d) {
        return 8;
    }

    public static int zzo(int i, String str) {
        return zzkw(i) + zztd(str);
    }

    public static int zztd(String str) {
        int zzd;
        try {
            zzd = zzfgl.zzd(str);
        } catch (zzfgo e) {
            zzd = str.getBytes(zzfer.UTF_8).length;
        }
        return zzd + zzky(zzd);
    }

    public abstract void flush() throws IOException;

    public abstract void write(byte[] bArr, int i, int i2) throws IOException;

    public final void zza(int i, double d) throws IOException {
        zzb(i, Double.doubleToRawLongBits(d));
    }

    public abstract void zza(int i, long j) throws IOException;

    public abstract void zza(int i, zzfdh com_google_android_gms_internal_zzfdh) throws IOException;

    public abstract void zza(int i, zzffi com_google_android_gms_internal_zzffi) throws IOException;

    final void zza(String str, zzfgo com_google_android_gms_internal_zzfgo) throws IOException {
        logger.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", com_google_android_gms_internal_zzfgo);
        byte[] bytes = str.getBytes(zzfer.UTF_8);
        try {
            zzkt(bytes.length);
            zzd(bytes, 0, bytes.length);
        } catch (Throwable e) {
            throw new zzc(e);
        } catch (zzc e2) {
            throw e2;
        }
    }

    public abstract void zzaa(int i, int i2) throws IOException;

    public abstract void zzab(int i, int i2) throws IOException;

    public abstract void zzac(int i, int i2) throws IOException;

    public abstract void zzam(zzfdh com_google_android_gms_internal_zzfdh) throws IOException;

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzb(int i, long j) throws IOException;

    public abstract void zzcs(long j) throws IOException;

    public final void zzct(long j) throws IOException {
        zzcs(zzda(j));
    }

    public abstract void zzcu(long j) throws IOException;

    public abstract int zzcur();

    public final void zzcus() {
        if (zzcur() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public abstract void zzd(zzffi com_google_android_gms_internal_zzffi) throws IOException;

    abstract void zzi(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzks(int i) throws IOException;

    public abstract void zzkt(int i) throws IOException;

    public final void zzku(int i) throws IOException {
        zzkt(zzle(i));
    }

    public abstract void zzkv(int i) throws IOException;

    public abstract void zzl(int i, boolean z) throws IOException;

    public abstract void zzn(int i, String str) throws IOException;

    public abstract void zztc(String str) throws IOException;

    public abstract void zzz(int i, int i2) throws IOException;
}

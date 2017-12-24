package com.google.android.gms.internal;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzfgj {
    private static final Logger logger = Logger.getLogger(zzfgj.class.getName());
    private static final Unsafe zzloi = zzcwy();
    private static final boolean zzpbf = zzcwz();
    private static final Class<?> zzpeo = zztg("libcore.io.Memory");
    private static final boolean zzpep = (zztg("org.robolectric.Robolectric") != null);
    private static final boolean zzpeq = zzj(Long.TYPE);
    private static final boolean zzper = zzj(Integer.TYPE);
    private static final zzd zzpes;
    private static final boolean zzpet = zzcxa();
    private static final long zzpeu = ((long) zzh(byte[].class));
    private static final long zzpev = ((long) zzh(boolean[].class));
    private static final long zzpew = ((long) zzi(boolean[].class));
    private static final long zzpex = ((long) zzh(int[].class));
    private static final long zzpey = ((long) zzi(int[].class));
    private static final long zzpez = ((long) zzh(long[].class));
    private static final long zzpfa = ((long) zzi(long[].class));
    private static final long zzpfb = ((long) zzh(float[].class));
    private static final long zzpfc = ((long) zzi(float[].class));
    private static final long zzpfd = ((long) zzh(double[].class));
    private static final long zzpfe = ((long) zzi(double[].class));
    private static final long zzpff = ((long) zzh(Object[].class));
    private static final long zzpfg = ((long) zzi(Object[].class));
    private static final long zzpfh;
    private static final boolean zzpfi;

    static abstract class zzd {
        Unsafe zzpfj;

        zzd(Unsafe unsafe) {
            this.zzpfj = unsafe;
        }

        public abstract void zze(Object obj, long j, byte b);

        public abstract byte zzf(Object obj, long j);
    }

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzfgj.zzpfi) {
                zzfgj.zza(obj, j, b);
            } else {
                zzfgj.zzb(obj, j, b);
            }
        }

        public final byte zzf(Object obj, long j) {
            return zzfgj.zzpfi ? zzfgj.zzb(obj, j) : zzfgj.zzc(obj, j);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zze(Object obj, long j, byte b) {
            if (zzfgj.zzpfi) {
                zzfgj.zza(obj, j, b);
            } else {
                zzfgj.zzb(obj, j, b);
            }
        }

        public final byte zzf(Object obj, long j) {
            return zzfgj.zzpfi ? zzfgj.zzb(obj, j) : zzfgj.zzc(obj, j);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        public final void zze(Object obj, long j, byte b) {
            this.zzpfj.putByte(obj, j, b);
        }

        public final byte zzf(Object obj, long j) {
            return this.zzpfj.getByte(obj, j);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r3 = 0;
        r1 = 1;
        r2 = 0;
        r0 = com.google.android.gms.internal.zzfgj.class;
        r0 = r0.getName();
        r0 = java.util.logging.Logger.getLogger(r0);
        logger = r0;
        r0 = zzcwy();
        zzloi = r0;
        r0 = "libcore.io.Memory";
        r0 = zztg(r0);
        zzpeo = r0;
        r0 = "org.robolectric.Robolectric";
        r0 = zztg(r0);
        if (r0 == 0) goto L_0x00e5;
    L_0x0025:
        r0 = r1;
    L_0x0026:
        zzpep = r0;
        r0 = java.lang.Long.TYPE;
        r0 = zzj(r0);
        zzpeq = r0;
        r0 = java.lang.Integer.TYPE;
        r0 = zzj(r0);
        zzper = r0;
        r0 = zzloi;
        if (r0 != 0) goto L_0x00e8;
    L_0x003c:
        r0 = r3;
    L_0x003d:
        zzpes = r0;
        r0 = zzcxa();
        zzpet = r0;
        r0 = zzcwz();
        zzpbf = r0;
        r0 = byte[].class;
        r0 = zzh(r0);
        r4 = (long) r0;
        zzpeu = r4;
        r0 = boolean[].class;
        r0 = zzh(r0);
        r4 = (long) r0;
        zzpev = r4;
        r0 = boolean[].class;
        r0 = zzi(r0);
        r4 = (long) r0;
        zzpew = r4;
        r0 = int[].class;
        r0 = zzh(r0);
        r4 = (long) r0;
        zzpex = r4;
        r0 = int[].class;
        r0 = zzi(r0);
        r4 = (long) r0;
        zzpey = r4;
        r0 = long[].class;
        r0 = zzh(r0);
        r4 = (long) r0;
        zzpez = r4;
        r0 = long[].class;
        r0 = zzi(r0);
        r4 = (long) r0;
        zzpfa = r4;
        r0 = float[].class;
        r0 = zzh(r0);
        r4 = (long) r0;
        zzpfb = r4;
        r0 = float[].class;
        r0 = zzi(r0);
        r4 = (long) r0;
        zzpfc = r4;
        r0 = double[].class;
        r0 = zzh(r0);
        r4 = (long) r0;
        zzpfd = r4;
        r0 = double[].class;
        r0 = zzi(r0);
        r4 = (long) r0;
        zzpfe = r4;
        r0 = java.lang.Object[].class;
        r0 = zzh(r0);
        r4 = (long) r0;
        zzpff = r4;
        r0 = java.lang.Object[].class;
        r0 = zzi(r0);
        r4 = (long) r0;
        zzpfg = r4;
        r0 = zzcxb();
        if (r0 == 0) goto L_0x0114;
    L_0x00c6:
        r0 = java.nio.Buffer.class;
        r3 = "effectiveDirectAddress";
        r0 = zza(r0, r3);
        if (r0 == 0) goto L_0x0114;
    L_0x00d0:
        if (r0 == 0) goto L_0x00d6;
    L_0x00d2:
        r3 = zzpes;
        if (r3 != 0) goto L_0x011d;
    L_0x00d6:
        r4 = -1;
    L_0x00d8:
        zzpfh = r4;
        r0 = java.nio.ByteOrder.nativeOrder();
        r3 = java.nio.ByteOrder.BIG_ENDIAN;
        if (r0 != r3) goto L_0x0126;
    L_0x00e2:
        zzpfi = r1;
        return;
    L_0x00e5:
        r0 = r2;
        goto L_0x0026;
    L_0x00e8:
        r0 = zzcxb();
        if (r0 == 0) goto L_0x010b;
    L_0x00ee:
        r0 = zzpeq;
        if (r0 == 0) goto L_0x00fb;
    L_0x00f2:
        r0 = new com.google.android.gms.internal.zzfgj$zzb;
        r3 = zzloi;
        r0.<init>(r3);
        goto L_0x003d;
    L_0x00fb:
        r0 = zzper;
        if (r0 == 0) goto L_0x0108;
    L_0x00ff:
        r0 = new com.google.android.gms.internal.zzfgj$zza;
        r3 = zzloi;
        r0.<init>(r3);
        goto L_0x003d;
    L_0x0108:
        r0 = r3;
        goto L_0x003d;
    L_0x010b:
        r0 = new com.google.android.gms.internal.zzfgj$zzc;
        r3 = zzloi;
        r0.<init>(r3);
        goto L_0x003d;
    L_0x0114:
        r0 = java.nio.Buffer.class;
        r3 = "address";
        r0 = zza(r0, r3);
        goto L_0x00d0;
    L_0x011d:
        r3 = zzpes;
        r3 = r3.zzpfj;
        r4 = r3.objectFieldOffset(r0);
        goto L_0x00d8;
    L_0x0126:
        r1 = r2;
        goto L_0x00e2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfgj.<clinit>():void");
    }

    private zzfgj() {
    }

    private static int zza(Object obj, long j) {
        return zzpes.zzpfj.getInt(obj, j);
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable th) {
            return null;
        }
    }

    private static void zza(Object obj, long j, byte b) {
        int i = ((((int) j) ^ -1) & 3) << 3;
        zza(obj, j & -4, (zza(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    private static void zza(Object obj, long j, int i) {
        zzpes.zzpfj.putInt(obj, j, i);
    }

    static void zza(byte[] bArr, long j, byte b) {
        zzpes.zze(bArr, zzpeu + j, b);
    }

    private static byte zzb(Object obj, long j) {
        return (byte) (zza(obj, -4 & j) >>> ((int) (((-1 ^ j) & 3) << 3)));
    }

    static byte zzb(byte[] bArr, long j) {
        return zzpes.zzf(bArr, zzpeu + j);
    }

    private static void zzb(Object obj, long j, byte b) {
        int i = (((int) j) & 3) << 3;
        zza(obj, j & -4, (zza(obj, j & -4) & ((255 << i) ^ -1)) | ((b & 255) << i));
    }

    private static byte zzc(Object obj, long j) {
        return (byte) (zza(obj, -4 & j) >>> ((int) ((3 & j) << 3)));
    }

    static boolean zzcww() {
        return zzpbf;
    }

    static boolean zzcwx() {
        return zzpet;
    }

    private static Unsafe zzcwy() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzfgk());
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean zzcwz() {
        if (zzloi == null) {
            return false;
        }
        try {
            Class cls = zzloi.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("arrayIndexScale", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzcxb()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzcxa() {
        if (zzloi == null) {
            return false;
        }
        try {
            Class cls = zzloi.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzcxb()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzcxb() {
        return (zzpeo == null || zzpep) ? false : true;
    }

    private static int zzh(Class<?> cls) {
        return zzpbf ? zzpes.zzpfj.arrayBaseOffset(cls) : -1;
    }

    private static int zzi(Class<?> cls) {
        return zzpbf ? zzpes.zzpfj.arrayIndexScale(cls) : -1;
    }

    private static boolean zzj(Class<?> cls) {
        if (!zzcxb()) {
            return false;
        }
        try {
            Class cls2 = zzpeo;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static <T> Class<T> zztg(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }
}

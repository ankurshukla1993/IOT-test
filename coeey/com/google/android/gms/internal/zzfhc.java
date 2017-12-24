package com.google.android.gms.internal;

import com.ihealth.communication.manager.iHealthDevicesManager;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;

public final class zzfhc {
    private final ByteBuffer zzpgx;

    private zzfhc(ByteBuffer byteBuffer) {
        this.zzpgx = byteBuffer;
        this.zzpgx.order(ByteOrder.LITTLE_ENDIAN);
    }

    private zzfhc(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    private static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int length = charSequence.length();
        int i3 = 0;
        int i4 = i + i2;
        while (i3 < length && i3 + i < i4) {
            char charAt = charSequence.charAt(i3);
            if (charAt >= '') {
                break;
            }
            bArr[i + i3] = (byte) charAt;
            i3++;
        }
        if (i3 == length) {
            return i + length;
        }
        int i5 = i + i3;
        while (i3 < length) {
            int i6;
            char charAt2 = charSequence.charAt(i3);
            if (charAt2 < '' && i5 < i4) {
                i6 = i5 + 1;
                bArr[i5] = (byte) charAt2;
            } else if (charAt2 < 'ࠀ' && i5 <= i4 - 2) {
                r6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 >>> 6) | 960);
                i6 = r6 + 1;
                bArr[r6] = (byte) ((charAt2 & 63) | 128);
            } else if ((charAt2 < '?' || '?' < charAt2) && i5 <= i4 - 3) {
                i6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 >>> 12) | 480);
                i5 = i6 + 1;
                bArr[i6] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i6 = i5 + 1;
                bArr[i5] = (byte) ((charAt2 & 63) | 128);
            } else if (i5 <= i4 - 4) {
                if (i3 + 1 != charSequence.length()) {
                    i3++;
                    charAt = charSequence.charAt(i3);
                    if (Character.isSurrogatePair(charAt2, charAt)) {
                        int toCodePoint = Character.toCodePoint(charAt2, charAt);
                        i6 = i5 + 1;
                        bArr[i5] = (byte) ((toCodePoint >>> 18) | 240);
                        i5 = i6 + 1;
                        bArr[i6] = (byte) (((toCodePoint >>> 12) & 63) | 128);
                        r6 = i5 + 1;
                        bArr[i5] = (byte) (((toCodePoint >>> 6) & 63) | 128);
                        i6 = r6 + 1;
                        bArr[r6] = (byte) ((toCodePoint & 63) | 128);
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i3 - 1));
            } else {
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i5);
            }
            i3++;
            i5 = i6;
        }
        return i5;
    }

    private static void zza(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        } else if (byteBuffer.hasArray()) {
            try {
                byteBuffer.position(zza(charSequence, byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining()) - byteBuffer.arrayOffset());
            } catch (Throwable e) {
                BufferOverflowException bufferOverflowException = new BufferOverflowException();
                bufferOverflowException.initCause(e);
                throw bufferOverflowException;
            }
        } else {
            zzb(charSequence, byteBuffer);
        }
    }

    public static int zzad(int i, int i2) {
        return zzkw(i) + zzkx(i2);
    }

    public static int zzb(int i, zzfhk com_google_android_gms_internal_zzfhk) {
        int zzkw = zzkw(i);
        int zzhl = com_google_android_gms_internal_zzfhk.zzhl();
        return zzkw + (zzhl + zzly(zzhl));
    }

    private static void zzb(CharSequence charSequence, ByteBuffer byteBuffer) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < '') {
                byteBuffer.put((byte) charAt);
            } else if (charAt < 'ࠀ') {
                byteBuffer.put((byte) ((charAt >>> 6) | 960));
                byteBuffer.put((byte) ((charAt & 63) | 128));
            } else if (charAt < '?' || '?' < charAt) {
                byteBuffer.put((byte) ((charAt >>> 12) | 480));
                byteBuffer.put((byte) (((charAt >>> 6) & 63) | 128));
                byteBuffer.put((byte) ((charAt & 63) | 128));
            } else {
                if (i + 1 != charSequence.length()) {
                    i++;
                    char charAt2 = charSequence.charAt(i);
                    if (Character.isSurrogatePair(charAt, charAt2)) {
                        int toCodePoint = Character.toCodePoint(charAt, charAt2);
                        byteBuffer.put((byte) ((toCodePoint >>> 18) | 240));
                        byteBuffer.put((byte) (((toCodePoint >>> 12) & 63) | 128));
                        byteBuffer.put((byte) (((toCodePoint >>> 6) & 63) | 128));
                        byteBuffer.put((byte) ((toCodePoint & 63) | 128));
                    }
                }
                throw new IllegalArgumentException("Unpaired surrogate at index " + (i - 1));
            }
            i++;
        }
    }

    public static zzfhc zzbe(byte[] bArr) {
        return zzo(bArr, 0, bArr.length);
    }

    public static int zzbf(byte[] bArr) {
        return zzly(bArr.length) + bArr.length;
    }

    public static int zzc(int i, long j) {
        return zzkw(i) + zzdh(j);
    }

    public static int zzd(int i, byte[] bArr) {
        return zzkw(i) + zzbf(bArr);
    }

    private static int zzd(CharSequence charSequence) {
        int i = 0;
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < '') {
            i2++;
        }
        int i3 = length;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt < 'ࠀ') {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 'ࠀ') {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if ('?' <= charAt2 && charAt2 <= '?') {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                throw new IllegalArgumentException("Unpaired surrogate at index " + i2);
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i2 = i3 + i;
                if (i2 < length) {
                    return i2;
                }
                throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + iHealthDevicesManager.DISCOVERY_BG5));
            }
        }
        i2 = i3;
        if (i2 < length) {
            return i2;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i2) + iHealthDevicesManager.DISCOVERY_BG5));
    }

    private static long zzda(long j) {
        return (j << 1) ^ (j >> 63);
    }

    private final void zzdg(long j) throws IOException {
        while ((-128 & j) != 0) {
            zzlw((((int) j) & 127) | 128);
            j >>>= 7;
        }
        zzlw((int) j);
    }

    public static int zzdh(long j) {
        return (-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    private final void zzdi(long j) throws IOException {
        if (this.zzpgx.remaining() < 8) {
            throw new zzfhd(this.zzpgx.position(), this.zzpgx.limit());
        }
        this.zzpgx.putLong(j);
    }

    public static int zzh(int i, long j) {
        return zzkw(i) + zzdh(zzda(j));
    }

    public static int zzkw(int i) {
        return zzly(i << 3);
    }

    public static int zzkx(int i) {
        return i >= 0 ? zzly(i) : 10;
    }

    public static int zzle(int i) {
        return (i << 1) ^ (i >> 31);
    }

    private final void zzlw(int i) throws IOException {
        byte b = (byte) i;
        if (this.zzpgx.hasRemaining()) {
            this.zzpgx.put(b);
            return;
        }
        throw new zzfhd(this.zzpgx.position(), this.zzpgx.limit());
    }

    public static int zzly(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    public static int zzo(int i, String str) {
        return zzkw(i) + zztd(str);
    }

    public static zzfhc zzo(byte[] bArr, int i, int i2) {
        return new zzfhc(bArr, 0, i2);
    }

    public static int zztd(String str) {
        int zzd = zzd(str);
        return zzd + zzly(zzd);
    }

    public final void zza(int i, double d) throws IOException {
        zzz(i, 1);
        zzdi(Double.doubleToLongBits(d));
    }

    public final void zza(int i, long j) throws IOException {
        zzz(i, 0);
        zzdg(j);
    }

    public final void zza(int i, zzfhk com_google_android_gms_internal_zzfhk) throws IOException {
        zzz(i, 2);
        zzb(com_google_android_gms_internal_zzfhk);
    }

    public final void zzaa(int i, int i2) throws IOException {
        zzz(i, 0);
        if (i2 >= 0) {
            zzlx(i2);
        } else {
            zzdg((long) i2);
        }
    }

    public final void zzb(int i, long j) throws IOException {
        zzz(i, 1);
        zzdi(j);
    }

    public final void zzb(zzfhk com_google_android_gms_internal_zzfhk) throws IOException {
        zzlx(com_google_android_gms_internal_zzfhk.zzcxl());
        com_google_android_gms_internal_zzfhk.zza(this);
    }

    public final void zzbg(byte[] bArr) throws IOException {
        int length = bArr.length;
        if (this.zzpgx.remaining() >= length) {
            this.zzpgx.put(bArr, 0, length);
            return;
        }
        throw new zzfhd(this.zzpgx.position(), this.zzpgx.limit());
    }

    public final void zzc(int i, float f) throws IOException {
        zzz(i, 5);
        int floatToIntBits = Float.floatToIntBits(f);
        if (this.zzpgx.remaining() < 4) {
            throw new zzfhd(this.zzpgx.position(), this.zzpgx.limit());
        }
        this.zzpgx.putInt(floatToIntBits);
    }

    public final void zzc(int i, byte[] bArr) throws IOException {
        zzz(i, 2);
        zzlx(bArr.length);
        zzbg(bArr);
    }

    public final void zzcus() {
        if (this.zzpgx.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", new Object[]{Integer.valueOf(this.zzpgx.remaining())}));
        }
    }

    public final void zzf(int i, long j) throws IOException {
        zzz(i, 0);
        zzdg(j);
    }

    public final void zzg(int i, long j) throws IOException {
        zzz(i, 0);
        zzdg(zzda(j));
    }

    public final void zzl(int i, boolean z) throws IOException {
        int i2 = 0;
        zzz(i, 0);
        if (z) {
            i2 = 1;
        }
        byte b = (byte) i2;
        if (this.zzpgx.hasRemaining()) {
            this.zzpgx.put(b);
            return;
        }
        throw new zzfhd(this.zzpgx.position(), this.zzpgx.limit());
    }

    public final void zzlx(int i) throws IOException {
        while ((i & -128) != 0) {
            zzlw((i & 127) | 128);
            i >>>= 7;
        }
        zzlw(i);
    }

    public final void zzn(int i, String str) throws IOException {
        zzz(i, 2);
        try {
            int zzly = zzly(str.length());
            if (zzly == zzly(str.length() * 3)) {
                int position = this.zzpgx.position();
                if (this.zzpgx.remaining() < zzly) {
                    throw new zzfhd(zzly + position, this.zzpgx.limit());
                }
                this.zzpgx.position(position + zzly);
                zza((CharSequence) str, this.zzpgx);
                int position2 = this.zzpgx.position();
                this.zzpgx.position(position);
                zzlx((position2 - position) - zzly);
                this.zzpgx.position(position2);
                return;
            }
            zzlx(zzd(str));
            zza((CharSequence) str, this.zzpgx);
        } catch (Throwable e) {
            zzfhd com_google_android_gms_internal_zzfhd = new zzfhd(this.zzpgx.position(), this.zzpgx.limit());
            com_google_android_gms_internal_zzfhd.initCause(e);
            throw com_google_android_gms_internal_zzfhd;
        }
    }

    public final void zzz(int i, int i2) throws IOException {
        zzlx((i << 3) | i2);
    }
}

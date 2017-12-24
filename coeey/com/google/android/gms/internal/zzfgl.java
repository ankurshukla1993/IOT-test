package com.google.android.gms.internal;

import com.ihealth.communication.manager.iHealthDevicesManager;

final class zzfgl {
    private static final zzfgm zzpfk;

    static {
        Object obj = (zzfgj.zzcww() && zzfgj.zzcwx()) ? 1 : null;
        zzpfk = obj != null ? new zzfgp() : new zzfgn();
    }

    static int zza(CharSequence charSequence, byte[] bArr, int i, int i2) {
        return zzpfk.zzb(charSequence, bArr, i, i2);
    }

    private static int zzaj(int i, int i2) {
        return (i > -12 || i2 > -65) ? -1 : (i2 << 8) ^ i;
    }

    static int zzd(CharSequence charSequence) {
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
                                throw new zzfgo(i2, length2);
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

    private static int zzi(int i, int i2, int i3) {
        return (i > -12 || i2 > -65 || i3 > -65) ? -1 : ((i2 << 8) ^ i) ^ (i3 << 16);
    }

    public static boolean zzk(byte[] bArr, int i, int i2) {
        return zzpfk.zzb(0, bArr, i, i2) == 0;
    }

    private static int zzl(byte[] bArr, int i, int i2) {
        byte b = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return zzlt(b);
            case 1:
                return zzaj(b, bArr[i]);
            case 2:
                return zzi(b, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    private static int zzlt(int i) {
        return i > -12 ? -1 : i;
    }
}

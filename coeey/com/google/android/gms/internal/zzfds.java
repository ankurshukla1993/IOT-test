package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzfds extends zzfdq {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzpaw;
    private int zzpax;
    private int zzpay;
    private int zzpaz;
    private int zzpba;

    private zzfds(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzpba = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i + i2;
        this.pos = i;
        this.zzpay = this.pos;
        this.zzpaw = z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzcum() throws java.io.IOException {
        /*
        r10 = this;
        r8 = 0;
        r0 = r10.pos;
        r1 = r10.limit;
        if (r1 == r0) goto L_0x00b4;
    L_0x0008:
        r4 = r10.buffer;
        r1 = r0 + 1;
        r0 = r4[r0];
        if (r0 < 0) goto L_0x0014;
    L_0x0010:
        r10.pos = r1;
        r0 = (long) r0;
    L_0x0013:
        return r0;
    L_0x0014:
        r2 = r10.limit;
        r2 = r2 - r1;
        r3 = 9;
        if (r2 < r3) goto L_0x00b4;
    L_0x001b:
        r2 = r1 + 1;
        r1 = r4[r1];
        r1 = r1 << 7;
        r0 = r0 ^ r1;
        if (r0 >= 0) goto L_0x002a;
    L_0x0024:
        r0 = r0 ^ -128;
        r0 = (long) r0;
    L_0x0027:
        r10.pos = r2;
        goto L_0x0013;
    L_0x002a:
        r3 = r2 + 1;
        r1 = r4[r2];
        r1 = r1 << 14;
        r0 = r0 ^ r1;
        if (r0 < 0) goto L_0x0038;
    L_0x0033:
        r0 = r0 ^ 16256;
        r0 = (long) r0;
        r2 = r3;
        goto L_0x0027;
    L_0x0038:
        r2 = r3 + 1;
        r1 = r4[r3];
        r1 = r1 << 21;
        r0 = r0 ^ r1;
        if (r0 >= 0) goto L_0x0047;
    L_0x0041:
        r1 = -2080896; // 0xffffffffffe03f80 float:NaN double:NaN;
        r0 = r0 ^ r1;
        r0 = (long) r0;
        goto L_0x0027;
    L_0x0047:
        r0 = (long) r0;
        r3 = r2 + 1;
        r2 = r4[r2];
        r6 = (long) r2;
        r2 = 28;
        r6 = r6 << r2;
        r0 = r0 ^ r6;
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x005b;
    L_0x0055:
        r4 = 266354560; // 0xfe03f80 float:2.2112565E-29 double:1.315966377E-315;
        r0 = r0 ^ r4;
        r2 = r3;
        goto L_0x0027;
    L_0x005b:
        r2 = r3 + 1;
        r3 = r4[r3];
        r6 = (long) r3;
        r3 = 35;
        r6 = r6 << r3;
        r0 = r0 ^ r6;
        r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r3 >= 0) goto L_0x006f;
    L_0x0068:
        r4 = -34093383808; // 0xfffffff80fe03f80 float:2.2112565E-29 double:NaN;
        r0 = r0 ^ r4;
        goto L_0x0027;
    L_0x006f:
        r3 = r2 + 1;
        r2 = r4[r2];
        r6 = (long) r2;
        r2 = 42;
        r6 = r6 << r2;
        r0 = r0 ^ r6;
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 < 0) goto L_0x0084;
    L_0x007c:
        r4 = 4363953127296; // 0x3f80fe03f80 float:2.2112565E-29 double:2.1560793202584E-311;
        r0 = r0 ^ r4;
        r2 = r3;
        goto L_0x0027;
    L_0x0084:
        r2 = r3 + 1;
        r3 = r4[r3];
        r6 = (long) r3;
        r3 = 49;
        r6 = r6 << r3;
        r0 = r0 ^ r6;
        r3 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r3 >= 0) goto L_0x0098;
    L_0x0091:
        r4 = -558586000294016; // 0xfffe03f80fe03f80 float:2.2112565E-29 double:NaN;
        r0 = r0 ^ r4;
        goto L_0x0027;
    L_0x0098:
        r3 = r2 + 1;
        r2 = r4[r2];
        r6 = (long) r2;
        r2 = 56;
        r6 = r6 << r2;
        r0 = r0 ^ r6;
        r6 = 71499008037633920; // 0xfe03f80fe03f80 float:2.2112565E-29 double:6.838959413692434E-304;
        r0 = r0 ^ r6;
        r2 = (r0 > r8 ? 1 : (r0 == r8 ? 0 : -1));
        if (r2 >= 0) goto L_0x00ba;
    L_0x00ab:
        r2 = r3 + 1;
        r3 = r4[r3];
        r4 = (long) r3;
        r3 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r3 >= 0) goto L_0x0027;
    L_0x00b4:
        r0 = r10.zzcui();
        goto L_0x0013;
    L_0x00ba:
        r2 = r3;
        goto L_0x0027;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfds.zzcum():long");
    }

    private final int zzcun() throws IOException {
        int i = this.pos;
        if (this.limit - i < 4) {
            throw zzfew.zzcvr();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
    }

    private final long zzcuo() throws IOException {
        int i = this.pos;
        if (this.limit - i < 8) {
            throw zzfew.zzcvr();
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48));
    }

    private final void zzcup() {
        this.limit += this.zzpax;
        int i = this.limit - this.zzpay;
        if (i > this.zzpba) {
            this.zzpax = i - this.zzpba;
            this.limit -= this.zzpax;
            return;
        }
        this.zzpax = 0;
    }

    private final byte zzcuq() throws IOException {
        if (this.pos == this.limit) {
            throw zzfew.zzcvr();
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzcuo());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzcun());
    }

    public final String readString() throws IOException {
        int zzcuh = zzcuh();
        if (zzcuh > 0 && zzcuh <= this.limit - this.pos) {
            String str = new String(this.buffer, this.pos, zzcuh, zzfer.UTF_8);
            this.pos = zzcuh + this.pos;
            return str;
        } else if (zzcuh == 0) {
            return "";
        } else {
            if (zzcuh < 0) {
                throw zzfew.zzcvs();
            }
            throw zzfew.zzcvr();
        }
    }

    public final <T extends zzfee<T, ?>> T zza(T t, zzfea com_google_android_gms_internal_zzfea) throws IOException {
        int zzcuh = zzcuh();
        if (this.zzpar >= this.zzpas) {
            throw zzfew.zzcvx();
        }
        zzcuh = zzki(zzcuh);
        this.zzpar++;
        T zza = zzfee.zza((zzfee) t, (zzfdq) this, com_google_android_gms_internal_zzfea);
        zzkf(0);
        this.zzpar--;
        zzkj(zzcuh);
        return zza;
    }

    public final void zza(zzffj com_google_android_gms_internal_zzffj, zzfea com_google_android_gms_internal_zzfea) throws IOException {
        int zzcuh = zzcuh();
        if (this.zzpar >= this.zzpas) {
            throw zzfew.zzcvx();
        }
        zzcuh = zzki(zzcuh);
        this.zzpar++;
        com_google_android_gms_internal_zzffj.zzb(this, com_google_android_gms_internal_zzfea);
        zzkf(0);
        this.zzpar--;
        zzkj(zzcuh);
    }

    public final int zzcts() throws IOException {
        if (zzcuk()) {
            this.zzpaz = 0;
            return 0;
        }
        this.zzpaz = zzcuh();
        if ((this.zzpaz >>> 3) != 0) {
            return this.zzpaz;
        }
        throw zzfew.zzcvu();
    }

    public final long zzctt() throws IOException {
        return zzcum();
    }

    public final long zzctu() throws IOException {
        return zzcum();
    }

    public final int zzctv() throws IOException {
        return zzcuh();
    }

    public final long zzctw() throws IOException {
        return zzcuo();
    }

    public final int zzctx() throws IOException {
        return zzcun();
    }

    public final boolean zzcty() throws IOException {
        return zzcum() != 0;
    }

    public final String zzctz() throws IOException {
        int zzcuh = zzcuh();
        if (zzcuh <= 0 || zzcuh > this.limit - this.pos) {
            if (zzcuh == 0) {
                return "";
            }
            if (zzcuh <= 0) {
                throw zzfew.zzcvs();
            }
            throw zzfew.zzcvr();
        } else if (zzfgl.zzk(this.buffer, this.pos, this.pos + zzcuh)) {
            int i = this.pos;
            this.pos += zzcuh;
            return new String(this.buffer, i, zzcuh, zzfer.UTF_8);
        } else {
            throw zzfew.zzcvz();
        }
    }

    public final zzfdh zzcua() throws IOException {
        int zzcuh = zzcuh();
        if (zzcuh > 0 && zzcuh <= this.limit - this.pos) {
            zzfdh zze = zzfdh.zze(this.buffer, this.pos, zzcuh);
            this.pos = zzcuh + this.pos;
            return zze;
        } else if (zzcuh == 0) {
            return zzfdh.zzpal;
        } else {
            byte[] copyOfRange;
            if (zzcuh > 0 && zzcuh <= this.limit - this.pos) {
                int i = this.pos;
                this.pos = zzcuh + this.pos;
                copyOfRange = Arrays.copyOfRange(this.buffer, i, this.pos);
            } else if (zzcuh > 0) {
                throw zzfew.zzcvr();
            } else if (zzcuh == 0) {
                copyOfRange = zzfer.EMPTY_BYTE_ARRAY;
            } else {
                throw zzfew.zzcvs();
            }
            return zzfdh.zzaz(copyOfRange);
        }
    }

    public final int zzcub() throws IOException {
        return zzcuh();
    }

    public final int zzcuc() throws IOException {
        return zzcuh();
    }

    public final int zzcud() throws IOException {
        return zzcun();
    }

    public final long zzcue() throws IOException {
        return zzcuo();
    }

    public final int zzcuf() throws IOException {
        return zzfdq.zzkl(zzcuh());
    }

    public final long zzcug() throws IOException {
        return zzfdq.zzcr(zzcum());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zzcuh() throws java.io.IOException {
        /*
        r5 = this;
        r0 = r5.pos;
        r1 = r5.limit;
        if (r1 == r0) goto L_0x006c;
    L_0x0006:
        r3 = r5.buffer;
        r2 = r0 + 1;
        r0 = r3[r0];
        if (r0 < 0) goto L_0x0011;
    L_0x000e:
        r5.pos = r2;
    L_0x0010:
        return r0;
    L_0x0011:
        r1 = r5.limit;
        r1 = r1 - r2;
        r4 = 9;
        if (r1 < r4) goto L_0x006c;
    L_0x0018:
        r1 = r2 + 1;
        r2 = r3[r2];
        r2 = r2 << 7;
        r0 = r0 ^ r2;
        if (r0 >= 0) goto L_0x0026;
    L_0x0021:
        r0 = r0 ^ -128;
    L_0x0023:
        r5.pos = r1;
        goto L_0x0010;
    L_0x0026:
        r2 = r1 + 1;
        r1 = r3[r1];
        r1 = r1 << 14;
        r0 = r0 ^ r1;
        if (r0 < 0) goto L_0x0033;
    L_0x002f:
        r0 = r0 ^ 16256;
        r1 = r2;
        goto L_0x0023;
    L_0x0033:
        r1 = r2 + 1;
        r2 = r3[r2];
        r2 = r2 << 21;
        r0 = r0 ^ r2;
        if (r0 >= 0) goto L_0x0041;
    L_0x003c:
        r2 = -2080896; // 0xffffffffffe03f80 float:NaN double:NaN;
        r0 = r0 ^ r2;
        goto L_0x0023;
    L_0x0041:
        r2 = r1 + 1;
        r1 = r3[r1];
        r4 = r1 << 28;
        r0 = r0 ^ r4;
        r4 = 266354560; // 0xfe03f80 float:2.2112565E-29 double:1.315966377E-315;
        r0 = r0 ^ r4;
        if (r1 >= 0) goto L_0x0072;
    L_0x004e:
        r1 = r2 + 1;
        r2 = r3[r2];
        if (r2 >= 0) goto L_0x0023;
    L_0x0054:
        r2 = r1 + 1;
        r1 = r3[r1];
        if (r1 >= 0) goto L_0x0072;
    L_0x005a:
        r1 = r2 + 1;
        r2 = r3[r2];
        if (r2 >= 0) goto L_0x0023;
    L_0x0060:
        r2 = r1 + 1;
        r1 = r3[r1];
        if (r1 >= 0) goto L_0x0072;
    L_0x0066:
        r1 = r2 + 1;
        r2 = r3[r2];
        if (r2 >= 0) goto L_0x0023;
    L_0x006c:
        r0 = r5.zzcui();
        r0 = (int) r0;
        goto L_0x0010;
    L_0x0072:
        r1 = r2;
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfds.zzcuh():int");
    }

    final long zzcui() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzcuq = zzcuq();
            j |= ((long) (zzcuq & 127)) << i;
            if ((zzcuq & 128) == 0) {
                return j;
            }
        }
        throw zzfew.zzcvt();
    }

    public final int zzcuj() {
        return this.zzpba == Integer.MAX_VALUE ? -1 : this.zzpba - zzcul();
    }

    public final boolean zzcuk() throws IOException {
        return this.pos == this.limit;
    }

    public final int zzcul() {
        return this.pos - this.zzpay;
    }

    public final void zzkf(int i) throws zzfew {
        if (this.zzpaz != i) {
            throw zzfew.zzcvv();
        }
    }

    public final boolean zzkg(int i) throws IOException {
        int i2 = 0;
        switch (i & 7) {
            case 0:
                if (this.limit - this.pos >= 10) {
                    while (i2 < 10) {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        this.pos = i3 + 1;
                        if (bArr[i3] >= (byte) 0) {
                            return true;
                        }
                        i2++;
                    }
                    throw zzfew.zzcvt();
                }
                while (i2 < 10) {
                    if (zzcuq() >= (byte) 0) {
                        return true;
                    }
                    i2++;
                }
                throw zzfew.zzcvt();
            case 1:
                zzkk(8);
                return true;
            case 2:
                zzkk(zzcuh());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzkk(4);
                return true;
            default:
                throw zzfew.zzcvw();
        }
        do {
            i2 = zzcts();
            if (i2 != 0) {
            }
            zzkf(((i >>> 3) << 3) | 4);
            return true;
        } while (zzkg(i2));
        zzkf(((i >>> 3) << 3) | 4);
        return true;
    }

    public final int zzki(int i) throws zzfew {
        if (i < 0) {
            throw zzfew.zzcvs();
        }
        int zzcul = zzcul() + i;
        int i2 = this.zzpba;
        if (zzcul > i2) {
            throw zzfew.zzcvr();
        }
        this.zzpba = zzcul;
        zzcup();
        return i2;
    }

    public final void zzkj(int i) {
        this.zzpba = i;
        zzcup();
    }

    public final void zzkk(int i) throws IOException {
        if (i >= 0 && i <= this.limit - this.pos) {
            this.pos += i;
        } else if (i < 0) {
            throw zzfew.zzcvs();
        } else {
            throw zzfew.zzcvr();
        }
    }
}

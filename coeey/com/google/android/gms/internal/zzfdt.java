package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

final class zzfdt extends zzfdq {
    private final byte[] buffer;
    private int pos;
    private int zzpax;
    private int zzpaz;
    private int zzpba;
    private final InputStream zzpbb;
    private int zzpbc;
    private int zzpbd;
    private zzfdu zzpbe;

    private zzfdt(InputStream inputStream, int i) {
        super();
        this.zzpba = Integer.MAX_VALUE;
        this.zzpbe = null;
        zzfer.zzc(inputStream, "input");
        this.zzpbb = inputStream;
        this.buffer = new byte[i];
        this.zzpbc = 0;
        this.pos = 0;
        this.zzpbd = 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long zzcum() throws java.io.IOException {
        /*
        r10 = this;
        r8 = 0;
        r0 = r10.pos;
        r1 = r10.zzpbc;
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
        r2 = r10.zzpbc;
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfdt.zzcum():long");
    }

    private final int zzcun() throws IOException {
        int i = this.pos;
        if (this.zzpbc - i < 4) {
            zzkm(4);
            i = this.pos;
        }
        byte[] bArr = this.buffer;
        this.pos = i + 4;
        return ((bArr[i + 3] & 255) << 24) | (((bArr[i] & 255) | ((bArr[i + 1] & 255) << 8)) | ((bArr[i + 2] & 255) << 16));
    }

    private final long zzcuo() throws IOException {
        int i = this.pos;
        if (this.zzpbc - i < 8) {
            zzkm(8);
            i = this.pos;
        }
        byte[] bArr = this.buffer;
        this.pos = i + 8;
        return ((((long) bArr[i + 7]) & 255) << 56) | (((((((((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8)) | ((((long) bArr[i + 2]) & 255) << 16)) | ((((long) bArr[i + 3]) & 255) << 24)) | ((((long) bArr[i + 4]) & 255) << 32)) | ((((long) bArr[i + 5]) & 255) << 40)) | ((((long) bArr[i + 6]) & 255) << 48));
    }

    private final void zzcup() {
        this.zzpbc += this.zzpax;
        int i = this.zzpbd + this.zzpbc;
        if (i > this.zzpba) {
            this.zzpax = i - this.zzpba;
            this.zzpbc -= this.zzpax;
            return;
        }
        this.zzpax = 0;
    }

    private final byte zzcuq() throws IOException {
        if (this.pos == this.zzpbc) {
            zzkm(1);
        }
        byte[] bArr = this.buffer;
        int i = this.pos;
        this.pos = i + 1;
        return bArr[i];
    }

    private final void zzkm(int i) throws IOException {
        if (!zzkn(i)) {
            if (i > (this.zzpat - this.zzpbd) - this.pos) {
                throw zzfew.zzcvy();
            }
            throw zzfew.zzcvr();
        }
    }

    private final boolean zzkn(int i) throws IOException {
        while (this.pos + i > this.zzpbc) {
            if (i > (this.zzpat - this.zzpbd) - this.pos || (this.zzpbd + this.pos) + i > this.zzpba) {
                return false;
            }
            int i2 = this.pos;
            if (i2 > 0) {
                if (this.zzpbc > i2) {
                    System.arraycopy(this.buffer, i2, this.buffer, 0, this.zzpbc - i2);
                }
                this.zzpbd += i2;
                this.zzpbc -= i2;
                this.pos = 0;
            }
            i2 = this.zzpbb.read(this.buffer, this.zzpbc, Math.min(this.buffer.length - this.zzpbc, (this.zzpat - this.zzpbd) - this.zzpbc));
            if (i2 == 0 || i2 < -1 || i2 > this.buffer.length) {
                throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + i2 + "\nThe InputStream implementation is buggy.");
            } else if (i2 <= 0) {
                return false;
            } else {
                this.zzpbc = i2 + this.zzpbc;
                zzcup();
                if (this.zzpbc >= i) {
                    return true;
                }
            }
        }
        throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
    }

    private final byte[] zzko(int i) throws IOException {
        byte[] zzkp = zzkp(i);
        if (zzkp != null) {
            return zzkp;
        }
        int i2 = this.pos;
        int i3 = this.zzpbc - this.pos;
        this.zzpbd += this.zzpbc;
        this.pos = 0;
        this.zzpbc = 0;
        List<byte[]> zzkq = zzkq(i - i3);
        Object obj = new byte[i];
        System.arraycopy(this.buffer, i2, obj, 0, i3);
        i2 = i3;
        for (byte[] zzkp2 : zzkq) {
            System.arraycopy(zzkp2, 0, obj, i2, zzkp2.length);
            i2 = zzkp2.length + i2;
        }
        return obj;
    }

    private final byte[] zzkp(int i) throws IOException {
        if (i == 0) {
            return zzfer.EMPTY_BYTE_ARRAY;
        }
        if (i < 0) {
            throw zzfew.zzcvs();
        }
        int i2 = (this.zzpbd + this.pos) + i;
        if (i2 - this.zzpat > 0) {
            throw zzfew.zzcvy();
        } else if (i2 > this.zzpba) {
            zzkk((this.zzpba - this.zzpbd) - this.pos);
            throw zzfew.zzcvr();
        } else {
            i2 = this.zzpbc - this.pos;
            int i3 = i - i2;
            if (i3 >= 4096 && i3 > this.zzpbb.available()) {
                return null;
            }
            Object obj = new byte[i];
            System.arraycopy(this.buffer, this.pos, obj, 0, i2);
            this.zzpbd += this.zzpbc;
            this.pos = 0;
            this.zzpbc = 0;
            while (i2 < obj.length) {
                int read = this.zzpbb.read(obj, i2, i - i2);
                if (read == -1) {
                    throw zzfew.zzcvr();
                }
                this.zzpbd += read;
                i2 += read;
            }
            return obj;
        }
    }

    private final List<byte[]> zzkq(int i) throws IOException {
        List<byte[]> arrayList = new ArrayList();
        while (i > 0) {
            Object obj = new byte[Math.min(i, 4096)];
            int i2 = 0;
            while (i2 < obj.length) {
                int read = this.zzpbb.read(obj, i2, obj.length - i2);
                if (read == -1) {
                    throw zzfew.zzcvr();
                }
                this.zzpbd += read;
                i2 += read;
            }
            i -= obj.length;
            arrayList.add(obj);
        }
        return arrayList;
    }

    public final double readDouble() throws IOException {
        return Double.longBitsToDouble(zzcuo());
    }

    public final float readFloat() throws IOException {
        return Float.intBitsToFloat(zzcun());
    }

    public final String readString() throws IOException {
        int zzcuh = zzcuh();
        String str;
        if (zzcuh > 0 && zzcuh <= this.zzpbc - this.pos) {
            str = new String(this.buffer, this.pos, zzcuh, zzfer.UTF_8);
            this.pos = zzcuh + this.pos;
            return str;
        } else if (zzcuh == 0) {
            return "";
        } else {
            if (zzcuh > this.zzpbc) {
                return new String(zzko(zzcuh), zzfer.UTF_8);
            }
            zzkm(zzcuh);
            str = new String(this.buffer, this.pos, zzcuh, zzfer.UTF_8);
            this.pos = zzcuh + this.pos;
            return str;
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
        byte[] bArr;
        int zzcuh = zzcuh();
        int i = this.pos;
        if (zzcuh <= this.zzpbc - i && zzcuh > 0) {
            bArr = this.buffer;
            this.pos = i + zzcuh;
        } else if (zzcuh == 0) {
            return "";
        } else {
            if (zzcuh <= this.zzpbc) {
                zzkm(zzcuh);
                byte[] bArr2 = this.buffer;
                this.pos = zzcuh;
                bArr = bArr2;
                i = 0;
            } else {
                bArr = zzko(zzcuh);
                i = 0;
            }
        }
        if (zzfgl.zzk(bArr, i, i + zzcuh)) {
            return new String(bArr, i, zzcuh, zzfer.UTF_8);
        }
        throw zzfew.zzcvz();
    }

    public final zzfdh zzcua() throws IOException {
        int zzcuh = zzcuh();
        if (zzcuh <= this.zzpbc - this.pos && zzcuh > 0) {
            zzfdh zze = zzfdh.zze(this.buffer, this.pos, zzcuh);
            this.pos = zzcuh + this.pos;
            return zze;
        } else if (zzcuh == 0) {
            return zzfdh.zzpal;
        } else {
            byte[] zzkp = zzkp(zzcuh);
            if (zzkp != null) {
                return zzfdh.zzaz(zzkp);
            }
            int i = this.pos;
            int i2 = this.zzpbc - this.pos;
            this.zzpbd += this.zzpbc;
            this.pos = 0;
            this.zzpbc = 0;
            List<byte[]> zzkq = zzkq(zzcuh - i2);
            Iterable arrayList = new ArrayList(zzkq.size() + 1);
            arrayList.add(zzfdh.zze(this.buffer, i, i2));
            for (byte[] zzkp2 : zzkq) {
                arrayList.add(zzfdh.zzaz(zzkp2));
            }
            return zzfdh.zzf(arrayList);
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
        r1 = r5.zzpbc;
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
        r1 = r5.zzpbc;
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfdt.zzcuh():int");
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
        if (this.zzpba == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzpba - (this.zzpbd + this.pos);
    }

    public final boolean zzcuk() throws IOException {
        return this.pos == this.zzpbc && !zzkn(1);
    }

    public final int zzcul() {
        return this.zzpbd + this.pos;
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
                if (this.zzpbc - this.pos >= 10) {
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
        int i2 = (this.zzpbd + this.pos) + i;
        int i3 = this.zzpba;
        if (i2 > i3) {
            throw zzfew.zzcvr();
        }
        this.zzpba = i2;
        zzcup();
        return i3;
    }

    public final void zzkj(int i) {
        this.zzpba = i;
        zzcup();
    }

    public final void zzkk(int i) throws IOException {
        if (i <= this.zzpbc - this.pos && i >= 0) {
            this.pos += i;
        } else if (i < 0) {
            throw zzfew.zzcvs();
        } else if ((this.zzpbd + this.pos) + i > this.zzpba) {
            zzkk((this.zzpba - this.zzpbd) - this.pos);
            throw zzfew.zzcvr();
        } else {
            int i2 = this.zzpbc - this.pos;
            this.pos = this.zzpbc;
            zzkm(1);
            while (i - i2 > this.zzpbc) {
                i2 += this.zzpbc;
                this.pos = this.zzpbc;
                zzkm(1);
            }
            this.pos = i - i2;
        }
    }
}

package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfhb {
    private final byte[] buffer;
    private int zzpar;
    private int zzpas = 64;
    private int zzpat = 67108864;
    private int zzpax;
    private int zzpaz;
    private int zzpba = Integer.MAX_VALUE;
    private int zzpbc;
    private int zzpgv;
    private int zzpgw;

    private zzfhb(byte[] bArr, int i, int i2) {
        this.buffer = bArr;
        this.zzpgv = i;
        this.zzpbc = i + i2;
        this.zzpgw = i;
    }

    public static zzfhb zzbd(byte[] bArr) {
        return zzn(bArr, 0, bArr.length);
    }

    private final void zzcup() {
        this.zzpbc += this.zzpax;
        int i = this.zzpbc;
        if (i > this.zzpba) {
            this.zzpax = i - this.zzpba;
            this.zzpbc -= this.zzpax;
            return;
        }
        this.zzpax = 0;
    }

    private final byte zzcuq() throws IOException {
        if (this.zzpgw == this.zzpbc) {
            throw zzfhj.zzcxh();
        }
        byte[] bArr = this.buffer;
        int i = this.zzpgw;
        this.zzpgw = i + 1;
        return bArr[i];
    }

    private final void zzkk(int i) throws IOException {
        if (i < 0) {
            throw zzfhj.zzcxi();
        } else if (this.zzpgw + i > this.zzpba) {
            zzkk(this.zzpba - this.zzpgw);
            throw zzfhj.zzcxh();
        } else if (i <= this.zzpbc - this.zzpgw) {
            this.zzpgw += i;
        } else {
            throw zzfhj.zzcxh();
        }
    }

    public static zzfhb zzn(byte[] bArr, int i, int i2) {
        return new zzfhb(bArr, 0, i2);
    }

    public final int getPosition() {
        return this.zzpgw - this.zzpgv;
    }

    public final byte[] readBytes() throws IOException {
        int zzcuh = zzcuh();
        if (zzcuh < 0) {
            throw zzfhj.zzcxi();
        } else if (zzcuh == 0) {
            return zzfhn.zzphr;
        } else {
            if (zzcuh > this.zzpbc - this.zzpgw) {
                throw zzfhj.zzcxh();
            }
            Object obj = new byte[zzcuh];
            System.arraycopy(this.buffer, this.zzpgw, obj, 0, zzcuh);
            this.zzpgw = zzcuh + this.zzpgw;
            return obj;
        }
    }

    public final String readString() throws IOException {
        int zzcuh = zzcuh();
        if (zzcuh < 0) {
            throw zzfhj.zzcxi();
        } else if (zzcuh > this.zzpbc - this.zzpgw) {
            throw zzfhj.zzcxh();
        } else {
            String str = new String(this.buffer, this.zzpgw, zzcuh, zzfhi.UTF_8);
            this.zzpgw = zzcuh + this.zzpgw;
            return str;
        }
    }

    public final void zza(zzfhk com_google_android_gms_internal_zzfhk) throws IOException {
        int zzcuh = zzcuh();
        if (this.zzpar >= this.zzpas) {
            throw zzfhj.zzcxk();
        }
        zzcuh = zzki(zzcuh);
        this.zzpar++;
        com_google_android_gms_internal_zzfhk.zza(this);
        zzkf(0);
        this.zzpar--;
        zzkj(zzcuh);
    }

    public final void zza(zzfhk com_google_android_gms_internal_zzfhk, int i) throws IOException {
        if (this.zzpar >= this.zzpas) {
            throw zzfhj.zzcxk();
        }
        this.zzpar++;
        com_google_android_gms_internal_zzfhk.zza(this);
        zzkf((i << 3) | 4);
        this.zzpar--;
    }

    public final byte[] zzal(int i, int i2) {
        if (i2 == 0) {
            return zzfhn.zzphr;
        }
        Object obj = new byte[i2];
        System.arraycopy(this.buffer, this.zzpgv + i, obj, 0, i2);
        return obj;
    }

    final void zzam(int i, int i2) {
        if (i > this.zzpgw - this.zzpgv) {
            throw new IllegalArgumentException("Position " + i + " is beyond current " + (this.zzpgw - this.zzpgv));
        } else if (i < 0) {
            throw new IllegalArgumentException("Bad position " + i);
        } else {
            this.zzpgw = this.zzpgv + i;
            this.zzpaz = i2;
        }
    }

    public final int zzcts() throws IOException {
        if (this.zzpgw == this.zzpbc) {
            this.zzpaz = 0;
            return 0;
        }
        this.zzpaz = zzcuh();
        if (this.zzpaz != 0) {
            return this.zzpaz;
        }
        throw new zzfhj("Protocol message contained an invalid tag (zero).");
    }

    public final long zzctu() throws IOException {
        return zzcum();
    }

    public final int zzctv() throws IOException {
        return zzcuh();
    }

    public final boolean zzcty() throws IOException {
        return zzcuh() != 0;
    }

    public final long zzcug() throws IOException {
        long zzcum = zzcum();
        return (-(zzcum & 1)) ^ (zzcum >>> 1);
    }

    public final int zzcuh() throws IOException {
        byte zzcuq = zzcuq();
        if (zzcuq >= (byte) 0) {
            return zzcuq;
        }
        int i = zzcuq & 127;
        byte zzcuq2 = zzcuq();
        if (zzcuq2 >= (byte) 0) {
            return i | (zzcuq2 << 7);
        }
        i |= (zzcuq2 & 127) << 7;
        zzcuq2 = zzcuq();
        if (zzcuq2 >= (byte) 0) {
            return i | (zzcuq2 << 14);
        }
        i |= (zzcuq2 & 127) << 14;
        zzcuq2 = zzcuq();
        if (zzcuq2 >= (byte) 0) {
            return i | (zzcuq2 << 21);
        }
        i |= (zzcuq2 & 127) << 21;
        zzcuq2 = zzcuq();
        i |= zzcuq2 << 28;
        if (zzcuq2 >= (byte) 0) {
            return i;
        }
        for (int i2 = 0; i2 < 5; i2++) {
            if (zzcuq() >= (byte) 0) {
                return i;
            }
        }
        throw zzfhj.zzcxj();
    }

    public final int zzcuj() {
        if (this.zzpba == Integer.MAX_VALUE) {
            return -1;
        }
        return this.zzpba - this.zzpgw;
    }

    public final long zzcum() throws IOException {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte zzcuq = zzcuq();
            j |= ((long) (zzcuq & 127)) << i;
            if ((zzcuq & 128) == 0) {
                return j;
            }
        }
        throw zzfhj.zzcxj();
    }

    public final int zzcun() throws IOException {
        return (((zzcuq() & 255) | ((zzcuq() & 255) << 8)) | ((zzcuq() & 255) << 16)) | ((zzcuq() & 255) << 24);
    }

    public final long zzcuo() throws IOException {
        byte zzcuq = zzcuq();
        byte zzcuq2 = zzcuq();
        return ((((((((((long) zzcuq2) & 255) << 8) | (((long) zzcuq) & 255)) | ((((long) zzcuq()) & 255) << 16)) | ((((long) zzcuq()) & 255) << 24)) | ((((long) zzcuq()) & 255) << 32)) | ((((long) zzcuq()) & 255) << 40)) | ((((long) zzcuq()) & 255) << 48)) | ((((long) zzcuq()) & 255) << 56);
    }

    public final void zzkf(int i) throws zzfhj {
        if (this.zzpaz != i) {
            throw new zzfhj("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final boolean zzkg(int i) throws IOException {
        switch (i & 7) {
            case 0:
                zzcuh();
                return true;
            case 1:
                zzcuo();
                return true;
            case 2:
                zzkk(zzcuh());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                zzcun();
                return true;
            default:
                throw new zzfhj("Protocol message tag had invalid wire type.");
        }
        int zzcts;
        do {
            zzcts = zzcts();
            if (zzcts != 0) {
            }
            zzkf(((i >>> 3) << 3) | 4);
            return true;
        } while (zzkg(zzcts));
        zzkf(((i >>> 3) << 3) | 4);
        return true;
    }

    public final int zzki(int i) throws zzfhj {
        if (i < 0) {
            throw zzfhj.zzcxi();
        }
        int i2 = this.zzpgw + i;
        int i3 = this.zzpba;
        if (i2 > i3) {
            throw zzfhj.zzcxh();
        }
        this.zzpba = i2;
        zzcup();
        return i3;
    }

    public final void zzkj(int i) {
        this.zzpba = i;
        zzcup();
    }

    public final void zzlv(int i) {
        zzam(i, this.zzpaz);
    }
}

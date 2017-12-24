package com.google.android.gms.internal;

import java.io.IOException;

class zzfdv$zzb extends zzfdv {
    private final byte[] buffer;
    private final int limit;
    private final int offset;
    private int position;

    zzfdv$zzb(byte[] bArr, int i, int i2) {
        super(null);
        if (bArr == null) {
            throw new NullPointerException("buffer");
        } else if (((i | i2) | (bArr.length - (i + i2))) < 0) {
            throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", new Object[]{Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)}));
        } else {
            this.buffer = bArr;
            this.offset = i;
            this.position = i;
            this.limit = i + i2;
        }
    }

    public void flush() {
    }

    public final void write(byte[] bArr, int i, int i2) throws IOException {
        try {
            System.arraycopy(bArr, i, this.buffer, this.position, i2);
            this.position += i2;
        } catch (Throwable e) {
            throw new zzfdv$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(i2)}), e);
        }
    }

    public final void zza(int i, long j) throws IOException {
        zzz(i, 0);
        zzcs(j);
    }

    public final void zza(int i, zzfdh com_google_android_gms_internal_zzfdh) throws IOException {
        zzz(i, 2);
        zzam(com_google_android_gms_internal_zzfdh);
    }

    public final void zza(int i, zzffi com_google_android_gms_internal_zzffi) throws IOException {
        zzz(i, 2);
        zzd(com_google_android_gms_internal_zzffi);
    }

    public final void zzaa(int i, int i2) throws IOException {
        zzz(i, 0);
        zzks(i2);
    }

    public final void zzab(int i, int i2) throws IOException {
        zzz(i, 0);
        zzkt(i2);
    }

    public final void zzac(int i, int i2) throws IOException {
        zzz(i, 5);
        zzkv(i2);
    }

    public final void zzam(zzfdh com_google_android_gms_internal_zzfdh) throws IOException {
        zzkt(com_google_android_gms_internal_zzfdh.size());
        com_google_android_gms_internal_zzfdh.zza(this);
    }

    public final void zzb(byte b) throws IOException {
        try {
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = b;
        } catch (Throwable e) {
            throw new zzfdv$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
        }
    }

    public final void zzb(int i, long j) throws IOException {
        zzz(i, 1);
        zzcu(j);
    }

    public final void zzcs(long j) throws IOException {
        byte[] bArr;
        int i;
        if (!zzfdv.zzcut() || zzcur() < 10) {
            while ((j & -128) != 0) {
                try {
                    bArr = this.buffer;
                    i = this.position;
                    this.position = i + 1;
                    bArr[i] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (Throwable e) {
                    throw new zzfdv$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            }
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) j);
            return;
        }
        while ((j & -128) != 0) {
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            zzfgj.zza(bArr, (long) i, (byte) ((((int) j) & 127) | 128));
            j >>>= 7;
        }
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        zzfgj.zza(bArr, (long) i, (byte) ((int) j));
    }

    public final void zzcu(long j) throws IOException {
        try {
            byte[] bArr = this.buffer;
            int i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) j);
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 8));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 16));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 24));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 32));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 40));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 48));
            bArr = this.buffer;
            i = this.position;
            this.position = i + 1;
            bArr[i] = (byte) ((int) (j >> 56));
        } catch (Throwable e) {
            throw new zzfdv$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
        }
    }

    public final int zzcur() {
        return this.limit - this.position;
    }

    public final void zzd(zzffi com_google_android_gms_internal_zzffi) throws IOException {
        zzkt(com_google_android_gms_internal_zzffi.zzhl());
        com_google_android_gms_internal_zzffi.zza(this);
    }

    public final void zzd(byte[] bArr, int i, int i2) throws IOException {
        write(bArr, i, i2);
    }

    public final void zzi(byte[] bArr, int i, int i2) throws IOException {
        zzkt(i2);
        write(bArr, 0, i2);
    }

    public final void zzks(int i) throws IOException {
        if (i >= 0) {
            zzkt(i);
        } else {
            zzcs((long) i);
        }
    }

    public final void zzkt(int i) throws IOException {
        byte[] bArr;
        int i2;
        if (!zzfdv.zzcut() || zzcur() < 10) {
            while ((i & -128) != 0) {
                try {
                    bArr = this.buffer;
                    i2 = this.position;
                    this.position = i2 + 1;
                    bArr[i2] = (byte) ((i & 127) | 128);
                    i >>>= 7;
                } catch (Throwable e) {
                    throw new zzfdv$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
                }
            }
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) i;
            return;
        }
        while ((i & -128) != 0) {
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            zzfgj.zza(bArr, (long) i2, (byte) ((i & 127) | 128));
            i >>>= 7;
        }
        bArr = this.buffer;
        i2 = this.position;
        this.position = i2 + 1;
        zzfgj.zza(bArr, (long) i2, (byte) i);
    }

    public final void zzkv(int i) throws IOException {
        try {
            byte[] bArr = this.buffer;
            int i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) i;
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) (i >> 8);
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = (byte) (i >> 16);
            bArr = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            bArr[i2] = i >> 24;
        } catch (Throwable e) {
            throw new zzfdv$zzc(String.format("Pos: %d, limit: %d, len: %d", new Object[]{Integer.valueOf(this.position), Integer.valueOf(this.limit), Integer.valueOf(1)}), e);
        }
    }

    public final void zzl(int i, boolean z) throws IOException {
        int i2 = 0;
        zzz(i, 0);
        if (z) {
            i2 = 1;
        }
        zzb((byte) i2);
    }

    public final void zzn(int i, String str) throws IOException {
        zzz(i, 2);
        zztc(str);
    }

    public final void zztc(String str) throws IOException {
        int i = this.position;
        try {
            int zzky = zzky(str.length() * 3);
            int zzky2 = zzky(str.length());
            if (zzky2 == zzky) {
                this.position = i + zzky2;
                zzky = zzfgl.zza(str, this.buffer, this.position, zzcur());
                this.position = i;
                zzkt((zzky - i) - zzky2);
                this.position = zzky;
                return;
            }
            zzkt(zzfgl.zzd(str));
            this.position = zzfgl.zza(str, this.buffer, this.position, zzcur());
        } catch (zzfgo e) {
            this.position = i;
            zza(str, e);
        } catch (Throwable e2) {
            throw new zzfdv$zzc(e2);
        }
    }

    public final void zzz(int i, int i2) throws IOException {
        zzkt((i << 3) | i2);
    }
}

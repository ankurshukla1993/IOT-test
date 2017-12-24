package com.google.android.gms.internal;

abstract class zzfdv$zza extends zzfdv {
    final byte[] buffer;
    final int limit;
    int position;
    int zzpbg;

    zzfdv$zza(int i) {
        super(null);
        if (i < 0) {
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }
        this.buffer = new byte[Math.max(i, 20)];
        this.limit = this.buffer.length;
    }

    final void zzah(int i, int i2) {
        zzlf((i << 3) | i2);
    }

    final void zzc(byte b) {
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = b;
        this.zzpbg++;
    }

    public final int zzcur() {
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array or ByteBuffer.");
    }

    final void zzdb(long j) {
        if (zzfdv.zzcut()) {
            byte[] bArr;
            int i;
            long j2 = (long) this.position;
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
            this.zzpbg = ((int) (((long) this.position) - j2)) + this.zzpbg;
            return;
        }
        byte[] bArr2;
        int i2;
        while ((j & -128) != 0) {
            bArr2 = this.buffer;
            i2 = this.position;
            this.position = i2 + 1;
            bArr2[i2] = (byte) ((((int) j) & 127) | 128);
            this.zzpbg++;
            j >>>= 7;
        }
        bArr2 = this.buffer;
        i2 = this.position;
        this.position = i2 + 1;
        bArr2[i2] = (byte) ((int) j);
        this.zzpbg++;
    }

    final void zzdc(long j) {
        byte[] bArr = this.buffer;
        int i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) ((int) (j & 255));
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) ((int) ((j >> 8) & 255));
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) ((int) ((j >> 16) & 255));
        bArr = this.buffer;
        i = this.position;
        this.position = i + 1;
        bArr[i] = (byte) ((int) ((j >> 24) & 255));
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
        this.zzpbg += 8;
    }

    final void zzlf(int i) {
        if (zzfdv.zzcut()) {
            byte[] bArr;
            int i2;
            long j = (long) this.position;
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
            this.zzpbg = ((int) (((long) this.position) - j)) + this.zzpbg;
            return;
        }
        byte[] bArr2;
        int i3;
        while ((i & -128) != 0) {
            bArr2 = this.buffer;
            i3 = this.position;
            this.position = i3 + 1;
            bArr2[i3] = (byte) ((i & 127) | 128);
            this.zzpbg++;
            i >>>= 7;
        }
        bArr2 = this.buffer;
        i3 = this.position;
        this.position = i3 + 1;
        bArr2[i3] = (byte) i;
        this.zzpbg++;
    }

    final void zzlg(int i) {
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
        this.zzpbg += 4;
    }
}

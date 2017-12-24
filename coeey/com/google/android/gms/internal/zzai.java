package com.google.android.gms.internal;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

final class zzai extends FilterInputStream {
    private final long zzby;
    private long zzbz;

    zzai(InputStream inputStream, long j) {
        super(inputStream);
        this.zzby = j;
    }

    public final int read() throws IOException {
        int read = super.read();
        if (read != -1) {
            this.zzbz++;
        }
        return read;
    }

    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int read = super.read(bArr, i, i2);
        if (read != -1) {
            this.zzbz += (long) read;
        }
        return read;
    }

    final long zzn() {
        return this.zzby - this.zzbz;
    }
}

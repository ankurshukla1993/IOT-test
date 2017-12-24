package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;

final class zzfft extends InputStream {
    private int mark;
    private zzffs zzpdp;
    private zzfdn zzpdq;
    private int zzpdr;
    private int zzpds;
    private int zzpdt;
    private /* synthetic */ zzffp zzpdu;

    public zzfft(zzffp com_google_android_gms_internal_zzffp) {
        this.zzpdu = com_google_android_gms_internal_zzffp;
        initialize();
    }

    private final void initialize() {
        this.zzpdp = new zzffs(this.zzpdu);
        this.zzpdq = (zzfdn) this.zzpdp.next();
        this.zzpdr = this.zzpdq.size();
        this.zzpds = 0;
        this.zzpdt = 0;
    }

    private final void zzcwi() {
        if (this.zzpdq != null && this.zzpds == this.zzpdr) {
            this.zzpdt += this.zzpdr;
            this.zzpds = 0;
            if (this.zzpdp.hasNext()) {
                this.zzpdq = (zzfdn) this.zzpdp.next();
                this.zzpdr = this.zzpdq.size();
                return;
            }
            this.zzpdq = null;
            this.zzpdr = 0;
        }
    }

    private final int zzj(byte[] bArr, int i, int i2) {
        int i3 = i2;
        int i4 = i;
        while (i3 > 0) {
            zzcwi();
            if (this.zzpdq == null) {
                if (i3 == i2) {
                    return -1;
                }
                return i2 - i3;
            }
            int min = Math.min(this.zzpdr - this.zzpds, i3);
            if (bArr != null) {
                this.zzpdq.zza(bArr, this.zzpds, i4, min);
                i4 += min;
            }
            this.zzpds += min;
            i3 -= min;
        }
        return i2 - i3;
    }

    public final int available() throws IOException {
        return this.zzpdu.size() - (this.zzpdt + this.zzpds);
    }

    public final void mark(int i) {
        this.mark = this.zzpdt + this.zzpds;
    }

    public final boolean markSupported() {
        return true;
    }

    public final int read() throws IOException {
        zzcwi();
        if (this.zzpdq == null) {
            return -1;
        }
        zzfdh com_google_android_gms_internal_zzfdh = this.zzpdq;
        int i = this.zzpds;
        this.zzpds = i + 1;
        return com_google_android_gms_internal_zzfdh.zzkd(i) & 255;
    }

    public final int read(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new NullPointerException();
        } else if (i >= 0 && i2 >= 0 && i2 <= bArr.length - i) {
            return zzj(bArr, i, i2);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public final synchronized void reset() {
        initialize();
        zzj(null, 0, this.mark);
    }

    public final long skip(long j) {
        if (j < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (j > 2147483647L) {
            j = 2147483647L;
        }
        return (long) zzj(null, 0, (int) j);
    }
}

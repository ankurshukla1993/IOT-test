package com.google.android.gms.internal;

import java.io.IOException;
import java.io.OutputStream;

final class zzfdv$zzd extends zzfdv$zza {
    private final OutputStream out;

    zzfdv$zzd(OutputStream outputStream, int i) {
        super(i);
        if (outputStream == null) {
            throw new NullPointerException("out");
        }
        this.out = outputStream;
    }

    private final void doFlush() throws IOException {
        this.out.write(this.buffer, 0, this.position);
        this.position = 0;
    }

    private final void zzlh(int i) throws IOException {
        if (this.limit - this.position < i) {
            doFlush();
        }
    }

    public final void flush() throws IOException {
        if (this.position > 0) {
            doFlush();
        }
    }

    public final void write(byte[] bArr, int i, int i2) throws IOException {
        if (this.limit - this.position >= i2) {
            System.arraycopy(bArr, i, this.buffer, this.position, i2);
            this.position += i2;
        } else {
            int i3 = this.limit - this.position;
            System.arraycopy(bArr, i, this.buffer, this.position, i3);
            int i4 = i + i3;
            i2 -= i3;
            this.position = this.limit;
            this.zzpbg = i3 + this.zzpbg;
            doFlush();
            if (i2 <= this.limit) {
                System.arraycopy(bArr, i4, this.buffer, 0, i2);
                this.position = i2;
            } else {
                this.out.write(bArr, i4, i2);
            }
        }
        this.zzpbg += i2;
    }

    public final void zza(int i, long j) throws IOException {
        zzlh(20);
        zzah(i, 0);
        zzdb(j);
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
        zzlh(20);
        zzah(i, 0);
        if (i2 >= 0) {
            zzlf(i2);
        } else {
            zzdb((long) i2);
        }
    }

    public final void zzab(int i, int i2) throws IOException {
        zzlh(20);
        zzah(i, 0);
        zzlf(i2);
    }

    public final void zzac(int i, int i2) throws IOException {
        zzlh(14);
        zzah(i, 5);
        zzlg(i2);
    }

    public final void zzam(zzfdh com_google_android_gms_internal_zzfdh) throws IOException {
        zzkt(com_google_android_gms_internal_zzfdh.size());
        com_google_android_gms_internal_zzfdh.zza(this);
    }

    public final void zzb(byte b) throws IOException {
        if (this.position == this.limit) {
            doFlush();
        }
        zzc(b);
    }

    public final void zzb(int i, long j) throws IOException {
        zzlh(18);
        zzah(i, 1);
        zzdc(j);
    }

    public final void zzcs(long j) throws IOException {
        zzlh(10);
        zzdb(j);
    }

    public final void zzcu(long j) throws IOException {
        zzlh(8);
        zzdc(j);
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
        zzlh(10);
        zzlf(i);
    }

    public final void zzkv(int i) throws IOException {
        zzlh(4);
        zzlg(i);
    }

    public final void zzl(int i, boolean z) throws IOException {
        int i2 = 0;
        zzlh(11);
        zzah(i, 0);
        if (z) {
            i2 = 1;
        }
        zzc((byte) i2);
    }

    public final void zzn(int i, String str) throws IOException {
        zzz(i, 2);
        zztc(str);
    }

    public final void zztc(String str) throws IOException {
        int i;
        try {
            int length = str.length() * 3;
            int zzky = zzky(length);
            if (zzky + length > this.limit) {
                byte[] bArr = new byte[length];
                length = zzfgl.zza(str, bArr, 0, length);
                zzkt(length);
                zzd(bArr, 0, length);
                return;
            }
            if (length + zzky > this.limit - this.position) {
                doFlush();
            }
            length = zzky(str.length());
            i = this.position;
            if (length == zzky) {
                this.position = i + length;
                zzky = zzfgl.zza(str, this.buffer, this.position, this.limit - this.position);
                this.position = i;
                length = (zzky - i) - length;
                zzlf(length);
                this.position = zzky;
            } else {
                length = zzfgl.zzd(str);
                zzlf(length);
                this.position = zzfgl.zza(str, this.buffer, this.position, length);
            }
            this.zzpbg = length + this.zzpbg;
        } catch (zzfgo e) {
            this.zzpbg -= this.position - i;
            this.position = i;
            throw e;
        } catch (Throwable e2) {
            throw new zzfdv$zzc(e2);
        } catch (zzfgo e3) {
            zza(str, e3);
        }
    }

    public final void zzz(int i, int i2) throws IOException {
        zzkt((i << 3) | i2);
    }
}

package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class zzffp extends zzfdh {
    private static final int[] zzpdg;
    private final int zzpdh;
    private final zzfdh zzpdi;
    private final zzfdh zzpdj;
    private final int zzpdk;
    private final int zzpdl;

    static {
        int i = 1;
        List arrayList = new ArrayList();
        int i2 = 1;
        while (i > 0) {
            arrayList.add(Integer.valueOf(i));
            int i3 = i2 + i;
            i2 = i;
            i = i3;
        }
        arrayList.add(Integer.valueOf(Integer.MAX_VALUE));
        zzpdg = new int[arrayList.size()];
        for (i2 = 0; i2 < zzpdg.length; i2++) {
            zzpdg[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
    }

    private zzffp(zzfdh com_google_android_gms_internal_zzfdh, zzfdh com_google_android_gms_internal_zzfdh2) {
        this.zzpdi = com_google_android_gms_internal_zzfdh;
        this.zzpdj = com_google_android_gms_internal_zzfdh2;
        this.zzpdk = com_google_android_gms_internal_zzfdh.size();
        this.zzpdh = this.zzpdk + com_google_android_gms_internal_zzfdh2.size();
        this.zzpdl = Math.max(com_google_android_gms_internal_zzfdh.zzctm(), com_google_android_gms_internal_zzfdh2.zzctm()) + 1;
    }

    static zzfdh zza(zzfdh com_google_android_gms_internal_zzfdh, zzfdh com_google_android_gms_internal_zzfdh2) {
        if (com_google_android_gms_internal_zzfdh2.size() == 0) {
            return com_google_android_gms_internal_zzfdh;
        }
        if (com_google_android_gms_internal_zzfdh.size() == 0) {
            return com_google_android_gms_internal_zzfdh2;
        }
        int size = com_google_android_gms_internal_zzfdh2.size() + com_google_android_gms_internal_zzfdh.size();
        if (size < 128) {
            return zzb(com_google_android_gms_internal_zzfdh, com_google_android_gms_internal_zzfdh2);
        }
        if (com_google_android_gms_internal_zzfdh instanceof zzffp) {
            zzffp com_google_android_gms_internal_zzffp = (zzffp) com_google_android_gms_internal_zzfdh;
            if (com_google_android_gms_internal_zzffp.zzpdj.size() + com_google_android_gms_internal_zzfdh2.size() < 128) {
                return new zzffp(com_google_android_gms_internal_zzffp.zzpdi, zzb(com_google_android_gms_internal_zzffp.zzpdj, com_google_android_gms_internal_zzfdh2));
            } else if (com_google_android_gms_internal_zzffp.zzpdi.zzctm() > com_google_android_gms_internal_zzffp.zzpdj.zzctm() && com_google_android_gms_internal_zzffp.zzctm() > com_google_android_gms_internal_zzfdh2.zzctm()) {
                return new zzffp(com_google_android_gms_internal_zzffp.zzpdi, new zzffp(com_google_android_gms_internal_zzffp.zzpdj, com_google_android_gms_internal_zzfdh2));
            }
        }
        return size >= zzpdg[Math.max(com_google_android_gms_internal_zzfdh.zzctm(), com_google_android_gms_internal_zzfdh2.zzctm()) + 1] ? new zzffp(com_google_android_gms_internal_zzfdh, com_google_android_gms_internal_zzfdh2) : new zzffr().zzc(com_google_android_gms_internal_zzfdh, com_google_android_gms_internal_zzfdh2);
    }

    private static zzfdh zzb(zzfdh com_google_android_gms_internal_zzfdh, zzfdh com_google_android_gms_internal_zzfdh2) {
        int size = com_google_android_gms_internal_zzfdh.size();
        int size2 = com_google_android_gms_internal_zzfdh2.size();
        byte[] bArr = new byte[(size + size2)];
        com_google_android_gms_internal_zzfdh.zza(bArr, 0, 0, size);
        com_google_android_gms_internal_zzfdh2.zza(bArr, 0, size, size2);
        return zzfdh.zzaz(bArr);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfdh)) {
            return false;
        }
        zzfdh com_google_android_gms_internal_zzfdh = (zzfdh) obj;
        if (this.zzpdh != com_google_android_gms_internal_zzfdh.size()) {
            return false;
        }
        if (this.zzpdh == 0) {
            return true;
        }
        int zzcto = zzcto();
        int zzcto2 = com_google_android_gms_internal_zzfdh.zzcto();
        if (zzcto != 0 && zzcto2 != 0 && zzcto != zzcto2) {
            return false;
        }
        Iterator com_google_android_gms_internal_zzffs = new zzffs(this, null);
        zzfdh com_google_android_gms_internal_zzfdh2 = (zzfdn) com_google_android_gms_internal_zzffs.next();
        Iterator com_google_android_gms_internal_zzffs2 = new zzffs(com_google_android_gms_internal_zzfdh, null);
        zzfdh com_google_android_gms_internal_zzfdh3 = (zzfdn) com_google_android_gms_internal_zzffs2.next();
        int i = 0;
        zzfdh com_google_android_gms_internal_zzfdh4 = com_google_android_gms_internal_zzfdh2;
        int i2 = 0;
        zzcto = 0;
        while (true) {
            int size = com_google_android_gms_internal_zzfdh4.size() - i2;
            int size2 = com_google_android_gms_internal_zzfdh3.size() - i;
            int min = Math.min(size, size2);
            if (!(i2 == 0 ? com_google_android_gms_internal_zzfdh4.zza(com_google_android_gms_internal_zzfdh3, i, min) : com_google_android_gms_internal_zzfdh3.zza(com_google_android_gms_internal_zzfdh4, i2, min))) {
                return false;
            }
            zzcto2 = zzcto + min;
            if (zzcto2 >= this.zzpdh) {
                break;
            }
            if (min == size) {
                com_google_android_gms_internal_zzfdh4 = (zzfdn) com_google_android_gms_internal_zzffs.next();
                i2 = 0;
            } else {
                i2 += min;
            }
            if (min == size2) {
                com_google_android_gms_internal_zzfdh3 = (zzfdn) com_google_android_gms_internal_zzffs2.next();
                i = 0;
                zzcto = zzcto2;
            } else {
                i += min;
                zzcto = zzcto2;
            }
        }
        if (zzcto2 == this.zzpdh) {
            return true;
        }
        throw new IllegalStateException();
    }

    public final int size() {
        return this.zzpdh;
    }

    final void zza(zzfdg com_google_android_gms_internal_zzfdg) throws IOException {
        this.zzpdi.zza(com_google_android_gms_internal_zzfdg);
        this.zzpdj.zza(com_google_android_gms_internal_zzfdg);
    }

    protected final void zzb(byte[] bArr, int i, int i2, int i3) {
        if (i + i3 <= this.zzpdk) {
            this.zzpdi.zzb(bArr, i, i2, i3);
        } else if (i >= this.zzpdk) {
            this.zzpdj.zzb(bArr, i - this.zzpdk, i2, i3);
        } else {
            int i4 = this.zzpdk - i;
            this.zzpdi.zzb(bArr, i, i2, i4);
            this.zzpdj.zzb(bArr, 0, i2 + i4, i3 - i4);
        }
    }

    public final zzfdq zzctl() {
        return zzfdq.zzi(new zzfft(this));
    }

    protected final int zzctm() {
        return this.zzpdl;
    }

    protected final boolean zzctn() {
        return this.zzpdh >= zzpdg[this.zzpdl];
    }

    protected final int zzg(int i, int i2, int i3) {
        if (i2 + i3 <= this.zzpdk) {
            return this.zzpdi.zzg(i, i2, i3);
        }
        if (i2 >= this.zzpdk) {
            return this.zzpdj.zzg(i, i2 - this.zzpdk, i3);
        }
        int i4 = this.zzpdk - i2;
        return this.zzpdj.zzg(this.zzpdi.zzg(i, i2, i4), 0, i3 - i4);
    }

    public final byte zzkd(int i) {
        zzfdh.zzy(i, this.zzpdh);
        return i < this.zzpdk ? this.zzpdi.zzkd(i) : this.zzpdj.zzkd(i - this.zzpdk);
    }

    public final zzfdh zzx(int i, int i2) {
        int zzh = zzfdh.zzh(i, i2, this.zzpdh);
        if (zzh == 0) {
            return zzfdh.zzpal;
        }
        if (zzh == this.zzpdh) {
            return this;
        }
        if (i2 <= this.zzpdk) {
            return this.zzpdi.zzx(i, i2);
        }
        if (i >= this.zzpdk) {
            return this.zzpdj.zzx(i - this.zzpdk, i2 - this.zzpdk);
        }
        zzfdh com_google_android_gms_internal_zzfdh = this.zzpdi;
        return new zzffp(com_google_android_gms_internal_zzfdh.zzx(i, com_google_android_gms_internal_zzfdh.size()), this.zzpdj.zzx(0, i2 - this.zzpdk));
    }
}

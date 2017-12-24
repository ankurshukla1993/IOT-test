package com.google.android.gms.internal;

import java.util.Arrays;

final class zzfhm {
    final int tag;
    final byte[] zzjkl;

    zzfhm(int i, byte[] bArr) {
        this.tag = i;
        this.zzjkl = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfhm)) {
            return false;
        }
        zzfhm com_google_android_gms_internal_zzfhm = (zzfhm) obj;
        return this.tag == com_google_android_gms_internal_zzfhm.tag && Arrays.equals(this.zzjkl, com_google_android_gms_internal_zzfhm.zzjkl);
    }

    public final int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzjkl);
    }
}

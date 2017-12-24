package com.google.android.gms.common;

import java.util.Arrays;

final class zzh extends zzg {
    private final byte[] zzfii;

    zzh(byte[] bArr) {
        super(Arrays.copyOfRange(bArr, 0, 25));
        this.zzfii = bArr;
    }

    final byte[] getBytes() {
        return this.zzfii;
    }
}

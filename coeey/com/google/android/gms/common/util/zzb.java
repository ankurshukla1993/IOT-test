package com.google.android.gms.common.util;

import android.util.Base64;

public final class zzb {
    public static String zzj(byte[] bArr) {
        return bArr == null ? null : Base64.encodeToString(bArr, 0);
    }

    public static String zzk(byte[] bArr) {
        return bArr == null ? null : Base64.encodeToString(bArr, 10);
    }

    public static String zzl(byte[] bArr) {
        return bArr == null ? null : Base64.encodeToString(bArr, 11);
    }
}

package com.google.android.gms.common;

import android.util.Log;
import com.google.android.gms.common.internal.zzat;
import com.google.android.gms.common.internal.zzau;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzl;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

abstract class zzg extends zzau {
    private int zzfih;

    protected zzg(byte[] bArr) {
        boolean z = false;
        if (bArr.length != 25) {
            int length = bArr.length;
            String zza = zzl.zza(bArr, 0, bArr.length, false);
            Log.wtf("GoogleCertificates", new StringBuilder(String.valueOf(zza).length() + 51).append("Cert hash data has incorrect length (").append(length).append("):\n").append(zza).toString(), new Exception());
            bArr = Arrays.copyOfRange(bArr, 0, 25);
            if (bArr.length == 25) {
                z = true;
            }
            zzbq.checkArgument(z, "cert hash data has incorrect length. length=" + bArr.length);
        }
        this.zzfih = Arrays.hashCode(bArr);
    }

    protected static byte[] zzfs(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof zzat)) {
            return false;
        }
        try {
            zzat com_google_android_gms_common_internal_zzat = (zzat) obj;
            if (com_google_android_gms_common_internal_zzat.zzafp() != hashCode()) {
                return false;
            }
            IObjectWrapper zzafo = com_google_android_gms_common_internal_zzat.zzafo();
            if (zzafo == null) {
                return false;
            }
            return Arrays.equals(getBytes(), (byte[]) zzn.zzx(zzafo));
        } catch (Throwable e) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            return false;
        }
    }

    abstract byte[] getBytes();

    public int hashCode() {
        return this.zzfih;
    }

    public final IObjectWrapper zzafo() {
        return zzn.zzy(getBytes());
    }

    public final int zzafp() {
        return hashCode();
    }
}

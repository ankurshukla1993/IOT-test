package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

final class zzah {
    final String key;
    final String zza;
    final long zzb;
    long zzbx;
    final long zzc;
    final long zzd;
    final long zze;
    final Map<String, String> zzf;

    zzah(String str, zzc com_google_android_gms_internal_zzc) {
        this(str, com_google_android_gms_internal_zzc.zza, com_google_android_gms_internal_zzc.zzb, com_google_android_gms_internal_zzc.zzc, com_google_android_gms_internal_zzc.zzd, com_google_android_gms_internal_zzc.zze, com_google_android_gms_internal_zzc.zzf);
        this.zzbx = (long) com_google_android_gms_internal_zzc.data.length;
    }

    private zzah(String str, String str2, long j, long j2, long j3, long j4, Map<String, String> map) {
        this.key = str;
        if ("".equals(str2)) {
            str2 = null;
        }
        this.zza = str2;
        this.zzb = j;
        this.zzc = j2;
        this.zzd = j3;
        this.zze = j4;
        this.zzf = map;
    }

    static zzah zzc(zzai com_google_android_gms_internal_zzai) throws IOException {
        if (zzag.zzb((InputStream) com_google_android_gms_internal_zzai) == 538247942) {
            return new zzah(zzag.zza(com_google_android_gms_internal_zzai), zzag.zza(com_google_android_gms_internal_zzai), zzag.zzc(com_google_android_gms_internal_zzai), zzag.zzc(com_google_android_gms_internal_zzai), zzag.zzc(com_google_android_gms_internal_zzai), zzag.zzc(com_google_android_gms_internal_zzai), zzag.zzb(com_google_android_gms_internal_zzai));
        }
        throw new IOException();
    }

    final boolean zza(OutputStream outputStream) {
        try {
            zzag.zza(outputStream, 538247942);
            zzag.zza(outputStream, this.key);
            zzag.zza(outputStream, this.zza == null ? "" : this.zza);
            zzag.zza(outputStream, this.zzb);
            zzag.zza(outputStream, this.zzc);
            zzag.zza(outputStream, this.zzd);
            zzag.zza(outputStream, this.zze);
            Map map = this.zzf;
            if (map != null) {
                zzag.zza(outputStream, map.size());
                for (Entry entry : map.entrySet()) {
                    zzag.zza(outputStream, (String) entry.getKey());
                    zzag.zza(outputStream, (String) entry.getValue());
                }
            } else {
                zzag.zza(outputStream, 0);
            }
            outputStream.flush();
            return true;
        } catch (IOException e) {
            zzab.zzb("%s", e.toString());
            return false;
        }
    }
}

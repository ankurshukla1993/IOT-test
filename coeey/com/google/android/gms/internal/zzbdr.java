package com.google.android.gms.internal;

import android.content.Context;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public final class zzbdr implements zzbcy {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    private static final zzctn zzfho = new zzctn(zzctc.zzkm("com.google.android.gms.clearcut.public")).zzko("gms:playlog:service:sampling_").zzkp("LogSampling__");
    private static Map<String, zzctg<String>> zzfhp = null;
    private static Boolean zzfhq = null;
    private static Long zzfhr = null;
    private final Context zzaif;

    public zzbdr(Context context) {
        this.zzaif = context;
        if (zzfhp == null) {
            zzfhp = new HashMap();
        }
        if (this.zzaif != null) {
            zzctg.zzdw(this.zzaif);
        }
    }

    private static boolean zzbz(Context context) {
        if (zzfhq == null) {
            zzfhq = Boolean.valueOf(zzbgc.zzcy(context).checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
        }
        return zzfhq.booleanValue();
    }

    private static zzbds zzfr(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        String str2 = "";
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        }
        int indexOf2 = str.indexOf(47, i);
        if (indexOf2 <= 0) {
            str2 = "LogSamplerImpl";
            String str3 = "Failed to parse the rule: ";
            String valueOf = String.valueOf(str);
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return new zzbds(str2, parseLong, parseLong2);
            }
            Log.e("LogSamplerImpl", "negative values not supported: " + parseLong + "/" + parseLong2);
            return null;
        } catch (Throwable e) {
            Throwable th = e;
            str3 = "LogSamplerImpl";
            String str4 = "parseLong() failed while parsing: ";
            valueOf = String.valueOf(str);
            Log.e(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4), th);
            return null;
        }
    }

    public final boolean zzg(String str, int i) {
        if (str == null || str.isEmpty()) {
            str = i >= 0 ? String.valueOf(i) : null;
        }
        if (str == null) {
            return true;
        }
        String str2;
        if (this.zzaif == null || !zzbz(this.zzaif)) {
            str2 = null;
        } else {
            zzctg com_google_android_gms_internal_zzctg = (zzctg) zzfhp.get(str);
            if (com_google_android_gms_internal_zzctg == null) {
                com_google_android_gms_internal_zzctg = zzfho.zzaw(str, null);
                zzfhp.put(str, com_google_android_gms_internal_zzctg);
            }
            str2 = (String) com_google_android_gms_internal_zzctg.get();
        }
        zzbds zzfr = zzfr(str2);
        if (zzfr == null) {
            return true;
        }
        long j;
        long j2;
        long j3;
        String str3 = zzfr.zzfhs;
        Context context = this.zzaif;
        if (zzfhr == null) {
            if (context == null) {
                j = 0;
                if (str3 != null || str3.isEmpty()) {
                    j = zzbdm.zzi(ByteBuffer.allocate(8).putLong(j).array());
                } else {
                    byte[] bytes = str3.getBytes(UTF_8);
                    ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
                    allocate.put(bytes);
                    allocate.putLong(j);
                    j = zzbdm.zzi(allocate.array());
                }
                j2 = zzfr.zzfht;
                j3 = zzfr.zzfhu;
                if (j2 >= 0 || j3 < 0) {
                    throw new IllegalArgumentException("negative values not supported: " + j2 + "/" + j3);
                }
                if (j3 > 0) {
                    if ((j >= 0 ? j % j3 : (((j & Long.MAX_VALUE) % j3) + ((Long.MAX_VALUE % j3) + 1)) % j3) < j2) {
                        return true;
                    }
                }
                return false;
            } else if (zzbz(context)) {
                zzfhr = Long.valueOf(zzdld.getLong(context.getContentResolver(), "android_id", 0));
            } else {
                zzfhr = Long.valueOf(0);
            }
        }
        j = zzfhr.longValue();
        if (str3 != null) {
        }
        j = zzbdm.zzi(ByteBuffer.allocate(8).putLong(j).array());
        j2 = zzfr.zzfht;
        j3 = zzfr.zzfhu;
        if (j2 >= 0) {
        }
        throw new IllegalArgumentException("negative values not supported: " + j2 + "/" + j3);
    }
}

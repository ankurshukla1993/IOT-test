package com.google.android.gms.internal;

import java.io.UnsupportedEncodingException;

public class zzaq extends zzp<String> {
    private final zzv<String> zzcd;

    public zzaq(int i, String str, zzv<String> com_google_android_gms_internal_zzv_java_lang_String, zzu com_google_android_gms_internal_zzu) {
        super(i, str, com_google_android_gms_internal_zzu);
        this.zzcd = com_google_android_gms_internal_zzv_java_lang_String;
    }

    protected final zzt<String> zza(zzn com_google_android_gms_internal_zzn) {
        Object str;
        try {
            str = new String(com_google_android_gms_internal_zzn.data, zzal.zza(com_google_android_gms_internal_zzn.zzy));
        } catch (UnsupportedEncodingException e) {
            str = new String(com_google_android_gms_internal_zzn.data);
        }
        return zzt.zza(str, zzal.zzb(com_google_android_gms_internal_zzn));
    }

    protected final /* synthetic */ void zza(Object obj) {
        String str = (String) obj;
        if (this.zzcd != null) {
            this.zzcd.zzb(str);
        }
    }
}

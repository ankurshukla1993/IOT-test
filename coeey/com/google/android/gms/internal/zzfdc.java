package com.google.android.gms.internal;

public abstract class zzfdc<MessageType extends zzffi> implements zzffm<MessageType> {
    private static final zzfea zzpaj = zzfea.zzcuz();

    public final /* synthetic */ Object zzc(zzfdq com_google_android_gms_internal_zzfdq, zzfea com_google_android_gms_internal_zzfea) throws zzfew {
        zzffi com_google_android_gms_internal_zzffi = (zzffi) zze(com_google_android_gms_internal_zzfdq, com_google_android_gms_internal_zzfea);
        if (com_google_android_gms_internal_zzffi == null || com_google_android_gms_internal_zzffi.isInitialized()) {
            return com_google_android_gms_internal_zzffi;
        }
        zzfgh com_google_android_gms_internal_zzfgh = com_google_android_gms_internal_zzffi instanceof zzfcz ? new zzfgh((zzfcz) com_google_android_gms_internal_zzffi) : com_google_android_gms_internal_zzffi instanceof zzfdb ? new zzfgh((zzfdb) com_google_android_gms_internal_zzffi) : new zzfgh(com_google_android_gms_internal_zzffi);
        throw com_google_android_gms_internal_zzfgh.zzcwt().zzh(com_google_android_gms_internal_zzffi);
    }
}

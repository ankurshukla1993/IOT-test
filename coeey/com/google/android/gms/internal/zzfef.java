package com.google.android.gms.internal;

import java.io.IOException;

public class zzfef<MessageType extends zzfee<MessageType, BuilderType>, BuilderType extends zzfef<MessageType, BuilderType>> extends zzfda<MessageType, BuilderType> {
    private final MessageType zzpbu;
    protected MessageType zzpbv;
    private boolean zzpbw = false;

    protected zzfef(MessageType messageType) {
        this.zzpbu = messageType;
        this.zzpbv = (zzfee) messageType.zza(zzfem.zzpcg, null, null);
    }

    private static void zza(MessageType messageType, MessageType messageType2) {
        Object obj = zzfel.zzpcb;
        messageType.zza(zzfem.zzpcd, obj, (Object) messageType2);
        messageType.zzpbs = obj.zza(messageType.zzpbs, messageType2.zzpbs);
    }

    private final BuilderType zzd(zzfdq com_google_android_gms_internal_zzfdq, zzfea com_google_android_gms_internal_zzfea) throws IOException {
        zzcvi();
        try {
            this.zzpbv.zza(zzfem.zzpce, (Object) com_google_android_gms_internal_zzfdq, (Object) com_google_android_gms_internal_zzfea);
            return this;
        } catch (RuntimeException e) {
            if (e.getCause() instanceof IOException) {
                throw ((IOException) e.getCause());
            }
            throw e;
        }
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzfee com_google_android_gms_internal_zzfee;
        zzfef com_google_android_gms_internal_zzfef = (zzfef) this.zzpbu.zza(zzfem.zzpch, null, null);
        if (this.zzpbw) {
            com_google_android_gms_internal_zzfee = this.zzpbv;
        } else {
            com_google_android_gms_internal_zzfee = this.zzpbv;
            com_google_android_gms_internal_zzfee.zza(zzfem.zzpcf, null, null);
            com_google_android_gms_internal_zzfee.zzpbs.zzbim();
            this.zzpbw = true;
            com_google_android_gms_internal_zzfee = this.zzpbv;
        }
        com_google_android_gms_internal_zzfef.zza(com_google_android_gms_internal_zzfee);
        return com_google_android_gms_internal_zzfef;
    }

    public final boolean isInitialized() {
        return this.zzpbv.zza(zzfem.zzpcc, Boolean.valueOf(false), null) != null;
    }

    protected final /* synthetic */ zzfda zza(zzfcz com_google_android_gms_internal_zzfcz) {
        return zza((zzfee) com_google_android_gms_internal_zzfcz);
    }

    public final /* synthetic */ zzfda zza(zzfdq com_google_android_gms_internal_zzfdq, zzfea com_google_android_gms_internal_zzfea) throws IOException {
        return (zzfef) zzb(com_google_android_gms_internal_zzfdq, com_google_android_gms_internal_zzfea);
    }

    public final BuilderType zza(MessageType messageType) {
        zzcvi();
        zza(this.zzpbv, (zzfee) messageType);
        return this;
    }

    public final /* synthetic */ zzffj zzb(zzfdq com_google_android_gms_internal_zzfdq, zzfea com_google_android_gms_internal_zzfea) throws IOException {
        return zzd(com_google_android_gms_internal_zzfdq, com_google_android_gms_internal_zzfea);
    }

    public final /* synthetic */ zzfda zzctg() {
        return (zzfef) clone();
    }

    public final /* synthetic */ zzffi zzcvh() {
        return this.zzpbu;
    }

    protected final void zzcvi() {
        if (this.zzpbw) {
            zzfee com_google_android_gms_internal_zzfee = (zzfee) this.zzpbv.zza(zzfem.zzpcg, null, null);
            zza(com_google_android_gms_internal_zzfee, this.zzpbv);
            this.zzpbv = com_google_android_gms_internal_zzfee;
            this.zzpbw = false;
        }
    }

    public final MessageType zzcvj() {
        if (this.zzpbw) {
            return this.zzpbv;
        }
        zzfee com_google_android_gms_internal_zzfee = this.zzpbv;
        com_google_android_gms_internal_zzfee.zza(zzfem.zzpcf, null, null);
        com_google_android_gms_internal_zzfee.zzpbs.zzbim();
        this.zzpbw = true;
        return this.zzpbv;
    }

    public final MessageType zzcvk() {
        zzfee com_google_android_gms_internal_zzfee;
        boolean z = true;
        if (this.zzpbw) {
            com_google_android_gms_internal_zzfee = this.zzpbv;
        } else {
            com_google_android_gms_internal_zzfee = this.zzpbv;
            com_google_android_gms_internal_zzfee.zza(zzfem.zzpcf, null, null);
            com_google_android_gms_internal_zzfee.zzpbs.zzbim();
            this.zzpbw = true;
            com_google_android_gms_internal_zzfee = this.zzpbv;
        }
        com_google_android_gms_internal_zzfee = com_google_android_gms_internal_zzfee;
        if (com_google_android_gms_internal_zzfee.zza(zzfem.zzpcc, Boolean.TRUE, null) == null) {
            z = false;
        }
        if (z) {
            return com_google_android_gms_internal_zzfee;
        }
        throw new zzfgh(com_google_android_gms_internal_zzfee);
    }

    public final /* synthetic */ zzffi zzcvl() {
        if (this.zzpbw) {
            return this.zzpbv;
        }
        zzfee com_google_android_gms_internal_zzfee = this.zzpbv;
        com_google_android_gms_internal_zzfee.zza(zzfem.zzpcf, null, null);
        com_google_android_gms_internal_zzfee.zzpbs.zzbim();
        this.zzpbw = true;
        return this.zzpbv;
    }

    public final /* synthetic */ zzffi zzcvm() {
        zzfee com_google_android_gms_internal_zzfee;
        boolean z = true;
        if (this.zzpbw) {
            com_google_android_gms_internal_zzfee = this.zzpbv;
        } else {
            com_google_android_gms_internal_zzfee = this.zzpbv;
            com_google_android_gms_internal_zzfee.zza(zzfem.zzpcf, null, null);
            com_google_android_gms_internal_zzfee.zzpbs.zzbim();
            this.zzpbw = true;
            com_google_android_gms_internal_zzfee = this.zzpbv;
        }
        com_google_android_gms_internal_zzfee = com_google_android_gms_internal_zzfee;
        if (com_google_android_gms_internal_zzfee.zza(zzfem.zzpcc, Boolean.TRUE, null) == null) {
            z = false;
        }
        if (z) {
            return com_google_android_gms_internal_zzfee;
        }
        throw new zzfgh(com_google_android_gms_internal_zzfee);
    }
}

package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfim extends zzfhe<zzfim> {
    public long zzgew;
    public String zzpld;
    public String zzple;
    public long zzplf;
    public String zzplg;
    public long zzplh;
    public String zzpli;
    public String zzplj;
    public String zzplk;
    public String zzpll;
    public String zzplm;
    public int zzpln;
    public zzfil[] zzplo;

    public zzfim() {
        this.zzpld = "";
        this.zzple = "";
        this.zzplf = 0;
        this.zzplg = "";
        this.zzplh = 0;
        this.zzgew = 0;
        this.zzpli = "";
        this.zzplj = "";
        this.zzplk = "";
        this.zzpll = "";
        this.zzplm = "";
        this.zzpln = 0;
        this.zzplo = zzfil.zzcyc();
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzfim zzbh(byte[] bArr) throws zzfhj {
        return (zzfim) zzfhk.zza(new zzfim(), bArr);
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        while (true) {
            int zzcts = com_google_android_gms_internal_zzfhb.zzcts();
            switch (zzcts) {
                case 0:
                    break;
                case 10:
                    this.zzpld = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 18:
                    this.zzple = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 24:
                    this.zzplf = com_google_android_gms_internal_zzfhb.zzctu();
                    continue;
                case 34:
                    this.zzplg = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 40:
                    this.zzplh = com_google_android_gms_internal_zzfhb.zzctu();
                    continue;
                case 48:
                    this.zzgew = com_google_android_gms_internal_zzfhb.zzctu();
                    continue;
                case 58:
                    this.zzpli = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 66:
                    this.zzplj = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 74:
                    this.zzplk = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 82:
                    this.zzpll = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 90:
                    this.zzplm = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 96:
                    this.zzpln = com_google_android_gms_internal_zzfhb.zzctv();
                    continue;
                case 106:
                    int zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 106);
                    zzcts = this.zzplo == null ? 0 : this.zzplo.length;
                    Object obj = new zzfil[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzplo, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzfil();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzfil();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzplo = obj;
                    continue;
                default:
                    if (!super.zza(com_google_android_gms_internal_zzfhb, zzcts)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzfhc com_google_android_gms_internal_zzfhc) throws IOException {
        if (!(this.zzpld == null || this.zzpld.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(1, this.zzpld);
        }
        if (!(this.zzple == null || this.zzple.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.zzple);
        }
        if (this.zzplf != 0) {
            com_google_android_gms_internal_zzfhc.zzf(3, this.zzplf);
        }
        if (!(this.zzplg == null || this.zzplg.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(4, this.zzplg);
        }
        if (this.zzplh != 0) {
            com_google_android_gms_internal_zzfhc.zzf(5, this.zzplh);
        }
        if (this.zzgew != 0) {
            com_google_android_gms_internal_zzfhc.zzf(6, this.zzgew);
        }
        if (!(this.zzpli == null || this.zzpli.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(7, this.zzpli);
        }
        if (!(this.zzplj == null || this.zzplj.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(8, this.zzplj);
        }
        if (!(this.zzplk == null || this.zzplk.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(9, this.zzplk);
        }
        if (!(this.zzpll == null || this.zzpll.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(10, this.zzpll);
        }
        if (!(this.zzplm == null || this.zzplm.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(11, this.zzplm);
        }
        if (this.zzpln != 0) {
            com_google_android_gms_internal_zzfhc.zzaa(12, this.zzpln);
        }
        if (this.zzplo != null && this.zzplo.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzplo) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    com_google_android_gms_internal_zzfhc.zza(13, com_google_android_gms_internal_zzfhk);
                }
            }
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (!(this.zzpld == null || this.zzpld.equals(""))) {
            zzo += zzfhc.zzo(1, this.zzpld);
        }
        if (!(this.zzple == null || this.zzple.equals(""))) {
            zzo += zzfhc.zzo(2, this.zzple);
        }
        if (this.zzplf != 0) {
            zzo += zzfhc.zzc(3, this.zzplf);
        }
        if (!(this.zzplg == null || this.zzplg.equals(""))) {
            zzo += zzfhc.zzo(4, this.zzplg);
        }
        if (this.zzplh != 0) {
            zzo += zzfhc.zzc(5, this.zzplh);
        }
        if (this.zzgew != 0) {
            zzo += zzfhc.zzc(6, this.zzgew);
        }
        if (!(this.zzpli == null || this.zzpli.equals(""))) {
            zzo += zzfhc.zzo(7, this.zzpli);
        }
        if (!(this.zzplj == null || this.zzplj.equals(""))) {
            zzo += zzfhc.zzo(8, this.zzplj);
        }
        if (!(this.zzplk == null || this.zzplk.equals(""))) {
            zzo += zzfhc.zzo(9, this.zzplk);
        }
        if (!(this.zzpll == null || this.zzpll.equals(""))) {
            zzo += zzfhc.zzo(10, this.zzpll);
        }
        if (!(this.zzplm == null || this.zzplm.equals(""))) {
            zzo += zzfhc.zzo(11, this.zzplm);
        }
        if (this.zzpln != 0) {
            zzo += zzfhc.zzad(12, this.zzpln);
        }
        if (this.zzplo == null || this.zzplo.length <= 0) {
            return zzo;
        }
        int i = zzo;
        for (zzfhk com_google_android_gms_internal_zzfhk : this.zzplo) {
            if (com_google_android_gms_internal_zzfhk != null) {
                i += zzfhc.zzb(13, com_google_android_gms_internal_zzfhk);
            }
        }
        return i;
    }
}

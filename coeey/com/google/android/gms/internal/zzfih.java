package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzfih extends zzfhe<zzfih> implements Cloneable {
    private byte[] zzpkc;
    private String zzpkd;
    private byte[][] zzpke;
    private boolean zzpkf;

    public zzfih() {
        this.zzpkc = zzfhn.zzphr;
        this.zzpkd = "";
        this.zzpke = zzfhn.zzphq;
        this.zzpkf = false;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    private zzfih zzcxx() {
        try {
            zzfih com_google_android_gms_internal_zzfih = (zzfih) super.zzcxe();
            if (this.zzpke != null && this.zzpke.length > 0) {
                com_google_android_gms_internal_zzfih.zzpke = (byte[][]) this.zzpke.clone();
            }
            return com_google_android_gms_internal_zzfih;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcxx();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfih)) {
            return false;
        }
        zzfih com_google_android_gms_internal_zzfih = (zzfih) obj;
        if (!Arrays.equals(this.zzpkc, com_google_android_gms_internal_zzfih.zzpkc)) {
            return false;
        }
        if (this.zzpkd == null) {
            if (com_google_android_gms_internal_zzfih.zzpkd != null) {
                return false;
            }
        } else if (!this.zzpkd.equals(com_google_android_gms_internal_zzfih.zzpkd)) {
            return false;
        }
        return !zzfhi.zza(this.zzpke, com_google_android_gms_internal_zzfih.zzpke) ? false : this.zzpkf != com_google_android_gms_internal_zzfih.zzpkf ? false : (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzfih.zzpgy == null || com_google_android_gms_internal_zzfih.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzfih.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzpkf ? 1231 : 1237) + (((((this.zzpkd == null ? 0 : this.zzpkd.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzpkc)) * 31)) * 31) + zzfhi.zzd(this.zzpke)) * 31)) * 31;
        if (!(this.zzpgy == null || this.zzpgy.isEmpty())) {
            i = this.zzpgy.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        while (true) {
            int zzcts = com_google_android_gms_internal_zzfhb.zzcts();
            switch (zzcts) {
                case 0:
                    break;
                case 10:
                    this.zzpkc = com_google_android_gms_internal_zzfhb.readBytes();
                    continue;
                case 18:
                    int zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 18);
                    zzcts = this.zzpke == null ? 0 : this.zzpke.length;
                    Object obj = new byte[(zzb + zzcts)][];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzpke, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = com_google_android_gms_internal_zzfhb.readBytes();
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = com_google_android_gms_internal_zzfhb.readBytes();
                    this.zzpke = obj;
                    continue;
                case 24:
                    this.zzpkf = com_google_android_gms_internal_zzfhb.zzcty();
                    continue;
                case 34:
                    this.zzpkd = com_google_android_gms_internal_zzfhb.readString();
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
        if (!Arrays.equals(this.zzpkc, zzfhn.zzphr)) {
            com_google_android_gms_internal_zzfhc.zzc(1, this.zzpkc);
        }
        if (this.zzpke != null && this.zzpke.length > 0) {
            for (byte[] bArr : this.zzpke) {
                if (bArr != null) {
                    com_google_android_gms_internal_zzfhc.zzc(2, bArr);
                }
            }
        }
        if (this.zzpkf) {
            com_google_android_gms_internal_zzfhc.zzl(3, this.zzpkf);
        }
        if (!(this.zzpkd == null || this.zzpkd.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(4, this.zzpkd);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    public final /* synthetic */ zzfhe zzcxe() throws CloneNotSupportedException {
        return (zzfih) clone();
    }

    public final /* synthetic */ zzfhk zzcxf() throws CloneNotSupportedException {
        return (zzfih) clone();
    }

    protected final int zzo() {
        int i = 0;
        int zzo = super.zzo();
        if (!Arrays.equals(this.zzpkc, zzfhn.zzphr)) {
            zzo += zzfhc.zzd(1, this.zzpkc);
        }
        if (this.zzpke != null && this.zzpke.length > 0) {
            int i2 = 0;
            int i3 = 0;
            while (i < this.zzpke.length) {
                byte[] bArr = this.zzpke[i];
                if (bArr != null) {
                    i3++;
                    i2 += zzfhc.zzbf(bArr);
                }
                i++;
            }
            zzo = (zzo + i2) + (i3 * 1);
        }
        if (this.zzpkf) {
            zzo += zzfhc.zzkw(3) + 1;
        }
        return (this.zzpkd == null || this.zzpkd.equals("")) ? zzo : zzo + zzfhc.zzo(4, this.zzpkd);
    }
}

package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfif extends zzfhe<zzfif> implements Cloneable {
    private String[] zzpjw;
    private String[] zzpjx;
    private int[] zzpjy;
    private long[] zzpjz;
    private long[] zzpka;

    public zzfif() {
        this.zzpjw = zzfhn.EMPTY_STRING_ARRAY;
        this.zzpjx = zzfhn.EMPTY_STRING_ARRAY;
        this.zzpjy = zzfhn.zzphl;
        this.zzpjz = zzfhn.zzphm;
        this.zzpka = zzfhn.zzphm;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    private zzfif zzcxv() {
        try {
            zzfif com_google_android_gms_internal_zzfif = (zzfif) super.zzcxe();
            if (this.zzpjw != null && this.zzpjw.length > 0) {
                com_google_android_gms_internal_zzfif.zzpjw = (String[]) this.zzpjw.clone();
            }
            if (this.zzpjx != null && this.zzpjx.length > 0) {
                com_google_android_gms_internal_zzfif.zzpjx = (String[]) this.zzpjx.clone();
            }
            if (this.zzpjy != null && this.zzpjy.length > 0) {
                com_google_android_gms_internal_zzfif.zzpjy = (int[]) this.zzpjy.clone();
            }
            if (this.zzpjz != null && this.zzpjz.length > 0) {
                com_google_android_gms_internal_zzfif.zzpjz = (long[]) this.zzpjz.clone();
            }
            if (this.zzpka != null && this.zzpka.length > 0) {
                com_google_android_gms_internal_zzfif.zzpka = (long[]) this.zzpka.clone();
            }
            return com_google_android_gms_internal_zzfif;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcxv();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfif)) {
            return false;
        }
        zzfif com_google_android_gms_internal_zzfif = (zzfif) obj;
        return !zzfhi.equals(this.zzpjw, com_google_android_gms_internal_zzfif.zzpjw) ? false : !zzfhi.equals(this.zzpjx, com_google_android_gms_internal_zzfif.zzpjx) ? false : !zzfhi.equals(this.zzpjy, com_google_android_gms_internal_zzfif.zzpjy) ? false : !zzfhi.equals(this.zzpjz, com_google_android_gms_internal_zzfif.zzpjz) ? false : !zzfhi.equals(this.zzpka, com_google_android_gms_internal_zzfif.zzpka) ? false : (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzfif.zzpgy == null || com_google_android_gms_internal_zzfif.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzfif.zzpgy);
    }

    public final int hashCode() {
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + zzfhi.hashCode(this.zzpjw)) * 31) + zzfhi.hashCode(this.zzpjx)) * 31) + zzfhi.hashCode(this.zzpjy)) * 31) + zzfhi.hashCode(this.zzpjz)) * 31) + zzfhi.hashCode(this.zzpka)) * 31;
        int hashCode2 = (this.zzpgy == null || this.zzpgy.isEmpty()) ? 0 : this.zzpgy.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        while (true) {
            int zzcts = com_google_android_gms_internal_zzfhb.zzcts();
            int zzb;
            Object obj;
            int zzki;
            Object obj2;
            switch (zzcts) {
                case 0:
                    break;
                case 10:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 10);
                    zzcts = this.zzpjw == null ? 0 : this.zzpjw.length;
                    obj = new String[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzpjw, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = com_google_android_gms_internal_zzfhb.readString();
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = com_google_android_gms_internal_zzfhb.readString();
                    this.zzpjw = obj;
                    continue;
                case 18:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 18);
                    zzcts = this.zzpjx == null ? 0 : this.zzpjx.length;
                    obj = new String[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzpjx, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = com_google_android_gms_internal_zzfhb.readString();
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = com_google_android_gms_internal_zzfhb.readString();
                    this.zzpjx = obj;
                    continue;
                case 24:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 24);
                    zzcts = this.zzpjy == null ? 0 : this.zzpjy.length;
                    obj = new int[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzpjy, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = com_google_android_gms_internal_zzfhb.zzctv();
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = com_google_android_gms_internal_zzfhb.zzctv();
                    this.zzpjy = obj;
                    continue;
                case 26:
                    zzki = com_google_android_gms_internal_zzfhb.zzki(com_google_android_gms_internal_zzfhb.zzcuh());
                    zzb = com_google_android_gms_internal_zzfhb.getPosition();
                    zzcts = 0;
                    while (com_google_android_gms_internal_zzfhb.zzcuj() > 0) {
                        com_google_android_gms_internal_zzfhb.zzctv();
                        zzcts++;
                    }
                    com_google_android_gms_internal_zzfhb.zzlv(zzb);
                    zzb = this.zzpjy == null ? 0 : this.zzpjy.length;
                    obj2 = new int[(zzcts + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzpjy, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfhb.zzctv();
                        zzb++;
                    }
                    this.zzpjy = obj2;
                    com_google_android_gms_internal_zzfhb.zzkj(zzki);
                    continue;
                case 32:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 32);
                    zzcts = this.zzpjz == null ? 0 : this.zzpjz.length;
                    obj = new long[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzpjz, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = com_google_android_gms_internal_zzfhb.zzctu();
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = com_google_android_gms_internal_zzfhb.zzctu();
                    this.zzpjz = obj;
                    continue;
                case 34:
                    zzki = com_google_android_gms_internal_zzfhb.zzki(com_google_android_gms_internal_zzfhb.zzcuh());
                    zzb = com_google_android_gms_internal_zzfhb.getPosition();
                    zzcts = 0;
                    while (com_google_android_gms_internal_zzfhb.zzcuj() > 0) {
                        com_google_android_gms_internal_zzfhb.zzctu();
                        zzcts++;
                    }
                    com_google_android_gms_internal_zzfhb.zzlv(zzb);
                    zzb = this.zzpjz == null ? 0 : this.zzpjz.length;
                    obj2 = new long[(zzcts + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzpjz, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfhb.zzctu();
                        zzb++;
                    }
                    this.zzpjz = obj2;
                    com_google_android_gms_internal_zzfhb.zzkj(zzki);
                    continue;
                case 40:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 40);
                    zzcts = this.zzpka == null ? 0 : this.zzpka.length;
                    obj = new long[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzpka, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = com_google_android_gms_internal_zzfhb.zzctu();
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = com_google_android_gms_internal_zzfhb.zzctu();
                    this.zzpka = obj;
                    continue;
                case 42:
                    zzki = com_google_android_gms_internal_zzfhb.zzki(com_google_android_gms_internal_zzfhb.zzcuh());
                    zzb = com_google_android_gms_internal_zzfhb.getPosition();
                    zzcts = 0;
                    while (com_google_android_gms_internal_zzfhb.zzcuj() > 0) {
                        com_google_android_gms_internal_zzfhb.zzctu();
                        zzcts++;
                    }
                    com_google_android_gms_internal_zzfhb.zzlv(zzb);
                    zzb = this.zzpka == null ? 0 : this.zzpka.length;
                    obj2 = new long[(zzcts + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzpka, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfhb.zzctu();
                        zzb++;
                    }
                    this.zzpka = obj2;
                    com_google_android_gms_internal_zzfhb.zzkj(zzki);
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
        int i = 0;
        if (this.zzpjw != null && this.zzpjw.length > 0) {
            for (String str : this.zzpjw) {
                if (str != null) {
                    com_google_android_gms_internal_zzfhc.zzn(1, str);
                }
            }
        }
        if (this.zzpjx != null && this.zzpjx.length > 0) {
            for (String str2 : this.zzpjx) {
                if (str2 != null) {
                    com_google_android_gms_internal_zzfhc.zzn(2, str2);
                }
            }
        }
        if (this.zzpjy != null && this.zzpjy.length > 0) {
            for (int zzaa : this.zzpjy) {
                com_google_android_gms_internal_zzfhc.zzaa(3, zzaa);
            }
        }
        if (this.zzpjz != null && this.zzpjz.length > 0) {
            for (long zzf : this.zzpjz) {
                com_google_android_gms_internal_zzfhc.zzf(4, zzf);
            }
        }
        if (this.zzpka != null && this.zzpka.length > 0) {
            while (i < this.zzpka.length) {
                com_google_android_gms_internal_zzfhc.zzf(5, this.zzpka[i]);
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    public final /* synthetic */ zzfhe zzcxe() throws CloneNotSupportedException {
        return (zzfif) clone();
    }

    public final /* synthetic */ zzfhk zzcxf() throws CloneNotSupportedException {
        return (zzfif) clone();
    }

    protected final int zzo() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        int zzo = super.zzo();
        if (this.zzpjw == null || this.zzpjw.length <= 0) {
            i = zzo;
        } else {
            i2 = 0;
            i3 = 0;
            for (String str : this.zzpjw) {
                if (str != null) {
                    i3++;
                    i2 += zzfhc.zztd(str);
                }
            }
            i = (zzo + i2) + (i3 * 1);
        }
        if (this.zzpjx != null && this.zzpjx.length > 0) {
            i3 = 0;
            zzo = 0;
            for (String str2 : this.zzpjx) {
                if (str2 != null) {
                    zzo++;
                    i3 += zzfhc.zztd(str2);
                }
            }
            i = (i + i3) + (zzo * 1);
        }
        if (this.zzpjy != null && this.zzpjy.length > 0) {
            i3 = 0;
            for (int zzo2 : this.zzpjy) {
                i3 += zzfhc.zzkx(zzo2);
            }
            i = (i + i3) + (this.zzpjy.length * 1);
        }
        if (this.zzpjz != null && this.zzpjz.length > 0) {
            i3 = 0;
            for (long zzdh : this.zzpjz) {
                i3 += zzfhc.zzdh(zzdh);
            }
            i = (i + i3) + (this.zzpjz.length * 1);
        }
        if (this.zzpka == null || this.zzpka.length <= 0) {
            return i;
        }
        i2 = 0;
        while (i4 < this.zzpka.length) {
            i2 += zzfhc.zzdh(this.zzpka[i4]);
            i4++;
        }
        return (i + i2) + (this.zzpka.length * 1);
    }
}

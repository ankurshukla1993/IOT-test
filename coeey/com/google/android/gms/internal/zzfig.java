package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfig extends zzfhe<zzfig> implements Cloneable {
    private String version;
    private int zzivi;
    private String zzpkb;

    public zzfig() {
        this.zzivi = 0;
        this.zzpkb = "";
        this.version = "";
        this.zzpgy = null;
        this.zzpai = -1;
    }

    private zzfig zzcxw() {
        try {
            return (zzfig) super.zzcxe();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcxw();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfig)) {
            return false;
        }
        zzfig com_google_android_gms_internal_zzfig = (zzfig) obj;
        if (this.zzivi != com_google_android_gms_internal_zzfig.zzivi) {
            return false;
        }
        if (this.zzpkb == null) {
            if (com_google_android_gms_internal_zzfig.zzpkb != null) {
                return false;
            }
        } else if (!this.zzpkb.equals(com_google_android_gms_internal_zzfig.zzpkb)) {
            return false;
        }
        if (this.version == null) {
            if (com_google_android_gms_internal_zzfig.version != null) {
                return false;
            }
        } else if (!this.version.equals(com_google_android_gms_internal_zzfig.version)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzfig.zzpgy == null || com_google_android_gms_internal_zzfig.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzfig.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.version == null ? 0 : this.version.hashCode()) + (((this.zzpkb == null ? 0 : this.zzpkb.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.zzivi) * 31)) * 31)) * 31;
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
                case 8:
                    this.zzivi = com_google_android_gms_internal_zzfhb.zzctv();
                    continue;
                case 18:
                    this.zzpkb = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 26:
                    this.version = com_google_android_gms_internal_zzfhb.readString();
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
        if (this.zzivi != 0) {
            com_google_android_gms_internal_zzfhc.zzaa(1, this.zzivi);
        }
        if (!(this.zzpkb == null || this.zzpkb.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.zzpkb);
        }
        if (!(this.version == null || this.version.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(3, this.version);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    public final /* synthetic */ zzfhe zzcxe() throws CloneNotSupportedException {
        return (zzfig) clone();
    }

    public final /* synthetic */ zzfhk zzcxf() throws CloneNotSupportedException {
        return (zzfig) clone();
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.zzivi != 0) {
            zzo += zzfhc.zzad(1, this.zzivi);
        }
        if (!(this.zzpkb == null || this.zzpkb.equals(""))) {
            zzo += zzfhc.zzo(2, this.zzpkb);
        }
        return (this.version == null || this.version.equals("")) ? zzo : zzo + zzfhc.zzo(3, this.version);
    }
}

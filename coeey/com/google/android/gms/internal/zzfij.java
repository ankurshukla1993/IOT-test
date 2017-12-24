package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfij extends zzfhe<zzfij> implements Cloneable {
    private static volatile zzfij[] zzpkz;
    private String key;
    private String value;

    public zzfij() {
        this.key = "";
        this.value = "";
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzfij[] zzcxz() {
        if (zzpkz == null) {
            synchronized (zzfhi.zzphg) {
                if (zzpkz == null) {
                    zzpkz = new zzfij[0];
                }
            }
        }
        return zzpkz;
    }

    private zzfij zzcya() {
        try {
            return (zzfij) super.zzcxe();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcya();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfij)) {
            return false;
        }
        zzfij com_google_android_gms_internal_zzfij = (zzfij) obj;
        if (this.key == null) {
            if (com_google_android_gms_internal_zzfij.key != null) {
                return false;
            }
        } else if (!this.key.equals(com_google_android_gms_internal_zzfij.key)) {
            return false;
        }
        if (this.value == null) {
            if (com_google_android_gms_internal_zzfij.value != null) {
                return false;
            }
        } else if (!this.value.equals(com_google_android_gms_internal_zzfij.value)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzfij.zzpgy == null || com_google_android_gms_internal_zzfij.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzfij.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.key == null ? 0 : this.key.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
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
                    this.key = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 18:
                    this.value = com_google_android_gms_internal_zzfhb.readString();
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
        if (!(this.key == null || this.key.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(1, this.key);
        }
        if (!(this.value == null || this.value.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.value);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    public final /* synthetic */ zzfhe zzcxe() throws CloneNotSupportedException {
        return (zzfij) clone();
    }

    public final /* synthetic */ zzfhk zzcxf() throws CloneNotSupportedException {
        return (zzfij) clone();
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (!(this.key == null || this.key.equals(""))) {
            zzo += zzfhc.zzo(1, this.key);
        }
        return (this.value == null || this.value.equals("")) ? zzo : zzo + zzfhc.zzo(2, this.value);
    }
}

package com.google.android.gms.internal;

public class zzffc {
    private static final zzfea zzpaj = zzfea.zzcuz();
    private zzfdh zzpcw;
    private volatile zzffi zzpcx;
    private volatile zzfdh zzpcy;

    private zzfdh toByteString() {
        if (this.zzpcy != null) {
            return this.zzpcy;
        }
        synchronized (this) {
            if (this.zzpcy != null) {
                zzfdh com_google_android_gms_internal_zzfdh = this.zzpcy;
                return com_google_android_gms_internal_zzfdh;
            }
            if (this.zzpcx == null) {
                this.zzpcy = zzfdh.zzpal;
            } else {
                this.zzpcy = this.zzpcx.toByteString();
            }
            com_google_android_gms_internal_zzfdh = this.zzpcy;
            return com_google_android_gms_internal_zzfdh;
        }
    }

    private zzffi zzi(zzffi com_google_android_gms_internal_zzffi) {
        if (this.zzpcx == null) {
            synchronized (this) {
                if (this.zzpcx != null) {
                } else {
                    try {
                        this.zzpcx = com_google_android_gms_internal_zzffi;
                        this.zzpcy = zzfdh.zzpal;
                    } catch (zzfew e) {
                        this.zzpcx = com_google_android_gms_internal_zzffi;
                        this.zzpcy = zzfdh.zzpal;
                    }
                }
            }
        }
        return this.zzpcx;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzffc)) {
            return false;
        }
        zzffc com_google_android_gms_internal_zzffc = (zzffc) obj;
        zzffi com_google_android_gms_internal_zzffi = this.zzpcx;
        zzffi com_google_android_gms_internal_zzffi2 = com_google_android_gms_internal_zzffc.zzpcx;
        return (com_google_android_gms_internal_zzffi == null && com_google_android_gms_internal_zzffi2 == null) ? toByteString().equals(com_google_android_gms_internal_zzffc.toByteString()) : (com_google_android_gms_internal_zzffi == null || com_google_android_gms_internal_zzffi2 == null) ? com_google_android_gms_internal_zzffi != null ? com_google_android_gms_internal_zzffi.equals(com_google_android_gms_internal_zzffc.zzi(com_google_android_gms_internal_zzffi.zzcvh())) : zzi(com_google_android_gms_internal_zzffi2.zzcvh()).equals(com_google_android_gms_internal_zzffi2) : com_google_android_gms_internal_zzffi.equals(com_google_android_gms_internal_zzffi2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zzhl() {
        return this.zzpcy != null ? this.zzpcy.size() : this.zzpcx != null ? this.zzpcx.zzhl() : 0;
    }

    public final zzffi zzj(zzffi com_google_android_gms_internal_zzffi) {
        zzffi com_google_android_gms_internal_zzffi2 = this.zzpcx;
        this.zzpcw = null;
        this.zzpcy = null;
        this.zzpcx = com_google_android_gms_internal_zzffi;
        return com_google_android_gms_internal_zzffi2;
    }
}

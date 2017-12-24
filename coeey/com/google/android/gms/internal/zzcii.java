package com.google.android.gms.internal;

abstract class zzcii extends zzcih {
    private boolean zzdqd;

    zzcii(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
        this.zzitk.zzb(this);
    }

    public final void initialize() {
        if (this.zzdqd) {
            throw new IllegalStateException("Can't initialize twice");
        } else if (!zzaxn()) {
            this.zzitk.zzazy();
            this.zzdqd = true;
        }
    }

    final boolean isInitialized() {
        return this.zzdqd;
    }

    protected abstract boolean zzaxn();

    protected void zzaym() {
    }

    public final void zzazk() {
        if (this.zzdqd) {
            throw new IllegalStateException("Can't initialize twice");
        }
        zzaym();
        this.zzitk.zzazy();
        this.zzdqd = true;
    }

    protected final void zzwu() {
        if (!isInitialized()) {
            throw new IllegalStateException("Not initialized");
        }
    }
}

package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;

final class zzbeg extends ConstantState {
    int mChangingConfigurations;
    int zzfvi;

    zzbeg(zzbeg com_google_android_gms_internal_zzbeg) {
        if (com_google_android_gms_internal_zzbeg != null) {
            this.mChangingConfigurations = com_google_android_gms_internal_zzbeg.mChangingConfigurations;
            this.zzfvi = com_google_android_gms_internal_zzbeg.zzfvi;
        }
    }

    public final int getChangingConfigurations() {
        return this.mChangingConfigurations;
    }

    public final Drawable newDrawable() {
        return new zzbec(this);
    }
}

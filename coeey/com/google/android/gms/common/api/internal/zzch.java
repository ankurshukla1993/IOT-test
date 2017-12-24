package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.internal.zzbq;

public final class zzch {
    private final Object zzfri;

    public zzch(Activity activity) {
        zzbq.checkNotNull(activity, "Activity must not be null");
        this.zzfri = activity;
    }

    public final boolean zzaix() {
        return this.zzfri instanceof FragmentActivity;
    }

    public final boolean zzaiy() {
        return this.zzfri instanceof Activity;
    }

    public final Activity zzaiz() {
        return (Activity) this.zzfri;
    }

    public final FragmentActivity zzaja() {
        return (FragmentActivity) this.zzfri;
    }
}

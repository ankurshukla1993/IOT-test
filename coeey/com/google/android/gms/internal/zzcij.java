package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzbq;

public final class zzcij {
    final Context mContext;

    public zzcij(Context context) {
        zzbq.checkNotNull(context);
        Context applicationContext = context.getApplicationContext();
        zzbq.checkNotNull(applicationContext);
        this.mContext = applicationContext;
    }
}

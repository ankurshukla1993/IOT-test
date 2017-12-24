package com.google.android.gms.internal;

import android.database.ContentObserver;
import android.os.Handler;

final class zzdle extends ContentObserver {
    zzdle(Handler handler) {
        super(null);
    }

    public final void onChange(boolean z) {
        zzdld.zzlju.set(true);
    }
}

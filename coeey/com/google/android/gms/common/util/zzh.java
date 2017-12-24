package com.google.android.gms.common.util;

import android.os.SystemClock;

public final class zzh implements zzd {
    private static zzh zzgbl = new zzh();

    private zzh() {
    }

    public static zzd zzalu() {
        return zzgbl;
    }

    public final long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public final long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }

    public final long nanoTime() {
        return System.nanoTime();
    }
}

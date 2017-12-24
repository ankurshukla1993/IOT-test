package com.google.android.gms.internal;

import android.os.Process;

final class zzbfz implements Runnable {
    private final int mPriority;
    private final Runnable zzv;

    public zzbfz(Runnable runnable, int i) {
        this.zzv = runnable;
        this.mPriority = i;
    }

    public final void run() {
        Process.setThreadPriority(this.mPriority);
        this.zzv.run();
    }
}

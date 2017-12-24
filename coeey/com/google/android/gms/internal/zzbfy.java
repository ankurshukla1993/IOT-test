package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzbfy implements ThreadFactory {
    private final int mPriority;
    private final String zzgch;
    private final AtomicInteger zzgci;
    private final ThreadFactory zzgcj;

    public zzbfy(String str) {
        this(str, 0);
    }

    private zzbfy(String str, int i) {
        this.zzgci = new AtomicInteger();
        this.zzgcj = Executors.defaultThreadFactory();
        this.zzgch = (String) zzbq.checkNotNull(str, "Name must not be null");
        this.mPriority = 0;
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.zzgcj.newThread(new zzbfz(runnable, 0));
        String str = this.zzgch;
        newThread.setName(new StringBuilder(String.valueOf(str).length() + 13).append(str).append("[").append(this.zzgci.getAndIncrement()).append("]").toString());
        return newThread;
    }
}

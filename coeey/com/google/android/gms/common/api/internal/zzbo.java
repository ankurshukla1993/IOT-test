package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.zzbfy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class zzbo {
    private static final ExecutorService zzfqd = Executors.newFixedThreadPool(2, new zzbfy("GAC_Executor"));

    public static ExecutorService zzaid() {
        return zzfqd;
    }
}

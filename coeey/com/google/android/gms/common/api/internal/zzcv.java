package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.zzbfy;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class zzcv {
    private static final ExecutorService zzfqd = new ThreadPoolExecutor(0, 4, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), new zzbfy("GAC_Transform"));

    public static ExecutorService zzaid() {
        return zzfqd;
    }
}

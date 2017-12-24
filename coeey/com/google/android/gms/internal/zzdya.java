package com.google.android.gms.internal;

import android.content.Context;
import java.util.concurrent.TimeUnit;

public final class zzdya {
    public static final zzbzn<Boolean> zzmef = zzbzn.zzb(0, "crash:enabled", Boolean.valueOf(true));
    private static zzbzn<String> zzmeg = new zzbzs(0, "crash:gateway_url", "https://mobilecrashreporting.googleapis.com/v1/crashes:batchCreate?key=");
    private static zzbzn<Integer> zzmeh = zzbzn.zzb(0, "crash:log_buffer_capacity", 100);
    private static zzbzn<Integer> zzmei = zzbzn.zzb(0, "crash:log_buffer_max_total_size", 32768);
    private static zzbzn<Integer> zzmej = zzbzn.zzb(0, "crash:crash_backlog_capacity", 5);
    private static zzbzn<Long> zzmek = zzbzn.zzb(0, "crash:crash_backlog_max_age", 604800000);
    private static zzbzn<Long> zzmel = zzbzn.zzb(0, "crash:starting_backoff", TimeUnit.SECONDS.toMillis(1));
    private static zzbzn<Long> zzmem = zzbzn.zzb(0, "crash:backoff_limit", TimeUnit.MINUTES.toMillis(60));
    private static zzbzn<Integer> zzmen = zzbzn.zzb(0, "crash:retry_num_attempts", 12);
    private static zzbzn<Integer> zzmeo = zzbzn.zzb(0, "crash:batch_size", 5);
    private static zzbzn<Long> zzmep = zzbzn.zzb(0, "crash:batch_throttle", TimeUnit.MINUTES.toMillis(5));
    private static zzbzn<Integer> zzmeq = zzbzn.zzb(0, "crash:frame_depth", 60);
    private static zzbzn<Integer> zzmer = zzbzn.zzb(0, "crash:receiver_delay", 100);
    private static zzbzn<Integer> zzmes = zzbzn.zzb(0, "crash:thread_idle_timeout", 10);

    public static final void initialize(Context context) {
        zzbzy.zzaqp();
        zzbzy.zzaqq().initialize(context);
    }
}

package com.google.android.gms.internal;

import com.facebook.common.util.UriUtil;

public final class zzcfz {
    private static zzcga<Boolean> zzixa = zzcga.zzb("measurement.service_enabled", true, true);
    private static zzcga<Boolean> zzixb = zzcga.zzb("measurement.service_client_enabled", true, true);
    private static zzcga<Boolean> zzixc = zzcga.zzb("measurement.log_third_party_store_events_enabled", false, false);
    private static zzcga<Boolean> zzixd = zzcga.zzb("measurement.log_installs_enabled", false, false);
    private static zzcga<Boolean> zzixe = zzcga.zzb("measurement.log_upgrades_enabled", false, false);
    private static zzcga<Boolean> zzixf = zzcga.zzb("measurement.log_androidId_enabled", false, false);
    public static zzcga<Boolean> zzixg = zzcga.zzb("measurement.upload_dsid_enabled", false, false);
    public static zzcga<Boolean> zzixh = zzcga.zzb("measurement.event_sampling_enabled", false, false);
    public static zzcga<String> zzixi = zzcga.zzi("measurement.log_tag", "FA", "FA-SVC");
    public static zzcga<Long> zzixj = zzcga.zzb("measurement.ad_id_cache_time", 10000, 10000);
    public static zzcga<Long> zzixk = zzcga.zzb("measurement.monitoring.sample_period_millis", 86400000, 86400000);
    public static zzcga<Long> zzixl = zzcga.zzb("measurement.config.cache_time", 86400000, 3600000);
    public static zzcga<String> zzixm;
    public static zzcga<String> zzixn;
    public static zzcga<Integer> zzixo = zzcga.zzm("measurement.upload.max_bundles", 100, 100);
    public static zzcga<Integer> zzixp = zzcga.zzm("measurement.upload.max_batch_size", 65536, 65536);
    public static zzcga<Integer> zzixq = zzcga.zzm("measurement.upload.max_bundle_size", 65536, 65536);
    public static zzcga<Integer> zzixr = zzcga.zzm("measurement.upload.max_events_per_bundle", 1000, 1000);
    public static zzcga<Integer> zzixs = zzcga.zzm("measurement.upload.max_events_per_day", 100000, 100000);
    public static zzcga<Integer> zzixt = zzcga.zzm("measurement.upload.max_error_events_per_day", 1000, 1000);
    public static zzcga<Integer> zzixu = zzcga.zzm("measurement.upload.max_public_events_per_day", 50000, 50000);
    public static zzcga<Integer> zzixv = zzcga.zzm("measurement.upload.max_conversions_per_day", 500, 500);
    public static zzcga<Integer> zzixw = zzcga.zzm("measurement.upload.max_realtime_events_per_day", 10, 10);
    public static zzcga<Integer> zzixx = zzcga.zzm("measurement.store.max_stored_events_per_app", 100000, 100000);
    public static zzcga<String> zzixy;
    public static zzcga<Long> zzixz = zzcga.zzb("measurement.upload.backoff_period", 43200000, 43200000);
    public static zzcga<Long> zziya = zzcga.zzb("measurement.upload.window_interval", 3600000, 3600000);
    public static zzcga<Long> zziyb = zzcga.zzb("measurement.upload.interval", 3600000, 3600000);
    public static zzcga<Long> zziyc = zzcga.zzb("measurement.upload.realtime_upload_interval", 10000, 10000);
    public static zzcga<Long> zziyd = zzcga.zzb("measurement.upload.debug_upload_interval", 1000, 1000);
    public static zzcga<Long> zziye = zzcga.zzb("measurement.upload.minimum_delay", 500, 500);
    public static zzcga<Long> zziyf = zzcga.zzb("measurement.alarm_manager.minimum_interval", 60000, 60000);
    public static zzcga<Long> zziyg = zzcga.zzb("measurement.upload.stale_data_deletion_interval", 86400000, 86400000);
    public static zzcga<Long> zziyh = zzcga.zzb("measurement.upload.refresh_blacklisted_config_interval", 604800000, 604800000);
    public static zzcga<Long> zziyi = zzcga.zzb("measurement.upload.initial_upload_delay_time", 15000, 15000);
    public static zzcga<Long> zziyj = zzcga.zzb("measurement.upload.retry_time", 1800000, 1800000);
    public static zzcga<Integer> zziyk = zzcga.zzm("measurement.upload.retry_count", 6, 6);
    public static zzcga<Long> zziyl = zzcga.zzb("measurement.upload.max_queue_time", 2419200000L, 2419200000L);
    public static zzcga<Integer> zziym = zzcga.zzm("measurement.lifetimevalue.max_currency_tracked", 4, 4);
    public static zzcga<Integer> zziyn = zzcga.zzm("measurement.audience.filter_result_max_count", 200, 200);
    public static zzcga<Long> zziyo = zzcga.zzb("measurement.service_client.idle_disconnect_millis", 5000, 5000);

    static {
        String str = UriUtil.HTTPS_SCHEME;
        zzixm = zzcga.zzi("measurement.config.url_scheme", str, str);
        str = "app-measurement.com";
        zzixn = zzcga.zzi("measurement.config.url_authority", str, str);
        str = "https://app-measurement.com/a";
        zzixy = zzcga.zzi("measurement.upload.url", str, str);
    }
}

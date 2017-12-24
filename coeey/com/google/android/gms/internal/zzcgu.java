package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.math.BigInteger;
import java.util.Locale;

final class zzcgu extends zzcii {
    static final Pair<String, Long> zzizu = new Pair("", Long.valueOf(0));
    private SharedPreferences zzdvp;
    public final zzcgy zzizv = new zzcgy(this, "health_monitor", Math.max(0, ((Long) zzcfz.zzixk.get()).longValue()));
    public final zzcgx zzizw = new zzcgx(this, "last_upload", 0);
    public final zzcgx zzizx = new zzcgx(this, "last_upload_attempt", 0);
    public final zzcgx zzizy = new zzcgx(this, "backoff", 0);
    public final zzcgx zzizz = new zzcgx(this, "last_delete_stale", 0);
    public final zzcgx zzjaa = new zzcgx(this, "midnight_offset", 0);
    public final zzcgx zzjab = new zzcgx(this, "first_open_time", 0);
    public final zzcgz zzjac = new zzcgz(this, "app_instance_id", null);
    private String zzjad;
    private boolean zzjae;
    private long zzjaf;
    private String zzjag;
    private long zzjah;
    private final Object zzjai = new Object();
    public final zzcgx zzjaj = new zzcgx(this, "time_before_start", 10000);
    public final zzcgx zzjak = new zzcgx(this, "session_timeout", 1800000);
    public final zzcgw zzjal = new zzcgw(this, "start_new_session", true);
    public final zzcgx zzjam = new zzcgx(this, "last_pause_time", 0);
    public final zzcgx zzjan = new zzcgx(this, "time_active", 0);
    public boolean zzjao;

    zzcgu(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    @WorkerThread
    private final SharedPreferences zzayz() {
        zzut();
        zzwu();
        return this.zzdvp;
    }

    @WorkerThread
    final void setMeasurementEnabled(boolean z) {
        zzut();
        zzawm().zzayx().zzj("Setting measurementEnabled", Boolean.valueOf(z));
        Editor edit = zzayz().edit();
        edit.putBoolean("measurement_enabled", z);
        edit.apply();
    }

    protected final boolean zzaxn() {
        return true;
    }

    protected final void zzaym() {
        this.zzdvp = getContext().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzjao = this.zzdvp.getBoolean("has_been_opened", false);
        if (!this.zzjao) {
            Editor edit = this.zzdvp.edit();
            edit.putBoolean("has_been_opened", true);
            edit.apply();
        }
    }

    @WorkerThread
    final String zzaza() {
        zzut();
        return zzayz().getString("gmp_app_id", null);
    }

    final String zzazb() {
        String str;
        synchronized (this.zzjai) {
            if (Math.abs(zzwh().elapsedRealtime() - this.zzjah) < 1000) {
                str = this.zzjag;
            } else {
                str = null;
            }
        }
        return str;
    }

    @WorkerThread
    final Boolean zzazc() {
        zzut();
        return !zzayz().contains("use_service") ? null : Boolean.valueOf(zzayz().getBoolean("use_service", false));
    }

    @WorkerThread
    final void zzazd() {
        boolean z = true;
        zzut();
        zzawm().zzayx().log("Clearing collection preferences.");
        boolean contains = zzayz().contains("measurement_enabled");
        if (contains) {
            z = zzbm(true);
        }
        Editor edit = zzayz().edit();
        edit.clear();
        edit.apply();
        if (contains) {
            setMeasurementEnabled(z);
        }
    }

    @WorkerThread
    protected final String zzaze() {
        zzut();
        String string = zzayz().getString("previous_os_version", null);
        zzawc().zzwu();
        String str = VERSION.RELEASE;
        if (!(TextUtils.isEmpty(str) || str.equals(string))) {
            Editor edit = zzayz().edit();
            edit.putString("previous_os_version", str);
            edit.apply();
        }
        return string;
    }

    @WorkerThread
    final void zzbl(boolean z) {
        zzut();
        zzawm().zzayx().zzj("Setting useService", Boolean.valueOf(z));
        Editor edit = zzayz().edit();
        edit.putBoolean("use_service", z);
        edit.apply();
    }

    @WorkerThread
    final boolean zzbm(boolean z) {
        zzut();
        return zzayz().getBoolean("measurement_enabled", z);
    }

    @WorkerThread
    @NonNull
    final Pair<String, Boolean> zzjg(String str) {
        zzut();
        long elapsedRealtime = zzwh().elapsedRealtime();
        if (this.zzjad != null && elapsedRealtime < this.zzjaf) {
            return new Pair(this.zzjad, Boolean.valueOf(this.zzjae));
        }
        this.zzjaf = elapsedRealtime + zzawo().zza(str, zzcfz.zzixj);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(getContext());
            if (advertisingIdInfo != null) {
                this.zzjad = advertisingIdInfo.getId();
                this.zzjae = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzjad == null) {
                this.zzjad = "";
            }
        } catch (Throwable th) {
            zzawm().zzayw().zzj("Unable to get advertising id", th);
            this.zzjad = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair(this.zzjad, Boolean.valueOf(this.zzjae));
    }

    @WorkerThread
    final String zzjh(String str) {
        zzut();
        String str2 = (String) zzjg(str).first;
        if (zzckn.zzed(CommonUtils.MD5_INSTANCE) == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzckn.zzed(CommonUtils.MD5_INSTANCE).digest(str2.getBytes()))});
    }

    @WorkerThread
    final void zzji(String str) {
        zzut();
        Editor edit = zzayz().edit();
        edit.putString("gmp_app_id", str);
        edit.apply();
    }

    final void zzjj(String str) {
        synchronized (this.zzjai) {
            this.zzjag = str;
            this.zzjah = zzwh().elapsedRealtime();
        }
    }
}

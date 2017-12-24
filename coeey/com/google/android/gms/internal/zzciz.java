package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement$zza;
import com.google.android.gms.measurement.AppMeasurement$zzb;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public final class zzciz extends zzcii {
    protected zzcjc zzjes;
    private volatile AppMeasurement$zzb zzjet;
    private AppMeasurement$zzb zzjeu;
    private long zzjev;
    private final Map<Activity, zzcjc> zzjew = new ArrayMap();
    private final CopyOnWriteArrayList<AppMeasurement$zza> zzjex = new CopyOnWriteArrayList();
    private boolean zzjey;
    private AppMeasurement$zzb zzjez;
    private String zzjfa;

    public zzciz(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    @MainThread
    private final void zza(Activity activity, zzcjc com_google_android_gms_internal_zzcjc, boolean z) {
        boolean hasNext;
        boolean z2 = true;
        AppMeasurement$zzb appMeasurement$zzb = this.zzjet != null ? this.zzjet : (this.zzjeu == null || Math.abs(zzwh().elapsedRealtime() - this.zzjev) >= 1000) ? null : this.zzjeu;
        appMeasurement$zzb = appMeasurement$zzb != null ? new AppMeasurement$zzb(appMeasurement$zzb) : null;
        this.zzjey = true;
        try {
            Iterator it = this.zzjex.iterator();
            while (true) {
                hasNext = it.hasNext();
                if (!hasNext) {
                    break;
                }
                try {
                    z2 &= ((AppMeasurement$zza) it.next()).zza(appMeasurement$zzb, com_google_android_gms_internal_zzcjc);
                } catch (Exception e) {
                    zzawm().zzayr().zzj("onScreenChangeCallback threw exception", e);
                }
            }
            hasNext = z2;
        } catch (Exception e2) {
            Exception exception = e2;
            hasNext = z2;
            z2 = zzawm().zzayr();
            z2.zzj("onScreenChangeCallback loop threw exception", exception);
        } finally {
            this.zzjey = false;
        }
        appMeasurement$zzb = this.zzjet == null ? this.zzjeu : this.zzjet;
        if (hasNext) {
            if (com_google_android_gms_internal_zzcjc.zzitq == null) {
                com_google_android_gms_internal_zzcjc.zzitq = zzjs(activity.getClass().getCanonicalName());
            }
            AppMeasurement$zzb com_google_android_gms_internal_zzcjc2 = new zzcjc(com_google_android_gms_internal_zzcjc);
            this.zzjeu = this.zzjet;
            this.zzjev = zzwh().elapsedRealtime();
            this.zzjet = com_google_android_gms_internal_zzcjc2;
            zzawl().zzg(new zzcja(this, z, appMeasurement$zzb, com_google_android_gms_internal_zzcjc2));
        }
    }

    @WorkerThread
    private final void zza(@NonNull zzcjc com_google_android_gms_internal_zzcjc) {
        zzavy().zzai(zzwh().elapsedRealtime());
        if (zzawk().zzbr(com_google_android_gms_internal_zzcjc.zzjfg)) {
            com_google_android_gms_internal_zzcjc.zzjfg = false;
        }
    }

    public static void zza(AppMeasurement$zzb appMeasurement$zzb, Bundle bundle) {
        if (bundle != null && appMeasurement$zzb != null && !bundle.containsKey("_sc")) {
            if (appMeasurement$zzb.zzitp != null) {
                bundle.putString("_sn", appMeasurement$zzb.zzitp);
            }
            bundle.putString("_sc", appMeasurement$zzb.zzitq);
            bundle.putLong("_si", appMeasurement$zzb.zzitr);
        }
    }

    private static String zzjs(String str) {
        String[] split = str.split("\\.");
        if (split.length == 0) {
            return str.substring(0, 36);
        }
        String str2 = split[split.length - 1];
        return str2.length() > 36 ? str2.substring(0, 36) : str2;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @MainThread
    public final void onActivityDestroyed(Activity activity) {
        this.zzjew.remove(activity);
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        zzcjc zzq = zzq(activity);
        this.zzjeu = this.zzjet;
        this.zzjev = zzwh().elapsedRealtime();
        this.zzjet = null;
        zzawl().zzg(new zzcjb(this, zzq));
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        zza(activity, zzq(activity), false);
        zzcih zzavy = zzavy();
        zzavy.zzawl().zzg(new zzcfd(zzavy, zzavy.zzwh().elapsedRealtime()));
    }

    @MainThread
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (bundle != null) {
            zzcjc com_google_android_gms_internal_zzcjc = (zzcjc) this.zzjew.get(activity);
            if (com_google_android_gms_internal_zzcjc != null) {
                Bundle bundle2 = new Bundle();
                bundle2.putLong(ShareConstants.WEB_DIALOG_PARAM_ID, com_google_android_gms_internal_zzcjc.zzitr);
                bundle2.putString("name", com_google_android_gms_internal_zzcjc.zzitp);
                bundle2.putString("referrer_name", com_google_android_gms_internal_zzcjc.zzitq);
                bundle.putBundle("com.google.firebase.analytics.screen_service", bundle2);
            }
        }
    }

    @MainThread
    public final void registerOnScreenChangeCallback(@NonNull AppMeasurement$zza appMeasurement$zza) {
        if (appMeasurement$zza == null) {
            zzawm().zzayt().log("Attempting to register null OnScreenChangeCallback");
            return;
        }
        this.zzjex.remove(appMeasurement$zza);
        this.zzjex.add(appMeasurement$zza);
    }

    @MainThread
    public final void setCurrentScreen(@NonNull Activity activity, @Nullable @Size(max = 36, min = 1) String str, @Nullable @Size(max = 36, min = 1) String str2) {
        if (activity == null) {
            zzawm().zzayt().log("setCurrentScreen must be called with a non-null activity");
            return;
        }
        zzawl();
        if (!zzche.zzas()) {
            zzawm().zzayt().log("setCurrentScreen must be called from the main thread");
        } else if (this.zzjey) {
            zzawm().zzayt().log("Cannot call setCurrentScreen from onScreenChangeCallback");
        } else if (this.zzjet == null) {
            zzawm().zzayt().log("setCurrentScreen cannot be called while no activity active");
        } else if (this.zzjew.get(activity) == null) {
            zzawm().zzayt().log("setCurrentScreen must be called with an activity in the activity lifecycle");
        } else {
            if (str2 == null) {
                str2 = zzjs(activity.getClass().getCanonicalName());
            }
            boolean equals = this.zzjet.zzitq.equals(str2);
            boolean zzas = zzckn.zzas(this.zzjet.zzitp, str);
            if (equals && zzas) {
                zzawm().zzayu().log("setCurrentScreen cannot be called with the same class and name");
            } else if (str != null && (str.length() <= 0 || str.length() > 100)) {
                zzawm().zzayt().zzj("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            } else if (str2 == null || (str2.length() > 0 && str2.length() <= 100)) {
                Object obj;
                zzcgl zzayx = zzawm().zzayx();
                String str3 = "Setting current screen to name, class";
                if (str == null) {
                    obj = "null";
                } else {
                    String str4 = str;
                }
                zzayx.zze(str3, obj, str2);
                zzcjc com_google_android_gms_internal_zzcjc = new zzcjc(str, str2, zzawi().zzbam());
                this.zzjew.put(activity, com_google_android_gms_internal_zzcjc);
                zza(activity, com_google_android_gms_internal_zzcjc, true);
            } else {
                zzawm().zzayt().zzj("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            }
        }
    }

    @MainThread
    public final void unregisterOnScreenChangeCallback(@NonNull AppMeasurement$zza appMeasurement$zza) {
        this.zzjex.remove(appMeasurement$zza);
    }

    @WorkerThread
    public final void zza(String str, AppMeasurement$zzb appMeasurement$zzb) {
        zzut();
        synchronized (this) {
            if (this.zzjfa == null || this.zzjfa.equals(str) || appMeasurement$zzb != null) {
                this.zzjfa = str;
                this.zzjez = appMeasurement$zzb;
            }
        }
    }

    public final /* bridge */ /* synthetic */ void zzavw() {
        super.zzavw();
    }

    public final /* bridge */ /* synthetic */ void zzavx() {
        super.zzavx();
    }

    public final /* bridge */ /* synthetic */ zzcfa zzavy() {
        return super.zzavy();
    }

    public final /* bridge */ /* synthetic */ zzcfh zzavz() {
        return super.zzavz();
    }

    public final /* bridge */ /* synthetic */ zzcik zzawa() {
        return super.zzawa();
    }

    public final /* bridge */ /* synthetic */ zzcge zzawb() {
        return super.zzawb();
    }

    public final /* bridge */ /* synthetic */ zzcfr zzawc() {
        return super.zzawc();
    }

    public final /* bridge */ /* synthetic */ zzcjd zzawd() {
        return super.zzawd();
    }

    public final /* bridge */ /* synthetic */ zzciz zzawe() {
        return super.zzawe();
    }

    public final /* bridge */ /* synthetic */ zzcgf zzawf() {
        return super.zzawf();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzawg() {
        return super.zzawg();
    }

    public final /* bridge */ /* synthetic */ zzcgh zzawh() {
        return super.zzawh();
    }

    public final /* bridge */ /* synthetic */ zzckn zzawi() {
        return super.zzawi();
    }

    public final /* bridge */ /* synthetic */ zzchd zzawj() {
        return super.zzawj();
    }

    public final /* bridge */ /* synthetic */ zzckc zzawk() {
        return super.zzawk();
    }

    public final /* bridge */ /* synthetic */ zzche zzawl() {
        return super.zzawl();
    }

    public final /* bridge */ /* synthetic */ zzcgj zzawm() {
        return super.zzawm();
    }

    public final /* bridge */ /* synthetic */ zzcgu zzawn() {
        return super.zzawn();
    }

    public final /* bridge */ /* synthetic */ zzcfk zzawo() {
        return super.zzawo();
    }

    protected final boolean zzaxn() {
        return false;
    }

    @WorkerThread
    public final zzcjc zzbac() {
        zzwu();
        zzut();
        return this.zzjes;
    }

    public final AppMeasurement$zzb zzbad() {
        AppMeasurement$zzb appMeasurement$zzb = this.zzjet;
        return appMeasurement$zzb == null ? null : new AppMeasurement$zzb(appMeasurement$zzb);
    }

    @MainThread
    final zzcjc zzq(@NonNull Activity activity) {
        zzbq.checkNotNull(activity);
        zzcjc com_google_android_gms_internal_zzcjc = (zzcjc) this.zzjew.get(activity);
        if (com_google_android_gms_internal_zzcjc != null) {
            return com_google_android_gms_internal_zzcjc;
        }
        com_google_android_gms_internal_zzcjc = new zzcjc(null, zzjs(activity.getClass().getCanonicalName()), zzawi().zzbam());
        this.zzjew.put(activity, com_google_android_gms_internal_zzcjc);
        return com_google_android_gms_internal_zzcjc;
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }
}

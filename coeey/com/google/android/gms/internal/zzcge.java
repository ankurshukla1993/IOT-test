package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzcc;
import com.google.android.gms.common.util.zzd;
import com.google.firebase.iid.FirebaseInstanceId;
import java.math.BigInteger;
import java.util.Locale;

public final class zzcge extends zzcii {
    private String mAppId;
    private String zzcvg;
    private String zzdob;
    private String zzdoc;
    private String zziuh;
    private long zziul;
    private int zziyp;
    private long zziyq;
    private int zziyr;

    zzcge(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    @WorkerThread
    private final String zzawr() {
        zzut();
        try {
            return FirebaseInstanceId.getInstance().getId();
        } catch (IllegalStateException e) {
            zzawm().zzayt().log("Failed to retrieve Firebase Instance Id");
            return null;
        }
    }

    final String getAppId() {
        zzwu();
        return this.mAppId;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    final String getGmpAppId() {
        zzwu();
        return this.zzcvg;
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
        return true;
    }

    protected final void zzaym() {
        int i = 1;
        String str = "unknown";
        String str2 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        int i2 = Integer.MIN_VALUE;
        String str3 = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
        String packageName = getContext().getPackageName();
        PackageManager packageManager = getContext().getPackageManager();
        if (packageManager == null) {
            zzawm().zzayr().zzj("PackageManager is null, app identity information might be inaccurate. appId", zzcgj.zzje(packageName));
        } else {
            try {
                str = packageManager.getInstallerPackageName(packageName);
            } catch (IllegalArgumentException e) {
                zzawm().zzayr().zzj("Error retrieving app installer package name. appId", zzcgj.zzje(packageName));
            }
            if (str == null) {
                str = "manual_install";
            } else if ("com.android.vending".equals(str)) {
                str = "";
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(getContext().getPackageName(), 0);
                if (packageInfo != null) {
                    CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                    if (!TextUtils.isEmpty(applicationLabel)) {
                        str3 = applicationLabel.toString();
                    }
                    str2 = packageInfo.versionName;
                    i2 = packageInfo.versionCode;
                }
            } catch (NameNotFoundException e2) {
                zzawm().zzayr().zze("Error retrieving package info. appId, appName", zzcgj.zzje(packageName), str3);
            }
        }
        this.mAppId = packageName;
        this.zziuh = str;
        this.zzdoc = str2;
        this.zziyp = i2;
        this.zzdob = str3;
        this.zziyq = 0;
        Status zzci = zzcc.zzci(getContext());
        int i3 = (zzci == null || !zzci.isSuccess()) ? 0 : 1;
        if (i3 == 0) {
            if (zzci == null) {
                zzawm().zzayr().log("GoogleService failed to initialize (no status)");
            } else {
                zzawm().zzayr().zze("GoogleService failed to initialize, status", Integer.valueOf(zzci.getStatusCode()), zzci.getStatusMessage());
            }
        }
        if (i3 != 0) {
            Boolean zzis = zzawo().zzis("firebase_analytics_collection_enabled");
            if (zzawo().zzaxo()) {
                zzawm().zzayv().log("Collection disabled with firebase_analytics_collection_deactivated=1");
                i3 = 0;
            } else if (zzis != null && !zzis.booleanValue()) {
                zzawm().zzayv().log("Collection disabled with firebase_analytics_collection_enabled=0");
                i3 = 0;
            } else if (zzis == null && zzcc.zzaiw()) {
                zzawm().zzayv().log("Collection disabled with google_app_measurement_enable=0");
                i3 = 0;
            } else {
                zzawm().zzayx().log("Collection enabled");
                i3 = 1;
            }
        } else {
            i3 = 0;
        }
        this.zzcvg = "";
        this.zziul = 0;
        try {
            String zzaiv = zzcc.zzaiv();
            if (TextUtils.isEmpty(zzaiv)) {
                zzaiv = "";
            }
            this.zzcvg = zzaiv;
            if (i3 != 0) {
                zzawm().zzayx().zze("App package, google app id", this.mAppId, this.zzcvg);
            }
        } catch (IllegalStateException e3) {
            zzawm().zzayr().zze("getGoogleAppId or isMeasurementEnabled failed with exception. appId", zzcgj.zzje(packageName), e3);
        }
        if (VERSION.SDK_INT >= 16) {
            if (!zzbga.zzcw(getContext())) {
                i = 0;
            }
            this.zziyr = i;
            return;
        }
        this.zziyr = 0;
    }

    @WorkerThread
    final String zzayn() {
        zzawi().zzban().nextBytes(new byte[16]);
        return String.format(Locale.US, "%032x", new Object[]{new BigInteger(1, r0)});
    }

    final int zzayo() {
        zzwu();
        return this.zziyp;
    }

    final int zzayp() {
        zzwu();
        return this.zziyr;
    }

    @WorkerThread
    final zzcff zzja(String str) {
        zzut();
        String appId = getAppId();
        String gmpAppId = getGmpAppId();
        zzwu();
        String str2 = this.zzdoc;
        long zzayo = (long) zzayo();
        zzwu();
        String str3 = this.zziuh;
        zzwu();
        zzut();
        if (this.zziyq == 0) {
            this.zziyq = this.zzitk.zzawi().zzaf(getContext(), getContext().getPackageName());
        }
        long j = this.zziyq;
        boolean isEnabled = this.zzitk.isEnabled();
        boolean z = !zzawn().zzjao;
        String zzawr = zzawr();
        zzwu();
        long zzazt = this.zzitk.zzazt();
        int zzayp = zzayp();
        Boolean zzis = zzawo().zzis("google_analytics_adid_collection_enabled");
        boolean z2 = zzis == null || zzis.booleanValue();
        return new zzcff(appId, gmpAppId, str2, zzayo, str3, 11720, j, str, isEnabled, z, zzawr, 0, zzazt, zzayp, Boolean.valueOf(z2).booleanValue());
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }
}

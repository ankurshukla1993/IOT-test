package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.common.util.zzs;
import java.lang.reflect.InvocationTargetException;

public final class zzcfk extends zzcih {
    private Boolean zzdsn;

    zzcfk(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    public static long zzaxp() {
        return ((Long) zzcfz.zziyl.get()).longValue();
    }

    public static long zzaxq() {
        return ((Long) zzcfz.zzixl.get()).longValue();
    }

    public static boolean zzaxs() {
        return ((Boolean) zzcfz.zzixg.get()).booleanValue();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final long zza(String str, zzcga<Long> com_google_android_gms_internal_zzcga_java_lang_Long) {
        if (str == null) {
            return ((Long) com_google_android_gms_internal_zzcga_java_lang_Long.get()).longValue();
        }
        Object zzam = zzawj().zzam(str, com_google_android_gms_internal_zzcga_java_lang_Long.getKey());
        if (TextUtils.isEmpty(zzam)) {
            return ((Long) com_google_android_gms_internal_zzcga_java_lang_Long.get()).longValue();
        }
        try {
            return ((Long) com_google_android_gms_internal_zzcga_java_lang_Long.get(Long.valueOf(Long.valueOf(zzam).longValue()))).longValue();
        } catch (NumberFormatException e) {
            return ((Long) com_google_android_gms_internal_zzcga_java_lang_Long.get()).longValue();
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

    public final boolean zzaxo() {
        Boolean zzis = zzis("firebase_analytics_collection_deactivated");
        return zzis != null && zzis.booleanValue();
    }

    public final String zzaxr() {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{"debug.firebase.analytics.app", ""});
        } catch (ClassNotFoundException e) {
            zzawm().zzayr().zzj("Could not find SystemProperties class", e);
        } catch (NoSuchMethodException e2) {
            zzawm().zzayr().zzj("Could not find SystemProperties.get() method", e2);
        } catch (IllegalAccessException e3) {
            zzawm().zzayr().zzj("Could not access SystemProperties.get()", e3);
        } catch (InvocationTargetException e4) {
            zzawm().zzayr().zzj("SystemProperties.get() threw an exception", e4);
        }
        return "";
    }

    public final int zzb(String str, zzcga<Integer> com_google_android_gms_internal_zzcga_java_lang_Integer) {
        if (str == null) {
            return ((Integer) com_google_android_gms_internal_zzcga_java_lang_Integer.get()).intValue();
        }
        Object zzam = zzawj().zzam(str, com_google_android_gms_internal_zzcga_java_lang_Integer.getKey());
        if (TextUtils.isEmpty(zzam)) {
            return ((Integer) com_google_android_gms_internal_zzcga_java_lang_Integer.get()).intValue();
        }
        try {
            return ((Integer) com_google_android_gms_internal_zzcga_java_lang_Integer.get(Integer.valueOf(Integer.valueOf(zzam).intValue()))).intValue();
        } catch (NumberFormatException e) {
            return ((Integer) com_google_android_gms_internal_zzcga_java_lang_Integer.get()).intValue();
        }
    }

    public final int zzir(@Size(min = 1) String str) {
        return zzb(str, zzcfz.zzixw);
    }

    @Nullable
    final Boolean zzis(@Size(min = 1) String str) {
        Boolean bool = null;
        zzbq.zzgh(str);
        try {
            if (getContext().getPackageManager() == null) {
                zzawm().zzayr().log("Failed to load metadata: PackageManager is null");
            } else {
                ApplicationInfo applicationInfo = zzbgc.zzcy(getContext()).getApplicationInfo(getContext().getPackageName(), 128);
                if (applicationInfo == null) {
                    zzawm().zzayr().log("Failed to load metadata: ApplicationInfo is null");
                } else if (applicationInfo.metaData == null) {
                    zzawm().zzayr().log("Failed to load metadata: Metadata bundle is null");
                } else if (applicationInfo.metaData.containsKey(str)) {
                    bool = Boolean.valueOf(applicationInfo.metaData.getBoolean(str));
                }
            }
        } catch (NameNotFoundException e) {
            zzawm().zzayr().zzj("Failed to load metadata: Package name not found", e);
        }
        return bool;
    }

    public final boolean zzit(String str) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(zzawj().zzam(str, "gaia_collection_enabled"));
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }

    public final boolean zzye() {
        if (this.zzdsn == null) {
            synchronized (this) {
                if (this.zzdsn == null) {
                    ApplicationInfo applicationInfo = getContext().getApplicationInfo();
                    String zzamc = zzs.zzamc();
                    if (applicationInfo != null) {
                        String str = applicationInfo.processName;
                        boolean z = str != null && str.equals(zzamc);
                        this.zzdsn = Boolean.valueOf(z);
                    }
                    if (this.zzdsn == null) {
                        this.zzdsn = Boolean.TRUE;
                        zzawm().zzayr().log("My process not in the list of running processes");
                    }
                }
            }
        }
        return this.zzdsn.booleanValue();
    }
}

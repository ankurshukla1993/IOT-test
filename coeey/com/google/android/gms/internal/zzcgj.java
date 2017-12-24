package com.google.android.gms.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement;

public final class zzcgj extends zzcii {
    private final String zzfye = ((String) zzcfz.zzixi.get());
    private final long zziui = 11720;
    private final char zziyy;
    private final zzcgl zziyz;
    private final zzcgl zziza;
    private final zzcgl zzizb;
    private final zzcgl zzizc;
    private final zzcgl zzizd;
    private final zzcgl zzize;
    private final zzcgl zzizf;
    private final zzcgl zzizg;
    private final zzcgl zzizh;

    zzcgj(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
        if (zzawo().zzye()) {
            this.zziyy = 'C';
        } else {
            this.zziyy = 'c';
        }
        this.zziyz = new zzcgl(this, 6, false, false);
        this.zziza = new zzcgl(this, 6, true, false);
        this.zzizb = new zzcgl(this, 6, false, true);
        this.zzizc = new zzcgl(this, 5, false, false);
        this.zzizd = new zzcgl(this, 5, true, false);
        this.zzize = new zzcgl(this, 5, false, true);
        this.zzizf = new zzcgl(this, 4, false, false);
        this.zzizg = new zzcgl(this, 3, false, false);
        this.zzizh = new zzcgl(this, 2, false, false);
    }

    private static String zza(boolean z, String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            Object obj4 = "";
        }
        Object zzb = zzb(z, obj);
        Object zzb2 = zzb(z, obj2);
        Object zzb3 = zzb(z, obj3);
        StringBuilder stringBuilder = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(obj4)) {
            stringBuilder.append(obj4);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zzb)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzb);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzb2)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzb2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzb3)) {
            stringBuilder.append(str2);
            stringBuilder.append(zzb3);
        }
        return stringBuilder.toString();
    }

    private static String zzb(boolean z, Object obj) {
        if (obj == null) {
            return "";
        }
        Object valueOf = obj instanceof Integer ? Long.valueOf((long) ((Integer) obj).intValue()) : obj;
        if (valueOf instanceof Long) {
            if (!z) {
                return String.valueOf(valueOf);
            }
            if (Math.abs(((Long) valueOf).longValue()) < 100) {
                return String.valueOf(valueOf);
            }
            String str = String.valueOf(valueOf).charAt(0) == '-' ? "-" : "";
            String valueOf2 = String.valueOf(Math.abs(((Long) valueOf).longValue()));
            return new StringBuilder((String.valueOf(str).length() + 43) + String.valueOf(str).length()).append(str).append(Math.round(Math.pow(10.0d, (double) (valueOf2.length() - 1)))).append("...").append(str).append(Math.round(Math.pow(10.0d, (double) valueOf2.length()) - 1.0d)).toString();
        } else if (valueOf instanceof Boolean) {
            return String.valueOf(valueOf);
        } else {
            if (!(valueOf instanceof Throwable)) {
                return valueOf instanceof zzcgm ? ((zzcgm) valueOf).zzguz : z ? "-" : String.valueOf(valueOf);
            } else {
                Throwable th = (Throwable) valueOf;
                StringBuilder stringBuilder = new StringBuilder(z ? th.getClass().getName() : th.toString());
                String zzjf = zzjf(AppMeasurement.class.getCanonicalName());
                String zzjf2 = zzjf(zzchj.class.getCanonicalName());
                for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                    if (!stackTraceElement.isNativeMethod()) {
                        String className = stackTraceElement.getClassName();
                        if (className != null) {
                            className = zzjf(className);
                            if (className.equals(zzjf) || className.equals(zzjf2)) {
                                stringBuilder.append(": ");
                                stringBuilder.append(stackTraceElement);
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
                return stringBuilder.toString();
            }
        }
    }

    protected static Object zzje(String str) {
        return str == null ? null : new zzcgm(str);
    }

    private static String zzjf(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf != -1 ? str.substring(0, lastIndexOf) : str;
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    protected final void zza(int i, boolean z, boolean z2, String str, Object obj, Object obj2, Object obj3) {
        if (!z && zzae(i)) {
            zzk(i, zza(false, str, obj, obj2, obj3));
        }
        if (!z2 && i >= 5) {
            zzbq.checkNotNull(str);
            zzcii zzazm = this.zzitk.zzazm();
            if (zzazm == null) {
                zzk(6, "Scheduler not set. Not logging error/warn");
            } else if (zzazm.isInitialized()) {
                int i2 = i < 0 ? 0 : i;
                if (i2 >= 9) {
                    i2 = 8;
                }
                String str2 = "2";
                char charAt = "01VDIWEA?".charAt(i2);
                char c = this.zziyy;
                long j = this.zziui;
                String zza = zza(true, str, obj, obj2, obj3);
                String stringBuilder = new StringBuilder((String.valueOf(str2).length() + 23) + String.valueOf(zza).length()).append(str2).append(charAt).append(c).append(j).append(":").append(zza).toString();
                if (stringBuilder.length() > 1024) {
                    stringBuilder = str.substring(0, 1024);
                }
                zzazm.zzg(new zzcgk(this, stringBuilder));
            } else {
                zzk(6, "Scheduler not initialized. Not logging error/warn");
            }
        }
    }

    protected final boolean zzae(int i) {
        return Log.isLoggable(this.zzfye, i);
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

    public final zzcgl zzayr() {
        return this.zziyz;
    }

    public final zzcgl zzays() {
        return this.zziza;
    }

    public final zzcgl zzayt() {
        return this.zzizc;
    }

    public final zzcgl zzayu() {
        return this.zzize;
    }

    public final zzcgl zzayv() {
        return this.zzizf;
    }

    public final zzcgl zzayw() {
        return this.zzizg;
    }

    public final zzcgl zzayx() {
        return this.zzizh;
    }

    public final String zzayy() {
        Pair zzzs = zzawn().zzizv.zzzs();
        if (zzzs == null || zzzs == zzcgu.zzizu) {
            return null;
        }
        String valueOf = String.valueOf(zzzs.second);
        String str = (String) zzzs.first;
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(str).length()).append(valueOf).append(":").append(str).toString();
    }

    protected final void zzk(int i, String str) {
        Log.println(i, this.zzfye, str);
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }
}

package com.google.android.gms.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement$Event;
import com.google.android.gms.measurement.AppMeasurement$Param;
import com.google.android.gms.measurement.AppMeasurement$UserProperty;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import java.io.IOException;
import java.util.Map;

public final class zzchd extends zzcii {
    private static int zzjbc = 65535;
    private static int zzjbd = 2;
    private final Map<String, Map<String, String>> zzjbe = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzjbf = new ArrayMap();
    private final Map<String, Map<String, Boolean>> zzjbg = new ArrayMap();
    private final Map<String, zzckv> zzjbh = new ArrayMap();
    private final Map<String, Map<String, Integer>> zzjbi = new ArrayMap();
    private final Map<String, String> zzjbj = new ArrayMap();

    zzchd(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    private static Map<String, String> zza(zzckv com_google_android_gms_internal_zzckv) {
        Map<String, String> arrayMap = new ArrayMap();
        if (!(com_google_android_gms_internal_zzckv == null || com_google_android_gms_internal_zzckv.zzjid == null)) {
            for (zzckw com_google_android_gms_internal_zzckw : com_google_android_gms_internal_zzckv.zzjid) {
                if (com_google_android_gms_internal_zzckw != null) {
                    arrayMap.put(com_google_android_gms_internal_zzckw.key, com_google_android_gms_internal_zzckw.value);
                }
            }
        }
        return arrayMap;
    }

    private final void zza(String str, zzckv com_google_android_gms_internal_zzckv) {
        Map arrayMap = new ArrayMap();
        Map arrayMap2 = new ArrayMap();
        Map arrayMap3 = new ArrayMap();
        if (!(com_google_android_gms_internal_zzckv == null || com_google_android_gms_internal_zzckv.zzjie == null)) {
            for (zzcku com_google_android_gms_internal_zzcku : com_google_android_gms_internal_zzckv.zzjie) {
                if (TextUtils.isEmpty(com_google_android_gms_internal_zzcku.name)) {
                    zzawm().zzayt().log("EventConfig contained null event name");
                } else {
                    Object zzik = AppMeasurement$Event.zzik(com_google_android_gms_internal_zzcku.name);
                    if (!TextUtils.isEmpty(zzik)) {
                        com_google_android_gms_internal_zzcku.name = zzik;
                    }
                    arrayMap.put(com_google_android_gms_internal_zzcku.name, com_google_android_gms_internal_zzcku.zzjhy);
                    arrayMap2.put(com_google_android_gms_internal_zzcku.name, com_google_android_gms_internal_zzcku.zzjhz);
                    if (com_google_android_gms_internal_zzcku.zzjia != null) {
                        if (com_google_android_gms_internal_zzcku.zzjia.intValue() < zzjbd || com_google_android_gms_internal_zzcku.zzjia.intValue() > zzjbc) {
                            zzawm().zzayt().zze("Invalid sampling rate. Event name, sample rate", com_google_android_gms_internal_zzcku.name, com_google_android_gms_internal_zzcku.zzjia);
                        } else {
                            arrayMap3.put(com_google_android_gms_internal_zzcku.name, com_google_android_gms_internal_zzcku.zzjia);
                        }
                    }
                }
            }
        }
        this.zzjbf.put(str, arrayMap);
        this.zzjbg.put(str, arrayMap2);
        this.zzjbi.put(str, arrayMap3);
    }

    @WorkerThread
    private final zzckv zzc(String str, byte[] bArr) {
        if (bArr == null) {
            return new zzckv();
        }
        zzfhb zzn = zzfhb.zzn(bArr, 0, bArr.length);
        zzfhk com_google_android_gms_internal_zzckv = new zzckv();
        try {
            com_google_android_gms_internal_zzckv.zza(zzn);
            zzawm().zzayx().zze("Parsed config. version, gmp_app_id", com_google_android_gms_internal_zzckv.zzjib, com_google_android_gms_internal_zzckv.zziux);
            return com_google_android_gms_internal_zzckv;
        } catch (IOException e) {
            zzawm().zzayt().zze("Unable to merge remote config. appId", zzcgj.zzje(str), e);
            return new zzckv();
        }
    }

    @WorkerThread
    private final void zzjl(String str) {
        zzwu();
        zzut();
        zzbq.zzgh(str);
        if (this.zzjbh.get(str) == null) {
            byte[] zzix = zzawg().zzix(str);
            if (zzix == null) {
                this.zzjbe.put(str, null);
                this.zzjbf.put(str, null);
                this.zzjbg.put(str, null);
                this.zzjbh.put(str, null);
                this.zzjbj.put(str, null);
                this.zzjbi.put(str, null);
                return;
            }
            zzckv zzc = zzc(str, zzix);
            this.zzjbe.put(str, zza(zzc));
            zza(str, zzc);
            this.zzjbh.put(str, zzc);
            this.zzjbj.put(str, null);
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @WorkerThread
    final String zzam(String str, String str2) {
        zzut();
        zzjl(str);
        Map map = (Map) this.zzjbe.get(str);
        return map != null ? (String) map.get(str2) : null;
    }

    @WorkerThread
    final boolean zzan(String str, String str2) {
        zzut();
        zzjl(str);
        if (zzawi().zzkf(str) && zzckn.zzkc(str2)) {
            return true;
        }
        if (zzawi().zzkg(str) && zzckn.zzjt(str2)) {
            return true;
        }
        Map map = (Map) this.zzjbf.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        return bool == null ? false : bool.booleanValue();
    }

    @WorkerThread
    final boolean zzao(String str, String str2) {
        zzut();
        zzjl(str);
        if (Event.ECOMMERCE_PURCHASE.equals(str2)) {
            return true;
        }
        Map map = (Map) this.zzjbg.get(str);
        if (map == null) {
            return false;
        }
        Boolean bool = (Boolean) map.get(str2);
        return bool == null ? false : bool.booleanValue();
    }

    @WorkerThread
    final int zzap(String str, String str2) {
        zzut();
        zzjl(str);
        Map map = (Map) this.zzjbi.get(str);
        if (map == null) {
            return 1;
        }
        Integer num = (Integer) map.get(str2);
        return num == null ? 1 : num.intValue();
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
    protected final boolean zzb(String str, byte[] bArr, String str2) {
        zzwu();
        zzut();
        zzbq.zzgh(str);
        zzfhk zzc = zzc(str, bArr);
        if (zzc == null) {
            return false;
        }
        zza(str, zzc);
        this.zzjbh.put(str, zzc);
        this.zzjbj.put(str, str2);
        this.zzjbe.put(str, zza(zzc));
        zzcih zzavz = zzavz();
        zzcko[] com_google_android_gms_internal_zzckoArr = zzc.zzjif;
        zzbq.checkNotNull(com_google_android_gms_internal_zzckoArr);
        for (zzcko com_google_android_gms_internal_zzcko : com_google_android_gms_internal_zzckoArr) {
            for (zzckp com_google_android_gms_internal_zzckp : com_google_android_gms_internal_zzcko.zzjgz) {
                String zzik = AppMeasurement$Event.zzik(com_google_android_gms_internal_zzckp.zzjhc);
                if (zzik != null) {
                    com_google_android_gms_internal_zzckp.zzjhc = zzik;
                }
                for (zzckq com_google_android_gms_internal_zzckq : com_google_android_gms_internal_zzckp.zzjhd) {
                    String zzik2 = AppMeasurement$Param.zzik(com_google_android_gms_internal_zzckq.zzjhk);
                    if (zzik2 != null) {
                        com_google_android_gms_internal_zzckq.zzjhk = zzik2;
                    }
                }
            }
            for (zzcks com_google_android_gms_internal_zzcks : com_google_android_gms_internal_zzcko.zzjgy) {
                String zzik3 = AppMeasurement$UserProperty.zzik(com_google_android_gms_internal_zzcks.zzjhr);
                if (zzik3 != null) {
                    com_google_android_gms_internal_zzcks.zzjhr = zzik3;
                }
            }
        }
        zzavz.zzawg().zza(str, com_google_android_gms_internal_zzckoArr);
        try {
            zzc.zzjif = null;
            byte[] bArr2 = new byte[zzc.zzhl()];
            zzc.zza(zzfhc.zzo(bArr2, 0, bArr2.length));
            bArr = bArr2;
        } catch (IOException e) {
            zzawm().zzayt().zze("Unable to serialize reduced-size config. Storing full config instead. appId", zzcgj.zzje(str), e);
        }
        zzcih zzawg = zzawg();
        zzbq.zzgh(str);
        zzawg.zzut();
        zzawg.zzwu();
        ContentValues contentValues = new ContentValues();
        contentValues.put("remote_config", bArr);
        try {
            if (((long) zzawg.getWritableDatabase().update("apps", contentValues, "app_id = ?", new String[]{str})) == 0) {
                zzawg.zzawm().zzayr().zzj("Failed to update remote config (got 0). appId", zzcgj.zzje(str));
            }
        } catch (SQLiteException e2) {
            zzawg.zzawm().zzayr().zze("Error storing remote config. appId", zzcgj.zzje(str), e2);
        }
        return true;
    }

    @WorkerThread
    protected final zzckv zzjm(String str) {
        zzwu();
        zzut();
        zzbq.zzgh(str);
        zzjl(str);
        return (zzckv) this.zzjbh.get(str);
    }

    @WorkerThread
    protected final String zzjn(String str) {
        zzut();
        return (String) this.zzjbj.get(str);
    }

    @WorkerThread
    protected final void zzjo(String str) {
        zzut();
        this.zzjbj.put(str, null);
    }

    @WorkerThread
    final void zzjp(String str) {
        zzut();
        this.zzjbh.remove(str);
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }
}

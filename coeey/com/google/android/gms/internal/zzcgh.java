package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement$Event;
import com.google.android.gms.measurement.AppMeasurement$Param;
import com.google.android.gms.measurement.AppMeasurement$UserProperty;
import com.ihealth.communication.control.Bg5Profile;

public final class zzcgh extends zzcii {
    private static String[] zziyv = new String[AppMeasurement$Event.zzitl.length];
    private static String[] zziyw = new String[AppMeasurement$Param.zzitn.length];
    private static String[] zziyx = new String[AppMeasurement$UserProperty.zzits.length];

    zzcgh(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    @Nullable
    private static String zza(String str, String[] strArr, String[] strArr2, String[] strArr3) {
        boolean z = true;
        int i = 0;
        zzbq.checkNotNull(strArr);
        zzbq.checkNotNull(strArr2);
        zzbq.checkNotNull(strArr3);
        zzbq.checkArgument(strArr.length == strArr2.length);
        if (strArr.length != strArr3.length) {
            z = false;
        }
        zzbq.checkArgument(z);
        while (i < strArr.length) {
            if (zzckn.zzas(str, strArr[i])) {
                synchronized (strArr3) {
                    if (strArr3[i] == null) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(strArr2[i]);
                        stringBuilder.append("(");
                        stringBuilder.append(strArr[i]);
                        stringBuilder.append(")");
                        strArr3[i] = stringBuilder.toString();
                    }
                    str = strArr3[i];
                }
                return str;
            }
            i++;
        }
        return str;
    }

    private static void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("  ");
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, zzckq com_google_android_gms_internal_zzckq) {
        if (com_google_android_gms_internal_zzckq != null) {
            zza(stringBuilder, i);
            stringBuilder.append("filter {\n");
            zza(stringBuilder, i, "complement", com_google_android_gms_internal_zzckq.zzjhj);
            zza(stringBuilder, i, "param_name", zzjc(com_google_android_gms_internal_zzckq.zzjhk));
            int i2 = i + 1;
            String str = "string_filter";
            zzckt com_google_android_gms_internal_zzckt = com_google_android_gms_internal_zzckq.zzjhh;
            if (com_google_android_gms_internal_zzckt != null) {
                zza(stringBuilder, i2);
                stringBuilder.append(str);
                stringBuilder.append(" {\n");
                if (com_google_android_gms_internal_zzckt.zzjht != null) {
                    Object obj = "UNKNOWN_MATCH_TYPE";
                    switch (com_google_android_gms_internal_zzckt.zzjht.intValue()) {
                        case 1:
                            obj = "REGEXP";
                            break;
                        case 2:
                            obj = "BEGINS_WITH";
                            break;
                        case 3:
                            obj = "ENDS_WITH";
                            break;
                        case 4:
                            obj = "PARTIAL";
                            break;
                        case 5:
                            obj = "EXACT";
                            break;
                        case 6:
                            obj = "IN_LIST";
                            break;
                    }
                    zza(stringBuilder, i2, "match_type", obj);
                }
                zza(stringBuilder, i2, "expression", com_google_android_gms_internal_zzckt.zzjhu);
                zza(stringBuilder, i2, "case_sensitive", com_google_android_gms_internal_zzckt.zzjhv);
                if (com_google_android_gms_internal_zzckt.zzjhw.length > 0) {
                    zza(stringBuilder, i2 + 1);
                    stringBuilder.append("expression_list {\n");
                    for (String str2 : com_google_android_gms_internal_zzckt.zzjhw) {
                        zza(stringBuilder, i2 + 2);
                        stringBuilder.append(str2);
                        stringBuilder.append("\n");
                    }
                    stringBuilder.append("}\n");
                }
                zza(stringBuilder, i2);
                stringBuilder.append("}\n");
            }
            zza(stringBuilder, i + 1, "number_filter", com_google_android_gms_internal_zzckq.zzjhi);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, String str, zzckr com_google_android_gms_internal_zzckr) {
        if (com_google_android_gms_internal_zzckr != null) {
            zza(stringBuilder, i);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (com_google_android_gms_internal_zzckr.zzjhl != null) {
                Object obj = "UNKNOWN_COMPARISON_TYPE";
                switch (com_google_android_gms_internal_zzckr.zzjhl.intValue()) {
                    case 1:
                        obj = "LESS_THAN";
                        break;
                    case 2:
                        obj = "GREATER_THAN";
                        break;
                    case 3:
                        obj = "EQUAL";
                        break;
                    case 4:
                        obj = "BETWEEN";
                        break;
                }
                zza(stringBuilder, i, "comparison_type", obj);
            }
            zza(stringBuilder, i, "match_as_float", com_google_android_gms_internal_zzckr.zzjhm);
            zza(stringBuilder, i, "comparison_value", com_google_android_gms_internal_zzckr.zzjhn);
            zza(stringBuilder, i, "min_comparison_value", com_google_android_gms_internal_zzckr.zzjho);
            zza(stringBuilder, i, "max_comparison_value", com_google_android_gms_internal_zzckr.zzjhp);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, zzclc com_google_android_gms_internal_zzclc) {
        int i2 = 0;
        if (com_google_android_gms_internal_zzclc != null) {
            int i3;
            int i4;
            int i5 = i + 1;
            zza(stringBuilder, i5);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (com_google_android_gms_internal_zzclc.zzjjv != null) {
                zza(stringBuilder, i5 + 1);
                stringBuilder.append("results: ");
                long[] jArr = com_google_android_gms_internal_zzclc.zzjjv;
                int length = jArr.length;
                i3 = 0;
                i4 = 0;
                while (i3 < length) {
                    Long valueOf = Long.valueOf(jArr[i3]);
                    int i6 = i4 + 1;
                    if (i4 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf);
                    i3++;
                    i4 = i6;
                }
                stringBuilder.append('\n');
            }
            if (com_google_android_gms_internal_zzclc.zzjju != null) {
                zza(stringBuilder, i5 + 1);
                stringBuilder.append("status: ");
                long[] jArr2 = com_google_android_gms_internal_zzclc.zzjju;
                int length2 = jArr2.length;
                i3 = 0;
                while (i2 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i2]);
                    i4 = i3 + 1;
                    if (i3 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf2);
                    i2++;
                    i3 = i4;
                }
                stringBuilder.append('\n');
            }
            zza(stringBuilder, i5);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, Object obj) {
        if (obj != null) {
            zza(stringBuilder, i + 1);
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(obj);
            stringBuilder.append('\n');
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, zzckx[] com_google_android_gms_internal_zzckxArr) {
        if (com_google_android_gms_internal_zzckxArr != null) {
            for (zzckx com_google_android_gms_internal_zzckx : com_google_android_gms_internal_zzckxArr) {
                if (com_google_android_gms_internal_zzckx != null) {
                    zza(stringBuilder, 2);
                    stringBuilder.append("audience_membership {\n");
                    zza(stringBuilder, 2, "audience_id", com_google_android_gms_internal_zzckx.zzjgx);
                    zza(stringBuilder, 2, "new_audience", com_google_android_gms_internal_zzckx.zzjik);
                    zza(stringBuilder, 2, "current_data", com_google_android_gms_internal_zzckx.zzjii);
                    zza(stringBuilder, 2, "previous_data", com_google_android_gms_internal_zzckx.zzjij);
                    zza(stringBuilder, 2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, zzcky[] com_google_android_gms_internal_zzckyArr) {
        if (com_google_android_gms_internal_zzckyArr != null) {
            for (zzcky com_google_android_gms_internal_zzcky : com_google_android_gms_internal_zzckyArr) {
                if (com_google_android_gms_internal_zzcky != null) {
                    zza(stringBuilder, 2);
                    stringBuilder.append("event {\n");
                    zza(stringBuilder, 2, "name", zzjb(com_google_android_gms_internal_zzcky.name));
                    zza(stringBuilder, 2, "timestamp_millis", com_google_android_gms_internal_zzcky.zzjin);
                    zza(stringBuilder, 2, "previous_timestamp_millis", com_google_android_gms_internal_zzcky.zzjio);
                    zza(stringBuilder, 2, Bg5Profile.HISTORICAL_NUM_BG, com_google_android_gms_internal_zzcky.count);
                    zzckz[] com_google_android_gms_internal_zzckzArr = com_google_android_gms_internal_zzcky.zzjim;
                    if (com_google_android_gms_internal_zzckzArr != null) {
                        for (zzckz com_google_android_gms_internal_zzckz : com_google_android_gms_internal_zzckzArr) {
                            if (com_google_android_gms_internal_zzckz != null) {
                                zza(stringBuilder, 3);
                                stringBuilder.append("param {\n");
                                zza(stringBuilder, 3, "name", zzjc(com_google_android_gms_internal_zzckz.name));
                                zza(stringBuilder, 3, "string_value", com_google_android_gms_internal_zzckz.zzfzi);
                                zza(stringBuilder, 3, "int_value", com_google_android_gms_internal_zzckz.zzjiq);
                                zza(stringBuilder, 3, "double_value", com_google_android_gms_internal_zzckz.zzjgq);
                                zza(stringBuilder, 3);
                                stringBuilder.append("}\n");
                            }
                        }
                    }
                    zza(stringBuilder, 2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private final void zza(StringBuilder stringBuilder, int i, zzcld[] com_google_android_gms_internal_zzcldArr) {
        if (com_google_android_gms_internal_zzcldArr != null) {
            for (zzcld com_google_android_gms_internal_zzcld : com_google_android_gms_internal_zzcldArr) {
                if (com_google_android_gms_internal_zzcld != null) {
                    zza(stringBuilder, 2);
                    stringBuilder.append("user_property {\n");
                    zza(stringBuilder, 2, "set_timestamp_millis", com_google_android_gms_internal_zzcld.zzjjx);
                    zza(stringBuilder, 2, "name", zzjd(com_google_android_gms_internal_zzcld.name));
                    zza(stringBuilder, 2, "string_value", com_google_android_gms_internal_zzcld.zzfzi);
                    zza(stringBuilder, 2, "int_value", com_google_android_gms_internal_zzcld.zzjiq);
                    zza(stringBuilder, 2, "double_value", com_google_android_gms_internal_zzcld.zzjgq);
                    zza(stringBuilder, 2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private final boolean zzayq() {
        return this.zzitk.zzawm().zzae(3);
    }

    @Nullable
    private final String zzb(zzcfu com_google_android_gms_internal_zzcfu) {
        return com_google_android_gms_internal_zzcfu == null ? null : !zzayq() ? com_google_android_gms_internal_zzcfu.toString() : zzx(com_google_android_gms_internal_zzcfu.zzayl());
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    @Nullable
    protected final String zza(zzcfs com_google_android_gms_internal_zzcfs) {
        if (com_google_android_gms_internal_zzcfs == null) {
            return null;
        }
        if (!zzayq()) {
            return com_google_android_gms_internal_zzcfs.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Event{appId='");
        stringBuilder.append(com_google_android_gms_internal_zzcfs.mAppId);
        stringBuilder.append("', name='");
        stringBuilder.append(zzjb(com_google_android_gms_internal_zzcfs.mName));
        stringBuilder.append("', params=");
        stringBuilder.append(zzb(com_google_android_gms_internal_zzcfs.zziwo));
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    protected final String zza(zzckp com_google_android_gms_internal_zzckp) {
        int i = 0;
        if (com_google_android_gms_internal_zzckp == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nevent_filter {\n");
        zza(stringBuilder, 0, "filter_id", com_google_android_gms_internal_zzckp.zzjhb);
        zza(stringBuilder, 0, "event_name", zzjb(com_google_android_gms_internal_zzckp.zzjhc));
        zza(stringBuilder, 1, "event_count_filter", com_google_android_gms_internal_zzckp.zzjhf);
        stringBuilder.append("  filters {\n");
        zzckq[] com_google_android_gms_internal_zzckqArr = com_google_android_gms_internal_zzckp.zzjhd;
        int length = com_google_android_gms_internal_zzckqArr.length;
        while (i < length) {
            zza(stringBuilder, 2, com_google_android_gms_internal_zzckqArr[i]);
            i++;
        }
        zza(stringBuilder, 1);
        stringBuilder.append("}\n}\n");
        return stringBuilder.toString();
    }

    protected final String zza(zzcks com_google_android_gms_internal_zzcks) {
        if (com_google_android_gms_internal_zzcks == null) {
            return "null";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nproperty_filter {\n");
        zza(stringBuilder, 0, "filter_id", com_google_android_gms_internal_zzcks.zzjhb);
        zza(stringBuilder, 0, "property_name", zzjd(com_google_android_gms_internal_zzcks.zzjhr));
        zza(stringBuilder, 1, com_google_android_gms_internal_zzcks.zzjhs);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    protected final String zza(zzcla com_google_android_gms_internal_zzcla) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nbatch {\n");
        if (com_google_android_gms_internal_zzcla.zzjir != null) {
            for (zzclb com_google_android_gms_internal_zzclb : com_google_android_gms_internal_zzcla.zzjir) {
                if (!(com_google_android_gms_internal_zzclb == null || com_google_android_gms_internal_zzclb == null)) {
                    zza(stringBuilder, 1);
                    stringBuilder.append("bundle {\n");
                    zza(stringBuilder, 1, "protocol_version", com_google_android_gms_internal_zzclb.zzjit);
                    zza(stringBuilder, 1, "platform", com_google_android_gms_internal_zzclb.zzjjb);
                    zza(stringBuilder, 1, "gmp_version", com_google_android_gms_internal_zzclb.zzjjf);
                    zza(stringBuilder, 1, "uploading_gmp_version", com_google_android_gms_internal_zzclb.zzjjg);
                    zza(stringBuilder, 1, "config_version", com_google_android_gms_internal_zzclb.zzjjs);
                    zza(stringBuilder, 1, "gmp_app_id", com_google_android_gms_internal_zzclb.zziux);
                    zza(stringBuilder, 1, "app_id", com_google_android_gms_internal_zzclb.zzch);
                    zza(stringBuilder, 1, "app_version", com_google_android_gms_internal_zzclb.zzicq);
                    zza(stringBuilder, 1, "app_version_major", com_google_android_gms_internal_zzclb.zzjjo);
                    zza(stringBuilder, 1, "firebase_instance_id", com_google_android_gms_internal_zzclb.zzivf);
                    zza(stringBuilder, 1, "dev_cert_hash", com_google_android_gms_internal_zzclb.zzjjk);
                    zza(stringBuilder, 1, "app_store", com_google_android_gms_internal_zzclb.zziuy);
                    zza(stringBuilder, 1, "upload_timestamp_millis", com_google_android_gms_internal_zzclb.zzjiw);
                    zza(stringBuilder, 1, "start_timestamp_millis", com_google_android_gms_internal_zzclb.zzjix);
                    zza(stringBuilder, 1, "end_timestamp_millis", com_google_android_gms_internal_zzclb.zzjiy);
                    zza(stringBuilder, 1, "previous_bundle_start_timestamp_millis", com_google_android_gms_internal_zzclb.zzjiz);
                    zza(stringBuilder, 1, "previous_bundle_end_timestamp_millis", com_google_android_gms_internal_zzclb.zzjja);
                    zza(stringBuilder, 1, "app_instance_id", com_google_android_gms_internal_zzclb.zzjjj);
                    zza(stringBuilder, 1, "resettable_device_id", com_google_android_gms_internal_zzclb.zzjjh);
                    zza(stringBuilder, 1, CooeySQLHelper.KEY_DEVICE_ID, com_google_android_gms_internal_zzclb.zzjjr);
                    zza(stringBuilder, 1, "limited_ad_tracking", com_google_android_gms_internal_zzclb.zzjji);
                    zza(stringBuilder, 1, "os_version", com_google_android_gms_internal_zzclb.zzcv);
                    zza(stringBuilder, 1, CooeySQLHelper.KEY_DEVICE_MODEL, com_google_android_gms_internal_zzclb.zzjjc);
                    zza(stringBuilder, 1, "user_default_language", com_google_android_gms_internal_zzclb.zzjjd);
                    zza(stringBuilder, 1, "time_zone_offset_minutes", com_google_android_gms_internal_zzclb.zzjje);
                    zza(stringBuilder, 1, "bundle_sequential_index", com_google_android_gms_internal_zzclb.zzjjl);
                    zza(stringBuilder, 1, "service_upload", com_google_android_gms_internal_zzclb.zzjjm);
                    zza(stringBuilder, 1, "health_monitor", com_google_android_gms_internal_zzclb.zzivb);
                    if (com_google_android_gms_internal_zzclb.zzfhr.longValue() != 0) {
                        zza(stringBuilder, 1, "android_id", com_google_android_gms_internal_zzclb.zzfhr);
                    }
                    zza(stringBuilder, 1, com_google_android_gms_internal_zzclb.zzjiv);
                    zza(stringBuilder, 1, com_google_android_gms_internal_zzclb.zzjjn);
                    zza(stringBuilder, 1, com_google_android_gms_internal_zzclb.zzjiu);
                    zza(stringBuilder, 1);
                    stringBuilder.append("}\n");
                }
            }
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
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

    @Nullable
    protected final String zzb(zzcfx com_google_android_gms_internal_zzcfx) {
        if (com_google_android_gms_internal_zzcfx == null) {
            return null;
        }
        if (!zzayq()) {
            return com_google_android_gms_internal_zzcfx.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("origin=");
        stringBuilder.append(com_google_android_gms_internal_zzcfx.zzivk);
        stringBuilder.append(",name=");
        stringBuilder.append(zzjb(com_google_android_gms_internal_zzcfx.name));
        stringBuilder.append(",params=");
        stringBuilder.append(zzb(com_google_android_gms_internal_zzcfx.zziwy));
        return stringBuilder.toString();
    }

    @Nullable
    protected final String zzjb(String str) {
        return str == null ? null : zzayq() ? zza(str, AppMeasurement$Event.zzitm, AppMeasurement$Event.zzitl, zziyv) : str;
    }

    @Nullable
    protected final String zzjc(String str) {
        return str == null ? null : zzayq() ? zza(str, AppMeasurement$Param.zzito, AppMeasurement$Param.zzitn, zziyw) : str;
    }

    @Nullable
    protected final String zzjd(String str) {
        if (str == null) {
            return null;
        }
        if (!zzayq()) {
            return str;
        }
        if (!str.startsWith("_exp_")) {
            return zza(str, AppMeasurement$UserProperty.zzitt, AppMeasurement$UserProperty.zzits, zziyx);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("experiment_id");
        stringBuilder.append("(");
        stringBuilder.append(str);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }

    @Nullable
    protected final String zzx(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        if (!zzayq()) {
            return bundle.toString();
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : bundle.keySet()) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append(", ");
            } else {
                stringBuilder.append("Bundle[{");
            }
            stringBuilder.append(zzjc(str));
            stringBuilder.append("=");
            stringBuilder.append(bundle.get(str));
        }
        stringBuilder.append("}]");
        return stringBuilder.toString();
    }
}

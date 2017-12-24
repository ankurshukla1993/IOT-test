package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty;
import com.google.android.gms.measurement.AppMeasurement$Event;
import com.google.android.gms.measurement.AppMeasurement$EventInterceptor;
import com.google.android.gms.measurement.AppMeasurement$OnEventListener;
import com.google.android.gms.measurement.AppMeasurement$zzb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicReference;

public final class zzcik extends zzcii {
    protected zzciy zzjec;
    private AppMeasurement$EventInterceptor zzjed;
    private final Set<AppMeasurement$OnEventListener> zzjee = new CopyOnWriteArraySet();
    private boolean zzjef;
    private final AtomicReference<String> zzjeg = new AtomicReference();

    protected zzcik(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    private final void zza(AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        long currentTimeMillis = zzwh().currentTimeMillis();
        zzbq.checkNotNull(appMeasurement$ConditionalUserProperty);
        zzbq.zzgh(appMeasurement$ConditionalUserProperty.mName);
        zzbq.zzgh(appMeasurement$ConditionalUserProperty.mOrigin);
        zzbq.checkNotNull(appMeasurement$ConditionalUserProperty.mValue);
        appMeasurement$ConditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        String str = appMeasurement$ConditionalUserProperty.mName;
        Object obj = appMeasurement$ConditionalUserProperty.mValue;
        if (zzawi().zzjx(str) != 0) {
            zzawm().zzayr().zzj("Invalid conditional user property name", zzawh().zzjd(str));
        } else if (zzawi().zzl(str, obj) != 0) {
            zzawm().zzayr().zze("Invalid conditional user property value", zzawh().zzjd(str), obj);
        } else {
            Object zzm = zzawi().zzm(str, obj);
            if (zzm == null) {
                zzawm().zzayr().zze("Unable to normalize conditional user property value", zzawh().zzjd(str), obj);
                return;
            }
            appMeasurement$ConditionalUserProperty.mValue = zzm;
            long j = appMeasurement$ConditionalUserProperty.mTriggerTimeout;
            if (TextUtils.isEmpty(appMeasurement$ConditionalUserProperty.mTriggerEventName) || (j <= 15552000000L && j >= 1)) {
                j = appMeasurement$ConditionalUserProperty.mTimeToLive;
                if (j > 15552000000L || j < 1) {
                    zzawm().zzayr().zze("Invalid conditional user property time to live", zzawh().zzjd(str), Long.valueOf(j));
                    return;
                } else {
                    zzawl().zzg(new zzcim(this, appMeasurement$ConditionalUserProperty));
                    return;
                }
            }
            zzawm().zzayr().zze("Invalid conditional user property timeout", zzawh().zzjd(str), Long.valueOf(j));
        }
    }

    private final void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        Bundle bundle2;
        if (bundle == null) {
            bundle2 = new Bundle();
        } else {
            bundle2 = new Bundle(bundle);
            for (String str4 : bundle2.keySet()) {
                Object obj = bundle2.get(str4);
                if (obj instanceof Bundle) {
                    bundle2.putBundle(str4, new Bundle((Bundle) obj));
                } else if (obj instanceof Parcelable[]) {
                    Parcelable[] parcelableArr = (Parcelable[]) obj;
                    for (r4 = 0; r4 < parcelableArr.length; r4++) {
                        if (parcelableArr[r4] instanceof Bundle) {
                            parcelableArr[r4] = new Bundle((Bundle) parcelableArr[r4]);
                        }
                    }
                } else if (obj instanceof ArrayList) {
                    ArrayList arrayList = (ArrayList) obj;
                    for (r4 = 0; r4 < arrayList.size(); r4++) {
                        Object obj2 = arrayList.get(r4);
                        if (obj2 instanceof Bundle) {
                            arrayList.set(r4, new Bundle((Bundle) obj2));
                        }
                    }
                }
            }
        }
        zzawl().zzg(new zzcis(this, str, str2, j, bundle2, z, z2, z3, str3));
    }

    private final void zza(String str, String str2, long j, Object obj) {
        zzawl().zzg(new zzcit(this, str, str2, obj, j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zza(str, str2, zzwh().currentTimeMillis(), bundle, true, z2, z3, null);
    }

    @WorkerThread
    private final void zza(String str, String str2, Object obj, long j) {
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        zzut();
        zzwu();
        if (!this.zzitk.isEnabled()) {
            zzawm().zzayw().log("User property not set since app measurement is disabled");
        } else if (this.zzitk.zzazj()) {
            zzawm().zzayw().zze("Setting user property (FE)", zzawh().zzjb(str2), obj);
            zzawd().zzb(new zzckk(str2, j, obj, str));
        }
    }

    private final void zza(String str, String str2, String str3, Bundle bundle) {
        long currentTimeMillis = zzwh().currentTimeMillis();
        zzbq.zzgh(str2);
        AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty = new AppMeasurement$ConditionalUserProperty();
        appMeasurement$ConditionalUserProperty.mAppId = str;
        appMeasurement$ConditionalUserProperty.mName = str2;
        appMeasurement$ConditionalUserProperty.mCreationTimestamp = currentTimeMillis;
        if (str3 != null) {
            appMeasurement$ConditionalUserProperty.mExpiredEventName = str3;
            appMeasurement$ConditionalUserProperty.mExpiredEventParams = bundle;
        }
        zzawl().zzg(new zzcin(this, appMeasurement$ConditionalUserProperty));
    }

    private final Map<String, Object> zzb(String str, String str2, String str3, boolean z) {
        if (zzawl().zzazg()) {
            zzawm().zzayr().log("Cannot get user properties from analytics worker thread");
            return Collections.emptyMap();
        }
        zzawl();
        if (zzche.zzas()) {
            zzawm().zzayr().log("Cannot get user properties from main thread");
            return Collections.emptyMap();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zzitk.zzawl().zzg(new zzcip(this, atomicReference, str, str2, str3, z));
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzawm().zzayt().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzckk> list = (List) atomicReference.get();
        if (list == null) {
            zzawm().zzayt().log("Timed out waiting for get user properties");
            return Collections.emptyMap();
        }
        Map<String, Object> arrayMap = new ArrayMap(list.size());
        for (zzckk com_google_android_gms_internal_zzckk : list) {
            arrayMap.put(com_google_android_gms_internal_zzckk.name, com_google_android_gms_internal_zzckk.getValue());
        }
        return arrayMap;
    }

    @WorkerThread
    private final void zzb(AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        zzut();
        zzwu();
        zzbq.checkNotNull(appMeasurement$ConditionalUserProperty);
        zzbq.zzgh(appMeasurement$ConditionalUserProperty.mName);
        zzbq.zzgh(appMeasurement$ConditionalUserProperty.mOrigin);
        zzbq.checkNotNull(appMeasurement$ConditionalUserProperty.mValue);
        if (this.zzitk.isEnabled()) {
            zzckk com_google_android_gms_internal_zzckk = new zzckk(appMeasurement$ConditionalUserProperty.mName, appMeasurement$ConditionalUserProperty.mTriggeredTimestamp, appMeasurement$ConditionalUserProperty.mValue, appMeasurement$ConditionalUserProperty.mOrigin);
            try {
                zzcfx zza = zzawi().zza(appMeasurement$ConditionalUserProperty.mTriggeredEventName, appMeasurement$ConditionalUserProperty.mTriggeredEventParams, appMeasurement$ConditionalUserProperty.mOrigin, 0, true, false);
                zzawd().zzf(new zzcfi(appMeasurement$ConditionalUserProperty.mAppId, appMeasurement$ConditionalUserProperty.mOrigin, com_google_android_gms_internal_zzckk, appMeasurement$ConditionalUserProperty.mCreationTimestamp, false, appMeasurement$ConditionalUserProperty.mTriggerEventName, zzawi().zza(appMeasurement$ConditionalUserProperty.mTimedOutEventName, appMeasurement$ConditionalUserProperty.mTimedOutEventParams, appMeasurement$ConditionalUserProperty.mOrigin, 0, true, false), appMeasurement$ConditionalUserProperty.mTriggerTimeout, zza, appMeasurement$ConditionalUserProperty.mTimeToLive, zzawi().zza(appMeasurement$ConditionalUserProperty.mExpiredEventName, appMeasurement$ConditionalUserProperty.mExpiredEventParams, appMeasurement$ConditionalUserProperty.mOrigin, 0, true, false)));
                return;
            } catch (IllegalArgumentException e) {
                return;
            }
        }
        zzawm().zzayw().log("Conditional property not sent since Firebase Analytics is disabled");
    }

    @WorkerThread
    private final void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzbq.zzgh(str);
        zzbq.zzgh(str2);
        zzbq.checkNotNull(bundle);
        zzut();
        zzwu();
        if (this.zzitk.isEnabled()) {
            if (!this.zzjef) {
                this.zzjef = true;
                try {
                    try {
                        Class.forName("com.google.android.gms.tagmanager.TagManagerService").getDeclaredMethod("initialize", new Class[]{Context.class}).invoke(null, new Object[]{getContext()});
                    } catch (Exception e) {
                        zzawm().zzayt().zzj("Failed to invoke Tag Manager's initialize() method", e);
                    }
                } catch (ClassNotFoundException e2) {
                    zzawm().zzayv().log("Tag Manager is not found and thus will not be used");
                }
            }
            boolean equals = "am".equals(str);
            boolean zzkc = zzckn.zzkc(str2);
            if (z && this.zzjed != null && !zzkc && !equals) {
                zzawm().zzayw().zze("Passing event to registered event handler (FE)", zzawh().zzjb(str2), zzawh().zzx(bundle));
                this.zzjed.interceptEvent(str, str2, bundle, j);
                return;
            } else if (this.zzitk.zzazj()) {
                int zzjv = zzawi().zzjv(str2);
                if (zzjv != 0) {
                    zzawi();
                    this.zzitk.zzawi().zza(str3, zzjv, "_ev", zzckn.zza(str2, 40, true), str2 != null ? str2.length() : 0);
                    return;
                }
                int i;
                Bundle zza;
                List singletonList = Collections.singletonList("_o");
                Bundle zza2 = zzawi().zza(str2, bundle, singletonList, z3, true);
                List arrayList = new ArrayList();
                arrayList.add(zza2);
                long nextLong = zzawi().zzban().nextLong();
                int i2 = 0;
                String[] strArr = (String[]) zza2.keySet().toArray(new String[bundle.size()]);
                Arrays.sort(strArr);
                int length = strArr.length;
                int i3 = 0;
                while (i3 < length) {
                    int length2;
                    String str4 = strArr[i3];
                    Object obj = zza2.get(str4);
                    zzawi();
                    Bundle[] zzae = zzckn.zzae(obj);
                    if (zzae != null) {
                        zza2.putInt(str4, zzae.length);
                        for (i = 0; i < zzae.length; i++) {
                            zza = zzawi().zza("_ep", zzae[i], singletonList, z3, false);
                            zza.putString("_en", str2);
                            zza.putLong("_eid", nextLong);
                            zza.putString("_gn", str4);
                            zza.putInt("_ll", zzae.length);
                            zza.putInt("_i", i);
                            arrayList.add(zza);
                        }
                        length2 = zzae.length + i2;
                    } else {
                        length2 = i2;
                    }
                    i3++;
                    i2 = length2;
                }
                if (i2 != 0) {
                    zza2.putLong("_eid", nextLong);
                    zza2.putInt("_epc", i2);
                }
                AppMeasurement$zzb zzbac = zzawe().zzbac();
                if (!(zzbac == null || zza2.containsKey("_sc"))) {
                    zzbac.zzjfg = true;
                }
                i = 0;
                while (i < arrayList.size()) {
                    zza = (Bundle) arrayList.get(i);
                    String str5 = (i != 0 ? 1 : null) != null ? "_ep" : str2;
                    zza.putString("_o", str);
                    if (!zza.containsKey("_sc")) {
                        zzciz.zza(zzbac, zza);
                    }
                    Bundle zzy = z2 ? zzawi().zzy(zza) : zza;
                    zzawm().zzayw().zze("Logging event (FE)", zzawh().zzjb(str2), zzawh().zzx(zzy));
                    zzawd().zzc(new zzcfx(str5, new zzcfu(zzy), str, j), str3);
                    if (!equals) {
                        for (AppMeasurement$OnEventListener onEvent : this.zzjee) {
                            onEvent.onEvent(str, str2, new Bundle(zzy), j);
                        }
                    }
                    i++;
                }
                if (zzawe().zzbac() != null && AppMeasurement$Event.APP_EXCEPTION.equals(str2)) {
                    zzawk().zzbr(true);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        zzawm().zzayw().log("Event not sent since app measurement is disabled");
    }

    @WorkerThread
    private final void zzbo(boolean z) {
        zzut();
        zzwu();
        zzawm().zzayw().zzj("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzawn().setMeasurementEnabled(z);
        zzawd().zzbae();
    }

    @WorkerThread
    private final void zzc(AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        zzut();
        zzwu();
        zzbq.checkNotNull(appMeasurement$ConditionalUserProperty);
        zzbq.zzgh(appMeasurement$ConditionalUserProperty.mName);
        if (this.zzitk.isEnabled()) {
            zzckk com_google_android_gms_internal_zzckk = new zzckk(appMeasurement$ConditionalUserProperty.mName, 0, null, null);
            try {
                zzawd().zzf(new zzcfi(appMeasurement$ConditionalUserProperty.mAppId, appMeasurement$ConditionalUserProperty.mOrigin, com_google_android_gms_internal_zzckk, appMeasurement$ConditionalUserProperty.mCreationTimestamp, appMeasurement$ConditionalUserProperty.mActive, appMeasurement$ConditionalUserProperty.mTriggerEventName, null, appMeasurement$ConditionalUserProperty.mTriggerTimeout, null, appMeasurement$ConditionalUserProperty.mTimeToLive, zzawi().zza(appMeasurement$ConditionalUserProperty.mExpiredEventName, appMeasurement$ConditionalUserProperty.mExpiredEventParams, appMeasurement$ConditionalUserProperty.mOrigin, appMeasurement$ConditionalUserProperty.mCreationTimestamp, true, false)));
                return;
            } catch (IllegalArgumentException e) {
                return;
            }
        }
        zzawm().zzayw().log("Conditional property not cleared since Firebase Analytics is disabled");
    }

    private final List<AppMeasurement$ConditionalUserProperty> zzk(String str, String str2, String str3) {
        if (zzawl().zzazg()) {
            zzawm().zzayr().log("Cannot get conditional user properties from analytics worker thread");
            return Collections.emptyList();
        }
        zzawl();
        if (zzche.zzas()) {
            zzawm().zzayr().log("Cannot get conditional user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zzitk.zzawl().zzg(new zzcio(this, atomicReference, str, str2, str3));
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzawm().zzayt().zze("Interrupted waiting for get conditional user properties", str, e);
            }
        }
        List<zzcfi> list = (List) atomicReference.get();
        if (list == null) {
            zzawm().zzayt().zzj("Timed out waiting for get conditional user properties", str);
            return Collections.emptyList();
        }
        List<AppMeasurement$ConditionalUserProperty> arrayList = new ArrayList(list.size());
        for (zzcfi com_google_android_gms_internal_zzcfi : list) {
            AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty = new AppMeasurement$ConditionalUserProperty();
            appMeasurement$ConditionalUserProperty.mAppId = str;
            appMeasurement$ConditionalUserProperty.mOrigin = str2;
            appMeasurement$ConditionalUserProperty.mCreationTimestamp = com_google_android_gms_internal_zzcfi.zzivm;
            appMeasurement$ConditionalUserProperty.mName = com_google_android_gms_internal_zzcfi.zzivl.name;
            appMeasurement$ConditionalUserProperty.mValue = com_google_android_gms_internal_zzcfi.zzivl.getValue();
            appMeasurement$ConditionalUserProperty.mActive = com_google_android_gms_internal_zzcfi.zzivn;
            appMeasurement$ConditionalUserProperty.mTriggerEventName = com_google_android_gms_internal_zzcfi.zzivo;
            if (com_google_android_gms_internal_zzcfi.zzivp != null) {
                appMeasurement$ConditionalUserProperty.mTimedOutEventName = com_google_android_gms_internal_zzcfi.zzivp.name;
                if (com_google_android_gms_internal_zzcfi.zzivp.zziwy != null) {
                    appMeasurement$ConditionalUserProperty.mTimedOutEventParams = com_google_android_gms_internal_zzcfi.zzivp.zziwy.zzayl();
                }
            }
            appMeasurement$ConditionalUserProperty.mTriggerTimeout = com_google_android_gms_internal_zzcfi.zzivq;
            if (com_google_android_gms_internal_zzcfi.zzivr != null) {
                appMeasurement$ConditionalUserProperty.mTriggeredEventName = com_google_android_gms_internal_zzcfi.zzivr.name;
                if (com_google_android_gms_internal_zzcfi.zzivr.zziwy != null) {
                    appMeasurement$ConditionalUserProperty.mTriggeredEventParams = com_google_android_gms_internal_zzcfi.zzivr.zziwy.zzayl();
                }
            }
            appMeasurement$ConditionalUserProperty.mTriggeredTimestamp = com_google_android_gms_internal_zzcfi.zzivl.zzjgn;
            appMeasurement$ConditionalUserProperty.mTimeToLive = com_google_android_gms_internal_zzcfi.zzivs;
            if (com_google_android_gms_internal_zzcfi.zzivt != null) {
                appMeasurement$ConditionalUserProperty.mExpiredEventName = com_google_android_gms_internal_zzcfi.zzivt.name;
                if (com_google_android_gms_internal_zzcfi.zzivt.zziwy != null) {
                    appMeasurement$ConditionalUserProperty.mExpiredEventParams = com_google_android_gms_internal_zzcfi.zzivt.zziwy.zzayl();
                }
            }
            arrayList.add(appMeasurement$ConditionalUserProperty);
        }
        return arrayList;
    }

    public final void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        zza(null, str, str2, bundle);
    }

    public final void clearConditionalUserPropertyAs(String str, String str2, String str3, Bundle bundle) {
        zzbq.zzgh(str);
        zzavw();
        zza(str, str2, str3, bundle);
    }

    public final Task<String> getAppInstanceId() {
        try {
            String zzazb = zzawn().zzazb();
            return zzazb != null ? Tasks.forResult(zzazb) : Tasks.call(zzawl().zzazh(), new zzciv(this));
        } catch (Exception e) {
            zzawm().zzayt().log("Failed to schedule task for getAppInstanceId");
            return Tasks.forException(e);
        }
    }

    public final List<AppMeasurement$ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        return zzk(null, str, str2);
    }

    public final List<AppMeasurement$ConditionalUserProperty> getConditionalUserPropertiesAs(String str, String str2, String str3) {
        zzbq.zzgh(str);
        zzavw();
        return zzk(str, str2, str3);
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final Map<String, Object> getUserProperties(String str, String str2, boolean z) {
        return zzb(null, str, str2, z);
    }

    public final Map<String, Object> getUserPropertiesAs(String str, String str2, String str3, boolean z) {
        zzbq.zzgh(str);
        zzavw();
        return zzb(str, str2, str3, z);
    }

    public final void registerOnMeasurementEventListener(AppMeasurement$OnEventListener appMeasurement$OnEventListener) {
        zzwu();
        zzbq.checkNotNull(appMeasurement$OnEventListener);
        if (!this.zzjee.add(appMeasurement$OnEventListener)) {
            zzawm().zzayt().log("OnEventListener already registered");
        }
    }

    public final void resetAnalyticsData() {
        zzawl().zzg(new zzcix(this));
    }

    public final void setConditionalUserProperty(AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        zzbq.checkNotNull(appMeasurement$ConditionalUserProperty);
        AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty2 = new AppMeasurement$ConditionalUserProperty(appMeasurement$ConditionalUserProperty);
        if (!TextUtils.isEmpty(appMeasurement$ConditionalUserProperty2.mAppId)) {
            zzawm().zzayt().log("Package name should be null when calling setConditionalUserProperty");
        }
        appMeasurement$ConditionalUserProperty2.mAppId = null;
        zza(appMeasurement$ConditionalUserProperty2);
    }

    public final void setConditionalUserPropertyAs(AppMeasurement$ConditionalUserProperty appMeasurement$ConditionalUserProperty) {
        zzbq.checkNotNull(appMeasurement$ConditionalUserProperty);
        zzbq.zzgh(appMeasurement$ConditionalUserProperty.mAppId);
        zzavw();
        zza(new AppMeasurement$ConditionalUserProperty(appMeasurement$ConditionalUserProperty));
    }

    @WorkerThread
    public final void setEventInterceptor(AppMeasurement$EventInterceptor appMeasurement$EventInterceptor) {
        zzut();
        zzwu();
        if (!(appMeasurement$EventInterceptor == null || appMeasurement$EventInterceptor == this.zzjed)) {
            zzbq.zza(this.zzjed == null, (Object) "EventInterceptor already set.");
        }
        this.zzjed = appMeasurement$EventInterceptor;
    }

    public final void setMeasurementEnabled(boolean z) {
        zzwu();
        zzawl().zzg(new zzcil(this, z));
    }

    public final void setMinimumSessionDuration(long j) {
        zzawl().zzg(new zzciq(this, j));
    }

    public final void setSessionTimeoutDuration(long j) {
        zzawl().zzg(new zzcir(this, j));
    }

    public final void unregisterOnMeasurementEventListener(AppMeasurement$OnEventListener appMeasurement$OnEventListener) {
        zzwu();
        zzbq.checkNotNull(appMeasurement$OnEventListener);
        if (!this.zzjee.remove(appMeasurement$OnEventListener)) {
            zzawm().zzayt().log("OnEventListener had not been registered");
        }
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, j, bundle, false, true, true, null);
    }

    public final void zza(String str, String str2, Bundle bundle, boolean z) {
        boolean z2 = this.zzjed == null || zzckn.zzkc(str2);
        zza(str, str2, bundle, true, z2, true, null);
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
    public final String zzazb() {
        return (String) this.zzjeg.get();
    }

    public final void zzb(String str, String str2, Object obj) {
        int i = 0;
        zzbq.zzgh(str);
        long currentTimeMillis = zzwh().currentTimeMillis();
        int zzjx = zzawi().zzjx(str2);
        String zza;
        if (zzjx != 0) {
            zzawi();
            zza = zzckn.zza(str2, 24, true);
            if (str2 != null) {
                i = str2.length();
            }
            this.zzitk.zzawi().zza(zzjx, "_ev", zza, i);
        } else if (obj != null) {
            zzjx = zzawi().zzl(str2, obj);
            if (zzjx != 0) {
                zzawi();
                zza = zzckn.zza(str2, 24, true);
                if ((obj instanceof String) || (obj instanceof CharSequence)) {
                    i = String.valueOf(obj).length();
                }
                this.zzitk.zzawi().zza(zzjx, "_ev", zza, i);
                return;
            }
            Object zzm = zzawi().zzm(str2, obj);
            if (zzm != null) {
                zza(str, str2, currentTimeMillis, zzm);
            }
        } else {
            zza(str, str2, currentTimeMillis, null);
        }
    }

    @Nullable
    final String zzbc(long j) {
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            zzawl().zzg(new zzciw(this, atomicReference));
            try {
                atomicReference.wait(j);
            } catch (InterruptedException e) {
                zzawm().zzayt().log("Interrupted waiting for app instance id");
                return null;
            }
        }
        return (String) atomicReference.get();
    }

    public final List<zzckk> zzbp(boolean z) {
        zzwu();
        zzawm().zzayw().log("Fetching user attributes (FE)");
        if (zzawl().zzazg()) {
            zzawm().zzayr().log("Cannot get all user properties from analytics worker thread");
            return Collections.emptyList();
        }
        zzawl();
        if (zzche.zzas()) {
            zzawm().zzayr().log("Cannot get all user properties from main thread");
            return Collections.emptyList();
        }
        AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.zzitk.zzawl().zzg(new zzciu(this, atomicReference, z));
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzawm().zzayt().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<zzckk> list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        zzawm().zzayt().log("Timed out waiting for get user properties");
        return Collections.emptyList();
    }

    public final void zzc(String str, String str2, Bundle bundle) {
        boolean z = this.zzjed == null || zzckn.zzkc(str2);
        zza(str, str2, bundle, true, z, false, null);
    }

    final void zzjj(@Nullable String str) {
        this.zzjeg.set(str);
    }

    public final /* bridge */ /* synthetic */ void zzut() {
        super.zzut();
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }
}

package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.zzcc;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzchj;
import com.google.android.gms.internal.zzckk;
import com.google.android.gms.internal.zzckn;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.List;
import java.util.Map;

@Keep
@Deprecated
public class AppMeasurement {
    @KeepForSdk
    public static final String CRASH_ORIGIN = "crash";
    @KeepForSdk
    public static final String FCM_ORIGIN = "fcm";
    private final zzchj zzitk;

    public AppMeasurement(zzchj com_google_android_gms_internal_zzchj) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzchj);
        this.zzitk = com_google_android_gms_internal_zzchj;
    }

    @Keep
    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WAKE_LOCK"})
    @Deprecated
    public static AppMeasurement getInstance(Context context) {
        return zzchj.zzdu(context).zzazn();
    }

    @Keep
    public void beginAdUnitExposure(@Size(min = 1) @NonNull String str) {
        this.zzitk.zzavy().beginAdUnitExposure(str);
    }

    @Keep
    protected void clearConditionalUserProperty(@Size(max = 24, min = 1) @NonNull String str, @Nullable String str2, @Nullable Bundle bundle) {
        this.zzitk.zzawa().clearConditionalUserProperty(str, str2, bundle);
    }

    @Keep
    protected void clearConditionalUserPropertyAs(@Size(min = 1) @NonNull String str, @Size(max = 24, min = 1) @NonNull String str2, @Nullable String str3, @Nullable Bundle bundle) {
        this.zzitk.zzawa().clearConditionalUserPropertyAs(str, str2, str3, bundle);
    }

    @Keep
    public void endAdUnitExposure(@Size(min = 1) @NonNull String str) {
        this.zzitk.zzavy().endAdUnitExposure(str);
    }

    @Keep
    public long generateEventId() {
        return this.zzitk.zzawi().zzbam();
    }

    @Keep
    @Nullable
    public String getAppInstanceId() {
        return this.zzitk.zzawa().zzazb();
    }

    @Keep
    @WorkerThread
    protected List<ConditionalUserProperty> getConditionalUserProperties(@Nullable String str, @Nullable @Size(max = 23, min = 1) String str2) {
        return this.zzitk.zzawa().getConditionalUserProperties(str, str2);
    }

    @Keep
    @WorkerThread
    protected List<ConditionalUserProperty> getConditionalUserPropertiesAs(@Size(min = 1) @NonNull String str, @Nullable String str2, @Nullable @Size(max = 23, min = 1) String str3) {
        return this.zzitk.zzawa().getConditionalUserPropertiesAs(str, str2, str3);
    }

    @Keep
    @Nullable
    public String getCurrentScreenClass() {
        zzb zzbad = this.zzitk.zzawe().zzbad();
        return zzbad != null ? zzbad.zzitq : null;
    }

    @Keep
    @Nullable
    public String getCurrentScreenName() {
        zzb zzbad = this.zzitk.zzawe().zzbad();
        return zzbad != null ? zzbad.zzitp : null;
    }

    @Keep
    @Nullable
    public String getGmpAppId() {
        try {
            return zzcc.zzaiv();
        } catch (IllegalStateException e) {
            this.zzitk.zzawm().zzayr().zzj("getGoogleAppId failed with exception", e);
            return null;
        }
    }

    @Keep
    @WorkerThread
    protected int getMaxUserProperties(@Size(min = 1) @NonNull String str) {
        this.zzitk.zzawa();
        zzbq.zzgh(str);
        return 25;
    }

    @Keep
    @WorkerThread
    protected Map<String, Object> getUserProperties(@Nullable String str, @Nullable @Size(max = 24, min = 1) String str2, boolean z) {
        return this.zzitk.zzawa().getUserProperties(str, str2, z);
    }

    @WorkerThread
    @KeepForSdk
    public Map<String, Object> getUserProperties(boolean z) {
        List<zzckk> zzbp = this.zzitk.zzawa().zzbp(z);
        Map<String, Object> arrayMap = new ArrayMap(zzbp.size());
        for (zzckk com_google_android_gms_internal_zzckk : zzbp) {
            arrayMap.put(com_google_android_gms_internal_zzckk.name, com_google_android_gms_internal_zzckk.getValue());
        }
        return arrayMap;
    }

    @Keep
    @WorkerThread
    protected Map<String, Object> getUserPropertiesAs(@Size(min = 1) @NonNull String str, @Nullable String str2, @Nullable @Size(max = 23, min = 1) String str3, boolean z) {
        return this.zzitk.zzawa().getUserPropertiesAs(str, str2, str3, z);
    }

    public final void logEvent(@Size(max = 40, min = 1) @NonNull String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (!"_iap".equals(str)) {
            int zzju = this.zzitk.zzawi().zzju(str);
            if (zzju != 0) {
                this.zzitk.zzawi();
                this.zzitk.zzawi().zza(zzju, "_ev", zzckn.zza(str, 40, true), str != null ? str.length() : 0);
                return;
            }
        }
        this.zzitk.zzawa().zza(SettingsJsonConstants.APP_KEY, str, bundle, true);
    }

    @Keep
    public void logEventInternal(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.zzitk.zzawa().zzc(str, str2, bundle);
    }

    @KeepForSdk
    public void logEventInternalNoInterceptor(String str, String str2, Bundle bundle, long j) {
        this.zzitk.zzawa().zza(str, str2, bundle == null ? new Bundle() : bundle, j);
    }

    @KeepForSdk
    public void registerOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzitk.zzawa().registerOnMeasurementEventListener(onEventListener);
    }

    @Keep
    public void registerOnScreenChangeCallback(@NonNull zza com_google_android_gms_measurement_AppMeasurement_zza) {
        this.zzitk.zzawe().registerOnScreenChangeCallback(com_google_android_gms_measurement_AppMeasurement_zza);
    }

    @Keep
    protected void setConditionalUserProperty(@NonNull ConditionalUserProperty conditionalUserProperty) {
        this.zzitk.zzawa().setConditionalUserProperty(conditionalUserProperty);
    }

    @Keep
    protected void setConditionalUserPropertyAs(@NonNull ConditionalUserProperty conditionalUserProperty) {
        this.zzitk.zzawa().setConditionalUserPropertyAs(conditionalUserProperty);
    }

    @WorkerThread
    @KeepForSdk
    public void setEventInterceptor(EventInterceptor eventInterceptor) {
        this.zzitk.zzawa().setEventInterceptor(eventInterceptor);
    }

    @Deprecated
    public void setMeasurementEnabled(boolean z) {
        this.zzitk.zzawa().setMeasurementEnabled(z);
    }

    public final void setMinimumSessionDuration(long j) {
        this.zzitk.zzawa().setMinimumSessionDuration(j);
    }

    public final void setSessionTimeoutDuration(long j) {
        this.zzitk.zzawa().setSessionTimeoutDuration(j);
    }

    public final void setUserProperty(@Size(max = 24, min = 1) @NonNull String str, @Nullable @Size(max = 36) String str2) {
        int zzjw = this.zzitk.zzawi().zzjw(str);
        if (zzjw != 0) {
            this.zzitk.zzawi();
            this.zzitk.zzawi().zza(zzjw, "_ev", zzckn.zza(str, 24, true), str != null ? str.length() : 0);
            return;
        }
        setUserPropertyInternal(SettingsJsonConstants.APP_KEY, str, str2);
    }

    @KeepForSdk
    public void setUserPropertyInternal(String str, String str2, Object obj) {
        this.zzitk.zzawa().zzb(str, str2, obj);
    }

    @KeepForSdk
    public void unregisterOnMeasurementEventListener(OnEventListener onEventListener) {
        this.zzitk.zzawa().unregisterOnMeasurementEventListener(onEventListener);
    }

    @Keep
    public void unregisterOnScreenChangeCallback(@NonNull zza com_google_android_gms_measurement_AppMeasurement_zza) {
        this.zzitk.zzawe().unregisterOnScreenChangeCallback(com_google_android_gms_measurement_AppMeasurement_zza);
    }
}

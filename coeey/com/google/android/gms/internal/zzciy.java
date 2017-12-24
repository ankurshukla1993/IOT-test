package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.text.TextUtils;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.facebook.share.internal.ShareConstants;

@TargetApi(14)
@MainThread
final class zzciy implements ActivityLifecycleCallbacks {
    private /* synthetic */ zzcik zzjeh;

    private zzciy(zzcik com_google_android_gms_internal_zzcik) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            this.zzjeh.zzawm().zzayx().log("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent != null) {
                Uri data = intent.getData();
                if (data != null && data.isHierarchical()) {
                    if (bundle == null) {
                        Bundle zzp = this.zzjeh.zzawi().zzp(data);
                        this.zzjeh.zzawi();
                        String str = zzckn.zzo(intent) ? "gs" : ReactScrollViewHelper.AUTO;
                        if (zzp != null) {
                            this.zzjeh.zzc(str, "_cmp", zzp);
                        }
                    }
                    CharSequence queryParameter = data.getQueryParameter("referrer");
                    if (!TextUtils.isEmpty(queryParameter)) {
                        Object obj = (queryParameter.contains("gclid") && (queryParameter.contains("utm_campaign") || queryParameter.contains("utm_source") || queryParameter.contains("utm_medium") || queryParameter.contains("utm_term") || queryParameter.contains("utm_content"))) ? 1 : null;
                        if (obj == null) {
                            this.zzjeh.zzawm().zzayw().log("Activity created with data 'referrer' param without gclid and at least one utm field");
                            return;
                        }
                        this.zzjeh.zzawm().zzayw().zzj("Activity created with referrer", queryParameter);
                        if (!TextUtils.isEmpty(queryParameter)) {
                            this.zzjeh.zzb(ReactScrollViewHelper.AUTO, "_ldl", queryParameter);
                        }
                    } else {
                        return;
                    }
                }
            }
        } catch (Throwable th) {
            this.zzjeh.zzawm().zzayr().zzj("Throwable caught in onActivityCreated", th);
        }
        zzciz zzawe = this.zzjeh.zzawe();
        if (bundle != null) {
            Bundle bundle2 = bundle.getBundle("com.google.firebase.analytics.screen_service");
            if (bundle2 != null) {
                zzcjc zzq = zzawe.zzq(activity);
                zzq.zzitr = bundle2.getLong(ShareConstants.WEB_DIALOG_PARAM_ID);
                zzq.zzitp = bundle2.getString("name");
                zzq.zzitq = bundle2.getString("referrer_name");
            }
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        this.zzjeh.zzawe().onActivityDestroyed(activity);
    }

    @MainThread
    public final void onActivityPaused(Activity activity) {
        this.zzjeh.zzawe().onActivityPaused(activity);
        zzcih zzawk = this.zzjeh.zzawk();
        zzawk.zzawl().zzg(new zzckg(zzawk, zzawk.zzwh().elapsedRealtime()));
    }

    @MainThread
    public final void onActivityResumed(Activity activity) {
        this.zzjeh.zzawe().onActivityResumed(activity);
        zzcih zzawk = this.zzjeh.zzawk();
        zzawk.zzawl().zzg(new zzckf(zzawk, zzawk.zzwh().elapsedRealtime()));
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zzjeh.zzawe().onActivitySaveInstanceState(activity, bundle);
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }
}

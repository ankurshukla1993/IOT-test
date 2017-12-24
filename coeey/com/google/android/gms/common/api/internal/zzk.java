package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.zzq;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzk implements ActivityLifecycleCallbacks, ComponentCallbacks2 {
    private static final zzk zzfll = new zzk();
    private boolean zzdqd = false;
    private final AtomicBoolean zzflm = new AtomicBoolean();
    private final AtomicBoolean zzfln = new AtomicBoolean();
    private final ArrayList<zzl> zzflo = new ArrayList();

    private zzk() {
    }

    public static void zza(Application application) {
        synchronized (zzfll) {
            if (!zzfll.zzdqd) {
                application.registerActivityLifecycleCallbacks(zzfll);
                application.registerComponentCallbacks(zzfll);
                zzfll.zzdqd = true;
            }
        }
    }

    public static zzk zzagp() {
        return zzfll;
    }

    private final void zzbe(boolean z) {
        synchronized (zzfll) {
            ArrayList arrayList = this.zzflo;
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                ((zzl) obj).zzbe(z);
            }
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        boolean compareAndSet = this.zzflm.compareAndSet(true, false);
        this.zzfln.set(true);
        if (compareAndSet) {
            zzbe(false);
        }
    }

    public final void onActivityDestroyed(Activity activity) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
        boolean compareAndSet = this.zzflm.compareAndSet(true, false);
        this.zzfln.set(true);
        if (compareAndSet) {
            zzbe(false);
        }
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onActivityStarted(Activity activity) {
    }

    public final void onActivityStopped(Activity activity) {
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int i) {
        if (i == 20 && this.zzflm.compareAndSet(false, true)) {
            this.zzfln.set(true);
            zzbe(true);
        }
    }

    public final void zza(zzl com_google_android_gms_common_api_internal_zzl) {
        synchronized (zzfll) {
            this.zzflo.add(com_google_android_gms_common_api_internal_zzl);
        }
    }

    public final boolean zzagq() {
        return this.zzflm.get();
    }

    @TargetApi(16)
    public final boolean zzbd(boolean z) {
        if (!this.zzfln.get()) {
            if (!zzq.zzalw()) {
                return true;
            }
            RunningAppProcessInfo runningAppProcessInfo = new RunningAppProcessInfo();
            ActivityManager.getMyMemoryState(runningAppProcessInfo);
            if (!this.zzfln.getAndSet(true) && runningAppProcessInfo.importance > 100) {
                this.zzflm.set(true);
            }
        }
        return this.zzflm.get();
    }
}

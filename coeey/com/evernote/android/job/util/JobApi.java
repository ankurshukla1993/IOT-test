package com.evernote.android.job.util;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.evernote.android.job.JobManager;
import com.evernote.android.job.JobProxy;
import com.evernote.android.job.gcm.JobProxyGcm;
import com.evernote.android.job.v14.JobProxy14;
import com.evernote.android.job.v14.PlatformAlarmReceiver;
import com.evernote.android.job.v14.PlatformAlarmService;
import com.evernote.android.job.v19.JobProxy19;
import com.evernote.android.job.v21.JobProxy21;
import com.evernote.android.job.v21.PlatformJobService;
import com.evernote.android.job.v24.JobProxy24;
import java.util.List;

public enum JobApi {
    V_24(true, false),
    V_21(true, true),
    V_19(true, true),
    V_14(false, true),
    GCM(true, false);
    
    private static final String JOB_SCHEDULER_PERMISSION = "android.permission.BIND_JOB_SERVICE";
    private static volatile boolean forceAllowApi14;
    private volatile JobProxy mCachedProxy;
    private final boolean mFlexSupport;
    private final boolean mSupportsExecutionWindow;

    static {
        forceAllowApi14 = false;
    }

    @Deprecated
    public static void setForceAllowApi14(boolean forceAllowApi14) {
        forceAllowApi14 = forceAllowApi14;
    }

    @Deprecated
    public static boolean isForceAllowApi14() {
        return forceAllowApi14;
    }

    private JobApi(boolean supportsExecutionWindow, boolean flexSupport) {
        this.mSupportsExecutionWindow = supportsExecutionWindow;
        this.mFlexSupport = flexSupport;
    }

    public boolean supportsExecutionWindow() {
        return this.mSupportsExecutionWindow;
    }

    public boolean isFlexSupport() {
        return this.mFlexSupport;
    }

    public boolean isSupported(Context context) {
        boolean z = false;
        switch (1.$SwitchMap$com$evernote$android$job$util$JobApi[ordinal()]) {
            case 1:
                if (VERSION.SDK_INT < 24 || !isServiceEnabledAndHasPermission(context, PlatformJobService.class, JOB_SCHEDULER_PERMISSION)) {
                    return false;
                }
                return true;
            case 2:
                if (VERSION.SDK_INT < 21 || !isServiceEnabledAndHasPermission(context, PlatformJobService.class, JOB_SCHEDULER_PERMISSION)) {
                    return false;
                }
                return true;
            case 3:
                if (VERSION.SDK_INT >= 19 && isServiceEnabled(context, PlatformAlarmService.class) && isBroadcastEnabled(context, PlatformAlarmReceiver.class)) {
                    return true;
                }
                return false;
            case 4:
                if (forceAllowApi14 || (isServiceEnabled(context, PlatformAlarmService.class) && isBroadcastEnabled(context, PlatformAlarmReceiver.class))) {
                    z = true;
                }
                return z;
            case 5:
                return GcmAvailableHelper.isGcmApiSupported(context);
            default:
                throw new IllegalStateException("not implemented");
        }
    }

    @NonNull
    public JobProxy createProxy(Context context) {
        switch (1.$SwitchMap$com$evernote$android$job$util$JobApi[ordinal()]) {
            case 1:
                return new JobProxy24(context);
            case 2:
                return new JobProxy21(context);
            case 3:
                return new JobProxy19(context);
            case 4:
                return new JobProxy14(context);
            case 5:
                return new JobProxyGcm(context);
            default:
                throw new IllegalStateException("not implemented");
        }
    }

    @NonNull
    public synchronized JobProxy getCachedProxy(Context context) {
        if (this.mCachedProxy == null) {
            this.mCachedProxy = createProxy(context);
        }
        return this.mCachedProxy;
    }

    public synchronized void invalidateCachedProxy() {
        this.mCachedProxy = null;
    }

    private boolean isServiceEnabled(@NonNull Context context, @NonNull Class<? extends Service> clazz) {
        try {
            List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentServices(new Intent(context, clazz), 0);
            if (resolveInfos == null || resolveInfos.isEmpty()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isServiceEnabledAndHasPermission(@NonNull Context context, @NonNull Class<? extends Service> clazz, @NonNull String permission) {
        try {
            List<ResolveInfo> resolveInfos = context.getPackageManager().queryIntentServices(new Intent(context, clazz), 0);
            if (resolveInfos == null || resolveInfos.isEmpty()) {
                return false;
            }
            for (ResolveInfo info : resolveInfos) {
                if (info.serviceInfo != null && permission.equals(info.serviceInfo.permission)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isBroadcastEnabled(@NonNull Context context, @NonNull Class<? extends BroadcastReceiver> clazz) {
        try {
            List<ResolveInfo> resolveInfos = context.getPackageManager().queryBroadcastReceivers(new Intent(context, clazz), 0);
            if (resolveInfos == null || resolveInfos.isEmpty()) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Deprecated
    @NonNull
    public static JobApi getDefault(Context context) {
        return getDefault(context, JobManager.instance().getConfig().isGcmApiEnabled());
    }

    @NonNull
    public static JobApi getDefault(Context context, boolean gcmEnabled) {
        if (V_24.isSupported(context)) {
            return V_24;
        }
        if (V_21.isSupported(context)) {
            return V_21;
        }
        if (gcmEnabled && GCM.isSupported(context)) {
            return GCM;
        }
        if (V_19.isSupported(context)) {
            return V_19;
        }
        return V_14;
    }
}

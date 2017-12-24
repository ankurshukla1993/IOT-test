package com.evernote.android.job.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.evernote.android.job.gcm.JobProxyGcm;
import com.evernote.android.job.gcm.PlatformGcmService;
import com.google.android.gms.common.GoogleApiAvailability;
import java.util.List;
import net.vrallev.android.cat.CatLog;

final class GcmAvailableHelper {
    private static final String ACTION_TASK_READY = "com.google.android.gms.gcm.ACTION_TASK_READY";
    private static final CatLog CAT = new JobCat("GcmAvailableHelper");
    private static final boolean GCM_IN_CLASSPATH;
    private static final String GCM_PERMISSION = "com.google.android.gms.permission.BIND_NETWORK_TASK_SERVICE";
    private static boolean checkedServiceEnabled;
    private static int gcmServiceAvailable = -1;

    static {
        boolean gcmInClasspath;
        try {
            Class.forName("com.google.android.gms.gcm.GcmNetworkManager");
            gcmInClasspath = true;
        } catch (Throwable th) {
            gcmInClasspath = false;
        }
        GCM_IN_CLASSPATH = gcmInClasspath;
    }

    public static boolean isGcmApiSupported(Context context) {
        try {
            if (!checkedServiceEnabled) {
                checkedServiceEnabled = true;
                setServiceEnabled(context, GCM_IN_CLASSPATH);
            }
            if (GCM_IN_CLASSPATH && GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == 0 && isGcmServiceRegistered(context) == 0) {
                return true;
            }
            return false;
        } catch (Throwable t) {
            CAT.w(t.getMessage());
            return false;
        }
    }

    private static int isGcmServiceRegistered(Context context) {
        if (gcmServiceAvailable < 0) {
            synchronized (JobApi.class) {
                if (gcmServiceAvailable < 0) {
                    int i;
                    if (hasPermission(context.getPackageManager().queryIntentServices(new Intent(context, PlatformGcmService.class), 0))) {
                        Intent intent = new Intent(ACTION_TASK_READY);
                        intent.setPackage(context.getPackageName());
                        if (hasPermission(context.getPackageManager().queryIntentServices(intent, 0))) {
                            gcmServiceAvailable = 0;
                        } else {
                            gcmServiceAvailable = 1;
                            i = gcmServiceAvailable;
                            return i;
                        }
                    }
                    gcmServiceAvailable = 1;
                    i = gcmServiceAvailable;
                    return i;
                }
            }
        }
        return gcmServiceAvailable;
    }

    private static boolean hasPermission(List<ResolveInfo> resolveInfos) {
        if (resolveInfos == null || resolveInfos.isEmpty()) {
            return false;
        }
        for (ResolveInfo info : resolveInfos) {
            if (info.serviceInfo != null && GCM_PERMISSION.equals(info.serviceInfo.permission) && info.serviceInfo.exported) {
                return true;
            }
        }
        return false;
    }

    private static void setServiceEnabled(Context context, boolean enabled) {
        try {
            PackageManager packageManager = context.getPackageManager();
            ComponentName component = new ComponentName(context, JobProxyGcm.class.getPackage().getName() + ".PlatformGcmService");
            switch (packageManager.getComponentEnabledSetting(component)) {
                case 0:
                case 2:
                    if (enabled) {
                        packageManager.setComponentEnabledSetting(component, 1, 1);
                        CAT.i("GCM service enabled");
                        return;
                    }
                    return;
                case 1:
                    if (!enabled) {
                        packageManager.setComponentEnabledSetting(component, 2, 1);
                        CAT.i("GCM service disabled");
                        return;
                    }
                    return;
                default:
                    return;
            }
        } catch (Throwable t) {
            CAT.e(t.getMessage());
        }
        CAT.e(t.getMessage());
    }

    private GcmAvailableHelper() {
    }
}

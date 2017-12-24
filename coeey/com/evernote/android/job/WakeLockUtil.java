package com.evernote.android.job;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import com.evernote.android.job.util.JobCat;
import com.evernote.android.job.util.JobUtil;
import java.util.concurrent.TimeUnit;
import net.vrallev.android.cat.CatLog;

final class WakeLockUtil {
    private static final SparseArray<WakeLock> ACTIVE_WAKE_LOCKS = new SparseArray();
    private static final CatLog CAT = new JobCat("WakeLockUtil");
    private static final String EXTRA_WAKE_LOCK_ID = "com.evernote.android.job.wakelockid";
    private static int nextId = 1;

    private WakeLockUtil() {
    }

    @Nullable
    public static WakeLock acquireWakeLock(@NonNull Context context, @NonNull String tag, long timeoutMillis) {
        WakeLock wakeLock = ((PowerManager) context.getSystemService("power")).newWakeLock(1, tag);
        wakeLock.setReferenceCounted(false);
        return acquireWakeLock(context, wakeLock, timeoutMillis) ? wakeLock : null;
    }

    public static boolean acquireWakeLock(@NonNull Context context, @Nullable WakeLock wakeLock, long timeoutMillis) {
        if (!(wakeLock == null || wakeLock.isHeld() || !JobUtil.hasWakeLockPermission(context))) {
            try {
                wakeLock.acquire(timeoutMillis);
                return true;
            } catch (Exception e) {
                CAT.e(e);
            }
        }
        return false;
    }

    public static void releaseWakeLock(@Nullable WakeLock wakeLock) {
        if (wakeLock != null) {
            try {
                if (wakeLock.isHeld()) {
                    wakeLock.release();
                }
            } catch (Exception e) {
                CAT.e(e);
            }
        }
    }

    public static ComponentName startWakefulService(Context context, Intent intent) {
        ComponentName comp;
        synchronized (ACTIVE_WAKE_LOCKS) {
            int id = nextId;
            nextId++;
            if (nextId <= 0) {
                nextId = 1;
            }
            intent.putExtra(EXTRA_WAKE_LOCK_ID, id);
            comp = context.startService(intent);
            if (comp == null) {
                comp = null;
            } else {
                WakeLock wakeLock = acquireWakeLock(context, "wake:" + comp.flattenToShortString(), TimeUnit.MINUTES.toMillis(3));
                if (wakeLock != null) {
                    ACTIVE_WAKE_LOCKS.put(id, wakeLock);
                }
            }
        }
        return comp;
    }

    public static boolean completeWakefulIntent(Intent intent) {
        boolean z = false;
        if (intent != null) {
            int id = intent.getIntExtra(EXTRA_WAKE_LOCK_ID, 0);
            if (id != 0) {
                synchronized (ACTIVE_WAKE_LOCKS) {
                    releaseWakeLock((WakeLock) ACTIVE_WAKE_LOCKS.get(id));
                    ACTIVE_WAKE_LOCKS.remove(id);
                    z = true;
                }
            }
        }
        return z;
    }
}

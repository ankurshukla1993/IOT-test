package com.evernote.android.job.util;

import android.content.Context;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import net.vrallev.android.cat.CatLog;

public final class JobUtil {
    private static final CatLog CAT = new JobCat("JobUtil");
    private static final ThreadLocal<SimpleDateFormat> FORMAT = new ThreadLocal();
    private static final long ONE_DAY = TimeUnit.DAYS.toMillis(1);

    private JobUtil() {
    }

    public static String timeToString(long timeMs) {
        SimpleDateFormat simpleDateFormat = (SimpleDateFormat) FORMAT.get();
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat(DateTimeFormat.TIME_PATTERN_1, Locale.US);
            FORMAT.set(simpleDateFormat);
        }
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        String result = simpleDateFormat.format(new Date(timeMs));
        long days = timeMs / ONE_DAY;
        if (days == 1) {
            return result + " (+1 day)";
        }
        if (days > 1) {
            return result + " (+" + days + " days)";
        }
        return result;
    }

    public static boolean hasBootPermission(Context context) {
        return hasPermission(context, "android.permission.RECEIVE_BOOT_COMPLETED", 0);
    }

    public static boolean hasWakeLockPermission(Context context) {
        return hasPermission(context, "android.permission.WAKE_LOCK", 0);
    }

    private static boolean hasPermission(Context context, String permission, int repeatCount) {
        try {
            if (context.getPackageManager().checkPermission(permission, context.getPackageName()) == 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            CAT.e(e);
            if (repeatCount >= 1 || !hasPermission(context.getApplicationContext(), permission, repeatCount + 1)) {
                return false;
            }
            return true;
        }
    }
}

package com.facebook.common.logging;

public class FLog {
    public static final int ASSERT = 7;
    public static final int DEBUG = 3;
    public static final int ERROR = 6;
    public static final int INFO = 4;
    public static final int VERBOSE = 2;
    public static final int WARN = 5;
    private static LoggingDelegate sHandler = FLogDefaultLoggingDelegate.getInstance();

    public static void setLoggingDelegate(LoggingDelegate delegate) {
        if (delegate == null) {
            throw new IllegalArgumentException();
        }
        sHandler = delegate;
    }

    public static boolean isLoggable(int level) {
        return sHandler.isLoggable(level);
    }

    public static void setMinimumLoggingLevel(int level) {
        sHandler.setMinimumLoggingLevel(level);
    }

    public static int getMinimumLoggingLevel() {
        return sHandler.getMinimumLoggingLevel();
    }

    public static void m139v(String tag, String msg) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(tag, msg);
        }
    }

    public static void m140v(String tag, String msg, Object arg1) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(tag, formatString(msg, arg1));
        }
    }

    public static void m141v(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(tag, formatString(msg, arg1, arg2));
        }
    }

    public static void m142v(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void m143v(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void m131v(Class<?> cls, String msg) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(getTag(cls), msg);
        }
    }

    public static void m132v(Class<?> cls, String msg, Object arg1) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(getTag(cls), formatString(msg, arg1));
        }
    }

    public static void m133v(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    public static void m134v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (isLoggable(2)) {
            m131v((Class) cls, formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void m135v(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void m145v(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(tag, formatString(msg, args));
        }
    }

    public static void m146v(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2965v(tag, formatString(msg, args), tr);
        }
    }

    public static void m137v(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2964v(getTag(cls), formatString(msg, args));
        }
    }

    public static void m138v(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2965v(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void m144v(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2965v(tag, msg, tr);
        }
    }

    public static void m136v(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(2)) {
            sHandler.mo2965v(getTag(cls), msg, tr);
        }
    }

    public static void m99d(String tag, String msg) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(tag, msg);
        }
    }

    public static void m100d(String tag, String msg, Object arg1) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(tag, formatString(msg, arg1));
        }
    }

    public static void m101d(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(tag, formatString(msg, arg1, arg2));
        }
    }

    public static void m102d(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void m103d(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void m91d(Class<?> cls, String msg) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(getTag(cls), msg);
        }
    }

    public static void m92d(Class<?> cls, String msg, Object arg1) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(getTag(cls), formatString(msg, arg1));
        }
    }

    public static void m93d(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    public static void m94d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(getTag(cls), formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void m95d(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void m105d(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            m99d(tag, formatString(msg, args));
        }
    }

    public static void m106d(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            m104d(tag, formatString(msg, args), tr);
        }
    }

    public static void m97d(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2954d(getTag(cls), formatString(msg, args));
        }
    }

    public static void m98d(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2955d(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void m104d(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2955d(tag, msg, tr);
        }
    }

    public static void m96d(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(3)) {
            sHandler.mo2955d(getTag(cls), msg, tr);
        }
    }

    public static void m123i(String tag, String msg) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(tag, msg);
        }
    }

    public static void m124i(String tag, String msg, Object arg1) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(tag, formatString(msg, arg1));
        }
    }

    public static void m125i(String tag, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(tag, formatString(msg, arg1, arg2));
        }
    }

    public static void m126i(String tag, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(tag, formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void m127i(String tag, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(tag, formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void m115i(Class<?> cls, String msg) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(getTag(cls), msg);
        }
    }

    public static void m116i(Class<?> cls, String msg, Object arg1) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(getTag(cls), formatString(msg, arg1));
        }
    }

    public static void m117i(Class<?> cls, String msg, Object arg1, Object arg2) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(getTag(cls), formatString(msg, arg1, arg2));
        }
    }

    public static void m118i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(getTag(cls), formatString(msg, arg1, arg2, arg3));
        }
    }

    public static void m119i(Class<?> cls, String msg, Object arg1, Object arg2, Object arg3, Object arg4) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(getTag(cls), formatString(msg, arg1, arg2, arg3, arg4));
        }
    }

    public static void m129i(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(tag, formatString(msg, args));
        }
    }

    public static void m130i(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2960i(tag, formatString(msg, args), tr);
        }
    }

    public static void m121i(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2959i(getTag(cls), formatString(msg, args));
        }
    }

    public static void m122i(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (isLoggable(4)) {
            sHandler.mo2960i(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void m128i(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2960i(tag, msg, tr);
        }
    }

    public static void m120i(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(4)) {
            sHandler.mo2960i(getTag(cls), msg, tr);
        }
    }

    public static void m151w(String tag, String msg) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo2966w(tag, msg);
        }
    }

    public static void m147w(Class<?> cls, String msg) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo2966w(getTag(cls), msg);
        }
    }

    public static void m153w(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo2966w(tag, formatString(msg, args));
        }
    }

    public static void m154w(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo2967w(tag, formatString(msg, args), tr);
        }
    }

    public static void m149w(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo2966w(getTag(cls), formatString(msg, args));
        }
    }

    public static void m150w(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (isLoggable(5)) {
            m148w((Class) cls, formatString(msg, args), tr);
        }
    }

    public static void m152w(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo2967w(tag, msg, tr);
        }
    }

    public static void m148w(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(5)) {
            sHandler.mo2967w(getTag(cls), msg, tr);
        }
    }

    public static void m111e(String tag, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo2956e(tag, msg);
        }
    }

    public static void m107e(Class<?> cls, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo2956e(getTag(cls), msg);
        }
    }

    public static void m113e(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo2956e(tag, formatString(msg, args));
        }
    }

    public static void m114e(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo2957e(tag, formatString(msg, args), tr);
        }
    }

    public static void m109e(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo2956e(getTag(cls), formatString(msg, args));
        }
    }

    public static void m110e(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo2957e(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void m112e(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo2957e(tag, msg, tr);
        }
    }

    public static void m108e(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.mo2957e(getTag(cls), msg, tr);
        }
    }

    public static void wtf(String tag, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, msg);
        }
    }

    public static void wtf(Class<?> cls, String msg) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), msg);
        }
    }

    public static void wtf(String tag, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, formatString(msg, args));
        }
    }

    public static void wtf(String tag, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, formatString(msg, args), tr);
        }
    }

    public static void wtf(Class<?> cls, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), formatString(msg, args));
        }
    }

    public static void wtf(Class<?> cls, Throwable tr, String msg, Object... args) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), formatString(msg, args), tr);
        }
    }

    public static void wtf(String tag, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(tag, msg, tr);
        }
    }

    public static void wtf(Class<?> cls, String msg, Throwable tr) {
        if (sHandler.isLoggable(6)) {
            sHandler.wtf(getTag(cls), msg, tr);
        }
    }

    private static String formatString(String str, Object... args) {
        return String.format(null, str, args);
    }

    private static String getTag(Class<?> clazz) {
        return clazz.getSimpleName();
    }
}

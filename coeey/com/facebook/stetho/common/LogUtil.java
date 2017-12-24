package com.facebook.stetho.common;

import java.util.Locale;

public class LogUtil {
    private static final String TAG = "stetho";

    public static void m190e(String format, Object... args) {
        m189e(format(format, args));
    }

    public static void m192e(Throwable t, String format, Object... args) {
        m191e(t, format(format, args));
    }

    public static void m189e(String message) {
        if (isLoggable(6)) {
            LogRedirector.m177e(TAG, message);
        }
    }

    public static void m191e(Throwable t, String message) {
        if (isLoggable(6)) {
            LogRedirector.m178e(TAG, message, t);
        }
    }

    public static void m202w(String format, Object... args) {
        m201w(format(format, args));
    }

    public static void m204w(Throwable t, String format, Object... args) {
        m203w(t, format(format, args));
    }

    public static void m201w(String message) {
        if (isLoggable(5)) {
            LogRedirector.m183w(TAG, message);
        }
    }

    public static void m203w(Throwable t, String message) {
        if (isLoggable(5)) {
            LogRedirector.m184w(TAG, message, t);
        }
    }

    public static void m194i(String format, Object... args) {
        m193i(format(format, args));
    }

    public static void m196i(Throwable t, String format, Object... args) {
        m195i(t, format(format, args));
    }

    public static void m193i(String message) {
        if (isLoggable(4)) {
            LogRedirector.m179i(TAG, message);
        }
    }

    public static void m195i(Throwable t, String message) {
        if (isLoggable(4)) {
            LogRedirector.m180i(TAG, message, t);
        }
    }

    public static void m186d(String format, Object... args) {
        m185d(format(format, args));
    }

    public static void m188d(Throwable t, String format, Object... args) {
        m187d(t, format(format, args));
    }

    public static void m185d(String message) {
        if (isLoggable(3)) {
            LogRedirector.m175d(TAG, message);
        }
    }

    public static void m187d(Throwable t, String message) {
        if (isLoggable(3)) {
            LogRedirector.m176d(TAG, message, t);
        }
    }

    public static void m198v(String format, Object... args) {
        m197v(format(format, args));
    }

    public static void m200v(Throwable t, String format, Object... args) {
        m199v(t, format(format, args));
    }

    public static void m197v(String message) {
        if (isLoggable(2)) {
            LogRedirector.m181v(TAG, message);
        }
    }

    public static void m199v(Throwable t, String message) {
        if (isLoggable(2)) {
            LogRedirector.m182v(TAG, message, t);
        }
    }

    private static String format(String format, Object... args) {
        return String.format(Locale.US, format, args);
    }

    public static boolean isLoggable(int priority) {
        switch (priority) {
            case 5:
            case 6:
                return true;
            default:
                return LogRedirector.isLoggable(TAG, priority);
        }
    }
}

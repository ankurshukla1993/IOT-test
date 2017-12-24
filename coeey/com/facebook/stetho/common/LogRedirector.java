package com.facebook.stetho.common;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;

public class LogRedirector {
    private static volatile Logger sLogger;

    public interface Logger {
        boolean isLoggable(String str, int i);

        void log(int i, String str, String str2);
    }

    public static void setLogger(Logger logger) {
        Util.throwIfNull(logger);
        Util.throwIfNotNull(sLogger);
        sLogger = logger;
    }

    public static void m178e(String tag, String message, Throwable t) {
        m177e(tag, message + "\n" + formatThrowable(t));
    }

    public static void m177e(String tag, String message) {
        log(6, tag, message);
    }

    public static void m184w(String tag, String message, Throwable t) {
        m183w(tag, message + "\n" + formatThrowable(t));
    }

    public static void m183w(String tag, String message) {
        log(5, tag, message);
    }

    public static void m180i(String tag, String message, Throwable t) {
        m179i(tag, message + "\n" + formatThrowable(t));
    }

    public static void m179i(String tag, String message) {
        log(4, tag, message);
    }

    public static void m176d(String tag, String message, Throwable t) {
        m175d(tag, message + "\n" + formatThrowable(t));
    }

    public static void m175d(String tag, String message) {
        log(3, tag, message);
    }

    public static void m182v(String tag, String message, Throwable t) {
        m181v(tag, message + "\n" + formatThrowable(t));
    }

    public static void m181v(String tag, String message) {
        log(2, tag, message);
    }

    private static String formatThrowable(Throwable t) {
        StringWriter buf = new StringWriter();
        PrintWriter writer = new PrintWriter(buf);
        t.printStackTrace();
        writer.flush();
        return buf.toString();
    }

    private static void log(int priority, String tag, String message) {
        Logger logger = sLogger;
        if (logger != null) {
            logger.log(priority, tag, message);
        } else {
            Log.println(priority, tag, message);
        }
    }

    public static boolean isLoggable(String tag, int priority) {
        Logger logger = sLogger;
        if (logger != null) {
            return logger.isLoggable(tag, priority);
        }
        return Log.isLoggable(tag, priority);
    }
}

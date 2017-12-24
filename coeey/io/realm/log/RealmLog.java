package io.realm.log;

import android.util.Log;
import java.util.Locale;
import javax.annotation.Nullable;

public final class RealmLog {
    private static String REALM_JAVA_TAG = "REALM_JAVA";

    private static native void nativeAddLogger(RealmLogger realmLogger);

    private static native void nativeClearLoggers();

    static native void nativeCloseCoreLoggerBridge(long j);

    static native long nativeCreateCoreLoggerBridge(String str);

    private static native int nativeGetLogLevel();

    private static native void nativeLog(int i, String str, @Nullable Throwable th, @Nullable String str2);

    static native void nativeLogToCoreLoggerBridge(long j, int i, String str);

    private static native void nativeRegisterDefaultLogger();

    private static native void nativeRemoveLogger(RealmLogger realmLogger);

    private static native void nativeSetLogLevel(int i);

    public static void add(RealmLogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("A non-null logger has to be provided");
        }
        nativeAddLogger(logger);
    }

    public static void setLevel(int level) {
        if (level < 1 || level > 8) {
            throw new IllegalArgumentException("Invalid log level: " + level);
        }
        nativeSetLogLevel(level);
    }

    public static int getLevel() {
        return nativeGetLogLevel();
    }

    public static boolean remove(RealmLogger logger) {
        if (logger == null) {
            throw new IllegalArgumentException("A non-null logger has to be provided");
        }
        nativeRemoveLogger(logger);
        return true;
    }

    public static void clear() {
        nativeClearLoggers();
    }

    public static void registerDefaultLogger() {
        nativeRegisterDefaultLogger();
    }

    public static void trace(Throwable throwable) {
        trace(throwable, null, new Object[0]);
    }

    public static void trace(String message, Object... args) {
        trace(null, message, args);
    }

    public static void trace(@Nullable Throwable throwable, @Nullable String message, Object... args) {
        log(2, throwable, message, args);
    }

    public static void debug(@Nullable Throwable throwable) {
        debug(throwable, null, new Object[0]);
    }

    public static void debug(String message, Object... args) {
        debug(null, message, args);
    }

    public static void debug(@Nullable Throwable throwable, @Nullable String message, Object... args) {
        log(3, throwable, message, args);
    }

    public static void info(Throwable throwable) {
        info(throwable, null, new Object[0]);
    }

    public static void info(String message, Object... args) {
        info(null, message, args);
    }

    public static void info(@Nullable Throwable throwable, @Nullable String message, Object... args) {
        log(4, throwable, message, args);
    }

    public static void warn(Throwable throwable) {
        warn(throwable, null, new Object[0]);
    }

    public static void warn(String message, Object... args) {
        warn(null, message, args);
    }

    public static void warn(@Nullable Throwable throwable, @Nullable String message, Object... args) {
        log(5, throwable, message, args);
    }

    public static void error(Throwable throwable) {
        error(throwable, null, new Object[0]);
    }

    public static void error(String message, Object... args) {
        error(null, message, args);
    }

    public static void error(@Nullable Throwable throwable, @Nullable String message, Object... args) {
        log(6, throwable, message, args);
    }

    public static void fatal(Throwable throwable) {
        fatal(throwable, null, new Object[0]);
    }

    public static void fatal(String message, Object... args) {
        fatal(null, message, args);
    }

    public static void fatal(@Nullable Throwable throwable, @Nullable String message, Object... args) {
        log(7, throwable, message, args);
    }

    private static void log(int level, @Nullable Throwable throwable, @Nullable String message, @Nullable Object... args) {
        if (level >= getLevel()) {
            StringBuilder stringBuilder = new StringBuilder();
            if (!(message == null || args == null || args.length <= 0)) {
                message = String.format(Locale.US, message, args);
            }
            if (throwable != null) {
                stringBuilder.append(Log.getStackTraceString(throwable));
            }
            if (message != null) {
                if (throwable != null) {
                    stringBuilder.append("\n");
                }
                stringBuilder.append(message);
            }
            nativeLog(level, REALM_JAVA_TAG, throwable, stringBuilder.toString());
        }
    }
}

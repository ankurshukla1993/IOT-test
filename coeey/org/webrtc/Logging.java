package org.webrtc;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
    private static final Logger fallbackLogger = Logger.getLogger("org.webrtc.Logging");
    private static volatile boolean loggingEnabled;
    private static volatile boolean nativeLibLoaded = true;
    private static volatile boolean tracingEnabled;

    public enum Severity {
        LS_SENSITIVE,
        LS_VERBOSE,
        LS_INFO,
        LS_WARNING,
        LS_ERROR,
        LS_NONE
    }

    public enum TraceLevel {
        TRACE_NONE(0),
        TRACE_STATEINFO(1),
        TRACE_WARNING(2),
        TRACE_ERROR(4),
        TRACE_CRITICAL(8),
        TRACE_APICALL(16),
        TRACE_DEFAULT(255),
        TRACE_MODULECALL(32),
        TRACE_MEMORY(256),
        TRACE_TIMER(512),
        TRACE_STREAM(1024),
        TRACE_DEBUG(2048),
        TRACE_INFO(4096),
        TRACE_TERSEINFO(8192),
        TRACE_ALL(65535);
        
        public final int level;

        private TraceLevel(int level) {
            this.level = level;
        }
    }

    private static native void nativeEnableLogThreads();

    private static native void nativeEnableLogTimeStamps();

    private static native void nativeEnableLogToDebugOutput(int i);

    private static native void nativeEnableTracing(String str, int i);

    private static native void nativeLog(int i, String str, String str2);

    static {
        try {
            System.loadLibrary("jingle_peerconnection_so");
        } catch (UnsatisfiedLinkError t) {
            fallbackLogger.setLevel(Level.ALL);
            fallbackLogger.log(Level.WARNING, "Failed to load jingle_peerconnection_so: ", t);
        }
    }

    public static void enableLogThreads() {
        if (nativeLibLoaded) {
            nativeEnableLogThreads();
        } else {
            fallbackLogger.log(Level.WARNING, "Cannot enable log thread because native lib not loaded.");
        }
    }

    public static void enableLogTimeStamps() {
        if (nativeLibLoaded) {
            nativeEnableLogTimeStamps();
        } else {
            fallbackLogger.log(Level.WARNING, "Cannot enable log timestamps because native lib not loaded.");
        }
    }

    public static synchronized void enableTracing(String path, EnumSet<TraceLevel> levels) {
        synchronized (Logging.class) {
            if (!nativeLibLoaded) {
                fallbackLogger.log(Level.WARNING, "Cannot enable tracing because native lib not loaded.");
            } else if (!tracingEnabled) {
                int nativeLevel = 0;
                Iterator it = levels.iterator();
                while (it.hasNext()) {
                    nativeLevel |= ((TraceLevel) it.next()).level;
                }
                nativeEnableTracing(path, nativeLevel);
                tracingEnabled = true;
            }
        }
    }

    public static synchronized void enableLogToDebugOutput(Severity severity) {
        synchronized (Logging.class) {
            if (nativeLibLoaded) {
                nativeEnableLogToDebugOutput(severity.ordinal());
                loggingEnabled = true;
            } else {
                fallbackLogger.log(Level.WARNING, "Cannot enable logging because native lib not loaded.");
            }
        }
    }

    public static void log(Severity severity, String tag, String message) {
        if (loggingEnabled) {
            nativeLog(severity.ordinal(), tag, message);
            return;
        }
        Level level;
        switch (severity) {
            case LS_ERROR:
                level = Level.SEVERE;
                break;
            case LS_WARNING:
                level = Level.WARNING;
                break;
            case LS_INFO:
                level = Level.INFO;
                break;
            default:
                level = Level.FINE;
                break;
        }
        fallbackLogger.log(level, tag + ": " + message);
    }

    public static void m2187d(String tag, String message) {
        log(Severity.LS_INFO, tag, message);
    }

    public static void m2188e(String tag, String message) {
        log(Severity.LS_ERROR, tag, message);
    }

    public static void m2191w(String tag, String message) {
        log(Severity.LS_WARNING, tag, message);
    }

    public static void m2189e(String tag, String message, Throwable e) {
        log(Severity.LS_ERROR, tag, message);
        log(Severity.LS_ERROR, tag, e.toString());
        log(Severity.LS_ERROR, tag, getStackTraceString(e));
    }

    public static void m2192w(String tag, String message, Throwable e) {
        log(Severity.LS_WARNING, tag, message);
        log(Severity.LS_WARNING, tag, e.toString());
        log(Severity.LS_WARNING, tag, getStackTraceString(e));
    }

    public static void m2190v(String tag, String message) {
        log(Severity.LS_VERBOSE, tag, message);
    }

    private static String getStackTraceString(Throwable e) {
        if (e == null) {
            return "";
        }
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }
}

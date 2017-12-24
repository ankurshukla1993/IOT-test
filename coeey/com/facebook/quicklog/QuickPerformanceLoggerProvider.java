package com.facebook.quicklog;

public class QuickPerformanceLoggerProvider {
    private static final QuickPerformanceLogger sQuickPerformanceLogger = new QuickPerformanceLogger();

    public static QuickPerformanceLogger getQPLInstance() {
        return sQuickPerformanceLogger;
    }
}

package com.facebook.common.logging;

public interface LoggingDelegate {
    void mo2954d(String str, String str2);

    void mo2955d(String str, String str2, Throwable th);

    void mo2956e(String str, String str2);

    void mo2957e(String str, String str2, Throwable th);

    int getMinimumLoggingLevel();

    void mo2959i(String str, String str2);

    void mo2960i(String str, String str2, Throwable th);

    boolean isLoggable(int i);

    void log(int i, String str, String str2);

    void setMinimumLoggingLevel(int i);

    void mo2964v(String str, String str2);

    void mo2965v(String str, String str2, Throwable th);

    void mo2966w(String str, String str2);

    void mo2967w(String str, String str2, Throwable th);

    void wtf(String str, String str2);

    void wtf(String str, String str2, Throwable th);
}

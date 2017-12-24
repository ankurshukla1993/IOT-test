package io.fabric.sdk.android;

public interface Logger {
    void mo5779d(String str, String str2);

    void mo5780d(String str, String str2, Throwable th);

    void mo5781e(String str, String str2);

    void mo5782e(String str, String str2, Throwable th);

    int getLogLevel();

    void mo5784i(String str, String str2);

    void mo5785i(String str, String str2, Throwable th);

    boolean isLoggable(String str, int i);

    void log(int i, String str, String str2);

    void log(int i, String str, String str2, boolean z);

    void setLogLevel(int i);

    void mo5790v(String str, String str2);

    void mo5791v(String str, String str2, Throwable th);

    void mo5792w(String str, String str2);

    void mo5793w(String str, String str2, Throwable th);
}

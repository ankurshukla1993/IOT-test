package io.fabric.sdk.android;

public class SilentLogger implements Logger {
    private int logLevel = 7;

    public boolean isLoggable(String tag, int level) {
        return false;
    }

    public void mo5780d(String tag, String text, Throwable throwable) {
    }

    public void mo5791v(String tag, String text, Throwable throwable) {
    }

    public void mo5785i(String tag, String text, Throwable throwable) {
    }

    public void mo5793w(String tag, String text, Throwable throwable) {
    }

    public void mo5782e(String tag, String text, Throwable throwable) {
    }

    public void mo5779d(String tag, String text) {
    }

    public void mo5790v(String tag, String text) {
    }

    public void mo5784i(String tag, String text) {
    }

    public void mo5792w(String tag, String text) {
    }

    public void mo5781e(String tag, String text) {
    }

    public void log(int priority, String tag, String msg) {
    }

    public void log(int priority, String tag, String msg, boolean forceLog) {
    }

    public int getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(int logLevel) {
    }
}

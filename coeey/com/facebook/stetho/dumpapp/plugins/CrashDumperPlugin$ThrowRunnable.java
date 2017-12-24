package com.facebook.stetho.dumpapp.plugins;

import com.facebook.stetho.common.ExceptionUtil;

class CrashDumperPlugin$ThrowRunnable implements Runnable {
    private final Throwable mThrowable;

    public CrashDumperPlugin$ThrowRunnable(Throwable t) {
        this.mThrowable = t;
    }

    public void run() {
        ExceptionUtil.sneakyThrow(this.mThrowable);
    }
}

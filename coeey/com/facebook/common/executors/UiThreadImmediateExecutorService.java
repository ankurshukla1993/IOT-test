package com.facebook.common.executors;

import android.os.Handler;
import android.os.Looper;

public class UiThreadImmediateExecutorService extends HandlerExecutorServiceImpl {
    private static UiThreadImmediateExecutorService sInstance = null;

    private UiThreadImmediateExecutorService() {
        super(new Handler(Looper.getMainLooper()));
    }

    public static UiThreadImmediateExecutorService getInstance() {
        if (sInstance == null) {
            sInstance = new UiThreadImmediateExecutorService();
        }
        return sInstance;
    }

    public void execute(Runnable command) {
        if (isHandlerThread()) {
            command.run();
        } else {
            super.execute(command);
        }
    }
}

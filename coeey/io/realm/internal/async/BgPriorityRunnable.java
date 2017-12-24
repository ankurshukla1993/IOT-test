package io.realm.internal.async;

import android.os.Process;

public class BgPriorityRunnable implements Runnable {
    private final Runnable runnable;

    BgPriorityRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    public void run() {
        Process.setThreadPriority(10);
        this.runnable.run();
    }
}

package io.realm.internal;

import io.realm.log.RealmLog;
import java.lang.ref.ReferenceQueue;

class FinalizerRunnable implements Runnable {
    private final ReferenceQueue<NativeObject> referenceQueue;

    FinalizerRunnable(ReferenceQueue<NativeObject> referenceQueue) {
        this.referenceQueue = referenceQueue;
    }

    public void run() {
        while (true) {
            try {
                ((NativeObjectReference) this.referenceQueue.remove()).cleanup();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                RealmLog.fatal("The FinalizerRunnable thread has been interrupted. Native resources cannot be freed anymore", new Object[0]);
                return;
            }
        }
    }
}

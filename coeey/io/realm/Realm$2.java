package io.realm;

import java.util.concurrent.atomic.AtomicInteger;

class Realm$2 implements Callback {
    final /* synthetic */ AtomicInteger val$globalCount;

    Realm$2(AtomicInteger atomicInteger) {
        this.val$globalCount = atomicInteger;
    }

    public void onResult(int count) {
        this.val$globalCount.set(count);
    }
}

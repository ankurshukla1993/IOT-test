package com.facebook.react.bridge;

class ReactContext$1 implements Runnable {
    final /* synthetic */ ReactContext this$0;
    final /* synthetic */ LifecycleEventListener val$listener;

    ReactContext$1(ReactContext this$0, LifecycleEventListener lifecycleEventListener) {
        this.this$0 = this$0;
        this.val$listener = lifecycleEventListener;
    }

    public void run() {
        this.val$listener.onHostResume();
    }
}

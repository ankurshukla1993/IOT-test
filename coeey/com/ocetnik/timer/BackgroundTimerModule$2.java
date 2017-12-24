package com.ocetnik.timer;

import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

class BackgroundTimerModule$2 implements Runnable {
    final /* synthetic */ BackgroundTimerModule this$0;
    final /* synthetic */ int val$id;

    BackgroundTimerModule$2(BackgroundTimerModule this$0, int i) {
        this.this$0 = this$0;
        this.val$id = i;
    }

    public void run() {
        if (BackgroundTimerModule.access$400(this.this$0).hasActiveCatalystInstance()) {
            ((RCTDeviceEventEmitter) BackgroundTimerModule.access$500(this.this$0).getJSModule(RCTDeviceEventEmitter.class)).emit("backgroundTimer.timeout", Integer.valueOf(this.val$id));
        }
    }
}

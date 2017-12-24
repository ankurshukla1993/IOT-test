package com.rnimmersive;

import android.annotation.TargetApi;
import android.app.Activity;
import com.facebook.react.bridge.Promise;

class RNImmersiveModule$1 implements Runnable {
    final /* synthetic */ RNImmersiveModule this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ boolean val$isOn;
    final /* synthetic */ Promise val$res;

    RNImmersiveModule$1(RNImmersiveModule this$0, boolean z, Activity activity, Promise promise) {
        this.this$0 = this$0;
        this.val$isOn = z;
        this.val$activity = activity;
        this.val$res = promise;
    }

    @TargetApi(19)
    public void run() {
        RNImmersiveModule.access$002(this.this$0, this.val$isOn);
        this.val$activity.getWindow().getDecorView().setSystemUiVisibility(this.val$isOn ? 5894 : 0);
        this.val$res.resolve(null);
    }
}

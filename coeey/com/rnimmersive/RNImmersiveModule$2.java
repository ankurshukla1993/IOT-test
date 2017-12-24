package com.rnimmersive;

import android.annotation.TargetApi;
import android.app.Activity;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;

class RNImmersiveModule$2 implements Runnable {
    final /* synthetic */ RNImmersiveModule this$0;
    final /* synthetic */ Activity val$activity;
    final /* synthetic */ Promise val$res;

    RNImmersiveModule$2(RNImmersiveModule this$0, Activity activity, Promise promise) {
        this.this$0 = this$0;
        this.val$activity = activity;
        this.val$res = promise;
    }

    @TargetApi(19)
    public void run() {
        boolean isImmersiveOn = (this.val$activity.getWindow().getDecorView().getSystemUiVisibility() & 5894) != 0;
        WritableMap map = Arguments.createMap();
        map.putBoolean("isImmersiveOn", isImmersiveOn);
        this.val$res.resolve(map);
    }
}

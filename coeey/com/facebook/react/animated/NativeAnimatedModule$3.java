package com.facebook.react.animated;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

class NativeAnimatedModule$3 implements AnimatedNodeValueListener {
    final /* synthetic */ NativeAnimatedModule this$0;
    final /* synthetic */ int val$tag;

    NativeAnimatedModule$3(NativeAnimatedModule this$0, int i) {
        this.this$0 = this$0;
        this.val$tag = i;
    }

    public void onValueUpdate(double value) {
        WritableMap onAnimatedValueData = Arguments.createMap();
        onAnimatedValueData.putInt(JobStorage.COLUMN_TAG, this.val$tag);
        onAnimatedValueData.putDouble("value", value);
        ((RCTDeviceEventEmitter) NativeAnimatedModule.access$400(this.this$0).getJSModule(RCTDeviceEventEmitter.class)).emit("onAnimatedValueUpdate", onAnimatedValueData);
    }
}

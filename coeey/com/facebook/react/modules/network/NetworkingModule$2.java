package com.facebook.react.modules.network;

import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

class NetworkingModule$2 implements ProgressListener {
    long last = System.nanoTime();
    final /* synthetic */ NetworkingModule this$0;
    final /* synthetic */ RCTDeviceEventEmitter val$eventEmitter;
    final /* synthetic */ int val$requestId;

    NetworkingModule$2(NetworkingModule this$0, RCTDeviceEventEmitter rCTDeviceEventEmitter, int i) {
        this.this$0 = this$0;
        this.val$eventEmitter = rCTDeviceEventEmitter;
        this.val$requestId = i;
    }

    public void onProgress(long bytesWritten, long contentLength, boolean done) {
        long now = System.nanoTime();
        if (done || NetworkingModule.access$000(now, this.last)) {
            ResponseUtil.onDataSend(this.val$eventEmitter, this.val$requestId, bytesWritten, contentLength);
            this.last = now;
        }
    }
}

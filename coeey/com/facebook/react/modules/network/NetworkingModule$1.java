package com.facebook.react.modules.network;

import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.views.text.ReactTextShadowNode;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.Response;

class NetworkingModule$1 implements Interceptor {
    final /* synthetic */ NetworkingModule this$0;
    final /* synthetic */ RCTDeviceEventEmitter val$eventEmitter;
    final /* synthetic */ int val$requestId;
    final /* synthetic */ String val$responseType;

    class C13191 implements ProgressListener {
        long last = System.nanoTime();

        C13191() {
        }

        public void onProgress(long bytesWritten, long contentLength, boolean done) {
            long now = System.nanoTime();
            if ((done || NetworkingModule.access$000(now, this.last)) && !NetworkingModule$1.this.val$responseType.equals(ReactTextShadowNode.PROP_TEXT)) {
                ResponseUtil.onDataReceivedProgress(NetworkingModule$1.this.val$eventEmitter, NetworkingModule$1.this.val$requestId, bytesWritten, contentLength);
                this.last = now;
            }
        }
    }

    NetworkingModule$1(NetworkingModule this$0, String str, RCTDeviceEventEmitter rCTDeviceEventEmitter, int i) {
        this.this$0 = this$0;
        this.val$responseType = str;
        this.val$eventEmitter = rCTDeviceEventEmitter;
        this.val$requestId = i;
    }

    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder().body(new ProgressResponseBody(originalResponse.body(), new C13191())).build();
    }
}

package com.facebook.react.modules.network;

import android.util.Base64;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.views.text.ReactTextShadowNode;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

class NetworkingModule$3 implements Callback {
    final /* synthetic */ NetworkingModule this$0;
    final /* synthetic */ RCTDeviceEventEmitter val$eventEmitter;
    final /* synthetic */ int val$requestId;
    final /* synthetic */ String val$responseType;
    final /* synthetic */ boolean val$useIncrementalUpdates;

    NetworkingModule$3(NetworkingModule this$0, int i, RCTDeviceEventEmitter rCTDeviceEventEmitter, boolean z, String str) {
        this.this$0 = this$0;
        this.val$requestId = i;
        this.val$eventEmitter = rCTDeviceEventEmitter;
        this.val$useIncrementalUpdates = z;
        this.val$responseType = str;
    }

    public void onFailure(Call call, IOException e) {
        if (!NetworkingModule.access$100(this.this$0)) {
            NetworkingModule.access$200(this.this$0, this.val$requestId);
            ResponseUtil.onRequestError(this.val$eventEmitter, this.val$requestId, e.getMessage(), e);
        }
    }

    public void onResponse(Call call, Response response) throws IOException {
        if (!NetworkingModule.access$100(this.this$0)) {
            NetworkingModule.access$200(this.this$0, this.val$requestId);
            ResponseUtil.onResponseReceived(this.val$eventEmitter, this.val$requestId, response.code(), NetworkingModule.access$300(response.headers()), response.request().url().toString());
            ResponseBody responseBody = response.body();
            try {
                if (this.val$useIncrementalUpdates && this.val$responseType.equals(ReactTextShadowNode.PROP_TEXT)) {
                    NetworkingModule.access$400(this.this$0, this.val$eventEmitter, this.val$requestId, responseBody);
                    ResponseUtil.onRequestSuccess(this.val$eventEmitter, this.val$requestId);
                    return;
                }
                String responseString = "";
                if (this.val$responseType.equals(ReactTextShadowNode.PROP_TEXT)) {
                    responseString = responseBody.string();
                } else if (this.val$responseType.equals(RNFetchBlobConst.RNFB_RESPONSE_BASE64)) {
                    responseString = Base64.encodeToString(responseBody.bytes(), 2);
                }
                ResponseUtil.onDataReceived(this.val$eventEmitter, this.val$requestId, responseString);
                ResponseUtil.onRequestSuccess(this.val$eventEmitter, this.val$requestId);
            } catch (IOException e) {
                ResponseUtil.onRequestError(this.val$eventEmitter, this.val$requestId, e.getMessage(), e);
            }
        }
    }
}

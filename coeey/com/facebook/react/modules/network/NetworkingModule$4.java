package com.facebook.react.modules.network;

import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.network.OkHttpCallUtil;

class NetworkingModule$4 extends GuardedAsyncTask<Void, Void> {
    final /* synthetic */ NetworkingModule this$0;
    final /* synthetic */ int val$requestId;

    NetworkingModule$4(NetworkingModule this$0, ReactContext reactContext, int i) {
        this.this$0 = this$0;
        this.val$requestId = i;
        super(reactContext);
    }

    protected void doInBackgroundGuarded(Void... params) {
        OkHttpCallUtil.cancelTag(NetworkingModule.access$500(this.this$0), Integer.valueOf(this.val$requestId));
    }
}

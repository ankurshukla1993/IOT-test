package com.facebook.react.modules.network;

import com.facebook.react.bridge.GuardedAsyncTask;
import com.facebook.react.bridge.ReactContext;

class ForwardingCookieHandler$4 extends GuardedAsyncTask<Void, Void> {
    final /* synthetic */ ForwardingCookieHandler this$0;
    final /* synthetic */ Runnable val$runnable;

    ForwardingCookieHandler$4(ForwardingCookieHandler this$0, ReactContext reactContext, Runnable runnable) {
        this.this$0 = this$0;
        this.val$runnable = runnable;
        super(reactContext);
    }

    protected void doInBackgroundGuarded(Void... params) {
        this.val$runnable.run();
    }
}

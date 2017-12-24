package com.facebook.react.modules.network;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.GuardedResultAsyncTask;
import com.facebook.react.bridge.ReactContext;

class ForwardingCookieHandler$1 extends GuardedResultAsyncTask<Boolean> {
    final /* synthetic */ ForwardingCookieHandler this$0;
    final /* synthetic */ Callback val$callback;

    ForwardingCookieHandler$1(ForwardingCookieHandler this$0, ReactContext reactContext, Callback callback) {
        this.this$0 = this$0;
        this.val$callback = callback;
        super(reactContext);
    }

    protected Boolean doInBackgroundGuarded() {
        ForwardingCookieHandler.access$000(this.this$0).removeAllCookie();
        ForwardingCookieHandler.access$100(this.this$0).onCookiesModified();
        return Boolean.valueOf(true);
    }

    protected void onPostExecuteGuarded(Boolean result) {
        this.val$callback.invoke(result);
    }
}

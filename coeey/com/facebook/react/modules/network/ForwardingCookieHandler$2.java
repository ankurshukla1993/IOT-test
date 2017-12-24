package com.facebook.react.modules.network;

import android.webkit.ValueCallback;
import com.facebook.react.bridge.Callback;

class ForwardingCookieHandler$2 implements ValueCallback<Boolean> {
    final /* synthetic */ ForwardingCookieHandler this$0;
    final /* synthetic */ Callback val$callback;

    ForwardingCookieHandler$2(ForwardingCookieHandler this$0, Callback callback) {
        this.this$0 = this$0;
        this.val$callback = callback;
    }

    public void onReceiveValue(Boolean value) {
        ForwardingCookieHandler.access$100(this.this$0).onCookiesModified();
        this.val$callback.invoke(value);
    }
}

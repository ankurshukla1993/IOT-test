package com.facebook.react.modules.network;

import java.util.List;

class ForwardingCookieHandler$3 implements Runnable {
    final /* synthetic */ ForwardingCookieHandler this$0;
    final /* synthetic */ List val$cookies;
    final /* synthetic */ String val$url;

    ForwardingCookieHandler$3(ForwardingCookieHandler this$0, List list, String str) {
        this.this$0 = this$0;
        this.val$cookies = list;
        this.val$url = str;
    }

    public void run() {
        for (String cookie : this.val$cookies) {
            ForwardingCookieHandler.access$000(this.this$0).setCookie(this.val$url, cookie);
        }
        ForwardingCookieHandler.access$100(this.this$0).onCookiesModified();
    }
}

package com.facebook.react.views.webview;

import android.webkit.GeolocationPermissions.Callback;
import android.webkit.WebChromeClient;

class ReactWebViewManager$2 extends WebChromeClient {
    final /* synthetic */ ReactWebViewManager this$0;

    ReactWebViewManager$2(ReactWebViewManager this$0) {
        this.this$0 = this$0;
    }

    public void onGeolocationPermissionsShowPrompt(String origin, Callback callback) {
        callback.invoke(origin, true, false);
    }
}

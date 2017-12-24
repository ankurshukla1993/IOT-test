package com.facebook.react.views.webview;

import android.graphics.Picture;
import android.webkit.WebView;
import android.webkit.WebView.PictureListener;
import com.facebook.react.uimanager.events.ContentSizeChangeEvent;

class ReactWebViewManager$3 implements PictureListener {
    final /* synthetic */ ReactWebViewManager this$0;

    ReactWebViewManager$3(ReactWebViewManager this$0) {
        this.this$0 = this$0;
    }

    public void onNewPicture(WebView webView, Picture picture) {
        ReactWebViewManager.access$000(webView, new ContentSizeChangeEvent(webView.getId(), webView.getWidth(), webView.getContentHeight()));
    }
}

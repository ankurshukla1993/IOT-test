package com.facebook.react.views.webview;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.views.webview.ReactWebViewManager.ReactWebView;
import com.facebook.react.views.webview.events.TopLoadingErrorEvent;
import com.facebook.react.views.webview.events.TopLoadingFinishEvent;
import com.facebook.react.views.webview.events.TopLoadingStartEvent;

protected class ReactWebViewManager$ReactWebViewClient extends WebViewClient {
    private boolean mLastLoadFailed = false;

    protected ReactWebViewManager$ReactWebViewClient() {
    }

    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        if (!this.mLastLoadFailed) {
            ReactWebView reactWebView = (ReactWebView) webView;
            reactWebView.callInjectedJavaScript();
            reactWebView.linkBridge();
            emitFinishEvent(webView, url);
        }
    }

    public void onPageStarted(WebView webView, String url, Bitmap favicon) {
        super.onPageStarted(webView, url, favicon);
        this.mLastLoadFailed = false;
        ReactWebViewManager.access$000(webView, new TopLoadingStartEvent(webView.getId(), createWebViewEvent(webView, url)));
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith("http://") || url.startsWith("https://") || url.startsWith("file://")) {
            return false;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
            intent.setFlags(268435456);
            view.getContext().startActivity(intent);
        } catch (Throwable e) {
            FLog.m152w(ReactConstants.TAG, "activity not found to handle uri scheme for: " + url, e);
        }
        return true;
    }

    public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
        super.onReceivedError(webView, errorCode, description, failingUrl);
        this.mLastLoadFailed = true;
        emitFinishEvent(webView, failingUrl);
        WritableMap eventData = createWebViewEvent(webView, failingUrl);
        eventData.putDouble("code", (double) errorCode);
        eventData.putString("description", description);
        ReactWebViewManager.access$000(webView, new TopLoadingErrorEvent(webView.getId(), eventData));
    }

    public void doUpdateVisitedHistory(WebView webView, String url, boolean isReload) {
        super.doUpdateVisitedHistory(webView, url, isReload);
        ReactWebViewManager.access$000(webView, new TopLoadingStartEvent(webView.getId(), createWebViewEvent(webView, url)));
    }

    private void emitFinishEvent(WebView webView, String url) {
        ReactWebViewManager.access$000(webView, new TopLoadingFinishEvent(webView.getId(), createWebViewEvent(webView, url)));
    }

    private WritableMap createWebViewEvent(WebView webView, String url) {
        WritableMap event = Arguments.createMap();
        event.putDouble("target", (double) webView.getId());
        event.putString("url", url);
        String str = "loading";
        boolean z = (this.mLastLoadFailed || webView.getProgress() == 100) ? false : true;
        event.putBoolean(str, z);
        event.putString("title", webView.getTitle());
        event.putBoolean("canGoBack", webView.canGoBack());
        event.putBoolean("canGoForward", webView.canGoForward());
        return event;
    }
}

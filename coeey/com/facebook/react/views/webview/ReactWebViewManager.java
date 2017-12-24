package com.facebook.react.views.webview;

import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebView.PictureListener;
import com.RNFetchBlob.RNFetchBlobConst;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.views.webview.events.TopMessageEvent;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@ReactModule(name = "RCTWebView")
public class ReactWebViewManager extends SimpleViewManager<WebView> {
    private static final String BLANK_URL = "about:blank";
    private static final String BRIDGE_NAME = "__REACT_WEB_VIEW_BRIDGE";
    public static final int COMMAND_GO_BACK = 1;
    public static final int COMMAND_GO_FORWARD = 2;
    public static final int COMMAND_INJECT_JAVASCRIPT = 6;
    public static final int COMMAND_POST_MESSAGE = 5;
    public static final int COMMAND_RELOAD = 3;
    public static final int COMMAND_STOP_LOADING = 4;
    private static final String HTML_ENCODING = "UTF-8";
    private static final String HTML_MIME_TYPE = "text/html; charset=utf-8";
    private static final String HTTP_METHOD_POST = "POST";
    protected static final String REACT_CLASS = "RCTWebView";
    @Nullable
    private PictureListener mPictureListener;
    private WebViewConfig mWebViewConfig;

    protected static class ReactWebView extends WebView implements LifecycleEventListener {
        @Nullable
        private String injectedJS;
        private boolean messagingEnabled = false;

        private class ReactWebViewBridge {
            ReactWebView mContext;

            ReactWebViewBridge(ReactWebView c) {
                this.mContext = c;
            }

            @JavascriptInterface
            public void postMessage(String message) {
                this.mContext.onMessage(message);
            }
        }

        public ReactWebView(ThemedReactContext reactContext) {
            super(reactContext);
        }

        public void onHostResume() {
        }

        public void onHostPause() {
        }

        public void onHostDestroy() {
            cleanupCallbacksAndDestroy();
        }

        public void setInjectedJavaScript(@Nullable String js) {
            this.injectedJS = js;
        }

        public void setMessagingEnabled(boolean enabled) {
            if (this.messagingEnabled != enabled) {
                this.messagingEnabled = enabled;
                if (enabled) {
                    addJavascriptInterface(new ReactWebViewBridge(this), ReactWebViewManager.BRIDGE_NAME);
                    linkBridge();
                    return;
                }
                removeJavascriptInterface(ReactWebViewManager.BRIDGE_NAME);
            }
        }

        public void callInjectedJavaScript() {
            if (getSettings().getJavaScriptEnabled() && this.injectedJS != null && !TextUtils.isEmpty(this.injectedJS)) {
                loadUrl("javascript:(function() {\n" + this.injectedJS + ";\n})();");
            }
        }

        public void linkBridge() {
            if (this.messagingEnabled) {
                loadUrl("javascript:(window.originalPostMessage = window.postMessage,window.postMessage = function(data) {__REACT_WEB_VIEW_BRIDGE.postMessage(String(data));})");
            }
        }

        public void onMessage(String message) {
            ReactWebViewManager.dispatchEvent(this, new TopMessageEvent(getId(), message));
        }

        private void cleanupCallbacksAndDestroy() {
            setWebViewClient(null);
            destroy();
        }
    }

    public ReactWebViewManager() {
        this.mWebViewConfig = new 1(this);
    }

    public ReactWebViewManager(WebViewConfig webViewConfig) {
        this.mWebViewConfig = webViewConfig;
    }

    public String getName() {
        return REACT_CLASS;
    }

    protected WebView createViewInstance(ThemedReactContext reactContext) {
        ReactWebView webView = new ReactWebView(reactContext);
        webView.setWebChromeClient(new 2(this));
        reactContext.addLifecycleEventListener(webView);
        this.mWebViewConfig.configWebView(webView);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setLayoutParams(new LayoutParams(-1, -1));
        return webView;
    }

    @ReactProp(name = "javaScriptEnabled")
    public void setJavaScriptEnabled(WebView view, boolean enabled) {
        view.getSettings().setJavaScriptEnabled(enabled);
    }

    @ReactProp(name = "scalesPageToFit")
    public void setScalesPageToFit(WebView view, boolean enabled) {
        view.getSettings().setUseWideViewPort(!enabled);
    }

    @ReactProp(name = "domStorageEnabled")
    public void setDomStorageEnabled(WebView view, boolean enabled) {
        view.getSettings().setDomStorageEnabled(enabled);
    }

    @ReactProp(name = "userAgent")
    public void setUserAgent(WebView view, @Nullable String userAgent) {
        if (userAgent != null) {
            view.getSettings().setUserAgentString(userAgent);
        }
    }

    @ReactProp(name = "mediaPlaybackRequiresUserAction")
    public void setMediaPlaybackRequiresUserAction(WebView view, boolean requires) {
        view.getSettings().setMediaPlaybackRequiresUserGesture(requires);
    }

    @ReactProp(name = "allowUniversalAccessFromFileURLs")
    public void setAllowUniversalAccessFromFileURLs(WebView view, boolean allow) {
        view.getSettings().setAllowUniversalAccessFromFileURLs(allow);
    }

    @ReactProp(name = "injectedJavaScript")
    public void setInjectedJavaScript(WebView view, @Nullable String injectedJavaScript) {
        ((ReactWebView) view).setInjectedJavaScript(injectedJavaScript);
    }

    @ReactProp(name = "messagingEnabled")
    public void setMessagingEnabled(WebView view, boolean enabled) {
        ((ReactWebView) view).setMessagingEnabled(enabled);
    }

    @ReactProp(name = "source")
    public void setSource(WebView view, @Nullable ReadableMap source) {
        if (source != null) {
            if (source.hasKey("html")) {
                String html = source.getString("html");
                if (source.hasKey("baseUrl")) {
                    WebView webView = view;
                    webView.loadDataWithBaseURL(source.getString("baseUrl"), html, HTML_MIME_TYPE, "UTF-8", null);
                    return;
                }
                view.loadData(html, HTML_MIME_TYPE, "UTF-8");
                return;
            }
            if (source.hasKey(RNFetchBlobConst.DATA_ENCODE_URI)) {
                String url = source.getString(RNFetchBlobConst.DATA_ENCODE_URI);
                String previousUrl = view.getUrl();
                if (previousUrl == null || !previousUrl.equals(url)) {
                    if (source.hasKey("method")) {
                        if (source.getString("method").equals("POST")) {
                            byte[] postData = null;
                            if (source.hasKey("body")) {
                                String body = source.getString("body");
                                try {
                                    postData = body.getBytes("UTF-8");
                                } catch (UnsupportedEncodingException e) {
                                    postData = body.getBytes();
                                }
                            }
                            if (postData == null) {
                                postData = new byte[0];
                            }
                            view.postUrl(url, postData);
                            return;
                        }
                    }
                    HashMap<String, String> headerMap = new HashMap();
                    if (source.hasKey("headers")) {
                        ReadableMap headers = source.getMap("headers");
                        ReadableMapKeySetIterator iter = headers.keySetIterator();
                        while (iter.hasNextKey()) {
                            String key = iter.nextKey();
                            if (!"user-agent".equals(key.toLowerCase(Locale.ENGLISH))) {
                                headerMap.put(key, headers.getString(key));
                            } else if (view.getSettings() != null) {
                                view.getSettings().setUserAgentString(headers.getString(key));
                            }
                        }
                    }
                    view.loadUrl(url, headerMap);
                    return;
                }
                return;
            }
        }
        view.loadUrl(BLANK_URL);
    }

    @ReactProp(name = "onContentSizeChange")
    public void setOnContentSizeChange(WebView view, boolean sendContentSizeChangeEvents) {
        if (sendContentSizeChangeEvents) {
            view.setPictureListener(getPictureListener());
        } else {
            view.setPictureListener(null);
        }
    }

    protected void addEventEmitters(ThemedReactContext reactContext, WebView view) {
        view.setWebViewClient(new ReactWebViewClient());
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("goBack", Integer.valueOf(1), "goForward", Integer.valueOf(2), "reload", Integer.valueOf(3), "stopLoading", Integer.valueOf(4), "postMessage", Integer.valueOf(5), "injectJavaScript", Integer.valueOf(6));
    }

    public void receiveCommand(WebView root, int commandId, @Nullable ReadableArray args) {
        switch (commandId) {
            case 1:
                root.goBack();
                return;
            case 2:
                root.goForward();
                return;
            case 3:
                root.reload();
                return;
            case 4:
                root.stopLoading();
                return;
            case 5:
                try {
                    JSONObject eventInitDict = new JSONObject();
                    eventInitDict.put("data", args.getString(0));
                    root.loadUrl("javascript:(function () {var event;var data = " + eventInitDict.toString() + ";" + "try {" + "event = new MessageEvent('message', data);" + "} catch (e) {" + "event = document.createEvent('MessageEvent');" + "event.initMessageEvent('message', true, true, data.data, data.origin, data.lastEventId, data.source);" + "}" + "document.dispatchEvent(event);" + "})();");
                    return;
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            case 6:
                root.loadUrl("javascript:" + args.getString(0));
                return;
            default:
                return;
        }
    }

    public void onDropViewInstance(WebView webView) {
        super.onDropViewInstance(webView);
        ((ThemedReactContext) webView.getContext()).removeLifecycleEventListener((ReactWebView) webView);
        ((ReactWebView) webView).cleanupCallbacksAndDestroy();
    }

    private PictureListener getPictureListener() {
        if (this.mPictureListener == null) {
            this.mPictureListener = new 3(this);
        }
        return this.mPictureListener;
    }

    private static void dispatchEvent(WebView webView, Event event) {
        ((UIManagerModule) ((ReactContext) webView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(event);
    }
}

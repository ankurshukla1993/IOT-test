package com.facebook.react.modules.websocket;

import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.modules.network.ForwardingCookieHandler;
import com.facebook.share.internal.ShareConstants;
import com.google.common.net.HttpHeaders;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okio.Buffer;
import okio.ByteString;

@ReactModule(name = "WebSocketModule")
public class WebSocketModule extends ReactContextBaseJavaModule {
    private ForwardingCookieHandler mCookieHandler;
    private ReactContext mReactContext;
    private final Map<Integer, WebSocket> mWebSocketConnections = new HashMap();

    public WebSocketModule(ReactApplicationContext context) {
        super(context);
        this.mReactContext = context;
        this.mCookieHandler = new ForwardingCookieHandler(context);
    }

    private void sendEvent(String eventName, WritableMap params) {
        ((RCTDeviceEventEmitter) this.mReactContext.getJSModule(RCTDeviceEventEmitter.class)).emit(eventName, params);
    }

    public String getName() {
        return "WebSocketModule";
    }

    @ReactMethod
    public void connect(String url, @Nullable ReadableArray protocols, @Nullable ReadableMap headers, int id) {
        OkHttpClient client = new Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(0, TimeUnit.MINUTES).build();
        Request.Builder builder = new Request.Builder().tag(Integer.valueOf(id)).url(url);
        String cookie = getCookie(url);
        if (cookie != null) {
            builder.addHeader(HttpHeaders.COOKIE, cookie);
        }
        if (headers != null) {
            ReadableMapKeySetIterator iterator = headers.keySetIterator();
            if (!headers.hasKey(Param.ORIGIN)) {
                builder.addHeader(Param.ORIGIN, getDefaultOrigin(url));
            }
            while (iterator.hasNextKey()) {
                String key = iterator.nextKey();
                if (ReadableType.String.equals(headers.getType(key))) {
                    builder.addHeader(key, headers.getString(key));
                } else {
                    FLog.w(ReactConstants.TAG, "Ignoring: requested " + key + ", value not a string");
                }
            }
        } else {
            builder.addHeader(Param.ORIGIN, getDefaultOrigin(url));
        }
        if (protocols != null && protocols.size() > 0) {
            StringBuilder protocolsValue = new StringBuilder("");
            for (int i = 0; i < protocols.size(); i++) {
                String v = protocols.getString(i).trim();
                if (!(v.isEmpty() || v.contains(","))) {
                    protocolsValue.append(v);
                    protocolsValue.append(",");
                }
            }
            if (protocolsValue.length() > 0) {
                protocolsValue.replace(protocolsValue.length() - 1, protocolsValue.length(), "");
                builder.addHeader("Sec-WebSocket-Protocol", protocolsValue.toString());
            }
        }
        WebSocketCall.create(client, builder.build()).enqueue(new 1(this, id));
        client.dispatcher().executorService().shutdown();
    }

    @ReactMethod
    public void close(int code, String reason, int id) {
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client != null) {
            try {
                client.close(code, reason);
                this.mWebSocketConnections.remove(Integer.valueOf(id));
            } catch (Exception e) {
                FLog.e(ReactConstants.TAG, "Could not close WebSocket connection for id " + id, e);
            }
        }
    }

    @ReactMethod
    public void send(String message, int id) {
        Exception e;
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client == null) {
            throw new RuntimeException("Cannot send a message. Unknown WebSocket id " + id);
        }
        try {
            client.sendMessage(RequestBody.create(WebSocket.TEXT, message));
            return;
        } catch (IOException e2) {
            e = e2;
        } catch (IllegalStateException e3) {
            e = e3;
        }
        notifyWebSocketFailed(id, e.getMessage());
    }

    @ReactMethod
    public void sendBinary(String base64String, int id) {
        Exception e;
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client == null) {
            throw new RuntimeException("Cannot send a message. Unknown WebSocket id " + id);
        }
        try {
            client.sendMessage(RequestBody.create(WebSocket.BINARY, ByteString.decodeBase64(base64String)));
            return;
        } catch (IOException e2) {
            e = e2;
        } catch (IllegalStateException e3) {
            e = e3;
        }
        notifyWebSocketFailed(id, e.getMessage());
    }

    @ReactMethod
    public void ping(int id) {
        Exception e;
        WebSocket client = (WebSocket) this.mWebSocketConnections.get(Integer.valueOf(id));
        if (client == null) {
            throw new RuntimeException("Cannot send a message. Unknown WebSocket id " + id);
        }
        try {
            client.sendPing(new Buffer());
            return;
        } catch (IOException e2) {
            e = e2;
        } catch (IllegalStateException e3) {
            e = e3;
        }
        notifyWebSocketFailed(id, e.getMessage());
    }

    private void notifyWebSocketFailed(int id, String message) {
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, id);
        params.putString("message", message);
        sendEvent("websocketFailed", params);
    }

    private static String getDefaultOrigin(String uri) {
        try {
            String scheme = "";
            URI requestURI = new URI(uri);
            if (requestURI.getScheme().equals("wss")) {
                scheme = scheme + UriUtil.HTTPS_SCHEME;
            } else if (requestURI.getScheme().equals("ws")) {
                scheme = scheme + UriUtil.HTTP_SCHEME;
            }
            if (requestURI.getPort() != -1) {
                return String.format("%s://%s:%s", new Object[]{scheme, requestURI.getHost(), Integer.valueOf(requestURI.getPort())});
            }
            return String.format("%s://%s/", new Object[]{scheme, requestURI.getHost()});
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to set " + uri + " as default origin header");
        }
    }

    private String getCookie(String uri) {
        try {
            List<String> cookieList = (List) this.mCookieHandler.get(new URI(getDefaultOrigin(uri)), new HashMap()).get(HttpHeaders.COOKIE);
            if (cookieList == null || cookieList.isEmpty()) {
                return null;
            }
            return (String) cookieList.get(0);
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException("Unable to get cookie from " + uri);
        } catch (IOException e2) {
            throw new IllegalArgumentException("Unable to get cookie from " + uri);
        }
    }
}

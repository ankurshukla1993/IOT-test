package com.facebook.react.devsupport;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.JavascriptException;
import com.facebook.share.internal.ShareConstants;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;

public class JSDebuggerWebSocketClient implements WebSocketListener {
    private static final String TAG = "JSDebuggerWebSocketClient";
    private final ConcurrentHashMap<Integer, JSDebuggerCallback> mCallbacks = new ConcurrentHashMap();
    @Nullable
    private JSDebuggerCallback mConnectCallback;
    @Nullable
    private OkHttpClient mHttpClient;
    private final AtomicInteger mRequestID = new AtomicInteger();
    @Nullable
    private WebSocket mWebSocket;

    public void connect(String url, JSDebuggerCallback callback) {
        if (this.mHttpClient != null) {
            throw new IllegalStateException("JSDebuggerWebSocketClient is already initialized.");
        }
        this.mConnectCallback = callback;
        this.mHttpClient = new Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(0, TimeUnit.MINUTES).build();
        WebSocketCall.create(this.mHttpClient, new Request.Builder().url(url).build()).enqueue(this);
    }

    public void prepareJSRuntime(JSDebuggerCallback callback) {
        int requestID = this.mRequestID.getAndIncrement();
        this.mCallbacks.put(Integer.valueOf(requestID), callback);
        try {
            StringWriter sw = new StringWriter();
            new JsonWriter(sw).beginObject().name(ShareConstants.WEB_DIALOG_PARAM_ID).value((long) requestID).name("method").value("prepareJSRuntime").endObject().close();
            sendMessage(requestID, sw.toString());
        } catch (IOException e) {
            triggerRequestFailure(requestID, e);
        }
    }

    public void loadApplicationScript(String sourceURL, HashMap<String, String> injectedObjects, JSDebuggerCallback callback) {
        int requestID = this.mRequestID.getAndIncrement();
        this.mCallbacks.put(Integer.valueOf(requestID), callback);
        try {
            StringWriter sw = new StringWriter();
            JsonWriter js = new JsonWriter(sw).beginObject().name(ShareConstants.WEB_DIALOG_PARAM_ID).value((long) requestID).name("method").value("executeApplicationScript").name("url").value(sourceURL).name("inject").beginObject();
            for (String key : injectedObjects.keySet()) {
                js.name(key).value((String) injectedObjects.get(key));
            }
            js.endObject().endObject().close();
            sendMessage(requestID, sw.toString());
        } catch (IOException e) {
            triggerRequestFailure(requestID, e);
        }
    }

    public void executeJSCall(String methodName, String jsonArgsArray, JSDebuggerCallback callback) {
        int requestID = this.mRequestID.getAndIncrement();
        this.mCallbacks.put(Integer.valueOf(requestID), callback);
        try {
            StringWriter sw = new StringWriter();
            JsonWriter js = new JsonWriter(sw);
            js.beginObject().name(ShareConstants.WEB_DIALOG_PARAM_ID).value((long) requestID).name("method").value(methodName);
            sw.append(",\"arguments\":").append(jsonArgsArray);
            js.endObject().close();
            sendMessage(requestID, sw.toString());
        } catch (IOException e) {
            triggerRequestFailure(requestID, e);
        }
    }

    public void closeQuietly() {
        if (this.mWebSocket != null) {
            try {
                this.mWebSocket.close(1000, "End of session");
            } catch (IOException e) {
            }
            this.mWebSocket = null;
        }
    }

    private void sendMessage(int requestID, String message) {
        if (this.mWebSocket == null) {
            triggerRequestFailure(requestID, new IllegalStateException("WebSocket connection no longer valid"));
            return;
        }
        try {
            this.mWebSocket.sendMessage(RequestBody.create(WebSocket.TEXT, message));
        } catch (IOException e) {
            triggerRequestFailure(requestID, e);
        }
    }

    private void triggerRequestFailure(int requestID, Throwable cause) {
        JSDebuggerCallback callback = (JSDebuggerCallback) this.mCallbacks.get(Integer.valueOf(requestID));
        if (callback != null) {
            this.mCallbacks.remove(Integer.valueOf(requestID));
            callback.onFailure(cause);
        }
    }

    private void triggerRequestSuccess(int requestID, @Nullable String response) {
        JSDebuggerCallback callback = (JSDebuggerCallback) this.mCallbacks.get(Integer.valueOf(requestID));
        if (callback != null) {
            this.mCallbacks.remove(Integer.valueOf(requestID));
            callback.onSuccess(response);
        }
    }

    public void onMessage(ResponseBody response) throws IOException {
        if (response.contentType() != WebSocket.TEXT) {
            FLog.w(TAG, "Websocket received unexpected message with payload of type " + response.contentType());
            return;
        }
        Integer replyID = null;
        try {
            JsonReader reader = new JsonReader(response.charStream());
            String result = null;
            reader.beginObject();
            while (reader.hasNext()) {
                String field = reader.nextName();
                if (JsonToken.NULL == reader.peek()) {
                    reader.skipValue();
                } else if ("replyID".equals(field)) {
                    replyID = Integer.valueOf(reader.nextInt());
                } else if ("result".equals(field)) {
                    result = reader.nextString();
                } else if ("error".equals(field)) {
                    String error = reader.nextString();
                    abort(error, new JavascriptException(error));
                }
            }
            if (replyID != null) {
                triggerRequestSuccess(replyID.intValue(), result);
            }
            response.close();
        } catch (IOException e) {
            if (replyID != null) {
                triggerRequestFailure(replyID.intValue(), e);
            } else {
                abort("Parsing response message from websocket failed", e);
            }
            response.close();
        } catch (Throwable th) {
            response.close();
        }
    }

    public void onFailure(IOException e, Response response) {
        abort("Websocket exception", e);
    }

    public void onOpen(WebSocket webSocket, Response response) {
        this.mWebSocket = webSocket;
        ((JSDebuggerCallback) Assertions.assertNotNull(this.mConnectCallback)).onSuccess(null);
        this.mConnectCallback = null;
    }

    public void onClose(int code, String reason) {
        this.mWebSocket = null;
    }

    public void onPong(Buffer payload) {
    }

    private void abort(String message, Throwable cause) {
        FLog.e(TAG, "Error occurred, shutting down websocket connection: " + message, cause);
        closeQuietly();
        if (this.mConnectCallback != null) {
            this.mConnectCallback.onFailure(cause);
            this.mConnectCallback = null;
        }
        for (JSDebuggerCallback callback : this.mCallbacks.values()) {
            callback.onFailure(cause);
        }
        this.mCallbacks.clear();
    }
}

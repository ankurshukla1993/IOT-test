package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import android.util.JsonReader;
import android.util.JsonToken;
import com.facebook.common.logging.FLog;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;

public class JSPackagerWebSocketClient implements WebSocketListener {
    private static final int RECONNECT_DELAY_MS = 2000;
    private static final String TAG = "JSPackagerWebSocketClient";
    @Nullable
    private JSPackagerCallback mCallback;
    private boolean mClosed = false;
    private final Handler mHandler;
    private boolean mSuppressConnectionErrors;
    private final String mUrl;
    @Nullable
    private WebSocket mWebSocket;

    public JSPackagerWebSocketClient(String url, JSPackagerCallback callback) {
        this.mUrl = url;
        this.mCallback = callback;
        this.mHandler = new Handler(Looper.getMainLooper());
    }

    public void connect() {
        if (this.mClosed) {
            throw new IllegalStateException("Can't connect closed client");
        }
        WebSocketCall.create(new Builder().connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).readTimeout(0, TimeUnit.MINUTES).build(), new Request.Builder().url(this.mUrl).build()).enqueue(this);
    }

    private void reconnect() {
        if (this.mClosed) {
            throw new IllegalStateException("Can't reconnect closed client");
        }
        if (!this.mSuppressConnectionErrors) {
            FLog.w(TAG, "Couldn't connect to packager, will silently retry");
            this.mSuppressConnectionErrors = true;
        }
        this.mHandler.postDelayed(new 1(this), 2000);
    }

    public void closeQuietly() {
        this.mClosed = true;
        closeWebSocketQuietly();
    }

    private void closeWebSocketQuietly() {
        if (this.mWebSocket != null) {
            try {
                this.mWebSocket.close(1000, "End of session");
            } catch (IOException e) {
            }
            this.mWebSocket = null;
        }
    }

    private void triggerMessageCallback(String target, String action) {
        if (this.mCallback != null) {
            this.mCallback.onMessage(this.mWebSocket, target, action);
        }
    }

    public void onMessage(ResponseBody response) throws IOException {
        if (response.contentType() != WebSocket.TEXT) {
            FLog.w(TAG, "Websocket received unexpected message with payload of type " + response.contentType());
            return;
        }
        try {
            JsonReader reader = new JsonReader(response.charStream());
            Integer version = null;
            String target = null;
            String action = null;
            reader.beginObject();
            while (reader.hasNext()) {
                String field = reader.nextName();
                if (JsonToken.NULL == reader.peek()) {
                    reader.skipValue();
                } else if (ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION.equals(field)) {
                    version = Integer.valueOf(reader.nextInt());
                } else if ("target".equals(field)) {
                    target = reader.nextString();
                } else if (NativeProtocol.WEB_DIALOG_ACTION.equals(field)) {
                    action = reader.nextString();
                }
            }
            if (version.intValue() == 1) {
                if (target == null || action == null) {
                    response.close();
                    return;
                }
                triggerMessageCallback(target, action);
                response.close();
            }
        } catch (IOException e) {
            abort("Parsing response message from websocket failed", e);
        } finally {
            response.close();
        }
    }

    public void onFailure(IOException e, Response response) {
        if (this.mWebSocket != null) {
            abort("Websocket exception", e);
        }
        if (!this.mClosed) {
            reconnect();
        }
    }

    public void onOpen(WebSocket webSocket, Response response) {
        this.mWebSocket = webSocket;
        this.mSuppressConnectionErrors = false;
    }

    public void onClose(int code, String reason) {
        this.mWebSocket = null;
        if (!this.mClosed) {
            reconnect();
        }
    }

    public void onPong(Buffer payload) {
    }

    private void abort(String message, Throwable cause) {
        FLog.e(TAG, "Error occurred, shutting down websocket connection: " + message, cause);
        closeWebSocketQuietly();
    }
}

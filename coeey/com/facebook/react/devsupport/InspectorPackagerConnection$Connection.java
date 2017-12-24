package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import com.facebook.common.logging.FLog;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketCall;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;
import org.json.JSONException;
import org.json.JSONObject;

class InspectorPackagerConnection$Connection implements WebSocketListener {
    private static final int RECONNECT_DELAY_MS = 2000;
    private boolean mClosed;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mSuppressConnectionErrors;
    private final String mUrl;
    @Nullable
    private WebSocket mWebSocket;
    final /* synthetic */ InspectorPackagerConnection this$0;

    public InspectorPackagerConnection$Connection(InspectorPackagerConnection inspectorPackagerConnection, String url) {
        this.this$0 = inspectorPackagerConnection;
        this.mUrl = url;
    }

    public void onOpen(WebSocket webSocket, Response response) {
        this.mWebSocket = webSocket;
    }

    public void onFailure(IOException e, Response response) {
        if (this.mWebSocket != null) {
            abort("Websocket exception", e);
        }
        if (!this.mClosed) {
            reconnect();
        }
    }

    public void onMessage(ResponseBody message) throws IOException {
        try {
            this.this$0.handleProxyMessage(new JSONObject(message.string()));
            message.close();
        } catch (JSONException e) {
            throw new IOException(e);
        } catch (Throwable th) {
            message.close();
        }
    }

    public void onPong(Buffer payload) {
    }

    public void onClose(int code, String reason) {
        this.mWebSocket = null;
        this.this$0.closeAllConnections();
        if (!this.mClosed) {
            reconnect();
        }
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
            FLog.w("InspectorPackagerConnection", "Couldn't connect to packager, will silently retry");
            this.mSuppressConnectionErrors = true;
        }
        this.mHandler.postDelayed(new 1(this), 2000);
    }

    public void close() {
        this.mClosed = true;
        if (this.mWebSocket != null) {
            try {
                this.mWebSocket.close(1000, "End of session");
            } catch (IOException e) {
            }
            this.mWebSocket = null;
        }
    }

    public void send(JSONObject object) throws IOException {
        if (this.mWebSocket != null) {
            this.mWebSocket.sendMessage(RequestBody.create(WebSocket.TEXT, object.toString()));
        }
    }

    private void abort(String message, Throwable cause) {
        FLog.e("InspectorPackagerConnection", "Error occurred, shutting down websocket connection: " + message, cause);
        this.this$0.closeAllConnections();
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
}

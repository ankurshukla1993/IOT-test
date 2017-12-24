package com.facebook.react.modules.websocket;

import android.util.Base64;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.views.text.ReactTextShadowNode;
import com.facebook.share.internal.ShareConstants;
import java.io.IOException;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;

class WebSocketModule$1 implements WebSocketListener {
    final /* synthetic */ WebSocketModule this$0;
    final /* synthetic */ int val$id;

    WebSocketModule$1(WebSocketModule this$0, int i) {
        this.this$0 = this$0;
        this.val$id = i;
    }

    public void onOpen(WebSocket webSocket, Response response) {
        WebSocketModule.access$000(this.this$0).put(Integer.valueOf(this.val$id), webSocket);
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.val$id);
        WebSocketModule.access$100(this.this$0, "websocketOpen", params);
    }

    public void onClose(int code, String reason) {
        WritableMap params = Arguments.createMap();
        params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.val$id);
        params.putInt("code", code);
        params.putString("reason", reason);
        WebSocketModule.access$100(this.this$0, "websocketClosed", params);
    }

    public void onFailure(IOException e, Response response) {
        WebSocketModule.access$200(this.this$0, this.val$id, e.getMessage());
    }

    public void onPong(Buffer buffer) {
    }

    public void onMessage(ResponseBody response) throws IOException {
        try {
            String message;
            if (response.contentType() == WebSocket.BINARY) {
                message = Base64.encodeToString(response.source().readByteArray(), 2);
            } else {
                message = response.source().readUtf8();
            }
            try {
                response.source().close();
            } catch (Throwable e) {
                FLog.m112e(ReactConstants.TAG, "Could not close BufferedSource for WebSocket id " + this.val$id, e);
            }
            WritableMap params = Arguments.createMap();
            params.putInt(ShareConstants.WEB_DIALOG_PARAM_ID, this.val$id);
            params.putString("data", message);
            params.putString("type", response.contentType() == WebSocket.BINARY ? "binary" : ReactTextShadowNode.PROP_TEXT);
            WebSocketModule.access$100(this.this$0, "websocketMessage", params);
        } catch (IOException e2) {
            WebSocketModule.access$200(this.this$0, this.val$id, e2.getMessage());
        }
    }
}

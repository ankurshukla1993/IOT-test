package com.facebook.react.devsupport;

import android.os.Handler;
import javax.annotation.Nullable;

class WebsocketJavaScriptExecutor$2 implements JSDebuggerWebSocketClient$JSDebuggerCallback {
    private boolean didSendResult = false;
    final /* synthetic */ WebsocketJavaScriptExecutor this$0;
    final /* synthetic */ WebsocketJavaScriptExecutor$JSExecutorConnectCallback val$callback;
    final /* synthetic */ JSDebuggerWebSocketClient val$client;
    final /* synthetic */ Handler val$timeoutHandler;

    class C13071 implements JSDebuggerWebSocketClient$JSDebuggerCallback {
        C13071() {
        }

        public void onSuccess(@Nullable String response) {
            WebsocketJavaScriptExecutor$2.this.val$timeoutHandler.removeCallbacksAndMessages(null);
            WebsocketJavaScriptExecutor.access$102(WebsocketJavaScriptExecutor$2.this.this$0, WebsocketJavaScriptExecutor$2.this.val$client);
            if (!WebsocketJavaScriptExecutor$2.this.didSendResult) {
                WebsocketJavaScriptExecutor$2.this.val$callback.onSuccess();
                WebsocketJavaScriptExecutor$2.this.didSendResult = true;
            }
        }

        public void onFailure(Throwable cause) {
            WebsocketJavaScriptExecutor$2.this.val$timeoutHandler.removeCallbacksAndMessages(null);
            if (!WebsocketJavaScriptExecutor$2.this.didSendResult) {
                WebsocketJavaScriptExecutor$2.this.val$callback.onFailure(cause);
                WebsocketJavaScriptExecutor$2.this.didSendResult = true;
            }
        }
    }

    WebsocketJavaScriptExecutor$2(WebsocketJavaScriptExecutor this$0, JSDebuggerWebSocketClient jSDebuggerWebSocketClient, Handler handler, WebsocketJavaScriptExecutor$JSExecutorConnectCallback websocketJavaScriptExecutor$JSExecutorConnectCallback) {
        this.this$0 = this$0;
        this.val$client = jSDebuggerWebSocketClient;
        this.val$timeoutHandler = handler;
        this.val$callback = websocketJavaScriptExecutor$JSExecutorConnectCallback;
    }

    public void onSuccess(@Nullable String response) {
        this.val$client.prepareJSRuntime(new C13071());
    }

    public void onFailure(Throwable cause) {
        this.val$timeoutHandler.removeCallbacksAndMessages(null);
        if (!this.didSendResult) {
            this.val$callback.onFailure(cause);
            this.didSendResult = true;
        }
    }
}

package com.facebook.react.devsupport;

import java.util.concurrent.atomic.AtomicInteger;

class WebsocketJavaScriptExecutor$1 implements WebsocketJavaScriptExecutor$JSExecutorConnectCallback {
    final /* synthetic */ WebsocketJavaScriptExecutor this$0;
    final /* synthetic */ WebsocketJavaScriptExecutor$JSExecutorConnectCallback val$callback;
    final /* synthetic */ AtomicInteger val$retryCount;
    final /* synthetic */ String val$webSocketServerUrl;

    WebsocketJavaScriptExecutor$1(WebsocketJavaScriptExecutor this$0, WebsocketJavaScriptExecutor$JSExecutorConnectCallback websocketJavaScriptExecutor$JSExecutorConnectCallback, AtomicInteger atomicInteger, String str) {
        this.this$0 = this$0;
        this.val$callback = websocketJavaScriptExecutor$JSExecutorConnectCallback;
        this.val$retryCount = atomicInteger;
        this.val$webSocketServerUrl = str;
    }

    public void onSuccess() {
        this.val$callback.onSuccess();
    }

    public void onFailure(Throwable cause) {
        if (this.val$retryCount.decrementAndGet() <= 0) {
            this.val$callback.onFailure(cause);
        } else {
            WebsocketJavaScriptExecutor.access$000(this.this$0, this.val$webSocketServerUrl, this);
        }
    }
}

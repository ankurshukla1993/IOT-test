package com.facebook.react.devsupport;

class WebsocketJavaScriptExecutor$3 implements Runnable {
    final /* synthetic */ WebsocketJavaScriptExecutor this$0;
    final /* synthetic */ WebsocketJavaScriptExecutor$JSExecutorConnectCallback val$callback;
    final /* synthetic */ JSDebuggerWebSocketClient val$client;

    WebsocketJavaScriptExecutor$3(WebsocketJavaScriptExecutor this$0, JSDebuggerWebSocketClient jSDebuggerWebSocketClient, WebsocketJavaScriptExecutor$JSExecutorConnectCallback websocketJavaScriptExecutor$JSExecutorConnectCallback) {
        this.this$0 = this$0;
        this.val$client = jSDebuggerWebSocketClient;
        this.val$callback = websocketJavaScriptExecutor$JSExecutorConnectCallback;
    }

    public void run() {
        this.val$client.closeQuietly();
        this.val$callback.onFailure(new WebsocketJavaScriptExecutor$WebsocketExecutorTimeoutException("Timeout while connecting to remote debugger"));
    }
}

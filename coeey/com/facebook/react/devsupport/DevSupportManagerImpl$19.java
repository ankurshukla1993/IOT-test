package com.facebook.react.devsupport;

import okhttp3.ws.WebSocket;

class DevSupportManagerImpl$19 implements Runnable {
    final /* synthetic */ DevSupportManagerImpl this$0;
    final /* synthetic */ WebSocket val$webSocket;

    DevSupportManagerImpl$19(DevSupportManagerImpl this$0, WebSocket webSocket) {
        this.this$0 = this$0;
        this.val$webSocket = webSocket;
    }

    public void run() {
        DevSupportManagerImpl.access$900(this.this$0, this.val$webSocket);
    }
}

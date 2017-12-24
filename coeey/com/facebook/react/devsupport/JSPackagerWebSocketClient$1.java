package com.facebook.react.devsupport;

class JSPackagerWebSocketClient$1 implements Runnable {
    final /* synthetic */ JSPackagerWebSocketClient this$0;

    JSPackagerWebSocketClient$1(JSPackagerWebSocketClient this$0) {
        this.this$0 = this$0;
    }

    public void run() {
        if (!JSPackagerWebSocketClient.access$000(this.this$0)) {
            this.this$0.connect();
        }
    }
}

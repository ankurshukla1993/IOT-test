package com.facebook.react.devsupport;

import com.facebook.react.devsupport.InspectorPackagerConnection.Connection;

class InspectorPackagerConnection$Connection$1 implements Runnable {
    final /* synthetic */ Connection this$1;

    InspectorPackagerConnection$Connection$1(Connection this$1) {
        this.this$1 = this$1;
    }

    public void run() {
        if (!Connection.access$400(this.this$1)) {
            this.this$1.connect();
        }
    }
}

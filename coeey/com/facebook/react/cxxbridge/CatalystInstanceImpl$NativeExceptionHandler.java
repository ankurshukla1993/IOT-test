package com.facebook.react.cxxbridge;

import com.facebook.react.bridge.queue.QueueThreadExceptionHandler;

class CatalystInstanceImpl$NativeExceptionHandler implements QueueThreadExceptionHandler {
    final /* synthetic */ CatalystInstanceImpl this$0;

    private CatalystInstanceImpl$NativeExceptionHandler(CatalystInstanceImpl catalystInstanceImpl) {
        this.this$0 = catalystInstanceImpl;
    }

    public void handleException(Exception e) {
        CatalystInstanceImpl.access$400(this.this$0, e);
    }
}

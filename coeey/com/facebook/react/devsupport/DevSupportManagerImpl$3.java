package com.facebook.react.devsupport;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.devsupport.RedBoxHandler.ErrorType;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;

class DevSupportManagerImpl$3 implements Runnable {
    final /* synthetic */ DevSupportManagerImpl this$0;
    final /* synthetic */ ReadableArray val$details;
    final /* synthetic */ int val$errorCookie;
    final /* synthetic */ String val$message;

    DevSupportManagerImpl$3(DevSupportManagerImpl this$0, int i, ReadableArray readableArray, String str) {
        this.this$0 = this$0;
        this.val$errorCookie = i;
        this.val$details = readableArray;
        this.val$message = str;
    }

    public void run() {
        if (DevSupportManagerImpl.access$200(this.this$0) != null && DevSupportManagerImpl.access$200(this.this$0).isShowing() && this.val$errorCookie == DevSupportManagerImpl.access$300(this.this$0)) {
            StackFrame[] stack = StackTraceHelper.convertJsStackTrace(this.val$details);
            DevSupportManagerImpl.access$200(this.this$0).setExceptionDetails(this.val$message, stack);
            DevSupportManagerImpl.access$400(this.this$0, this.val$message, stack, this.val$errorCookie, DevSupportManagerImpl$ErrorType.JS);
            if (DevSupportManagerImpl.access$500(this.this$0) != null) {
                DevSupportManagerImpl.access$500(this.this$0).handleRedbox(this.val$message, stack, ErrorType.JS);
                DevSupportManagerImpl.access$200(this.this$0).resetReporting(true);
            }
            DevSupportManagerImpl.access$200(this.this$0).show();
        }
    }
}

package com.facebook.react.devsupport;

import com.cooey.devices.five_in_one.bluetooth.Const;
import com.facebook.react.devsupport.RedBoxHandler.ErrorType;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;

class DevSupportManagerImpl$4 implements Runnable {
    final /* synthetic */ DevSupportManagerImpl this$0;
    final /* synthetic */ int val$errorCookie;
    final /* synthetic */ DevSupportManagerImpl$ErrorType val$errorType;
    final /* synthetic */ String val$message;
    final /* synthetic */ StackFrame[] val$stack;

    DevSupportManagerImpl$4(DevSupportManagerImpl this$0, String str, StackFrame[] stackFrameArr, int i, DevSupportManagerImpl$ErrorType devSupportManagerImpl$ErrorType) {
        this.this$0 = this$0;
        this.val$message = str;
        this.val$stack = stackFrameArr;
        this.val$errorCookie = i;
        this.val$errorType = devSupportManagerImpl$ErrorType;
    }

    public void run() {
        if (DevSupportManagerImpl.access$200(this.this$0) == null) {
            DevSupportManagerImpl.access$202(this.this$0, new RedBoxDialog(DevSupportManagerImpl.access$600(this.this$0), this.this$0, DevSupportManagerImpl.access$500(this.this$0)));
            DevSupportManagerImpl.access$200(this.this$0).getWindow().setType(Const.MESSAGE_OXIMETER_PARAMS);
        }
        if (!DevSupportManagerImpl.access$200(this.this$0).isShowing()) {
            DevSupportManagerImpl.access$200(this.this$0).setExceptionDetails(this.val$message, this.val$stack);
            DevSupportManagerImpl.access$400(this.this$0, this.val$message, this.val$stack, this.val$errorCookie, this.val$errorType);
            if (DevSupportManagerImpl.access$500(this.this$0) == null || this.val$errorType != DevSupportManagerImpl$ErrorType.NATIVE) {
                DevSupportManagerImpl.access$200(this.this$0).resetReporting(false);
            } else {
                DevSupportManagerImpl.access$500(this.this$0).handleRedbox(this.val$message, this.val$stack, ErrorType.NATIVE);
                DevSupportManagerImpl.access$200(this.this$0).resetReporting(true);
            }
            DevSupportManagerImpl.access$200(this.this$0).show();
        }
    }
}

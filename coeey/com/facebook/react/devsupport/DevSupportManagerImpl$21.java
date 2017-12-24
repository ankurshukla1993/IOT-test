package com.facebook.react.devsupport;

import android.app.AlertDialog;
import com.facebook.common.logging.FLog;
import com.facebook.react.C1275R;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.common.futures.SimpleSettableFuture;
import java.io.IOException;

class DevSupportManagerImpl$21 implements WebsocketJavaScriptExecutor$JSExecutorConnectCallback {
    final /* synthetic */ DevSupportManagerImpl this$0;
    final /* synthetic */ SimpleSettableFuture val$future;
    final /* synthetic */ AlertDialog val$progressDialog;

    DevSupportManagerImpl$21(DevSupportManagerImpl this$0, SimpleSettableFuture simpleSettableFuture, AlertDialog alertDialog) {
        this.this$0 = this$0;
        this.val$future = simpleSettableFuture;
        this.val$progressDialog = alertDialog;
    }

    public void onSuccess() {
        this.val$future.set(Boolean.valueOf(true));
        this.val$progressDialog.dismiss();
    }

    public void onFailure(Throwable cause) {
        this.val$progressDialog.dismiss();
        FLog.m112e(ReactConstants.TAG, "Unable to connect to remote debugger", cause);
        this.val$future.setException(new IOException(DevSupportManagerImpl.access$600(this.this$0).getString(C1275R.string.catalyst_remotedbg_error), cause));
    }
}

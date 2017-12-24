package com.facebook.react.devsupport;

import android.app.AlertDialog;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.bridge.JavaJSExecutor.Factory;
import com.facebook.react.common.futures.SimpleSettableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class DevSupportManagerImpl$20 implements Factory {
    final /* synthetic */ DevSupportManagerImpl this$0;
    final /* synthetic */ AlertDialog val$progressDialog;

    DevSupportManagerImpl$20(DevSupportManagerImpl this$0, AlertDialog alertDialog) {
        this.this$0 = this$0;
        this.val$progressDialog = alertDialog;
    }

    public JavaJSExecutor create() throws Exception {
        Exception e;
        WebsocketJavaScriptExecutor executor = new WebsocketJavaScriptExecutor();
        SimpleSettableFuture<Boolean> future = new SimpleSettableFuture();
        executor.connect(DevSupportManagerImpl.access$100(this.this$0).getWebsocketProxyURL(), DevSupportManagerImpl.access$1200(this.this$0, this.val$progressDialog, future));
        try {
            future.get(90, TimeUnit.SECONDS);
            return executor;
        } catch (ExecutionException e2) {
            throw ((Exception) e2.getCause());
        } catch (InterruptedException e3) {
            e = e3;
            throw new RuntimeException(e);
        } catch (TimeoutException e4) {
            e = e4;
            throw new RuntimeException(e);
        }
    }
}

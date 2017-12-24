package com.facebook.react.devsupport;

import android.app.AlertDialog;
import com.facebook.common.logging.FLog;
import com.facebook.react.C1275R;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.ReactConstants;

class DevSupportManagerImpl$22 implements DevServerHelper$BundleDownloadCallback {
    final /* synthetic */ DevSupportManagerImpl this$0;
    final /* synthetic */ AlertDialog val$progressDialog;

    class C12991 implements Runnable {
        C12991() {
        }

        public void run() {
            DevSupportManagerImpl.access$700(DevSupportManagerImpl$22.this.this$0).onJSBundleLoadedFromServer();
        }
    }

    DevSupportManagerImpl$22(DevSupportManagerImpl this$0, AlertDialog alertDialog) {
        this.this$0 = this$0;
        this.val$progressDialog = alertDialog;
    }

    public void onSuccess() {
        this.val$progressDialog.dismiss();
        UiThreadUtil.runOnUiThread(new C12991());
    }

    public void onFailure(final Exception cause) {
        this.val$progressDialog.dismiss();
        FLog.m112e(ReactConstants.TAG, "Unable to download JS bundle", (Throwable) cause);
        UiThreadUtil.runOnUiThread(new Runnable() {
            public void run() {
                if (cause instanceof DebugServerException) {
                    DevSupportManagerImpl$22.this.this$0.showNewJavaError(cause.getMessage(), cause);
                    return;
                }
                DevSupportManagerImpl$22.this.this$0.showNewJavaError(DevSupportManagerImpl.access$600(DevSupportManagerImpl$22.this.this$0).getString(C1275R.string.catalyst_jsload_error), cause);
            }
        });
    }
}

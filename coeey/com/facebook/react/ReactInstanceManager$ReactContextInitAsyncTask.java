package com.facebook.react;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Process;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactInstanceManager.Result;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.ReactConstants;

final class ReactInstanceManager$ReactContextInitAsyncTask extends AsyncTask<ReactInstanceManager$ReactContextInitParams, Void, Result<ReactApplicationContext>> {
    final /* synthetic */ ReactInstanceManager this$0;

    private ReactInstanceManager$ReactContextInitAsyncTask(ReactInstanceManager reactInstanceManager) {
        this.this$0 = reactInstanceManager;
    }

    protected void onPreExecute() {
        if (ReactInstanceManager.access$400(this.this$0) != null) {
            ReactInstanceManager.access$500(this.this$0, ReactInstanceManager.access$400(this.this$0));
            ReactInstanceManager.access$402(this.this$0, null);
        }
    }

    protected Result<ReactApplicationContext> doInBackground(ReactInstanceManager$ReactContextInitParams... params) {
        boolean z = false;
        Process.setThreadPriority(0);
        if (!(params == null || params.length <= 0 || params[0] == null)) {
            z = true;
        }
        Assertions.assertCondition(z);
        try {
            return Result.of(ReactInstanceManager.access$600(this.this$0, params[0].getJsExecutorFactory().create(), params[0].getJsBundleLoader()));
        } catch (Exception e) {
            return Result.of(e);
        }
    }

    protected void onPostExecute(Result<ReactApplicationContext> result) {
        try {
            ReactInstanceManager.access$700(this.this$0, (ReactApplicationContext) result.get());
        } catch (Exception e) {
            ReactInstanceManager.access$800(this.this$0).handleException(e);
        } finally {
            ReactInstanceManager.access$902(this.this$0, null);
        }
        if (ReactInstanceManager.access$1000(this.this$0) != null) {
            ReactInstanceManager.access$1100(this.this$0, ReactInstanceManager.access$1000(this.this$0).getJsExecutorFactory(), ReactInstanceManager.access$1000(this.this$0).getJsBundleLoader());
            ReactInstanceManager.access$1002(this.this$0, null);
        }
    }

    protected void onCancelled(Result<ReactApplicationContext> reactApplicationContextResult) {
        try {
            ReactInstanceManager.access$1200(this.this$0).destroy((Context) reactApplicationContextResult.get());
        } catch (Throwable e) {
            FLog.m152w(ReactConstants.TAG, "Caught exception after cancelling react context init", e);
        } finally {
            ReactInstanceManager.access$902(this.this$0, null);
        }
    }
}

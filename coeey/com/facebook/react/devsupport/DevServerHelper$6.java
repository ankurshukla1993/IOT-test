package com.facebook.react.devsupport;

import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

class DevServerHelper$6 implements Callback {
    final /* synthetic */ DevServerHelper this$0;
    final /* synthetic */ DevServerHelper$PackagerStatusCallback val$callback;

    DevServerHelper$6(DevServerHelper this$0, DevServerHelper$PackagerStatusCallback devServerHelper$PackagerStatusCallback) {
        this.this$0 = this$0;
        this.val$callback = devServerHelper$PackagerStatusCallback;
    }

    public void onFailure(Call call, IOException e) {
        FLog.m151w(ReactConstants.TAG, "The packager does not seem to be running as we got an IOException requesting its status: " + e.getMessage());
        this.val$callback.onPackagerStatusFetched(false);
    }

    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            if (body == null) {
                FLog.m111e(ReactConstants.TAG, "Got null body response from packager when requesting status");
                this.val$callback.onPackagerStatusFetched(false);
                return;
            } else if ("packager-status:running".equals(body.string())) {
                this.val$callback.onPackagerStatusFetched(true);
                return;
            } else {
                FLog.m111e(ReactConstants.TAG, "Got unexpected response from packager when requesting status: " + body.string());
                this.val$callback.onPackagerStatusFetched(false);
                return;
            }
        }
        FLog.m111e(ReactConstants.TAG, "Got non-success http code from packager when requesting status: " + response.code());
        this.val$callback.onPackagerStatusFetched(false);
    }
}

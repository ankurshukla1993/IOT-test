package com.facebook.react.devsupport;

import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import com.ihealth.communication.manager.iHealthDevicesManager.ScanDevice;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

class DevServerHelper$8 implements Callback {
    final /* synthetic */ DevServerHelper this$0;

    class C12981 implements Runnable {
        C12981() {
        }

        public void run() {
            DevServerHelper.access$600(DevServerHelper$8.this.this$0, false);
        }
    }

    DevServerHelper$8(DevServerHelper this$0) {
        this.this$0 = this$0;
    }

    public void onFailure(Call call, IOException e) {
        if (DevServerHelper.access$500(this.this$0)) {
            FLog.m104d(ReactConstants.TAG, "Error while requesting /onchange endpoint", (Throwable) e);
            DevServerHelper.access$700(this.this$0).postDelayed(new C12981(), 5000);
        }
    }

    public void onResponse(Call call, Response response) throws IOException {
        DevServerHelper.access$600(this.this$0, response.code() == ScanDevice.LINK_AU);
    }
}

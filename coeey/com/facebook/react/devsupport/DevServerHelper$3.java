package com.facebook.react.devsupport;

import android.os.AsyncTask;

class DevServerHelper$3 extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ DevServerHelper this$0;

    DevServerHelper$3(DevServerHelper this$0) {
        this.this$0 = this$0;
    }

    protected Void doInBackground(Void... params) {
        DevServerHelper.access$202(this.this$0, new InspectorPackagerConnection(this.this$0.getInspectorDeviceUrl()));
        DevServerHelper.access$200(this.this$0).connect();
        return null;
    }
}

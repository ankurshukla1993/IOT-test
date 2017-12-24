package com.facebook.react.devsupport;

import android.os.AsyncTask;

class DevServerHelper$2 extends AsyncTask<Void, Void, Void> {
    final /* synthetic */ DevServerHelper this$0;

    DevServerHelper$2(DevServerHelper this$0) {
        this.this$0 = this$0;
    }

    protected Void doInBackground(Void... params) {
        if (DevServerHelper.access$000(this.this$0) != null) {
            DevServerHelper.access$000(this.this$0).closeQuietly();
            DevServerHelper.access$002(this.this$0, null);
        }
        return null;
    }
}

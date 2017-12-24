package com.facebook.react.devsupport;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class DevSupportManagerImpl$23 implements OnCancelListener {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$23(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onCancel(DialogInterface dialog) {
        DevSupportManagerImpl.access$100(this.this$0).cancelDownloadBundleFromURL();
    }
}

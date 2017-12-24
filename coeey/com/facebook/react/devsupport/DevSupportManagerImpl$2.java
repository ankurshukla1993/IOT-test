package com.facebook.react.devsupport;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class DevSupportManagerImpl$2 extends BroadcastReceiver {
    final /* synthetic */ DevSupportManagerImpl this$0;

    DevSupportManagerImpl$2(DevSupportManagerImpl this$0) {
        this.this$0 = this$0;
    }

    public void onReceive(Context context, Intent intent) {
        if (DevServerHelper.getReloadAppAction(context).equals(intent.getAction())) {
            if (intent.getBooleanExtra(DevServerHelper.RELOAD_APP_EXTRA_JS_PROXY, false)) {
                DevSupportManagerImpl.access$000(this.this$0).setRemoteJSDebugEnabled(true);
                DevSupportManagerImpl.access$100(this.this$0).launchJSDevtools();
            } else {
                DevSupportManagerImpl.access$000(this.this$0).setRemoteJSDebugEnabled(false);
            }
            this.this$0.handleReloadJS();
        }
    }
}

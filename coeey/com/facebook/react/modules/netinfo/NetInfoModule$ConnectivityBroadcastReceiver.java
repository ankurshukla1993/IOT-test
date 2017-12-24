package com.facebook.react.modules.netinfo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class NetInfoModule$ConnectivityBroadcastReceiver extends BroadcastReceiver {
    private boolean isRegistered;
    final /* synthetic */ NetInfoModule this$0;

    private NetInfoModule$ConnectivityBroadcastReceiver(NetInfoModule netInfoModule) {
        this.this$0 = netInfoModule;
        this.isRegistered = false;
    }

    public void setRegistered(boolean registered) {
        this.isRegistered = registered;
    }

    public boolean isRegistered() {
        return this.isRegistered;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            NetInfoModule.access$100(this.this$0);
        }
    }
}

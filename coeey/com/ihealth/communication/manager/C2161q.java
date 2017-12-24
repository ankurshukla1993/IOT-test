package com.ihealth.communication.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.ihealth.communication.ins.F0InsSet;
import com.ihealth.communication.utils.Log;

class C2161q extends BroadcastReceiver {
    final /* synthetic */ iHealthDevicesUpgradeManager f2020a;

    C2161q(iHealthDevicesUpgradeManager com_ihealth_communication_manager_iHealthDevicesUpgradeManager) {
        this.f2020a = com_ihealth_communication_manager_iHealthDevicesUpgradeManager;
    }

    public void onReceive(Context context, Intent intent) {
        this.f2020a.f2006m = false;
        this.f2020a.f1999f = true;
        if (F0InsSet.MSG_DISCONNECTED.equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra(iHealthDevicesManager.IHEALTH_DEVICE_MAC);
            this.f2020a.f1994a.remove(stringExtra);
            this.f2020a.f2002i = null;
            if (this.f2020a.f2003j != null) {
                this.f2020a.f2003j.a();
            } else {
                Log.m1215w("iHealthDUM", "mCommCloudAMInstall is null");
            }
            this.f2020a.m1166a(stringExtra, this.f2020a.f2000g, 300);
        }
    }
}

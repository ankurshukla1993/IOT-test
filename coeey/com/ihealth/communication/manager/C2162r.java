package com.ihealth.communication.manager;

import android.os.SystemClock;
import com.ihealth.communication.utils.Log;

class C2162r extends Thread {
    final /* synthetic */ String f2021a;
    final /* synthetic */ String f2022b;
    final /* synthetic */ iHealthDevicesUpgradeManager f2023c;

    C2162r(iHealthDevicesUpgradeManager com_ihealth_communication_manager_iHealthDevicesUpgradeManager, String str, String str2) {
        this.f2023c = com_ihealth_communication_manager_iHealthDevicesUpgradeManager;
        this.f2021a = str;
        this.f2022b = str2;
    }

    public void run() {
        super.run();
        this.f2023c.f2002i = this.f2023c.getUpDeviceControl(this.f2021a, this.f2022b);
        if (this.f2023c.f2002i == null) {
            Log.m1215w("iHealthDUM", "mUpDeviceControl is null for mac = " + this.f2021a + "   type = " + this.f2022b);
            this.f2023c.m1166a(this.f2021a, this.f2022b, 300);
        } else if (!this.f2023c.isUpgradeState(this.f2021a, this.f2022b)) {
            SystemClock.sleep(500);
            this.f2023c.f2002i.borrowComm();
            this.f2023c.f2002i.judgUpgrade();
        }
    }
}

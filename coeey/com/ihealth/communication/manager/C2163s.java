package com.ihealth.communication.manager;

import android.os.SystemClock;
import com.ihealth.communication.control.UpgradeProfile;
import com.ihealth.communication.utils.Log;

class C2163s extends Thread {
    final /* synthetic */ String f2024a;
    final /* synthetic */ String f2025b;
    final /* synthetic */ iHealthDevicesUpgradeManager f2026c;

    C2163s(iHealthDevicesUpgradeManager com_ihealth_communication_manager_iHealthDevicesUpgradeManager, String str, String str2) {
        this.f2026c = com_ihealth_communication_manager_iHealthDevicesUpgradeManager;
        this.f2024a = str;
        this.f2025b = str2;
    }

    public void run() {
        super.run();
        this.f2026c.f2002i = this.f2026c.getUpDeviceControl(this.f2024a, this.f2025b);
        if (this.f2026c.f2002i == null) {
            Log.m1215w("iHealthDUM", "mUpDeviceControl is null for mac = " + this.f2024a + "   type = " + this.f2025b);
            this.f2026c.m1166a(this.f2024a, this.f2025b, 300);
            return;
        }
        SystemClock.sleep(500);
        if (this.f2026c.f2003j != null) {
            this.f2026c.f2003j.a();
        }
        if (this.f2026c.f2002i != null) {
            this.f2026c.f2002i.stopUpgrade();
            this.f2026c.f2002i.returnComm();
        }
        this.f2026c.f2004k.onNotify(this.f2024a, this.f2025b, UpgradeProfile.ACTION_DEVICE_STOP_UP, null);
    }
}

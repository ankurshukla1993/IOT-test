package com.lifesense.ble;

import com.lifesense.ble.p003a.C2220h;
import java.util.TimerTask;

class C2261h extends TimerTask {
    final /* synthetic */ LsBleManager f2459a;
    private final /* synthetic */ String f2460b;

    C2261h(LsBleManager lsBleManager, String str) {
        this.f2459a = lsBleManager;
        this.f2460b = str;
    }

    public void run() {
        if (this.f2459a.disableConnectDeviceList != null && this.f2459a.disableConnectDeviceList.size() > 0 && this.f2460b != null && this.f2459a.disableConnectDeviceList.contains(this.f2460b)) {
            C2220h.m1596a((Object) this, "Enable reconnect device with broadcastID(" + this.f2460b + ")", 3);
            this.f2459a.disableConnectDeviceList.remove(this.f2460b);
        }
    }
}

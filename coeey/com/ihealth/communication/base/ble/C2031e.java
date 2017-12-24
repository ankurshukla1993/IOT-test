package com.ihealth.communication.base.ble;

import java.util.TimerTask;

class C2031e extends TimerTask {
    final /* synthetic */ Ble f321a;

    C2031e(Ble ble) {
        this.f321a = ble;
    }

    public void run() {
        this.f321a.f303y.disconnect();
    }
}

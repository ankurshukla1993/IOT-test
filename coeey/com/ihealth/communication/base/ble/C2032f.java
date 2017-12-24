package com.ihealth.communication.base.ble;

import java.util.TimerTask;

class C2032f extends TimerTask {
    final /* synthetic */ Ble f322a;

    C2032f(Ble ble) {
        this.f322a = ble;
    }

    public void run() {
        this.f322a.f303y.disconnect();
    }
}

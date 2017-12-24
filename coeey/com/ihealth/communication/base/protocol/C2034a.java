package com.ihealth.communication.base.protocol;

import android.os.SystemClock;
import java.util.TimerTask;

class C2034a extends TimerTask {
    final /* synthetic */ BleCommProtocol f417a;

    C2034a(BleCommProtocol bleCommProtocol) {
        this.f417a = bleCommProtocol;
    }

    public void run() {
        while (!this.f417a.f362h.isEmpty()) {
            this.f417a.f356b.sendData(this.f417a.f358d, (byte[]) this.f417a.f362h.poll());
            SystemClock.sleep(10);
        }
        this.f417a.m280a(false);
    }
}

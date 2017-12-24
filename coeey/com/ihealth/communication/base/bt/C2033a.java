package com.ihealth.communication.base.bt;

import java.util.TimerTask;

class C2033a extends TimerTask {
    final /* synthetic */ BtCommThreadEE f350a;

    C2033a(BtCommThreadEE btCommThreadEE) {
        this.f350a = btCommThreadEE;
    }

    public void run() {
        this.f350a.close();
    }
}

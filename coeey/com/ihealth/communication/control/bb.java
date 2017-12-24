package com.ihealth.communication.control;

import java.util.TimerTask;

class bb extends TimerTask {
    final /* synthetic */ Bg1Control f1286a;

    bb(Bg1Control bg1Control) {
        this.f1286a = bg1Control;
    }

    public void run() {
        this.f1286a.m512d();
        if (this.f1286a.f1068n == null) {
            this.f1286a.f1068n = new Thread(this.f1286a.f1054J);
            this.f1286a.f1068n.start();
        }
    }
}

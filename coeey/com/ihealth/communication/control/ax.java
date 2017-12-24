package com.ihealth.communication.control;

import com.ihealth.communication.utils.Log;

class ax extends Thread {
    final /* synthetic */ byte f1279a;
    final /* synthetic */ int f1280b;
    final /* synthetic */ Bg1Control f1281c;

    ax(Bg1Control bg1Control, byte b, int i) {
        this.f1281c = bg1Control;
        this.f1279a = b;
        this.f1280b = i;
    }

    public void run() {
        try {
            this.f1281c.m542r();
            if (this.f1281c.f1059e != null) {
                this.f1281c.f1059e.sendACK((byte) 0, this.f1279a, this.f1280b);
            }
            this.f1281c.m543s();
        } catch (Exception e) {
            Log.w(Bg1Control.f1042a, e.toString());
        }
    }
}

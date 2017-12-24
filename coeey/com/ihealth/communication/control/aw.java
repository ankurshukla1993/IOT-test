package com.ihealth.communication.control;

class aw extends Thread {
    final /* synthetic */ Bg1Control f1278a;

    aw(Bg1Control bg1Control) {
        this.f1278a = bg1Control;
    }

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.f1278a.m542r();
        this.f1278a.f1046B = this.f1278a.m535m();
        this.f1278a.m543s();
        this.f1278a.f1051G = false;
    }
}

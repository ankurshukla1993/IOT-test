package com.ihealth.communication.control;

class bg extends Thread {
    final /* synthetic */ Bg1Control f1293a;

    bg(Bg1Control bg1Control) {
        this.f1293a = bg1Control;
    }

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!this.f1293a.f1046B) {
            this.f1293a.m542r();
            this.f1293a.f1046B = this.f1293a.m535m();
            this.f1293a.m543s();
        }
        this.f1293a.f1051G = false;
    }
}

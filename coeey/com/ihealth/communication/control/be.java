package com.ihealth.communication.control;

class be implements Runnable {
    final /* synthetic */ Bg1Control f1291a;

    be(Bg1Control bg1Control) {
        this.f1291a = bg1Control;
    }

    public void run() {
        this.f1291a.f1059e.sendCommand();
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.utils.Log;
import java.util.TimerTask;

class bf extends TimerTask {
    final /* synthetic */ Bg1Control f1292a;

    bf(Bg1Control bg1Control) {
        this.f1292a = bg1Control;
    }

    public void run() {
        this.f1292a.f1052H = 0;
        Log.v(Bg1Control.f1042a, "reset commandID");
    }
}

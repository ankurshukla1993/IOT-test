package com.ihealth.communication.base.protocol;

import com.ihealth.communication.utils.Log;
import java.util.TimerTask;

class C2036c extends TimerTask {
    final /* synthetic */ Bp7sBtCommProtocol f419a;

    C2036c(Bp7sBtCommProtocol bp7sBtCommProtocol) {
        this.f419a = bp7sBtCommProtocol;
    }

    public void run() {
        this.f419a.m297b();
        Bp7sBtCommProtocol bp7sBtCommProtocol = this.f419a;
        bp7sBtCommProtocol.f371a++;
        if (this.f419a.f371a >= 3) {
            Log.w("BtCommProtocol >>>", "repeatSendTimer() -- failed");
            this.f419a.m300c();
        }
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class dw implements C2026d {
    final /* synthetic */ byte f1405a;
    final /* synthetic */ BtmControl f1406b;

    dw(BtmControl btmControl, byte b) {
        this.f1406b = btmControl;
        this.f1405a = b;
    }

    public void mo5513a() {
        this.f1406b.f1157c.setMeasuringTarget(this.f1405a);
    }
}

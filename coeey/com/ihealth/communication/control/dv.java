package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class dv implements C2026d {
    final /* synthetic */ byte f1403a;
    final /* synthetic */ BtmControl f1404b;

    dv(BtmControl btmControl, byte b) {
        this.f1404b = btmControl;
        this.f1403a = b;
    }

    public void mo5513a() {
        this.f1404b.f1157c.setTemperatureUnit(this.f1403a);
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class dx implements C2026d {
    final /* synthetic */ byte f1407a;
    final /* synthetic */ BtmControl f1408b;

    dx(BtmControl btmControl, byte b) {
        this.f1408b = btmControl;
        this.f1407a = b;
    }

    public void mo5513a() {
        this.f1408b.f1157c.setOfflineTarget(this.f1407a);
    }
}

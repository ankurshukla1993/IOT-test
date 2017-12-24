package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class du implements C2026d {
    final /* synthetic */ int f1399a;
    final /* synthetic */ int f1400b;
    final /* synthetic */ int f1401c;
    final /* synthetic */ BtmControl f1402d;

    du(BtmControl btmControl, int i, int i2, int i3) {
        this.f1402d = btmControl;
        this.f1399a = i;
        this.f1400b = i2;
        this.f1401c = i3;
    }

    public void mo5513a() {
        this.f1402d.f1157c.setStandbyTime(this.f1399a, this.f1400b, this.f1401c);
    }
}

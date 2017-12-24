package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class ee implements C2026d {
    final /* synthetic */ int f1419a;
    final /* synthetic */ int f1420b;
    final /* synthetic */ Hs4sControl f1421c;

    ee(Hs4sControl hs4sControl, int i, int i2) {
        this.f1421c = hs4sControl;
        this.f1419a = i;
        this.f1420b = i2;
    }

    public void mo5513a() {
        this.f1421c.f1178c.setUnitAndUserId(this.f1419a, this.f1420b);
        this.f1421c.f1178c.stopLink(1);
    }
}

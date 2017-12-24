package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class eb implements C2026d {
    final /* synthetic */ int f1414a;
    final /* synthetic */ int f1415b;
    final /* synthetic */ Hs4Control f1416c;

    eb(Hs4Control hs4Control, int i, int i2) {
        this.f1416c = hs4Control;
        this.f1414a = i;
        this.f1415b = i2;
    }

    public void mo5513a() {
        this.f1416c.f1170c.setUnitAndUserId(this.f1414a, this.f1415b);
        this.f1416c.f1170c.stopLink(1);
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class dq implements C2026d {
    final /* synthetic */ int f1390a;
    final /* synthetic */ Bp7sControl f1391b;

    dq(Bp7sControl bp7sControl, int i) {
        this.f1391b = bp7sControl;
        this.f1390a = i;
    }

    public void mo5513a() {
        if (this.f1390a < 0 || this.f1390a > 1) {
            this.f1391b.m578a(400, "setUnit() parameter unit should be 0 or 1 ");
        } else {
            this.f1391b.f1141b.setUnit(this.f1390a);
        }
    }
}

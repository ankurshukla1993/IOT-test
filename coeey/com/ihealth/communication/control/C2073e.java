package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class C2073e implements C2026d {
    final /* synthetic */ int f1411a;
    final /* synthetic */ ABPMControl f1412b;

    C2073e(ABPMControl aBPMControl, int i) {
        this.f1412b = aBPMControl;
        this.f1411a = i;
    }

    public void mo5513a() {
        this.f1412b.f1007b.setDisplayUnit(this.f1411a);
    }
}

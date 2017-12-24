package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class C2069b implements C2026d {
    final /* synthetic */ ABPMControl f1284a;

    C2069b(ABPMControl aBPMControl) {
        this.f1284a = aBPMControl;
    }

    public void mo5513a() {
        this.f1284a.f1007b.getOfflineData = true;
        this.f1284a.f1007b.getOffLineDataNum();
    }
}

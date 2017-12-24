package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class dc implements C2026d {
    final /* synthetic */ Bp5sControl f1367a;

    dc(Bp5sControl bp5sControl) {
        this.f1367a = bp5sControl;
    }

    public void mo5513a() {
        this.f1367a.f1124b.getOfflineData = true;
        this.f1367a.f1124b.getOfflineDataNum();
    }
}

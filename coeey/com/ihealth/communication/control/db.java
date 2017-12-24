package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class db implements C2026d {
    final /* synthetic */ Bp5sControl f1366a;

    db(Bp5sControl bp5sControl) {
        this.f1366a = bp5sControl;
    }

    public void mo5513a() {
        this.f1366a.f1124b.getOfflineData = false;
        this.f1366a.f1124b.getOfflineDataNum();
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class C2072do implements C2026d {
    final /* synthetic */ Bp7sControl f1388a;

    C2072do(Bp7sControl bp7sControl) {
        this.f1388a = bp7sControl;
    }

    public void mo5513a() {
        this.f1388a.f1141b.getOfflineData = false;
        this.f1388a.f1141b.setMemory_Size(1);
        this.f1388a.f1141b.getOffLineDataNum();
    }
}

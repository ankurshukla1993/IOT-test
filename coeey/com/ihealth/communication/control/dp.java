package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class dp implements C2026d {
    final /* synthetic */ Bp7sControl f1389a;

    dp(Bp7sControl bp7sControl) {
        this.f1389a = bp7sControl;
    }

    public void mo5513a() {
        this.f1389a.f1141b.getOfflineData = true;
        this.f1389a.f1141b.setMemory_Size(1);
        this.f1389a.f1141b.getOffLineDataNum();
    }
}

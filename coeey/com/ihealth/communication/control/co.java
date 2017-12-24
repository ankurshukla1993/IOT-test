package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class co implements C2026d {
    final /* synthetic */ Bp550BTControl f1352a;

    co(Bp550BTControl bp550BTControl) {
        this.f1352a = bp550BTControl;
    }

    public void mo5513a() {
        this.f1352a.f1114b.getOfflineData = false;
        this.f1352a.f1114b.setMemory_Size(1);
        this.f1352a.f1114b.getOffLineDataNum();
    }
}

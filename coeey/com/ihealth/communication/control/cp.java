package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class cp implements C2026d {
    final /* synthetic */ Bp550BTControl f1353a;

    cp(Bp550BTControl bp550BTControl) {
        this.f1353a = bp550BTControl;
    }

    public void mo5513a() {
        this.f1353a.f1114b.getOfflineData = true;
        this.f1353a.f1114b.setMemory_Size(1);
        this.f1353a.f1114b.getOffLineDataNum();
    }
}

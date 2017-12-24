package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;
import com.ihealth.communication.privatecontrol.AbiControlSubManager;

class C2080l implements C2026d {
    final /* synthetic */ AbiControl f1467a;

    C2080l(AbiControl abiControl) {
        this.f1467a = abiControl;
    }

    public void mo5513a() {
        AbiControlSubManager.getInstance().getBattery(this.f1467a.f1011a);
    }
}

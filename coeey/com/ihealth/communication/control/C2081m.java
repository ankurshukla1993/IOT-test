package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;
import com.ihealth.communication.privatecontrol.AbiControlSubManager;

class C2081m implements C2026d {
    final /* synthetic */ AbiControl f1468a;

    C2081m(AbiControl abiControl) {
        this.f1468a = abiControl;
    }

    public void mo5513a() {
        AbiControlSubManager.getInstance().startMeasure(this.f1468a.f1011a);
    }
}

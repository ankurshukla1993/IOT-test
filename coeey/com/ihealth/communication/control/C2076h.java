package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class C2076h implements C2026d {
    final /* synthetic */ int[][] f1462a;
    final /* synthetic */ ABPMControl f1463b;

    C2076h(ABPMControl aBPMControl, int[][] iArr) {
        this.f1463b = aBPMControl;
        this.f1462a = iArr;
    }

    public void mo5513a() {
        this.f1463b.f1007b.setAlarm(this.f1462a);
    }
}

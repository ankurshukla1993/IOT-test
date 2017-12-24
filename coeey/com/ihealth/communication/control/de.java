package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class de implements C2026d {
    final /* synthetic */ int f1377a;
    final /* synthetic */ Bp5sControl f1378b;

    de(Bp5sControl bp5sControl, int i) {
        this.f1378b = bp5sControl;
        this.f1377a = i;
    }

    public void mo5513a() {
        this.f1378b.f1124b.deleteRepeatedlyMeasureSetting(this.f1377a);
    }
}

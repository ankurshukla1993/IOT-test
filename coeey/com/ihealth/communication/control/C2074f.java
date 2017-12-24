package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class C2074f implements C2026d {
    final /* synthetic */ int f1457a;
    final /* synthetic */ boolean f1458b;
    final /* synthetic */ int[][] f1459c;
    final /* synthetic */ ABPMControl f1460d;

    C2074f(ABPMControl aBPMControl, int i, boolean z, int[][] iArr) {
        this.f1460d = aBPMControl;
        this.f1457a = i;
        this.f1458b = z;
        this.f1459c = iArr;
    }

    public void mo5513a() {
        this.f1460d.f1007b.setMeasureTime(this.f1457a, this.f1458b, this.f1459c);
    }
}

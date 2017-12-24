package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class el implements C2026d {
    final /* synthetic */ int f1444a;
    final /* synthetic */ int f1445b;
    final /* synthetic */ Hs5Control f1446c;

    el(Hs5Control hs5Control, int i, int i2) {
        this.f1446c = hs5Control;
        this.f1444a = i;
        this.f1445b = i2;
    }

    public void mo5513a() {
        if (this.f1444a < 0 || this.f1444a > 19) {
            this.f1446c.m601a("startMeasure() parameter userPstCode should be in the range [0, 19].", Integer.valueOf(this.f1444a));
        } else if (this.f1445b < 1) {
            this.f1446c.m601a("startMeasure() parameter userId should be in the range [1, 2147483647(0x7FFFFFFF)].", Integer.valueOf(this.f1445b));
        } else {
            this.f1446c.f1185b.creatMeasurementCnn(this.f1444a, this.f1445b);
        }
    }
}

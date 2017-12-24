package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class C2090w implements C2026d {
    final /* synthetic */ int f1480a;
    final /* synthetic */ C2067n f1481b;

    C2090w(C2067n c2067n, int i) {
        this.f1481b = c2067n;
        this.f1480a = i;
    }

    public void mo5513a() {
        if (this.f1480a < 0 || this.f1480a > 5) {
            this.f1481b.m480a("setHourMode() parameter hourMode should be in range [0, 5]");
        } else {
            this.f1481b.f1014b.x02Ins(this.f1480a);
        }
    }
}

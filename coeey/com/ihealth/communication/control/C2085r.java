package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class C2085r implements C2026d {
    final /* synthetic */ int f1474a;
    final /* synthetic */ C2067n f1475b;

    C2085r(C2067n c2067n, int i) {
        this.f1475b = c2067n;
        this.f1474a = i;
    }

    public void mo5513a() {
        if (this.f1474a < 1 || this.f1474a > 32767) {
            this.f1475b.m480a("setUserBmr() parameter bmr should be in range [1, 32767(0x7FFF)]");
        } else {
            this.f1475b.f1014b.b7Ins(this.f1474a);
        }
    }
}

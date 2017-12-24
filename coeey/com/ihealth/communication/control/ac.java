package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class ac implements C2026d {
    final /* synthetic */ int f1239a;
    final /* synthetic */ C2067n f1240b;

    ac(C2067n c2067n, int i) {
        this.f1240b = c2067n;
        this.f1239a = i;
    }

    public void mo5513a() {
        if (this.f1239a < 0 || this.f1239a > 3) {
            this.f1240b.m480a("setMode() parameter mode should be 0, 1 , 2 or 3.");
        } else {
            this.f1240b.f1014b.b5Ins(this.f1239a);
        }
    }
}

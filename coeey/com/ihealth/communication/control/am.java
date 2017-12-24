package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class am implements C2026d {
    final /* synthetic */ int f1263a;
    final /* synthetic */ C2067n f1264b;

    am(C2067n c2067n, int i) {
        this.f1264b = c2067n;
        this.f1263a = i;
    }

    public void mo5513a() {
        if (this.f1263a < 1 || this.f1263a > 3) {
            this.f1264b.m480a("deleteAlarmClock() parameter id should be 1, 2, or 3.");
        } else {
            this.f1264b.f1014b.a9Ins(this.f1263a);
        }
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class C2082o implements C2026d {
    final /* synthetic */ int f1469a;
    final /* synthetic */ C2067n f1470b;

    C2082o(C2067n c2067n, int i) {
        this.f1470b = c2067n;
        this.f1469a = i;
    }

    public void mo5513a() {
        if (this.f1469a < 1) {
            this.f1470b.m480a("reset() parameter id should in the range [1, 2147483647(0x7FFFFFFF)]");
        } else {
            this.f1470b.f1014b.a1Ins(this.f1469a);
        }
    }
}

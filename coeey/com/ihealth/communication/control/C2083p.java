package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class C2083p implements C2026d {
    final /* synthetic */ int f1471a;
    final /* synthetic */ C2067n f1472b;

    C2083p(C2067n c2067n, int i) {
        this.f1472b = c2067n;
        this.f1471a = i;
    }

    public void mo5513a() {
        if (this.f1471a < 1 || this.f1471a > Integer.MAX_VALUE) {
            this.f1472b.m480a("setUserId() parameter id should be in the range [1, 2147483647(0x7FFFFFFF)]");
        } else {
            this.f1472b.f1014b.a3Ins(this.f1471a);
        }
    }
}

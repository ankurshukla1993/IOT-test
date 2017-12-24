package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class ah implements C2026d {
    final /* synthetic */ int f1250a;
    final /* synthetic */ C2067n f1251b;

    ah(C2067n c2067n, int i) {
        this.f1251b = c2067n;
        this.f1250a = i;
    }

    public void mo5513a() {
        if (this.f1250a < 0 || this.f1250a > 1) {
            this.f1251b.m480a("setPicture() parameter index should be 0 or 1.");
        } else {
            this.f1251b.f1014b.x11Ins(this.f1250a);
        }
    }
}

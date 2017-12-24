package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class at implements C2026d {
    final /* synthetic */ int f1274a;
    final /* synthetic */ BPControl f1275b;

    at(BPControl bPControl, int i) {
        this.f1275b = bPControl;
        this.f1274a = i;
    }

    public void mo5513a() {
        if (this.f1274a > 2 || this.f1274a < 1) {
            this.f1275b.m483a(400, "commandSetUser() parameter UserID should be 1 or 2 ");
        } else {
            this.f1275b.f1038f.commandSetUser(this.f1274a);
        }
    }
}

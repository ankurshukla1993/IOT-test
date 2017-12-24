package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class ci implements C2026d {
    final /* synthetic */ String f1345a;
    final /* synthetic */ Bg5lControl f1346b;

    ci(Bg5lControl bg5lControl, String str) {
        this.f1346b = bg5lControl;
        this.f1345a = str;
    }

    public void mo5513a() {
        this.f1346b.f1089a = this.f1346b.m559a(this.f1345a);
        if (this.f1346b.f1089a != 0) {
            this.f1346b.f1090b.setBottleId(this.f1346b.f1089a, this.f1345a, 0, "", true);
        }
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class bw implements C2026d {
    final /* synthetic */ String f1321a;
    final /* synthetic */ int f1322b;
    final /* synthetic */ String f1323c;
    final /* synthetic */ Bg5lControl f1324d;

    bw(Bg5lControl bg5lControl, String str, int i, String str2) {
        this.f1324d = bg5lControl;
        this.f1321a = str;
        this.f1322b = i;
        this.f1323c = str2;
    }

    public void mo5513a() {
        this.f1324d.f1089a = this.f1324d.m559a(this.f1321a);
        if (this.f1324d.f1089a != 0) {
            this.f1324d.f1090b.setBottleId(this.f1324d.f1089a, this.f1321a, this.f1322b, this.f1323c, true);
        }
    }
}

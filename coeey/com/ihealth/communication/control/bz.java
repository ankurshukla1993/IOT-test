package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class bz implements C2026d {
    final /* synthetic */ long f1332a;
    final /* synthetic */ Bg5lControl f1333b;

    bz(Bg5lControl bg5lControl, long j) {
        this.f1333b = bg5lControl;
        this.f1332a = j;
    }

    public void mo5513a() {
        this.f1333b.f1090b.setBottleId(this.f1332a, "", 0, "", false);
    }
}

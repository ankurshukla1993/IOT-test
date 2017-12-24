package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class bl implements C2026d {
    final /* synthetic */ long f1303a;
    final /* synthetic */ Bg5Control f1304b;

    bl(Bg5Control bg5Control, long j) {
        this.f1304b = bg5Control;
        this.f1303a = j;
    }

    public void mo5513a() {
        this.f1304b.f1081a.setBottleId(this.f1303a, "", 0, "", false);
    }
}

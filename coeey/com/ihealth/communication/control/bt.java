package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class bt implements C2026d {
    final /* synthetic */ String f1314a;
    final /* synthetic */ Bg5Control f1315b;

    bt(Bg5Control bg5Control, String str) {
        this.f1315b = bg5Control;
        this.f1314a = str;
    }

    public void mo5513a() {
        this.f1315b.f1083c = this.f1315b.m549a(this.f1314a);
        if (this.f1315b.f1083c != 0) {
            this.f1315b.f1081a.setBottleId(this.f1315b.f1083c, this.f1314a, 0, "", true);
        }
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class bu implements C2026d {
    final /* synthetic */ String f1316a;
    final /* synthetic */ int f1317b;
    final /* synthetic */ String f1318c;
    final /* synthetic */ Bg5Control f1319d;

    bu(Bg5Control bg5Control, String str, int i, String str2) {
        this.f1319d = bg5Control;
        this.f1316a = str;
        this.f1317b = i;
        this.f1318c = str2;
    }

    public void mo5513a() {
        this.f1319d.f1083c = this.f1319d.m549a(this.f1316a);
        if (this.f1319d.f1083c != 0) {
            this.f1319d.f1081a.setBottleId(this.f1319d.f1083c, this.f1316a, this.f1317b, this.f1318c, true);
        }
    }
}

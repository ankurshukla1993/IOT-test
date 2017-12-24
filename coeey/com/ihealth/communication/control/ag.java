package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class ag implements C2026d {
    final /* synthetic */ int f1244a;
    final /* synthetic */ int f1245b;
    final /* synthetic */ int f1246c;
    final /* synthetic */ int f1247d;
    final /* synthetic */ boolean f1248e;
    final /* synthetic */ C2067n f1249f;

    ag(C2067n c2067n, int i, int i2, int i3, int i4, boolean z) {
        this.f1249f = c2067n;
        this.f1244a = i;
        this.f1245b = i2;
        this.f1246c = i3;
        this.f1247d = i4;
        this.f1248e = z;
    }

    public void mo5513a() {
        if (this.f1244a < 0 || this.f1244a > 255) {
            this.f1249f.m480a("setSwimPara() parameter poolLength should be in range [0, 255].");
        } else if (this.f1245b < 0 || this.f1245b > 23) {
            this.f1249f.m480a("setSwimPara() parameter hours should be in range [0, 23].");
        } else if (this.f1246c < 0 || this.f1246c > 59) {
            this.f1249f.m480a("setSwimPara() parameter minutes should be in range [0, 59].");
        } else if (this.f1247d < 0 || this.f1247d > 1) {
            this.f1249f.m480a("setSwimPara() parameter unit should be 0 or 1.");
        } else {
            this.f1249f.f1014b.setSwimPara(this.f1248e, (byte) this.f1244a, (byte) this.f1245b, (byte) this.f1246c, (byte) this.f1247d);
        }
    }
}

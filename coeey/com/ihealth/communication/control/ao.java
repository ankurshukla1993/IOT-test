package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class ao implements C2026d {
    final /* synthetic */ int f1266a;
    final /* synthetic */ int f1267b;
    final /* synthetic */ boolean f1268c;
    final /* synthetic */ C2067n f1269d;

    ao(C2067n c2067n, int i, int i2, boolean z) {
        this.f1269d = c2067n;
        this.f1266a = i;
        this.f1267b = i2;
        this.f1268c = z;
    }

    public void mo5513a() {
        if (this.f1266a < 0 || this.f1266a > 23) {
            this.f1269d.m480a("setActivityRemind() parameter hour should be in the range [0, 23].");
        } else if (this.f1267b < 0 || this.f1267b > 59) {
            this.f1269d.m480a("setActivityRemind() parameter min should be in the range [0, 59].");
        } else if (this.f1266a == 0 && this.f1267b == 0) {
            this.f1269d.m480a("setActivityRemind() time(hour * 60 + min) should be larger than 0 min.");
        } else {
            this.f1269d.f1014b.aaIns((this.f1266a * 60) + this.f1267b, this.f1268c);
        }
    }
}

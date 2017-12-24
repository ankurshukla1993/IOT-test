package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class ek implements C2026d {
    final /* synthetic */ int f1441a;
    final /* synthetic */ int f1442b;
    final /* synthetic */ Hs5Control f1443c;

    ek(Hs5Control hs5Control, int i, int i2) {
        this.f1443c = hs5Control;
        this.f1441a = i;
        this.f1442b = i2;
    }

    public void mo5513a() {
        if (this.f1441a < 0 || this.f1441a > 19) {
            this.f1443c.m601a("getOfflineData() parameter userPstCode should be in the range [0, 19].", Integer.valueOf(this.f1441a));
        } else if (this.f1442b < 1) {
            this.f1443c.m601a("getOfflineData() parameter userId should be in the range [1, 2147483647(0x7FFFFFFF)].", Integer.valueOf(this.f1442b));
        } else {
            this.f1443c.f1185b.creatMemoryCnn(this.f1441a, this.f1442b);
        }
    }
}

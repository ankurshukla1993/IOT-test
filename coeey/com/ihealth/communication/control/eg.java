package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class eg implements C2026d {
    final /* synthetic */ int f1423a;
    final /* synthetic */ Hs5Control f1424b;

    eg(Hs5Control hs5Control, int i) {
        this.f1424b = hs5Control;
        this.f1423a = i;
    }

    public void mo5513a() {
        if (this.f1423a < 1) {
            this.f1424b.m601a("creatManagement() parameter userId should in the range [1, 2147483647(0x7FFFFFFF)].", Integer.valueOf(this.f1423a));
            return;
        }
        this.f1424b.f1185b.setUserId(this.f1423a);
        this.f1424b.f1185b.createManagementCnn();
    }
}

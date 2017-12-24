package com.ihealth.communication.control;

import com.ihealth.communication.ins.A9InsSet.Status;
import com.ihealth.communication.p001a.C2026d;

class ei implements C2026d {
    final /* synthetic */ int f1432a;
    final /* synthetic */ Hs5Control f1433b;

    ei(Hs5Control hs5Control, int i) {
        this.f1433b = hs5Control;
        this.f1432a = i;
    }

    public void mo5513a() {
        if (this.f1433b.f1185b.getStatus() != Status.Management) {
            this.f1433b.m600a("It is mandatory to call creatManagement() before call DeleteUserInScale().");
        } else if (this.f1432a < 0 || this.f1432a > 19) {
            this.f1433b.m601a("DeleteUserInScale() parameter userPstCode should be in the range [0, 19].", Integer.valueOf(this.f1432a));
        } else {
            this.f1433b.f1185b.DeleteUserInScale(this.f1432a, this.f1433b.getUserListInHs5().getUserIds()[this.f1432a]);
        }
    }
}

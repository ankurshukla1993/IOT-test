package com.ihealth.communication.control;

import com.ihealth.communication.ins.A9InsSet.Status;
import com.ihealth.communication.p001a.C2026d;

class ej implements C2026d {
    final /* synthetic */ int f1434a;
    final /* synthetic */ int f1435b;
    final /* synthetic */ int f1436c;
    final /* synthetic */ int f1437d;
    final /* synthetic */ int f1438e;
    final /* synthetic */ int f1439f;
    final /* synthetic */ Hs5Control f1440g;

    ej(Hs5Control hs5Control, int i, int i2, int i3, int i4, int i5, int i6) {
        this.f1440g = hs5Control;
        this.f1434a = i;
        this.f1435b = i2;
        this.f1436c = i3;
        this.f1437d = i4;
        this.f1438e = i5;
        this.f1439f = i6;
    }

    public void mo5513a() {
        if (this.f1440g.f1185b.getStatus() != Status.Management) {
            this.f1440g.m600a("It is mandatory to call creatManagement() before call updateUserInfo().");
        } else if (this.f1434a < 0 || this.f1434a > 19) {
            this.f1440g.m601a("updateUserInfo() parameter userPstCode should be in the range [0, 19].", Integer.valueOf(this.f1434a));
        } else if (this.f1435b < 1) {
            this.f1440g.m601a("updateUserInfo() parameter userId should be in the range [1, 2147483647(0x7FFFFFFF)].", Integer.valueOf(this.f1435b));
        } else if (this.f1436c < 7 || this.f1436c > 99) {
            this.f1440g.m601a("updateUserInfo() parameter age should be in the range [7, 99].", Integer.valueOf(this.f1436c));
        } else if (this.f1437d < 81 || this.f1437d > 219) {
            this.f1440g.m601a("updateUserInfo() parameter height should be in the range [81, 219].", Integer.valueOf(this.f1437d));
        } else if (this.f1438e < 0 || this.f1438e > 1) {
            this.f1440g.m601a("updateUserInfo() parameter isSporter should be 0 or 1.", Integer.valueOf(this.f1438e));
        } else if (this.f1439f < 0 || this.f1439f > 1) {
            this.f1440g.m601a("updateUserInfo() parameter gender should be 0 or 1.", Integer.valueOf(this.f1439f));
        } else {
            this.f1440g.f1185b.updateUserInfo(this.f1434a, this.f1435b, this.f1436c, this.f1437d, this.f1438e, this.f1439f);
        }
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.ins.A9InsSet.Status;
import com.ihealth.communication.p001a.C2026d;

class eh implements C2026d {
    final /* synthetic */ int f1425a;
    final /* synthetic */ int f1426b;
    final /* synthetic */ int f1427c;
    final /* synthetic */ int f1428d;
    final /* synthetic */ int f1429e;
    final /* synthetic */ int f1430f;
    final /* synthetic */ Hs5Control f1431g;

    eh(Hs5Control hs5Control, int i, int i2, int i3, int i4, int i5, int i6) {
        this.f1431g = hs5Control;
        this.f1425a = i;
        this.f1426b = i2;
        this.f1427c = i3;
        this.f1428d = i4;
        this.f1429e = i5;
        this.f1430f = i6;
    }

    public void mo5513a() {
        if (this.f1431g.f1185b.getStatus() != Status.Management) {
            this.f1431g.m600a("It is mandatory to call creatManagement() before call WriteUserToScale().");
        } else if (this.f1425a < 0 || this.f1425a > 19) {
            this.f1431g.m601a("WriteUserToScale() parameter userPstCode should be in the range [0, 19].", Integer.valueOf(this.f1425a));
        } else if (this.f1426b < 1) {
            this.f1431g.m601a("WriteUserToScale() parameter userId should be in the range [1, 2147483647(0x7FFFFFFF)].", Integer.valueOf(this.f1426b));
        } else if (this.f1427c < 7 || this.f1427c > 99) {
            this.f1431g.m601a("WriteUserToScale() parameter age should be in the range [7, 99].", Integer.valueOf(this.f1427c));
        } else if (this.f1428d < 81 || this.f1428d > 219) {
            this.f1431g.m601a("WriteUserToScale() parameter height should be in the range [81, 219].", Integer.valueOf(this.f1428d));
        } else if (this.f1429e < 0 || this.f1429e > 1) {
            this.f1431g.m601a("WriteUserToScale() parameter isSporter should be 0 or 1.", Integer.valueOf(this.f1429e));
        } else if (this.f1430f < 0 || this.f1430f > 1) {
            this.f1431g.m601a("WriteUserToScale() parameter gender should be 0 or 1.", Integer.valueOf(this.f1430f));
        } else {
            this.f1431g.f1185b.WriteUserToScale(this.f1425a, this.f1426b, this.f1427c, this.f1428d, this.f1429e, this.f1430f);
        }
    }
}

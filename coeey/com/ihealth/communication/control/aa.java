package com.ihealth.communication.control;

import com.google.android.flexbox.FlexItem;
import com.ihealth.communication.p001a.C2026d;

class aa implements C2026d {
    final /* synthetic */ int f1222a;
    final /* synthetic */ int f1223b;
    final /* synthetic */ float f1224c;
    final /* synthetic */ int f1225d;
    final /* synthetic */ int f1226e;
    final /* synthetic */ int f1227f;
    final /* synthetic */ int f1228g;
    final /* synthetic */ C2067n f1229h;

    aa(C2067n c2067n, int i, int i2, float f, int i3, int i4, int i5, int i6) {
        this.f1229h = c2067n;
        this.f1222a = i;
        this.f1223b = i2;
        this.f1224c = f;
        this.f1225d = i3;
        this.f1226e = i4;
        this.f1227f = i5;
        this.f1228g = i6;
    }

    public void mo5513a() {
        if (this.f1222a < 1 || this.f1222a > 255) {
            this.f1229h.m480a("setUserInfo() parameter age should be in range [1, 255].");
        } else if (this.f1223b < 1 || this.f1223b > 255) {
            this.f1229h.m480a("setUserInfo() parameter height should be in range [1, 255].");
        } else if (this.f1224c < FlexItem.FLEX_SHRINK_DEFAULT || this.f1224c > 255.0f) {
            this.f1229h.m480a("setUserInfo() parameter weight should be in range [1.0, 255.0].");
        } else if (this.f1225d < 0 || this.f1225d > 1) {
            this.f1229h.m480a("setUserInfo() parameter gender should be 0 or 1.");
        } else if (this.f1226e < 0 || this.f1226e > 1) {
            this.f1229h.m480a("setUserInfo() parameter unit should be 0 or 1.");
        } else if (this.f1227f < 4 || this.f1227f > 65535) {
            this.f1229h.m480a("setUserInfo() parameter target should be in range [4, 65535(0xFFFF)].");
        } else if (this.f1228g < 1 || this.f1228g > 3) {
            this.f1229h.m480a("setUserInfo() parameter activityLevel should be 1, 2 or 3.");
        } else {
            this.f1229h.f1024l = this.f1224c;
            this.f1229h.f1026n = (float) this.f1223b;
            this.f1229h.f1027o = this.f1222a;
            this.f1229h.f1028p = this.f1225d;
            this.f1229h.f1029q = this.f1228g;
            this.f1229h.f1030r = this.f1227f;
            int i = (int) ((((double) this.f1223b) - ((0.6666666666666666d * ((double) this.f1223b)) / 7.0d)) / 2.0d);
            this.f1229h.f1031s = this.f1229h.m464a();
            this.f1229h.f1014b.setUserInfo(this.f1222a, i, (float) this.f1223b, this.f1225d, this.f1224c, this.f1226e, this.f1229h.f1030r, this.f1229h.f1030r / 2, this.f1229h.f1030r / 4, this.f1229h.f1031s);
        }
    }
}

package com.ihealth.communication.control;

import com.google.android.flexbox.FlexItem;
import com.ihealth.communication.p001a.C2026d;

class ab implements C2026d {
    final /* synthetic */ int f1230a;
    final /* synthetic */ int f1231b;
    final /* synthetic */ float f1232c;
    final /* synthetic */ int f1233d;
    final /* synthetic */ int f1234e;
    final /* synthetic */ int f1235f;
    final /* synthetic */ int f1236g;
    final /* synthetic */ int f1237h;
    final /* synthetic */ C2067n f1238i;

    ab(C2067n c2067n, int i, int i2, float f, int i3, int i4, int i5, int i6, int i7) {
        this.f1238i = c2067n;
        this.f1230a = i;
        this.f1231b = i2;
        this.f1232c = f;
        this.f1233d = i3;
        this.f1234e = i4;
        this.f1235f = i5;
        this.f1236g = i6;
        this.f1237h = i7;
    }

    public void mo5513a() {
        if (this.f1230a < 1 || this.f1230a > 255) {
            this.f1238i.m480a("setUserInfo() parameter age should be in range [1, 255].");
        } else if (this.f1231b < 1 || this.f1231b > 255) {
            this.f1238i.m480a("setUserInfo() parameter height should be in range [1, 255].");
        } else if (this.f1232c < FlexItem.FLEX_SHRINK_DEFAULT || this.f1232c > 255.0f) {
            this.f1238i.m480a("setUserInfo() parameter weight should be in range [1.0, 255.0].");
        } else if (this.f1233d < 0 || this.f1233d > 1) {
            this.f1238i.m480a("setUserInfo() parameter gender should be 0 or 1.");
        } else if (this.f1234e < 0 || this.f1234e > 1) {
            this.f1238i.m480a("setUserInfo() parameter unit should be 0 or 1.");
        } else if (this.f1235f < 4 || this.f1235f > Integer.MAX_VALUE) {
            this.f1238i.m480a("setUserInfo() parameter target should be in range [4, 2147483647(0x7FFFFFFF)].");
        } else if (this.f1236g < 1 || this.f1236g > 3) {
            this.f1238i.m480a("setUserInfo() parameter activityLevel should be 1, 2 or 3.");
        } else if (this.f1237h < 1 || this.f1237h > 1439) {
            this.f1238i.m480a("setUserInfo() parameter min should be in range [1, 1439(23 * 60 + 59)].");
        } else {
            this.f1238i.f1024l = this.f1232c;
            this.f1238i.f1026n = (float) this.f1231b;
            this.f1238i.f1027o = this.f1230a;
            this.f1238i.f1028p = this.f1233d;
            this.f1238i.f1029q = this.f1236g;
            this.f1238i.f1030r = this.f1235f;
            int i = (int) ((((double) this.f1231b) - ((0.6666666666666666d * ((double) this.f1231b)) / 7.0d)) / 2.0d);
            this.f1238i.f1031s = this.f1238i.m464a();
            this.f1238i.f1014b.setUserInfoForAM4Plus(this.f1230a, i, (float) this.f1231b, this.f1233d, this.f1232c, this.f1234e, this.f1238i.f1030r, this.f1238i.f1030r / 2, this.f1238i.f1030r / 4, this.f1238i.f1031s, this.f1237h);
        }
    }
}

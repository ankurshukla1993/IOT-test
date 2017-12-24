package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class al implements C2026d {
    final /* synthetic */ int f1256a;
    final /* synthetic */ int f1257b;
    final /* synthetic */ int f1258c;
    final /* synthetic */ int[] f1259d;
    final /* synthetic */ boolean f1260e;
    final /* synthetic */ boolean f1261f;
    final /* synthetic */ C2067n f1262g;

    al(C2067n c2067n, int i, int i2, int i3, int[] iArr, boolean z, boolean z2) {
        this.f1262g = c2067n;
        this.f1256a = i;
        this.f1257b = i2;
        this.f1258c = i3;
        this.f1259d = iArr;
        this.f1260e = z;
        this.f1261f = z2;
    }

    public void mo5513a() {
        if (this.f1256a < 1 || this.f1256a > 3) {
            this.f1262g.m480a("setAlarmClock() parameter id should be 1, 2 or 3.");
        } else if (this.f1257b < 0 || this.f1257b > 23) {
            this.f1262g.m480a("setAlarmClock() parameter hour should be in the range [0, 23].");
        } else if (this.f1258c < 0 || this.f1258c > 59) {
            this.f1262g.m480a("setAlarmClock() parameter min should be in the range [0, 59].");
        } else if (this.f1259d == null) {
            this.f1262g.m480a("setAlarmClock() parameter weeks is null.");
        } else if (this.f1259d.length != 7) {
            this.f1262g.m480a("setAlarmClock() parameter weeks length should be 7.");
        } else {
            for (int i : this.f1259d) {
                if (i < 0 || i > 1) {
                    this.f1262g.m480a("setAlarmClock() parameter weeks item should be 0 or 1.");
                    return;
                }
            }
            this.f1262g.f1014b.a8Ins(this.f1256a, this.f1257b, this.f1258c, this.f1260e, (byte) (((((((this.f1259d[6] << 6) | (this.f1259d[5] << 5)) | (this.f1259d[4] << 4)) | (this.f1259d[3] << 3)) | (this.f1259d[2] << 2)) | (this.f1259d[1] << 1)) | (this.f1259d[0] << 0)), this.f1261f);
        }
    }
}

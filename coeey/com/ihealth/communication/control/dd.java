package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class dd implements C2026d {
    final /* synthetic */ int f1368a;
    final /* synthetic */ int f1369b;
    final /* synthetic */ int f1370c;
    final /* synthetic */ int f1371d;
    final /* synthetic */ int f1372e;
    final /* synthetic */ int f1373f;
    final /* synthetic */ int f1374g;
    final /* synthetic */ int f1375h;
    final /* synthetic */ Bp5sControl f1376i;

    dd(Bp5sControl bp5sControl, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.f1376i = bp5sControl;
        this.f1368a = i;
        this.f1369b = i2;
        this.f1370c = i3;
        this.f1371d = i4;
        this.f1372e = i5;
        this.f1373f = i6;
        this.f1374g = i7;
        this.f1375h = i8;
    }

    public void mo5513a() {
        this.f1376i.f1124b.setRepeatedlyMeasureParameter(this.f1368a, this.f1369b, this.f1370c, this.f1371d, this.f1372e, this.f1373f, this.f1374g, this.f1375h);
    }
}

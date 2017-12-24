package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class dr implements C2026d {
    final /* synthetic */ byte f1392a;
    final /* synthetic */ byte f1393b;
    final /* synthetic */ byte f1394c;
    final /* synthetic */ byte f1395d;
    final /* synthetic */ Bp7sControl f1396e;

    dr(Bp7sControl bp7sControl, byte b, byte b2, byte b3, byte b4) {
        this.f1396e = bp7sControl;
        this.f1392a = b;
        this.f1393b = b2;
        this.f1394c = b3;
        this.f1395d = b4;
    }

    public void mo5513a() {
        if (this.f1392a >= this.f1393b || this.f1394c >= this.f1395d || this.f1392a < (byte) 0 || this.f1393b > (byte) 90 || this.f1394c < (byte) 0 || this.f1395d > (byte) 90) {
            this.f1396e.m578a(400, "each parameter of angleSet() should be in range from 0 to 90, and {xxxLow} should be less than {xxxUpper}.");
        } else {
            this.f1396e.f1141b.angleSet(this.f1393b, this.f1392a, this.f1395d, this.f1394c);
        }
    }
}

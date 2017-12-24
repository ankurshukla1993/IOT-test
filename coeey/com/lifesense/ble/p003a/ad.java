package com.lifesense.ble.p003a;

import java.util.Arrays;

public class ad {
    private ag f2273a;
    private byte[] f2274b;

    public ad(ag agVar, byte[] bArr) {
        m1552a(agVar);
        m1553a(bArr);
    }

    public void m1552a(ag agVar) {
        this.f2273a = agVar;
    }

    public void m1553a(byte[] bArr) {
        this.f2274b = bArr;
    }

    public byte[] m1554a() {
        return this.f2274b;
    }

    public ag m1555b() {
        return this.f2273a;
    }

    public String toString() {
        return "ProtocolMessage [operatingDirective=" + this.f2273a + ", commandData=" + Arrays.toString(this.f2274b) + "]";
    }
}

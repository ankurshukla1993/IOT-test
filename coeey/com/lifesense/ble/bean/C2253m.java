package com.lifesense.ble.bean;

import com.lifesense.ble.p003a.C2226n;

public class C2253m {
    private String f2438a;
    private String f2439b;
    private C2226n f2440c;

    public String m1857a() {
        return this.f2438a;
    }

    public void m1858a(C2226n c2226n) {
        this.f2440c = c2226n;
    }

    public void m1859a(String str) {
        this.f2438a = str;
    }

    public String m1860b() {
        return this.f2439b;
    }

    public void m1861b(String str) {
        this.f2439b = str;
    }

    public String toString() {
        return "WriteSuccessListener [deviceId=" + this.f2438a + ", memberId=" + this.f2439b + ", userInfoType=" + this.f2440c + "]";
    }
}

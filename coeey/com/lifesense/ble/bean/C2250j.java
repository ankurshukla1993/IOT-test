package com.lifesense.ble.bean;

import java.util.HashMap;

public class C2250j {
    private static C2250j f2436a;
    private HashMap f2437b = new HashMap();

    public static C2250j m1851a() {
        if (f2436a != null) {
            return f2436a;
        }
        C2250j c2250j = new C2250j();
        f2436a = c2250j;
        return c2250j;
    }

    public HashMap m1852b() {
        return this.f2437b;
    }
}

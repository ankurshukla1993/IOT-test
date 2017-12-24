package com.lifesense.ble.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class C2248h {
    public String f2429a = "GattServices";
    private UUID f2430b;
    private C2249i f2431c;
    private int f2432d;
    private ArrayList f2433e;
    private C2250j f2434f = C2250j.m1851a();

    public List m1843a(String str) {
        return (this.f2434f == null || !this.f2434f.m1852b().containsKey(str)) ? null : (List) this.f2434f.m1852b().get(str);
    }

    public UUID m1844a() {
        return this.f2430b;
    }

    public void m1845a(int i) {
        this.f2432d = i;
    }

    public void m1846a(C2249i c2249i) {
        this.f2431c = c2249i;
    }

    public void m1847a(ArrayList arrayList, String str) {
        this.f2433e = arrayList;
        this.f2434f.m1852b().put(str, arrayList);
    }

    public void m1848a(UUID uuid) {
        this.f2430b = uuid;
    }

    public List m1849b() {
        return this.f2433e;
    }

    public int m1850c() {
        return this.f2432d;
    }
}

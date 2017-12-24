package com.lifesense.ble.bean;

import java.util.Calendar;

public class C2247g {
    private int f2417a;
    private long f2418b;
    private long f2419c;
    private int f2420d;
    private int f2421e;
    private int f2422f;
    private double f2423g;
    private double f2424h;
    private int f2425i;
    private String f2426j;
    private int f2427k;
    private int f2428l;

    public void m1832a(double d) {
        this.f2423g = d;
    }

    public void m1833a(int i) {
        this.f2427k = i;
    }

    public void m1834a(long j) {
        this.f2418b = j;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        this.f2417a = instance.get(11);
    }

    public void m1835a(String str) {
        this.f2426j = str;
    }

    public void m1836b(double d) {
        this.f2424h = d;
    }

    public void m1837b(int i) {
        this.f2428l = i;
    }

    public void m1838b(long j) {
        this.f2419c = j;
    }

    public void m1839c(int i) {
        this.f2420d = i;
    }

    public void m1840d(int i) {
        this.f2421e = i;
    }

    public void m1841e(int i) {
        this.f2422f = i;
    }

    public void m1842f(int i) {
        this.f2425i = i;
    }
}

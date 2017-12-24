package com.ihealth.communication.utils;

final class C2176e implements Runnable {
    final /* synthetic */ String f2124a;
    final /* synthetic */ String f2125b;
    final /* synthetic */ String f2126c;
    final /* synthetic */ int f2127d;
    final /* synthetic */ int f2128e;
    final /* synthetic */ String f2129f;
    final /* synthetic */ String f2130g;
    final /* synthetic */ String f2131h;

    C2176e(String str, String str2, String str3, int i, int i2, String str4, String str5, String str6) {
        this.f2124a = str;
        this.f2125b = str2;
        this.f2126c = str3;
        this.f2127d = i;
        this.f2128e = i2;
        this.f2129f = str4;
        this.f2130g = str5;
        this.f2131h = str6;
    }

    public void run() {
        try {
            if (!Logger.f2109b) {
                Logger.f2109b = true;
                Logger.m1222b(this.f2124a);
            }
            Logger.m1223b(Logger.f2111d + this.f2125b + Logger.f2110c, this.f2126c, this.f2127d, this.f2128e, this.f2129f, this.f2130g, this.f2131h);
        } catch (Exception e) {
        }
    }
}

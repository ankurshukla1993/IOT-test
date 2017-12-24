package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;
import java.util.Calendar;
import java.util.TimeZone;

class C2089v implements C2026d {
    final /* synthetic */ C2067n f1479a;

    C2089v(C2067n c2067n) {
        this.f1479a = c2067n;
    }

    public void mo5513a() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        int i = instance.get(1) - 2000;
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(7);
        this.f1479a.f1014b.a4Ins(i, i2, i3, instance.get(11), instance.get(12), instance.get(13), i4);
    }
}

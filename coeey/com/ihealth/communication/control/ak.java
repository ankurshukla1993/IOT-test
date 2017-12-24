package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

class ak implements C2026d {
    final /* synthetic */ int[] f1254a;
    final /* synthetic */ C2067n f1255b;

    ak(C2067n c2067n, int[] iArr) {
        this.f1255b = c2067n;
        this.f1254a = iArr;
    }

    public void mo5513a() {
        if (this.f1254a.length == 0) {
            this.f1255b.m480a("getAlarmClockDetail() parameter can not be empty.");
            return;
        }
        HashSet hashSet = new HashSet();
        int[] iArr = this.f1254a;
        int length = iArr.length;
        int i = 0;
        while (i < length) {
            int i2 = iArr[i];
            if (i2 < 1 || i2 > 3) {
                this.f1255b.m480a("getAlarmClockDetail() parameter should be 1, 2 or 3.");
                return;
            } else {
                hashSet.add(Integer.valueOf(i2));
                i++;
            }
        }
        iArr = new int[hashSet.size()];
        Iterator it = hashSet.iterator();
        i = 0;
        while (it.hasNext()) {
            int i3 = i + 1;
            iArr[i] = ((Integer) it.next()).intValue();
            i = i3;
        }
        Arrays.sort(iArr);
        this.f1255b.f1014b.a7Ins(iArr);
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;

class ae implements C2026d {
    final /* synthetic */ C2067n f1242a;

    ae(C2067n c2067n) {
        this.f1242a = c2067n;
    }

    public void mo5513a() {
        Integer[] numArr = new Integer[6];
        for (int i = 0; i < 6; i++) {
            numArr[i] = Integer.valueOf((int) (Math.random() * 10.0d));
        }
        this.f1242a.f1014b.x09Ins(numArr);
    }
}

package com.ihealth.communication.manager;

import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.utils.Log;

class C2134a {
    private C2135b[] f1928a;
    private int f1929b = 0;
    private BleComm f1930c;
    private boolean f1931d = false;
    private C2142i f1932e = null;

    C2134a(C2142i c2142i) {
        this.f1932e = c2142i;
    }

    private void m1056c() {
        if (this.f1931d) {
            Log.m1210d("DiscoveryStateMachine", "Current state machine already exited, so not move to next any more.");
        } else if (this.f1929b < this.f1928a.length) {
            this.f1929b++;
            C2135b c2135b = this.f1928a[this.f1929b];
            Log.m1210d("DiscoveryStateMachine", "Move to next state : " + c2135b);
            c2135b.m1061a(this.f1930c, this);
        } else {
            Log.m1211e("DiscoveryStateMachine", "DiscoveryStateMachine already stop.");
        }
    }

    private void m1057d() {
        Log.m1210d("DiscoveryStateMachine", "Sate machine stop internally.");
        this.f1931d = true;
        if (this.f1932e != null) {
            this.f1932e.mo5531a();
        }
    }

    void m1058a() {
        Log.m1210d("DiscoveryStateMachine", "State machine stop.");
        this.f1931d = true;
        if (this.f1929b < this.f1928a.length) {
            this.f1928a[this.f1929b].m1062b(this.f1930c, this);
        }
    }

    void m1059a(BleComm bleComm, C2135b[] c2135bArr) {
        this.f1930c = bleComm;
        this.f1928a = c2135bArr;
        this.f1929b = 0;
        Log.m1210d("DiscoveryStateMachine", "Move to first state : " + this.f1928a[this.f1929b]);
        this.f1928a[this.f1929b].m1061a(this.f1930c, this);
    }

    boolean m1060b() {
        return !this.f1931d;
    }
}

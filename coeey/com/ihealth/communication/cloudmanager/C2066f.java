package com.ihealth.communication.cloudmanager;

class C2066f extends Thread {
    final /* synthetic */ int f1001a;
    final /* synthetic */ C2065e f1002b;

    C2066f(C2065e c2065e, int i) {
        this.f1002b = c2065e;
        this.f1001a = i;
    }

    public void run() {
        this.f1002b.f1000a.f999b.f996e.f1004b.onUserStatus(this.f1002b.f1000a.f999b.f992a, this.f1001a);
    }
}

package com.lifesense.ble.p003a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class C2219g extends Handler {
    final /* synthetic */ C2214b f2301a;

    public C2219g(C2214b c2214b, Looper looper) {
        this.f2301a = c2214b;
        super(looper);
    }

    public void handleMessage(Message message) {
        if (message.arg1 == 1) {
            if (message.obj instanceof String) {
                this.f2301a.f2281d = this.f2301a.f2279b.getRemoteDevice((String) message.obj);
                this.f2301a.f2280c = this.f2301a.f2281d.connectGatt(this.f2301a.f2282e, false, this.f2301a.f2296s);
            }
        } else if (message.arg1 == 2) {
            this.f2301a.f2280c.discoverServices();
        } else if (message.arg1 == 3) {
            this.f2301a.f2280c.disconnect();
            this.f2301a.f2280c.close();
        }
    }
}

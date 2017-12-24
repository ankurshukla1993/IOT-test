package com.ihealth.communication.base.ble;

import android.os.SystemClock;
import com.ihealth.communication.utils.Log;

class C2030d implements Runnable {
    final /* synthetic */ C2029c f320a;

    C2030d(C2029c c2029c) {
        this.f320a = c2029c;
    }

    public void run() {
        Log.w(Ble.f279a, "commandAddTimeoutForDevice() time out");
        this.f320a.f319c.disconnect(this.f320a.f317a);
        SystemClock.sleep(300);
        this.f320a.f319c.m242a(this.f320a.f318b, 0, 0);
    }
}

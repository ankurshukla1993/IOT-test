package com.ihealth.communication.control;

import android.os.SystemClock;
import com.ihealth.androidbg.audio.AudioTrackManager;

class bc extends Thread {
    final /* synthetic */ String f1287a;
    final /* synthetic */ Bg1Control f1288b;

    bc(Bg1Control bg1Control, String str) {
        this.f1288b = bg1Control;
        this.f1287a = str;
    }

    public void run() {
        this.f1288b.m542r();
        if (this.f1288b.f1077w == 16716549) {
            this.f1288b.m503b(this.f1287a);
        } else if (this.f1288b.f1077w == 16716548) {
            if (this.f1288b.f1080z) {
                SystemClock.sleep(1500);
                AudioTrackManager.inCommunication = true;
                this.f1288b.m498a(this.f1287a, false);
            } else {
                this.f1288b.m498a(this.f1287a, false);
            }
        }
        this.f1288b.m543s();
    }
}

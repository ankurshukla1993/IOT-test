package com.ihealth.communication.control;

import android.os.SystemClock;
import com.ihealth.androidbg.audio.AudioTrackManager;

class bd extends Thread {
    final /* synthetic */ String f1289a;
    final /* synthetic */ Bg1Control f1290b;

    bd(Bg1Control bg1Control, String str) {
        this.f1290b = bg1Control;
        this.f1289a = str;
    }

    public void run() {
        this.f1290b.m542r();
        if (this.f1290b.f1077w == 16716549) {
            this.f1290b.m503b(this.f1289a);
        } else if (this.f1290b.f1077w == 16716548) {
            if (this.f1290b.f1080z) {
                SystemClock.sleep(1500);
                AudioTrackManager.inCommunication = true;
                this.f1290b.m498a(this.f1289a, false);
            } else {
                this.f1290b.m498a(this.f1289a, false);
            }
        }
        this.f1290b.m543s();
    }
}

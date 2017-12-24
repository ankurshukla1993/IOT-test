package com.ihealth.communication.control;

import com.ihealth.androidbg.audio.AudioTrackManager;
import java.util.TimerTask;

class ay extends TimerTask {
    final /* synthetic */ Bg1Control f1282a;

    ay(Bg1Control bg1Control) {
        this.f1282a = bg1Control;
    }

    public void run() {
        AudioTrackManager.getInstance().playMI2S();
    }
}

package com.ihealth.communication.control;

import android.content.Intent;
import com.ihealth.communication.utils.Log;
import java.util.TimerTask;

class ba extends TimerTask {
    final /* synthetic */ Bg1Control f1285a;

    ba(Bg1Control bg1Control) {
        this.f1285a = bg1Control;
    }

    public void run() {
        Log.e(Bg1Control.f1042a, "connection failed :: flag 32");
        Intent intent = new Intent(Bg1Profile.ACTION_BG1_CONNECT_RESULT);
        intent.putExtra(Bg1Profile.BG1_CONNECT_RESULT, 32);
        intent.setPackage(this.f1285a.f1061g.getPackageName());
        if (this.f1285a.m544t()) {
            this.f1285a.f1061g.sendBroadcast(intent);
        }
        this.f1285a.disconnect();
    }
}

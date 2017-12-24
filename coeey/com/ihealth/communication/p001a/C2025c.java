package com.ihealth.communication.p001a;

import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.util.TimerTask;

class C2025c extends TimerTask {
    final /* synthetic */ C2023a f273a;

    C2025c(C2023a c2023a) {
        this.f273a = c2023a;
    }

    public void run() {
        Log.p("CommandCacheControl", Level.INFO, "Cache TimeOut", new Object[]{this.f273a.f264b});
        if (this.f273a.f267e.size() > 1) {
            Log.p("CommandCacheControl", Level.INFO, "__Cache TimeOut", new Object[]{"* time out *  [" + this.f273a.f267e.size() + "] command left in queue"});
            this.f273a.f267e.poll();
            this.f273a.m227c();
        } else if (this.f273a.f267e.size() == 1) {
            this.f273a.m230a();
        } else {
            this.f273a.m230a();
            Log.p("CommandCacheControl", Level.INFO, "__Cache TimeOut", new Object[]{"* time out * command finish in queue"});
        }
    }
}

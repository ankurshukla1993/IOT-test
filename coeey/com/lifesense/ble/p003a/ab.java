package com.lifesense.ble.p003a;

import android.util.Log;
import java.util.TimerTask;

class ab extends TimerTask {
    final /* synthetic */ C2235w f2270a;
    private final /* synthetic */ ag f2271b;

    ab(C2235w c2235w, ag agVar) {
        this.f2270a = c2235w;
        this.f2271b = agVar;
    }

    public void run() {
        this.f2270a.f2372M = false;
        if (this.f2271b == ag.OPERATING_WRITE_C9_COMMAND_TO_DEVICE) {
            Log.e("写完C9回复命令", "收到上层允许断开连接的指令");
            this.f2270a.f2401y = ag.OPERATING_UPLOADED_RESULTS_PROCESS;
            this.f2270a.m1723a(this.f2270a.f2401y);
            if (this.f2270a.f2379c != null) {
                this.f2270a.f2379c.mo5592b(this.f2270a.f2390n);
            }
        } else if (this.f2270a.f2371L) {
            Log.e("Auto disconnect", "failed to init auto disconnect timer,has measure data........");
        } else {
            C2220h.m1596a((Object) this, "no measured data upload from device a4,auto disconnect...", 1);
            this.f2270a.m1780l();
            if (this.f2270a.f2379c != null) {
                this.f2270a.f2379c.mo5592b(this.f2270a.f2390n);
            }
        }
    }
}

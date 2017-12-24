package com.lifesense.ble.p003a;

import com.lifesense.ble.p003a.p004a.ac;
import java.util.TimerTask;

class aa extends TimerTask {
    final /* synthetic */ C2235w f2269a;

    aa(C2235w c2235w) {
        this.f2269a = c2235w;
    }

    public void run() {
        if (this.f2269a.f2367H < this.f2269a.f2369J.size()) {
            this.f2269a.m1739a(ac.m1298a(((String) this.f2269a.f2369J.get(this.f2269a.f2367H)).toCharArray()));
        } else {
            C2220h.m1596a((Object) this, "Done of write command response...", 3);
            this.f2269a.f2364E = true;
            if (this.f2269a.f2401y == ag.OPERATING_WRITE_C7_COMMAND_TO_DEVICE || this.f2269a.f2401y == ag.OPERATING_WRITE_CA_COMMAND_TO_DEVICE || this.f2269a.f2401y == ag.OPERATING_WRITE_CE_COMMAND_TO_DEVICE) {
                this.f2269a.f2371L = false;
                this.f2269a.m1742b(this.f2269a.f2401y);
            } else if (this.f2269a.f2401y == ag.OPERATING_WRITE_C9_COMMAND_TO_DEVICE && this.f2269a.f2373N && !this.f2269a.f2374O) {
                this.f2269a.m1742b(this.f2269a.f2401y);
            }
            cancel();
            this.f2269a.f2365F.cancel();
        }
        C2235w c2235w = this.f2269a;
        c2235w.f2367H = c2235w.f2367H + 1;
    }
}

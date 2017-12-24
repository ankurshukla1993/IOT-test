package com.lifesense.ble.p003a;

import com.lifesense.ble.p003a.p004a.ac;
import java.util.TimerTask;

class C2232t extends TimerTask {
    final /* synthetic */ C2229q f2352a;

    C2232t(C2229q c2229q) {
        this.f2352a = c2229q;
    }

    public void run() {
        if (this.f2352a.f2346w < this.f2352a.f2348y.size()) {
            this.f2352a.m1655a(ac.m1298a(((String) this.f2352a.f2348y.get(this.f2352a.f2346w)).toCharArray()));
        } else {
            C2220h.m1596a((Object) this, "Done of write command response...", 3);
            this.f2352a.f2343t = true;
            if ((this.f2352a.f2337n == ag.OPERATING_WRITE_C7_COMMAND_TO_DEVICE || this.f2352a.f2337n == ag.OPERATING_WRITE_CA_COMMAND_TO_DEVICE || this.f2352a.f2337n == ag.OPERATING_WRITE_CE_COMMAND_TO_DEVICE || this.f2352a.f2337n == ag.OPERATING_WRITE_C9_COMMAND_TO_DEVICE) && this.f2352a.f2322H && !this.f2352a.f2316B && this.f2352a.f2321G != null && this.f2352a.f2321G.size() > 0) {
                for (String a : this.f2352a.f2321G) {
                    this.f2352a.m1651a(a);
                }
                this.f2352a.f2322H = false;
                this.f2352a.f2321G.clear();
            }
            cancel();
            this.f2352a.f2344u.cancel();
        }
        C2229q c2229q = this.f2352a;
        c2229q.f2346w = c2229q.f2346w + 1;
    }
}

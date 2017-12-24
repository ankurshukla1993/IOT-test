package com.ihealth.communication.base.protocol;

import com.ihealth.communication.control.HsProfile;
import com.ihealth.communication.utils.Log;
import java.util.TimerTask;
import org.json.JSONObject;

class C2037d extends TimerTask {
    final /* synthetic */ WifiCommProtocol f420a;

    C2037d(WifiCommProtocol wifiCommProtocol) {
        this.f420a = wifiCommProtocol;
    }

    public void run() {
        if (this.f420a.f395a >= 6) {
            Log.w("HS5Wifi", "repeatSendTimer() -- failed");
            this.f420a.f405k.onConnectionStateChange(this.f420a.f400f, this.f420a.f406l, 2, 0, null);
            this.f420a.m320c();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("error", 600);
                this.f420a.f410p.onNotify(this.f420a.f400f, this.f420a.f406l, HsProfile.ACTION_ERROR_HS, jSONObject.toString());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.f420a.m321d();
        WifiCommProtocol wifiCommProtocol = this.f420a;
        wifiCommProtocol.f395a++;
    }
}

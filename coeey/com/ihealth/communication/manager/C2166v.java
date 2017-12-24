package com.ihealth.communication.manager;

import com.ihealth.communication.cloud.a.i;
import com.ihealth.communication.control.UpgradeProfile;
import org.json.JSONException;
import org.json.JSONObject;

class C2166v implements i {
    final /* synthetic */ C2165u f2034a;

    C2166v(C2165u c2165u) {
        this.f2034a = c2165u;
    }

    public void m1199a(int i) {
        if (i > 50) {
            i = 50;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("progress", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.f2034a.f2033c.f2004k.onNotify(this.f2034a.f2031a, this.f2034a.f2032b, UpgradeProfile.ACTION_DEVICE_UP_DOWNLOADING, jSONObject.toString());
    }
}

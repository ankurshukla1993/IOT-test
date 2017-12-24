package com.ihealth.communication.manager;

import com.ihealth.communication.cloud.a.i;
import com.ihealth.communication.control.UpgradeProfile;
import org.json.JSONException;
import org.json.JSONObject;

class C2167w implements i {
    final /* synthetic */ C2165u f2035a;

    C2167w(C2165u c2165u) {
        this.f2035a = c2165u;
    }

    public void m1200a(int i) {
        if (i < 50 || i > 100) {
            i = 100;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("progress", i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.f2035a.f2033c.f2004k.onNotify(this.f2035a.f2031a, this.f2035a.f2032b, UpgradeProfile.ACTION_DEVICE_UP_DOWNLOADING, jSONObject.toString());
    }
}

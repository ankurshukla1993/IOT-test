package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;
import org.json.JSONException;
import org.json.JSONObject;

class cd implements C2026d {
    final /* synthetic */ int f1338a;
    final /* synthetic */ Bg5lControl f1339b;

    cd(Bg5lControl bg5lControl, int i) {
        this.f1339b = bg5lControl;
        this.f1338a = i;
    }

    public void mo5513a() {
        if (this.f1338a <= 0 || this.f1338a >= 3) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                jSONObject.put("description", "setUnit(int type) parameter type should be in the range [1, 2].");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f1339b.f1094f.onNotify(this.f1339b.f1092d, this.f1339b.f1093e, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
            return;
        }
        this.f1339b.f1090b.setUnit(this.f1338a);
    }
}

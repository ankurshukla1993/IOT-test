package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;
import org.json.JSONException;
import org.json.JSONObject;

class bq implements C2026d {
    final /* synthetic */ int f1310a;
    final /* synthetic */ Bg5Control f1311b;

    bq(Bg5Control bg5Control, int i) {
        this.f1311b = bg5Control;
        this.f1310a = i;
    }

    public void mo5513a() {
        if (this.f1310a <= 0 || this.f1310a >= 3) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                jSONObject.put("description", "startMeasure(int type) parameter type should be in the range [1, 2].");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f1311b.f1086f.onNotify(this.f1311b.f1084d, this.f1311b.f1085e, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
            return;
        }
        this.f1311b.f1081a.startMeasure(1);
    }
}

package com.ihealth.communication.control;

import com.ihealth.communication.p001a.C2026d;
import org.json.JSONException;
import org.json.JSONObject;

class cf implements C2026d {
    final /* synthetic */ int f1341a;
    final /* synthetic */ Bg5lControl f1342b;

    cf(Bg5lControl bg5lControl, int i) {
        this.f1342b = bg5lControl;
        this.f1341a = i;
    }

    public void mo5513a() {
        if (this.f1341a <= 0 || this.f1341a >= 3) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                jSONObject.put("description", "startMeasure(int type) parameter type should be in the range [1, 2].");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f1342b.f1094f.onNotify(this.f1342b.f1092d, this.f1342b.f1093e, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
            return;
        }
        this.f1342b.f1090b.startMeasure(this.f1341a);
    }
}

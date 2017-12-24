package com.ihealth.communication.control;

import android.text.TextUtils;
import com.ihealth.communication.p001a.C2026d;
import org.json.JSONException;
import org.json.JSONObject;

class bx implements C2026d {
    final /* synthetic */ int f1325a;
    final /* synthetic */ int f1326b;
    final /* synthetic */ String f1327c;
    final /* synthetic */ int f1328d;
    final /* synthetic */ String f1329e;
    final /* synthetic */ Bg5lControl f1330f;

    bx(Bg5lControl bg5lControl, int i, int i2, String str, int i3, String str2) {
        this.f1330f = bg5lControl;
        this.f1325a = i;
        this.f1326b = i2;
        this.f1327c = str;
        this.f1328d = i3;
        this.f1329e = str2;
    }

    public void mo5513a() {
        if (this.f1325a < 1 || this.f1325a > 2 || this.f1326b < 1 || this.f1326b > 2 || ((this.f1325a == 1 && TextUtils.isEmpty(this.f1327c)) || (this.f1325a == 1 && !TextUtils.isEmpty(this.f1327c) && this.f1330f.m559a(this.f1327c) == 0))) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                jSONObject.put("description", "setBottleMessageWithInfo(int stripType, int measureType, String QRCode, int stripNum, String overDate) parameter type should be valid");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f1330f.f1094f.onNotify(this.f1330f.f1092d, this.f1330f.f1093e, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
            return;
        }
        if (this.f1325a == 1) {
            this.f1330f.f1089a = this.f1330f.m559a(this.f1327c);
        } else if (this.f1325a == 2) {
            this.f1330f.f1089a = 4294967295L;
        }
        if (this.f1330f.f1089a > 0) {
            this.f1330f.f1090b.setBottleId(3, this.f1325a, this.f1326b, this.f1330f.f1089a, this.f1327c, this.f1328d, this.f1329e, true);
        }
    }
}

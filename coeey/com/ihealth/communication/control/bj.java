package com.ihealth.communication.control;

import android.text.TextUtils;
import com.ihealth.communication.p001a.C2026d;
import org.json.JSONException;
import org.json.JSONObject;

class bj implements C2026d {
    final /* synthetic */ int f1296a;
    final /* synthetic */ int f1297b;
    final /* synthetic */ String f1298c;
    final /* synthetic */ int f1299d;
    final /* synthetic */ String f1300e;
    final /* synthetic */ Bg5Control f1301f;

    bj(Bg5Control bg5Control, int i, int i2, String str, int i3, String str2) {
        this.f1301f = bg5Control;
        this.f1296a = i;
        this.f1297b = i2;
        this.f1298c = str;
        this.f1299d = i3;
        this.f1300e = str2;
    }

    public void mo5513a() {
        if (this.f1296a < 1 || this.f1296a > 2 || this.f1297b < 1 || this.f1297b > 2 || ((this.f1296a == 1 && TextUtils.isEmpty(this.f1298c)) || (this.f1296a == 1 && !TextUtils.isEmpty(this.f1298c) && this.f1301f.m549a(this.f1298c) == 0))) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                jSONObject.put("description", "setBottleMessageWithInfo(int stripType, int measureType, String QRCode, int stripNum, String overDate) parameter type should be valid");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f1301f.f1086f.onNotify(this.f1301f.f1084d, this.f1301f.f1085e, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
            return;
        }
        if (this.f1296a == 1) {
            this.f1301f.f1083c = this.f1301f.m549a(this.f1298c);
        } else if (this.f1296a == 2) {
            this.f1301f.f1083c = 4294967295L;
        }
        if (this.f1301f.f1083c > 0) {
            this.f1301f.f1081a.setBottleId(2, this.f1296a, this.f1297b, this.f1301f.f1083c, this.f1298c, this.f1299d, this.f1300e, true);
        }
    }
}

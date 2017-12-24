package com.ihealth.communication.cloud.p002a;

import android.content.SharedPreferences.Editor;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.CommCloudSDK;
import com.ihealth.communication.cloud.ReturnDataUser;
import com.ihealth.communication.cloud.UserCheckSDK;

class C2045f extends Thread {
    final /* synthetic */ String f525a;
    final /* synthetic */ C2044e f526b;

    C2045f(C2044e c2044e, String str) {
        this.f526b = c2044e;
        this.f525a = str;
    }

    public void run() {
        ReturnDataUser UserSign;
        String string = this.f526b.f523a.getSharedPreferences(this.f525a + "userinfo", 0).getString("email", "");
        String string2 = this.f526b.f523a.getSharedPreferences(this.f525a + "userinfo", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(string2)) {
            string2 = "https://api.ihealthlabs.com:443";
        }
        String string3 = this.f526b.f523a.getSharedPreferences(this.f525a + "userinfo", 0).getString("client_id", "");
        String string4 = this.f526b.f523a.getSharedPreferences(this.f525a + "userinfo", 0).getString("client_secret", "");
        CommCloudSDK commCloudSDK = new CommCloudSDK(this.f526b.f523a);
        ReturnDataUser returnDataUser = new ReturnDataUser();
        try {
            UserSign = commCloudSDK.UserSign(string3, string4, string, string2);
        } catch (Exception e) {
            e.printStackTrace();
            UserSign = returnDataUser;
        }
        if ("100".equals(UserSign.getResultCode()) && UserSign.getApiName() != null) {
            UserCheckSDK.saveUserInfo(this.f526b.f523a, null, UserSign.getApiName(), null, UserSign.getAccessToken(), UserSign.getRefreshToken(), null, null, UserSign.getId());
            Editor edit = this.f526b.f523a.getSharedPreferences("noNetWorkTime", 0).edit();
            edit.putBoolean("IsIdentifed", true);
            edit.apply();
        }
    }
}

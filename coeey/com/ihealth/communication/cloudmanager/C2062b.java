package com.ihealth.communication.cloudmanager;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Looper;
import com.ihealth.communication.cloud.ApiData;
import com.ihealth.communication.cloud.CommCloudCenter;
import com.ihealth.communication.cloud.ReturnDataCenter;
import java.util.ArrayList;

class C2062b extends Thread {
    final /* synthetic */ String f992a;
    final /* synthetic */ Context f993b;
    final /* synthetic */ String f994c;
    final /* synthetic */ String f995d;
    final /* synthetic */ iHealthDeviceCloudManager f996e;

    C2062b(iHealthDeviceCloudManager com_ihealth_communication_cloudmanager_iHealthDeviceCloudManager, String str, Context context, String str2, String str3) {
        this.f996e = com_ihealth_communication_cloudmanager_iHealthDeviceCloudManager;
        this.f992a = str;
        this.f993b = context;
        this.f994c = str2;
        this.f995d = str3;
    }

    public void run() {
        String str = "OpenApiWeight";
        String str2 = this.f992a;
        ReturnDataCenter returnDataCenter = null;
        CharSequence charSequence = "";
        try {
            returnDataCenter = new CommCloudCenter(this.f993b).PrivacyandAuthorizationDownload(this.f994c, this.f995d);
            ArrayList apiInfo = returnDataCenter.getApiInfo();
            if (apiInfo.size() > 0 && apiInfo != null) {
                int i = 0;
                String str3 = charSequence;
                while (i < apiInfo.size()) {
                    try {
                        ApiData apiData = (ApiData) apiInfo.get(i);
                        str3 = str3 + apiData.getAPIShowName() + ":\n" + apiData.getAPIDescription() + "\n";
                        i++;
                    } catch (Exception e) {
                        Object obj = str3;
                    }
                }
                charSequence = str3;
            }
            charSequence = returnDataCenter.getContent() + "Authorization : " + "\n" + charSequence;
        } catch (Exception e2) {
            this.f996e.f1004b.onUserStatus(this.f992a, 8);
            Looper.prepare();
            new Builder(this.f993b).setTitle(returnDataCenter.getAuthorizationTitle()).setMessage(charSequence).setPositiveButton("OK", new C2064d(this, str2)).setOnCancelListener(new C2063c(this)).show();
            Looper.loop();
        }
        Looper.prepare();
        new Builder(this.f993b).setTitle(returnDataCenter.getAuthorizationTitle()).setMessage(charSequence).setPositiveButton("OK", new C2064d(this, str2)).setOnCancelListener(new C2063c(this)).show();
        Looper.loop();
    }
}

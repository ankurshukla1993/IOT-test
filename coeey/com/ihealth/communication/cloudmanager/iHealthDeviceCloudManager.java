package com.ihealth.communication.cloudmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.ihealth.communication.cloud.UserCheckSDK;
import com.ihealth.communication.cloud.p002a.C2044e;
import com.ihealth.communication.manager.iHealthDevicesCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;

public class iHealthDeviceCloudManager {
    private Context f1003a;
    private iHealthDevicesCallback f1004b;
    private boolean f1005c = false;

    public iHealthDeviceCloudManager(Context context, iHealthDevicesCallback ihealthDevicesCallback) {
        this.f1003a = context;
        this.f1004b = ihealthDevicesCallback;
    }

    private void m458a(Context context, String str, String str2, String str3) {
        new C2062b(this, str, context, str2, str3).start();
    }

    private void m460a(String str, String str2, String str3) {
        String[] strArr = new String[]{"OpenApiActivity", "OpenApiSleep", "OpenApiSpO2", "OpenApiWeight", "OpenApiBP", "OpenApiBG"};
        SharedPreferences sharedPreferences = this.f1003a.getSharedPreferences(str + "userinfo", 0);
        long j = sharedPreferences.getLong("installTS", 0);
        if (j == 0) {
            j = System.currentTimeMillis() / 1000;
            Editor edit = sharedPreferences.edit();
            edit.putLong("installTS", j);
            edit.apply();
        }
        long j2 = j;
        int CheckSDK = new UserCheckSDK().CheckSDK(this.f1003a, strArr, str2, str3, str);
        Editor edit2;
        switch (CheckSDK) {
            case 1:
                edit2 = this.f1003a.getSharedPreferences("noNetWorkTime", 0).edit();
                edit2.putBoolean("IsIdentifed", true);
                edit2.apply();
                this.f1004b.onUserStatus(str, 1);
                return;
            case 2:
                new C2044e(this.f1003a, str).m367a();
                this.f1004b.onUserStatus(str, 2);
                return;
            case 3:
                edit2 = this.f1003a.getSharedPreferences("noNetWorkTime", 0).edit();
                edit2.putBoolean("IsIdentifed", true);
                edit2.apply();
                this.f1004b.onUserStatus(str, 3);
                return;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                long currentTimeMillis = System.currentTimeMillis() / 1000;
                if (currentTimeMillis < j2 || currentTimeMillis - j2 >= 864000) {
                    this.f1004b.onUserStatus(str, CheckSDK);
                    return;
                }
                this.f1005c = true;
                this.f1004b.onUserStatus(str, 4);
                return;
            case 33:
                m458a(this.f1003a, str, str2, str3);
                return;
            default:
                return;
        }
    }

    public void SDKUserInAuthor(String userName, String clientID, String clientSecret) {
        new Thread(new C2061a(this, userName, clientID, clientSecret)).start();
    }

    public boolean getDevicePermisson(String userName, String type) {
        if (this.f1005c) {
            return true;
        }
        String string = this.f1003a.getSharedPreferences(userName + "userinfo", 0).getString("apiName", null);
        if (string == null) {
            return false;
        }
        String str = "OpenApiActivity";
        String str2 = "OpenApiSleep";
        String str3 = "OpenApiSpO2";
        String str4 = "OpenApiWeight";
        String str5 = "OpenApiBP";
        String str6 = "OpenApiBG";
        String[] strArr;
        if (iHealthDevicesManager.TYPE_AM3.equals(type) || iHealthDevicesManager.TYPE_AM3S.equals(type) || iHealthDevicesManager.TYPE_AM4.equals(type)) {
            strArr = new String[]{str, str2};
        } else if (iHealthDevicesManager.TYPE_BG1.equals(type) || iHealthDevicesManager.TYPE_BG5.equals(type) || iHealthDevicesManager.TYPE_BG5l.equals(type)) {
            strArr = new String[]{str6};
        } else if (iHealthDevicesManager.TYPE_BP3L.equals(type) || iHealthDevicesManager.TYPE_BP3M.equals(type) || iHealthDevicesManager.TYPE_BP5.equals(type) || iHealthDevicesManager.TYPE_BP7.equals(type) || iHealthDevicesManager.TYPE_BP7S.equals(type) || iHealthDevicesManager.TYPE_550BT.equals(type) || iHealthDevicesManager.TYPE_KD926.equals(type) || iHealthDevicesManager.TYPE_KD723.equals(type) || iHealthDevicesManager.TYPE_ABPM.equals(type) || iHealthDevicesManager.TYPE_BP5S.equals(type) || iHealthDevicesManager.TYPE_CBP.equals(type)) {
            strArr = new String[]{str5};
        } else if (iHealthDevicesManager.TYPE_HS3.equals(type) || iHealthDevicesManager.TYPE_HS4.equals(type) || iHealthDevicesManager.TYPE_HS4S.equals(type) || iHealthDevicesManager.TYPE_HS5.equals(type) || iHealthDevicesManager.TYPE_HS5S.equals(type) || iHealthDevicesManager.TYPE_HS6.equals(type) || iHealthDevicesManager.TYPE_HS5_BT.equals(type)) {
            strArr = new String[]{str4};
        } else if (!iHealthDevicesManager.TYPE_PO3.equals(type)) {
            return false;
        } else {
            strArr = new String[]{str3};
        }
        for (CharSequence contains : r0) {
            if (string.contains(contains)) {
                return true;
            }
        }
        return false;
    }
}

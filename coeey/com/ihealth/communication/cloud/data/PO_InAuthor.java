package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.utils.Log;

public class PO_InAuthor {
    private static final PO_InAuthor f954c = new PO_InAuthor();
    private boolean f955a = false;
    private boolean f956b = false;
    public Context context;

    public static PO_InAuthor getInstance() {
        return f954c;
    }

    public void initAuthor(Context context, String un) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(un + "userinfo", 0);
        String string = sharedPreferences.getString("email", "");
        String string2 = sharedPreferences.getString("apiName", "");
        String string3 = sharedPreferences.getString(HttpHeaders.HOST, "");
        String string4 = sharedPreferences.getString("accessToken", "");
        String string5 = sharedPreferences.getString("refreshToken", "");
        String string6 = sharedPreferences.getString("client_id", "");
        String string7 = sharedPreferences.getString("client_secret", "");
        if (this.f955a) {
            Log.i("AM_SDK", "进入SDK前打印用户配置!!!");
            Log.i("AM_SDK", "email = " + string);
            Log.i("AM_SDK", "apiName = " + string2);
            Log.i("AM_SDK", "Host = " + string3);
            Log.i("AM_SDK", "accessToken = " + string4);
            Log.i("AM_SDK", "refreshToken = " + string5);
            Log.i("AM_SDK", "client_id = " + string6);
            Log.i("AM_SDK", "client_secret = " + string7);
            Log.i("AM_SDK", "取到数据放入配置文件 : jiuan.sdk.author");
        }
        Editor edit = context.getSharedPreferences("jiuan.sdk.author", 0).edit();
        edit.putString("email", string);
        edit.putString("apiName", string2);
        edit.putString(HttpHeaders.HOST, string3);
        edit.putString("accessToken", string4);
        edit.putString("refreshToken", string5);
        edit.putString("client_id", string6);
        edit.putString("client_secret", string7);
        edit.commit();
        this.context = context;
    }

    public void run() {
        if (!this.f956b) {
            new PO_Up(this.context).Start_timer();
            this.f956b = true;
        }
    }
}

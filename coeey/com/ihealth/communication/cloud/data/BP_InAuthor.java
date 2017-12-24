package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.common.net.HttpHeaders;

public class BP_InAuthor {
    private static final BP_InAuthor f600b = new BP_InAuthor();
    private boolean f601a = false;
    public Context context;

    public static BP_InAuthor getInstance() {
        return f600b;
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
        if (!this.f601a) {
            new BP_Up(this.context).Start_timer();
            this.f601a = true;
        }
    }
}

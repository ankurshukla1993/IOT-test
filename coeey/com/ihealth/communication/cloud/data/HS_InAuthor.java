package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.utils.Log;

public class HS_InAuthor {
    private static final HS_InAuthor f931c = new HS_InAuthor();
    private boolean f932a = false;
    private boolean f933b = false;
    public Context context;

    public static HS_InAuthor getInstance() {
        return f931c;
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
        if (this.f932a) {
            Log.i(BG_CommCloud.TAG, "进入BPSDK前打印用户配置!!!");
            Log.i(BG_CommCloud.TAG, "email = " + string);
            Log.i(BG_CommCloud.TAG, "apiName = " + string2);
            Log.i(BG_CommCloud.TAG, "Host = " + string3);
            Log.i(BG_CommCloud.TAG, "accessToken = " + string4);
            Log.i(BG_CommCloud.TAG, "refreshToken = " + string5);
            Log.i(BG_CommCloud.TAG, "client_id = " + string6);
            Log.i(BG_CommCloud.TAG, "client_secret = " + string7);
            Log.i(BG_CommCloud.TAG, "取到数据放入配置文件 : jiuan.sdk.author");
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
        if (!this.f933b) {
            new HS_Up(this.context).Start_timer();
            this.f933b = true;
        }
    }
}

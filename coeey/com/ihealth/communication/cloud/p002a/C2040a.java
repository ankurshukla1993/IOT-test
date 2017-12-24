package com.ihealth.communication.cloud.p002a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.provider.Settings.Secure;
import android.text.TextUtils;
import com.ihealth.communication.utils.Log;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

public class C2040a {
    protected static final String f501a = C2040a.class.getSimpleName();
    String f502b;
    String f503c;
    Context f504d;

    public C2040a(Context context) {
        this.f504d = context;
    }

    private String m360c() {
        String str = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10);
        try {
            return new UUID((long) str.hashCode(), (long) Build.class.getField("SERIAL").get(null).toString().hashCode()).toString();
        } catch (Exception e) {
            return new UUID((long) str.hashCode(), (long) "serial".hashCode()).toString();
        }
    }

    public String m361a() {
        SharedPreferences sharedPreferences = this.f504d.getSharedPreferences("device_id.txt", 0);
        this.f502b = sharedPreferences.getString("app_guid", null);
        if (TextUtils.isEmpty(this.f502b)) {
            this.f502b = Secure.getString(this.f504d.getContentResolver(), "android_id");
            Log.v(f501a, "AppID SecureRandom before = " + this.f502b);
            this.f502b = new BigInteger(64, new SecureRandom()).toString(16);
            Log.v(f501a, "AppID SecureRandom after = " + this.f502b);
            Editor edit = sharedPreferences.edit();
            edit.putString("app_guid", this.f502b);
            edit.commit();
        } else {
            Log.v(f501a, "already exist AppID :: " + this.f502b);
        }
        return this.f502b;
    }

    public String m362b() {
        SharedPreferences sharedPreferences = this.f504d.getSharedPreferences("device_id.txt", 0);
        this.f503c = sharedPreferences.getString("device_uuid", null);
        if (TextUtils.isEmpty(this.f503c)) {
            this.f503c = m360c();
            Editor edit = sharedPreferences.edit();
            edit.putString("device_uuid", this.f503c);
            edit.commit();
        } else {
            Log.v(f501a, "already exist DeviceID :: " + this.f503c);
        }
        return this.f503c;
    }
}

package com.ihealth.communication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.ihealth.communication.cloud.CommCloudSyncTime;
import com.ihealth.communication.cloud.a.l;
import com.ihealth.communication.manager.iHealthDevicesManager;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BgSyncData {
    private static Context f2063a;
    private static SharedPreferences f2064b;
    private static Editor f2065c;

    private static long m1205a(Context context, String str) {
        f2063a = context;
        long downloadSyncTime = downloadSyncTime(context, str);
        long a = m1206a(str);
        Log.m1214v("BgSyncData", "templ1:" + l.a(Long.valueOf(downloadSyncTime)) + " templ2:" + l.a(Long.valueOf(a)));
        return (a <= downloadSyncTime && downloadSyncTime <= l.b()) ? downloadSyncTime : a;
    }

    private static long m1206a(String str) {
        f2064b = f2063a.getSharedPreferences("historyTimeBG", 0);
        return f2064b.getLong(str, 0);
    }

    public static long downloadSyncTime(Context con, String mac) {
        long j = 0;
        CommCloudSyncTime commCloudSyncTime = new CommCloudSyncTime(con);
        try {
            if (commCloudSyncTime.downloadSyncTime(mac, iHealthDevicesManager.TYPE_BG5) == 100) {
                j = commCloudSyncTime.getSyncTime();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return j;
    }

    public static JSONArray processData(Context con, String mac, JSONArray array) {
        long a = m1205a(con, mac);
        Log.m1214v("BgSyncData", "regTime:" + l.a(Long.valueOf(a)));
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (i < array.length()) {
            try {
                JSONObject optJSONObject = array.optJSONObject(i);
                JSONObject jSONObject = new JSONObject();
                Object string = optJSONObject.getString("date");
                String str = "";
                if (l.c(string) > a && a != 0) {
                    string = l.a(Long.valueOf(((l.c(string) - a) / 2) + a));
                }
                jSONObject.put("date", string);
                jSONObject.put("value", optJSONObject.getInt("value"));
                jSONObject.put("dataID", optJSONObject.getString("dataID"));
                jSONArray.put(jSONObject);
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONArray;
    }

    public static void setHistoryTime(Context context, String mac, long time) {
        f2064b = context.getSharedPreferences("historyTimeBG", 0);
        f2065c = f2064b.edit();
        f2065c.putLong(mac, time);
        f2065c.commit();
    }

    public static boolean uploadSyncTime(Context con, String mac, long TS) {
        CommCloudSyncTime commCloudSyncTime = new CommCloudSyncTime(con);
        try {
            setHistoryTime(con, mac, TS);
            if (commCloudSyncTime.uploadSyncTime(mac, iHealthDevicesManager.TYPE_BG5, TS) != 100) {
                return false;
            }
            Log.m1214v("BgSyncData", "uploadSyncTime:" + new Date(1000 * TS));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

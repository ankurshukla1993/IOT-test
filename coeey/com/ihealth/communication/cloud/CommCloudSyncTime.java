package com.ihealth.communication.cloud;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.facebook.appevents.AppEventsConstants;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.p002a.C2040a;
import com.ihealth.communication.cloud.p002a.C2050k;
import com.ihealth.communication.cloud.p002a.C2051l;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CommCloudSyncTime {
    public static final String TAG = "CommCloudSyncTime";
    public long TS = 0;
    Context f487a;
    private long f488b;
    public String messageReturn = "";
    public int queueNum = 0;
    public int result = 0;
    public int resultMessage = 0;

    public CommCloudSyncTime(Context context) {
        this.f487a = context;
    }

    private String m349a() {
        return new C2040a(this.f487a).m362b();
    }

    private String m350b() {
        return new C2040a(this.f487a).m361a();
    }

    public int downloadSyncTime(String mac, String deviceType) {
        this.f488b = 0;
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "08f8480f0d2f4bd4bb63a23ceebeb45a");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m350b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m349a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        hashMap.put("mDeviceId", mac);
        hashMap.put("DeviceType", deviceType);
        try {
            this.messageReturn = new C2050k(this.f487a).m382a("https://api.ihealthlabs.com:443/api5/lowmachine_download_time.htm", hashMap, "UTF-8");
            if (this.messageReturn.length() == 0) {
                return 999;
            }
            if (this.messageReturn.length() == 3) {
                return Integer.valueOf(this.messageReturn).intValue();
            }
            try {
                JSONObject jSONObject = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
                this.result = jSONObject.getInt(DataBaseConstants.PO_RESULT);
                this.TS = Long.parseLong(jSONObject.getString("TS"));
                this.resultMessage = Integer.parseInt(jSONObject.getString("ResultMessage"));
                this.queueNum = jSONObject.getInt("QueueNum");
                if (this.resultMessage != 100) {
                    return this.resultMessage;
                }
                this.f488b = ((JSONObject) new JSONTokener(jSONObject.getString("ReturnValue")).nextValue()).getLong("TimeOfAppSetLow");
                return 100;
            } catch (JSONException e) {
                return 999;
            }
        } catch (UnknownHostException e2) {
            return 101;
        } catch (SocketTimeoutException e3) {
            return 102;
        }
    }

    public long getSyncTime() {
        return this.f488b;
    }

    public int uploadSyncTime(String mac, String deviceType, long TS) {
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "8f75928e6bc9490bafea38be9d3e678c");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m350b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m349a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (i < 1) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("mDeviceId", mac);
                jSONObject.put("TimeOfAppSetLow", TS);
                jSONObject.put("DeviceType", deviceType);
                jSONObject.put("TimeZone", (double) C2051l.m383a());
                jSONArray.put(i, jSONObject);
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        hashMap.put("UploadData", jSONArray.toString());
        try {
            this.messageReturn = new C2050k(this.f487a).m382a("https://api.ihealthlabs.com:443/api5/lowmachine_upload_time.htm", hashMap, "UTF-8");
            if (this.messageReturn.length() == 0) {
                return 999;
            }
            if (this.messageReturn.length() == 3) {
                return Integer.valueOf(this.messageReturn).intValue();
            }
            try {
                JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
                this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
                this.TS = Long.parseLong(jSONObject2.getString("TS"));
                this.resultMessage = Integer.parseInt(jSONObject2.getString("ResultMessage"));
                this.queueNum = jSONObject2.getInt("QueueNum");
                return this.resultMessage == 100 ? 100 : this.resultMessage;
            } catch (JSONException e2) {
                return 999;
            }
        } catch (UnknownHostException e3) {
            return 101;
        } catch (SocketTimeoutException e4) {
            return 102;
        }
    }
}

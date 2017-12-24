package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import com.facebook.appevents.AppEventsConstants;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.CommCloudSDK;
import com.ihealth.communication.cloud.UserCheckSDK;
import com.ihealth.communication.cloud.p002a.C2040a;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.cloud.p002a.C2050k;
import com.ihealth.communication.cloud.p002a.C2051l;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class PO_CommCloud {
    public static final String TAG = "CommCloudPO";
    private static boolean f946b = false;
    public long TS = 0;
    Context f947a;
    private String f948c = "111111";
    private String f949d;
    private String f950e;
    private String f951f;
    private String f952g;
    private long f953h;
    public String messageReturn = "";
    public int queueNum = 0;
    public int result = 0;
    public float resultMessage = 0.0f;

    public PO_CommCloud(Context context) {
        if (f946b) {
            Log.i(TAG, "实例化sdk_AuthorTools,获取本地配置 un host");
        }
        this.f947a = context;
        this.f949d = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "jiuan");
        this.f950e = context.getSharedPreferences(this.f949d + "userinfo", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f950e)) {
            this.f950e = C2041b.f506b;
        }
        this.f951f = context.getSharedPreferences(this.f949d + "userinfo", 0).getString("client_id", "");
        this.f952g = context.getSharedPreferences(this.f949d + "userinfo", 0).getString("client_secret", "");
        if (f946b) {
            Log.i(TAG, "取得un = " + this.f949d);
            Log.i(TAG, "取得host = " + this.f950e);
        }
    }

    private String m448a() {
        return new C2040a(this.f947a).m362b();
    }

    private String m449b() {
        return new C2040a(this.f947a).m361a();
    }

    public PO_ReturnData am_po_upload(String userName, String VerifyToken, ArrayList hsArr, String inputHost) {
        PO_ReturnData pO_ReturnData = new PO_ReturnData();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "46d171ad45fa41d88ee4af3257d67066");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m449b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m448a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        try {
            if (f946b) {
                Log.i(TAG, hsArr.size() + "");
            }
            for (int i = 0; i < hsArr.size(); i++) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ChangeType", ((Data_PO_Result) hsArr.get(i)).getChangeType());
                jSONObject.put("LastChangeTime", ((Data_PO_Result) hsArr.get(i)).getLastChangeTime());
                jSONObject.put("PhoneDataID", ((Data_PO_Result) hsArr.get(i)).getPhoneDataID());
                jSONObject.put("PhoneCreateTime", ((Data_PO_Result) hsArr.get(i)).getPhoneCreateTime());
                jSONObject.put("Lat", ((Data_PO_Result) hsArr.get(i)).getLat());
                jSONObject.put("Lon", ((Data_PO_Result) hsArr.get(i)).getLon());
                jSONObject.put("TimeZone", (double) ((Data_PO_Result) hsArr.get(i)).getTimeZone());
                jSONObject.put("Activity", ((Data_PO_Result) hsArr.get(i)).getActivity());
                JSONArray jSONArray2 = new JSONArray();
                int length = ((Data_PO_Result) hsArr.get(i)).getWave().split("A").length;
                for (int i2 = 0; i2 < length; i2++) {
                    jSONArray2.put(i2, ((Data_PO_Result) hsArr.get(i)).getWave().split("A")[i2]);
                }
                jSONObject.put(DataBaseConstants.PO_WAVE, jSONArray2);
                jSONObject.put(DataBaseConstants.PO_PR, ((Data_PO_Result) hsArr.get(i)).getPR());
                jSONObject.put(DataBaseConstants.PO_RESULT, ((Data_PO_Result) hsArr.get(i)).getResult());
                jSONObject.put(DataBaseConstants.PO_FLOWRATE, ((Data_PO_Result) hsArr.get(i)).getFlowrate());
                jSONObject.put(DataBaseConstants.PO_RESULTSOURCE, ((Data_PO_Result) hsArr.get(i)).getResultSource());
                jSONObject.put("Mood", ((Data_PO_Result) hsArr.get(i)).getMood());
                jSONObject.put("Weather", ((Data_PO_Result) hsArr.get(i)).getWeather());
                jSONObject.put("Note", ((Data_PO_Result) hsArr.get(i)).getNote());
                jSONObject.put("NoteTS", ((Data_PO_Result) hsArr.get(i)).getNoteTS());
                jSONObject.put("MeasureTime", ((Data_PO_Result) hsArr.get(i)).getMeasureTime());
                jSONObject.put("MechineType", ((Data_PO_Result) hsArr.get(i)).getMechineType());
                jSONObject.put("MechineDeviceID", ((Data_PO_Result) hsArr.get(i)).getMechineDeviceID());
                jSONArray.put(i, jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", VerifyToken);
        hashMap.put("UploadData", jSONArray.toString());
        String str = inputHost + C2041b.f507c + "oxygen_upload.htm";
        if (f946b) {
            Log.i(TAG, "数据上传 = " + hashMap.toString());
        }
        try {
            this.messageReturn = new C2050k(this.f947a).m382a(str, hashMap, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return pO_ReturnData;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject2.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
            this.queueNum = jSONObject2.getInt("QueueNum");
            pO_ReturnData.setResultMessage(jSONObject2.getString("ResultMessage"));
            if (f946b) {
                Log.i(TAG, "上传返回 = " + this.resultMessage);
            }
            if (((double) this.resultMessage) == 100.0d) {
                return pO_ReturnData;
            }
            PO_ReturnData am_po_upload;
            if (((double) this.resultMessage) == 208.0d) {
                String string = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                if (f946b) {
                    Log.i(TAG, "hs_upload返回208,拿到regionHost = " + string);
                }
                am_po_upload = am_po_upload(this.f949d, VerifyToken, hsArr, string);
                if (!"100".equals(am_po_upload.getResultMessage())) {
                    return am_po_upload;
                }
                UserCheckSDK.saveUserInfo(this.f947a, null, null, string, null, null, null, null, -1);
                if (f946b) {
                    Log.i(TAG, "保存regionHost到本地");
                }
                this.f950e = string;
                return am_po_upload;
            } else if (((double) this.resultMessage) == 212.0d) {
                if (f946b) {
                    Log.i(TAG, "212->Token过期:刷新Token");
                }
                SharedPreferences sharedPreferences = this.f947a.getSharedPreferences("jiuan.sdk.author", 0);
                r1 = new CommCloudSDK(this.f947a).token_refresh(sharedPreferences.getString("refreshToken", ""), this.f949d, this.f950e);
                if (!"100".equals(r1.getResultCode())) {
                    return pO_ReturnData;
                }
                if (f946b) {
                    Log.i(TAG, "重新调用hs_upload");
                }
                r4 = r1.getAccessToken();
                am_po_upload = am_po_upload(this.f949d, r4, hsArr, this.f950e);
                if (!"100".equals(am_po_upload.getResultMessage())) {
                    return am_po_upload;
                }
                if (f946b) {
                    Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
                }
                r5 = r1.getRefreshToken();
                Editor edit = sharedPreferences.edit();
                edit.putString("accessToken", r4);
                edit.putString("refreshToken", r5);
                edit.commit();
                UserCheckSDK.saveUserInfo(this.f947a, null, null, null, r4, r5, null, null, -1);
                return am_po_upload;
            } else if (((double) this.resultMessage) != 221.0d) {
                return pO_ReturnData;
            } else {
                if (f946b) {
                    Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
                }
                PO_ReturnData pO_ReturnData2 = new PO_ReturnData();
                try {
                    r1 = new CommCloudSDK(this.f947a).UserSign(this.f951f, this.f952g, this.f949d, inputHost);
                    if (!"100".equals(r1.getResultCode())) {
                        return pO_ReturnData2;
                    }
                    pO_ReturnData2.setResultMessage(r1.getResultCode());
                    pO_ReturnData2.setRegionHost(r1.getRegionHost());
                    pO_ReturnData2.setAccessToken(r1.getAccessToken());
                    pO_ReturnData2.setRefreshToken(r1.getRefreshToken());
                    pO_ReturnData2.setExpires(r1.getExpires());
                    if (f946b) {
                        Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
                    }
                    r4 = pO_ReturnData2.getAccessToken();
                    am_po_upload = am_po_upload(this.f949d, r4, hsArr, inputHost);
                    if (!"100".equals(am_po_upload.getResultMessage())) {
                        return am_po_upload;
                    }
                    if (f946b) {
                        Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");
                    }
                    Editor edit2 = this.f947a.getSharedPreferences("jiuan.sdk.author", 0).edit();
                    r5 = pO_ReturnData2.getRefreshToken();
                    edit2.putString("accessToken", r4);
                    edit2.putString("refreshToken", r5);
                    edit2.commit();
                    UserCheckSDK.saveUserInfo(this.f947a, null, null, null, r4, r5, null, null, -1);
                    return am_po_upload;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return pO_ReturnData;
                }
            }
        } catch (JSONException e3) {
            return pO_ReturnData;
        }
    }

    public int downloadSyncTime(String mac, String inputHost) {
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "08f8480f0d2f4bd4bb63a23ceebeb45a");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m449b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m448a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        hashMap.put("mDeviceId", mac);
        hashMap.put("DeviceType", iHealthDevicesManager.TYPE_PO3);
        String str = inputHost + "/api5/lowmachine_download_time.htm";
        Log.i("", "数据上传 = " + hashMap.toString());
        try {
            this.messageReturn = new C2050k(this.f947a).m382a(str, hashMap, "UTF-8");
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
                this.resultMessage = (float) Integer.parseInt(jSONObject.getString("ResultMessage"));
                this.queueNum = jSONObject.getInt("QueueNum");
                Log.i("", "上传返回 = " + this.resultMessage);
                if (this.resultMessage == 100.0f) {
                    this.f953h = ((JSONObject) new JSONTokener(jSONObject.getString("ReturnValue")).nextValue()).getLong("TimeOfAppSetLow");
                    return 100;
                } else if (this.resultMessage != 208.0f) {
                    return (int) this.resultMessage;
                } else {
                    String string = ((JSONObject) new JSONTokener(jSONObject.getString("ReturnValue")).nextValue()).getString("RegionHost");
                    Log.i("", "hs_upload返回208,拿到regionHost = " + string);
                    return downloadSyncTime(mac, string);
                }
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
        return this.f953h;
    }

    public int uploadSyncTime(String mac, String inputHost) {
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "8f75928e6bc9490bafea38be9d3e678c");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m449b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m448a());
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
                jSONObject.put("TimeOfAppSetLow", System.currentTimeMillis() / 1000);
                Log.e(TAG, "上传同步时间" + ByteBufferUtil.TS2String(System.currentTimeMillis() / 1000));
                jSONObject.put("DeviceType", iHealthDevicesManager.TYPE_PO3);
                jSONObject.put("TimeZone", (double) C2051l.m383a());
                jSONArray.put(i, jSONObject);
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        hashMap.put("UploadData", jSONArray.toString());
        String str = inputHost + "/api5/lowmachine_upload_time.htm";
        Log.i("", "数据上传 = " + hashMap.toString());
        try {
            this.messageReturn = new C2050k(this.f947a).m382a(str, hashMap, "UTF-8");
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
                this.resultMessage = (float) Integer.parseInt(jSONObject2.getString("ResultMessage"));
                this.queueNum = jSONObject2.getInt("QueueNum");
                Log.i("", "上传返回 = " + this.resultMessage);
                if (this.resultMessage == 100.0f) {
                    return 100;
                }
                if (this.resultMessage != 208.0f) {
                    return (int) this.resultMessage;
                }
                str = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                Log.i("", "hs_upload返回208,拿到regionHost = " + str);
                return uploadSyncTime(mac, str);
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

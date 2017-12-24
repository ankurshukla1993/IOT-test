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
import com.ihealth.communication.utils.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class HS_CommCloud {
    public static final String SV_weight_upload = "6695adca89834f1794cc02ac1ff7c7fc";
    public static final String TAG = "CommCloudHS";
    private static boolean f924b = false;
    public long TS = 0;
    Context f925a;
    private String f926c = "111111";
    private String f927d;
    private String f928e;
    private String f929f;
    private String f930g;
    public String messageReturn = "";
    public int queueNum = 0;
    public int result = 0;
    public float resultMessage = 0.0f;

    public HS_CommCloud(Context context) {
        if (f924b) {
            Log.i(TAG, "实例化sdk_AuthorTools,获取本地配置 un host");
        }
        this.f925a = context;
        this.f927d = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "jiuan");
        this.f928e = context.getSharedPreferences(this.f927d + "userinfo", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f928e)) {
            this.f928e = C2041b.f506b;
        }
        this.f929f = context.getSharedPreferences(this.f927d + "userinfo", 0).getString("client_id", "");
        this.f930g = context.getSharedPreferences(this.f927d + "userinfo", 0).getString("client_secret", "");
        if (f924b) {
            Log.i(TAG, "取得un = " + this.f927d);
            Log.i(TAG, "取得host = " + this.f928e);
        }
    }

    private String m441a() {
        return new C2040a(this.f925a).m362b();
    }

    private String m442b() {
        return new C2040a(this.f925a).m361a();
    }

    public HS_ReturnData weight_upload(String userName, String VerifyToken, ArrayList hsArr, String inputHost) {
        HS_ReturnData hS_ReturnData = new HS_ReturnData();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", SV_weight_upload);
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m442b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m441a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        try {
            if (f924b) {
                Log.i(TAG, hsArr.size() + "");
            }
            for (int i = 0; i < hsArr.size(); i++) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ChangeType", ((Data_HS_Result) hsArr.get(i)).getChangeType());
                jSONObject.put("LastChangeTime", ((Data_HS_Result) hsArr.get(i)).getLastChangeTime());
                jSONObject.put("PhoneDataID", ((Data_HS_Result) hsArr.get(i)).getPhoneDataID());
                jSONObject.put("PhoneCreateTime", ((Data_HS_Result) hsArr.get(i)).getPhoneCreateTime());
                jSONObject.put("Lat", ((Data_HS_Result) hsArr.get(i)).getLat());
                jSONObject.put("Lon", ((Data_HS_Result) hsArr.get(i)).getLon());
                jSONObject.put("TimeZone", (double) ((Data_HS_Result) hsArr.get(i)).getTimeZone());
                jSONObject.put(DataBaseConstants.HSRESULT_BMI, (double) ((Data_HS_Result) hsArr.get(i)).getBMI());
                jSONObject.put(DataBaseConstants.HSRESULT_BONEVALUE, (double) ((Data_HS_Result) hsArr.get(i)).getBoneValue());
                jSONObject.put("DCI", (double) ((Data_HS_Result) hsArr.get(i)).getDCI());
                jSONObject.put(DataBaseConstants.HSRESULT_FATVALUE, (double) ((Data_HS_Result) hsArr.get(i)).getFatValue());
                jSONObject.put(DataBaseConstants.HSRESULT_MUSCALEVALUE, (double) ((Data_HS_Result) hsArr.get(i)).getMuscaleValue());
                jSONObject.put("MeasureType", ((Data_HS_Result) hsArr.get(i)).getMeasureType());
                jSONObject.put(DataBaseConstants.HSRESULT_WATERVALUE, (double) ((Data_HS_Result) hsArr.get(i)).getWaterValue());
                jSONObject.put(DataBaseConstants.HSRESULT_WEIGHTVALUE, (double) ((Data_HS_Result) hsArr.get(i)).getWeightValue());
                jSONObject.put("MeasureTime", ((Data_HS_Result) hsArr.get(i)).getMeasureTime());
                jSONObject.put("Note", ((Data_HS_Result) hsArr.get(i)).getNote());
                jSONObject.put(DataBaseConstants.HSRESULT_VISCERAFATLEVEL, ((Data_HS_Result) hsArr.get(i)).getVisceraFatLevel());
                jSONObject.put("MechineType", ((Data_HS_Result) hsArr.get(i)).getMechineType());
                jSONObject.put("MechineDeviceID", ((Data_HS_Result) hsArr.get(i)).getMechineDeviceID());
                jSONObject.put("NoteTS", ((Data_HS_Result) hsArr.get(i)).getNoteTS());
                jSONObject.put("Mood", ((Data_HS_Result) hsArr.get(i)).getMood());
                jSONObject.put("Activity", ((Data_HS_Result) hsArr.get(i)).getActivity());
                jSONObject.put("Weather", ((Data_HS_Result) hsArr.get(i)).getTemp() + "," + ((Data_HS_Result) hsArr.get(i)).getHumidity() + "," + ((Data_HS_Result) hsArr.get(i)).getVisibility() + "," + ((Data_HS_Result) hsArr.get(i)).getWeather());
                jSONArray.put(i, jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", VerifyToken);
        hashMap.put("UploadData", jSONArray.toString());
        String str = inputHost + C2041b.f507c + "weight_upload.htm";
        if (f924b) {
            Log.i(TAG, "体重数据上传 = " + hashMap.toString());
        }
        try {
            this.messageReturn = new C2050k(this.f925a).m382a(str, hashMap, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return hS_ReturnData;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject2.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
            this.queueNum = jSONObject2.getInt("QueueNum");
            hS_ReturnData.setResultMessage(jSONObject2.getString("ResultMessage"));
            if (f924b) {
                Log.i(TAG, "体重上传返回 = " + this.resultMessage);
            }
            if (((double) this.resultMessage) == 100.0d) {
                return hS_ReturnData;
            }
            HS_ReturnData weight_upload;
            if (((double) this.resultMessage) == 208.0d) {
                String string = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                if (f924b) {
                    Log.i(TAG, "hs_upload返回208,拿到regionHost = " + string);
                }
                weight_upload = weight_upload(this.f927d, VerifyToken, hsArr, string);
                if (!"100".equals(weight_upload.getResultMessage())) {
                    return weight_upload;
                }
                UserCheckSDK.saveUserInfo(this.f925a, null, null, string, null, null, null, null, -1);
                if (f924b) {
                    Log.i(TAG, "保存regionHost到本地");
                }
                this.f928e = string;
                return weight_upload;
            } else if (((double) this.resultMessage) == 212.0d) {
                if (f924b) {
                    Log.i(TAG, "212->Token过期:刷新Token");
                }
                SharedPreferences sharedPreferences = this.f925a.getSharedPreferences("jiuan.sdk.author", 0);
                r1 = new CommCloudSDK(this.f925a).token_refresh(sharedPreferences.getString("refreshToken", ""), this.f927d, this.f928e);
                if (!"100".equals(r1.getResultCode())) {
                    return hS_ReturnData;
                }
                if (f924b) {
                    Log.i(TAG, "重新调用hs_upload");
                }
                r4 = r1.getAccessToken();
                weight_upload = weight_upload(this.f927d, r4, hsArr, this.f928e);
                if (!"100".equals(weight_upload.getResultMessage())) {
                    return weight_upload;
                }
                if (f924b) {
                    Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
                }
                r5 = r1.getRefreshToken();
                Editor edit = sharedPreferences.edit();
                edit.putString("accessToken", r4);
                edit.putString("refreshToken", r5);
                edit.commit();
                UserCheckSDK.saveUserInfo(this.f925a, null, null, null, r4, r5, null, null, -1);
                return weight_upload;
            } else if (((double) this.resultMessage) != 221.0d) {
                return hS_ReturnData;
            } else {
                if (f924b) {
                    Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
                }
                HS_ReturnData hS_ReturnData2 = new HS_ReturnData();
                try {
                    r1 = new CommCloudSDK(this.f925a).UserSign(this.f929f, this.f930g, this.f927d, inputHost);
                    if (!"100".equals(r1.getResultCode())) {
                        return hS_ReturnData2;
                    }
                    hS_ReturnData2.setResultMessage(r1.getResultCode());
                    hS_ReturnData2.setRegionHost(r1.getRegionHost());
                    hS_ReturnData2.setAccessToken(r1.getAccessToken());
                    hS_ReturnData2.setRefreshToken(r1.getRefreshToken());
                    hS_ReturnData2.setExpires(r1.getExpires());
                    if (f924b) {
                        Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
                    }
                    r4 = hS_ReturnData2.getAccessToken();
                    weight_upload = weight_upload(this.f927d, r4, hsArr, inputHost);
                    if (!"100".equals(weight_upload.getResultMessage())) {
                        return weight_upload;
                    }
                    if (f924b) {
                        Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");
                    }
                    Editor edit2 = this.f925a.getSharedPreferences("jiuan.sdk.author", 0).edit();
                    r5 = hS_ReturnData2.getRefreshToken();
                    edit2.putString("accessToken", r4);
                    edit2.putString("refreshToken", r5);
                    edit2.commit();
                    UserCheckSDK.saveUserInfo(this.f925a, null, null, null, r4, r5, null, null, -1);
                    return weight_upload;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return hS_ReturnData;
                }
            }
        } catch (JSONException e3) {
            return hS_ReturnData;
        }
    }
}

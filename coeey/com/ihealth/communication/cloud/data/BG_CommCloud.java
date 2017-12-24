package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.CommCloudSDK;
import com.ihealth.communication.cloud.ReturnDataUser;
import com.ihealth.communication.cloud.UserCheckSDK;
import com.ihealth.communication.cloud.p002a.C2040a;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.cloud.p002a.C2050k;
import com.ihealth.communication.utils.Log;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class BG_CommCloud {
    public static final String SV_bg_upload = "163fc4265de64d518e287d7696d3b71f";
    public static final String TAG = "BG_SDK";
    private static boolean f575b = false;
    public long TS = 0;
    Context f576a;
    private String f577c = "111111";
    private String f578d;
    private String f579e;
    private String f580f;
    private String f581g;
    public String messageReturn = "";
    public int queueNum = 0;
    public int result = 0;
    public float resultMessage = 0.0f;

    public BG_CommCloud(Context context) {
        if (f575b) {
            Log.i(TAG, "实例化CommCloudBG,获取本地配置 un host");
        }
        this.f576a = context;
        this.f578d = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
        this.f579e = context.getSharedPreferences("jiuan.sdk.author", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f579e)) {
            this.f579e = C2041b.f506b;
        }
        this.f580f = context.getSharedPreferences("jiuan.sdk.author", 0).getString("client_id", "");
        this.f581g = context.getSharedPreferences("jiuan.sdk.author", 0).getString("client_secret", "");
        if (f575b) {
            Log.i(TAG, "取得un = " + this.f578d);
            Log.i(TAG, "取得host = " + this.f579e);
            Log.i(TAG, "取得client_id = " + this.f580f);
            Log.i(TAG, "取得client_secret = " + this.f581g);
        }
    }

    private String m416a() {
        return new C2040a(this.f576a).m362b();
    }

    private String m417b() {
        return new C2040a(this.f576a).m361a();
    }

    public BG_ReturnData bg_upload(String userName, String VerifyToken, ArrayList bgArr, String inputHost) {
        BG_ReturnData bG_ReturnData = new BG_ReturnData();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", SV_bg_upload);
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m417b());
        hashMap.put("PhoneOS", AbstractSpiCall.ANDROID_CLIENT_TYPE + VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m416a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", this.f577c);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        try {
            if (f575b) {
                Log.i(TAG, "血糖 上传 条目数 = " + bgArr.size());
            }
            for (int i = 0; i < bgArr.size(); i++) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ChangeType", ((Data_BG_Result) bgArr.get(i)).getChangeType());
                jSONObject.put("LastChangeTime", ((Data_BG_Result) bgArr.get(i)).getLastChangeTime());
                jSONObject.put("PhoneCreateTime", ((Data_BG_Result) bgArr.get(i)).getPhoneCreateTime());
                jSONObject.put("PhoneDataID", ((Data_BG_Result) bgArr.get(i)).getPhoneDataID());
                jSONObject.put("Lat", ((Data_BG_Result) bgArr.get(i)).getLat());
                jSONObject.put("Lon", ((Data_BG_Result) bgArr.get(i)).getLon());
                jSONObject.put("TimeZone", (double) ((Data_BG_Result) bgArr.get(i)).getTimeZone());
                jSONObject.put(DataBaseConstants.BGRESULT_BGVALUE, (double) ((Data_BG_Result) bgArr.get(i)).getBGValue());
                jSONObject.put(DataBaseConstants.BGRESULT_MEDICATION, ((Data_BG_Result) bgArr.get(i)).getMedication());
                jSONObject.put(DataBaseConstants.BGRESULT_MTIMETYPE, ((Data_BG_Result) bgArr.get(i)).getMTimeType());
                jSONObject.put("MeasureType", ((Data_BG_Result) bgArr.get(i)).getMeasureType());
                jSONObject.put(DataBaseConstants.BGRESULT_BOTTLEID, ((Data_BG_Result) bgArr.get(i)).getBottleId());
                jSONObject.put(DataBaseConstants.BGRESULT_SPORTS, ((Data_BG_Result) bgArr.get(i)).getSports());
                jSONObject.put(DataBaseConstants.BGRESULT_SNACKS, ((Data_BG_Result) bgArr.get(i)).getSnacks());
                jSONObject.put(DataBaseConstants.BGRESULT_EFFECTIVE, ((Data_BG_Result) bgArr.get(i)).getEffective());
                jSONObject.put("MeasureTime", ((Data_BG_Result) bgArr.get(i)).getMeasureTime());
                jSONObject.put("Note", ((Data_BG_Result) bgArr.get(i)).getNote());
                jSONObject.put("MechineType", ((Data_BG_Result) bgArr.get(i)).getMechineType());
                jSONObject.put("MechineDeviceID", ((Data_BG_Result) bgArr.get(i)).getMechineDeviceID());
                jSONArray.put(i, jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", VerifyToken);
        hashMap.put("UploadData", jSONArray.toString());
        String str = inputHost + C2041b.f507c + "bg_upload.htm";
        C2050k c2050k = new C2050k(this.f576a);
        if (f575b) {
            Log.i(TAG, "血糖数据上传 = " + hashMap.toString());
        }
        try {
            this.messageReturn = c2050k.m382a(str, hashMap, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return bG_ReturnData;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject2.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
            this.queueNum = jSONObject2.getInt("QueueNum");
            bG_ReturnData.setResultMessage(jSONObject2.getString("ResultMessage"));
            if (((double) this.resultMessage) == 100.0d) {
                return bG_ReturnData;
            }
            String string;
            if (((double) this.resultMessage) == 208.0d) {
                string = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                if (f575b) {
                    Log.i(TAG, "bp_upload返回208,拿到regionHost = " + string);
                }
                BG_ReturnData bg_upload = bg_upload(this.f578d, VerifyToken, bgArr, string);
                if (!"100".equals(bg_upload.getResultMessage())) {
                    return bg_upload;
                }
                UserCheckSDK.saveUserInfo(this.f576a, null, null, string, null, null, null, null, -1);
                if (f575b) {
                    Log.i(TAG, "保存regionHost到本地");
                }
                this.f579e = string;
                return bg_upload;
            } else if (((double) this.resultMessage) == 212.0d) {
                if (f575b) {
                    Log.i(TAG, "212->Token过期:刷新Token");
                }
                SharedPreferences sharedPreferences = this.f576a.getSharedPreferences("jiuan.sdk.author", 0);
                ReturnDataUser token_refresh = new CommCloudSDK(this.f576a).token_refresh(sharedPreferences.getString("refreshToken", ""), this.f578d, this.f579e);
                if (!"100".equals(token_refresh.getResultCode())) {
                    return bG_ReturnData;
                }
                string = token_refresh.getAccessToken();
                if (f575b) {
                    Log.i(TAG, "重新调用bg_upload");
                }
                r0 = bg_upload(this.f578d, string, bgArr, this.f579e);
                if (!"100".equals(r0.getResultMessage())) {
                    return r0;
                }
                if (f575b) {
                    Log.i(TAG, "刷新Token后上传BG数据成功!保存最新Token到本地");
                }
                r2 = token_refresh.getRefreshToken();
                Editor edit = sharedPreferences.edit();
                edit.putString("accessToken", string);
                edit.putString("refreshToken", r2);
                edit.commit();
                return r0;
            } else if (((double) this.resultMessage) != 221.0d) {
                return bG_ReturnData;
            } else {
                if (f575b) {
                    Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
                }
                BG_ReturnData bG_ReturnData2 = new BG_ReturnData();
                try {
                    ReturnDataUser UserSign = new CommCloudSDK(this.f576a).UserSign(this.f580f, this.f581g, this.f578d, inputHost);
                    if (!"100".equals(UserSign.getResultCode())) {
                        return bG_ReturnData2;
                    }
                    bG_ReturnData2.setResultMessage(UserSign.getResultCode());
                    bG_ReturnData2.setRegionHost(UserSign.getRegionHost());
                    bG_ReturnData2.setAccessToken(UserSign.getAccessToken());
                    bG_ReturnData2.setRefreshToken(UserSign.getRefreshToken());
                    bG_ReturnData2.setExpires(UserSign.getExpires());
                    if (f575b) {
                        Log.i(TAG, "重新登录成功,重新调用bg_upload上传数据");
                    }
                    r2 = bG_ReturnData2.getAccessToken();
                    r0 = bg_upload(this.f578d, r2, bgArr, inputHost);
                    if (!"100".equals(r0.getResultMessage())) {
                        return r0;
                    }
                    if (f575b) {
                        Log.i(TAG, "再次上传BG数据成功,保存最新Token信息到本地");
                    }
                    Editor edit2 = this.f576a.getSharedPreferences("jiuan.sdk.author", 0).edit();
                    String refreshToken = bG_ReturnData2.getRefreshToken();
                    edit2.putString("accessToken", r2);
                    edit2.putString("refreshToken", refreshToken);
                    edit2.commit();
                    return r0;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return bG_ReturnData;
                }
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
            return bG_ReturnData;
        }
    }
}

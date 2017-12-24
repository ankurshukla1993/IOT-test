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
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class BP_CommCloud {
    public static final String SV_bp_upload = "c629584d4e8141a6b18b2fab90d28b1e";
    public static final String TAG = "Net";
    public long TS = 0;
    Context f594a;
    private String f595b = "111111";
    private String f596c;
    private String f597d;
    private String f598e;
    private String f599f;
    public String messageReturn = "";
    public int queueNum;
    public int result = 0;
    public float resultMessage;

    public BP_CommCloud(Context context) {
        this.f594a = context;
        this.f597d = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
        this.f596c = context.getSharedPreferences(this.f597d + "userinfo", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f596c)) {
            this.f596c = C2041b.f506b;
        }
        this.f598e = context.getSharedPreferences(this.f597d + "userinfo", 0).getString("client_id", "");
        this.f599f = context.getSharedPreferences(this.f597d + "userinfo", 0).getString("client_secret", "");
    }

    private String m423a() {
        return new C2040a(this.f594a).m362b();
    }

    private String m424b() {
        return new C2040a(this.f594a).m361a();
    }

    public BP_ReturnData bp_upload(String un, String VerifyToken, ArrayList bpArr, String inputHost) {
        BP_ReturnData bP_ReturnData = new BP_ReturnData();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", SV_bp_upload);
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m424b());
        hashMap.put("PhoneOS", AbstractSpiCall.ANDROID_CLIENT_TYPE + VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m423a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", this.f595b);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (i < bpArr.size()) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ChangeType", ((Data_BP_Result) bpArr.get(i)).getChangeType());
                jSONObject.put("LastChangeTime", ((Data_BP_Result) bpArr.get(i)).getLastChangeTime());
                jSONObject.put("PhoneCreateTime", ((Data_BP_Result) bpArr.get(i)).getPhoneCreateTime());
                jSONObject.put("PhoneDataID", ((Data_BP_Result) bpArr.get(i)).getPhoneDataID());
                jSONObject.put("Lat", ((Data_BP_Result) bpArr.get(i)).getLat());
                jSONObject.put("Lon", ((Data_BP_Result) bpArr.get(i)).getLon());
                jSONObject.put("TimeZone", (double) ((Data_BP_Result) bpArr.get(i)).getTimeZone());
                jSONObject.put("BPL", ((Data_BP_Result) bpArr.get(i)).getBPL());
                jSONObject.put("HP", ((Data_BP_Result) bpArr.get(i)).getHP());
                jSONObject.put("HR", ((Data_BP_Result) bpArr.get(i)).getHR());
                jSONObject.put("IsArr", ((Data_BP_Result) bpArr.get(i)).getIsArr());
                jSONObject.put("LP", ((Data_BP_Result) bpArr.get(i)).getLP());
                jSONObject.put("MeasureType", ((Data_BP_Result) bpArr.get(i)).getMeasureType());
                jSONObject.put("MeasureTime", ((Data_BP_Result) bpArr.get(i)).getMeasureTime());
                jSONObject.put("Note", ((Data_BP_Result) bpArr.get(i)).getNote());
                jSONObject.put("MechineType", ((Data_BP_Result) bpArr.get(i)).getMechineType());
                jSONObject.put("MechineDeviceID", ((Data_BP_Result) bpArr.get(i)).getMechineDeviceID());
                JSONArray jSONArray2 = new JSONArray();
                int length = ((Data_BP_Result) bpArr.get(i)).getWL().split("A").length;
                for (int i2 = 0; i2 < length; i2++) {
                    jSONArray2.put(i2, ((Data_BP_Result) bpArr.get(i)).getWL().split("A")[i2]);
                }
                jSONObject.put("WL", jSONArray2);
                jSONObject.put("NoteTS", ((Data_BP_Result) bpArr.get(i)).getNoteTS());
                jSONObject.put("Mood", ((Data_BP_Result) bpArr.get(i)).getBpMood());
                jSONObject.put("Activity", ((Data_BP_Result) bpArr.get(i)).getBpActivity());
                jSONObject.put("Weather", ((Data_BP_Result) bpArr.get(i)).getTemp() + "," + ((Data_BP_Result) bpArr.get(i)).getHumidity() + "," + ((Data_BP_Result) bpArr.get(i)).getVisibility() + "," + ((Data_BP_Result) bpArr.get(i)).getWeather());
                jSONArray.put(i, jSONObject);
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        hashMap.put("Un", un);
        hashMap.put("VerifyToken", VerifyToken);
        hashMap.put("UploadData", jSONArray.toString());
        try {
            this.messageReturn = new C2050k(this.f594a).m382a(inputHost + C2041b.f507c + "bp_upload.htm", hashMap, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return bP_ReturnData;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject2.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
            this.queueNum = jSONObject2.getInt("QueueNum");
            bP_ReturnData.setResultMessage(jSONObject2.getString("ResultMessage"));
            if (((double) this.resultMessage) == 100.0d) {
                return bP_ReturnData;
            }
            String string;
            if (((double) this.resultMessage) == 208.0d) {
                string = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                BP_ReturnData bp_upload = bp_upload(un, VerifyToken, bpArr, string);
                if (!"100".equals(bp_upload.getResultMessage())) {
                    return bp_upload;
                }
                UserCheckSDK.saveUserInfo(this.f594a, null, null, string, null, null, null, null, -1);
                this.f596c = string;
                return bp_upload;
            } else if (((double) this.resultMessage) == 212.0d) {
                SharedPreferences sharedPreferences = this.f594a.getSharedPreferences("jiuan.sdk.author", 0);
                ReturnDataUser token_refresh = new CommCloudSDK(this.f594a).token_refresh(sharedPreferences.getString("refreshToken", ""), un, this.f596c);
                if (!"100".equals(token_refresh.getResultCode())) {
                    return bP_ReturnData;
                }
                string = token_refresh.getAccessToken();
                r0 = bp_upload(un, string, bpArr, this.f596c);
                if (!"100".equals(r0.getResultMessage())) {
                    return r0;
                }
                r2 = token_refresh.getRefreshToken();
                Editor edit = sharedPreferences.edit();
                edit.putString("accessToken", string);
                edit.putString("refreshToken", r2);
                edit.commit();
                return r0;
            } else if (((double) this.resultMessage) != 221.0d) {
                return bP_ReturnData;
            } else {
                BP_ReturnData bP_ReturnData2 = new BP_ReturnData();
                try {
                    ReturnDataUser UserSign = new CommCloudSDK(this.f594a).UserSign(this.f598e, this.f599f, un, inputHost);
                    if (!"100".equals(UserSign.getResultCode())) {
                        return bP_ReturnData2;
                    }
                    bP_ReturnData2.setResultMessage(UserSign.getResultCode());
                    bP_ReturnData2.setRegionHost(UserSign.getRegionHost());
                    bP_ReturnData2.setAccessToken(UserSign.getAccessToken());
                    bP_ReturnData2.setRefreshToken(UserSign.getRefreshToken());
                    bP_ReturnData2.setExpires(UserSign.getExpires());
                    r2 = bP_ReturnData2.getAccessToken();
                    r0 = bp_upload(un, r2, bpArr, inputHost);
                    if (!"100".equals(r0.getResultMessage())) {
                        return r0;
                    }
                    Editor edit2 = this.f594a.getSharedPreferences("jiuan.sdk.author", 0).edit();
                    String refreshToken = bP_ReturnData2.getRefreshToken();
                    edit2.putString("accessToken", r2);
                    edit2.putString("refreshToken", refreshToken);
                    edit2.commit();
                    return r0;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return bP_ReturnData;
                }
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
            return bP_ReturnData;
        }
    }
}

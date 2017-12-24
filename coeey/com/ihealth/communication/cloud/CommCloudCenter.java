package com.ihealth.communication.cloud;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.facebook.appevents.AppEventsConstants;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.p002a.C2040a;
import com.ihealth.communication.cloud.p002a.C2050k;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CommCloudCenter {
    public long TS = 0;
    String f476a = "111111";
    private Context f477b;
    public String messageReturn = "";
    public int queueNum = 0;
    public int result = 0;
    public float resultMessage = 0.0f;

    public CommCloudCenter(Context context) {
        this.f477b = context;
    }

    private String m340a() {
        return new C2040a(this.f477b).m362b();
    }

    private static String m341a(String str) {
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
            char[] toCharArray = str.toCharArray();
            byte[] bArr = new byte[toCharArray.length];
            for (int i2 = 0; i2 < toCharArray.length; i2++) {
                bArr[i2] = (byte) toCharArray[i2];
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            while (i < digest.length) {
                int i3 = digest[i] & 255;
                if (i3 < 16) {
                    stringBuffer.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                stringBuffer.append(Integer.toHexString(i3));
                i++;
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private String m342b() {
        return new C2040a(this.f477b).m361a();
    }

    public ReturnDataCenter PrivacyandAuthorizationDownload(String client_id, String client_secret) {
        ReturnDataCenter returnDataCenter = new ReturnDataCenter();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "c1da25bfc46b4a638839f454bb96b725");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m342b());
        hashMap.put("PhoneOS", AbstractSpiCall.ANDROID_CLIENT_TYPE + VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m340a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", this.f476a);
        hashMap.put("Token", "");
        hashMap.put("client_id", client_id);
        hashMap.put("client_secret", client_secret);
        hashMap.put(SettingsJsonConstants.ICON_HASH_KEY, m341a(client_id + "ihealth_API-SDK" + this.f476a));
        this.messageReturn = new C2050k(this.f477b).m382a("https://api.ihealthlabs.com:443/apicenter/PrivacyandAuthorizationDownload.htm", hashMap, "UTF-8");
        if (this.messageReturn.length() == 0) {
            return returnDataCenter;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject.getString("ResultMessage"));
            this.queueNum = jSONObject.getInt("QueueNum");
            returnDataCenter.setReturncode(jSONObject.getString("ResultMessage"));
            if (((double) this.resultMessage) != 100.0d) {
                return returnDataCenter;
            }
            String str;
            int i;
            String string;
            jSONObject = (JSONObject) new JSONTokener(jSONObject.getString("ReturnValue")).nextValue();
            JSONArray jSONArray = jSONObject.getJSONArray("ContentList");
            int length = jSONArray.length();
            if (length > 0) {
                str = "";
                str = "";
                for (i = 0; i < length; i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        string = optJSONObject.getString("Content");
                        int i2 = optJSONObject.getInt("ContentType");
                        String str2 = "";
                        String str3 = i2 == 1 ? "Privacy:" : i2 == 2 ? "Terms:" : "Other:";
                        str = str + str3 + "\n" + string + "\n";
                    }
                }
                returnDataCenter.setContent(str);
            }
            JSONArray jSONArray2 = jSONObject.getJSONArray("AuthorizationList");
            i = jSONArray2.length();
            if (i > 0) {
                str = "";
                str = "";
                str = "";
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < i; i3++) {
                    JSONObject optJSONObject2 = jSONArray2.optJSONObject(i3);
                    if (optJSONObject2 != null) {
                        String string2 = optJSONObject2.getString("APIName");
                        string = optJSONObject2.getString("APIShowName");
                        String string3 = optJSONObject2.getString("APIDescription");
                        ApiData apiData = new ApiData();
                        apiData.setAPIName(string2);
                        apiData.setAPIShowName(string);
                        apiData.setAPIDescription(string3);
                        arrayList.add(apiData);
                    }
                }
                returnDataCenter.setApiInfo(arrayList);
            }
            returnDataCenter.setAuthorizationTitle(jSONObject.getString("AuthorizationTitle"));
            return returnDataCenter;
        } catch (JSONException e) {
            e.printStackTrace();
            return returnDataCenter;
        }
    }

    public String ServiceHostListByCountry_get() {
        String str;
        JSONException jSONException;
        String str2 = "";
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "b497716bec0b4850a0cc1d2026412d9a");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m342b());
        hashMap.put("PhoneOS", AbstractSpiCall.ANDROID_CLIENT_TYPE + VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m340a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", this.f476a);
        hashMap.put("Token", "");
        this.messageReturn = new C2050k(this.f477b).m382a("https://api.ihealthlabs.com:443/apicenter/ServiceHostListByCountry_get.htm", hashMap, "UTF-8");
        if (this.messageReturn.length() == 0) {
            return str2;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject.getString("ResultMessage"));
            this.queueNum = jSONObject.getInt("QueueNum");
            if (((double) this.resultMessage) != 100.0d) {
                return str2;
            }
            JSONArray jSONArray = ((JSONObject) new JSONTokener(jSONObject.getString("ReturnValue")).nextValue()).getJSONArray("HostList");
            int length = jSONArray.length();
            if (length > 0) {
                str = str2;
                int i = 0;
                while (i < length) {
                    try {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        if (optJSONObject != null && 1 == optJSONObject.getInt("HostType")) {
                            str = optJSONObject.getString("HostUrl");
                        }
                        i++;
                    } catch (JSONException e) {
                        JSONException jSONException2 = e;
                        str2 = str;
                        jSONException = jSONException2;
                    }
                }
            } else {
                str = str2;
            }
            return str;
        } catch (JSONException e2) {
            jSONException = e2;
            jSONException.printStackTrace();
            return str2;
        }
    }
}

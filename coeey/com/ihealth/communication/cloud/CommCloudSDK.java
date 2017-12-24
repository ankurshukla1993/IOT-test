package com.ihealth.communication.cloud;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.cooey.android.users.old.utils.CTConstants;
import com.facebook.appevents.AppEventsConstants;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.p002a.C2040a;
import com.ihealth.communication.cloud.p002a.C2050k;
import com.ihealth.communication.utils.Log;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.net.SocketTimeoutException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CommCloudSDK {
    public long TS = 0;
    boolean f484a = false;
    String f485b = "111111";
    private Context f486c;
    public String messageReturn = "";
    public int queueNum = 0;
    public int result = 0;
    public float resultMessage = 0.0f;

    public CommCloudSDK(Context context) {
        this.f486c = context;
    }

    private String m346a() {
        return new C2040a(this.f486c).m362b();
    }

    private static String m347a(String str) {
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

    private String m348b() {
        return new C2040a(this.f486c).m361a();
    }

    public ReturnDataUser UserCombine(String client_id, String client_secret, String username, String host) {
        ReturnDataUser returnDataUser = new ReturnDataUser();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "2fb61340b51445daa7670d781b0a7cc5");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m348b());
        hashMap.put("PhoneOS", AbstractSpiCall.ANDROID_CLIENT_TYPE + VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m346a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", this.f485b);
        hashMap.put("Token", "");
        hashMap.put("client_id", client_id);
        hashMap.put("client_secret", client_secret);
        hashMap.put(CTConstants.USERNAME, username);
        hashMap.put(SettingsJsonConstants.ICON_HASH_KEY, m347a(client_id + "ihealth_API-SDK" + this.f485b));
        this.messageReturn = new C2050k(this.f486c).m382a(host + "/api5/" + "UserCombine.htm", hashMap, "UTF-8");
        if (this.messageReturn.length() == 0) {
            return returnDataUser;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject.getString("ResultMessage"));
            this.queueNum = jSONObject.getInt("QueueNum");
            returnDataUser.setResultCode(jSONObject.getString("ResultMessage"));
            jSONObject = (JSONObject) new JSONTokener(jSONObject.getString("ReturnValue")).nextValue();
            String string;
            if (((double) this.resultMessage) == 100.0d) {
                String string2 = jSONObject.getString("APIName");
                String string3 = jSONObject.getString("AccessToken");
                long j = jSONObject.getLong("Expires");
                string = jSONObject.getString("RefreshToken");
                returnDataUser.setApiName(string2);
                returnDataUser.setAccessToken(string3);
                returnDataUser.setExpires(j);
                returnDataUser.setRefreshToken(string);
                returnDataUser.setId(Integer.parseInt(jSONObject.getString("ID")));
                return returnDataUser;
            } else if (((double) this.resultMessage) != 208.0d) {
                return returnDataUser;
            } else {
                string = jSONObject.getString("RegionHost");
                ReturnDataUser UserCombine = UserCombine(client_id, client_secret, username, string);
                if (!"100".equals(UserCombine.getResultCode())) {
                    return UserCombine;
                }
                UserCheckSDK.saveUserInfo(this.f486c, username, null, string, null, null, null, null, returnDataUser.getId());
                return UserCombine;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return returnDataUser;
        }
    }

    public ReturnDataUser UserExistForThird(String client_id, String client_secret, String username, String host) {
        ReturnDataUser returnDataUser = new ReturnDataUser();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "828811c6b7494c7b8c34b101a95f7877");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m348b());
        hashMap.put("PhoneOS", AbstractSpiCall.ANDROID_CLIENT_TYPE + VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m346a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", this.f485b);
        hashMap.put("Token", "");
        hashMap.put("client_id", client_id);
        hashMap.put("client_secret", client_secret);
        hashMap.put(CTConstants.USERNAME, username);
        hashMap.put(SettingsJsonConstants.ICON_HASH_KEY, m347a(client_id + "ihealth_API-SDK" + this.f485b));
        this.messageReturn = new C2050k(this.f486c).m382a(host + "/api5/" + "UserExistForThird.htm", hashMap, "UTF-8");
        if (this.messageReturn.length() == 0) {
            return returnDataUser;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject.getString("ResultMessage"));
            this.queueNum = jSONObject.getInt("QueueNum");
            returnDataUser.setResultCode(jSONObject.getString("ResultMessage"));
            if (this.resultMessage == 225.0f || this.resultMessage == 224.0f || this.resultMessage == 223.0f) {
                return returnDataUser;
            }
            jSONObject = (JSONObject) new JSONTokener(jSONObject.getString("ReturnValue")).nextValue();
            if (((double) this.resultMessage) == 100.0d) {
                int i = jSONObject.getInt("ID");
                int i2 = jSONObject.getInt("Status");
                returnDataUser.setId(i);
                returnDataUser.setStatus(i2);
                UserCheckSDK.saveUserInfo(this.f486c, username, null, host, null, null, null, null, i);
                return returnDataUser;
            } else if (((double) this.resultMessage) != 208.0d) {
                return returnDataUser;
            } else {
                String string = jSONObject.getString("RegionHost");
                ReturnDataUser UserExistForThird = UserExistForThird(client_id, client_secret, username, string);
                if (!"100".equals(UserExistForThird.getResultCode())) {
                    return UserExistForThird;
                }
                UserCheckSDK.saveUserInfo(this.f486c, username, null, string, null, null, null, null, returnDataUser.getId());
                return UserExistForThird;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return returnDataUser;
        }
    }

    public ReturnDataUser UserRegister(String client_id, String client_secret, String username, String host) {
        ReturnDataUser returnDataUser = new ReturnDataUser();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "4e4d39ad421e47dea254a86d91e673e3");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m348b());
        hashMap.put("PhoneOS", AbstractSpiCall.ANDROID_CLIENT_TYPE + VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m346a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", this.f485b);
        hashMap.put("Token", "");
        hashMap.put("client_id", client_id);
        hashMap.put("client_secret", client_secret);
        hashMap.put(CTConstants.USERNAME, username);
        hashMap.put(SettingsJsonConstants.ICON_HASH_KEY, m347a(client_id + "ihealth_API-SDK" + this.f485b));
        this.messageReturn = new C2050k(this.f486c).m382a(host + "/api5/" + "UserRegister.htm", hashMap, "UTF-8");
        if (this.messageReturn.length() == 0) {
            return returnDataUser;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject.getString("ResultMessage"));
            this.queueNum = jSONObject.getInt("QueueNum");
            returnDataUser.setResultCode(jSONObject.getString("ResultMessage"));
            jSONObject = (JSONObject) new JSONTokener(jSONObject.getString("ReturnValue")).nextValue();
            String string;
            if (((double) this.resultMessage) == 100.0d) {
                String string2 = jSONObject.getString("APIName");
                String string3 = jSONObject.getString("AccessToken");
                long j = jSONObject.getLong("Expires");
                string = jSONObject.getString("RefreshToken");
                returnDataUser.setApiName(string2);
                returnDataUser.setAccessToken(string3);
                returnDataUser.setExpires(j);
                returnDataUser.setRefreshToken(string);
                returnDataUser.setId(Integer.parseInt(jSONObject.getString("ID")));
                UserCheckSDK.saveUserInfo(this.f486c, null, null, host, null, null, null, null, returnDataUser.getId());
                return returnDataUser;
            } else if (((double) this.resultMessage) != 208.0d) {
                return returnDataUser;
            } else {
                string = jSONObject.getString("RegionHost");
                ReturnDataUser UserExistForThird = UserExistForThird(client_id, client_secret, username, string);
                if (!"100".equals(UserExistForThird.getResultCode())) {
                    return UserExistForThird;
                }
                UserCheckSDK.saveUserInfo(this.f486c, null, null, string, null, null, null, null, returnDataUser.getId());
                return UserExistForThird;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return returnDataUser;
        }
    }

    public ReturnDataUser UserSign(String client_id, String client_secret, String username, String host) {
        ReturnDataUser returnDataUser = new ReturnDataUser();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "b3118e4fa5204f6dba1e2f1723270747");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m348b());
        hashMap.put("PhoneOS", AbstractSpiCall.ANDROID_CLIENT_TYPE + VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m346a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", this.f485b);
        hashMap.put("Token", "");
        hashMap.put("client_id", client_id);
        hashMap.put("client_secret", client_secret);
        hashMap.put(CTConstants.USERNAME, username);
        hashMap.put(SettingsJsonConstants.ICON_HASH_KEY, m347a(client_id + "ihealth_API-SDK" + this.f485b));
        this.messageReturn = new C2050k(this.f486c).m382a(host + "/api5/" + "UserSign.htm", hashMap, "UTF-8");
        if (this.messageReturn.length() == 0) {
            return returnDataUser;
        }
        try {
            JSONObject jSONObject = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject.getString("ResultMessage"));
            this.queueNum = jSONObject.getInt("QueueNum");
            returnDataUser.setResultCode(jSONObject.getString("ResultMessage"));
            jSONObject = (JSONObject) new JSONTokener(jSONObject.getString("ReturnValue")).nextValue();
            if (((double) this.resultMessage) == 100.0d) {
                String string = jSONObject.getString("APIName");
                String string2 = jSONObject.getString("AccessToken");
                long j = jSONObject.getLong("Expires");
                String string3 = jSONObject.getString("RefreshToken");
                this.f486c.getSharedPreferences("ihealth_userid", 0).edit().putInt(username, Integer.parseInt(jSONObject.getString("ID"))).apply();
                returnDataUser.setId(Integer.parseInt(jSONObject.getString("ID")));
                if (this.f484a) {
                    Log.i("HS5wifi", "apiName = " + string);
                    Log.i("HS5wifi", "AccessToken = " + string2);
                    Log.i("HS5wifi", "Expires = " + j);
                    Log.i("HS5wifi", "RefreshToken = " + string3);
                }
                returnDataUser.setApiName(string);
                returnDataUser.setAccessToken(string2);
                returnDataUser.setExpires(j);
                returnDataUser.setRefreshToken(string3);
                UserCheckSDK.saveUserInfo(this.f486c, username, string, host, string2, string3, client_id, client_secret, returnDataUser.getId());
                return returnDataUser;
            } else if (((double) this.resultMessage) != 208.0d) {
                return returnDataUser;
            } else {
                String string4 = jSONObject.getString("RegionHost");
                ReturnDataUser UserSign = UserSign(client_id, client_secret, username, string4);
                if (!"100".equals(UserSign.getResultCode())) {
                    return UserSign;
                }
                UserCheckSDK.saveUserInfo(this.f486c, username, null, string4, null, null, null, null, UserSign.getId());
                return UserSign;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return returnDataUser;
        }
    }

    public ReturnDataUser token_refresh(String tokenRefresh, String username, String host) {
        ReturnDataUser returnDataUser = new ReturnDataUser();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "9e4a5f26773e4d8a87ce2b83fa2641b3");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m348b());
        hashMap.put("PhoneOS", AbstractSpiCall.ANDROID_CLIENT_TYPE + VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m346a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", this.f485b);
        hashMap.put("Token", "");
        hashMap.put("TokenRefresh", tokenRefresh);
        hashMap.put("Un", username);
        try {
            this.messageReturn = new C2050k(this.f486c).m382a(host + "/api5/" + "token_refresh.htm", hashMap, "UTF-8");
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
        } catch (SocketTimeoutException e2) {
            e2.printStackTrace();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return returnDataUser;
        }
        String str = "";
        try {
            JSONObject jSONObject = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject.getString("ResultMessage"));
            this.queueNum = jSONObject.getInt("QueueNum");
            returnDataUser.setResultCode(jSONObject.getString("ResultMessage"));
            if (((double) this.resultMessage) != 100.0d) {
                return returnDataUser;
            }
            jSONObject = (JSONObject) new JSONTokener(jSONObject.getString("ReturnValue")).nextValue();
            String string = jSONObject.getString("APIName");
            String string2 = jSONObject.getString("AccessToken");
            long j = jSONObject.getLong("Expires");
            str = jSONObject.getString("RefreshToken");
            returnDataUser.setApiName(string);
            returnDataUser.setAccessToken(string2);
            returnDataUser.setExpires(j);
            returnDataUser.setRefreshToken(str);
            return returnDataUser;
        } catch (JSONException e4) {
            e4.printStackTrace();
            return returnDataUser;
        }
    }
}

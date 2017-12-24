package com.ihealth.communication.cloud;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.cooey.android.users.old.utils.CTConstants;
import com.facebook.appevents.AppEventsConstants;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.Date_TB_HS6MeasureResult;
import com.ihealth.communication.cloud.data.UserNetData;
import com.ihealth.communication.cloud.p002a.C2040a;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.cloud.p002a.C2050k;
import com.ihealth.communication.cloud.p002a.C2051l;
import com.ihealth.communication.utils.Log;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CommCloudHS6 {
    private static String f478f = "/api5/";
    public ArrayList Bind_HS6ReturnArr;
    public long TS = 0;
    public ArrayList Unbind_HS6ReturnArr;
    private Context f479a;
    private final String f480b = "7c789858c0ec4ebf8189ebb14b6730a5";
    private String f481c;
    private String f482d;
    private String f483e;
    public String messageReturn = "";
    public int result = 0;
    public float resultMessage = 0.0f;
    public C2054a returnData = null;

    public CommCloudHS6(String username, Context context) {
        this.f479a = context;
        this.f482d = username;
        this.f483e = context.getSharedPreferences(this.f482d + "userinfo", 0).getString("accessToken", "");
        this.f481c = context.getSharedPreferences(this.f482d + "userinfo", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f481c)) {
            this.f481c = C2041b.f506b;
        }
    }

    private String m343a() {
        return new C2040a(this.f479a).m362b();
    }

    private static String m344a(String str) {
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

    private String m345b() {
        return new C2040a(this.f479a).m361a();
    }

    public boolean User_netdevice_Bind(ArrayList hs6Arr) {
        this.Bind_HS6ReturnArr = new ArrayList();
        this.TS = 0;
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "af7593f2e0744df2ab05f053d08f4dbf");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m345b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m343a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        int i = 0;
        while (i < hs6Arr.size()) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("MAC", ((Date_TB_HS6MeasureResult) hs6Arr.get(i)).getMAC());
                jSONObject.put("Model", ((Date_TB_HS6MeasureResult) hs6Arr.get(i)).getModel());
                jSONObject.put(CTConstants.POSITION, ((Date_TB_HS6MeasureResult) hs6Arr.get(i)).getPosition());
                jSONObject.put("TS", ((Date_TB_HS6MeasureResult) hs6Arr.get(i)).getTS());
                jSONArray.put(i, jSONObject);
                i++;
            } catch (Exception e) {
                Log.w("CommCloudHS6", e.toString());
            }
        }
        hashMap.put("Un", this.f482d);
        hashMap.put("VerifyToken", this.f483e);
        hashMap.put("UploadData", jSONArray.toString());
        try {
            this.messageReturn = new C2050k(this.f479a).m382a(this.f481c + f478f + "user_netdevice_bind.htm", hashMap, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return false;
        }
        JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
        Log.v("CommCloudHS6", "bind result = " + jSONObject2.toString());
        this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
        this.TS = Long.parseLong(jSONObject2.getString("TS"));
        this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
        if (((double) this.resultMessage) == 100.0d) {
            JSONArray jSONArray2 = (JSONArray) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue();
            int length = jSONArray2.length();
            if (length != 0) {
                for (i = 0; i < length; i++) {
                    Date_TB_HS6MeasureResult date_TB_HS6MeasureResult = new Date_TB_HS6MeasureResult();
                    jSONObject = jSONArray2.optJSONObject(i);
                    if (jSONObject == null) {
                        Log.v("CommCloudHS6", "jsonHS6ItemOb == null");
                    } else {
                        date_TB_HS6MeasureResult.setMAC(jSONObject.getString("MAC"));
                        date_TB_HS6MeasureResult.setStatus(jSONObject.getInt("Status"));
                        date_TB_HS6MeasureResult.setAction(jSONObject.getInt("Action"));
                        date_TB_HS6MeasureResult.setPosition(jSONObject.getInt(CTConstants.POSITION));
                        date_TB_HS6MeasureResult.setSetWifi(jSONObject.getInt("SetWifi"));
                        try {
                            date_TB_HS6MeasureResult.setBindNum(jSONObject.getInt("BindNum"));
                        } catch (Exception e3) {
                            try {
                                date_TB_HS6MeasureResult.setBindNum(0);
                            } catch (JSONException e4) {
                                e4.printStackTrace();
                                return false;
                            }
                        }
                        this.Bind_HS6ReturnArr.add(date_TB_HS6MeasureResult);
                    }
                }
            }
            return true;
        } else if (this.resultMessage != 212.0f && this.resultMessage != 221.0f) {
            return false;
        } else {
            ReturnDataUser token_refresh = new CommCloudSDK(this.f479a).token_refresh(this.f479a.getSharedPreferences(this.f482d + "userinfo", 0).getString("refreshToken", ""), this.f482d, this.f481c);
            if (!"100".equals(token_refresh.getResultCode())) {
                return false;
            }
            this.f483e = token_refresh.getAccessToken();
            return User_netdevice_Bind(hs6Arr);
        }
    }

    public boolean User_netdevice_Unbind(ArrayList hs6Arr) {
        int i;
        this.Unbind_HS6ReturnArr = new ArrayList();
        this.TS = 0;
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "dd603c07bff9428280e0c7452b48a79e");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m345b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m343a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        try {
            Log.v("CommCloudHS6", "Disbind HS6 size = " + hs6Arr.size());
            for (i = 0; i < hs6Arr.size(); i++) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("MAC", ((Date_TB_HS6MeasureResult) hs6Arr.get(i)).getMAC());
                jSONObject.put("Model", ((Date_TB_HS6MeasureResult) hs6Arr.get(i)).getModel());
                jSONObject.put("TS", ((Date_TB_HS6MeasureResult) hs6Arr.get(i)).getTS());
                jSONArray.put(i, jSONObject);
            }
        } catch (Exception e) {
            Log.w("CommCloudHS6", e.toString());
        }
        hashMap.put("Un", this.f482d);
        hashMap.put("VerifyToken", this.f483e);
        hashMap.put("UploadData", jSONArray.toString());
        Log.v("", "disbind mac upload = " + hashMap.toString());
        try {
            this.messageReturn = new C2050k(this.f479a).m382a(this.f481c + f478f + "user_netdevice_unbind.htm", hashMap, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return false;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            Log.v("CommCloudHS6", "disbind mac result = " + jSONObject2.toString());
            this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject2.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
            if (((double) this.resultMessage) == 100.0d) {
                JSONArray jSONArray2 = (JSONArray) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue();
                int length = jSONArray2.length();
                if (length != 0) {
                    for (i = 0; i < length; i++) {
                        Date_TB_HS6MeasureResult date_TB_HS6MeasureResult = new Date_TB_HS6MeasureResult();
                        jSONObject = jSONArray2.optJSONObject(i);
                        if (jSONObject == null) {
                            Log.v("CommCloudHS6", "jsonHS6ItemOb == null");
                        } else {
                            date_TB_HS6MeasureResult.setMAC(jSONObject.getString("MAC"));
                            date_TB_HS6MeasureResult.setStatus(jSONObject.getInt("Status"));
                            date_TB_HS6MeasureResult.setAction(jSONObject.getInt("Action"));
                            this.Unbind_HS6ReturnArr.add(date_TB_HS6MeasureResult);
                        }
                    }
                }
                return true;
            } else if (this.resultMessage != 212.0f && this.resultMessage != 221.0f) {
                return false;
            } else {
                ReturnDataUser token_refresh = new CommCloudSDK(this.f479a).token_refresh(this.f479a.getSharedPreferences(this.f482d + "userinfo", 0).getString("refreshToken", ""), this.f482d, this.f481c);
                if (!"100".equals(token_refresh.getResultCode())) {
                    return false;
                }
                this.f483e = token_refresh.getAccessToken();
                return User_netdevice_Unbind(hs6Arr);
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public String sdk_get_token(String client_id, String client_secret, String username, String client_para) {
        JSONObject jSONObject;
        if (UserCheckSDK.isNetworkAvailable(this.f479a)) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("client_id", client_id);
                hashMap.put("client_secret", client_secret);
                hashMap.put(CTConstants.USERNAME, username);
                hashMap.put("client_para", client_para);
                hashMap.put(SettingsJsonConstants.ICON_HASH_KEY, m344a(client_para + "|" + username + "|" + client_secret + "|" + client_id));
                try {
                    this.messageReturn = new C2050k(this.f479a).m382a(this.f481c + "/openapiv2/user/sdk_get_token.json", hashMap, "UTF-8");
                    JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
                    if (jSONObject2.isNull("ErrorCode") || jSONObject2.getInt("ErrorCode") != 2005) {
                        return this.messageReturn;
                    }
                    this.f481c = jSONObject2.getString("UserRegion");
                    return sdk_get_token(client_id, client_secret, username, client_para);
                } catch (SocketTimeoutException e) {
                    jSONObject = new JSONObject();
                    try {
                        jSONObject.put("ErrorCode", 102);
                        jSONObject.put("ErrorDescription", "Network timeout");
                    } catch (JSONException e2) {
                        e.printStackTrace();
                    }
                    return jSONObject.toString();
                }
            } catch (Exception e3) {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("Exception", "" + e3);
                } catch (JSONException e4) {
                    e3.printStackTrace();
                }
                return jSONObject.toString();
            }
        }
        jSONObject = new JSONObject();
        try {
            jSONObject.put("ErrorCode", 101);
            jSONObject.put("ErrorDescription", "No network");
        } catch (JSONException e5) {
            e5.printStackTrace();
        }
        return jSONObject.toString();
    }

    public int sync_unit(String username, int unit) {
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "f62995e6922547e294d11f7218a91383");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m345b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m343a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(CTConstants.FAB_WEIGHT, unit + "");
            jSONObject.put("Weight_TS", C2051l.m390b() + "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hashMap.put("Un", username);
        hashMap.put("VerifyToken", this.f483e);
        hashMap.put("UploadData", jSONObject.toString());
        try {
            this.messageReturn = new C2050k(this.f479a).m382a(this.f481c + "/api5/sync_unit.htm", hashMap, "UTF-8");
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
                this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
                if (((double) this.resultMessage) != 100.0d) {
                    return (int) this.resultMessage;
                }
                jSONObject2 = (JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue();
                return 100;
            } catch (JSONException e2) {
                e2.printStackTrace();
                return 999;
            }
        } catch (UnknownHostException e3) {
            return 101;
        } catch (SocketTimeoutException e4) {
            return 102;
        }
    }

    public int user_upload(String userName, UserNetData ob) {
        this.returnData = new C2054a(this);
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "cec7c99b534049de90b211ac7f4e90c5");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m345b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m343a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < ob.getEmail().length; i++) {
                jSONArray.put(i, ob.getEmail()[i]);
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("Data", ob.logo.data);
                jSONObject2.put("TS", ob.logo.TS);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONObject.put("ID", ob.getID());
            jSONObject.put("Birthday", ob.getBirthday());
            if (jSONArray != null) {
                jSONObject.put("Email", jSONArray);
            }
            jSONObject.put("Gender", ob.getGender());
            jSONObject.put("IsSporter", ob.getIsSporter());
            if (ob.getName() != null) {
                jSONObject.put("Name", ob.getName());
            }
            jSONObject.put("Height", ob.getHeight());
            jSONObject.put(CTConstants.FAB_WEIGHT, (double) ob.getWeight());
            if (ob.getNation() != null) {
                jSONObject.put("Nation", ob.getNation());
            }
            if (ob.getLanguage() != null) {
                jSONObject.put("Language", ob.getLanguage());
            }
            jSONObject.put("usecloud", ob.getUsecloud());
            jSONObject.put("TS", System.currentTimeMillis() / 1000);
            if (jSONObject2 != null) {
                jSONObject.put("Logo", jSONObject2);
            }
            jSONObject.put("ActivityLevel", ob.getActivityLevel());
            jSONObject.put("TimeZone", (double) C2051l.m383a());
            if (ob.getUserNation() != null) {
                jSONObject.put("UserNation", ob.getUserNation());
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", this.f483e);
        hashMap.put("UploadData", jSONObject.toString());
        Log.v("CommCloudHS6", hashMap.toString() + "--ob.getUserNation()" + ob.getUserNation());
        try {
            this.messageReturn = new C2050k(this.f479a).m382a(this.f481c + "/userauthapi/user_upload.htm", hashMap, "UTF-8");
            if (this.messageReturn.length() == 0) {
                return 999;
            }
            if (this.messageReturn.length() == 3) {
                return Integer.valueOf(this.messageReturn).intValue();
            }
            try {
                JSONObject jSONObject3 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
                this.result = jSONObject3.getInt(DataBaseConstants.PO_RESULT);
                this.TS = Long.parseLong(jSONObject3.getString("TS"));
                int parseInt = Integer.parseInt(jSONObject3.getString("ResultMessage"));
                if (parseInt == 100) {
                    jSONObject3 = (JSONObject) new JSONTokener(jSONObject3.getString("ReturnValue")).nextValue();
                    this.returnData.f541a = jSONObject3.getInt("ID");
                    this.returnData.f542b = jSONObject3.getString("iHealthID");
                    Log.v("CommCloudHS6", "returnData.ID = " + this.returnData.f541a);
                    Log.v("CommCloudHS6", "returnData.iHealthID = " + this.returnData.f542b);
                } else if (parseInt == 212 || parseInt == 221) {
                    ReturnDataUser token_refresh = new CommCloudSDK(this.f479a).token_refresh(this.f479a.getSharedPreferences(this.f482d + "userinfo", 0).getString("refreshToken", ""), this.f482d, this.f481c);
                    if (!"100".equals(token_refresh.getResultCode())) {
                        return 999;
                    }
                    this.f483e = token_refresh.getAccessToken();
                    return user_upload(userName, ob);
                }
                return parseInt;
            } catch (JSONException e22) {
                e22.printStackTrace();
                return 999;
            }
        } catch (UnknownHostException e3) {
            return 101;
        } catch (SocketTimeoutException e4) {
            return 102;
        }
    }
}

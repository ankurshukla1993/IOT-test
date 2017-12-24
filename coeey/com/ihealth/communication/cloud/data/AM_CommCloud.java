package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import com.cooey.devices.helpers.CooeySQLHelper;
import com.facebook.appevents.AppEventsConstants;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.CommCloudSDK;
import com.ihealth.communication.cloud.UserCheckSDK;
import com.ihealth.communication.cloud.p002a.C2040a;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.cloud.p002a.C2050k;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.Log;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
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

public class AM_CommCloud {
    public static final String SV_ambinding = "6089f6b908684656a84fd5ce449042bf";
    public static final String SV_amsearch = "d33f5ba526e44b58ab84c6f29d00b716";
    public static final String SV_amunbinding = "444f63ec37a843e497566855a0d45fec";
    public static final String Sv_swimActivity_upload = "f845fc6716664a2aaf52e58b9aaf4881";
    public static final String Sv_swimReport_upload = "f845fc6716646a2aaf52e58b9aaf4881";
    public static final String TAG = "AM_CommCloudData";
    private static boolean f548d = false;
    public long TS = 0;
    Context f549a;
    public amSearchReturn amsearch_return;
    ambindingReturn f550b;
    ambindingReturn f551c;
    private String f552e = "111111";
    private String f553f;
    private String f554g;
    private String f555h;
    private String f556i;
    public String messageReturn = "";
    public int queueNum = 0;
    public int result = 0;
    public float resultMessage = 0.0f;

    public class amSearchReturn {
        final /* synthetic */ AM_CommCloud f544a;
        public String iHealthID = new String();
        public String[] mac;

        amSearchReturn(AM_CommCloud this$0) {
            this.f544a = this$0;
        }
    }

    public class ambindingReturn {
        int f545a = 0;
        String f546b = new String();
        final /* synthetic */ AM_CommCloud f547c;

        ambindingReturn(AM_CommCloud this$0) {
            this.f547c = this$0;
        }
    }

    public AM_CommCloud(Context context) {
        if (f548d) {
            Log.i(TAG, "实例化sdk_AuthorTools,获取本地配置 un host");
        }
        this.f549a = context;
        this.f553f = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "jiuan");
        this.f554g = context.getSharedPreferences(this.f553f + "userinfo", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f554g)) {
            this.f554g = C2041b.f506b;
        }
        this.f555h = context.getSharedPreferences(this.f553f + "userinfo", 0).getString("client_id", "");
        this.f556i = context.getSharedPreferences(this.f553f + "userinfo", 0).getString("client_secret", "");
        if (f548d) {
            Log.i(TAG, "取得un = " + this.f553f);
            Log.i(TAG, "取得host = " + this.f554g);
        }
    }

    private String m397a() {
        return new C2040a(this.f549a).m362b();
    }

    private String m398b() {
        return new C2040a(this.f549a).m361a();
    }

    public AM_ReturnData am_activity_upload(String userName, String VerifyToken, ArrayList hsArr, String inputHost) {
        AM_ReturnData aM_ReturnData = new AM_ReturnData();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "f655fca476104655b4b7a89cfde03661");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m398b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m397a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        try {
            if (f548d) {
                Log.i(TAG, hsArr.size() + "");
            }
            for (int i = 0; i < hsArr.size(); i++) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ChangeType", ((Data_AM_Activity) hsArr.get(i)).getChangeType());
                jSONObject.put("LastChangeTime", ((Data_AM_Activity) hsArr.get(i)).getLastChangeTime());
                jSONObject.put("PhoneDataID", ((Data_AM_Activity) hsArr.get(i)).getPhoneDataID());
                jSONObject.put("PhoneCreateTime", ((Data_AM_Activity) hsArr.get(i)).getPhoneCreateTime());
                jSONObject.put("Lat", ((Data_AM_Activity) hsArr.get(i)).getLat());
                jSONObject.put("Lon", ((Data_AM_Activity) hsArr.get(i)).getLon());
                jSONObject.put("TimeZone", (double) ((Data_AM_Activity) hsArr.get(i)).getTimeZone());
                jSONObject.put("Calories", (double) ((Data_AM_Activity) hsArr.get(i)).getCalorie());
                jSONObject.put("StepLength", ((Data_AM_Activity) hsArr.get(i)).getStepLength());
                jSONObject.put("Steps", ((Data_AM_Activity) hsArr.get(i)).getSteps());
                jSONObject.put("SunCalories", ((Data_AM_Activity) hsArr.get(i)).getSumCalorie());
                jSONObject.put("SunSteps", ((Data_AM_Activity) hsArr.get(i)).getSumSteps());
                jSONObject.put("MeasureTime", ((Data_AM_Activity) hsArr.get(i)).getMeasureTime());
                jSONObject.put("MechineType", ((Data_AM_Activity) hsArr.get(i)).getMechineType());
                jSONObject.put("MechineDeviceID", ((Data_AM_Activity) hsArr.get(i)).getMechineDeviceID());
                jSONArray.put(i, jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", VerifyToken);
        hashMap.put("UploadData", jSONArray.toString());
        String str = inputHost + C2041b.f507c + "amsport_upload.htm";
        if (f548d) {
            Log.i(TAG, "数据上传 = " + hashMap.toString());
        }
        try {
            this.messageReturn = new C2050k(this.f549a).m382a(str, hashMap, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return aM_ReturnData;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject2.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
            this.queueNum = jSONObject2.getInt("QueueNum");
            aM_ReturnData.setResultMessage(jSONObject2.getString("ResultMessage"));
            if (f548d) {
                Log.i(TAG, "上传返回 = " + this.resultMessage);
            }
            if (((double) this.resultMessage) == 100.0d) {
                return aM_ReturnData;
            }
            AM_ReturnData am_activity_upload;
            if (((double) this.resultMessage) == 208.0d) {
                String string = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                if (f548d) {
                    Log.i(TAG, "hs_upload返回208,拿到regionHost = " + string);
                }
                am_activity_upload = am_activity_upload(this.f553f, VerifyToken, hsArr, string);
                if (!"100".equals(am_activity_upload.getResultMessage())) {
                    return am_activity_upload;
                }
                UserCheckSDK.saveUserInfo(this.f549a, null, null, string, null, null, null, null, -1);
                if (f548d) {
                    Log.i(TAG, "保存regionHost到本地");
                }
                this.f554g = string;
                return am_activity_upload;
            } else if (((double) this.resultMessage) == 212.0d) {
                if (f548d) {
                    Log.i(TAG, "212->Token过期:刷新Token");
                }
                SharedPreferences sharedPreferences = this.f549a.getSharedPreferences("jiuan.sdk.author", 0);
                r1 = new CommCloudSDK(this.f549a).token_refresh(sharedPreferences.getString("refreshToken", ""), this.f553f, this.f554g);
                if (!"100".equals(r1.getResultCode())) {
                    return aM_ReturnData;
                }
                if (f548d) {
                    Log.i(TAG, "重新调用hs_upload");
                }
                r4 = r1.getAccessToken();
                am_activity_upload = am_activity_upload(this.f553f, r4, hsArr, this.f554g);
                if (!"100".equals(am_activity_upload.getResultMessage())) {
                    return am_activity_upload;
                }
                if (f548d) {
                    Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
                }
                r5 = r1.getRefreshToken();
                Editor edit = sharedPreferences.edit();
                edit.putString("accessToken", r4);
                edit.putString("refreshToken", r5);
                edit.commit();
                UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                return am_activity_upload;
            } else if (((double) this.resultMessage) != 221.0d) {
                return aM_ReturnData;
            } else {
                if (f548d) {
                    Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
                }
                AM_ReturnData aM_ReturnData2 = new AM_ReturnData();
                try {
                    r1 = new CommCloudSDK(this.f549a).UserSign(this.f555h, this.f556i, this.f553f, inputHost);
                    if (!"100".equals(r1.getResultCode())) {
                        return aM_ReturnData2;
                    }
                    aM_ReturnData2.setResultMessage(r1.getResultCode());
                    aM_ReturnData2.setRegionHost(r1.getRegionHost());
                    aM_ReturnData2.setAccessToken(r1.getAccessToken());
                    aM_ReturnData2.setRefreshToken(r1.getRefreshToken());
                    aM_ReturnData2.setExpires(r1.getExpires());
                    if (f548d) {
                        Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
                    }
                    r4 = aM_ReturnData2.getAccessToken();
                    am_activity_upload = am_activity_upload(this.f553f, r4, hsArr, inputHost);
                    if (!"100".equals(am_activity_upload.getResultMessage())) {
                        return am_activity_upload;
                    }
                    if (f548d) {
                        Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");
                    }
                    Editor edit2 = this.f549a.getSharedPreferences("jiuan.sdk.author", 0).edit();
                    r5 = aM_ReturnData2.getRefreshToken();
                    edit2.putString("accessToken", r4);
                    edit2.putString("refreshToken", r5);
                    edit2.commit();
                    UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                    return am_activity_upload;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return aM_ReturnData;
                }
            }
        } catch (JSONException e3) {
            return aM_ReturnData;
        }
    }

    public AM_ReturnData am_report_upload(String userName, String VerifyToken, ArrayList hsArr, String inputHost) {
        AM_ReturnData aM_ReturnData = new AM_ReturnData();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "569f5746b8a840a490a4f7287ba822fa");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m398b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m397a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        try {
            if (f548d) {
                Log.i(TAG, hsArr.size() + "");
            }
            for (int i = 0; i < hsArr.size(); i++) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ChangeType", ((Data_AM_ActivityDayReport) hsArr.get(i)).getChangeType());
                jSONObject.put("LastChangeTime", ((Data_AM_ActivityDayReport) hsArr.get(i)).getLastChangeTime());
                jSONObject.put("PhoneDataID", ((Data_AM_ActivityDayReport) hsArr.get(i)).getPhoneDataID());
                jSONObject.put("PhoneCreateTime", ((Data_AM_ActivityDayReport) hsArr.get(i)).getPhoneCreateTime());
                jSONObject.put("Lat", ((Data_AM_ActivityDayReport) hsArr.get(i)).getLat());
                jSONObject.put("Lon", ((Data_AM_ActivityDayReport) hsArr.get(i)).getLon());
                jSONObject.put("TimeZone", (double) ((Data_AM_ActivityDayReport) hsArr.get(i)).getTimeZone());
                jSONObject.put("Calories", (double) ((Data_AM_ActivityDayReport) hsArr.get(i)).getCalorie());
                jSONObject.put("StepLength", ((Data_AM_ActivityDayReport) hsArr.get(i)).getStepLength());
                jSONObject.put("Steps", ((Data_AM_ActivityDayReport) hsArr.get(i)).getSteps());
                jSONObject.put("PlanCalories", (double) ((Data_AM_ActivityDayReport) hsArr.get(i)).getPlanCalorie());
                jSONObject.put(DataBaseConstants.ACTIVITYREPORT_PLANSTEPS, ((Data_AM_ActivityDayReport) hsArr.get(i)).getPlanSteps());
                jSONObject.put("City", ((Data_AM_ActivityDayReport) hsArr.get(i)).getCity());
                jSONObject.put("Weather", ((Data_AM_ActivityDayReport) hsArr.get(i)).getWeather());
                jSONObject.put("Comment", ((Data_AM_ActivityDayReport) hsArr.get(i)).getComment());
                jSONObject.put("MeasureTime", ((Data_AM_ActivityDayReport) hsArr.get(i)).getMeasureTime());
                jSONObject.put("MechineType", ((Data_AM_ActivityDayReport) hsArr.get(i)).getMechineType());
                jSONObject.put("MechineDeviceID", ((Data_AM_ActivityDayReport) hsArr.get(i)).getMechineDeviceID());
                jSONObject.put("Mood", 1);
                jSONObject.put("Activity", 1);
                jSONObject.put("OutDoorRatio", 50);
                jSONArray.put(i, jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", VerifyToken);
        hashMap.put("UploadData", jSONArray.toString());
        String str = inputHost + C2041b.f507c + "amsportdaily_upload.htm";
        if (f548d) {
            Log.i(TAG, "数据上传 = " + hashMap.toString());
        }
        try {
            this.messageReturn = new C2050k(this.f549a).m382a(str, hashMap, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return aM_ReturnData;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject2.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
            this.queueNum = jSONObject2.getInt("QueueNum");
            aM_ReturnData.setResultMessage(jSONObject2.getString("ResultMessage"));
            if (f548d) {
                Log.i(TAG, "上传返回 = " + this.resultMessage);
            }
            if (((double) this.resultMessage) == 100.0d) {
                return aM_ReturnData;
            }
            AM_ReturnData am_report_upload;
            if (((double) this.resultMessage) == 208.0d) {
                String string = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                if (f548d) {
                    Log.i(TAG, "hs_upload返回208,拿到regionHost = " + string);
                }
                am_report_upload = am_report_upload(this.f553f, VerifyToken, hsArr, string);
                if (!"100".equals(am_report_upload.getResultMessage())) {
                    return am_report_upload;
                }
                UserCheckSDK.saveUserInfo(this.f549a, null, null, string, null, null, null, null, -1);
                if (f548d) {
                    Log.i(TAG, "保存regionHost到本地");
                }
                this.f554g = string;
                return am_report_upload;
            } else if (((double) this.resultMessage) == 212.0d) {
                if (f548d) {
                    Log.i(TAG, "212->Token过期:刷新Token");
                }
                SharedPreferences sharedPreferences = this.f549a.getSharedPreferences("jiuan.sdk.author", 0);
                r1 = new CommCloudSDK(this.f549a).token_refresh(sharedPreferences.getString("refreshToken", ""), this.f553f, this.f554g);
                if (!"100".equals(r1.getResultCode())) {
                    return aM_ReturnData;
                }
                if (f548d) {
                    Log.i(TAG, "重新调用hs_upload");
                }
                r4 = r1.getAccessToken();
                am_report_upload = am_report_upload(this.f553f, r4, hsArr, this.f554g);
                if (!"100".equals(am_report_upload.getResultMessage())) {
                    return am_report_upload;
                }
                if (f548d) {
                    Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
                }
                r5 = r1.getRefreshToken();
                Editor edit = sharedPreferences.edit();
                edit.putString("accessToken", r4);
                edit.putString("refreshToken", r5);
                edit.commit();
                UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                return am_report_upload;
            } else if (((double) this.resultMessage) != 221.0d) {
                return aM_ReturnData;
            } else {
                if (f548d) {
                    Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
                }
                AM_ReturnData aM_ReturnData2 = new AM_ReturnData();
                try {
                    r1 = new CommCloudSDK(this.f549a).UserSign(this.f555h, this.f556i, this.f553f, inputHost);
                    if (!"100".equals(r1.getResultCode())) {
                        return aM_ReturnData2;
                    }
                    aM_ReturnData2.setResultMessage(r1.getResultCode());
                    aM_ReturnData2.setRegionHost(r1.getRegionHost());
                    aM_ReturnData2.setAccessToken(r1.getAccessToken());
                    aM_ReturnData2.setRefreshToken(r1.getRefreshToken());
                    aM_ReturnData2.setExpires(r1.getExpires());
                    if (f548d) {
                        Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
                    }
                    r4 = aM_ReturnData2.getAccessToken();
                    am_report_upload = am_report_upload(this.f553f, r4, hsArr, inputHost);
                    if (!"100".equals(am_report_upload.getResultMessage())) {
                        return am_report_upload;
                    }
                    if (f548d) {
                        Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");
                    }
                    Editor edit2 = this.f549a.getSharedPreferences("jiuan.sdk.author", 0).edit();
                    r5 = aM_ReturnData2.getRefreshToken();
                    edit2.putString("accessToken", r4);
                    edit2.putString("refreshToken", r5);
                    edit2.commit();
                    UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                    return am_report_upload;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return aM_ReturnData;
                }
            }
        } catch (JSONException e3) {
            return aM_ReturnData;
        }
    }

    public AM_ReturnData am_section_upload(String userName, String VerifyToken, ArrayList hsArr, String inputHost) {
        AM_ReturnData aM_ReturnData = new AM_ReturnData();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "3574ce171f834a109a572d0a3431025b");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m398b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m397a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        try {
            if (f548d) {
                Log.i(TAG, hsArr.size() + "");
            }
            for (int i = 0; i < hsArr.size(); i++) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ChangeType", ((Data_AM_SleepSectionReport) hsArr.get(i)).getChangeType());
                jSONObject.put("LastChangeTime", ((Data_AM_SleepSectionReport) hsArr.get(i)).getLastChangeTime());
                jSONObject.put("PhoneDataID", ((Data_AM_SleepSectionReport) hsArr.get(i)).getPhoneDataID());
                jSONObject.put("PhoneCreateTime", ((Data_AM_SleepSectionReport) hsArr.get(i)).getPhoneCreateTime());
                jSONObject.put("Lat", ((Data_AM_SleepSectionReport) hsArr.get(i)).getLat());
                jSONObject.put("Lon", ((Data_AM_SleepSectionReport) hsArr.get(i)).getLon());
                jSONObject.put("TimeZone", (double) ((Data_AM_SleepSectionReport) hsArr.get(i)).getTimeZone());
                jSONObject.put(DataBaseConstants.SLEEPREPORT_AWAKE, ((Data_AM_SleepSectionReport) hsArr.get(i)).getAwake());
                jSONObject.put(DataBaseConstants.SLEEPREPORT_DEEPSLEEP, ((Data_AM_SleepSectionReport) hsArr.get(i)).getDeepSleep());
                jSONObject.put(DataBaseConstants.SLEEPREPORT_FALLSLEEP, ((Data_AM_SleepSectionReport) hsArr.get(i)).getFallSleep());
                jSONObject.put(DataBaseConstants.SLEEPREPORT_SLEEP, ((Data_AM_SleepSectionReport) hsArr.get(i)).getSleep());
                jSONObject.put(DataBaseConstants.SLEEPREPORT_AWAKENTIMES, ((Data_AM_SleepSectionReport) hsArr.get(i)).getAwakenTimes());
                jSONObject.put(DataBaseConstants.SLEEPREPORT_SLEEPSTARTTIME, ((Data_AM_SleepSectionReport) hsArr.get(i)).getSleepStartTime());
                jSONObject.put(DataBaseConstants.SLEEPREPORT_SLEEPENDTIME, ((Data_AM_SleepSectionReport) hsArr.get(i)).getSleepEndTime());
                jSONObject.put("TimeSectionID", ((Data_AM_SleepSectionReport) hsArr.get(i)).getTimeSectionId());
                jSONObject.put("City", ((Data_AM_SleepSectionReport) hsArr.get(i)).getCity());
                jSONObject.put("Weather", ((Data_AM_SleepSectionReport) hsArr.get(i)).getWeather());
                jSONObject.put("Comment", ((Data_AM_SleepSectionReport) hsArr.get(i)).getComment());
                jSONObject.put(DataBaseConstants.SLEEPREPORT_NAP, ((Data_AM_SleepSectionReport) hsArr.get(i)).getNap());
                jSONObject.put("Mood", ((Data_AM_SleepSectionReport) hsArr.get(i)).getMood());
                jSONObject.put("Activity", ((Data_AM_SleepSectionReport) hsArr.get(i)).getActivity());
                jSONObject.put("MeasureTime", ((Data_AM_SleepSectionReport) hsArr.get(i)).getMeasureTime());
                jSONObject.put("MechineType", ((Data_AM_SleepSectionReport) hsArr.get(i)).getMechineType());
                jSONObject.put("MechineDeviceID", ((Data_AM_SleepSectionReport) hsArr.get(i)).getMechineDeviceID());
                jSONObject.put("Mood", 1);
                jSONObject.put("Activity", 1);
                jSONObject.put("OutDoorRatio", 50);
                jSONArray.put(i, jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", VerifyToken);
        hashMap.put("UploadData", jSONArray.toString());
        String str = inputHost + C2041b.f507c + "amsleepperiodreport_upload.htm";
        if (f548d) {
            Log.i(TAG, "数据上传 = " + hashMap.toString());
        }
        try {
            this.messageReturn = new C2050k(this.f549a).m382a(str, hashMap, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return aM_ReturnData;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject2.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
            this.queueNum = jSONObject2.getInt("QueueNum");
            aM_ReturnData.setResultMessage(jSONObject2.getString("ResultMessage"));
            if (f548d) {
                Log.i(TAG, "上传返回 = " + this.resultMessage);
            }
            if (((double) this.resultMessage) == 100.0d) {
                return aM_ReturnData;
            }
            AM_ReturnData am_section_upload;
            if (((double) this.resultMessage) == 208.0d) {
                String string = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                if (f548d) {
                    Log.i(TAG, "hs_upload返回208,拿到regionHost = " + string);
                }
                am_section_upload = am_section_upload(this.f553f, VerifyToken, hsArr, string);
                if (!"100".equals(am_section_upload.getResultMessage())) {
                    return am_section_upload;
                }
                UserCheckSDK.saveUserInfo(this.f549a, null, null, string, null, null, null, null, -1);
                if (f548d) {
                    Log.i(TAG, "保存regionHost到本地");
                }
                this.f554g = string;
                return am_section_upload;
            } else if (((double) this.resultMessage) == 212.0d) {
                if (f548d) {
                    Log.i(TAG, "212->Token过期:刷新Token");
                }
                SharedPreferences sharedPreferences = this.f549a.getSharedPreferences("jiuan.sdk.author", 0);
                r1 = new CommCloudSDK(this.f549a).token_refresh(sharedPreferences.getString("refreshToken", ""), this.f553f, this.f554g);
                if (!"100".equals(r1.getResultCode())) {
                    return aM_ReturnData;
                }
                if (f548d) {
                    Log.i(TAG, "重新调用hs_upload");
                }
                r4 = r1.getAccessToken();
                am_section_upload = am_section_upload(this.f553f, r4, hsArr, this.f554g);
                if (!"100".equals(am_section_upload.getResultMessage())) {
                    return am_section_upload;
                }
                if (f548d) {
                    Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
                }
                r5 = r1.getRefreshToken();
                Editor edit = sharedPreferences.edit();
                edit.putString("accessToken", r4);
                edit.putString("refreshToken", r5);
                edit.commit();
                UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                return am_section_upload;
            } else if (((double) this.resultMessage) != 221.0d) {
                return aM_ReturnData;
            } else {
                if (f548d) {
                    Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
                }
                AM_ReturnData aM_ReturnData2 = new AM_ReturnData();
                try {
                    r1 = new CommCloudSDK(this.f549a).UserSign(this.f555h, this.f556i, this.f553f, inputHost);
                    if (!"100".equals(r1.getResultCode())) {
                        return aM_ReturnData2;
                    }
                    aM_ReturnData2.setResultMessage(r1.getResultCode());
                    aM_ReturnData2.setRegionHost(r1.getRegionHost());
                    aM_ReturnData2.setAccessToken(r1.getAccessToken());
                    aM_ReturnData2.setRefreshToken(r1.getRefreshToken());
                    aM_ReturnData2.setExpires(r1.getExpires());
                    if (f548d) {
                        Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
                    }
                    r4 = aM_ReturnData2.getAccessToken();
                    am_section_upload = am_section_upload(this.f553f, r4, hsArr, inputHost);
                    if (!"100".equals(am_section_upload.getResultMessage())) {
                        return am_section_upload;
                    }
                    if (f548d) {
                        Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");
                    }
                    Editor edit2 = this.f549a.getSharedPreferences("jiuan.sdk.author", 0).edit();
                    r5 = aM_ReturnData2.getRefreshToken();
                    edit2.putString("accessToken", r4);
                    edit2.putString("refreshToken", r5);
                    edit2.commit();
                    UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                    return am_section_upload;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return aM_ReturnData;
                }
            }
        } catch (JSONException e3) {
            return aM_ReturnData;
        }
    }

    public AM_ReturnData am_sleep_upload(String userName, String VerifyToken, ArrayList hsArr, String inputHost) {
        AM_ReturnData aM_ReturnData = new AM_ReturnData();
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", "d63856e6867d45a2b5d4a598e49cc161");
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m398b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m397a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        try {
            if (f548d) {
                Log.i(TAG, hsArr.size() + "");
            }
            for (int i = 0; i < hsArr.size(); i++) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ChangeType", ((Data_AM_Sleep) hsArr.get(i)).getChangeType());
                jSONObject.put("LastChangeTime", ((Data_AM_Sleep) hsArr.get(i)).getLastChangeTime());
                jSONObject.put("PhoneDataID", ((Data_AM_Sleep) hsArr.get(i)).getPhoneDataID());
                jSONObject.put("PhoneCreateTime", ((Data_AM_Sleep) hsArr.get(i)).getPhoneCreateTime());
                jSONObject.put("Lat", ((Data_AM_Sleep) hsArr.get(i)).getLat());
                jSONObject.put("Lon", ((Data_AM_Sleep) hsArr.get(i)).getLon());
                jSONObject.put("TimeZone", (double) ((Data_AM_Sleep) hsArr.get(i)).getTimeZone());
                jSONObject.put(DataBaseConstants.SLEEP_SLEEPLEVEL, ((Data_AM_Sleep) hsArr.get(i)).getSleepLevel());
                jSONObject.put("TimeSectionID", ((Data_AM_Sleep) hsArr.get(i)).getTimeSectionId());
                jSONObject.put("MeasureTime", ((Data_AM_Sleep) hsArr.get(i)).getMeasureTime());
                jSONObject.put("MechineType", ((Data_AM_Sleep) hsArr.get(i)).getMechineType());
                jSONObject.put("MechineDeviceID", ((Data_AM_Sleep) hsArr.get(i)).getMechineDeviceID());
                jSONArray.put(i, jSONObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", VerifyToken);
        hashMap.put("UploadData", jSONArray.toString());
        String str = inputHost + C2041b.f507c + "amsleep_upload.htm";
        if (f548d) {
            Log.i(TAG, "数据上传 = " + hashMap.toString());
        }
        try {
            this.messageReturn = new C2050k(this.f549a).m382a(str, hashMap, "UTF-8");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (this.messageReturn.length() == 0) {
            return aM_ReturnData;
        }
        try {
            JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
            this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
            this.TS = Long.parseLong(jSONObject2.getString("TS"));
            this.resultMessage = Float.parseFloat(jSONObject2.getString("ResultMessage"));
            this.queueNum = jSONObject2.getInt("QueueNum");
            aM_ReturnData.setResultMessage(jSONObject2.getString("ResultMessage"));
            if (f548d) {
                Log.i(TAG, "上传返回 = " + this.resultMessage);
            }
            if (((double) this.resultMessage) == 100.0d) {
                return aM_ReturnData;
            }
            AM_ReturnData am_sleep_upload;
            if (((double) this.resultMessage) == 208.0d) {
                String string = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                if (f548d) {
                    Log.i(TAG, "hs_upload返回208,拿到regionHost = " + string);
                }
                am_sleep_upload = am_sleep_upload(this.f553f, VerifyToken, hsArr, string);
                if (!"100".equals(am_sleep_upload.getResultMessage())) {
                    return am_sleep_upload;
                }
                UserCheckSDK.saveUserInfo(this.f549a, null, null, string, null, null, null, null, -1);
                if (f548d) {
                    Log.i(TAG, "保存regionHost到本地");
                }
                this.f554g = string;
                return am_sleep_upload;
            } else if (((double) this.resultMessage) == 212.0d) {
                if (f548d) {
                    Log.i(TAG, "212->Token过期:刷新Token");
                }
                SharedPreferences sharedPreferences = this.f549a.getSharedPreferences("jiuan.sdk.author", 0);
                r1 = new CommCloudSDK(this.f549a).token_refresh(sharedPreferences.getString("refreshToken", ""), this.f553f, this.f554g);
                if (!"100".equals(r1.getResultCode())) {
                    return aM_ReturnData;
                }
                if (f548d) {
                    Log.i(TAG, "重新调用hs_upload");
                }
                r4 = r1.getAccessToken();
                am_sleep_upload = am_sleep_upload(this.f553f, r4, hsArr, this.f554g);
                if (!"100".equals(am_sleep_upload.getResultMessage())) {
                    return am_sleep_upload;
                }
                if (f548d) {
                    Log.i(TAG, "刷新Token后上传HS数据成功!保存最新Token到本地");
                }
                r5 = r1.getRefreshToken();
                Editor edit = sharedPreferences.edit();
                edit.putString("accessToken", r4);
                edit.putString("refreshToken", r5);
                edit.commit();
                UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                return am_sleep_upload;
            } else if (((double) this.resultMessage) != 221.0d) {
                return aM_ReturnData;
            } else {
                if (f548d) {
                    Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
                }
                AM_ReturnData aM_ReturnData2 = new AM_ReturnData();
                try {
                    r1 = new CommCloudSDK(this.f549a).UserSign(this.f555h, this.f556i, this.f553f, inputHost);
                    if (!"100".equals(r1.getResultCode())) {
                        return aM_ReturnData2;
                    }
                    aM_ReturnData2.setResultMessage(r1.getResultCode());
                    aM_ReturnData2.setRegionHost(r1.getRegionHost());
                    aM_ReturnData2.setAccessToken(r1.getAccessToken());
                    aM_ReturnData2.setRefreshToken(r1.getRefreshToken());
                    aM_ReturnData2.setExpires(r1.getExpires());
                    if (f548d) {
                        Log.i(TAG, "重新登录成功,重新调用hs_upload上传数据");
                    }
                    r4 = aM_ReturnData2.getAccessToken();
                    am_sleep_upload = am_sleep_upload(this.f553f, r4, hsArr, inputHost);
                    if (!"100".equals(am_sleep_upload.getResultMessage())) {
                        return am_sleep_upload;
                    }
                    if (f548d) {
                        Log.i(TAG, "再次上HS数据成功,保存最新Token信息到本地");
                    }
                    Editor edit2 = this.f549a.getSharedPreferences("jiuan.sdk.author", 0).edit();
                    r5 = aM_ReturnData2.getRefreshToken();
                    edit2.putString("accessToken", r4);
                    edit2.putString("refreshToken", r5);
                    edit2.commit();
                    UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                    return am_sleep_upload;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    return aM_ReturnData;
                }
            }
        } catch (JSONException e3) {
            return aM_ReturnData;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.ihealth.communication.cloud.data.AM_ReturnData am_workout_upload(java.lang.String r15, java.lang.String r16, java.util.ArrayList r17, java.lang.String r18) {
        /*
        r14 = this;
        r11 = new com.ihealth.communication.cloud.data.AM_ReturnData;
        r11.<init>();
        r4 = new java.util.HashMap;
        r4.<init>();
        r2 = "sc";
        r3 = "7c789858c0ec4ebf8189ebb14b6730a5";
        r4.put(r2, r3);
        r2 = "sv";
        r3 = "9a1932f91aba409baafa9c091728ec8d";
        r4.put(r2, r3);
        r2 = "AppVersion";
        r3 = "ASDK_2.3.2.22";
        r4.put(r2, r3);
        r2 = "AppGuid";
        r3 = r14.m398b();
        r4.put(r2, r3);
        r2 = "PhoneOS";
        r3 = android.os.Build.VERSION.RELEASE;
        r4.put(r2, r3);
        r2 = "PhoneName";
        r3 = android.os.Build.MODEL;
        r4.put(r2, r3);
        r2 = "PhoneID";
        r3 = r14.m397a();
        r4.put(r2, r3);
        r2 = "PhoneLanguage";
        r3 = java.util.Locale.getDefault();
        r3 = r3.getLanguage();
        r4.put(r2, r3);
        r2 = "PhoneRegion";
        r3 = java.util.Locale.getDefault();
        r3 = r3.getCountry();
        r4.put(r2, r3);
        r2 = "QueueNum";
        r3 = "1";
        r4.put(r2, r3);
        r2 = "Token";
        r3 = "";
        r4.put(r2, r3);
        r5 = new org.json.JSONArray;
        r5.<init>();
        r2 = "";
        r2 = "AM_CommCloudData";
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0231 }
        r3.<init>();	 Catch:{ JSONException -> 0x0231 }
        r6 = r17.size();	 Catch:{ JSONException -> 0x0231 }
        r3 = r3.append(r6);	 Catch:{ JSONException -> 0x0231 }
        r6 = "";
        r3 = r3.append(r6);	 Catch:{ JSONException -> 0x0231 }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x0231 }
        com.ihealth.communication.utils.Log.e(r2, r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = 0;
        r3 = r2;
    L_0x008c:
        r2 = r17.size();	 Catch:{ JSONException -> 0x0231 }
        if (r3 >= r2) goto L_0x0235;
    L_0x0092:
        r6 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0231 }
        r6.<init>();	 Catch:{ JSONException -> 0x0231 }
        r7 = "ChangeType";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_ChangeType();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = "LastChangeTime";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r8 = r2.getWorkout_LastChangeTime();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x0231 }
        r7 = "PhoneCreateTime";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r8 = r2.getWorkout_PhoneCreateTime();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x0231 }
        r7 = "PhoneDataID";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_PhoneDataID();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = "Lat";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r8 = r2.getWorkout_Lat();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x0231 }
        r7 = "Lon";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r8 = r2.getWorkout_Lon();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x0231 }
        r7 = "TimeZone";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_TimeZone();	 Catch:{ JSONException -> 0x0231 }
        r8 = (double) r2;	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x0231 }
        r7 = "SpendMinutes";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_SpendMinutes();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = "Steps";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_Steps();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = "Distance";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_Distance();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = "Calories";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_Calories();	 Catch:{ JSONException -> 0x0231 }
        r8 = (double) r2;	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r8);	 Catch:{ JSONException -> 0x0231 }
        r7 = "City";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_City();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0231 }
        r7.<init>();	 Catch:{ JSONException -> 0x0231 }
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_Temperature();	 Catch:{ JSONException -> 0x0231 }
        r2 = r7.append(r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = ",";
        r7 = r2.append(r7);	 Catch:{ JSONException -> 0x0231 }
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_Humidity();	 Catch:{ JSONException -> 0x0231 }
        r2 = r7.append(r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = ",";
        r7 = r2.append(r7);	 Catch:{ JSONException -> 0x0231 }
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_Atmosphere();	 Catch:{ JSONException -> 0x0231 }
        r2 = r7.append(r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = ",";
        r7 = r2.append(r7);	 Catch:{ JSONException -> 0x0231 }
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_WeatherCode();	 Catch:{ JSONException -> 0x0231 }
        r2 = r7.append(r2);	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x0231 }
        r7 = "Weather";
        r6.put(r7, r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0231 }
        r7.<init>();	 Catch:{ JSONException -> 0x0231 }
        r8 = "Note";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x022c }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x022c }
        r2 = r2.getWorkout_CommentNote();	 Catch:{ JSONException -> 0x022c }
        r7.put(r8, r2);	 Catch:{ JSONException -> 0x022c }
        r8 = "NoteTS";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x022c }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x022c }
        r12 = r2.getWorkout_CommentTS();	 Catch:{ JSONException -> 0x022c }
        r7.put(r8, r12);	 Catch:{ JSONException -> 0x022c }
        r8 = "Pic";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x022c }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x022c }
        r2 = r2.getWorkout_CommentPic();	 Catch:{ JSONException -> 0x022c }
        r7.put(r8, r2);	 Catch:{ JSONException -> 0x022c }
    L_0x01fd:
        r2 = "Comment";
        r6.put(r2, r7);	 Catch:{ JSONException -> 0x0231 }
        r7 = "MechineType";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_MechineType();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r2);	 Catch:{ JSONException -> 0x0231 }
        r7 = "MechineDeviceID";
        r0 = r17;
        r2 = r0.get(r3);	 Catch:{ JSONException -> 0x0231 }
        r2 = (com.ihealth.communication.cloud.data.Data_TB_Workout) r2;	 Catch:{ JSONException -> 0x0231 }
        r2 = r2.getWorkout_MechineDeviceID();	 Catch:{ JSONException -> 0x0231 }
        r6.put(r7, r2);	 Catch:{ JSONException -> 0x0231 }
        r5.put(r3, r6);	 Catch:{ JSONException -> 0x0231 }
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x008c;
    L_0x022c:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ JSONException -> 0x0231 }
        goto L_0x01fd;
    L_0x0231:
        r2 = move-exception;
        r2.printStackTrace();
    L_0x0235:
        r2 = "Un";
        r4.put(r2, r15);
        r2 = "VerifyToken";
        r0 = r16;
        r4.put(r2, r0);
        r2 = "UploadData";
        r3 = r5.toString();
        r4.put(r2, r3);
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r18;
        r2 = r2.append(r0);
        r3 = com.ihealth.communication.cloud.p002a.C2041b.f507c;
        r2 = r2.append(r3);
        r3 = "workout_upload.htm";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r3 = f548d;
        if (r3 == 0) goto L_0x0286;
    L_0x0269:
        r3 = "AM_CommCloudData";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "数据上传 = ";
        r5 = r5.append(r6);
        r6 = r4.toString();
        r5 = r5.append(r6);
        r5 = r5.toString();
        com.ihealth.communication.utils.Log.i(r3, r5);
    L_0x0286:
        r3 = new com.ihealth.communication.cloud.a.k;
        r5 = r14.f549a;
        r3.<init>(r5);
        r5 = "UTF-8";
        r2 = r3.m382a(r2, r4, r5);	 Catch:{ Exception -> 0x029f }
        r14.messageReturn = r2;	 Catch:{ Exception -> 0x029f }
    L_0x0295:
        r2 = r14.messageReturn;
        r2 = r2.length();
        if (r2 != 0) goto L_0x02a4;
    L_0x029d:
        r2 = r11;
    L_0x029e:
        return r2;
    L_0x029f:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0295;
    L_0x02a4:
        r2 = new org.json.JSONTokener;	 Catch:{ JSONException -> 0x04e5 }
        r3 = r14.messageReturn;	 Catch:{ JSONException -> 0x04e5 }
        r2.<init>(r3);	 Catch:{ JSONException -> 0x04e5 }
        r2 = r2.nextValue();	 Catch:{ JSONException -> 0x04e5 }
        r2 = (org.json.JSONObject) r2;	 Catch:{ JSONException -> 0x04e5 }
        r3 = "Result";
        r3 = r2.getInt(r3);	 Catch:{ JSONException -> 0x04e5 }
        r14.result = r3;	 Catch:{ JSONException -> 0x04e5 }
        r3 = "TS";
        r3 = r2.getString(r3);	 Catch:{ JSONException -> 0x04e5 }
        r4 = java.lang.Long.parseLong(r3);	 Catch:{ JSONException -> 0x04e5 }
        r14.TS = r4;	 Catch:{ JSONException -> 0x04e5 }
        r3 = "ResultMessage";
        r3 = r2.getString(r3);	 Catch:{ JSONException -> 0x04e5 }
        r3 = java.lang.Float.parseFloat(r3);	 Catch:{ JSONException -> 0x04e5 }
        r14.resultMessage = r3;	 Catch:{ JSONException -> 0x04e5 }
        r3 = "QueueNum";
        r3 = r2.getInt(r3);	 Catch:{ JSONException -> 0x04e5 }
        r14.queueNum = r3;	 Catch:{ JSONException -> 0x04e5 }
        r3 = "ResultMessage";
        r3 = r2.getString(r3);	 Catch:{ JSONException -> 0x04e5 }
        r11.setResultMessage(r3);	 Catch:{ JSONException -> 0x04e5 }
        r3 = f548d;	 Catch:{ JSONException -> 0x04e5 }
        if (r3 == 0) goto L_0x0301;
    L_0x02e6:
        r3 = "AM_CommCloudData";
        r4 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x04e5 }
        r4.<init>();	 Catch:{ JSONException -> 0x04e5 }
        r5 = "上传返回 = ";
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x04e5 }
        r5 = r14.resultMessage;	 Catch:{ JSONException -> 0x04e5 }
        r4 = r4.append(r5);	 Catch:{ JSONException -> 0x04e5 }
        r4 = r4.toString();	 Catch:{ JSONException -> 0x04e5 }
        com.ihealth.communication.utils.Log.i(r3, r4);	 Catch:{ JSONException -> 0x04e5 }
    L_0x0301:
        r3 = r14.resultMessage;	 Catch:{ JSONException -> 0x04e5 }
        r4 = (double) r3;	 Catch:{ JSONException -> 0x04e5 }
        r6 = 4636737291354636288; // 0x4059000000000000 float:0.0 double:100.0;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 != 0) goto L_0x030c;
    L_0x030a:
        r2 = r11;
        goto L_0x029e;
    L_0x030c:
        r3 = r14.resultMessage;	 Catch:{ JSONException -> 0x04e5 }
        r4 = (double) r3;	 Catch:{ JSONException -> 0x04e5 }
        r6 = 4641522365958717440; // 0x406a000000000000 float:0.0 double:208.0;
        r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r3 != 0) goto L_0x037e;
    L_0x0315:
        r3 = new org.json.JSONTokener;	 Catch:{ JSONException -> 0x04e5 }
        r4 = "ReturnValue";
        r2 = r2.getString(r4);	 Catch:{ JSONException -> 0x04e5 }
        r3.<init>(r2);	 Catch:{ JSONException -> 0x04e5 }
        r2 = r3.nextValue();	 Catch:{ JSONException -> 0x04e5 }
        r2 = (org.json.JSONObject) r2;	 Catch:{ JSONException -> 0x04e5 }
        r3 = "RegionHost";
        r5 = r2.getString(r3);	 Catch:{ JSONException -> 0x04e5 }
        r2 = f548d;	 Catch:{ JSONException -> 0x04e5 }
        if (r2 == 0) goto L_0x0348;
    L_0x0330:
        r2 = "AM_CommCloudData";
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x04e5 }
        r3.<init>();	 Catch:{ JSONException -> 0x04e5 }
        r4 = "hs_upload返回208,拿到regionHost = ";
        r3 = r3.append(r4);	 Catch:{ JSONException -> 0x04e5 }
        r3 = r3.append(r5);	 Catch:{ JSONException -> 0x04e5 }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x04e5 }
        com.ihealth.communication.utils.Log.i(r2, r3);	 Catch:{ JSONException -> 0x04e5 }
    L_0x0348:
        r2 = r14.f553f;	 Catch:{ JSONException -> 0x04e5 }
        r0 = r16;
        r1 = r17;
        r12 = r14.am_workout_upload(r2, r0, r1, r5);	 Catch:{ JSONException -> 0x04e5 }
        r2 = "100";
        r3 = r12.getResultMessage();	 Catch:{ JSONException -> 0x04e5 }
        r2 = r2.equals(r3);	 Catch:{ JSONException -> 0x04e5 }
        if (r2 == 0) goto L_0x037b;
    L_0x035e:
        r2 = r14.f549a;	 Catch:{ JSONException -> 0x04e5 }
        r3 = 0;
        r4 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = -1;
        com.ihealth.communication.cloud.UserCheckSDK.saveUserInfo(r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ JSONException -> 0x04e5 }
        r2 = f548d;	 Catch:{ JSONException -> 0x04e5 }
        if (r2 == 0) goto L_0x0376;
    L_0x036e:
        r2 = "AM_CommCloudData";
        r3 = "保存regionHost到本地";
        com.ihealth.communication.utils.Log.i(r2, r3);	 Catch:{ JSONException -> 0x04e5 }
    L_0x0376:
        r14.f554g = r5;	 Catch:{ JSONException -> 0x04e5 }
        r2 = r12;
        goto L_0x029e;
    L_0x037b:
        r2 = r12;
        goto L_0x029e;
    L_0x037e:
        r2 = r14.resultMessage;	 Catch:{ JSONException -> 0x04e5 }
        r2 = (double) r2;	 Catch:{ JSONException -> 0x04e5 }
        r4 = 4641663103447072768; // 0x406a800000000000 float:0.0 double:212.0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 != 0) goto L_0x041c;
    L_0x038a:
        r2 = f548d;	 Catch:{ JSONException -> 0x04e5 }
        if (r2 == 0) goto L_0x0395;
    L_0x038e:
        r2 = "AM_CommCloudData";
        r3 = "212->Token过期:刷新Token";
        com.ihealth.communication.utils.Log.i(r2, r3);	 Catch:{ JSONException -> 0x04e5 }
    L_0x0395:
        r2 = r14.f549a;	 Catch:{ JSONException -> 0x04e5 }
        r3 = "jiuan.sdk.author";
        r4 = 0;
        r2 = r2.getSharedPreferences(r3, r4);	 Catch:{ JSONException -> 0x04e5 }
        r3 = "refreshToken";
        r4 = "";
        r3 = r2.getString(r3, r4);	 Catch:{ JSONException -> 0x04e5 }
        r4 = new com.ihealth.communication.cloud.CommCloudSDK;	 Catch:{ JSONException -> 0x04e5 }
        r5 = r14.f549a;	 Catch:{ JSONException -> 0x04e5 }
        r4.<init>(r5);	 Catch:{ JSONException -> 0x04e5 }
        r5 = r14.f553f;	 Catch:{ JSONException -> 0x04e5 }
        r6 = r14.f554g;	 Catch:{ JSONException -> 0x04e5 }
        r3 = r4.token_refresh(r3, r5, r6);	 Catch:{ JSONException -> 0x04e5 }
        r4 = "100";
        r5 = r3.getResultCode();	 Catch:{ JSONException -> 0x04e5 }
        r4 = r4.equals(r5);	 Catch:{ JSONException -> 0x04e5 }
        if (r4 == 0) goto L_0x0419;
    L_0x03c1:
        r4 = f548d;	 Catch:{ JSONException -> 0x04e5 }
        if (r4 == 0) goto L_0x03cd;
    L_0x03c5:
        r4 = "AM_CommCloudData";
        r5 = "重新调用hs_upload";
        com.ihealth.communication.utils.Log.i(r4, r5);	 Catch:{ JSONException -> 0x04e5 }
    L_0x03cd:
        r6 = r3.getAccessToken();	 Catch:{ JSONException -> 0x04e5 }
        r4 = r14.f553f;	 Catch:{ JSONException -> 0x04e5 }
        r5 = r14.f554g;	 Catch:{ JSONException -> 0x04e5 }
        r0 = r17;
        r12 = r14.am_workout_upload(r4, r6, r0, r5);	 Catch:{ JSONException -> 0x04e5 }
        r4 = "100";
        r5 = r12.getResultMessage();	 Catch:{ JSONException -> 0x04e5 }
        r4 = r4.equals(r5);	 Catch:{ JSONException -> 0x04e5 }
        if (r4 == 0) goto L_0x0416;
    L_0x03e7:
        r4 = f548d;	 Catch:{ JSONException -> 0x04e5 }
        if (r4 == 0) goto L_0x03f3;
    L_0x03eb:
        r4 = "AM_CommCloudData";
        r5 = "刷新Token后上传HS数据成功!保存最新Token到本地";
        com.ihealth.communication.utils.Log.i(r4, r5);	 Catch:{ JSONException -> 0x04e5 }
    L_0x03f3:
        r7 = r3.getRefreshToken();	 Catch:{ JSONException -> 0x04e5 }
        r2 = r2.edit();	 Catch:{ JSONException -> 0x04e5 }
        r3 = "accessToken";
        r2.putString(r3, r6);	 Catch:{ JSONException -> 0x04e5 }
        r3 = "refreshToken";
        r2.putString(r3, r7);	 Catch:{ JSONException -> 0x04e5 }
        r2.commit();	 Catch:{ JSONException -> 0x04e5 }
        r2 = r14.f549a;	 Catch:{ JSONException -> 0x04e5 }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r8 = 0;
        r9 = 0;
        r10 = -1;
        com.ihealth.communication.cloud.UserCheckSDK.saveUserInfo(r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ JSONException -> 0x04e5 }
        r2 = r12;
        goto L_0x029e;
    L_0x0416:
        r2 = r12;
        goto L_0x029e;
    L_0x0419:
        r2 = r11;
        goto L_0x029e;
    L_0x041c:
        r2 = r14.resultMessage;	 Catch:{ JSONException -> 0x04e5 }
        r2 = (double) r2;	 Catch:{ JSONException -> 0x04e5 }
        r4 = 4641979762795872256; // 0x406ba00000000000 float:0.0 double:221.0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 != 0) goto L_0x04e2;
    L_0x0428:
        r2 = f548d;	 Catch:{ JSONException -> 0x04e5 }
        if (r2 == 0) goto L_0x0433;
    L_0x042c:
        r2 = "AM_CommCloudData";
        r3 = "221->Token验证失败->其他APP已刷新,需重新登录";
        com.ihealth.communication.utils.Log.i(r2, r3);	 Catch:{ JSONException -> 0x04e5 }
    L_0x0433:
        r2 = new com.ihealth.communication.cloud.data.AM_ReturnData;	 Catch:{ JSONException -> 0x04e5 }
        r2.<init>();	 Catch:{ JSONException -> 0x04e5 }
        r3 = new com.ihealth.communication.cloud.CommCloudSDK;	 Catch:{ Exception -> 0x04db }
        r4 = r14.f549a;	 Catch:{ Exception -> 0x04db }
        r3.<init>(r4);	 Catch:{ Exception -> 0x04db }
        r4 = r14.f555h;	 Catch:{ Exception -> 0x04db }
        r5 = r14.f556i;	 Catch:{ Exception -> 0x04db }
        r6 = r14.f553f;	 Catch:{ Exception -> 0x04db }
        r0 = r18;
        r3 = r3.UserSign(r4, r5, r6, r0);	 Catch:{ Exception -> 0x04db }
        r4 = "100";
        r5 = r3.getResultCode();	 Catch:{ Exception -> 0x04db }
        r4 = r4.equals(r5);	 Catch:{ Exception -> 0x04db }
        if (r4 == 0) goto L_0x029e;
    L_0x0457:
        r4 = r3.getResultCode();	 Catch:{ Exception -> 0x04db }
        r2.setResultMessage(r4);	 Catch:{ Exception -> 0x04db }
        r4 = r3.getRegionHost();	 Catch:{ Exception -> 0x04db }
        r2.setRegionHost(r4);	 Catch:{ Exception -> 0x04db }
        r4 = r3.getAccessToken();	 Catch:{ Exception -> 0x04db }
        r2.setAccessToken(r4);	 Catch:{ Exception -> 0x04db }
        r4 = r3.getRefreshToken();	 Catch:{ Exception -> 0x04db }
        r2.setRefreshToken(r4);	 Catch:{ Exception -> 0x04db }
        r4 = r3.getExpires();	 Catch:{ Exception -> 0x04db }
        r2.setExpires(r4);	 Catch:{ Exception -> 0x04db }
        r3 = f548d;	 Catch:{ Exception -> 0x04db }
        if (r3 == 0) goto L_0x0486;
    L_0x047e:
        r3 = "AM_CommCloudData";
        r4 = "重新登录成功,重新调用hs_upload上传数据";
        com.ihealth.communication.utils.Log.i(r3, r4);	 Catch:{ Exception -> 0x04db }
    L_0x0486:
        r6 = r2.getAccessToken();	 Catch:{ Exception -> 0x04db }
        r3 = r14.f553f;	 Catch:{ Exception -> 0x04db }
        r0 = r17;
        r1 = r18;
        r12 = r14.am_workout_upload(r3, r6, r0, r1);	 Catch:{ Exception -> 0x04db }
        r3 = "100";
        r4 = r12.getResultMessage();	 Catch:{ Exception -> 0x04db }
        r3 = r3.equals(r4);	 Catch:{ Exception -> 0x04db }
        if (r3 == 0) goto L_0x04d8;
    L_0x04a0:
        r3 = f548d;	 Catch:{ Exception -> 0x04db }
        if (r3 == 0) goto L_0x04ac;
    L_0x04a4:
        r3 = "AM_CommCloudData";
        r4 = "再次上HS数据成功,保存最新Token信息到本地";
        com.ihealth.communication.utils.Log.i(r3, r4);	 Catch:{ Exception -> 0x04db }
    L_0x04ac:
        r3 = r14.f549a;	 Catch:{ Exception -> 0x04db }
        r4 = "jiuan.sdk.author";
        r5 = 0;
        r3 = r3.getSharedPreferences(r4, r5);	 Catch:{ Exception -> 0x04db }
        r3 = r3.edit();	 Catch:{ Exception -> 0x04db }
        r7 = r2.getRefreshToken();	 Catch:{ Exception -> 0x04db }
        r2 = "accessToken";
        r3.putString(r2, r6);	 Catch:{ Exception -> 0x04db }
        r2 = "refreshToken";
        r3.putString(r2, r7);	 Catch:{ Exception -> 0x04db }
        r3.commit();	 Catch:{ Exception -> 0x04db }
        r2 = r14.f549a;	 Catch:{ Exception -> 0x04db }
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r8 = 0;
        r9 = 0;
        r10 = -1;
        com.ihealth.communication.cloud.UserCheckSDK.saveUserInfo(r2, r3, r4, r5, r6, r7, r8, r9, r10);	 Catch:{ Exception -> 0x04db }
        r2 = r12;
        goto L_0x029e;
    L_0x04d8:
        r2 = r12;
        goto L_0x029e;
    L_0x04db:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ JSONException -> 0x04e5 }
        r2 = r11;
        goto L_0x029e;
    L_0x04e2:
        r2 = r11;
        goto L_0x029e;
    L_0x04e5:
        r2 = move-exception;
        r2 = r11;
        goto L_0x029e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ihealth.communication.cloud.data.AM_CommCloud.am_workout_upload(java.lang.String, java.lang.String, java.util.ArrayList, java.lang.String):com.ihealth.communication.cloud.data.AM_ReturnData");
    }

    public int ambinding(String userName, String mac, String token, String host) {
        this.f550b = new ambindingReturn(this);
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", SV_ambinding);
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m398b());
        hashMap.put("PhoneOS", AbstractSpiCall.ANDROID_CLIENT_TYPE + VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m397a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(iHealthDevicesManager.IHEALTH_DEVICE_MAC, mac);
        jSONArray.put(jSONObject);
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", token);
        hashMap.put("UploadData", jSONArray.toString());
        try {
            this.messageReturn = new C2050k(this.f549a).m382a(host + C2041b.f507c + "ambinding.htm", hashMap, "UTF-8");
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
                if (this.resultMessage == 100.0f) {
                    jSONObject2 = (JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue();
                    this.f550b.f545a = jSONObject2.getInt("Status");
                    this.f550b.f546b = jSONObject2.getString("iHealthID");
                    if (this.f550b.f545a == 1) {
                        Log.e(TAG, "return true");
                        return 100;
                    }
                    Log.e(TAG, "return false");
                    return 999;
                }
                Log.e(TAG, "return false");
                return (int) this.resultMessage;
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "return false");
                return 999;
            }
        } catch (UnknownHostException e2) {
            return 101;
        } catch (SocketTimeoutException e3) {
            return 102;
        }
    }

    public int amsearch(String userName, String token, String host) {
        this.amsearch_return = new amSearchReturn(this);
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", SV_amsearch);
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m398b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m397a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", token);
        try {
            this.messageReturn = new C2050k(this.f549a).m382a(host + C2041b.f507c + "amsearch.htm", hashMap, "UTF-8");
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
                if (this.resultMessage == 100.0f) {
                    jSONObject = jSONObject.getJSONObject("ReturnValue");
                    JSONArray jSONArray = jSONObject.getJSONArray("Data");
                    this.amsearch_return.iHealthID = jSONObject.getString("iHealthID");
                    int length = jSONArray.length();
                    if (length == 0) {
                        return 999;
                    }
                    this.amsearch_return.mac = new String[length];
                    for (int i = 0; i < length; i++) {
                        jSONObject = jSONArray.optJSONObject(i);
                        if (jSONObject == null) {
                            Log.i("返回AMsearchArr 条目 ", "jsonAmscItemOb == null");
                        } else {
                            String string = jSONObject.getString(iHealthDevicesManager.IHEALTH_DEVICE_MAC);
                            if (string.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                                string = "";
                            }
                            this.amsearch_return.mac[i] = string;
                            Log.v(TAG, "mac====" + this.amsearch_return.mac[i]);
                        }
                    }
                    if (this.amsearch_return.mac[0] != null) {
                        Log.e(TAG, "return true");
                        return 100;
                    }
                    Log.e(TAG, "return false");
                    return 999;
                }
                Log.e(TAG, "return false");
                return (int) this.resultMessage;
            } catch (JSONException e) {
                return 999;
            }
        } catch (UnknownHostException e2) {
            return 101;
        } catch (SocketTimeoutException e3) {
            return 102;
        }
    }

    public int amunbinding(String userName, String mac, String token, String host) {
        this.f551c = new ambindingReturn(this);
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", SV_amunbinding);
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m398b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m397a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(iHealthDevicesManager.IHEALTH_DEVICE_MAC, mac);
        jSONArray.put(jSONObject);
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", token);
        hashMap.put("UploadData", jSONArray.toString());
        try {
            this.messageReturn = new C2050k(this.f549a).m382a(host + C2041b.f507c + "amunbinding.htm", hashMap, "UTF-8");
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
                if (this.resultMessage == 100.0f) {
                    jSONObject2 = (JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue();
                    this.f551c.f545a = jSONObject2.getInt("Status");
                    this.f551c.f546b = jSONObject2.getString("iHealthID");
                    if (this.f551c.f545a == 1) {
                        Log.e(TAG, "return true");
                        return 100;
                    }
                    Log.e(TAG, "return false");
                    return 999;
                }
                Log.e(TAG, "return false");
                return (int) this.resultMessage;
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "return false");
                return 999;
            }
        } catch (UnknownHostException e2) {
            return 101;
        } catch (SocketTimeoutException e3) {
            return 102;
        }
    }

    public amSearchReturn getAmsearch_return() {
        return this.amsearch_return;
    }

    public int swimActivity_upload(String userName, String verifyToken, ArrayList swimArr, String inputHost) {
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", Sv_swimActivity_upload);
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m398b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m397a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        Log.e("上传数据", "数组长度 = " + swimArr.size());
        int i = 0;
        while (i < swimArr.size()) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ChangeType", ((Data_TB_Swim) swimArr.get(i)).getSwim_ChangeType());
                jSONObject.put("swimEndTimeStamp", ((Data_TB_Swim) swimArr.get(i)).getSwim_endtime());
                jSONObject.put("swimCostTime", ((Data_TB_Swim) swimArr.get(i)).getSwim_SpendMinutes());
                jSONObject.put("swimRoundTimes", ((Data_TB_Swim) swimArr.get(i)).getSwim_Cycles());
                jSONObject.put("swimPoolLength", ((Data_TB_Swim) swimArr.get(i)).getSwim_Distance());
                jSONObject.put("swimStyle", ((Data_TB_Swim) swimArr.get(i)).getSwim_Stroke());
                jSONObject.put("swimThrashTimes", ((Data_TB_Swim) swimArr.get(i)).getSwim_PullTimes());
                jSONObject.put("swimCalories", (double) ((Data_TB_Swim) swimArr.get(i)).getSwim_Calories());
                jSONObject.put(CooeySQLHelper.COLUMN_CITY, ((Data_TB_Swim) swimArr.get(i)).getSwim_City());
                jSONObject.put("PhoneDataID", ((Data_TB_Swim) swimArr.get(i)).getSwim_PhoneDataID());
                jSONObject.put("MechineDeviceID", ((Data_TB_Swim) swimArr.get(i)).getSwim_MechineDeviceID());
                jSONObject.put("MechineType", ((Data_TB_Swim) swimArr.get(i)).getSwim_MechineType());
                jSONObject.put("Lat", ((Data_TB_Swim) swimArr.get(i)).getSwim_Lat());
                jSONObject.put("Lon", ((Data_TB_Swim) swimArr.get(i)).getSwim_Lon());
                jSONObject.put("LastChangeTime", ((Data_TB_Swim) swimArr.get(i)).getSwim_LastChangeTime());
                jSONObject.put("Note", ((Data_TB_Swim) swimArr.get(i)).getSwim_CommentNote());
                jSONObject.put("noteTS", ((Data_TB_Swim) swimArr.get(i)).getSwim_CommentTS());
                jSONObject.put("swimStartTimeStamp", ((Data_TB_Swim) swimArr.get(i)).getSwim_StartTimeStamp());
                jSONObject.put("TimeZone", (double) ((Data_TB_Swim) swimArr.get(i)).getSwim_TimeZone());
                jSONObject.put("swimCutInTimeDiff", ((Data_TB_Swim) swimArr.get(i)).getSwim_CutInTimeDif());
                jSONObject.put("swimCutOutTimeDiff", ((Data_TB_Swim) swimArr.get(i)).getSwim_CutOutTimeDif());
                jSONObject.put("swimProcessFlag", ((Data_TB_Swim) swimArr.get(i)).getSwim_ProcessFlag());
                jSONObject.put("PhoneCreateTime", ((Data_TB_Swim) swimArr.get(i)).getSwim_PhoneCreateTime());
                jSONArray.put(jSONObject);
                i++;
            } catch (Exception e) {
                Log.e(TAG, "解析上传数据错误！" + e.toString());
            }
        }
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", verifyToken);
        hashMap.put("UploadData", jSONArray.toString());
        if (f548d) {
            Log.e(TAG, "上传参数 = " + hashMap.toString());
        }
        try {
            this.messageReturn = new C2050k(this.f549a).m382a(inputHost + C2041b.f507c + "swimActivity_upload.htm", hashMap, "UTF-8");
            if (this.messageReturn.length() == 0) {
                return 999;
            }
            if (this.messageReturn.length() == 3) {
                return Integer.valueOf(this.messageReturn).intValue();
            }
            try {
                JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
                this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
                this.resultMessage = (float) Integer.parseInt(jSONObject2.getString("ResultMessage"));
                this.queueNum = jSONObject2.getInt("QueueNum");
                String string = jSONObject2.getString("ReturnValue");
                if (this.resultMessage == 100.0f) {
                    Log.i(TAG, "上传成功，返回：" + string);
                    return 100;
                } else if (this.resultMessage == 208.0f) {
                    String string2 = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                    UserCheckSDK.saveUserInfo(this.f549a, null, null, string2, null, null, null, null, -1);
                    return swimActivity_upload(userName, verifyToken, swimArr, string2);
                } else if (this.resultMessage == 212.0f) {
                    if (f548d) {
                        Log.i(TAG, "212->Token过期:刷新Token");
                    }
                    SharedPreferences sharedPreferences = this.f549a.getSharedPreferences("jiuan.sdk.author", 0);
                    r1 = new CommCloudSDK(this.f549a).token_refresh(sharedPreferences.getString("refreshToken", ""), this.f553f, this.f554g);
                    if (!"100".equals(r1.getResultCode())) {
                        return 999;
                    }
                    r4 = r1.getAccessToken();
                    r5 = r1.getRefreshToken();
                    Editor edit = sharedPreferences.edit();
                    edit.putString("accessToken", r4);
                    edit.putString("refreshToken", r5);
                    edit.commit();
                    UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                    return swimActivity_upload(userName, r4, swimArr, inputHost);
                } else if (((double) this.resultMessage) == 221.0d) {
                    if (f548d) {
                        Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
                    }
                    AM_ReturnData aM_ReturnData = new AM_ReturnData();
                    try {
                        r1 = new CommCloudSDK(this.f549a).UserSign(this.f555h, this.f556i, this.f553f, inputHost);
                        if (!"100".equals(r1.getResultCode())) {
                            return 999;
                        }
                        aM_ReturnData.setResultMessage(r1.getResultCode());
                        aM_ReturnData.setRegionHost(r1.getRegionHost());
                        aM_ReturnData.setAccessToken(r1.getAccessToken());
                        aM_ReturnData.setRefreshToken(r1.getRefreshToken());
                        aM_ReturnData.setExpires(r1.getExpires());
                        r4 = aM_ReturnData.getAccessToken();
                        Editor edit2 = this.f549a.getSharedPreferences("jiuan.sdk.author", 0).edit();
                        r5 = aM_ReturnData.getRefreshToken();
                        edit2.putString("accessToken", r4);
                        edit2.putString("refreshToken", r5);
                        edit2.commit();
                        UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                        return swimActivity_upload(userName, r4, swimArr, inputHost);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return 999;
                    }
                } else {
                    Log.i(TAG, "上传失败，返回：" + string);
                    return 999;
                }
            } catch (Exception e22) {
                Log.e(TAG, "解析返回数据失败！" + e22.toString());
            }
        } catch (UnknownHostException e3) {
            return 101;
        } catch (SocketTimeoutException e4) {
            return 102;
        }
    }

    public int swimReport_upload(String userName, String verifyToken, ArrayList swimArr, String inputHost) {
        Map hashMap = new HashMap();
        hashMap.put("sc", "7c789858c0ec4ebf8189ebb14b6730a5");
        hashMap.put("sv", Sv_swimReport_upload);
        hashMap.put("AppVersion", "ASDK_2.3.2.22");
        hashMap.put("AppGuid", m398b());
        hashMap.put("PhoneOS", VERSION.RELEASE);
        hashMap.put("PhoneName", Build.MODEL);
        hashMap.put("PhoneID", m397a());
        hashMap.put("PhoneLanguage", Locale.getDefault().getLanguage());
        hashMap.put("PhoneRegion", Locale.getDefault().getCountry());
        hashMap.put("QueueNum", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("Token", "");
        JSONArray jSONArray = new JSONArray();
        Log.e("上传数据", "数组长度 = " + swimArr.size());
        int i = 0;
        while (i < swimArr.size()) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ChangeType", 1);
                jSONObject.put("swimCostTime", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_SwimCoastTime());
                jSONObject.put("swimEndTime", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_Endtime());
                jSONObject.put("PhoneDataID", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_DataID());
                jSONObject.put("swimPoolLength", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_PoolLength());
                jSONObject.put("swimSpendTimeBackStroke", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_SpendTimeBackStroke());
                jSONObject.put("swimSpendTimeBreastStroke", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_SpendTimeBreastStroke());
                jSONObject.put("swimSpendTimeFreeStroke", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_SpendTimeFreeStroke());
                jSONObject.put("swimSpendTimeUnrecognized", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_SpendTimeUnrecognized());
                jSONObject.put(CooeySQLHelper.COLUMN_CITY, ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_City());
                jSONObject.put("MechineDeviceID", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_DeviceID());
                jSONObject.put("MechineType", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_DeviceSource());
                jSONObject.put("Lat", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_Lat());
                jSONObject.put("Lon", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_Lon());
                jSONObject.put("LastChangeTime", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_LastChangeTime());
                jSONObject.put("Note", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_Note());
                jSONObject.put("noteTS", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_NoteTS());
                jSONObject.put("swimStartTime", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_StartTime());
                jSONObject.put("TimeZone", (double) ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_TimeZone());
                jSONObject.put("swimSumCalories", (double) ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_SumCalories());
                jSONObject.put("swimSumThrashTimes", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_SumThrashTimes());
                jSONObject.put("swimSumTripCount", ((Data_TB_SwimSection) swimArr.get(i)).getSwimSection_SumTripCount());
                jSONArray.put(jSONObject);
                i++;
            } catch (Exception e) {
                Log.e(TAG, "解析上传数据错误！" + e.toString());
            }
        }
        hashMap.put("Un", userName);
        hashMap.put("VerifyToken", verifyToken);
        hashMap.put("UploadData", jSONArray.toString());
        Log.e(TAG, "上传参数 = " + hashMap.toString());
        try {
            this.messageReturn = new C2050k(this.f549a).m382a(C2041b.f508d + "swimReport_upload.htm", hashMap, "UTF-8");
            if (this.messageReturn.length() == 0) {
                return 999;
            }
            if (this.messageReturn.length() == 3) {
                return Integer.valueOf(this.messageReturn).intValue();
            }
            try {
                JSONObject jSONObject2 = (JSONObject) new JSONTokener(this.messageReturn).nextValue();
                this.result = jSONObject2.getInt(DataBaseConstants.PO_RESULT);
                this.resultMessage = (float) Integer.parseInt(jSONObject2.getString("ResultMessage"));
                this.queueNum = jSONObject2.getInt("QueueNum");
                String string = jSONObject2.getString("ReturnValue");
                if (this.resultMessage == 100.0f) {
                    Log.i(TAG, "上传成功，返回：" + string);
                    return 100;
                } else if (this.resultMessage == 208.0f) {
                    String string2 = ((JSONObject) new JSONTokener(jSONObject2.getString("ReturnValue")).nextValue()).getString("RegionHost");
                    UserCheckSDK.saveUserInfo(this.f549a, null, null, string2, null, null, null, null, -1);
                    return swimReport_upload(userName, verifyToken, swimArr, string2);
                } else if (this.resultMessage == 212.0f) {
                    if (f548d) {
                        Log.i(TAG, "212->Token过期:刷新Token");
                    }
                    SharedPreferences sharedPreferences = this.f549a.getSharedPreferences("jiuan.sdk.author", 0);
                    r1 = new CommCloudSDK(this.f549a).token_refresh(sharedPreferences.getString("refreshToken", ""), this.f553f, this.f554g);
                    if (!"100".equals(r1.getResultCode())) {
                        return 999;
                    }
                    r4 = r1.getAccessToken();
                    r5 = r1.getRefreshToken();
                    Editor edit = sharedPreferences.edit();
                    edit.putString("accessToken", r4);
                    edit.putString("refreshToken", r5);
                    edit.commit();
                    UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                    return swimReport_upload(userName, r4, swimArr, inputHost);
                } else if (((double) this.resultMessage) == 221.0d) {
                    if (f548d) {
                        Log.i(TAG, "221->Token验证失败->其他APP已刷新,需重新登录");
                    }
                    AM_ReturnData aM_ReturnData = new AM_ReturnData();
                    try {
                        r1 = new CommCloudSDK(this.f549a).UserSign(this.f555h, this.f556i, this.f553f, inputHost);
                        if (!"100".equals(r1.getResultCode())) {
                            return 999;
                        }
                        aM_ReturnData.setResultMessage(r1.getResultCode());
                        aM_ReturnData.setRegionHost(r1.getRegionHost());
                        aM_ReturnData.setAccessToken(r1.getAccessToken());
                        aM_ReturnData.setRefreshToken(r1.getRefreshToken());
                        aM_ReturnData.setExpires(r1.getExpires());
                        r4 = aM_ReturnData.getAccessToken();
                        Editor edit2 = this.f549a.getSharedPreferences("jiuan.sdk.author", 0).edit();
                        r5 = aM_ReturnData.getRefreshToken();
                        edit2.putString("accessToken", r4);
                        edit2.putString("refreshToken", r5);
                        edit2.commit();
                        UserCheckSDK.saveUserInfo(this.f549a, null, null, null, r4, r5, null, null, -1);
                        return swimReport_upload(userName, r4, swimArr, inputHost);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        return 999;
                    }
                } else {
                    Log.i(TAG, "上传失败，返回：" + string);
                    return 999;
                }
            } catch (Exception e22) {
                Log.e(TAG, "解析返回数据失败！" + e22.toString());
            }
        } catch (UnknownHostException e3) {
            return 101;
        } catch (SocketTimeoutException e4) {
            return 102;
        }
    }
}

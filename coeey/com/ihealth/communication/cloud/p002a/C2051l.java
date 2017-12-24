package com.ihealth.communication.cloud.p002a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.ihealth.communication.cloud.data.AM_InAuthor;
import com.ihealth.communication.cloud.data.ActivityData;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.DataBaseTools;
import com.ihealth.communication.cloud.data.Make_Data_Util;
import com.ihealth.communication.cloud.data.SleepData;
import com.ihealth.communication.control.AmProfile;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;
import humanize.util.Constants;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C2051l {
    public static float m383a() {
        float dSTSavings = ((float) TimeZone.getDefault().getDSTSavings()) / 3600000.0f;
        boolean inDaylightTime = TimeZone.getDefault().inDaylightTime(new Date());
        float rawOffset = ((float) TimeZone.getDefault().getRawOffset()) / 3600000.0f;
        if (!inDaylightTime) {
            dSTSavings = 0.0f;
        }
        return dSTSavings + rawOffset;
    }

    public static long m384a(String str) {
        long j = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        Date date = new Date();
        try {
            return simpleDateFormat.parse(str + " 00:00:00").getTime() / 1000;
        } catch (Exception e) {
            Log.e("aa", "getDefaultTimerStr Exception ");
            e.printStackTrace();
            return j;
        }
    }

    private static String m385a(Context context, String str) {
        return context.getSharedPreferences("LastActivityData", 0).getString(str, "");
    }

    public static String m386a(Long l) {
        String str = "1987-01-01 00:00:00";
        Date date = new Date(l.longValue() * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        try {
            str = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String m387a(String str, long j, int i) {
        String str2 = "";
        str2 = str + j + i + "00000000";
        if (str == null) {
            str2 = iHealthDevicesManager.TYPE_BG5 + j + i + "00000000";
        }
        return ByteBufferUtil.Bytes2HexString(MD5.md5(str2));
    }

    private static void m388a(Context context, String str, String str2) {
        Editor edit = context.getSharedPreferences("LastActivityData", 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public static void m389a(Context context, String str, String str2, String str3, JSONObject jSONObject) {
        JSONObject jSONObject2;
        JSONArray jSONArray = null;
        JSONArray jSONArray2 = null;
        String str4 = "";
        String str5 = "";
        String str6 = "";
        String str7 = "";
        String str8 = "";
        String str9 = "";
        if (jSONObject != null) {
            JSONArray jSONArray3;
            try {
                jSONArray3 = jSONObject.getJSONArray(AmProfile.SYNC_ACTIVITY_DATA_AM);
            } catch (JSONException e) {
                e.printStackTrace();
                jSONArray3 = jSONArray;
            }
            int i = 0;
            while (i < jSONArray3.length()) {
                JSONArray jSONArray4;
                String str10;
                String str11;
                String str12;
                String str13;
                try {
                    jSONArray4 = ((JSONObject) jSONArray3.get(i)).getJSONArray(AmProfile.SYNC_ACTIVITY_EACH_DATA_AM);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    jSONArray4 = jSONArray2;
                }
                if (jSONArray4 == null || jSONArray4.length() <= 0) {
                    Log.i("Method", "第" + i + "段运动数据为空");
                    str10 = str9;
                    str11 = str8;
                    str12 = str7;
                    str13 = str6;
                    str9 = str5;
                    str8 = str4;
                } else {
                    String str14;
                    String str15;
                    String str16;
                    int parseInt;
                    int parseInt2;
                    int i2;
                    int i3;
                    JSONObject jSONObject3;
                    ActivityData activityData;
                    int i4;
                    int calorie;
                    int i5;
                    int steps;
                    str13 = C2051l.m385a(context, str2);
                    if (str13 == null || str13.replace("#", "").replace("-", "").equals("")) {
                        str14 = str6;
                        str15 = str5;
                        str16 = str4;
                    } else {
                        str4 = str13.split("#")[0];
                        str5 = str13.split("#")[1].split("-")[0];
                        str14 = str13.split("#")[1].split("-")[1];
                        str15 = str5;
                        str16 = str4;
                    }
                    try {
                        str13 = ((JSONObject) jSONArray4.get(0)).getString("time").split(Constants.SPACE)[0];
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                        str13 = null;
                    }
                    if (str13 != null) {
                        if (!str16.equals("")) {
                            if (str13.trim().equals(str16.split(Constants.SPACE)[0])) {
                                parseInt = Integer.parseInt(str15);
                                parseInt2 = Integer.parseInt(str14);
                                i2 = parseInt2;
                                parseInt2 = 0;
                                jSONObject2 = null;
                                i3 = parseInt;
                                while (parseInt2 < jSONArray4.length()) {
                                    try {
                                        jSONObject3 = (JSONObject) jSONArray4.get(parseInt2);
                                    } catch (JSONException e222) {
                                        e222.printStackTrace();
                                        jSONObject3 = jSONObject2;
                                    }
                                    activityData = new ActivityData();
                                    try {
                                        activityData.setTime(jSONObject3.getString("time"));
                                        activityData.setSteps(Integer.parseInt(jSONObject3.getString("step")));
                                        activityData.setCalorie(Integer.parseInt(jSONObject3.getString("calorie")));
                                    } catch (JSONException e2222) {
                                        e2222.printStackTrace();
                                    }
                                    if (i2 != -1 || activityData.getCalorie() - i2 < 0) {
                                        i4 = 0;
                                        calorie = activityData.getCalorie();
                                    } else {
                                        i4 = activityData.getCalorie() - i2;
                                        calorie = activityData.getCalorie();
                                    }
                                    if (i3 != -1 || activityData.getSteps() - i3 < 0) {
                                        i5 = 0;
                                        steps = activityData.getSteps();
                                    } else {
                                        i5 = activityData.getSteps() - i3;
                                        steps = activityData.getSteps();
                                    }
                                    new DataBaseTools(context).addData(DataBaseConstants.TABLE_TB_AM_ACTIVITY, Make_Data_Util.makeDataSingleAMA(str, str2, str3, activityData, i4, i5, 0));
                                    parseInt2++;
                                    jSONObject2 = jSONObject3;
                                    i3 = steps;
                                    i2 = calorie;
                                }
                                str7 = ((JSONObject) jSONArray4.get(jSONArray4.length() - 1)).getString("time");
                                str8 = ((JSONObject) jSONArray4.get(jSONArray4.length() - 1)).getString("step");
                                str10 = ((JSONObject) jSONArray4.get(jSONArray4.length() - 1)).getString("calorie");
                                str11 = str8;
                                str12 = str7;
                                str13 = str12 + "#" + str11 + "-" + str10;
                                C2051l.m388a(context, str2, str13);
                                if (!str10.equals("")) {
                                    new DataBaseTools(context).addData(DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT, Make_Data_Util.makeDataSingleAMADR(str, str2, str3, Integer.parseInt(str10), Integer.parseInt(str11), 0, PublicMethod.String2TS(str13)));
                                }
                                str13 = str14;
                                str9 = str15;
                                str8 = str16;
                            }
                        }
                    }
                    parseInt = -1;
                    parseInt2 = -1;
                    i2 = parseInt2;
                    parseInt2 = 0;
                    jSONObject2 = null;
                    i3 = parseInt;
                    while (parseInt2 < jSONArray4.length()) {
                        jSONObject3 = (JSONObject) jSONArray4.get(parseInt2);
                        activityData = new ActivityData();
                        activityData.setTime(jSONObject3.getString("time"));
                        activityData.setSteps(Integer.parseInt(jSONObject3.getString("step")));
                        activityData.setCalorie(Integer.parseInt(jSONObject3.getString("calorie")));
                        if (i2 != -1) {
                        }
                        i4 = 0;
                        calorie = activityData.getCalorie();
                        if (i3 != -1) {
                        }
                        i5 = 0;
                        steps = activityData.getSteps();
                        new DataBaseTools(context).addData(DataBaseConstants.TABLE_TB_AM_ACTIVITY, Make_Data_Util.makeDataSingleAMA(str, str2, str3, activityData, i4, i5, 0));
                        parseInt2++;
                        jSONObject2 = jSONObject3;
                        i3 = steps;
                        i2 = calorie;
                    }
                    try {
                        str7 = ((JSONObject) jSONArray4.get(jSONArray4.length() - 1)).getString("time");
                        str8 = ((JSONObject) jSONArray4.get(jSONArray4.length() - 1)).getString("step");
                        str10 = ((JSONObject) jSONArray4.get(jSONArray4.length() - 1)).getString("calorie");
                        str11 = str8;
                        str12 = str7;
                    } catch (JSONException e22222) {
                        JSONException jSONException = e22222;
                        str13 = str8;
                        str8 = str7;
                        jSONException.printStackTrace();
                        str10 = str9;
                        str11 = str13;
                        str12 = str8;
                    }
                    str13 = str12 + "#" + str11 + "-" + str10;
                    C2051l.m388a(context, str2, str13);
                    if (str10.equals("")) {
                        new DataBaseTools(context).addData(DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT, Make_Data_Util.makeDataSingleAMADR(str, str2, str3, Integer.parseInt(str10), Integer.parseInt(str11), 0, PublicMethod.String2TS(str13)));
                    }
                    str13 = str14;
                    str9 = str15;
                    str8 = str16;
                }
                i++;
                str6 = str13;
                str5 = str9;
                str4 = str8;
                str9 = str10;
                str8 = str11;
                str7 = str12;
                jSONArray2 = jSONArray4;
            }
            AM_InAuthor aM_InAuthor = new AM_InAuthor();
            aM_InAuthor.initAuthor(context, str);
            aM_InAuthor.run();
        }
    }

    public static long m390b() {
        return System.currentTimeMillis() / 1000;
    }

    public static long m391b(String str) {
        long j = -1;
        try {
            j = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(str).getTime();
            return j / 1000;
        } catch (ParseException e) {
            ParseException parseException = e;
            long j2 = j;
            parseException.printStackTrace();
            return j2;
        }
    }

    public static void m392b(Context context, String str, String str2, String str3, JSONObject jSONObject) {
        JSONArray jSONArray = null;
        JSONArray jSONArray2 = null;
        ArrayList arrayList = new ArrayList();
        arrayList = new ArrayList();
        if (jSONObject != null) {
            JSONArray jSONArray3;
            try {
                jSONArray3 = jSONObject.getJSONArray("sleep");
            } catch (JSONException e) {
                e.printStackTrace();
                jSONArray3 = jSONArray;
            }
            int i = 0;
            while (i < jSONArray3.length()) {
                JSONArray jSONArray4;
                String str4 = "";
                String str5 = "";
                int i2 = 0;
                int i3 = 0;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                String str6 = "";
                try {
                    jSONArray4 = ((JSONObject) jSONArray3.get(i)).getJSONArray(AmProfile.SYNC_SLEEP_EACH_DATA_AM);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    jSONArray4 = jSONArray2;
                }
                if (jSONArray4 == null || jSONArray4.length() <= 0) {
                    Log.i("Method", "第" + i + "段睡眠数据为空");
                } else {
                    try {
                        str6 = ((JSONObject) jSONArray4.get(0)).getString("time").split(Constants.SPACE)[0];
                        str6 = ((JSONObject) jSONArray4.get(0)).getString("time").split(Constants.SPACE)[1];
                        str4 = ((JSONObject) jSONArray4.get(0)).getString("time");
                        str5 = ((JSONObject) jSONArray4.get(jSONArray4.length() - 1)).getString("time");
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                    }
                    String str7 = str2 + PublicMethod.String2TS(str4) + PublicMethod.String2TS(str5);
                    Object obj = 1;
                    int i7 = 0;
                    int i8 = 0;
                    JSONObject jSONObject2 = null;
                    while (i7 < jSONArray4.length()) {
                        JSONObject jSONObject3;
                        Object obj2;
                        try {
                            jSONObject3 = (JSONObject) jSONArray4.get(i7);
                        } catch (JSONException e3) {
                            jSONObject3 = jSONObject2;
                        }
                        SleepData sleepData = new SleepData();
                        try {
                            sleepData.setTime(jSONObject3.getString("time"));
                            sleepData.setGrade(Integer.parseInt(jSONObject3.getString("level")));
                        } catch (JSONException e222) {
                            e222.printStackTrace();
                        }
                        if (obj == null) {
                            obj2 = obj;
                        } else if (sleepData.getGrade() == 0) {
                            i2++;
                            obj2 = obj;
                        } else {
                            obj2 = null;
                        }
                        if (sleepData.getGrade() == 1) {
                            i3++;
                        }
                        if (sleepData.getGrade() == 2) {
                            i4++;
                        }
                        if (sleepData.getGrade() == 0 && r5 != 0) {
                            i5++;
                        }
                        int grade = sleepData.getGrade();
                        if (obj2 == null && sleepData.getGrade() == 0) {
                            i6++;
                        }
                        new DataBaseTools(context).addData(DataBaseConstants.TABLE_TB_AM_SLEEP, Make_Data_Util.makeDataSingleAMS(str, str2, str3, sleepData.getGrade(), str7, sleepData.getTime()));
                        i7++;
                        i8 = grade;
                        obj = obj2;
                        jSONObject2 = jSONObject3;
                    }
                    new DataBaseTools(context).addData(DataBaseConstants.TABLE_TB_AM_SLEEPREPORT, Make_Data_Util.makeDataSingleAMSSR(str, str2, str3, i6, i4, i2, i3, i5, str4, str5, str7));
                }
                i++;
                jSONArray2 = jSONArray4;
            }
            AM_InAuthor aM_InAuthor = new AM_InAuthor();
            aM_InAuthor.initAuthor(context, str);
            aM_InAuthor.run();
        }
    }

    public static long m393c(String str) {
        long j = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        Date date = new Date();
        try {
            return simpleDateFormat.parse(str).getTime() / 1000;
        } catch (Exception e) {
            e.printStackTrace();
            return j;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void m394c(android.content.Context r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, org.json.JSONObject r27) {
        /*
        r2 = 0;
        if (r27 == 0) goto L_0x0293;
    L_0x0003:
        r3 = "stage_data";
        r0 = r27;
        r2 = r0.getJSONArray(r3);	 Catch:{ JSONException -> 0x005a }
        r20 = r2;
    L_0x000d:
        r2 = new com.ihealth.communication.cloud.data.Data_TB_SwimSection;
        r2.<init>();
        r10 = 0;
        r3 = 0;
        r2 = 0;
        r22 = r2;
    L_0x0017:
        r2 = r20.length();
        r0 = r22;
        if (r0 >= r2) goto L_0x0284;
    L_0x001f:
        r0 = r20;
        r1 = r22;
        r2 = r0.get(r1);	 Catch:{ JSONException -> 0x0061 }
        r2 = (org.json.JSONObject) r2;	 Catch:{ JSONException -> 0x0061 }
        r21 = r2;
    L_0x002b:
        r3 = 0;
        r2 = "type";
        r0 = r21;
        r2 = r0.get(r2);	 Catch:{ JSONException -> 0x0068 }
        r2 = (java.lang.String) r2;	 Catch:{ JSONException -> 0x0068 }
    L_0x0036:
        r6 = new com.ihealth.communication.cloud.data.Summary_WorkOut;
        r6.<init>();
        r4 = new com.ihealth.communication.cloud.data.Summary_Sleep;
        r4.<init>();
        r5 = new com.ihealth.communication.cloud.data.Data_TB_Swim;
        r5.<init>();
        r3 = -1;
        r7 = r2.hashCode();
        switch(r7) {
            case 3543688: goto L_0x0082;
            case 109522647: goto L_0x0078;
            case 1144227340: goto L_0x006e;
            default: goto L_0x004d;
        };
    L_0x004d:
        r2 = r3;
    L_0x004e:
        switch(r2) {
            case 0: goto L_0x008c;
            case 1: goto L_0x015b;
            case 2: goto L_0x0188;
            default: goto L_0x0051;
        };
    L_0x0051:
        r2 = r10;
    L_0x0052:
        r3 = r22 + 1;
        r22 = r3;
        r10 = r2;
        r3 = r21;
        goto L_0x0017;
    L_0x005a:
        r3 = move-exception;
        r3.printStackTrace();
        r20 = r2;
        goto L_0x000d;
    L_0x0061:
        r2 = move-exception;
        r2.printStackTrace();
        r21 = r3;
        goto L_0x002b;
    L_0x0068:
        r2 = move-exception;
        r2.printStackTrace();
        r2 = r3;
        goto L_0x0036;
    L_0x006e:
        r7 = "stage_data_type_workout";
        r2 = r2.equals(r7);
        if (r2 == 0) goto L_0x004d;
    L_0x0076:
        r2 = 0;
        goto L_0x004e;
    L_0x0078:
        r7 = "sleep";
        r2 = r2.equals(r7);
        if (r2 == 0) goto L_0x004d;
    L_0x0080:
        r2 = 1;
        goto L_0x004e;
    L_0x0082:
        r7 = "swim";
        r2 = r2.equals(r7);
        if (r2 == 0) goto L_0x004d;
    L_0x008a:
        r2 = 2;
        goto L_0x004e;
    L_0x008c:
        r2 = 1;
        r6.setId(r2);
        r2 = "usedtime";
        r0 = r21;
        r2 = r0.getInt(r2);	 Catch:{ JSONException -> 0x0150 }
        r6.setUseTime(r2);	 Catch:{ JSONException -> 0x0150 }
        r2 = "stoptime";
        r0 = r21;
        r2 = r0.getString(r2);	 Catch:{ JSONException -> 0x0150 }
        r6.setOverTime(r2);	 Catch:{ JSONException -> 0x0150 }
        r2 = "stage_data_workout_step";
        r0 = r21;
        r2 = r0.getInt(r2);	 Catch:{ JSONException -> 0x0150 }
        r6.setSteps(r2);	 Catch:{ JSONException -> 0x0150 }
        r2 = "calorie";
        r0 = r21;
        r2 = r0.getInt(r2);	 Catch:{ JSONException -> 0x0150 }
        r6.setCalorie(r2);	 Catch:{ JSONException -> 0x0150 }
        r2 = "stage_data_distance";
        r0 = r21;
        r2 = r0.getString(r2);	 Catch:{ JSONException -> 0x0150 }
        r2 = java.lang.Float.parseFloat(r2);	 Catch:{ JSONException -> 0x0150 }
        r6.setDistance(r2);	 Catch:{ JSONException -> 0x0150 }
    L_0x00cb:
        r2 = 0;
        r3 = "stoptime";
        r0 = r21;
        r3 = r0.getString(r3);	 Catch:{ JSONException -> 0x0156 }
        r4 = com.ihealth.communication.utils.PublicMethod.String2TS(r3);	 Catch:{ JSONException -> 0x0156 }
        r3 = "usedtime";
        r0 = r21;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x0156 }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ JSONException -> 0x0156 }
        r8 = com.ihealth.communication.utils.PublicMethod.String2TS(r3);	 Catch:{ JSONException -> 0x0156 }
        r4 = r4 - r8;
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x0156 }
        r3.<init>();	 Catch:{ JSONException -> 0x0156 }
        r0 = r25;
        r3 = r3.append(r0);	 Catch:{ JSONException -> 0x0156 }
        r3 = r3.append(r4);	 Catch:{ JSONException -> 0x0156 }
        r4 = "stoptime";
        r0 = r21;
        r4 = r0.getString(r4);	 Catch:{ JSONException -> 0x0156 }
        r4 = com.ihealth.communication.utils.PublicMethod.String2TS(r4);	 Catch:{ JSONException -> 0x0156 }
        r3 = r3.append(r4);	 Catch:{ JSONException -> 0x0156 }
        r2 = r3.toString();	 Catch:{ JSONException -> 0x0156 }
    L_0x010c:
        r3 = r6.getUseTime();
        r4 = r6.getSteps();
        r5 = r6.getDistance();
        r5 = (int) r5;
        r6 = r6.getCalorie();
        r6 = (float) r6;
        r7 = r26;
        r8 = r25;
        r9 = r24;
        r2 = com.ihealth.communication.cloud.data.Make_Data_Util.makeDataSingleAMWorkOut(r2, r3, r4, r5, r6, r7, r8, r9);
        r3 = new com.ihealth.communication.cloud.data.DataBaseTools;
        r0 = r23;
        r3.<init>(r0);
        r4 = "TB_WorkOut";
        r2 = r3.addData(r4, r2);
        r3 = "Method";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r5 = "workout stage data up cloud result is ";
        r4 = r4.append(r5);
        r2 = r4.append(r2);
        r2 = r2.toString();
        com.ihealth.communication.utils.Log.i(r3, r2);
        r2 = r10;
        goto L_0x0052;
    L_0x0150:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x00cb;
    L_0x0156:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x010c;
    L_0x015b:
        r2 = 2;
        r4.setId(r2);
        r2 = "stoptime";
        r0 = r21;
        r2 = r0.getString(r2);	 Catch:{ JSONException -> 0x0184 }
        r4.setOverTime(r2);	 Catch:{ JSONException -> 0x0184 }
        r2 = "usedtime";
        r0 = r21;
        r2 = r0.getInt(r2);	 Catch:{ JSONException -> 0x0184 }
        r4.setUseTime(r2);	 Catch:{ JSONException -> 0x0184 }
        r2 = "sleepefficiency";
        r0 = r21;
        r2 = r0.getDouble(r2);	 Catch:{ JSONException -> 0x0184 }
        r2 = (float) r2;	 Catch:{ JSONException -> 0x0184 }
        r4.setSleepEfficiency(r2);	 Catch:{ JSONException -> 0x0184 }
        r2 = r10;
        goto L_0x0052;
    L_0x0184:
        r2 = move-exception;
        r2 = r10;
        goto L_0x0052;
    L_0x0188:
        r2 = r10 + 1;
        r3 = "calorie";
        r0 = r21;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x027e }
        r3 = (float) r3;	 Catch:{ JSONException -> 0x027e }
        r5.setSwim_Calories(r3);	 Catch:{ JSONException -> 0x027e }
        r3 = "number of strokes";
        r0 = r21;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x027e }
        r5.setSwim_PullTimes(r3);	 Catch:{ JSONException -> 0x027e }
        r3 = "number of turns";
        r0 = r21;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x027e }
        r5.setSwim_Cycles(r3);	 Catch:{ JSONException -> 0x027e }
        r3 = "swimming stroke";
        r0 = r21;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x027e }
        r5.setSwim_Storke(r3);	 Catch:{ JSONException -> 0x027e }
        r3 = "stage_data_swimpool_length";
        r0 = r21;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x027e }
        r5.setSwim_Distance(r3);	 Catch:{ JSONException -> 0x027e }
        r3 = "stoptime";
        r0 = r21;
        r3 = r0.getString(r3);	 Catch:{ JSONException -> 0x027e }
        r6 = com.ihealth.communication.utils.PublicMethod.String2TS(r3);	 Catch:{ JSONException -> 0x027e }
        r5.setSwim_endtime(r6);	 Catch:{ JSONException -> 0x027e }
        r3 = "usedtime";
        r0 = r21;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x027e }
        r5.setSwim_SpendMinutes(r3);	 Catch:{ JSONException -> 0x027e }
        r3 = "stage_data_cutindif";
        r0 = r21;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x027e }
        r5.setSwim_CutInTimeDif(r3);	 Catch:{ JSONException -> 0x027e }
        r3 = "stage_data_cutoutdif";
        r0 = r21;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x027e }
        r5.setSwim_CutOutTimeDif(r3);	 Catch:{ JSONException -> 0x027e }
        r3 = "stage_data_processflag";
        r0 = r21;
        r3 = r0.getInt(r3);	 Catch:{ JSONException -> 0x027e }
        r5.setSwim_ProcessFlag(r3);	 Catch:{ JSONException -> 0x027e }
        r6 = r5.getSwim_endtime();	 Catch:{ JSONException -> 0x027e }
        r3 = r5.getSwim_SpendMinutes();	 Catch:{ JSONException -> 0x027e }
        r8 = (long) r3;	 Catch:{ JSONException -> 0x027e }
        r18 = r6 - r8;
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e }
        r3.<init>();	 Catch:{ JSONException -> 0x027e }
        r0 = r25;
        r3 = r3.append(r0);	 Catch:{ JSONException -> 0x027e }
        r0 = r18;
        r3 = r3.append(r0);	 Catch:{ JSONException -> 0x027e }
        r6 = r5.getSwim_endtime();	 Catch:{ JSONException -> 0x027e }
        r3 = r3.append(r6);	 Catch:{ JSONException -> 0x027e }
        r4 = r3.toString();	 Catch:{ JSONException -> 0x027e }
        r7 = r5.getSwim_Calories();	 Catch:{ JSONException -> 0x027e }
        r8 = r5.getSwim_PullTimes();	 Catch:{ JSONException -> 0x027e }
        r9 = r5.getSwim_Cycles();	 Catch:{ JSONException -> 0x027e }
        r10 = r5.getSwim_Stroke();	 Catch:{ JSONException -> 0x027e }
        r11 = r5.getSwim_Distance();	 Catch:{ JSONException -> 0x027e }
        r12 = r5.getSwim_endtime();	 Catch:{ JSONException -> 0x027e }
        r14 = r5.getSwim_SpendMinutes();	 Catch:{ JSONException -> 0x027e }
        r15 = r5.getSwim_CutInTimeDif();	 Catch:{ JSONException -> 0x027e }
        r16 = r5.getSwim_CutOutTimeDif();	 Catch:{ JSONException -> 0x027e }
        r17 = r5.getSwim_ProcessFlag();	 Catch:{ JSONException -> 0x027e }
        r3 = r24;
        r5 = r25;
        r6 = r26;
        r3 = com.ihealth.communication.cloud.data.Make_Data_Util.makeDataSingleAMSwim(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r14, r15, r16, r17, r18);	 Catch:{ JSONException -> 0x027e }
        r4 = new com.ihealth.communication.cloud.data.DataBaseTools;	 Catch:{ JSONException -> 0x027e }
        r0 = r23;
        r4.<init>(r0);	 Catch:{ JSONException -> 0x027e }
        r5 = "TB_Swim";
        r3 = r4.addData(r5, r3);	 Catch:{ JSONException -> 0x027e }
        r4 = "Method";
        r5 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x027e }
        r5.<init>();	 Catch:{ JSONException -> 0x027e }
        r6 = "swim stage data up cloud result is ";
        r5 = r5.append(r6);	 Catch:{ JSONException -> 0x027e }
        r3 = r5.append(r3);	 Catch:{ JSONException -> 0x027e }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x027e }
        com.ihealth.communication.utils.Log.i(r4, r3);	 Catch:{ JSONException -> 0x027e }
        goto L_0x0052;
    L_0x027e:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0052;
    L_0x0284:
        r2 = new com.ihealth.communication.cloud.data.AM_InAuthor;
        r2.<init>();
        r0 = r23;
        r1 = r24;
        r2.initAuthor(r0, r1);
        r2.run();
    L_0x0293:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ihealth.communication.cloud.a.l.c(android.content.Context, java.lang.String, java.lang.String, java.lang.String, org.json.JSONObject):void");
    }
}

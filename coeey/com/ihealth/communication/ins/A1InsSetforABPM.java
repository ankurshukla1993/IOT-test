package com.ihealth.communication.ins;

import android.content.Context;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.BleCommProtocol;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;
import humanize.util.Constants;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class A1InsSetforABPM extends IdentifyIns implements NewDataCallback {
    private Context f1528a;
    private BaseCommProtocol f1529b;
    private String f1530c;
    public boolean getOfflineData = false;
    private String f1531l;
    private BaseComm f1532m;
    private String f1533n;
    private InsCallback f1534o;
    private BaseCommCallback f1535p;
    private A1DBtools f1536q;
    private int f1537r = 1;
    private byte[] f1538s;

    enum Command {
        Unknown(0),
        Verification_Feedback(251),
        Verification_Success(253),
        Verification_Failed(254),
        Get_BatteryLevel(32),
        Get_FunctionInfo(33),
        Set_DisplayUnit(38),
        Set_MeasureTime(39),
        Get_MeasureTime(40),
        Set_Alarm(41),
        Get_AlarmSetting(42),
        Get_AlarmType(43),
        Get_OffLineDataNum(64),
        Get_OffLineData(65),
        DeleteAllMemory_Finish(68),
        DeleteAllMemory_Confirm(69);
        
        int f1527a;

        private Command(int what) {
            this.f1527a = what;
        }

        static Command m778a(int i) {
            for (Command command : values()) {
                if (command.f1527a == i) {
                    return command;
                }
            }
            return Unknown;
        }

        public String toString() {
            return String.format("%s(0x%02X)", new Object[]{name(), Integer.valueOf(this.f1527a)});
        }
    }

    public A1InsSetforABPM(Context context, BaseComm com, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        Log.p("A1InsSetforABPM", Level.INFO, "A1InsSetforABPM_Constructor", new Object[]{userName, mac, type});
        this.f1528a = context;
        this.f1530c = mac;
        this.f1531l = type;
        this.f1529b = new BleCommProtocol(context, com, mac, (byte) -95, this);
        this.f1532m = com;
        this.f1533n = userName;
        this.f1534o = insCallback;
        this.f1535p = mBaseCommCallback;
        this.f1536q = new A1DBtools();
        m759a(insCallback, mac, type, this.f1532m);
    }

    private void m779a() {
        Log.p("A1InsSetforABPM", Level.INFO, "getOffLineData", new Object[0]);
        this.f1529b.packageData(this.f1530c, new byte[]{(byte) -95, (byte) 65, (byte) this.f1537r, (byte) 0, (byte) 0});
    }

    private static byte[] m780b(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    private String m781c(int i) {
        String str = "";
        switch (i) {
            case 0:
                return "not find a suitable zero in 20s.";
            case 1:
                return "not find high pressure.";
            case 2:
                return "not find low pressure or the high pressure value is lower than the low pressure value.";
            case 3:
                return "pressurization fast.";
            case 4:
                return "pressurization slow.";
            case 5:
                return "pressure exceeds 300mmHg.";
            case 6:
                return "time of pressure greater than 15 mmHg exceeds 160s.";
            case 7:
                return "EE read and write error.";
            case 8:
                return "EE three backup data error.";
            case 9:
                return "retention";
            case 10:
                return "SPAN value error.";
            case 11:
                return "CRC errors.";
            case 12:
                return "connect error.";
            case 13:
                return "low power tips.";
            case 14:
                return "high or low pressure value of measurement exceeds the set upper limit.";
            case 16:
                return "high or low pressure value of measurement exceeds the set lower limit.";
            case 17:
                return "arm movement during the measurement over the machine set point.";
            case 400:
                return "the argument of method is illegal.";
            case 401:
                return "the operation is illegal.";
            default:
                return "UNKNOWN ERROR";
        }
    }

    private void m782c(byte[] bArr) {
        boolean z = (bArr[0] & 1) != 0;
        boolean z2 = (bArr[0] & 2) != 0;
        boolean z3 = (bArr[0] & 4) != 0;
        boolean z4 = (bArr[0] & 8) != 0;
        boolean z5 = (bArr[0] & 16) != 0;
        boolean z6 = (bArr[0] & 32) != 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BpProfile.ALARM_TYPE_HIGH_ABPM, z);
            jSONObject.put(BpProfile.ALARM_TYPE_LOW_ABPM, z2);
            jSONObject.put(BpProfile.ALARM_TYPE_AVERAGE_ABPM, z3);
            jSONObject.put(BpProfile.ALARM_TYPE_HEART_RATE_ABPM, z4);
            jSONObject.put(BpProfile.ALARM_TYPE_EXCESS_ABPM, z5);
            jSONObject.put(BpProfile.ALARM_TYPE_OTHER_ABPM, z6);
            this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_ALARM_TYPE_ABPM, jSONObject.toString());
        } catch (JSONException e) {
            Log.p("A1InsSetforABPM", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
    }

    private void m783d(byte[] bArr) {
        boolean z = (bArr[0] & 1) != 0;
        boolean z2 = (bArr[0] & 2) != 0;
        int i = bArr[1] & 255;
        int i2 = bArr[2] & 255;
        int i3 = bArr[3] & 255;
        int i4 = bArr[4] & 255;
        boolean z3 = (bArr[5] & 1) != 0;
        boolean z4 = (bArr[5] & 2) != 0;
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(i2);
        jSONArray.put(i3);
        jSONArray.put(i4);
        jSONArray.put(z3);
        jSONArray.put(z4);
        i2 = bArr[6] & 255;
        i3 = bArr[7] & 255;
        i4 = bArr[8] & 255;
        z3 = (bArr[9] & 1) != 0;
        z4 = (bArr[9] & 2) != 0;
        JSONArray jSONArray2 = new JSONArray();
        jSONArray2.put(i2);
        jSONArray2.put(i3);
        jSONArray2.put(i4);
        jSONArray2.put(z3);
        jSONArray2.put(z4);
        i2 = bArr[10] & 255;
        i3 = bArr[11] & 255;
        i4 = bArr[12] & 255;
        z3 = (bArr[13] & 1) != 0;
        z4 = (bArr[13] & 2) != 0;
        JSONArray jSONArray3 = new JSONArray();
        jSONArray3.put(i2);
        jSONArray3.put(i3);
        jSONArray3.put(i4);
        jSONArray3.put(z3);
        jSONArray3.put(z4);
        i2 = bArr[14] & 255;
        i3 = bArr[15] & 255;
        i4 = bArr[16] & 255;
        z3 = (bArr[17] & 1) != 0;
        z4 = (bArr[17] & 2) != 0;
        JSONArray jSONArray4 = new JSONArray();
        jSONArray4.put(i2);
        jSONArray4.put(i3);
        jSONArray4.put(i4);
        jSONArray4.put(z3);
        jSONArray4.put(z4);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BpProfile.MEASURE_IS_COMPLETE_ABPM, z);
            jSONObject.put(BpProfile.MEASURE_IS_MEDICINE_ABPM, z2);
            jSONObject.put(BpProfile.GET_MEASURE_TIME_ABPM, i);
            jSONObject.put(BpProfile.GET_FIRST_TIME_ABPM, jSONArray);
            jSONObject.put(BpProfile.GET_SECOND_TIME_ABPM, jSONArray2);
            jSONObject.put(BpProfile.GET_THIRD_TIME_ABPM, jSONArray3);
            jSONObject.put(BpProfile.GET_FORTH_TIME_ABPM, jSONArray4);
            this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_GET_CYCLE_MEASURE_ABPM, jSONObject.toString());
        } catch (JSONException e) {
            Log.p("A1InsSetforABPM", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
    }

    private void m784e(byte[] bArr) {
        int i = bArr[0] & 255;
        boolean z = (bArr[1] & 1) != 0;
        boolean z2 = (bArr[1] & 2) != 0;
        boolean z3 = (bArr[1] & 4) != 0;
        boolean z4 = (bArr[1] & 8) != 0;
        boolean z5 = (bArr[1] & 16) != 0;
        boolean z6 = (bArr[1] & 32) != 0;
        boolean z7 = (bArr[1] & 64) != 0;
        boolean z8 = (bArr[1] & 128) != 0;
        int i2 = bArr[2] & 255;
        int i3 = bArr[3] & 255;
        boolean z9 = (bArr[4] & 16) != 0;
        int i4 = (bArr[4] & 32) != 0 ? 1 : 0;
        boolean z10 = (bArr[6] & 2) != 0;
        boolean z11 = (bArr[6] & 4) != 0;
        boolean z12 = (bArr[6] & 8) != 0;
        boolean z13 = (bArr[6] & 16) != 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("function_operating_state", i);
            jSONObject.put("function_is_upair_measure", z);
            jSONObject.put("function_is_arm_measure", z2);
            jSONObject.put("function_have_angle_sensor", z3);
            jSONObject.put(BpProfile.FUNCTION_HAVE_POWEROFF_ABPM, z4);
            jSONObject.put("function_have_offline", z5);
            jSONObject.put(BpProfile.FUNCTION_ALLOW_DELETE_OFFLINEDATA_ABPM, z6);
            jSONObject.put("function_have_hsd", z7);
            jSONObject.put(BpProfile.FUNCTION_IS_AUTOCYCLE_MEASURE_ABPM, z8);
            jSONObject.put("function_memory_group", i2);
            jSONObject.put("function_max_memory_capacity", i3);
            jSONObject.put("function_have_show_unit_setting", z9);
            jSONObject.put("function_show_unit", i4);
            jSONObject.put("function_is_multi_upload", z10);
            jSONObject.put(BpProfile.FUNCTION_IS_AUTO_UPDATE_ABPM, z11);
            jSONObject.put(BpProfile.FUNCTION_HAVE_ACTIVITY_DETECTION_ABPM, z12);
            jSONObject.put(BpProfile.FUNCTION_HAVE_ALARM_SETTING_ABPM, z13);
            this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_FUNCTION_INFORMATION_ABPM, jSONObject.toString());
        } catch (JSONException e) {
            Log.p("A1InsSetforABPM", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
    }

    private void m785f(byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        int length = bArr.length / 6;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            JSONObject jSONObject2 = new JSONObject();
            byte b = bArr[i + i2];
            boolean z = (bArr[(i + i2) + 1] & 1) != 0;
            boolean z2 = (bArr[(i + i2) + 1] & 2) != 0;
            boolean z3 = (bArr[(i + i2) + 1] & 4) != 0;
            int g = m786g(new byte[]{bArr[(i + i2) + 2], bArr[(i + i2) + 3]});
            int g2 = m786g(new byte[]{bArr[(i + i2) + 4], bArr[(i + i2) + 5]});
            try {
                jSONObject2.put(BpProfile.ALARM_TYPE_ABPM, b);
                jSONObject2.put(BpProfile.IS_SOUND_ALARM_ABPM, z);
                jSONObject2.put(BpProfile.IS_VISUAL_ALARM_ABPM, z2);
                jSONObject2.put(BpProfile.IS_VIBRATION_ALARM_ABPM, z3);
                jSONObject2.put(BpProfile.ALARM_HIGH_ABPM, g);
                jSONObject2.put(BpProfile.ALARM_LOW_ABPM, g2);
                jSONArray.put(jSONObject2);
            } catch (JSONException e) {
                Log.p("A1InsSetforABPM", Level.WARN, "Exception", new Object[]{e.getMessage()});
            }
            i++;
            i2 += 5;
        }
        try {
            jSONObject.putOpt(BpProfile.ALARM_SETTING_ABPM, jSONArray);
            this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_GET_ALARM_SETTING_ABPM, jSONObject.toString());
        } catch (JSONException e2) {
            Log.p("A1InsSetforABPM", Level.WARN, "Exception", new Object[]{e2.getMessage()});
        }
    }

    private static int m786g(byte[] bArr) {
        return (bArr[1] & 255) | ((bArr[0] & 255) << 8);
    }

    private void m787h(byte[] bArr) {
        JSONArray jSONArray = new JSONArray();
        int length = bArr.length / 28;
        for (int i = 0; i < length; i++) {
            byte b = bArr[(i * 28) + 0];
            byte b2 = bArr[(i * 28) + 1];
            byte b3 = bArr[(i * 28) + 2];
            byte b4 = bArr[(i * 28) + 3];
            byte b5 = bArr[(i * 28) + 4];
            byte b6 = bArr[(i * 28) + 5];
            String str = "20" + b + "-" + String.format("%02d", new Object[]{Integer.valueOf(b2)}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(b3)}) + Constants.SPACE + String.format("%02d", new Object[]{Integer.valueOf(b4)}) + ":" + String.format("%02d", new Object[]{Integer.valueOf(b5)}) + ":" + String.format("%02d", new Object[]{Integer.valueOf(b6)});
            b = bArr[(i * 28) + 6];
            b5 = bArr[(i * 28) + 7];
            int i2 = b5 + b;
            byte b7 = bArr[(i * 28) + 8];
            int[] iArr = new int[15];
            for (int i3 = 0; i3 < 15; i3++) {
                iArr[i3] = bArr[(i3 + 9) + (i * 28)];
            }
            byte b8 = bArr[(i * 28) + 24];
            byte b9 = bArr[(i * 28) + 25];
            byte b10 = bArr[(i * 28) + 26];
            boolean z = (bArr[(i * 28) + 27] & 128) != 0;
            boolean z2 = (bArr[(i * 28) + 27] & 16) != 0;
            int i4 = (bArr[(i * 28) + 27] & 8) != 0 ? 1 : 0;
            String md5String = MD5.md5String(PublicMethod.getBPDataID(this.f1530c, b7 + "", System.currentTimeMillis() / 1000));
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(BpProfile.MEASUREMENT_TIME_ABPM, str);
                jSONObject.put("sys", i2);
                jSONObject.put("dia", b5);
                jSONObject.put("heartRate", b7);
                jSONObject.put(BpProfile.MEASUREMENT_AMOUNT_EXERCISE_ABPM, Arrays.toString(iArr));
                jSONObject.put(BpProfile.MEASUREMENT_STATUS_EXERCISE_ABPM, b8);
                jSONObject.put("startAngle", b9);
                jSONObject.put(BpProfile.MEASUREMENT_ANGLE_CHANGE_ABPM, b10);
                jSONObject.put("arrhythmia", z);
                jSONObject.put("hsd", false);
                jSONObject.put(BpProfile.MEASUREMENT_BODY_MOVEMENT_ABPM, z2);
                jSONObject.put(BpProfile.MEASUREMEAT_MODE_ABPM, i4);
                jSONObject.put("dataID", md5String);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("data", jSONArray);
            this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_HISTORICAL_DATA_ABPM, jSONObject2.toString());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void deleteAllMemory() {
        Log.p("A1InsSetforABPM", Level.INFO, "deleteAllMemory", new Object[0]);
        this.f1529b.packageData(this.f1530c, new byte[]{(byte) -95, (byte) 67, (byte) this.f1537r, (byte) 0, (byte) 0});
    }

    public void destroy() {
        Log.p("A1InsSetforABPM", Level.INFO, "destroy", new Object[0]);
        if (this.f1529b != null) {
            this.f1529b.destroy();
        }
        this.f1529b = null;
        this.f1528a = null;
        this.f1532m = null;
    }

    public void getAlarmSetting() {
        Log.p("A1InsSetforABPM", Level.INFO, "getAlarmSetting", new Object[0]);
        this.f1529b.packageData(this.f1530c, new byte[]{(byte) -95, (byte) 42, (byte) 0, (byte) 0, (byte) 0});
    }

    public void getAlarmType() {
        Log.p("A1InsSetforABPM", Level.INFO, "getAlarmType", new Object[0]);
        this.f1529b.packageData(this.f1530c, new byte[]{(byte) -95, (byte) 43, (byte) 0, (byte) 0, (byte) 0});
    }

    public void getBatteryLevel() {
        Log.p("A1InsSetforABPM", Level.INFO, "getBatteryLevel", new Object[0]);
        this.f1529b.packageData(this.f1530c, new byte[]{(byte) -95, (byte) 32, (byte) 0, (byte) 0, (byte) 0});
    }

    public void getFunctionInfo() {
        Log.p("A1InsSetforABPM", Level.INFO, "getFunctionInfo", new Object[0]);
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        int i = instance.get(1) - 2000;
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        Log.e("A1InsSetforABPM", i + "-" + i2 + "-" + i3 + "   " + i4 + ":" + i5 + ":" + instance.get(13));
        this.f1529b.packageData(this.f1530c, new byte[]{(byte) -95, (byte) 33, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) r0});
    }

    public void getMeasureTime() {
        Log.p("A1InsSetforABPM", Level.INFO, "getMeasureTime", new Object[0]);
        this.f1529b.packageData(this.f1530c, new byte[]{(byte) -95, (byte) 40, (byte) 0, (byte) 0, (byte) 0});
    }

    public void getOffLineDataNum() {
        Log.p("A1InsSetforABPM", Level.INFO, "getOffLineDataNum", new Object[0]);
        this.f1529b.packageData(this.f1530c, new byte[]{(byte) -95, (byte) 64, (byte) this.f1537r, (byte) 0, (byte) 0});
    }

    public void haveNewData(int what, int stateId, byte[] returnData) {
        m757a(what);
        Command a = Command.m778a(what);
        Log.p("A1InsSetforABPM", Level.DEBUG, "haveNewData", new Object[]{a, Integer.valueOf(stateId), ByteBufferUtil.Bytes2HexString(returnData)});
        JSONObject jSONObject = new JSONObject();
        int i;
        switch (what) {
            case 32:
                i = returnData[0] & 255;
                if (i <= 0 || i > 100) {
                    i = 100;
                }
                int i2 = ((returnData[1] & 255) * 256) + (returnData[2] & 255);
                int i3 = ((returnData[3] & 255) * 256) + (returnData[4] & 255);
                try {
                    jSONObject.put("battery", i);
                    jSONObject.put("voltage", i2);
                    jSONObject.put("remaining_times", i3);
                    this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_BATTERY_ABPM, jSONObject.toString());
                    return;
                } catch (JSONException e) {
                    Log.p("A1InsSetforABPM", Level.WARN, "Exception", new Object[]{e.getMessage()});
                    return;
                }
            case 33:
                if (returnData != null) {
                    m784e(returnData);
                    return;
                } else {
                    Log.w("A1InsSetforABPM", "getFunctionInfo error, returnData from device is null.");
                    return;
                }
            case 38:
                this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_SET_UNIT_SUCCESS_ABPM, null);
                return;
            case 39:
                this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_SET_CYCLE_MEASURE_SUCCESS_ABPM, null);
                return;
            case 40:
                if (returnData != null) {
                    m783d(returnData);
                    return;
                } else {
                    Log.w("A1InsSetforABPM", "get the setting of autoCircleMeasure error, returnData from device is null.");
                    return;
                }
            case 41:
                this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_SET_ALARM_ABPM, null);
                return;
            case 42:
                if (returnData != null) {
                    this.f1538s = ByteBufferUtil.BufferMerger(this.f1538s, ByteBufferUtil.bytesCutt(1, returnData.length - 1, returnData));
                    if (returnData[0] != (byte) 0) {
                        getAlarmSetting();
                        return;
                    } else if (this.f1538s != null) {
                        m785f(this.f1538s);
                        this.f1538s = null;
                        return;
                    } else {
                        return;
                    }
                }
                return;
            case 43:
                if (returnData != null) {
                    m782c(returnData);
                    return;
                } else {
                    Log.w("A1InsSetforABPM", "get the type of alarm that devices support error, returnData from device is null.");
                    return;
                }
            case 56:
                i = returnData[0] & 255;
                try {
                    jSONObject.put("type", this.f1531l);
                    jSONObject.put("error", i);
                    jSONObject.put("description", m781c(i));
                    this.f1534o.onNotify(this.f1530c, this.f1531l, "error_bp", jSONObject.toString());
                    return;
                } catch (JSONException e2) {
                    Log.p("A1InsSetforABPM", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                    return;
                }
            case 64:
                i = returnData[1] & 255;
                if (i == 0 && this.getOfflineData) {
                    this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_HISTORICAL_DATA_BP, new JSONObject().toString());
                    return;
                } else if (i != 0 && this.getOfflineData) {
                    m779a();
                    return;
                } else if (!this.getOfflineData) {
                    try {
                        jSONObject.put(BpProfile.HISTORICAL_NUM_ABPM, i);
                        this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_HISTORICAL_NUM_ABPM, jSONObject.toString());
                        return;
                    } catch (JSONException e22) {
                        Log.p("A1InsSetforABPM", Level.WARN, "Exception", new Object[]{e22.getMessage()});
                        return;
                    }
                } else {
                    return;
                }
            case 65:
                this.f1538s = ByteBufferUtil.BufferMerger(this.f1538s, ByteBufferUtil.bytesCutt(2, returnData.length - 1, returnData));
                if (returnData[1] != (byte) 0) {
                    m779a();
                    return;
                } else if (this.f1538s != null) {
                    m787h(this.f1538s);
                    this.f1538s = null;
                    return;
                } else {
                    return;
                }
            case 69:
                this.f1534o.onNotify(this.f1530c, this.f1531l, BpProfile.ACTION_DELETE_ALL_MEMORY_SUCCESS_ABPM, null);
                return;
            case 251:
                byte[] a2 = m762a(returnData, this.f1531l, (byte) -95);
                m758a(252, 4000, 253, 254);
                this.f1529b.packageData(this.f1530c, a2);
                return;
            case 253:
                this.f1535p.onConnectionStateChange(this.f1530c, this.f1531l, 1, 0, null);
                return;
            case 254:
                this.f1532m.disconnect();
                return;
            case 255:
                identify();
                return;
            default:
                Log.p("A1InsSetforABPM", Level.WARN, "Exception", new Object[]{"no method"});
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void identify() {
        Log.p("A1InsSetforABPM", Level.INFO, "identify", new Object[0]);
        m758a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 4000, 251, 253, 254);
        this.f1529b.packageData(this.f1530c, m760a((byte) -95));
    }

    public void setAlarm(int[]... args) {
        Log.p("A1InsSetforABPM", Level.INFO, "setAlarm", new Object[]{Arrays.toString(args)});
        byte[] bArr = new byte[((args.length * 6) + 3)];
        bArr[0] = (byte) -95;
        bArr[1] = (byte) 41;
        bArr[2] = (byte) 0;
        for (int i = 0; i < args.length; i++) {
            int[] iArr = args[i];
            bArr[(i * 6) + 3] = (byte) iArr[0];
            bArr[(i * 6) + 4] = (byte) iArr[1];
            byte[] b = m780b(iArr[2]);
            bArr[(i * 6) + 5] = b[0];
            bArr[(i * 6) + 6] = b[1];
            byte[] b2 = m780b(iArr[3]);
            bArr[(i * 6) + 7] = b2[0];
            bArr[(i * 6) + 8] = b2[1];
        }
        Log.e("A1InsSetforABPM", Arrays.toString(bArr));
        this.f1529b.packageData(this.f1530c, bArr);
    }

    public void setDisplayUnit(int unit) {
        Log.p("A1InsSetforABPM", Level.INFO, "getFunctionInfo", new Object[]{Integer.valueOf(unit)});
        byte[] bArr = new byte[5];
        bArr[0] = (byte) -95;
        bArr[1] = (byte) 38;
        if (unit == 2) {
            bArr[2] = (byte) 85;
        } else if (unit == 1) {
            bArr[2] = (byte) 0;
        }
        bArr[3] = (byte) 0;
        bArr[4] = (byte) 0;
        this.f1529b.packageData(this.f1530c, bArr);
    }

    public void setMeasureTime(int length, boolean isMedicine, int[]... times) {
        Log.p("A1InsSetforABPM", Level.INFO, "setMeasureTime", new Object[]{Integer.valueOf(length), Boolean.valueOf(isMedicine), times});
        byte[] bArr = new byte[20];
        bArr[0] = (byte) -95;
        bArr[1] = (byte) 39;
        bArr[2] = (byte) length;
        if (isMedicine) {
            bArr[3] = (byte) 2;
        } else {
            bArr[3] = (byte) 0;
        }
        int i;
        int[] iArr;
        if (times.length == 4) {
            for (i = 0; i < times.length; i++) {
                iArr = times[i];
                bArr[(i * 4) + 4] = (byte) iArr[0];
                bArr[(i * 4) + 5] = (byte) iArr[1];
                bArr[(i * 4) + 6] = (byte) iArr[2];
                bArr[(i * 4) + 7] = (byte) iArr[3];
                Log.e("A1InsSetforABPM", Arrays.toString(iArr));
            }
        } else if (times.length == 2) {
            for (i = 0; i < 4; i++) {
                if (i < 2) {
                    iArr = times[i];
                    bArr[(i * 4) + 4] = (byte) iArr[0];
                    bArr[(i * 4) + 5] = (byte) iArr[1];
                    bArr[(i * 4) + 6] = (byte) iArr[2];
                    bArr[(i * 4) + 7] = (byte) iArr[3];
                } else {
                    bArr[(i * 4) + 4] = (byte) -1;
                    bArr[(i * 4) + 5] = (byte) -1;
                    bArr[(i * 4) + 6] = (byte) -1;
                    bArr[(i * 4) + 7] = (byte) -1;
                }
                Log.e("A1InsSetforABPM", Arrays.toString(bArr));
            }
        }
        this.f1529b.packageData(this.f1530c, bArr);
    }

    public void setMemory_Size(int memory_Size) {
        Log.p("A1InsSetforABPM", Level.INFO, "setMemory_Size", new Object[]{Integer.valueOf(memory_Size)});
        this.f1537r = memory_Size;
    }
}

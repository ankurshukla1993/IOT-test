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
import java.util.Calendar;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class A1InsSetforBp5s extends IdentifyIns implements NewDataCallback, GetBaseCommProtocolCallback {
    private String f1539a;
    private String f1540b;
    private BaseComm f1541c;
    public boolean getOfflineData = false;
    private BaseCommProtocol f1542l;
    private InsCallback f1543m;
    private BaseCommCallback f1544n;
    private Context f1545o;

    public A1InsSetforBp5s(Context context, BaseComm com, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1545o = context;
        this.f1539a = mac;
        this.f1541c = com;
        this.f1540b = type;
        this.f1543m = insCallback;
        this.f1544n = mBaseCommCallback;
        this.f1542l = new BleCommProtocol(context, com, mac, (byte) -95, this);
    }

    private void m788a() {
        Log.p("A1InsSetforBp5s", Level.INFO, "getOfflineDataOver", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) 71, (byte) 0, (byte) 0, (byte) 0};
        m758a(71, 4000, 71, 56);
        this.f1542l.packageData(this.f1539a, bArr);
    }

    private String m789b(int i) {
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
            case 32:
                return "device is measuring, can't respond directive";
            case 400:
                return "the argument of method is illegal.";
            case 401:
                return "the operation is illegal.";
            default:
                return "UNKNOWN ERROR";
        }
    }

    private void m790c(byte[] bArr) {
        int i = bArr[0] & 255;
        if (i <= 0 || i > 100) {
            i = 100;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("battery", i);
            this.f1543m.onNotify(this.f1539a, this.f1540b, BpProfile.ACTION_BATTERY_BP5S, jSONObject.toString());
        } catch (JSONException e) {
            Log.p("A1InsSetforBp5s", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
    }

    private void m791d(byte[] bArr) {
        int i = bArr[0] & 255;
        boolean z = (bArr[1] & 1) != 0;
        boolean z2 = (bArr[1] & 2) != 0;
        boolean z3 = (bArr[1] & 4) != 0;
        boolean z4 = (bArr[1] & 8) != 0;
        boolean z5 = (bArr[1] & 16) != 0;
        boolean z6 = (bArr[1] & 64) != 0;
        int i2 = bArr[2] & 255;
        int i3 = (bArr[7] * 256) + (bArr[3] & 255);
        boolean z7 = (bArr[4] & 16) != 0;
        int i4 = (bArr[4] & 32) != 0 ? 1 : 0;
        boolean z8 = (bArr[6] & 1) != 0;
        boolean z9 = (bArr[6] & 2) != 0;
        boolean z10 = (bArr[6] & 4) != 0;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("function_operating_state", i);
            jSONObject.put("function_is_upair_measure", z);
            jSONObject.put("function_is_arm_measure", z2);
            jSONObject.put("function_have_angle_sensor", z3);
            jSONObject.put(BpProfile.FUNCTION_HAVE_REPEATEDLY_MEASURE_BP5S, z4);
            jSONObject.put("function_have_offline", z5);
            jSONObject.put("function_have_hsd", z6);
            jSONObject.put("function_memory_group", i2);
            jSONObject.put("function_max_memory_capacity", i3);
            jSONObject.put("function_have_show_unit_setting", z7);
            jSONObject.put("function_show_unit", i4);
            jSONObject.put(BpProfile.FUNCTION_HAVE_ANGLE_SETTING_BP5S, z8);
            jSONObject.put("function_is_multi_upload", z9);
            jSONObject.put(BpProfile.FUNCTION_IS_SUPPORT_UPDATE_BP5S, z10);
            this.f1543m.onNotify(this.f1539a, this.f1540b, BpProfile.ACTION_FUNCTION_INFORMATION_BP5S, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void m792e(byte[] bArr) {
        int i = bArr[0] & 255;
        int i2 = bArr[1] & 255;
        int i3 = bArr[2] & 255;
        int i4 = bArr[3] & 255;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BpProfile.SCHEMEID_BP5S, i);
            jSONObject.put(BpProfile.REPEATEDLY_MEASURE_STATUS_BP5S, i2);
            jSONObject.put(BpProfile.TOTAL_MEASURE_TIME_BP5S, i3);
            jSONObject.put(BpProfile.REMAINING_MEASURE_TIME_BP5S, i4);
            this.f1543m.onNotify(this.f1539a, this.f1540b, BpProfile.ACTION_MEASURE_STATUS_BP5S, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void m793f(byte[] bArr) {
        int i = (bArr[2] * 256) + (bArr[1] & 255);
        JSONObject jSONObject = new JSONObject();
        if (i == 0 && this.getOfflineData) {
            this.f1543m.onNotify(this.f1539a, this.f1540b, "action_history_data", jSONObject.toString());
            this.f1543m.onNotify(this.f1539a, this.f1540b, BpProfile.ACTION_HISTORICAL_OVER_BP5S, null);
        } else if (i != 0 && this.getOfflineData) {
            getOfflineData();
        } else if (!this.getOfflineData) {
            try {
                jSONObject.put("offlinenum", i);
                this.f1543m.onNotify(this.f1539a, this.f1540b, BpProfile.ACTION_HISTORICAL_NUM_BP5S, jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void m794g(byte[] bArr) {
        byte[] bytesCutt = ByteBufferUtil.bytesCutt(2, bArr.length - 1, bArr);
        if ((bArr[1] & 255) == 0) {
            m788a();
            m795h(bytesCutt);
            return;
        }
        getOfflineData();
        m795h(bytesCutt);
    }

    private void m795h(byte[] bArr) {
        JSONArray jSONArray = new JSONArray();
        int length = bArr.length / 11;
        for (int i = 0; i < length; i++) {
            int i2 = bArr[(i * 11) + 1] & 255;
            int i3 = bArr[(i * 11) + 2] & 255;
            int i4 = bArr[(i * 11) + 3] & 255;
            int i5 = bArr[(i * 11) + 4] & 255;
            int i6 = bArr[(i * 11) + 5] & 255;
            String str = "20" + (bArr[i * 11] & 255) + "-" + String.format("%02d", new Object[]{Integer.valueOf(i2)}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(i3)}) + Constants.SPACE + String.format("%02d", new Object[]{Integer.valueOf(i4)}) + ":" + String.format("%02d", new Object[]{Integer.valueOf(i5)}) + ":" + String.format("%02d", new Object[]{Integer.valueOf(i6)});
            i4 = bArr[(i * 11) + 7] & 255;
            i5 = i4 + (bArr[(i * 11) + 6] & 255);
            i6 = bArr[(i * 11) + 8] & 255;
            int i7 = bArr[(i * 11) + 9] & 255;
            boolean z = (bArr[(i * 11) + 10] & 16) != 0;
            boolean z2 = (bArr[(i * 11) + 10] & 128) != 0;
            String md5String = MD5.md5String(PublicMethod.getBPDataID(this.f1539a, i6 + "", System.currentTimeMillis() / 1000));
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("time", str);
                jSONObject.put("sys", i5);
                jSONObject.put("dia", i4);
                jSONObject.put("heartbeat", i6);
                jSONObject.put(BpProfile.SCHEMEID_BP5S, i7);
                jSONObject.put("arrhythmia", z2);
                jSONObject.put("body_movement", z);
                jSONObject.put("dataID", md5String);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put(BpProfile.HISTORICAL_DATA_BP5S, jSONArray);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        this.f1543m.onNotify(this.f1539a, this.f1540b, "action_history_data", jSONObject2.toString());
    }

    private void m796i(byte[] bArr) {
        boolean z = true;
        int i = bArr[0] & 255;
        int i2 = bArr[2] & 255;
        int i3 = (bArr[1] & 255) + i2;
        int i4 = bArr[3] & 255;
        boolean z2 = bArr[4] != (byte) 0;
        if (bArr[6] == (byte) 0) {
            z = false;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(BpProfile.SCHEMEID_BP5S, i);
            jSONObject.put("sys", i3);
            jSONObject.put("dia", i2);
            jSONObject.put("heartbeat", i4);
            jSONObject.put("arrhythmia", z2);
            jSONObject.put("body_movement", z);
            jSONObject.put("dataID", MD5.md5String(PublicMethod.getBPDataID(this.f1539a, i4 + "", System.currentTimeMillis() / 1000)));
            this.f1543m.onNotify(this.f1539a, this.f1540b, BpProfile.ACTION_ONLINE_RESULT_BP5S, jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void m797j(byte[] bArr) {
        int i = bArr[0] & 255;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("error", i);
            jSONObject.put("description", m789b(i));
            this.f1543m.onNotify(this.f1539a, this.f1540b, "error_bp", jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void deleteRepeatedlyMeasureSetting(int schemeID) {
        Log.p("A1InsSetforBp5s", Level.INFO, "deleteRepeatedlyMeasureSetting", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) 73, (byte) schemeID, (byte) 0, (byte) 0};
        m758a(73, 4000, 73, 56);
        this.f1542l.packageData(this.f1539a, bArr);
    }

    public void destroy() {
        Log.p("A1InsSetforBp5s", Level.INFO, "destroy", new Object[0]);
        if (this.f1542l != null) {
            this.f1542l.destroy();
        }
        this.f1542l = null;
        this.f1545o = null;
        this.f1541c = null;
    }

    public BaseCommProtocol getBaseCommProtocol() {
        return this.f1542l;
    }

    public void getBattery() {
        Log.p("A1InsSetforBp5s", Level.INFO, "getBattery", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) 32, (byte) 0, (byte) 0, (byte) 0};
        m758a(32, 4000, 32);
        this.f1542l.packageData(this.f1539a, bArr);
    }

    public void getFunctionInfo() {
        Log.p("A1InsSetforBp5s", Level.INFO, "getFunctionInfo", new Object[0]);
        byte[] bArr = new byte[8];
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        int i = instance.get(1) - 2000;
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        bArr[0] = (byte) -95;
        bArr[1] = (byte) 33;
        bArr[2] = (byte) i;
        bArr[3] = (byte) i2;
        bArr[4] = (byte) i3;
        bArr[5] = (byte) i4;
        bArr[6] = (byte) i5;
        bArr[7] = (byte) i6;
        m758a(33, 4000, 33, 56);
        this.f1542l.packageData(this.f1539a, bArr);
    }

    public void getOfflineData() {
        Log.p("A1InsSetforBp5s", Level.INFO, "getOfflineData", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) 74, (byte) 1, (byte) 0, (byte) 0};
        m758a(74, 4000, 74, 56);
        this.f1542l.packageData(this.f1539a, bArr);
    }

    public void getOfflineDataNum() {
        Log.p("A1InsSetforBp5s", Level.INFO, "getOfflineDataNum", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) 64, (byte) 0, (byte) 0, (byte) 0};
        m758a(64, 4000, 64, 56);
        this.f1542l.packageData(this.f1539a, bArr);
    }

    public void haveNewData(int what, int stateId, byte[] returnData) {
        r6 = new Object[3];
        r6[0] = String.format("0x%02X", new Object[]{Integer.valueOf(what)});
        r6[1] = Integer.valueOf(stateId);
        r6[2] = ByteBufferUtil.Bytes2HexString(returnData);
        Log.p("A1InsSetforBp5s", Level.DEBUG, "haveNewData", r6);
        m757a(what);
        switch (what) {
            case 32:
                m790c(returnData);
                return;
            case 33:
                m791d(returnData);
                return;
            case 42:
                m792e(returnData);
                return;
            case 54:
                m796i(returnData);
                return;
            case 56:
                m797j(returnData);
                return;
            case 64:
                m793f(returnData);
                return;
            case 71:
                this.f1543m.onNotify(this.f1539a, this.f1540b, BpProfile.ACTION_HISTORICAL_OVER_BP5S, null);
                return;
            case 72:
                this.f1543m.onNotify(this.f1539a, this.f1540b, BpProfile.ACTION_SET_REPEATEDLY_MEASURE_SETTING_SUCCESS_BP5S, null);
                return;
            case 73:
                this.f1543m.onNotify(this.f1539a, this.f1540b, BpProfile.ACTION_DELETE_REPEATEDLY_MEASURE_SETTING_SUCCESS_BP5S, null);
                return;
            case 74:
                m794g(returnData);
                return;
            case 251:
                byte[] a = m762a(returnData, this.f1540b, (byte) -95);
                m758a(252, 4000, 253, 254);
                this.f1542l.packageData(this.f1539a, a);
                return;
            case 253:
                this.f1544n.onConnectionStateChange(this.f1539a, this.f1540b, 1, 0, null);
                return;
            case 254:
                this.f1541c.disconnect();
                return;
            default:
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void identify() {
        Log.p("A1InsSetforBp5s", Level.INFO, "identify", new Object[0]);
        m758a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 4000, 251, 253, 254);
        this.f1542l.packageData(this.f1539a, m760a((byte) -95));
    }

    public void queryMeasureStatus() {
        Log.p("A1InsSetforBp5s", Level.INFO, "queryMeasureStatus", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) 42, (byte) 0, (byte) 0, (byte) 0};
        m758a(42, 4000, 42, 56);
        this.f1542l.packageData(this.f1539a, bArr);
    }

    public void setRepeatedlyMeasureParameter(int schemeID, int timedMode, int repeatMode, int startHour, int startMinute, int measureTime, int measureIntervalMSB, int measureIntervalLSB) {
        Log.p("A1InsSetforBp5s", Level.INFO, "setRepeatedlyMeasureParameter", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) 72, (byte) schemeID, (byte) timedMode, (byte) repeatMode, (byte) startHour, (byte) startMinute, (byte) measureTime, (byte) measureIntervalMSB, (byte) measureIntervalLSB};
        m758a(72, 4000, 72, 56);
        this.f1542l.packageData(this.f1539a, bArr);
    }
}

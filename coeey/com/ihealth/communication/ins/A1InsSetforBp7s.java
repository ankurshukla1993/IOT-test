package com.ihealth.communication.ins;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.facebook.imageutils.JfifUtil;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.BleCommProtocol;
import com.ihealth.communication.base.protocol.Bp7sBtCommProtocol;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.manager.iHealthDevicesIDPS;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;
import humanize.util.Constants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class A1InsSetforBp7s extends IdentifyIns implements NewDataCallback {
    private Context f1546a;
    private BaseCommProtocol f1547b;
    private String f1548c;
    public boolean getOfflineData = false;
    private String f1549l;
    private String f1550m;
    private String f1551n;
    private String f1552o;
    private String f1553p;
    private String f1554q;
    private String f1555r = "";
    private BaseComm f1556s;
    private String f1557t;
    private InsCallback f1558u;
    private BaseCommCallback f1559v;
    private A1DBtools f1560w;
    private int f1561x = 1;
    private byte[] f1562y;
    private ArrayList f1563z;

    public A1InsSetforBp7s(Context context, BaseComm com, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        Log.p("A1InsSetforBp7s", Level.INFO, "A1InsSetforBp7s_Constructor", new Object[]{userName, mac, type});
        this.f1546a = context;
        this.f1548c = mac;
        this.f1549l = type;
        if (type.equals(iHealthDevicesManager.TYPE_BP7S)) {
            this.f1547b = new Bp7sBtCommProtocol(context, mac, type, com, this);
        } else {
            this.f1547b = new BleCommProtocol(context, com, mac, (byte) -95, this);
        }
        this.f1563z = new ArrayList();
        this.f1556s = com;
        this.f1557t = userName;
        this.f1558u = insCallback;
        this.f1559v = mBaseCommCallback;
        this.f1560w = new A1DBtools();
        m759a(insCallback, mac, type, this.f1556s);
    }

    private int m798a(byte[] bArr, int i) {
        int i2 = 0;
        while (i < bArr.length && bArr[i] != (byte) 0) {
            i2++;
            if (i2 > 15) {
                break;
            }
            i++;
        }
        return i2;
    }

    private void m799a() {
        Log.p("A1InsSetforBp7s", Level.INFO, "getOffLineData", new Object[0]);
        this.f1547b.packageData(this.f1548c, new byte[]{(byte) -95, (byte) 70, (byte) this.f1561x, (byte) 0, (byte) 0});
        m758a(70, 4000, 64, 70, 56);
    }

    private String m800b(int i) {
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

    private void m801b(byte b) {
        this.f1547b.packageData(this.f1548c, new byte[]{(byte) -95, b});
    }

    private void m802c(byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        this.f1563z.clear();
        int length = bArr.length / 10;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            StringBuffer stringBuffer = new StringBuffer();
            int i4 = 0;
            for (i3 = 0; i3 < 10; i3++) {
                if (i3 == 0) {
                    i4 = bArr[i3 + i2] < (byte) 0 ? 1 : 0;
                    stringBuffer.append(bArr[i3 + i2] & 127).append(",");
                } else if (i3 == 1) {
                    stringBuffer.append(bArr[i3 + i2] & 127).append(",");
                } else {
                    stringBuffer.append(bArr[i3 + i2] & 255).append(",");
                }
            }
            stringBuffer.append(i4);
            i3 = i2 + 10;
            this.f1563z.add(stringBuffer.toString());
            i++;
            i2 = i3;
        }
        for (int i5 = 0; i5 < this.f1563z.size(); i5++) {
            JSONObject jSONObject2 = new JSONObject();
            int parseInt = Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[5]) + Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[6]);
            int parseInt2 = Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[6]);
            int parseInt3 = Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[7]);
            i2 = Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[10]);
            byte parseInt4 = (byte) Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[0]);
            byte parseInt5 = (byte) Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[1]);
            Object obj = (parseInt5 & 128) != 0 ? 1 : null;
            byte parseInt6 = (byte) Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[2]);
            int i6 = (parseInt6 & JfifUtil.MARKER_SOFn) >> 6;
            byte parseInt7 = (byte) Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[8]);
            byte parseInt8 = (byte) Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[9]);
            String str = Integer.parseInt("20" + ((String) this.f1563z.get(i5)).split(",")[0]) + "-" + (parseInt5 & 127) + "-" + (parseInt6 & 63) + Constants.SPACE + Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[3]) + ":" + Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[4]) + ":00";
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            try {
                jSONObject2.put("time", str);
                jSONObject2.put("sys", parseInt);
                jSONObject2.put("dia", parseInt2);
                jSONObject2.put("heartRate", parseInt3);
                if (i2 == 0) {
                    jSONObject2.put("arrhythmia", false);
                } else {
                    jSONObject2.put("arrhythmia", true);
                }
                if (obj == null) {
                    jSONObject2.put("hsd", false);
                } else {
                    jSONObject2.put("hsd", true);
                }
                jSONObject2.put("startAngle", parseInt7);
                jSONObject2.put(BpProfile.MEASUREMENT_ANGLE_CHANGE_BP, parseInt8);
                jSONObject2.put(BpProfile.MEASUREMENT_HAND_BP, i6);
                jSONObject2.put("dataID", MD5.md5String(PublicMethod.getBPDataID(this.f1548c, parseInt3 + "", currentTimeMillis)));
                jSONArray.put(jSONObject2);
            } catch (JSONException e) {
                Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{e.getMessage()});
            }
            this.f1560w.save(this.f1546a, this.f1557t, this.f1548c, this.f1549l, parseInt, parseInt2, parseInt3, currentTimeMillis);
        }
        try {
            jSONObject.putOpt("data", jSONArray);
            this.f1558u.onNotify(this.f1548c, this.f1549l, BpProfile.ACTION_HISTORICAL_DATA_BP, jSONObject.toString());
        } catch (JSONException e2) {
            Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{e2.getMessage()});
        }
    }

    private void m803d(byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        this.f1563z.clear();
        int length = bArr.length / 10;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3;
            StringBuffer stringBuffer = new StringBuffer();
            int i4 = 0;
            for (i3 = 0; i3 < 10; i3++) {
                if (i3 == 0) {
                    i4 = bArr[i3 + i2] < (byte) 0 ? 1 : 0;
                    stringBuffer.append(bArr[i3 + i2] & 127).append(",");
                } else if (i3 == 1) {
                    stringBuffer.append(bArr[i3 + i2] & 127).append(",");
                } else {
                    stringBuffer.append(bArr[i3 + i2] & 255).append(",");
                }
            }
            stringBuffer.append(i4);
            i3 = i2 + 10;
            this.f1563z.add(stringBuffer.toString());
            i++;
            i2 = i3;
        }
        for (int i5 = 0; i5 < this.f1563z.size(); i5++) {
            JSONObject jSONObject2 = new JSONObject();
            int parseInt = Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[5]) + Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[6]);
            int parseInt2 = Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[6]);
            int parseInt3 = Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[7]);
            i2 = Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[10]);
            byte parseInt4 = (byte) Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[1]);
            Object obj = (parseInt4 & 128) != 0 ? 1 : null;
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            try {
                jSONObject2.put("time", Integer.parseInt("20" + ((String) this.f1563z.get(i5)).split(",")[0]) + "-" + (parseInt4 & 127) + "-" + (((byte) Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[2])) & 63) + Constants.SPACE + Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[3]) + ":" + Integer.parseInt(((String) this.f1563z.get(i5)).split(",")[4]) + ":00");
                jSONObject2.put("sys", parseInt);
                jSONObject2.put("dia", parseInt2);
                jSONObject2.put("heartRate", parseInt3);
                if (i2 == 0) {
                    jSONObject2.put("arrhythmia", false);
                } else {
                    jSONObject2.put("arrhythmia", true);
                }
                if (obj == null) {
                    jSONObject2.put("hsd", false);
                } else {
                    jSONObject2.put("hsd", true);
                }
                jSONObject2.put("dataID", MD5.md5String(PublicMethod.getBPDataID(this.f1548c, parseInt3 + "", currentTimeMillis)));
                jSONArray.put(jSONObject2);
            } catch (JSONException e) {
                Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{e.getMessage()});
            }
            this.f1560w.save(this.f1546a, this.f1557t, this.f1548c, this.f1549l, parseInt, parseInt2, parseInt3, currentTimeMillis);
        }
        try {
            jSONObject.putOpt("data", jSONArray);
            this.f1558u.onNotify(this.f1548c, this.f1549l, BpProfile.ACTION_HISTORICAL_DATA_BP, jSONObject.toString());
        } catch (JSONException e2) {
            Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{e2.getMessage()});
        }
    }

    private void m804e(byte[] bArr) {
        byte[] bArr2 = new byte[m798a(bArr, 0)];
        byte[] bArr3 = new byte[m798a(bArr, 16)];
        byte[] bArr4 = new byte[3];
        byte[] bArr5 = new byte[3];
        byte[] bArr6 = new byte[m798a(bArr, 38)];
        byte[] bArr7 = new byte[m798a(bArr, 54)];
        int i = 0;
        while (i < bArr2.length) {
            try {
                bArr2[i] = bArr[i + 0];
                i++;
            } catch (UnsupportedEncodingException e) {
                Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{e.getMessage()});
                return;
            } catch (Exception e2) {
                Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                return;
            }
        }
        for (i = 0; i < bArr3.length; i++) {
            bArr3[i] = bArr[i + 16];
        }
        for (i = 0; i < bArr4.length; i++) {
            bArr4[i] = bArr[i + 32];
        }
        for (i = 0; i < bArr5.length; i++) {
            bArr5[i] = bArr[i + 35];
        }
        for (i = 0; i < bArr6.length; i++) {
            bArr6[i] = bArr[i + 38];
        }
        for (i = 0; i < bArr7.length; i++) {
            bArr7[i] = bArr[i + 54];
        }
        this.f1554q = new String(bArr2, "UTF-8");
        this.f1555r = new String(bArr3, "UTF-8");
        this.f1551n = String.format("%c.%c.%c", new Object[]{Byte.valueOf(bArr4[0]), Byte.valueOf(bArr4[1]), Byte.valueOf(bArr4[2])});
        this.f1550m = String.format("%c.%c.%c", new Object[]{Byte.valueOf(bArr5[0]), Byte.valueOf(bArr5[1]), Byte.valueOf(bArr5[2])});
        if (bArr7[0] == (byte) 66) {
            this.f1553p = new String(bArr7, "UTF-8");
        } else if (this.f1549l.equals(iHealthDevicesManager.TYPE_BP7S)) {
            this.f1553p = "BP7S 11070";
        } else {
            this.f1553p = "BP 11070";
        }
        this.f1552o = new String(bArr6, "UTF-8");
        Intent intent = new Intent(iHealthDevicesIDPS.MSG_IHEALTH_DEVICE_IDPS);
        intent.putExtra(iHealthDevicesIDPS.PROTOCOLSTRING, this.f1554q);
        intent.putExtra(iHealthDevicesIDPS.ACCESSORYNAME, this.f1555r);
        intent.putExtra(iHealthDevicesIDPS.FIRMWAREVERSION, this.f1551n);
        intent.putExtra(iHealthDevicesIDPS.HARDWAREVERSION, this.f1550m);
        intent.putExtra(iHealthDevicesIDPS.MODENUMBER, this.f1553p);
        intent.putExtra(iHealthDevicesIDPS.MANUFACTURER, this.f1552o);
        intent.putExtra(iHealthDevicesIDPS.SERIALNUMBER, this.f1548c);
        intent.putExtra("type", this.f1549l);
        intent.setPackage(this.f1546a.getPackageName());
        this.f1546a.sendBroadcast(intent);
    }

    public void angleSet(byte leftUpper, byte leftLow, byte rightUpper, byte rightLow) {
        Log.p("A1InsSetforBp7s", Level.INFO, "angleSet", new Object[]{Byte.valueOf(leftUpper), Byte.valueOf(leftLow), Byte.valueOf(rightUpper), Byte.valueOf(rightLow)});
        this.f1547b.packageData(this.f1548c, new byte[]{(byte) -95, (byte) 41, leftUpper, leftLow, rightUpper, rightLow});
        m758a(41, 4000, 41, 56);
    }

    public void destroy() {
        Log.p("A1InsSetforBp7s", Level.INFO, "destroy", new Object[0]);
        if (this.f1547b != null) {
            this.f1547b.destroy();
        }
        this.f1547b = null;
        this.f1546a = null;
        this.f1556s = null;
    }

    public void getBatteryLevel() {
        Log.p("A1InsSetforBp7s", Level.INFO, "getBatteryLevel", new Object[0]);
        this.f1547b.packageData(this.f1548c, new byte[]{(byte) -95, (byte) 32, (byte) 0, (byte) 0, (byte) 0});
        m758a(32, 4000, 32, 56);
    }

    public void getFunctionInfo() {
        Log.p("A1InsSetforBp7s", Level.INFO, "getFunctionInfo", new Object[0]);
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        int i = instance.get(1) - 2000;
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        this.f1547b.packageData(this.f1548c, new byte[]{(byte) -95, (byte) 33, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6});
        m758a(33, 4000, 33, 56);
    }

    public void getIdps() {
        Log.p("A1InsSetforBp7s", Level.INFO, "getIdps", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) -15};
        m758a(241, 4000, 240, 255);
        this.f1547b.packageData(this.f1548c, bArr);
    }

    public void getOffLineDataNum() {
        Log.p("A1InsSetforBp7s", Level.INFO, "getOffLineDataNum", new Object[0]);
        this.f1547b.packageData(this.f1548c, new byte[]{(byte) -95, (byte) 64, (byte) this.f1561x, (byte) 0, (byte) 0});
        m758a(64, 4000, 64, 56);
    }

    public void haveNewData(int what, int stateId, byte[] returnData) {
        r3 = new Object[3];
        r3[0] = String.format("0x%02X", new Object[]{Integer.valueOf(what)});
        r3[1] = Integer.valueOf(stateId);
        r3[2] = ByteBufferUtil.Bytes2HexString(returnData);
        Log.p("A1InsSetforBp7s", Level.DEBUG, "haveNewData", r3);
        m757a(what);
        JSONObject jSONObject = new JSONObject();
        int i;
        switch (what) {
            case 32:
                i = returnData[0] & 255;
                if (i <= 0 || i > 100) {
                    i = 100;
                }
                try {
                    jSONObject.put("battery", i);
                    this.f1558u.onNotify(this.f1548c, this.f1549l, BpProfile.ACTION_BATTERY_BP, jSONObject.toString());
                    return;
                } catch (JSONException e) {
                    Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{e.getMessage()});
                    return;
                }
            case 33:
                if (returnData != null) {
                    boolean z = (returnData[1] & 1) != 0;
                    boolean z2 = (returnData[1] & 2) != 0;
                    boolean z3 = (returnData[1] & 4) != 0;
                    boolean z4 = (returnData[1] & 16) != 0;
                    boolean z5 = (returnData[1] & 64) != 0;
                    boolean z6 = (returnData[6] & 1) != 0;
                    boolean z7 = (returnData[6] & 2) != 0;
                    boolean z8 = (returnData[6] & 4) != 0;
                    try {
                        jSONObject.put(BpProfile.FUNCTION_IS_UPAIR_MEASURE, z);
                        jSONObject.put(BpProfile.FUNCTION_IS_ARM_MEASURE, z2);
                        jSONObject.put(BpProfile.FUNCTION_HAVE_ANGLE_SENSOR, z3);
                        jSONObject.put(BpProfile.FUNCTION_HAVE_OFFLINE, z4);
                        jSONObject.put(BpProfile.FUNCTION_HAVE_HSD, z5);
                        jSONObject.put(BpProfile.FUNCTION_HAVE_ANGLE_SETTING, z6);
                        jSONObject.put(BpProfile.FUNCTION_IS_MULTI_UPLOAD, z7);
                        jSONObject.put(BpProfile.FUNCTION_HAVE_SELF_UPDATE, z8);
                        this.f1558u.onNotify(this.f1548c, this.f1549l, BpProfile.ACTION_FUNCTION_INFORMATION_BP, jSONObject.toString());
                        return;
                    } catch (JSONException e2) {
                        Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                        return;
                    }
                }
                return;
            case 38:
                this.f1558u.onNotify(this.f1548c, this.f1549l, BpProfile.ACTION_SET_UNIT_SUCCESS_BP, null);
                return;
            case 41:
                this.f1558u.onNotify(this.f1548c, this.f1549l, BpProfile.ACTION_SET_ANGLE_SUCCESS_BP, null);
                return;
            case 56:
                i = returnData[0] & 255;
                try {
                    jSONObject.put("type", this.f1549l);
                    jSONObject.put("error", i);
                    jSONObject.put("description", m800b(i));
                    this.f1558u.onNotify(this.f1548c, this.f1549l, "error_bp", jSONObject.toString());
                    return;
                } catch (JSONException e22) {
                    Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{e22.getMessage()});
                    return;
                }
            case 64:
                i = returnData[1] & 255;
                if (i == 0 && this.getOfflineData) {
                    this.f1558u.onNotify(this.f1548c, this.f1549l, BpProfile.ACTION_HISTORICAL_DATA_BP, new JSONObject().toString());
                    return;
                } else if (i != 0 && this.getOfflineData) {
                    m799a();
                    return;
                } else if (!this.getOfflineData) {
                    try {
                        jSONObject.put("offlinenum", i);
                        this.f1558u.onNotify(this.f1548c, this.f1549l, "offlinenum", jSONObject.toString());
                        return;
                    } catch (JSONException e222) {
                        Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{e222.getMessage()});
                        return;
                    }
                } else {
                    return;
                }
            case 70:
                this.f1562y = ByteBufferUtil.BufferMerger(this.f1562y, ByteBufferUtil.bytesCutt(2, returnData.length - 1, returnData));
                if (returnData[1] == (byte) 0) {
                    if (this.f1549l.equals(iHealthDevicesManager.TYPE_BP7S) || this.f1549l.equals(iHealthDevicesManager.TYPE_550BT)) {
                        offlineDataOver();
                    }
                    if (this.f1562y != null) {
                        if (this.f1549l.equals(iHealthDevicesManager.TYPE_550BT)) {
                            m803d(this.f1562y);
                        } else if (this.f1549l.equals(iHealthDevicesManager.TYPE_BP7S)) {
                            m802c(this.f1562y);
                        }
                        this.f1562y = null;
                        return;
                    }
                    return;
                }
                m799a();
                return;
            case 71:
                this.f1558u.onNotify(this.f1548c, this.f1549l, BpProfile.ACTION_HISTORICAL_OVER_BP, null);
                return;
            case 240:
                m801b((byte) -16);
                m804e(returnData);
                return;
            case 251:
                byte[] a = m762a(returnData, this.f1549l, (byte) -95);
                m758a(252, 4000, 253, 254);
                this.f1547b.packageData(this.f1548c, a);
                return;
            case 253:
                this.f1559v.onConnectionStateChange(this.f1548c, this.f1549l, 1, 0, null);
                return;
            case 254:
                this.f1556s.disconnect();
                return;
            case 255:
                identify();
                return;
            default:
                Log.p("A1InsSetforBp7s", Level.WARN, "Exception", new Object[]{"no method"});
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void identify() {
        Log.p("A1InsSetforBp7s", Level.INFO, "identify", new Object[0]);
        m758a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 4000, 251, 253, 254);
        this.f1547b.packageData(this.f1548c, m760a((byte) -95));
    }

    public void offlineDataOver() {
        Log.p("A1InsSetforBp7s", Level.INFO, "offlineDataOver", new Object[0]);
        this.f1547b.packageData(this.f1548c, new byte[]{(byte) -95, (byte) 71, (byte) 0, (byte) 0, (byte) 0});
    }

    public void setMemory_Size(int memory_Size) {
        Log.p("A1InsSetforBp7s", Level.INFO, "setMemory_Size", new Object[]{Integer.valueOf(memory_Size)});
        this.f1561x = memory_Size;
    }

    public void setUnit(int unit) {
        Log.p("A1InsSetforBp7s", Level.INFO, "setUnit", new Object[]{Integer.valueOf(unit)});
        byte[] bArr = new byte[5];
        bArr[0] = (byte) -95;
        bArr[1] = (byte) 38;
        if (unit == 0) {
            bArr[2] = (byte) 0;
        } else {
            bArr[2] = (byte) 85;
        }
        bArr[3] = (byte) 0;
        bArr[4] = (byte) 0;
        this.f1547b.packageData(this.f1548c, bArr);
        m758a(38, 4000, 38, 56);
    }
}

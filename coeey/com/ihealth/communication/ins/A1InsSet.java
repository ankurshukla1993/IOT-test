package com.ihealth.communication.ins;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.common.base.Ascii;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.BleCommProtocol;
import com.ihealth.communication.base.protocol.BtCommProtocol;
import com.ihealth.communication.control.AbiProfile;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.manager.iHealthDevicesIDPS;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.privatecontrol.AbiControlSubManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;
import com.lifesense.ble.bean.DeviceTypeConstants;
import humanize.util.Constants;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tw.com.prolific.driver.pl2303.PL2303Driver;

public class A1InsSet extends IdentifyIns implements NewDataCallback {
    private boolean f1505A = false;
    private byte[] f1506B;
    private int f1507C = 0;
    boolean f1508a = false;
    private Context f1509b;
    private BaseCommProtocol f1510c;
    public boolean getOfflineData = false;
    private String f1511l;
    private String f1512m;
    private String f1513n = "";
    private String f1514o = "";
    private String f1515p = "";
    private String f1516q = "";
    private String f1517r = "";
    private String f1518s = "";
    private BaseComm f1519t;
    private String f1520u;
    private InsCallback f1521v;
    private BaseCommCallback f1522w;
    private A1DBtools f1523x;
    private boolean f1524y = true;
    private boolean f1525z = false;

    class C20941 extends TimerTask {
        final /* synthetic */ A1InsSet f1485a;

        C20941(A1InsSet this$0) {
            this.f1485a = this$0;
        }

        public void run() {
            this.f1485a.m757a(55);
            this.f1485a.f1521v.onNotify(this.f1485a.f1511l, this.f1485a.f1512m, BpProfile.ACTION_INTERRUPTED_BP, null);
        }
    }

    public A1InsSet(Context context, BaseComm com, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        Log.p("A1InsSet", Level.INFO, "A1InsSet_Constructor", new Object[]{userName, mac, type});
        this.f1509b = context;
        this.f1519t = com;
        this.f1520u = userName;
        this.f1511l = mac;
        this.f1512m = type;
        this.f1521v = insCallback;
        if (type.equals(iHealthDevicesManager.TYPE_BP3L)) {
            this.f1510c = new BleCommProtocol(context, com, mac, (byte) -95, this);
        } else {
            this.f1510c = new BtCommProtocol(com, this);
        }
        this.f1522w = mBaseCommCallback;
        this.f1523x = new A1DBtools();
        m759a(insCallback, mac, type, this.f1519t);
    }

    private int m765a(byte[] bArr, int i) {
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

    private void m767a() {
        this.f1510c.packageDataAsk(new byte[]{(byte) -95});
    }

    private void m768a(byte[] bArr, boolean z) {
        int i = (((((bArr[0] & 255) * 256) + (bArr[1] & 255)) * 300) + PL2303Driver.BAUD150) / 4096;
        byte[] bArr2 = new byte[8];
        for (int i2 = 2; i2 < bArr.length; i2++) {
            bArr2[i2 - 2] = bArr[i2];
        }
        String str = "[" + String.valueOf(bArr2[0] & 255) + "," + String.valueOf(bArr2[1] & 255) + "," + String.valueOf(bArr2[2] & 255) + "," + String.valueOf(bArr2[3] & 255) + "," + String.valueOf(bArr2[4] & 255) + "," + String.valueOf(bArr2[5] & 255) + "," + String.valueOf(bArr2[6] & 255) + "," + String.valueOf(bArr2[7] & 255) + "]";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("pressure", i);
            jSONObject.put("heartbeat", z);
            jSONObject.put(BpProfile.PULSEWAVE_BP, str);
            this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_ONLINE_PULSEWAVE_BP, jSONObject.toString());
        } catch (Exception e) {
            Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
    }

    private String m769b(int i) {
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

    private void m771b(byte b) {
        this.f1510c.packageData(this.f1511l, new byte[]{(byte) -95, b});
    }

    private void m772b(byte[] bArr, boolean z) {
        Object obj;
        int i = bArr[0] & 255;
        int i2 = (((((bArr[1] & 255) * 256) + (bArr[2] & 255)) * 300) + PL2303Driver.BAUD150) / 4096;
        String str = "";
        int[] iArr;
        int i3;
        if ((i != 0 || this.f1507C == 255) && i != this.f1507C + 1) {
            iArr = new int[10];
            for (i3 = 3; i3 < 13; i3++) {
                iArr[i3 - 3] = bArr[i3] & 255;
            }
            obj = "[" + String.valueOf(iArr[0] & 255) + "," + String.valueOf(iArr[1] & 255) + "," + String.valueOf(iArr[2] & 255) + "," + String.valueOf(iArr[3] & 255) + "," + String.valueOf(iArr[4] & 255) + "," + String.valueOf(iArr[5] & 255) + "," + String.valueOf(iArr[6] & 255) + "," + String.valueOf(iArr[7] & 255) + "," + String.valueOf(iArr[8] & 255) + "," + String.valueOf(iArr[9] & 255) + "]";
        } else {
            iArr = new int[5];
            for (i3 = 3; i3 < 8; i3++) {
                iArr[i3 - 3] = bArr[i3] & 255;
            }
            obj = "[" + String.valueOf(iArr[0] & 255) + "," + String.valueOf(iArr[1] & 255) + "," + String.valueOf(iArr[2] & 255) + "," + String.valueOf(iArr[3] & 255) + "," + String.valueOf(iArr[4] & 255) + "]";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject = new JSONObject();
            jSONObject.put("pressure", i2);
            jSONObject.put(BpProfile.PULSEWAVE_BP, obj);
            jSONObject.put("heartbeat", z);
            this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_ONLINE_PULSEWAVE_BP, jSONObject.toString());
        } catch (Exception e) {
            Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
        this.f1507C = i;
    }

    private void m774c(byte b) {
        switch (b) {
            case (byte) 0:
                this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_DISENABLE_OFFLINE_BP, null);
                return;
            case (byte) 85:
                this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_ENABLE_OFFLINE_BP, null);
                return;
            default:
                return;
        }
    }

    private void m775c(byte[] bArr) {
        int i = 16;
        byte[] bArr2 = new byte[m765a(bArr, 0)];
        byte[] bArr3 = new byte[m765a(bArr, 16)];
        byte[] bArr4 = new byte[3];
        byte[] bArr5 = new byte[3];
        byte[] bArr6 = new byte[m765a(bArr, 38)];
        int a = m765a(bArr, 54);
        if (a != 0) {
            i = a;
        }
        byte[] bArr7 = new byte[i];
        i = 0;
        while (i < bArr2.length) {
            try {
                bArr2[i] = bArr[i + 0];
                i++;
            } catch (UnsupportedEncodingException e) {
                Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                return;
            } catch (Exception e2) {
                Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                return;
            }
        }
        for (i = 0; i < bArr3.length; i++) {
            bArr3[i] = bArr[i + 16];
        }
        for (i = 0; i < bArr4.length; i++) {
            bArr4[i] = (byte) (bArr[i + 32] + 48);
        }
        for (i = 0; i < bArr5.length; i++) {
            bArr5[i] = (byte) (bArr[i + 35] + 48);
        }
        for (i = 0; i < bArr6.length; i++) {
            bArr6[i] = bArr[i + 38];
        }
        for (i = 0; i < bArr7.length; i++) {
            bArr7[i] = bArr[i + 54];
        }
        this.f1518s = new String(bArr2, "UTF-8");
        this.f1515p = new String(bArr3, "UTF-8");
        this.f1514o = String.format("%c.%c.%c", new Object[]{Byte.valueOf(bArr4[0]), Byte.valueOf(bArr4[1]), Byte.valueOf(bArr4[2])});
        this.f1513n = String.format("%c.%c.%c", new Object[]{Byte.valueOf(bArr5[0]), Byte.valueOf(bArr5[1]), Byte.valueOf(bArr5[2])});
        if (bArr7[0] == (byte) 66) {
            this.f1517r = new String(bArr7, "UTF-8");
        } else if (this.f1512m.equals(iHealthDevicesManager.TYPE_BP5)) {
            if (this.f1518s.contains("com.jiuan.BPV21")) {
                this.f1517r = "BPS5X 11070";
            } else {
                this.f1517r = "BP5 11070";
            }
        } else if (this.f1512m.equals(iHealthDevicesManager.TYPE_BP7)) {
            this.f1517r = "BP7 11070";
        } else if (this.f1512m.equals(iHealthDevicesManager.TYPE_BP3L)) {
            this.f1517r = "BP3L 11070";
        } else {
            this.f1517r = "BPx 11070";
        }
        this.f1516q = new String(bArr6, "UTF-8");
        Intent intent = new Intent(iHealthDevicesIDPS.MSG_IHEALTH_DEVICE_IDPS);
        intent.putExtra(iHealthDevicesIDPS.PROTOCOLSTRING, this.f1518s);
        intent.putExtra(iHealthDevicesIDPS.ACCESSORYNAME, this.f1515p);
        intent.putExtra(iHealthDevicesIDPS.FIRMWAREVERSION, this.f1514o);
        intent.putExtra(iHealthDevicesIDPS.HARDWAREVERSION, this.f1513n);
        intent.putExtra(iHealthDevicesIDPS.MODENUMBER, this.f1517r);
        intent.putExtra(iHealthDevicesIDPS.MANUFACTURER, this.f1516q);
        intent.putExtra(iHealthDevicesIDPS.SERIALNUMBER, this.f1511l);
        intent.putExtra("type", this.f1512m);
        intent.setPackage(this.f1509b.getPackageName());
        this.f1509b.sendBroadcast(intent);
        if (this.f1517r.contains("BPS5A")) {
            AbiControlSubManager.getInstance().createControlUp(this.f1509b, this.f1519t, this.f1510c, this.f1520u, this.f1511l, this.f1512m, AbiProfile.ABI_ARM, this.f1521v, this.f1522w);
        } else if (this.f1517r.contains("BPS5C")) {
            AbiControlSubManager.getInstance().createControlUp(this.f1509b, this.f1519t, this.f1510c, this.f1520u, this.f1511l, this.f1512m, AbiProfile.ABI_ARM, this.f1521v, this.f1522w);
        } else if (this.f1517r.contains("BPS5B")) {
            AbiControlSubManager.getInstance().createControlDown(this.f1509b, this.f1519t, this.f1510c, this.f1520u, this.f1511l, this.f1512m, AbiProfile.ABI_LEG, this.f1521v, this.f1522w);
        } else if (this.f1517r.contains("BPS5D")) {
            AbiControlSubManager.getInstance().createControlDown(this.f1509b, this.f1519t, this.f1510c, this.f1520u, this.f1511l, this.f1512m, AbiProfile.ABI_LEG, this.f1521v, this.f1522w);
        } else if (this.f1517r.contains("BPS5X")) {
            AbiControlSubManager.getInstance().createControlUnkonwn(this.f1509b, this.f1519t, this.f1510c, this.f1520u, this.f1511l, this.f1512m, AbiProfile.ABI_UNKNOWN, this.f1521v, this.f1522w);
        } else {
            identify();
        }
    }

    private void m776d(byte[] bArr) {
        final JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        int length = bArr.length / 8;
        int i = 0;
        int i2 = 0;
        while (i2 < length) {
            try {
                int i3 = bArr[i + 1] & 15;
                int i4 = bArr[i + 2] & 31;
                int i5 = bArr[i + 3] & 63;
                int i6 = bArr[i + 4] & 63;
                int i7 = bArr[i + 6] & 255;
                int i8 = (bArr[i + 5] & 255) + i7;
                int i9 = bArr[i + 7] & 255;
                int i10 = (bArr[i] & 255) >> 7;
                int i11 = (bArr[i + 1] & 255) >> 7;
                String str = String.valueOf((bArr[i] & 127) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE) + "-" + String.format("%02d", new Object[]{Integer.valueOf(i3)}) + "-" + String.format("%02d", new Object[]{Integer.valueOf(i4)}) + Constants.SPACE + String.format("%02d", new Object[]{Integer.valueOf(i5)}) + ":" + String.format("%02d", new Object[]{Integer.valueOf(i6)}) + ":" + DeviceTypeConstants.UNKNOW;
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("time", str);
                jSONObject2.put("sys", i8);
                jSONObject2.put("dia", i7);
                jSONObject2.put("heartRate", i9);
                if (i10 == 0) {
                    jSONObject2.put("arrhythmia", false);
                } else {
                    jSONObject2.put("arrhythmia", true);
                }
                if (i11 == 0) {
                    jSONObject2.put("hsd", false);
                } else {
                    jSONObject2.put("hsd", true);
                }
                jSONObject2.put("dataID", MD5.md5String(PublicMethod.getBPDataID(this.f1511l, i9 + "", PublicMethod.String2TS(str))));
                jSONArray.put(jSONObject2);
                i += 8;
                i2++;
            } catch (JSONException e) {
                Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                return;
            }
        }
        jSONObject.putOpt("data", jSONArray);
        new Timer().schedule(new TimerTask(this) {
            final /* synthetic */ A1InsSet f1487b;

            public void run() {
                this.f1487b.f1521v.onNotify(this.f1487b.f1511l, this.f1487b.f1512m, BpProfile.ACTION_HISTORICAL_DATA_BP, jSONObject.toString());
            }
        }, 500);
    }

    private void m777e(byte[] bArr) {
        int i = bArr[1] & 255;
        int i2 = bArr[2] & 255;
        int i3 = bArr[3] & 255;
        int i4 = bArr[4] & 255;
        int i5 = (bArr[0] & 255) + i;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        this.f1523x.save(this.f1509b, this.f1520u, this.f1511l, this.f1512m, i5, i, i2, currentTimeMillis);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sys", i5);
            jSONObject.put("dia", i);
            jSONObject.put("heartRate", i2);
            if (i3 == 0) {
                jSONObject.put("arrhythmia", false);
            } else {
                jSONObject.put("arrhythmia", true);
            }
            if (i4 == 0) {
                jSONObject.put("hsd", false);
            } else {
                jSONObject.put("hsd", true);
            }
            jSONObject.put("dataID", MD5.md5String(PublicMethod.getBPDataID(this.f1511l, i2 + "", currentTimeMillis)));
            this.f1521v.onNotify(this.f1511l, this.f1512m, "online_result_bp", jSONObject.toString());
        } catch (JSONException e) {
            Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
    }

    public void angleYes() {
        Log.p("A1InsSet", Level.INFO, "angleYes", new Object[0]);
        this.f1510c.packageData(this.f1511l, new byte[]{(byte) -95, (byte) 58, (byte) 0, (byte) 0, (byte) 0});
        this.f1508a = true;
        m757a(58);
        m758a(58, 4000, 48, 50, 55, 56, 59);
    }

    public void destroy() {
        Log.p("A1InsSet", Level.INFO, "destroy", new Object[0]);
        if (this.f1510c != null) {
            this.f1510c.destroy();
        }
        this.f1510c = null;
        this.f1509b = null;
        this.f1519t = null;
    }

    public void disableOfflineMeasure() {
        Log.p("A1InsSet", Level.INFO, "disableOfflineMeasure", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) 36, (byte) 0, (byte) 0, (byte) 0};
        this.f1510c.packageData(this.f1511l, bArr);
        m774c(bArr[2]);
    }

    public void enableOfflineMeasure() {
        Log.p("A1InsSet", Level.INFO, "enableOfflineMeasure", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) 36, (byte) 85, (byte) 0, (byte) 0};
        this.f1510c.packageData(this.f1511l, bArr);
        m774c(bArr[2]);
    }

    public void getBatteryLevel() {
        Log.p("A1InsSet", Level.INFO, "getBatteryLevel", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) 32, (byte) 0, (byte) 0, (byte) 0};
        m758a(32, 4000, 32);
        this.f1510c.packageData(this.f1511l, bArr);
        if (this.f1512m.equals(iHealthDevicesManager.TYPE_BP3M)) {
            this.f1521v.enterAction(BpProfile.ACTION_BATTERY_BP);
        }
    }

    public void getFunctionInfo() {
        Log.p("A1InsSet", Level.INFO, "getFunctionInfo", new Object[0]);
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        int i = instance.get(1) - 2000;
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        byte[] bArr = new byte[]{(byte) -95, (byte) 33, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6};
        m758a(33, 4000, 33, 56);
        this.f1510c.packageData(this.f1511l, bArr);
    }

    public void getIdps() {
        Log.p("A1InsSet", Level.INFO, "getIdps", new Object[0]);
        byte[] bArr = new byte[]{(byte) -95, (byte) -15};
        m758a(241, 8000, 240, 255);
        this.f1510c.packageData(this.f1511l, bArr);
    }

    public void getOffLineData() {
        Log.p("A1InsSet", Level.INFO, "getOffLineData", new Object[0]);
        this.f1510c.packageData(this.f1511l, new byte[]{(byte) -95, (byte) 65, (byte) 1, (byte) 0, (byte) 0});
        m758a(65, 4000, 65, 56);
    }

    public void getOffLineDataNum() {
        Log.p("A1InsSet", Level.INFO, "getOffLineDataNum", new Object[0]);
        this.f1510c.packageData(this.f1511l, new byte[]{(byte) -95, (byte) 64, (byte) 1, (byte) 0, (byte) 0});
        m758a(64, 4000, 64, 56);
    }

    public void haveNewData(int what, int stateId, byte[] returnData) {
        r3 = new Object[3];
        r3[0] = String.format("0x%02X", new Object[]{Integer.valueOf(what)});
        r3[1] = Integer.valueOf(stateId);
        r3[2] = ByteBufferUtil.Bytes2HexString(returnData);
        Log.p("A1InsSet", Level.DEBUG, "haveNewData", r3);
        if (stateId == 0 && !this.f1512m.equals(iHealthDevicesManager.TYPE_BP3L) && (what == 50 || what == 53 || what == 59 || what == 68 || what == 48 || what == 51 || what == 62 || what == 60 || what == 61 || what == 58 || what == 54 || what == 56)) {
            m767a();
        }
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
                    if (this.f1512m.equals(iHealthDevicesManager.TYPE_BP3M)) {
                        this.f1521v.onNotifyWithAction(this.f1511l, this.f1512m, BpProfile.ACTION_BATTERY_BP, jSONObject.toString());
                        return;
                    } else {
                        this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_BATTERY_BP, jSONObject.toString());
                        return;
                    }
                } catch (JSONException e) {
                    Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                    return;
                }
            case 33:
                try {
                    jSONObject.put("offlinestatus", (returnData[4] & 8) != 0);
                    this.f1521v.onNotify(this.f1511l, this.f1512m, "offlinestatus", jSONObject.toString());
                    return;
                } catch (JSONException e2) {
                    Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                    return;
                }
            case 48:
                if (!this.f1524y) {
                    m758a(48, 4000, 48, 50, 59, 56, 55);
                    this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_ZOREING_BP, jSONObject.toString());
                    return;
                }
                return;
            case 49:
            case 68:
                return;
            case 50:
                if (!this.f1524y) {
                    m758a(50, 4000, 50, 62, 59, 56, 55);
                    this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_ZOREOVER_BP, jSONObject.toString());
                }
                if (this.f1512m.equals(iHealthDevicesManager.TYPE_BP3L)) {
                    m771b((byte) Framer.STDERR_FRAME_PREFIX);
                    return;
                }
                return;
            case 54:
                if (!this.f1525z) {
                    this.f1525z = true;
                    this.f1508a = false;
                    m777e(returnData);
                    return;
                }
                return;
            case 55:
                this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_INTERRUPTED_BP, null);
                return;
            case 56:
                this.f1508a = false;
                i = returnData[0] & 255;
                try {
                    if (!this.f1525z) {
                        this.f1525z = true;
                        jSONObject.put("type", this.f1512m);
                        jSONObject.put("error", i);
                        jSONObject.put("description", m769b(i));
                        this.f1521v.onNotify(this.f1511l, this.f1512m, "error_bp", jSONObject.toString());
                    }
                } catch (JSONException e22) {
                    Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e22.getMessage()});
                }
                if (this.f1512m.equals(iHealthDevicesManager.TYPE_BP3L)) {
                    m771b((byte) 56);
                    return;
                }
                return;
            case 58:
                if (!this.f1508a) {
                    m758a(58, 4000, 58, 48, 62, 60, 60, 59, 56, 55);
                    int i2 = returnData[0] & 255;
                    if (!this.f1524y) {
                        try {
                            jSONObject.put(BpProfile.WHICH_ARM, (returnData[1] & 255) < 90 ? 0 : 1);
                            jSONObject.put("value", i2);
                            this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_ANGLE_BP, jSONObject.toString());
                            return;
                        } catch (JSONException e222) {
                            Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e222.getMessage()});
                            return;
                        }
                    }
                    return;
                }
                return;
            case 59:
                this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_STOP_BP, "");
                return;
            case 60:
                if (!this.f1524y) {
                    m758a(60, 4000, 60, 61, 62, 54, 59, 56, 55, 255);
                    if (this.f1512m.equals(iHealthDevicesManager.TYPE_BP3L)) {
                        m772b(returnData, false);
                        return;
                    } else {
                        m768a(returnData, false);
                        return;
                    }
                }
                return;
            case 61:
                if (!this.f1524y) {
                    m758a(61, 4000, 60, 61, 62, 54, 59, 56, 55, 255);
                    if (this.f1512m.equals(iHealthDevicesManager.TYPE_BP3L)) {
                        m772b(returnData, true);
                        return;
                    } else {
                        m768a(returnData, true);
                        return;
                    }
                }
                return;
            case 62:
                if (!this.f1524y) {
                    m758a(62, 4000, 62, 60, 61, 59, 56, 55, 255);
                    try {
                        jSONObject.put("pressure", (((((returnData[0] & 255) * 256) + (returnData[1] & 255)) * 300) + PL2303Driver.BAUD150) / 4096);
                        this.f1521v.onNotify(this.f1511l, this.f1512m, "online_pressure_bp", jSONObject.toString());
                        return;
                    } catch (JSONException e2222) {
                        Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e2222.getMessage()});
                        return;
                    }
                }
                return;
            case 64:
                i = returnData[1] & 255;
                if (i == 0 && this.getOfflineData) {
                    this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_HISTORICAL_DATA_BP, new JSONObject().toString());
                    return;
                } else if (i != 0 && this.getOfflineData) {
                    getOffLineData();
                    return;
                } else if (!this.getOfflineData) {
                    try {
                        jSONObject.put("offlinenum", i);
                        this.f1521v.onNotify(this.f1511l, this.f1512m, "offlinenum", jSONObject.toString());
                        return;
                    } catch (JSONException e22222) {
                        Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{e22222.getMessage()});
                        return;
                    }
                } else {
                    return;
                }
            case 65:
                this.f1506B = ByteBufferUtil.BufferMerger(this.f1506B, ByteBufferUtil.bytesCutt(2, returnData.length - 1, returnData));
                if (returnData[1] == (byte) 0) {
                    m776d(this.f1506B);
                    this.f1506B = null;
                    return;
                }
                getOffLineData();
                return;
            case 240:
                m775c(returnData);
                return;
            case 251:
                byte[] a = m762a(returnData, this.f1512m, (byte) -95);
                if (this.f1518s.contains("com.jiuan.BPV23")) {
                    a = m762a(returnData, "BPweixin", (byte) -95);
                }
                m758a(252, 4000, 253, 254);
                this.f1510c.packageData(this.f1511l, a);
                return;
            case 253:
                this.f1522w.onConnectionStateChange(this.f1511l, this.f1512m, 1, 0, null);
                if (this.f1505A) {
                    this.f1521v.onNotify(this.f1511l, this.f1512m, BpProfile.ACTION_RESET_BP3M, null);
                    this.f1505A = false;
                    return;
                }
                return;
            case 254:
                this.f1519t.disconnect();
                return;
            case 255:
                this.f1505A = true;
                identify();
                return;
            default:
                Log.p("A1InsSet", Level.WARN, "Exception", new Object[]{"no method"});
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void identify() {
        Log.p("A1InsSet", Level.INFO, "identify", new Object[0]);
        m758a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 4000, 251, 253, 254);
        this.f1510c.packageData(this.f1511l, m760a((byte) -95));
    }

    public void interruptMeasure() {
        Log.p("A1InsSet", Level.INFO, "interruptMeasure", new Object[0]);
        if (this.f1524y) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("error", 401);
                jSONObject.put("description", m769b(401));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f1521v.onNotify(this.f1511l, this.f1512m, "error_bp", jSONObject.toString());
            return;
        }
        this.f1510c.packageData(this.f1511l, new byte[]{(byte) -95, (byte) 55, (byte) 0, (byte) 0, (byte) 0});
        this.f1524y = true;
        this.f1508a = false;
        if (this.f1512m.equals(iHealthDevicesManager.TYPE_BP5) || this.f1512m.equals(iHealthDevicesManager.TYPE_BP7) || this.f1512m.equals(iHealthDevicesManager.TYPE_BP3M)) {
            new Timer().schedule(new C20941(this), 500);
        }
    }

    public void startMeasure() {
        Log.p("A1InsSet", Level.INFO, "startMeasure", new Object[0]);
        this.f1525z = false;
        byte[] bArr = new byte[]{(byte) -95, Framer.STDOUT_FRAME_PREFIX, (byte) 0, (byte) 0, (byte) 75, (byte) 0, (byte) 41, (byte) 0, Ascii.ESC, Ascii.RS};
        this.f1524y = false;
        m758a(49, 4000, 48, 50, 58, 59, 60, 61, 62, 54, 55, 56);
        this.f1510c.packageData(this.f1511l, bArr);
    }
}

package com.ihealth.communication.ins;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.common.base.Ascii;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.BleCommProtocol;
import com.ihealth.communication.base.protocol.BtCommProtocol;
import com.ihealth.communication.cloud.data.BG_InAuthor;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.DataBaseTools;
import com.ihealth.communication.cloud.data.Make_Data_Util;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.cloud.p002a.C2051l;
import com.ihealth.communication.control.Bg5Profile;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.BgSyncData;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.CrcCheck;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import humanize.util.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Bg5InsSet extends IdentifyIns implements NewDataCallback {
    private int f1739A = 0;
    private long f1740B = 0;
    private JSONObject f1741C = null;
    private JSONArray f1742D = null;
    private Timer f1743E;
    private TimerTask f1744F;
    private Context f1745a;
    private BaseCommProtocol f1746b;
    private String f1747c = "";
    private String f1748l = "";
    private long f1749m;
    private String f1750n = "";
    private BaseComm f1751o;
    private InsCallback f1752p;
    private BaseCommCallback f1753q;
    private String f1754r;
    private int f1755s;
    private String f1756t;
    private int f1757u;
    private int f1758v;
    private int f1759w;
    private boolean f1760x = false;
    private boolean f1761y = false;
    private int f1762z = 0;

    class C21131 extends TimerTask {
        final /* synthetic */ Bg5InsSet f1732a;

        C21131(Bg5InsSet this$0) {
            this.f1732a = this$0;
        }

        public void run() {
            Log.p("Bg5InsSet", Level.INFO, "NO Device StartUp Modle ", new Object[0]);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Bg5Profile.SET_BOTTLE_MESSAGE, true);
                jSONObject.put(Bg5Profile.START_MODE_EXTRA, 0);
                this.f1732a.f1752p.onNotify(this.f1732a.f1747c, this.f1732a.f1748l, Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS, jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class C21142 extends Thread {
        final /* synthetic */ Bg5InsSet f1733a;

        C21142(Bg5InsSet this$0) {
            this.f1733a = this$0;
        }

        public void run() {
            if (this.f1733a.f1742D.length() > 0) {
                this.f1733a.m922a();
                this.f1733a.f1742D = BgSyncData.processData(this.f1733a.f1745a, this.f1733a.f1747c, this.f1733a.f1742D);
                this.f1733a.m929c();
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Bg5Profile.HISTORICAL_DATA_BG, Bg5InsSet.arrayToJson("history", this.f1733a.f1742D));
                this.f1733a.f1752p.onNotify(this.f1733a.f1747c, this.f1733a.f1748l, Bg5Profile.ACTION_HISTORICAL_DATA_BG, jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f1733a.f1742D = null;
            BgSyncData.uploadSyncTime(this.f1733a.f1745a, this.f1733a.f1747c, this.f1733a.f1740B == 0 ? C2051l.m390b() : this.f1733a.f1740B);
        }
    }

    class C21153 extends TimerTask {
        final /* synthetic */ Bg5InsSet f1734a;

        C21153(Bg5InsSet this$0) {
            this.f1734a = this$0;
        }

        public void run() {
            this.f1734a.linkHold();
        }
    }

    public class BGData {
        String f1735a;
        int f1736b;
        int f1737c;
        final /* synthetic */ Bg5InsSet f1738d;

        public BGData(Bg5InsSet this$0) {
            this.f1738d = this$0;
        }

        public BGData(Bg5InsSet this$0, String bGData_Date, int bGData_Value, int bGData_TimeProofing) {
            this.f1738d = this$0;
            this.f1735a = bGData_Date;
            this.f1736b = bGData_Value;
            this.f1737c = bGData_TimeProofing;
        }

        public String getBGData_Date() {
            return this.f1735a;
        }

        public int getBGData_TimeProofing() {
            return this.f1737c;
        }

        public int getBGData_Value() {
            return this.f1736b;
        }

        public void setBGData_Date(String bGData_Date) {
            this.f1735a = bGData_Date;
        }

        public void setBGData_TimeProofing(int bGData_TimeProofing) {
            this.f1737c = bGData_TimeProofing;
        }

        public void setBGData_Value(int bGData_Value) {
            this.f1736b = bGData_Value;
        }
    }

    public Bg5InsSet(String userName, BaseComm com, Context context, String mac, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.f1750n = userName;
        this.f1751o = com;
        if (type.equals(iHealthDevicesManager.TYPE_BG5l)) {
            this.f1746b = new BleCommProtocol(context, com, mac, (byte) -94, this);
        } else {
            this.f1746b = new BtCommProtocol(com, this);
        }
        this.f1745a = context;
        this.f1747c = mac;
        this.f1748l = type;
        this.f1752p = insCallback;
        this.f1753q = baseCommCallback;
        this.f1760x = false;
        m759a(insCallback, mac, type, com);
    }

    private JSONArray m920a(JSONArray jSONArray, String[] strArr) {
        int i;
        int i2 = 0;
        ArrayList arrayList = new ArrayList();
        for (i = 0; i < strArr.length / 3; i++) {
            BGData bGData = new BGData(this);
            bGData.setBGData_Date(strArr[i * 3]);
            bGData.setBGData_Value(Integer.parseInt(strArr[(i * 3) + 1]));
            bGData.setBGData_TimeProofing(Integer.parseInt(strArr[(i * 3) + 2]));
            arrayList.add(bGData);
        }
        try {
            int size = arrayList.size();
            while (i2 < size) {
                BGData bGData2 = (BGData) arrayList.get(i2);
                String bGData_Date = bGData2.getBGData_Date();
                int bGData_Value = bGData2.getBGData_Value();
                i = bGData2.getBGData_TimeProofing();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("date", bGData_Date);
                jSONObject.put("value", bGData_Value);
                jSONObject.put("timeProofing", i);
                jSONObject.put("dataID", C2051l.m387a(this.f1747c, C2051l.m391b(bGData_Date), Integer.valueOf(bGData_Value).intValue()));
                jSONArray.put(jSONObject);
                i2++;
            }
            return jSONArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONArray m921a(String[] strArr) {
        int i;
        int i2 = 0;
        ArrayList arrayList = new ArrayList();
        for (i = 0; i < strArr.length / 3; i++) {
            BGData bGData = new BGData(this);
            bGData.setBGData_Date(strArr[i * 3]);
            bGData.setBGData_Value(Integer.parseInt(strArr[(i * 3) + 1]));
            bGData.setBGData_TimeProofing(Integer.parseInt(strArr[(i * 3) + 2]));
            arrayList.add(bGData);
        }
        try {
            JSONArray jSONArray = new JSONArray();
            int size = arrayList.size();
            while (i2 < size) {
                BGData bGData2 = (BGData) arrayList.get(i2);
                String bGData_Date = bGData2.getBGData_Date();
                int bGData_Value = bGData2.getBGData_Value();
                i = bGData2.getBGData_TimeProofing();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("date", bGData_Date);
                jSONObject.put("value", bGData_Value);
                jSONObject.put("timeProofing", i);
                jSONObject.put("dataID", C2051l.m387a(this.f1747c, C2051l.m391b(bGData_Date), Integer.valueOf(bGData_Value).intValue()));
                jSONArray.put(jSONObject);
                i2++;
            }
            return jSONArray;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void m922a() {
        m929c();
        this.f1743E = new Timer();
        this.f1744F = new C21153(this);
        this.f1743E.schedule(this.f1744F, 5000, 5000);
    }

    private byte[] m923a(String str, int i, int i2) {
        int i3;
        byte[] bArr = new byte[126];
        byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(str);
        byte[] bArr2 = null;
        if (i == 2) {
            if (i2 == 2) {
                bArr2 = ByteBufferUtil.hexStringToByte("02323C64323C01006400FA00E103016800F000F001F4025814012C0000A0002800A003D100320046005A006E0082009600AA00B400E601040118012C01400168017C0190064D05F905A8055C051304CF048F047103E803A203790353033202FC02E702D71027383D4E6F646464646464646464640319010B0701");
            } else if (i2 == 3) {
                bArr2 = ByteBufferUtil.hexStringToByte("02323C64323C01006400FA00E1030168003C003C01F4025814015E3200A0005A00A0032000320046005A006E0082009600AA00B400E60104011801400168017C0190019A04C604A8048B04700456043E0428041D03E803E803E803B303A3039D0399039810273D464E6F646464646464646464640319010B0701");
            }
        } else if (i2 == 2) {
            bArr2 = ByteBufferUtil.hexStringToByte("02323C64323C01006400FA00E1030168003C003C01F4025814015E3200A0005A00A0032000320046005A006E0082009600AA00B400E60104011801400168017C0190019A04C604A8048B04700456043E0428041D03E803E803E803B303A3039D0399039810273D464E6F646464646464646464640319010B0701");
        } else if (i2 == 3) {
            bArr2 = ByteBufferUtil.hexStringToByte("02323C64323C01006400FA00E1030168003C003C01F4025814015E3200A0005A00A0032000320046005A006E0082009600AA00B400E60104011801400168017C0190019A04C604A8048B04700456043E0428041D03E803E803E803B303A3039D0399039810273D464E6F646464646464646464640319010B0701");
        }
        for (i3 = 0; i3 < 6; i3++) {
            bArr[i3] = hexStringToByte[i3];
        }
        bArr[6] = (byte) 1;
        i3 = (int) (((((double) (hexStringToByte[6] & 255)) * 0.1d) * 1000.0d) / 20.0d);
        bArr[7] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i3) >> 8);
        bArr[8] = (byte) (i3 & 255);
        i3 = (int) (((((double) (hexStringToByte[7] & 255)) * 0.1d) * 1000.0d) / 20.0d);
        bArr[9] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i3) >> 8);
        bArr[10] = (byte) (i3 & 255);
        i3 = (int) (((((double) (hexStringToByte[8] & 255)) * 0.1d) * 1000.0d) / 20.0d);
        bArr[11] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i3) >> 8);
        bArr[12] = (byte) (i3 & 255);
        for (i3 = 13; i3 < 30; i3++) {
            bArr[i3] = bArr2[i3];
        }
        i3 = (int) (((((double) (hexStringToByte[9] & 255)) * 0.1d) * 1000.0d) / 20.0d);
        bArr[30] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i3) >> 8);
        bArr[31] = (byte) (i3 & 255);
        bArr[32] = hexStringToByte[10];
        bArr[33] = hexStringToByte[11];
        for (i3 = 34; i3 < 106; i3++) {
            bArr[i3] = bArr2[i3];
        }
        bArr[106] = hexStringToByte[12];
        bArr[107] = hexStringToByte[13];
        bArr[108] = hexStringToByte[14];
        bArr[109] = hexStringToByte[15];
        bArr[110] = hexStringToByte[16];
        bArr[111] = hexStringToByte[17];
        bArr[112] = hexStringToByte[18];
        bArr[113] = hexStringToByte[19];
        bArr[114] = hexStringToByte[20];
        bArr[115] = hexStringToByte[21];
        bArr[116] = bArr2[116];
        bArr[117] = hexStringToByte[22];
        bArr[118] = hexStringToByte[23];
        Integer valueOf = Integer.valueOf((hexStringToByte[24] & 254) >> 1);
        Integer valueOf2 = Integer.valueOf(((hexStringToByte[24] & 1) * 8) + ((hexStringToByte[25] & 224) >> 5));
        Integer valueOf3 = Integer.valueOf(hexStringToByte[25] & 31);
        bArr[119] = valueOf.byteValue();
        bArr[120] = valueOf2.byteValue();
        bArr[121] = valueOf3.byteValue();
        int cRCValue = new CrcCheck(ByteBufferUtil.hexByteToInt(bArr, 122)).getCRCValue();
        bArr[122] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & cRCValue) >> 8);
        bArr[123] = (byte) (cRCValue & 255);
        bArr[124] = (byte) 0;
        bArr[125] = (byte) 1;
        return bArr;
    }

    private static int[] m924a(byte[] bArr, int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = bArr[i2];
            if (iArr[i2] < 0) {
                iArr[i2] = iArr[i2] + 256;
            }
        }
        return iArr;
    }

    public static JSONObject arrayToJson(String objName, JSONArray array) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(objName, array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void m926b(byte b) {
        try {
            if (this.f1748l.equals(iHealthDevicesManager.TYPE_BG5)) {
                this.f1746b.packageDataAsk(new byte[]{(byte) -94});
                return;
            }
            this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, b});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void m927b(int i) {
        byte[] bArr = new byte[5];
        byte b = (byte) 64;
        if (iHealthDevicesManager.TYPE_BG5l.equals(this.f1748l)) {
            b = (byte) 75;
        }
        bArr[0] = (byte) -94;
        bArr[1] = b;
        bArr[2] = (byte) 0;
        bArr[3] = (byte) 0;
        bArr[4] = (byte) i;
        this.f1746b.packageData(this.f1747c, bArr);
    }

    private void m929c() {
        if (this.f1743E != null) {
            this.f1743E.cancel();
            this.f1743E = null;
        }
        if (this.f1744F != null) {
            this.f1744F.cancel();
            this.f1744F = null;
        }
    }

    private String[] m930c(byte[] bArr) {
        int i = bArr[0] & 255;
        byte[] bArr2 = new byte[(bArr.length - 1)];
        for (int i2 = 1; i2 < bArr.length; i2++) {
            bArr2[i2 - 1] = bArr[i2];
        }
        String[] strArr = new String[(i * 3)];
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            String valueOf = String.valueOf((bArr2[i4 + 0] & 255) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
            String valueOf2 = (bArr2[i4 + 1] & 255) < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(bArr2[i4 + 1] & 255) : String.valueOf(bArr2[i4 + 1] & 255);
            String valueOf3 = (bArr2[i4 + 2] & 255) < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(bArr2[i4 + 2] & 255) : String.valueOf(bArr2[i4 + 2] & 255);
            String valueOf4 = (bArr2[i4 + 3] & 255) < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(bArr2[i4 + 3] & 255) : String.valueOf(bArr2[i4 + 3] & 255);
            valueOf2 = valueOf + "-" + valueOf2 + "-" + valueOf3 + Constants.SPACE + valueOf4 + ":" + ((bArr2[i4 + 4] & 255) < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + String.valueOf(bArr2[i4 + 4] & 255) : String.valueOf(bArr2[i4 + 4] & 255)) + ":" + (i3 < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + i3 : "" + i3);
            int i5 = ((bArr2[i4 + 5] & 255) * 256) + (bArr2[i4 + 6] & 255);
            strArr[i3 * 3] = valueOf2;
            strArr[(i3 * 3) + 1] = i5 + "";
            strArr[(i3 * 3) + 2] = ((bArr2[i4 + 7] >> 7) & 1) + "";
            i3++;
            i4 += 8;
        }
        return strArr;
    }

    public void deleteEE() {
        Log.p("Bg5InsSet", Level.INFO, "deleteEE", new Object[0]);
        this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, (byte) 67, (byte) 0, (byte) 0, (byte) 0});
    }

    public void destroy() {
        this.f1753q = null;
        this.f1752p = null;
        this.f1745a = null;
        if (this.f1746b != null) {
            this.f1746b.destroy();
        }
        this.f1746b = null;
        m929c();
    }

    public int four2two(int year) {
        String str = year + "";
        String str2 = "";
        return str.length() > 2 ? Integer.parseInt(str.substring(2)) : year;
    }

    public void getBattery() {
        Log.p("Bg5InsSet", Level.INFO, "getBattery", new Object[0]);
        if (this.f1748l.equals(iHealthDevicesManager.TYPE_BG5)) {
            try {
                if (iHealthDevicesManager.getInstance().getEE(this.f1747c).compareTo("6.0.0") < 0) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("battery", -1);
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_BATTERY_BG, jSONObject.toString());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        m758a(38, 4000, 38);
        this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, (byte) 38, (byte) 0, (byte) 0, (byte) 0});
    }

    public void getBottleId() {
        Log.p("Bg5InsSet", Level.INFO, "getBottleId", new Object[0]);
        this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, (byte) 46, (byte) 0, (byte) 0, (byte) 0, (byte) 0});
    }

    public void getBottleMessage() {
        Log.p("Bg5InsSet", Level.INFO, "getBottleMessage", new Object[0]);
        this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, (byte) 70, (byte) 0, (byte) 0, (byte) 0});
    }

    public void getBtEE() {
        this.f1751o.sendData(this.f1747c, ByteBufferUtil.hexStringToByte("0D0A2B495353435F525F4545503A3031386130360D0A"));
    }

    public String getErrMessageFromErrNum(int errNum) {
        String str = "unKnow message";
        switch (errNum) {
            case 0:
                return "Battery is low.";
            case 1:
                return "Glucose test result is out of the measurement range.";
            case 2:
                return "Unknown interference detected, please repeat the test.";
            case 3:
                return "Strip is used or unknown moisture detected, discard the test strip and repeat the test with a new strip.";
            case 4:
                return "Reading transmission error. Repeat the test with a new test strip. If the problem persists, contact iHealth customer service for assistance.";
            case 5:
                return "The environmental temperature is beyond normal range, place the meter at room temperature for at least 30 minutes, then repeat the test.";
            case 6:
                return "The environmental temperature is beyond normal range, place the meter at room temperature for at least 30 minutes, then repeat the test.";
            case 7:
                return "Test strip coding error.";
            case 8:
                return "Communication error, press\"START\" or rescan the code to repeat the test.";
            case 9:
                return "Strip removed in the middle of reading, repeat the test with a new strip.";
            case 10:
                return "Insert a new test strip and repeat the test.";
            case 11:
                return "Cannot write to SN or KEY.";
            case 12:
                return "Please set time.";
            case 13:
                return "0 test strips remaining.";
            case 14:
                return "Test strip expired.";
            case 15:
                return "Unplug the charging cable before testing.";
            case 18:
                return "Unplug the charging cable before read the history data.";
            case 19:
                return "Charging line is inserted.";
            case 20:
                return "Charging line pull out.";
            case 21:
                return "The bluetooth module failure.";
            case 112:
                return "Device don't support to query energy.";
            case 400:
                return "Parameters out of range.";
            default:
                return "unKnow error message";
        }
    }

    public void getIdps() {
        this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, (byte) 63});
    }

    public void getTime() {
        Log.p("Bg5InsSet", Level.INFO, "getTime", new Object[0]);
        this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, (byte) 74, (byte) 0, (byte) 0, (byte) 0});
    }

    public void haveNewData(int what, int stateId, byte[] returnData) {
        int parseInt;
        int parseInt2;
        NumberFormatException e;
        int i;
        int i2;
        JSONObject jSONObject;
        int i3 = 0;
        Log.p("Bg5InsSet", Level.DEBUG, "haveNewData", new Object[]{Integer.toHexString(what), Integer.valueOf(stateId), ByteBufferUtil.Bytes2HexString(returnData)});
        m757a(what);
        JSONObject jSONObject2 = new JSONObject();
        int i4;
        String format;
        switch (what) {
            case 33:
            case 73:
                this.f1740B = C2051l.m390b();
                try {
                    jSONObject2.put(Bg5Profile.SET_TIME, true);
                    this.f1752p.onNotify(this.f1747c, this.f1748l, "action_set_time", jSONObject2.toString());
                    return;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    return;
                }
            case 35:
                try {
                    jSONObject2.put(Bg5Profile.SET_UNIT, true);
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_SET_UNIT, jSONObject2.toString());
                    return;
                } catch (JSONException e22) {
                    e22.printStackTrace();
                    return;
                }
            case 37:
                if (this.f1748l.equals(iHealthDevicesManager.TYPE_BG5) && this.f1760x) {
                    new Timer().schedule(new C21131(this), 500);
                    return;
                }
                return;
            case 38:
                i4 = returnData[0] & 255;
                if (i4 <= 0 || i4 > 100) {
                    i4 = 255;
                }
                try {
                    jSONObject2.put("battery", i4);
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_BATTERY_BG, jSONObject2.toString());
                    return;
                } catch (JSONException e222) {
                    e222.printStackTrace();
                    return;
                }
            case 44:
                m926b((byte) what);
                try {
                    i4 = returnData[0] & 255;
                    jSONObject2.put(Bg5Profile.ERROR_NUM_BG, i4);
                    jSONObject2.put("description", getErrMessageFromErrNum(i4));
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_ERROR_BG, jSONObject2.toString());
                    return;
                } catch (JSONException e2222) {
                    e2222.printStackTrace();
                    return;
                }
            case 45:
                this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_SET_BOTTLE_ID_SUCCESS, jSONObject2.toString());
                if (this.f1761y) {
                    this.f1755s = this.f1755s > 255 ? 255 : this.f1755s;
                    if (TextUtils.isEmpty(this.f1756t)) {
                        setBottleMessage(this.f1754r, this.f1755s, (byte) null, (byte) null, (byte) null);
                        return;
                    }
                    if (this.f1756t.split("-").length == 3) {
                        try {
                            parseInt = Integer.parseInt(this.f1756t.split("-")[0]);
                            try {
                                parseInt2 = Integer.parseInt(this.f1756t.split("-")[1]);
                            } catch (NumberFormatException e3) {
                                e = e3;
                                parseInt2 = 0;
                                e.printStackTrace();
                                i4 = 0;
                                if (judgeTimeStr(parseInt, parseInt2, i4)) {
                                    i3 = 1;
                                    i = parseInt2;
                                    i2 = parseInt;
                                } else {
                                    i = parseInt2;
                                    i2 = parseInt;
                                }
                                if (i3 != 0) {
                                    setBottleMessage(this.f1754r, this.f1755s, (byte) four2two(i2), (byte) i, (byte) i4);
                                    return;
                                }
                                jSONObject = new JSONObject();
                                try {
                                    jSONObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                                    jSONObject.put("description", "Please input a right time string");
                                } catch (JSONException e22222) {
                                    e22222.printStackTrace();
                                }
                                this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
                                return;
                            }
                            try {
                                i4 = Integer.parseInt(this.f1756t.split("-")[2]);
                            } catch (NumberFormatException e4) {
                                e = e4;
                                e.printStackTrace();
                                i4 = 0;
                                if (judgeTimeStr(parseInt, parseInt2, i4)) {
                                    i = parseInt2;
                                    i2 = parseInt;
                                } else {
                                    i3 = 1;
                                    i = parseInt2;
                                    i2 = parseInt;
                                }
                                if (i3 != 0) {
                                    setBottleMessage(this.f1754r, this.f1755s, (byte) four2two(i2), (byte) i, (byte) i4);
                                    return;
                                }
                                jSONObject = new JSONObject();
                                jSONObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                                jSONObject.put("description", "Please input a right time string");
                                this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
                                return;
                            }
                        } catch (NumberFormatException e5) {
                            e = e5;
                            parseInt2 = 0;
                            parseInt = 0;
                            e.printStackTrace();
                            i4 = 0;
                            if (judgeTimeStr(parseInt, parseInt2, i4)) {
                                i3 = 1;
                                i = parseInt2;
                                i2 = parseInt;
                            } else {
                                i = parseInt2;
                                i2 = parseInt;
                            }
                            if (i3 != 0) {
                                jSONObject = new JSONObject();
                                jSONObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                                jSONObject.put("description", "Please input a right time string");
                                this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
                                return;
                            }
                            setBottleMessage(this.f1754r, this.f1755s, (byte) four2two(i2), (byte) i, (byte) i4);
                            return;
                        }
                        try {
                            if (judgeTimeStr(parseInt, parseInt2, i4)) {
                                i3 = 1;
                                i = parseInt2;
                                i2 = parseInt;
                            } else {
                                i = parseInt2;
                                i2 = parseInt;
                            }
                        } catch (Exception e6) {
                            e6.printStackTrace();
                            return;
                        }
                    }
                    i4 = 0;
                    i = 0;
                    i2 = 0;
                    if (i3 != 0) {
                        jSONObject = new JSONObject();
                        jSONObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                        jSONObject.put("description", "Please input a right time string");
                        this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
                        return;
                    }
                    setBottleMessage(this.f1754r, this.f1755s, (byte) four2two(i2), (byte) i, (byte) i4);
                    return;
                }
                return;
            case 46:
                try {
                    jSONObject2.put("bottleid", (((((((long) (returnData[0] & 255)) * 256) * 256) * 256) + ((((long) (returnData[1] & 255)) * 256) * 256)) + (((long) (returnData[2] & 255)) * 256)) + ((long) (returnData[3] & 255)));
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_GET_BOTTLEID, jSONObject2.toString());
                    return;
                } catch (JSONException e222222) {
                    e222222.printStackTrace();
                    return;
                }
            case 49:
                try {
                    jSONObject2.put(Bg5Profile.START_MEASURE, true);
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_START_MEASURE, jSONObject2.toString());
                    return;
                } catch (JSONException e2222222) {
                    e2222222.printStackTrace();
                    return;
                }
            case 51:
                m926b((byte) what);
                this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_STRIP_IN, jSONObject2.toString());
                return;
            case 52:
                m926b((byte) what);
                this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_GET_BLOOD, jSONObject2.toString());
                return;
            case 53:
                m926b((byte) what);
                this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_STRIP_OUT, jSONObject2.toString());
                return;
            case 54:
                m926b((byte) what);
                parseInt2 = ((returnData[0] & 255) * 256) + (returnData[1] & 255);
                try {
                    jSONObject2.put("result", parseInt2);
                    jSONObject2.put("dataID", C2051l.m387a(this.f1747c, C2051l.m390b(), parseInt2));
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_ONLINE_RESULT_BG, jSONObject2.toString());
                    this.f1760x = true;
                } catch (JSONException e22222222) {
                    e22222222.printStackTrace();
                }
                if (C2041b.f505a) {
                    new DataBaseTools(this.f1745a).addData(DataBaseConstants.TABLE_TB_BGRESULT, Make_Data_Util.makeDataSingleBg(this.f1750n, parseInt2, this.f1748l, this.f1747c, this.f1749m));
                    BG_InAuthor instance = BG_InAuthor.getInstance();
                    instance.initAuthor(this.f1745a, this.f1750n);
                    instance.run();
                    return;
                }
                return;
            case 57:
                m926b((byte) what);
                return;
            case 58:
                m926b((byte) what);
                Log.p("Bg5InsSet", Level.INFO, "Device StartUp Modle " + returnData[2], new Object[0]);
                try {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put(Bg5Profile.SET_BOTTLE_MESSAGE, true);
                    jSONObject3.put(Bg5Profile.START_MODE_EXTRA, returnData[2]);
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS, jSONObject3.toString());
                    return;
                } catch (JSONException e222222222) {
                    e222222222.printStackTrace();
                    return;
                }
            case 64:
            case 75:
                String[] c = m930c(returnData);
                if (this.f1742D == null) {
                    this.f1742D = m921a(c);
                } else {
                    this.f1742D = m920a(this.f1742D, c);
                }
                if (this.f1762z > 0) {
                    m927b(this.f1739A);
                    this.f1762z--;
                    this.f1739A++;
                    return;
                } else if (iHealthDevicesManager.getInstance().getEE(this.f1747c).equals("3.0.0")) {
                    new C21142(this).start();
                    return;
                } else {
                    try {
                        jSONObject2.put(Bg5Profile.HISTORICAL_DATA_BG, arrayToJson("history", this.f1742D));
                        this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_HISTORICAL_DATA_BG, jSONObject2.toString());
                    } catch (JSONException e2222222222) {
                        e2222222222.printStackTrace();
                    }
                    this.f1742D = null;
                    return;
                }
            case 66:
                i4 = ((returnData[0] & 255) * 256) + (returnData[1] & 255);
                if (this.f1748l.equals(iHealthDevicesManager.TYPE_BG5l)) {
                    this.f1762z = (i4 / 17) + 1;
                } else {
                    this.f1762z = (i4 / 30) + 1;
                }
                this.f1739A = 0;
                try {
                    jSONObject2.put(Bg5Profile.HISTORICAL_NUM_BG, i4);
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_HISTORICAL_NUM_BG, jSONObject2.toString());
                } catch (JSONException e22222222222) {
                    e22222222222.printStackTrace();
                }
                if (this.f1762z != 0) {
                    m927b(this.f1739A);
                    this.f1762z--;
                    this.f1739A++;
                    return;
                }
                return;
            case 67:
                try {
                    jSONObject2.put(Bg5Profile.DELETE_HISTORICAL_DATA, true);
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_DELETE_HISTORICAL_DATA, jSONObject2.toString());
                    return;
                } catch (JSONException e222222222222) {
                    e222222222222.printStackTrace();
                    return;
                }
            case 69:
                try {
                    jSONObject2.put(Bg5Profile.KEEP_LINK, true);
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_KEEP_LINK, jSONObject2.toString());
                    return;
                } catch (JSONException e2222222222222) {
                    e2222222222222.printStackTrace();
                    return;
                }
            case 70:
                i4 = returnData[117] & 255;
                parseInt2 = (returnData[119] & 255) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
                i3 = returnData[120] & 255;
                i = returnData[121] & 255;
                Calendar instance2 = Calendar.getInstance();
                instance2.clear();
                instance2.set(1, parseInt2);
                instance2.set(2, i3 - 1);
                instance2.set(5, i);
                format = new SimpleDateFormat(DateTimeFormat.DATE_PATTERN_1).format(instance2.getTime());
                try {
                    jSONObject2.put(Bg5Profile.GET_USENUM, i4);
                    jSONObject2.put(Bg5Profile.GET_EXPIRECTIME, format + "");
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_GET_CODEINFO, jSONObject2.toString());
                    return;
                } catch (JSONException e22222222222222) {
                    e22222222222222.printStackTrace();
                    return;
                }
            case 74:
                format = String.format("%04d-%02d-%02d %02d:%02d:%02d", new Object[]{Integer.valueOf(returnData[0] + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE), Byte.valueOf(returnData[1]), Byte.valueOf(returnData[2]), Byte.valueOf(returnData[3]), Byte.valueOf(returnData[4]), Byte.valueOf(returnData[5])});
                float f = (returnData[6] & 128) == 0 ? ((float) returnData[6]) + (((float) returnData[7]) / 100.0f) : 0.0f - (((float) (returnData[6] & 127)) + (((float) returnData[7]) / 100.0f));
                try {
                    jSONObject2.put(Bg5Profile.GET_TIME, format);
                    jSONObject2.put(Bg5Profile.GET_TIMEZONE, (double) f);
                    this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_GET_TIME, jSONObject2.toString());
                    return;
                } catch (JSONException e222222222222222) {
                    e222222222222222.printStackTrace();
                    return;
                }
            case 251:
                byte[] a = m762a(returnData, this.f1748l, (byte) -94);
                if (this.f1748l.equals(iHealthDevicesManager.TYPE_BG5l)) {
                    a = m762a(returnData, iHealthDevicesManager.TYPE_BG5l, (byte) -94);
                }
                m758a(252, 4000, 253, 254);
                this.f1746b.packageData(null, a);
                return;
            case 253:
                this.f1753q.onConnectionStateChange(this.f1747c, this.f1748l, 1, 0, null);
                return;
            case 254:
                this.f1751o.disconnect();
                return;
            case 255:
                identify();
                return;
            default:
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }

    public void identify() {
        m758a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 4000, 251, 253, 254);
        this.f1746b.packageData(this.f1747c, m760a((byte) -94));
    }

    public boolean judgeTimeStr(int year, int month, int day) {
        if (year < CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE || month < 1 || month > 12) {
            return false;
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day < 1 || day > 31) {
                return false;
            }
        } else if (day < 1) {
            return false;
        } else {
            if (day > 30) {
                return false;
            }
        }
        if (!(year % 100 == 0 && year % 400 == 0) && (year % 100 == 0 || year % 4 != 0)) {
            if (month == 2) {
                if (day < 1) {
                    return false;
                }
                if (day > 28) {
                    return false;
                }
            }
        } else if (month == 2 && (day < 1 || day > 29)) {
            return false;
        }
        return true;
    }

    public void linkHold() {
        Log.p("Bg5InsSet", Level.INFO, "linkHold", new Object[0]);
        this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, (byte) 69, (byte) 0, (byte) 0, (byte) 0});
    }

    public void readEENum() {
        Log.p("Bg5InsSet", Level.INFO, "readEENum", new Object[0]);
        this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, (byte) 66, (byte) 0, (byte) 0, (byte) 0});
    }

    public void setBottleId(int deviceType, int stripType, int measureType, long bottleId, String QRCode, int stripNum, String overDate, boolean innerFlag) {
        this.f1757u = deviceType;
        this.f1758v = stripType;
        this.f1759w = measureType;
        setBottleId(bottleId, QRCode, stripNum, overDate, innerFlag);
    }

    public void setBottleId(long bottleId, String QRCode, int stripNum, String overDate, boolean innerFlag) {
        Log.p("Bg5InsSet", Level.INFO, "setBottleId", new Object[]{Long.valueOf(bottleId), QRCode, Integer.valueOf(stripNum), overDate, Boolean.valueOf(innerFlag)});
        this.f1761y = innerFlag;
        this.f1749m = bottleId;
        this.f1754r = QRCode;
        this.f1755s = stripNum;
        this.f1756t = overDate;
        if (innerFlag) {
            Object obj = 1;
            Object obj2 = "";
            if (stripNum < 1 || stripNum > 255) {
                obj = 0;
                obj2 = "parameter(stripNum) should be in the range [1, 255].";
            }
            if (obj == 1) {
                if (overDate == null) {
                    obj = null;
                    obj2 = "parameter(overDate) should not be null.";
                } else if (overDate.split("-").length != 3) {
                    obj = null;
                    obj2 = "parameter(overDate) should be the format(yyyy-MM-dd).";
                }
            }
            if (obj == null) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(Bg5Profile.ERROR_NUM_BG, 400);
                    jSONObject.put("description", obj2);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                this.f1752p.onNotify(this.f1747c, this.f1748l, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
                return;
            }
        }
        m758a(45, 4000, 45);
        byte[] intTo4Byte = ByteBufferUtil.intTo4Byte(bottleId);
        this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, Framer.STDIN_FRAME_PREFIX, intTo4Byte[0], intTo4Byte[1], intTo4Byte[2], intTo4Byte[3]});
    }

    public void setBottleMessage(String qr, int leftnum, byte year, byte month, byte day) {
        int i = 0;
        byte[] bArr = new byte[126];
        byte[] bArr2 = new byte[128];
        bArr2[0] = (byte) -94;
        bArr2[1] = (byte) 37;
        if (this.f1758v == 1) {
            byte[] a = m923a(qr, this.f1759w, this.f1757u);
            if (leftnum > 0) {
                a[117] = (byte) leftnum;
            }
            if (!(year == (byte) 0 || month == (byte) 0 || day == (byte) 0)) {
                a[119] = year;
                a[120] = month;
                a[121] = day;
            }
            int cRCValue = new CrcCheck(m924a(a, 122)).getCRCValue();
            a[122] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & cRCValue) >> 8);
            a[123] = (byte) (cRCValue & 255);
            while (i < 126) {
                bArr2[i + 2] = a[i];
                i++;
            }
        } else if (this.f1758v == 2) {
            Object obj = null;
            if (this.f1759w == 1) {
                if (this.f1757u == 2) {
                    obj = ByteBufferUtil.hexStringToByte("02323C46323C01006400FA00E103016800F000F001F4025814015E3200A0002800A003D100320046005A006E0082009600AA00B400E60104011801400168017C0190019A04CA04B10497047D046304490430042303E203BB03A2036E033A0321030702FA0C27383D4E6F2E4D6577B6144E6B91FA03FF010B070104F7");
                } else if (this.f1757u == 3) {
                    obj = ByteBufferUtil.hexStringToByte("0E323C46323C01006400FA00E103016800F000F001F4025814015E3200A0002800A003D100320046005A006E0082009600AA00B400E60104011801400168017C0190019A04CA04B10497047D046304490430042303E203BB03A2036E033A0321030702FA0C27383D4E6F2E4D6577B6144E6B91FA03FF010B0701021C");
                }
            } else if (this.f1759w == 2) {
                if (this.f1757u == 2) {
                    obj = ByteBufferUtil.hexStringToByte("02323C46323C01006400FA00E103016800F000F001F4025814015E3200A0002800A003D100320046005A006E0082009600AA00B400E601040118012C01400168017C01900584054F051C04EB04BC04900467045303E803C903AD0393037B0353034303350C27383D4E6F2E4D6577B6144E6B91FA03FF010B0701061C");
                } else if (this.f1757u == 3) {
                    obj = ByteBufferUtil.hexStringToByte("0E323C46323C01006400FA00E103016800F000F001F4025814015E3200A0002800A003D100320046005A006E0082009600AA00B400E60104011801400168017C0190019A04CA04B10497047D046304490430042303E203BB03A2036E033A0321030702FA0C27383D4E6F2E4D6577B6144E6B91FA03FF010B0701021C");
                }
            }
            System.arraycopy(obj, 0, bArr, 0, 122);
            if (leftnum > 0) {
                bArr[117] = (byte) leftnum;
            } else {
                bArr[117] = (byte) -1;
            }
            if (year == (byte) 0 || month == (byte) 0 || day == (byte) 0) {
                bArr[119] = (byte) 99;
                bArr[120] = Ascii.FF;
                bArr[121] = (byte) 16;
            } else {
                bArr[119] = year;
                bArr[120] = month;
                bArr[121] = day;
            }
            r0 = new CrcCheck(m924a(bArr, 122)).getCRCValue();
            bArr[122] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & r0) >> 8);
            bArr[123] = (byte) (r0 & 255);
            bArr[124] = (byte) 0;
            bArr[125] = (byte) 1;
            while (i < 126) {
                bArr2[i + 2] = bArr[i];
                i++;
            }
        } else {
            bArr = m923a(qr, this.f1759w, this.f1757u);
            if (leftnum > 0 && year > (byte) 0 && month > (byte) 0 && day > (byte) 0) {
                bArr[117] = (byte) leftnum;
                bArr[119] = year;
                bArr[120] = month;
                bArr[121] = day;
            }
            r0 = new CrcCheck(m924a(bArr, 122)).getCRCValue();
            bArr[122] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & r0) >> 8);
            bArr[123] = (byte) (r0 & 255);
            for (r0 = 0; r0 < 126; r0++) {
                bArr2[r0 + 2] = bArr[r0];
            }
        }
        this.f1746b.packageData(this.f1747c, bArr2);
    }

    public void setTime() {
        Log.p("Bg5InsSet", Level.INFO, "setTime", new Object[0]);
        byte[] bArr = new byte[8];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        Integer valueOf = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[0].split("-")[0]) - 2000);
        Integer valueOf2 = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[0].split("-")[1]));
        Integer valueOf3 = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[0].split("-")[2]));
        Integer valueOf4 = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[1].split(":")[0]));
        Integer valueOf5 = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[1].split(":")[1]));
        Integer valueOf6 = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[1].split(":")[2]));
        m758a(33, 4000, 33);
        bArr[0] = (byte) -94;
        bArr[1] = (byte) 33;
        bArr[2] = valueOf.byteValue();
        bArr[3] = valueOf2.byteValue();
        bArr[4] = valueOf3.byteValue();
        bArr[5] = valueOf4.byteValue();
        bArr[6] = valueOf5.byteValue();
        bArr[7] = valueOf6.byteValue();
        this.f1746b.packageData(this.f1747c, bArr);
    }

    public void setTimeBG5L() {
        Log.p("Bg5InsSet", Level.INFO, "setTimeBG5L", new Object[0]);
        byte[] bArr = new byte[10];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_TIME_PATTERN_1);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String format = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        Integer valueOf = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[0].split("-")[0]) - 2000);
        Integer valueOf2 = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[0].split("-")[1]));
        Integer valueOf3 = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[0].split("-")[2]));
        Integer valueOf4 = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[1].split(":")[0]));
        Integer valueOf5 = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[1].split(":")[1]));
        Integer valueOf6 = Integer.valueOf(Integer.parseInt(format.split(Constants.SPACE)[1].split(":")[2]));
        int rawOffset = TimeZone.getDefault().getRawOffset() / 1000;
        if (rawOffset > 0) {
            bArr[8] = (byte) (rawOffset / 3600);
            bArr[9] = (byte) (((rawOffset % 3600) * 100) / 3600);
        } else {
            bArr[8] = (byte) ((Math.abs(rawOffset) / 3600) | 128);
            bArr[9] = (byte) (((Math.abs(rawOffset) % 3600) * 100) / 3600);
        }
        m758a(73, 4000, 73);
        bArr[0] = (byte) -94;
        bArr[1] = (byte) 73;
        bArr[2] = valueOf.byteValue();
        bArr[3] = valueOf2.byteValue();
        bArr[4] = valueOf3.byteValue();
        bArr[5] = valueOf4.byteValue();
        bArr[6] = valueOf5.byteValue();
        bArr[7] = valueOf6.byteValue();
        this.f1746b.packageData(this.f1747c, bArr);
    }

    public void setUnit(int unit) {
        Log.p("Bg5InsSet", Level.INFO, "setUnit", new Object[]{Integer.valueOf(unit)});
        this.f1746b.packageData(this.f1747c, new byte[]{(byte) -94, (byte) 35, (byte) unit, (byte) 0, (byte) 0});
    }

    public void startMeasure(int type) {
        Log.p("Bg5InsSet", Level.INFO, "startMeasure", new Object[]{Integer.valueOf(type)});
        byte[] bArr = new byte[5];
        bArr[0] = (byte) -94;
        bArr[1] = Framer.STDOUT_FRAME_PREFIX;
        bArr[2] = (byte) 0;
        bArr[3] = (byte) 0;
        if (type == 1) {
            bArr[4] = (byte) 1;
        } else if (type == 2) {
            bArr[4] = (byte) 2;
        }
        this.f1746b.packageData(this.f1747c, bArr);
    }
}

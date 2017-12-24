package com.ihealth.communication.ins;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.facebook.imageutils.JfifUtil;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.BleCommProtocol;
import com.ihealth.communication.base.protocol.BtCommProtocol;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.DataBaseTools;
import com.ihealth.communication.cloud.data.HS_InAuthor;
import com.ihealth.communication.cloud.data.Make_Data_Util;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.control.HsProfile;
import com.ihealth.communication.manager.iHealthDevicesIDPS;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;
import humanize.util.Constants;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class A6InsSet extends IdentifyIns implements NewDataCallback, GetBaseCommProtocolCallback {
    public static final int MEASURETYPE_OFFLINE = 2;
    public static final int MEASURETYPE_ONLINE = 1;
    private int f1567A;
    private int f1568B = 0;
    private byte[] f1569C;
    private boolean f1570D = false;
    boolean f1571a = false;
    Timer f1572b;
    TimerTask f1573c;
    private final BaseComm f1574l;
    private BaseCommProtocol f1575m;
    private String f1576n;
    private String f1577o;
    private String f1578p;
    private String f1579q;
    private String f1580r;
    private String f1581s;
    private String f1582t;
    private String f1583u = "";
    private BaseCommCallback f1584v;
    private InsCallback f1585w;
    private Context f1586x;
    private String f1587y;
    private int f1588z;

    class C20972 extends TimerTask {
        final /* synthetic */ A6InsSet f1566a;

        C20972(A6InsSet this$0) {
            this.f1566a = this$0;
        }

        public void run() {
            this.f1566a.m822f();
            this.f1566a.m814b(700);
        }
    }

    public A6InsSet(String userName, Context context, BaseComm com, String mac, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        Log.p("A6InsSet", Level.INFO, "A6InsSet_Constructor", new Object[]{userName, mac, type});
        this.f1576n = mac;
        this.f1577o = type;
        this.f1584v = baseCommCallback;
        this.f1585w = insCallback;
        this.f1586x = context;
        this.f1587y = userName;
        if (type.equals(iHealthDevicesManager.TYPE_HS4)) {
            this.f1575m = new BleCommProtocol(context, com, this.f1576n, (byte) -90, this);
        } else if (type.equals(iHealthDevicesManager.TYPE_HS4S)) {
            this.f1575m = new BtCommProtocol(com, this);
        }
        this.f1574l = com;
        m759a(insCallback, mac, type, com);
    }

    private int m805a(byte[] bArr, int i) {
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

    private void m807a() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        int i = instance.get(1) - 2000;
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        byte[] intTo2Byte = ByteBufferUtil.intTo2Byte(i + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
        this.f1575m.packageData(this.f1576n, new byte[]{(byte) -90, (byte) 51, intTo2Byte[0], intTo2Byte[1], (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6});
    }

    private void m808a(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        int i3 = instance.get(1) - 2000;
        int i4 = instance.get(2) + 1;
        int i5 = instance.get(5);
        int i6 = instance.get(11);
        int i7 = instance.get(12);
        int i8 = instance.get(13);
        byte[] intTo2Byte = ByteBufferUtil.intTo2Byte(i3 + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
        r6 = new byte[14];
        byte[] intTo4Byte = ByteBufferUtil.intTo4Byte((long) i2);
        r6[10] = intTo4Byte[0];
        r6[11] = intTo4Byte[1];
        r6[12] = intTo4Byte[2];
        r6[13] = intTo4Byte[3];
        this.f1575m.packageData(this.f1576n, r6);
    }

    private void m809a(int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error", i);
            jSONObject.put("description", str);
            this.f1585w.onNotify(this.f1576n, this.f1577o, HsProfile.ACTION_ERROR_HS, jSONObject.toString());
        } catch (JSONException e) {
            Log.p("A6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
    }

    private void m811a(byte[] bArr, String str) {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        Object obj = null;
        int i = 0;
        while (i + 16 <= bArr.length) {
            try {
                int i2 = bArr[i + 6] & 255;
                int i3 = bArr[i + 7] & 255;
                int i4 = bArr[i + 8] & 255;
                int i5 = bArr[i + 9] & 255;
                int i6 = bArr[i + 10] & 255;
                double d = ((double) (((bArr[i + 11] & 255) * 256) + (bArr[i + 12] & 255))) / 10.0d;
                long String2TS = ByteBufferUtil.String2TS((((bArr[i + 4] & 255) * 256) + (bArr[i + 5] & 255)) + "-" + i2 + "-" + i3 + Constants.SPACE + i4 + ":" + i5 + ":" + i6);
                if (String2TS <= currentTimeMillis) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("dataID", MD5.md5String(PublicMethod.getDataID(this.f1576n, d + "", PublicMethod.getTs())));
                    jSONObject2.put("date", ByteBufferUtil.TS2String(String2TS));
                    jSONObject2.put("value", String.valueOf(d));
                    jSONArray.put(jSONObject2);
                    obj = 1;
                }
                i += 16;
            } catch (Exception e) {
                Log.p("A6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                return;
            }
        }
        if (obj != null) {
            jSONObject.putOpt(HsProfile.HISTORDATA__HS, jSONArray);
            this.f1585w.onNotify(this.f1576n, this.f1577o, HsProfile.ACTION_HISTORICAL_DATA_HS, jSONObject.toString());
            return;
        }
        this.f1585w.onNotify(this.f1576n, this.f1577o, HsProfile.ACTION_NO_HISTORICALDATA, jSONObject.toString());
    }

    private void m813b(byte b) {
        this.f1575m.packageData(this.f1576n, new byte[]{(byte) -90, b});
    }

    private void m814b(int i) {
        int i2 = -1;
        String str = "Unknown Error";
        switch (i) {
            case 9:
                i2 = 9;
                str = "No memory.";
                break;
            case 10:
                i2 = 10;
                str = "Device disconnect.";
                break;
            case 11:
                i2 = 11;
                str = "Communication error.";
                break;
            case 12:
                i2 = 12;
                str = "HS4DeviceRecWeightError.";
                break;
            case 160:
                i2 = 1;
                str = "Battery level is low.";
                break;
            case 224:
                i2 = 2;
                str = "The Scale failed to initialize.";
                break;
            case JfifUtil.MARKER_APP1 /*225*/:
                i2 = 3;
                str = "Maximum weight has been exceeded.";
                break;
            case 226:
                i2 = 4;
                str = "The Scale can't capture a steady reading.";
                break;
            case 228:
                i2 = 5;
                str = "Bluetooth connection error.";
                break;
            case 231:
                i2 = 6;
                str = "Movement while measuring.";
                break;
            case 232:
                i2 = 7;
                str = "Invalidate.";
                break;
            case 233:
                i2 = 8;
                str = "Scale memory access error.";
                break;
            case 400:
                i2 = 400;
                str = "measureOnline() parameter unit should be in range {1,2,3}.";
                break;
            case 500:
                i2 = 500;
                str = "setWifi() wifi is disabled, please enable it.";
                break;
            case 600:
                i2 = 13;
                str = "Failed to create measurement connections.";
                break;
            case 700:
                i2 = 14;
                str = "Instruction timeout.";
                break;
        }
        m809a(i2, str);
    }

    private void m816c() {
        this.f1575m.packageData(this.f1576n, new byte[]{(byte) -90, (byte) 65});
    }

    private void m817c(byte[] bArr) {
        Object obj = new byte[m805a(bArr, 0)];
        Object obj2 = new byte[m805a(bArr, 16)];
        Object obj3 = new byte[3];
        Object obj4 = new byte[3];
        Object obj5 = new byte[m805a(bArr, 38)];
        Object obj6 = new byte[m805a(bArr, 54)];
        try {
            System.arraycopy(bArr, 0, obj, 0, obj.length);
            System.arraycopy(bArr, 16, obj2, 0, obj2.length);
            System.arraycopy(bArr, 32, obj3, 0, obj3.length);
            System.arraycopy(bArr, 35, obj4, 0, obj4.length);
            System.arraycopy(bArr, 38, obj5, 0, obj5.length);
            System.arraycopy(bArr, 54, obj6, 0, obj6.length);
            this.f1582t = new String(obj, "UTF-8");
            this.f1583u = new String(obj2, "UTF-8");
            Log.i("info", Arrays.toString(obj3));
            this.f1579q = String.format("%c.%c.%c", new Object[]{Byte.valueOf(obj3[0]), Byte.valueOf(obj3[1]), Byte.valueOf(obj3[2])});
            this.f1578p = String.format("%c.%c.%c", new Object[]{Byte.valueOf(obj4[0]), Byte.valueOf(obj4[1]), Byte.valueOf(obj4[2])});
            if (obj6[0] != (byte) 66) {
                String str = this.f1577o;
                int i = -1;
                switch (str.hashCode()) {
                    case 71817:
                        if (str.equals(iHealthDevicesManager.TYPE_HS4)) {
                            i = 0;
                            break;
                        }
                        break;
                    case 2226410:
                        if (str.equals(iHealthDevicesManager.TYPE_HS4S)) {
                            i = 1;
                            break;
                        }
                        break;
                }
                switch (i) {
                    case 0:
                        this.f1581s = "HS4 11070";
                        break;
                    case 1:
                        this.f1581s = "HS4S 11070";
                        break;
                    default:
                        this.f1581s = "HS 11070";
                        break;
                }
            }
            this.f1581s = new String(obj6, "UTF-8");
            this.f1580r = new String(obj5, "UTF-8");
            Intent intent = new Intent(iHealthDevicesIDPS.MSG_IHEALTH_DEVICE_IDPS);
            intent.putExtra(iHealthDevicesIDPS.PROTOCOLSTRING, this.f1582t);
            intent.putExtra(iHealthDevicesIDPS.ACCESSORYNAME, this.f1583u);
            intent.putExtra(iHealthDevicesIDPS.FIRMWAREVERSION, this.f1579q);
            intent.putExtra(iHealthDevicesIDPS.HARDWAREVERSION, this.f1578p);
            intent.putExtra(iHealthDevicesIDPS.MODENUMBER, this.f1581s);
            intent.putExtra(iHealthDevicesIDPS.MANUFACTURER, this.f1580r);
            intent.putExtra(iHealthDevicesIDPS.SERIALNUMBER, this.f1576n);
            intent.putExtra("type", this.f1577o);
            intent.setPackage(this.f1586x.getPackageName());
            this.f1586x.sendBroadcast(intent);
        } catch (UnsupportedEncodingException e) {
            Log.p("A6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
    }

    private void m819d() {
        this.f1575m.packageData(this.f1576n, new byte[]{(byte) -90, (byte) 66});
    }

    private void m821e() {
        this.f1572b = new Timer();
        this.f1573c = new C20972(this);
        this.f1572b.schedule(this.f1573c, 4000);
    }

    private void m822f() {
        if (this.f1573c != null) {
            this.f1573c.cancel();
            this.f1573c = null;
        }
        if (this.f1572b != null) {
            this.f1572b.cancel();
            this.f1572b = null;
        }
    }

    public void destroy() {
        Log.p("A6InsSet", Level.INFO, "destroy", new Object[0]);
        if (this.f1575m != null) {
            this.f1575m.destroy();
        }
        this.f1575m = null;
        this.f1586x = null;
    }

    public BaseCommProtocol getBaseCommProtocol() {
        return this.f1575m;
    }

    public void getIdps() {
        Log.p("A6InsSet", Level.INFO, "getIdps", new Object[0]);
        this.f1575m.packageData(this.f1576n, new byte[]{(byte) -90, (byte) -15});
    }

    public void haveNewData(int what, int stateId, final byte[] returnData) {
        r6 = new Object[3];
        r6[0] = String.format("0x%02X", new Object[]{Integer.valueOf(what)});
        r6[1] = Integer.valueOf(stateId);
        r6[2] = ByteBufferUtil.Bytes2HexString(returnData);
        Log.p("A6InsSet", Level.DEBUG, "haveNewData", r6);
        String toHexString = Integer.toHexString(what & 255);
        if (toHexString.length() == 1) {
            '0' + toHexString;
        }
        m757a(what);
        switch (what) {
            case 50:
                if (returnData[0] == (byte) 1) {
                    m814b(600);
                    return;
                } else {
                    m821e();
                    return;
                }
            case 51:
                if (returnData[0] == (byte) 2) {
                    m816c();
                    return;
                }
                return;
            case 52:
                m813b((byte) 52);
                m822f();
                m814b(returnData[0] & 255);
                return;
            case 53:
                this.f1571a = true;
                m822f();
                double byte2ToInt = ((double) ByteBufferUtil.byte2ToInt(returnData)) / 10.0d;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("value", String.valueOf(byte2ToInt));
                    this.f1585w.onNotify(this.f1576n, this.f1577o, HsProfile.ACTION_LIVEDATA_HS, jSONObject.toString());
                } catch (Exception e) {
                    Log.p("A6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                }
                m821e();
                return;
            case 54:
                m813b((byte) 54);
                m822f();
                this.f1570D = true;
                if (this.f1571a) {
                    this.f1571a = false;
                    new Timer().schedule(new TimerTask(this) {
                        final /* synthetic */ A6InsSet f1565b;

                        public void run() {
                            this.f1565b.stopLink(1);
                            double byte2ToInt = ((double) ByteBufferUtil.byte2ToInt(returnData)) / 10.0d;
                            String dataID = PublicMethod.getDataID(this.f1565b.f1576n, byte2ToInt + "", PublicMethod.getTs());
                            try {
                                JSONObject jSONObject = new JSONObject();
                                jSONObject.put("value", String.valueOf(byte2ToInt));
                                jSONObject.put("dataID", MD5.md5String(dataID));
                                this.f1565b.f1585w.onNotify(this.f1565b.f1576n, this.f1565b.f1577o, HsProfile.ACTION_ONLINE_RESULT_HS, jSONObject.toString());
                            } catch (Exception e) {
                                Log.p("A6InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                            }
                            if (C2041b.f505a) {
                                new DataBaseTools(this.f1565b.f1586x).addData(DataBaseConstants.TABLE_TB_HSRESULT, Make_Data_Util.makeDataSingleHs(dataID, this.f1565b.f1587y, (float) byte2ToInt, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, this.f1565b.f1577o, this.f1565b.f1576n));
                                HS_InAuthor instance = HS_InAuthor.getInstance();
                                instance.initAuthor(this.f1565b.f1586x, this.f1565b.f1587y);
                                instance.run();
                            }
                        }
                    }, 500);
                    return;
                }
                return;
            case 57:
                if (this.f1570D) {
                    this.f1570D = false;
                    return;
                } else if ((byte) 3 != returnData[0]) {
                    return;
                } else {
                    if (2 == this.f1568B) {
                        m807a();
                        return;
                    } else if (1 == this.f1568B) {
                        m808a(this.f1588z, this.f1567A);
                        return;
                    } else if (this.f1568B != 3) {
                        return;
                    } else {
                        return;
                    }
                }
            case 65:
                byte b = returnData[1];
                this.f1569C = null;
                if (b != (byte) 0) {
                    m819d();
                    return;
                }
                try {
                    this.f1585w.onNotify(this.f1576n, this.f1577o, HsProfile.ACTION_NO_HISTORICALDATA, new JSONObject().toString());
                    return;
                } catch (Exception e2) {
                    Log.p("A6InsSet", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                    return;
                }
            case 66:
                if (returnData[3] != (byte) 0) {
                    m819d();
                    this.f1569C = ByteBufferUtil.BufferMerger(this.f1569C, returnData);
                    return;
                }
                m811a(this.f1569C, this.f1577o);
                this.f1569C = null;
                return;
            case 240:
                m813b((byte) -16);
                m817c(returnData);
                return;
            case 251:
                byte[] a = m762a(returnData, this.f1577o, (byte) -90);
                m758a(252, 4000, 253, 254);
                this.f1575m.packageData(this.f1576n, a);
                return;
            case 253:
                this.f1584v.onConnectionStateChange(this.f1576n, this.f1577o, 1, 0, null);
                return;
            case 254:
                this.f1574l.disconnect();
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
        Log.p("A6InsSet", Level.INFO, "identify", new Object[0]);
        m758a((int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 4000, 251, 253, 254);
        this.f1575m.packageData(this.f1576n, m760a((byte) -90));
    }

    public void setUnitAndUserId(int unit, int userId) {
        Log.p("A6InsSet", Level.INFO, "setUnitAndUserId", new Object[]{Integer.valueOf(unit), Integer.valueOf(userId)});
        if (unit < 1 || unit > 3) {
            m814b(400);
            return;
        }
        this.f1588z = unit;
        this.f1567A = userId;
    }

    public void stopLink(int index) {
        Log.p("A6InsSet", Level.INFO, "stopLink", new Object[]{Integer.valueOf(index)});
        this.f1568B = index;
        this.f1575m.packageData(this.f1576n, new byte[]{(byte) -90, (byte) 57, (byte) 1});
    }
}

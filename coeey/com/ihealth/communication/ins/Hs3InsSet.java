package com.ihealth.communication.ins;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.imageutils.JfifUtil;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.base.protocol.Hs3CommProtocol;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.DataBaseTools;
import com.ihealth.communication.cloud.data.HS_InAuthor;
import com.ihealth.communication.cloud.data.Make_Data_Util;
import com.ihealth.communication.cloud.p002a.C2041b;
import com.ihealth.communication.control.HsProfile;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.ihealth.communication.utils.MD5;
import com.ihealth.communication.utils.PublicMethod;
import humanize.util.Constants;
import java.util.Calendar;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONObject;

public class Hs3InsSet implements NewDataCallback {
    private static String f1860d = "HS3OLLINETIME";
    private Context f1861a;
    private byte f1862b = (byte) -90;
    private BaseCommProtocol f1863c;
    private String f1864e = "";
    private String f1865f = "";
    private BaseCommCallback f1866g;
    private InsCallback f1867h;
    private String f1868i;
    private int f1869j;
    private int f1870k;
    private long f1871l = 0;
    private int f1872m = 0;
    private byte[] f1873n;

    public Hs3InsSet(String userName, BaseComm com, Context context, String mac, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        Log.p("Hs3InsSet", Level.INFO, "Hs3InsSet_Constructor", new Object[]{userName, mac, type});
        this.f1863c = new Hs3CommProtocol(com, this);
        this.f1861a = context;
        this.f1864e = mac;
        this.f1865f = type;
        this.f1868i = userName;
        this.f1866g = baseCommCallback;
        this.f1867h = insCallback;
        m1011a();
    }

    private int m1010a(int i, int i2) {
        return (i2 * 32) + i;
    }

    private void m1011a() {
        Calendar instance = Calendar.getInstance();
        instance.set(2011, 0, 2, 12, 12, 12);
        this.f1871l = instance.getTimeInMillis();
        SharedPreferences sharedPreferences = this.f1861a.getSharedPreferences(this.f1868i + f1860d, 0);
        if (this.f1871l > sharedPreferences.getLong(this.f1864e, 0)) {
            Editor edit = sharedPreferences.edit();
            edit.putLong(this.f1864e, this.f1871l);
            edit.apply();
        }
    }

    private void m1012a(byte b, byte b2, byte b3, byte b4, byte b5, byte b6) {
        r0 = new byte[8];
        this.f1872m = 32;
        r0[0] = this.f1862b;
        r0[1] = (byte) 32;
        r0[2] = b;
        r0[3] = b2;
        r0[4] = b3;
        r0[5] = b4;
        r0[6] = b5;
        r0[7] = b6;
        this.f1863c.packageData(null, r0);
    }

    private void m1013a(long j) {
        SharedPreferences sharedPreferences = this.f1861a.getSharedPreferences(this.f1868i + f1860d, 0);
        if (j > sharedPreferences.getLong(this.f1864e, 0)) {
            Editor edit = sharedPreferences.edit();
            edit.putLong(this.f1864e, j);
            edit.apply();
        }
    }

    private void m1014a(String str) {
        this.f1871l = this.f1861a.getSharedPreferences(this.f1868i + f1860d, 0).getLong(str, this.f1871l);
    }

    private int[] m1015a(byte[] bArr) {
        int i = ((bArr[0] & 252) >> 2) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
        int i2 = ((bArr[1] & JfifUtil.MARKER_SOFn) >> 6) | ((bArr[0] & 3) << 2);
        int i3 = (bArr[1] & 62) >> 1;
        int i4 = bArr[1] & 1;
        int i5 = (bArr[2] & 240) >> 4;
        int i6 = ((bArr[2] & 15) << 2) | ((bArr[3] & JfifUtil.MARKER_SOFn) >> 6);
        int i7 = bArr[3] & 63;
        i4 = i5 == 12 ? i4 * 12 : (i4 * 12) + i5;
        i5 = (bArr[5] & 15) + ((((bArr[5] & 240) >> 4) * 10) + (((bArr[4] & 15) * 100) + (((bArr[4] & 240) >> 4) * 1000)));
        return new int[]{i, i2, i3, i4, i6, i7, i5};
    }

    private String m1016b(byte[] bArr) {
        String str = "";
        int length = bArr.length / 7;
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            long j = 0;
            for (int i = 0; i < length; i++) {
                byte[] bytesCutt = ByteBufferUtil.bytesCutt(i * 7, (i * 7) + 6, bArr);
                if (bytesCutt != null && bytesCutt.length == 7) {
                    int[] a = m1015a(bytesCutt);
                    long String2TS = ByteBufferUtil.String2TS((a[0] < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + a[0] : Integer.valueOf(a[0])) + "-" + (a[1] < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + a[1] : Integer.valueOf(a[1])) + "-" + (a[2] < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + a[2] : Integer.valueOf(a[2])) + Constants.SPACE + (a[3] < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + a[3] : Integer.valueOf(a[3])) + ":" + (a[4] < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + a[4] : Integer.valueOf(a[4])) + ":" + (a[5] < 10 ? AppEventsConstants.EVENT_PARAM_VALUE_NO + a[5] : Integer.valueOf(a[5])));
                    if (String2TS < currentTimeMillis && 1000 * String2TS > this.f1871l) {
                        if (j < 1000 * String2TS) {
                            j = 1000 * String2TS;
                        }
                        String dataID = PublicMethod.getDataID(this.f1864e, ((float) (((double) a[6]) / 10.0d)) + "", String2TS);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("dataID", MD5.md5String(dataID));
                        jSONObject2.put("date", ByteBufferUtil.TS2String(String2TS));
                        jSONObject2.put("value", String.valueOf(((double) a[6]) / 10.0d));
                        jSONArray.put(jSONObject2);
                    }
                }
            }
            if (jSONArray.length() > 0) {
                m1013a(j);
                jSONObject.put(HsProfile.HISTORDATA__HS, jSONArray);
                this.f1867h.onNotify(this.f1864e, this.f1865f, HsProfile.ACTION_HISTORICAL_DATA_HS, jSONObject.toString());
            } else {
                this.f1867h.onNotify(this.f1864e, this.f1865f, HsProfile.ACTION_NO_HISTORICALDATA, jSONObject.toString());
            }
        } catch (Exception e) {
            Log.p("Hs3InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
        return str;
    }

    private void m1017b() {
        byte[] bArr = new byte[5];
        bArr[0] = this.f1862b;
        bArr[1] = (byte) 39;
        this.f1863c.packageData(null, bArr);
    }

    private void m1018c() {
        this.f1863c.packageData(null, new byte[]{this.f1862b, (byte) -1});
    }

    private void m1019d() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1) - 2000;
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(10);
        int i5 = instance.get(9);
        int i6 = instance.get(12);
        m1012a((byte) i, (byte) i2, (byte) i3, (byte) m1010a(i4, i5), (byte) i6, (byte) instance.get(13));
    }

    public void createChannel() {
        Log.p("Hs3InsSet", Level.INFO, "createChannel", new Object[0]);
        r0 = new byte[2];
        this.f1872m = 35;
        r0[0] = this.f1862b;
        r0[1] = (byte) 35;
        this.f1863c.packageData(null, r0);
    }

    public void destroy() {
        Log.p("Hs3InsSet", Level.INFO, "destroy", new Object[0]);
    }

    public void getOffLineDataNum() {
        int i = 12;
        Log.p("Hs3InsSet", Level.INFO, "getOffLineDataNum", new Object[0]);
        m1014a(this.f1864e);
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        instance.setTimeInMillis(this.f1871l);
        int i2 = instance.get(1) - 2000;
        int i3 = instance.get(2) + 1;
        int i4 = instance.get(5);
        int i5 = instance.get(10);
        int i6 = instance.get(9);
        int i7 = instance.get(12);
        int i8 = instance.get(13);
        if (i5 != 0) {
            i = i5;
        }
        i = m1010a(i, i6);
        this.f1863c.packageData(null, new byte[]{this.f1862b, (byte) 37, (byte) i2, (byte) i3, (byte) i4, (byte) i, (byte) i7, (byte) i8});
    }

    public void haveNewData(int what, int stateId, byte[] returnData) {
        r4 = new Object[3];
        r4[0] = String.format("0x%02X", new Object[]{Integer.valueOf(what)});
        r4[1] = Integer.valueOf(stateId);
        r4[2] = ByteBufferUtil.Bytes2HexString(returnData);
        Log.p("Hs3InsSet", Level.INFO, "haveNewData", r4);
        String toHexString = Integer.toHexString(what & 255);
        if (toHexString.length() == 1) {
            '0' + toHexString;
        }
        int i;
        JSONObject jSONObject;
        switch (what) {
            case -1:
                return;
            case 33:
                m1018c();
                this.f1870k++;
                this.f1873n = ByteBufferUtil.BufferMerger(this.f1873n, returnData);
                if (this.f1870k >= this.f1869j) {
                    m1016b(this.f1873n);
                    return;
                }
                return;
            case 34:
                m1018c();
                i = returnData[0] & 255;
                try {
                    jSONObject = new JSONObject();
                    jSONObject.put("error", i);
                    this.f1867h.onNotify(this.f1864e, this.f1865f, HsProfile.ACTION_ERROR_HS, jSONObject.toString());
                    return;
                } catch (Exception e) {
                    Log.p("Hs3InsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                    return;
                }
            case 38:
                m1018c();
                i = returnData[0] & 255;
                if (i > 0) {
                    this.f1869j = i / 20;
                    this.f1870k = 0;
                    if (i % 20 != 0) {
                        this.f1869j++;
                    }
                    m1017b();
                    return;
                }
                try {
                    this.f1867h.onNotify(this.f1864e, this.f1865f, HsProfile.ACTION_NO_HISTORICALDATA, new JSONObject().toString());
                    return;
                } catch (Exception e2) {
                    Log.p("Hs3InsSet", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                    return;
                }
            case 40:
                m1018c();
                int i2 = ((returnData[5] & -16) >>> 4) & 15;
                float f = ((float) ((((((returnData[4] & -16) >>> 4) & 15) * 100) + (((returnData[4] & 15) & 15) * 10)) + i2)) + ((float) (((double) ((returnData[5] & 15) & 15)) / 10.0d));
                toHexString = PublicMethod.getDataID(this.f1864e, f + "", PublicMethod.getTs());
                try {
                    jSONObject = new JSONObject();
                    jSONObject.put("dataID", MD5.md5String(toHexString));
                    jSONObject.put("value", String.valueOf(f));
                    this.f1867h.onNotify(this.f1864e, this.f1865f, HsProfile.ACTION_ONLINE_RESULT_HS, jSONObject.toString());
                } catch (Exception e3) {
                    Log.p("Hs3InsSet", Level.WARN, "Exception", new Object[]{e3.getMessage()});
                }
                if (C2041b.f505a) {
                    new DataBaseTools(this.f1861a).addData(DataBaseConstants.TABLE_TB_HSRESULT, Make_Data_Util.makeDataSingleHs(toHexString, this.f1868i, f, 0.0f, 0.0f, 0.0f, 0.0f, 0, 0.0f, this.f1865f, this.f1864e));
                    HS_InAuthor instance = HS_InAuthor.getInstance();
                    instance.initAuthor(this.f1861a, this.f1868i);
                    instance.run();
                    return;
                }
                return;
            case 48:
                m1018c();
                return;
            case 49:
                m1018c();
                return;
            case 50:
                m1018c();
                m1019d();
                return;
            case 51:
                m1018c();
                return;
            case 255:
                if (this.f1872m == 32) {
                    this.f1866g.onConnectionStateChange(this.f1864e, this.f1865f, 1, 0, null);
                    this.f1872m = 0;
                    return;
                }
                return;
            default:
                Log.p("Hs3InsSet", Level.WARN, "Exception", new Object[]{"no method"});
                return;
        }
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
    }
}

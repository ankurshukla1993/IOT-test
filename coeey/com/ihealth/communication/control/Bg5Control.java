package com.ihealth.communication.control;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import com.facebook.appevents.AppEventsConstants;
import com.github.thunder413.datetimeutils.DateTimeFormat;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.ihealth.androidbg.audio.CrcCheck;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.ins.Bg5InsSet;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.p001a.C2023a;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Bg5Control implements IBGDelegate {
    private Bg5InsSet f1081a;
    private BaseComm f1082b;
    private long f1083c = 0;
    private String f1084d = "";
    private String f1085e = "";
    private InsCallback f1086f = null;
    private boolean f1087g;
    private C2023a f1088h;

    public Bg5Control(String userName, BaseComm com, Context context, String mac, String deviceType, boolean needRead, BaseCommCallback mBaseCommCallback, InsCallback insCallback) {
        Log.p("Bg5Control", Level.INFO, "Bg5Control", new Object[]{userName, mac, deviceType});
        Log.p("Bg5Control", Level.INFO, "Bg5Control", new Object[]{userName, com, context, mac, deviceType, mBaseCommCallback, insCallback});
        this.f1082b = com;
        this.f1081a = new Bg5InsSet(userName, com, context, mac, deviceType, mBaseCommCallback, insCallback);
        this.f1084d = mac;
        this.f1085e = deviceType;
        this.f1087g = needRead;
        this.f1086f = insCallback;
        this.f1088h = new C2023a(mac, deviceType, Bg5Profile.ACTION_ERROR_BG);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1088h);
    }

    private long m549a(String str) {
        if (m554b(str)) {
            byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(str);
            return (hexStringToByte == null || hexStringToByte.length != 30) ? 0 : (long) (((((((hexStringToByte[26] & 255) * 256) * 256) * 256) + (((hexStringToByte[27] & 255) * 256) * 256)) + ((hexStringToByte[24] & 255) * 256)) + (hexStringToByte[25] & 255));
        } else {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Bg5Profile.ERROR_NUM_BG, 7);
                this.f1086f.onNotify(this.f1084d, this.f1085e, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
                return 0;
            } catch (JSONException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    private static String m551a(String str, String[] strArr, String[] strArr2) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            for (int i = 0; i < strArr.length; i++) {
                jSONObject2.put(strArr2[i], strArr[i]);
            }
            jSONArray.put(jSONObject2);
            jSONObject.put(str, jSONArray);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int[] m552a(byte[] bArr, int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = bArr[i2];
            if (iArr[i2] < 0) {
                iArr[i2] = iArr[i2] + 256;
            }
        }
        return iArr;
    }

    private static synchronized boolean m554b(String str) {
        boolean z = false;
        synchronized (Bg5Control.class) {
            if (str != null) {
                if (str.length() == 60) {
                    byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(str);
                    if (hexStringToByte.length == 30) {
                        int cRCValue = new CrcCheck(m552a(hexStringToByte, 28)).getCRCValue();
                        if (hexStringToByte[28] == ((byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & cRCValue) >> 8)) && hexStringToByte[29] == ((byte) (cRCValue & 255))) {
                            z = true;
                        }
                    }
                }
            }
        }
        return z;
    }

    public static String getBottleInfoFromQR(String QRCode) {
        int i = 25;
        if (!m554b(QRCode)) {
            return new JSONObject().toString();
        }
        byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(QRCode);
        if (hexStringToByte == null || hexStringToByte.length != 30) {
            return new JSONObject().toString();
        }
        long j = (((((((long) (hexStringToByte[26] & 255)) * 256) * 256) * 256) + ((((long) (hexStringToByte[27] & 255)) * 256) * 256)) + (((long) (hexStringToByte[24] & 255)) * 256)) + ((long) (hexStringToByte[25] & 255));
        int i2 = (hexStringToByte[24] & 254) >> 1;
        int i3 = ((hexStringToByte[24] & 1) * 8) + ((hexStringToByte[25] & 224) >> 5);
        int i4 = hexStringToByte[25] & 31;
        if (i2 == 15 && i3 == 1 && i4 == 15) {
            i = 10;
        } else {
            int i5 = hexStringToByte[22] & 255;
            if (i5 <= 25) {
                i = i5;
            }
        }
        String str = "20" + i2 + "-" + (i3 > 10 ? Integer.valueOf(i3) : AppEventsConstants.EVENT_PARAM_VALUE_NO + i3) + "-" + (i4 > 10 ? Integer.valueOf(i4) : AppEventsConstants.EVENT_PARAM_VALUE_NO + i4);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateTimeFormat.DATE_PATTERN_1);
        try {
            str = simpleDateFormat.format(simpleDateFormat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return m551a("bottleInfo", new String[]{j + "", str, i + ""}, new String[]{"bottleId", "overDate", "stripNum"});
    }

    public String codeStripStrAnalysis(String qr) {
        int i = 25;
        JSONObject jSONObject = new JSONObject();
        if (m554b(qr)) {
            byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(qr);
            if (qr != null && hexStringToByte.length == 30) {
                try {
                    int i2 = ((((((hexStringToByte[26] & 255) * 256) * 256) * 256) + (((hexStringToByte[27] & 255) * 256) * 256)) + ((hexStringToByte[24] & 255) * 256)) + (hexStringToByte[25] & 255);
                    int i3 = (hexStringToByte[24] & 254) >> 1;
                    int i4 = ((hexStringToByte[24] & 1) * 8) + ((hexStringToByte[25] & 224) >> 5);
                    int i5 = hexStringToByte[25] & 31;
                    if (i3 == 15 && i4 == 1 && i5 == 15) {
                        i = 10;
                    } else {
                        int i6 = hexStringToByte[22] & 255;
                        if (i6 <= 25) {
                            i = i6;
                        }
                    }
                    String str = String.valueOf(i3 + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE) + "-" + String.valueOf(i4) + "-" + String.valueOf(i5);
                    jSONObject.put("bottleid", i2);
                    jSONObject.put(Bg5Profile.CODESTRIPANALYSIS_STRIPNUM, i);
                    jSONObject.put(Bg5Profile.CODESTRIPANALYSIS_DUEDATE, str);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return jSONObject.toString();
    }

    public void deleteOfflineData() {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_DELETE_HISTORICAL_DATA}), -1, new bs(this));
    }

    public void destroy() {
        if (this.f1081a != null) {
            this.f1081a.destroy();
        }
        this.f1081a = null;
        this.f1082b = null;
    }

    public void disconnect() {
        this.f1082b.disconnect();
    }

    public void getBattery() {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_BATTERY_BG}), 4500, new bp(this));
    }

    public void getBottleId() {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_GET_BOTTLEID}), 4500, new bm(this));
    }

    public void getBottleMessage() {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_GET_CODEINFO}), 4500, new bk(this));
    }

    public void getOfflineData() {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_HISTORICAL_DATA_BG}), -1, new br(this));
    }

    public void holdLink() {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_KEEP_LINK}), 4500, new bi(this));
    }

    public void init() {
        Log.e(this.f1084d, "mNeedReadEE = " + this.f1087g);
        if (this.f1087g) {
            this.f1081a.getBtEE();
        } else {
            this.f1081a.identify();
        }
    }

    public void readEE() {
        this.f1082b.sendData(this.f1084d, ByteBufferUtil.hexStringToByte("0D0A2B495353435F525F4545503A3031386130360D0A"));
    }

    public void setBottleId(long bottleId) {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_SET_BOTTLE_ID_SUCCESS}), 4500, new bl(this, bottleId));
    }

    public void setBottleMessage(String QRCode) {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS}), 4500, new bt(this, QRCode));
    }

    public void setBottleMessage(String QRCode, int stripNum, String overDate) {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS}), 4500, new bu(this, QRCode, stripNum, overDate));
    }

    public void setBottleMessageWithInfo(int stripType, int measureType, String QRCode, int stripNum, String overDate) {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS}), 4500, new bj(this, stripType, measureType, QRCode, stripNum, overDate));
    }

    public void setTime() {
        this.f1088h.m232a(Arrays.asList(new String[]{"action_set_time"}), 4500, new bn(this));
    }

    public void setUnit(int type) {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_SET_UNIT}), 4500, new bo(this, type));
    }

    public void startMeasure(int type) {
        this.f1088h.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_START_MEASURE}), -1, new bq(this, type));
    }
}

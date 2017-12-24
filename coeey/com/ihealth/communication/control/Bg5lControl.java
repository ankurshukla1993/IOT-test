package com.ihealth.communication.control;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import com.facebook.appevents.AppEventsConstants;
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
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Bg5lControl implements IBGDelegate {
    long f1089a = 0;
    private Bg5InsSet f1090b;
    private BaseComm f1091c;
    private String f1092d = "";
    private String f1093e = "";
    private InsCallback f1094f = null;
    private C2023a f1095g;

    public Bg5lControl(Context context, BaseComm com, String userName, String mac, String deviceType, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        Log.p("Bg5lControl", Level.INFO, "Bg5lControl", new Object[]{userName, mac, deviceType});
        this.f1091c = com;
        this.f1090b = new Bg5InsSet(userName, com, context, mac, deviceType, mBaseCommCallback, insCallback);
        this.f1094f = insCallback;
        this.f1092d = mac;
        this.f1093e = deviceType;
        this.f1095g = new C2023a(mac, deviceType, Bg5Profile.ACTION_ERROR_BG);
        iHealthDevicesManager.getInstance().commandCacheControlMap.put(mac, this.f1095g);
    }

    private long m559a(String str) {
        if (m564b(str)) {
            byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(str);
            return (hexStringToByte == null || hexStringToByte.length != 30) ? 0 : (long) (((((((hexStringToByte[26] & 255) * 256) * 256) * 256) + (((hexStringToByte[27] & 255) * 256) * 256)) + ((hexStringToByte[24] & 255) * 256)) + (hexStringToByte[25] & 255));
        } else {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Bg5Profile.ERROR_NUM_BG, 7);
                this.f1094f.onNotify(this.f1092d, this.f1093e, Bg5Profile.ACTION_ERROR_BG, jSONObject.toString());
                return 0;
            } catch (JSONException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    private static String m561a(String str, String[] strArr, String[] strArr2) {
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

    private static int[] m562a(byte[] bArr, int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = bArr[i2];
            if (iArr[i2] < 0) {
                iArr[i2] = iArr[i2] + 256;
            }
        }
        return iArr;
    }

    private static synchronized boolean m564b(String str) {
        boolean z = false;
        synchronized (Bg5lControl.class) {
            if (str != null) {
                if (str.length() == 60) {
                    byte[] hexStringToByte = ByteBufferUtil.hexStringToByte(str);
                    if (hexStringToByte.length == 30) {
                        int cRCValue = new CrcCheck(m562a(hexStringToByte, 28)).getCRCValue();
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
        if (m564b(QRCode)) {
            byte[] bArr = new byte[60];
            bArr = ByteBufferUtil.hexStringToByte(QRCode);
            if (bArr != null && bArr.length == 30) {
                int i2 = (((bArr[26] & 255) * 256) + bArr[27]) & 255;
                i2 = ((((((bArr[26] & 255) * 256) * 256) * 256) + (((bArr[27] & 255) * 256) * 256)) + ((bArr[24] & 255) * 256)) + (bArr[25] & 255);
                int i3 = (bArr[24] & 254) >> 1;
                int i4 = ((bArr[24] & 1) * 8) + ((bArr[25] & 224) >> 5);
                int i5 = bArr[25] & 31;
                if (i3 == 15 && i4 == 1 && i5 == 15) {
                    i = 10;
                } else {
                    int i6 = bArr[22] & 255;
                    if (i6 <= 25) {
                        i = i6;
                    }
                }
                String str = "20" + i3 + "-" + (i4 > 10 ? Integer.valueOf(i4) : AppEventsConstants.EVENT_PARAM_VALUE_NO + i4) + "-" + (i5 > 10 ? Integer.valueOf(i5) : AppEventsConstants.EVENT_PARAM_VALUE_NO + i5);
                return m561a("bottleInfo", new String[]{i2 + "", str, i + ""}, new String[]{"bottleId", "overDate", "stripNum"});
            }
        }
        return "";
    }

    public String codeStripStrAnalysis(String qr) {
        int i = 25;
        JSONObject jSONObject = new JSONObject();
        if (m564b(qr)) {
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
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_DELETE_HISTORICAL_DATA}), -1, new ch(this));
    }

    public void destroy() {
    }

    public void disconnect() {
        this.f1091c.disconnect(this.f1092d);
    }

    public void getBattery() {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_BATTERY_BG}), 4500, new ce(this));
    }

    public void getBottleId() {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_GET_BOTTLEID}), 4500, new ca(this));
    }

    public void getBottleMessage() {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_GET_CODEINFO}), 4500, new by(this));
    }

    public void getOfflineData() {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_HISTORICAL_DATA_BG}), -1, new cg(this));
    }

    public void getTime() {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_GET_TIME}), 4500, new cc(this));
    }

    public void holdLink() {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_KEEP_LINK}), 4500, new bv(this));
    }

    public void init() {
        this.f1090b.identify();
    }

    public void setBottleId(long bottleId) {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_SET_BOTTLE_ID_SUCCESS}), 4500, new bz(this, bottleId));
    }

    public void setBottleMessage(String QRCode) {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS}), 4500, new ci(this, QRCode));
    }

    public void setBottleMessage(String QRCode, int stripNum, String overDate) {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS}), 4500, new bw(this, QRCode, stripNum, overDate));
    }

    public void setBottleMessageWithInfo(int stripType, int measureType, String QRCode, int stripNum, String overDate) {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_SET_BOTTLE_MESSAGE_SUCCESS}), 4500, new bx(this, stripType, measureType, QRCode, stripNum, overDate));
    }

    public void setTime() {
        this.f1095g.m232a(Arrays.asList(new String[]{"action_set_time"}), 4500, new cb(this));
    }

    public void setUnit(int type) {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_SET_UNIT}), 4500, new cd(this, type));
    }

    public void startMeasure(int type) {
        this.f1095g.m232a(Arrays.asList(new String[]{Bg5Profile.ACTION_START_MEASURE}), -1, new cf(this, type));
    }
}

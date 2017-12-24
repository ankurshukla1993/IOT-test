package com.ihealth.communication.ins;

import android.content.Context;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.ble.BleConfig;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BleCommContinueProtocol;
import com.ihealth.communication.control.HsProfile;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.ContinuaDataAnalysis;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class HsInsSet implements NewDataCallback {
    private Context f1885a;
    private BaseComm f1886b;
    private BleComm f1887c;
    private String f1888d;
    private String f1889e;
    private InsCallback f1890f;
    private BleCommContinueProtocol f1891g;
    private boolean f1892h;

    public HsInsSet(Context context, BaseComm com, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        Log.p("HsInsSet", Level.INFO, "HsInsSet_Constructor", new Object[]{userName, mac, type});
        this.f1885a = context;
        this.f1886b = com;
        this.f1887c = bleComm;
        this.f1888d = mac;
        this.f1889e = type;
        this.f1890f = insCallback;
        this.f1891g = new BleCommContinueProtocol(com, mac, this);
    }

    private JSONObject m1027a(byte[] bArr) {
        if (bArr.length < 3) {
            return null;
        }
        ContinuaDataAnalysis continuaDataAnalysis = new ContinuaDataAnalysis(bArr, bArr.length);
        JSONObject jSONObject = new JSONObject();
        try {
            byte read8ByteValue = continuaDataAnalysis.read8ByteValue();
            int i = read8ByteValue & 1;
            jSONObject.put(HsProfile.CHS_UNIT_FLAG, i);
            int i2 = (read8ByteValue >> 1) & 1;
            jSONObject.put(HsProfile.CHS_TIMESTAMP_FLAG, i2);
            int i3 = (read8ByteValue >> 2) & 1;
            jSONObject.put(HsProfile.CHS_USER_ID_FLAG, i3);
            int i4 = (read8ByteValue >> 3) & 1;
            jSONObject.put(HsProfile.CHS_BMI_AND_Height_FLAG, i4);
            if (i == 0) {
                jSONObject.put(HsProfile.CHS_WEIGHT_SI, continuaDataAnalysis.readUInt16Value());
            } else {
                jSONObject.put(HsProfile.CHS_WEIGHT_IMPERIAL, continuaDataAnalysis.readUInt16Value());
            }
            if (i2 == 1) {
                jSONObject.put(HsProfile.CHS_TIME_STAMP, continuaDataAnalysis.readDateValue());
            }
            if (i3 == 1) {
                jSONObject.put(HsProfile.CHS_USER_ID, continuaDataAnalysis.readUInt8Value() & 255);
            }
            if (i4 != 1) {
                return jSONObject;
            }
            jSONObject.put(HsProfile.CHS_BMI, continuaDataAnalysis.readUInt16Value());
            if (i == 0) {
                jSONObject.put(HsProfile.CHS_HEIGHT_SI, continuaDataAnalysis.readUInt16Value());
                return jSONObject;
            }
            jSONObject.put(HsProfile.CHS_HEIGHT_IMPERIAL, continuaDataAnalysis.readUInt16Value());
            return jSONObject;
        } catch (JSONException e) {
            Log.p("HsInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            return jSONObject;
        }
    }

    public void destroy() {
        Log.p("HsInsSet", Level.INFO, "destroy", new Object[0]);
        this.f1890f = null;
        this.f1885a = null;
        if (this.f1891g != null) {
            this.f1891g.destroy();
        }
        this.f1891g = null;
    }

    public boolean getBattery() {
        Log.p("HsInsSet", Level.INFO, "getBattery", new Object[0]);
        if (!(this.f1886b instanceof BleComm)) {
            return false;
        }
        return ((BleComm) this.f1886b).Obtain(this.f1888d, UUID.fromString(BleConfig.UUID_BTM_BATTERY_SERVICE), UUID.fromString(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC));
    }

    public boolean getFeature() {
        Log.p("HsInsSet", Level.INFO, "getFeature", new Object[0]);
        if (!(this.f1886b instanceof BleComm)) {
            return false;
        }
        return ((BleComm) this.f1886b).Obtain(this.f1888d, UUID.fromString(BleConfig.UUID_HS_SERVICE), UUID.fromString(BleConfig.UUID_HS_READ));
    }

    public void getMeasurement() {
        Log.p("HsInsSet", Level.INFO, "getMeasurement", new Object[0]);
        if (!this.f1892h) {
            this.f1887c.getService(this.f1888d, BleConfig.UUID_HS_SERVICE, null, BleConfig.UUID_HS_RECEIVE, BleConfig.UUID_180A, true);
        }
    }

    public void haveNewData(int what, int stateId, byte[] command) {
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
        Log.p("HsInsSet", Level.DEBUG, "haveNewData", new Object[]{uuid, ByteBufferUtil.Bytes2HexString(command)});
        int i = -1;
        switch (uuid.hashCode()) {
            case -1063529840:
                if (uuid.equals(BleConfig.UUID_HS_RECEIVE)) {
                    boolean z = true;
                    break;
                }
                break;
            case -892660755:
                if (uuid.equals(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC)) {
                    i = 0;
                    break;
                }
                break;
            case -370428143:
                if (uuid.equals(BleConfig.UUID_HS_READ)) {
                    i = 2;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                byte b = command[0];
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(HsProfile.CHS_BATTERY, b);
                    this.f1890f.onNotify(this.f1888d, this.f1889e, HsProfile.ACTION_BATTERY_CHS, jSONObject.toString());
                    return;
                } catch (JSONException e) {
                    Log.p("HsInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                    return;
                }
            case 1:
                if (command == null) {
                    this.f1892h = true;
                    return;
                }
                try {
                    JSONObject a = m1027a(command);
                    if (a != null) {
                        this.f1890f.onNotify(this.f1888d, this.f1889e, HsProfile.ACTION_CHS_MEASUREMENT_DATA, a.toString());
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    Log.p("HsInsSet", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                    return;
                }
            case 2:
                if (command == null) {
                    this.f1892h = true;
                    return;
                }
                return;
            default:
                return;
        }
    }
}

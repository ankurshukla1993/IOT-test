package com.ihealth.communication.ins;

import android.content.Context;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.ble.BleConfig;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BleCommContinueProtocol;
import com.ihealth.communication.control.BsProfile;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.ContinuaDataAnalysis;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class BsInsSet implements NewDataCallback {
    private Context f1803a;
    private BaseComm f1804b;
    private BleComm f1805c;
    private String f1806d;
    private String f1807e;
    private InsCallback f1808f;
    private BleCommContinueProtocol f1809g;
    private boolean f1810h;

    public BsInsSet(Context context, BaseComm com, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        Log.p("BsInsSet", Level.INFO, "BsInsSet_Constructor", new Object[]{userName, mac, type});
        this.f1803a = context;
        this.f1804b = com;
        this.f1805c = bleComm;
        this.f1806d = mac;
        this.f1807e = type;
        this.f1808f = insCallback;
        this.f1809g = new BleCommContinueProtocol(com, mac, this);
    }

    private JSONObject m974a(byte[] bArr) {
        if (bArr.length < 4) {
            return null;
        }
        ContinuaDataAnalysis continuaDataAnalysis = new ContinuaDataAnalysis(bArr, bArr.length);
        JSONObject jSONObject = new JSONObject();
        try {
            short read16ByteValue = continuaDataAnalysis.read16ByteValue();
            int i = read16ByteValue & 1;
            jSONObject.put(BsProfile.CBS_UNIT_FLAG, i);
            int i2 = (read16ByteValue >> 1) & 1;
            jSONObject.put(BsProfile.CBS_TIMESTAMP_FLAG, i2);
            int i3 = (read16ByteValue >> 2) & 1;
            jSONObject.put(BsProfile.CBS_USER_ID_FLAG, i3);
            int i4 = (read16ByteValue >> 3) & 1;
            jSONObject.put(BsProfile.CBS_BASAL_METABOLISM_FLAG, i4);
            int i5 = (read16ByteValue >> 4) & 1;
            jSONObject.put(BsProfile.CBS_MUSCLE_PERCENTAGE_FLAG, i5);
            int i6 = (read16ByteValue >> 5) & 1;
            jSONObject.put(BsProfile.CBS_MUSCLE_MASS_FLAG, i6);
            int i7 = (read16ByteValue >> 6) & 1;
            jSONObject.put(BsProfile.CBS_FAT_FREE_MASS_FLAG, i7);
            int i8 = (read16ByteValue >> 7) & 1;
            jSONObject.put(BsProfile.CBS_SOFT_LEAN_MASS_FLAG, i8);
            int i9 = (read16ByteValue >> 8) & 1;
            jSONObject.put(BsProfile.CBS_BODY_WATER_MASS_FLAG, i9);
            int i10 = (read16ByteValue >> 9) & 1;
            jSONObject.put(BsProfile.CBS_IMPEDANCE_FLAG, i10);
            int i11 = (read16ByteValue >> 10) & 1;
            jSONObject.put(BsProfile.CBS_WEIGHT_FLAG, i11);
            int i12 = (read16ByteValue >> 11) & 1;
            jSONObject.put(BsProfile.CBS_HEIGHT_FLAG, i12);
            jSONObject.put(BsProfile.CBS_MULTIPLE_PACKET_MEASUREMENT_FLAG, (read16ByteValue >> 12) & 1);
            jSONObject.put(BsProfile.CBS_BODY_FAT_PERCENTAGE, continuaDataAnalysis.readUInt16Value());
            if (i2 == 1) {
                jSONObject.put(BsProfile.CBS_TIME_STAMP, continuaDataAnalysis.readDateValue());
            }
            if (i3 == 1) {
                jSONObject.put(BsProfile.CBS_USER_ID, continuaDataAnalysis.readUInt8Value() & 255);
            }
            if (i4 == 1) {
                jSONObject.put(BsProfile.CBS_BASAL_METABOLISM, continuaDataAnalysis.readUInt16Value());
            }
            if (i5 == 1) {
                jSONObject.put(BsProfile.CBS_MUSCLE_PERCENTAGE, continuaDataAnalysis.readUInt16Value());
            }
            if (i == 0) {
                if (i6 == 1) {
                    jSONObject.put(BsProfile.CBS_MUSCLE_MASS_KILOGRAMS, continuaDataAnalysis.readUInt16Value());
                }
                if (i7 == 1) {
                    jSONObject.put(BsProfile.CBS_FAT_FREE_MASS_KILOGRAMS, continuaDataAnalysis.readUInt16Value());
                }
                if (i8 == 1) {
                    jSONObject.put(BsProfile.CBS_SOFT_LEAN_MASS_KILOGRAMS, continuaDataAnalysis.readUInt16Value());
                }
                if (i9 == 1) {
                    jSONObject.put(BsProfile.CBS_BODY_WATER_MASS_KILOGRAMS, continuaDataAnalysis.readUInt16Value());
                }
            } else {
                if (i6 == 1) {
                    jSONObject.put(BsProfile.CBS_MUSCLE_MASS_POUNDS, continuaDataAnalysis.readUInt16Value());
                }
                if (i7 == 1) {
                    jSONObject.put(BsProfile.CBS_FAT_FREE_MASS_POUNDS, continuaDataAnalysis.readUInt16Value());
                }
                if (i8 == 1) {
                    jSONObject.put(BsProfile.CBS_SOFT_LEAN_MASS_POUNDS, continuaDataAnalysis.readUInt16Value());
                }
                if (i9 == 1) {
                    jSONObject.put(BsProfile.CBS_BODY_WATER_MASS_POUNDS, continuaDataAnalysis.readUInt16Value());
                }
            }
            if (i10 == 1) {
                jSONObject.put(BsProfile.CBS_IMPEDANCE, continuaDataAnalysis.readUInt16Value());
            }
            if (i == 0) {
                if (i11 == 1) {
                    jSONObject.put(BsProfile.CBS_WEIGHT_KILOGRAMS, continuaDataAnalysis.readUInt16Value());
                }
                if (i12 != 1) {
                    return jSONObject;
                }
                jSONObject.put(BsProfile.CBS_HEIGHT_KILOGRAMS, continuaDataAnalysis.readUInt16Value());
                return jSONObject;
            }
            if (i11 == 1) {
                jSONObject.put(BsProfile.CBS_WEIGHT_POUNDS, continuaDataAnalysis.readUInt16Value());
            }
            if (i12 != 1) {
                return jSONObject;
            }
            jSONObject.put(BsProfile.CBS_HEIGHT_POUNDS, continuaDataAnalysis.readUInt16Value());
            return jSONObject;
        } catch (JSONException e) {
            Log.p("BsInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            return jSONObject;
        }
    }

    public void destroy() {
        Log.p("BsInsSet", Level.INFO, "destroy", new Object[0]);
        this.f1808f = null;
        this.f1803a = null;
        if (this.f1809g != null) {
            this.f1809g.destroy();
        }
        this.f1809g = null;
    }

    public boolean getBattery() {
        Log.p("BsInsSet", Level.INFO, "getBattery", new Object[0]);
        if (!(this.f1804b instanceof BleComm)) {
            return false;
        }
        return ((BleComm) this.f1804b).Obtain(this.f1806d, UUID.fromString(BleConfig.UUID_BTM_BATTERY_SERVICE), UUID.fromString(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC));
    }

    public boolean getFeature() {
        Log.p("BsInsSet", Level.INFO, "getFeature", new Object[0]);
        if (!(this.f1804b instanceof BleComm)) {
            return false;
        }
        return ((BleComm) this.f1804b).Obtain(this.f1806d, UUID.fromString(BleConfig.UUID_BS_SERVICE), UUID.fromString(BleConfig.UUID_BS_READ));
    }

    public void getMeasurement() {
        Log.p("BsInsSet", Level.INFO, "getMeasurement", new Object[0]);
        if (!this.f1810h) {
            this.f1805c.getService(this.f1806d, BleConfig.UUID_BS_SERVICE, null, BleConfig.UUID_BS_RECEIVE, BleConfig.UUID_180A, true);
        }
    }

    public void haveNewData(int what, int stateId, byte[] command) {
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
        Log.p("BsInsSet", Level.DEBUG, "haveNewData", new Object[]{uuid, ByteBufferUtil.Bytes2HexString(command)});
        if (uuid.equals(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC)) {
            byte b = command[0];
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(BsProfile.CBS_BATTERY, b);
                this.f1808f.onNotify(this.f1806d, this.f1807e, BsProfile.ACTION_BATTERY_CBS, jSONObject.toString());
            } catch (JSONException e) {
                Log.p("BsInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            }
        } else if (!uuid.equals(BleConfig.UUID_HS_RECEIVE)) {
        } else {
            if (command == null) {
                this.f1810h = true;
                return;
            }
            try {
                JSONObject a = m974a(command);
                if (a != null) {
                    this.f1808f.onNotify(this.f1806d, this.f1807e, BsProfile.ACTION_CBS_MEASUREMENT_DATA, a.toString());
                }
            } catch (Exception e2) {
                Log.p("BsInsSet", Level.WARN, "Exception", new Object[]{e2.getMessage()});
            }
        }
    }
}

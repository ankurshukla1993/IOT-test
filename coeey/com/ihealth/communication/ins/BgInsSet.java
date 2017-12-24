package com.ihealth.communication.ins;

import android.content.Context;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.ble.BleConfig;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BleCommContinueProtocol;
import com.ihealth.communication.control.BgProfile;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.ContinuaDataAnalysis;
import com.ihealth.communication.utils.Log;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class BgInsSet implements NewDataCallback {
    private Context f1763a;
    private BaseComm f1764b;
    private BleComm f1765c;
    private String f1766d;
    private String f1767e;
    private InsCallback f1768f;
    private BleCommContinueProtocol f1769g;
    private boolean f1770h = false;
    private boolean f1771i = false;
    private boolean f1772j = false;

    public BgInsSet(Context context, BaseComm com, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1763a = context;
        this.f1764b = com;
        this.f1765c = bleComm;
        this.f1766d = mac;
        this.f1767e = type;
        this.f1768f = insCallback;
        this.f1769g = new BleCommContinueProtocol(com, mac, this);
    }

    private JSONObject m936a(byte[] bArr, int i) {
        if (i < 10) {
            Log.e("BgInsSet", "Invalidate data");
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            ContinuaDataAnalysis continuaDataAnalysis = new ContinuaDataAnalysis(bArr, i);
            byte read8ByteValue = continuaDataAnalysis.read8ByteValue();
            int i2 = read8ByteValue & 1;
            jSONObject.put(BgProfile.CBGINFO_TIME_OFFSET_FLAG, i2);
            int i3 = (read8ByteValue >> 1) & 1;
            jSONObject.put(BgProfile.CBGINFO_TYPE_LOCATION_FLAG, i3);
            jSONObject.put(BgProfile.CBGINFO_UNIT, (read8ByteValue >> 2) & 1);
            int i4 = (read8ByteValue >> 3) & 1;
            jSONObject.put(BgProfile.CBGINFO_SENSOR_STATUS_FLAG, i4);
            jSONObject.put(BgProfile.CBGINFO_CONTEXT_INFORMATION_FLAG, (read8ByteValue >> 4) & 1);
            jSONObject.put("sequence_number", continuaDataAnalysis.readUInt16Value());
            jSONObject.put("measure_time", continuaDataAnalysis.readDateValue());
            if (i2 == 1) {
                jSONObject.put(BgProfile.TIME_OFFSET_CBG, continuaDataAnalysis.readSInt16Value());
            }
            if (i3 == 1) {
                jSONObject.put("result", (double) continuaDataAnalysis.readSFloatValue());
                read8ByteValue = continuaDataAnalysis.readNibbleValue();
                jSONObject.put(BgProfile.TYPE_MEASUREMENT_CBG, read8ByteValue & 15);
                jSONObject.put(BgProfile.LOCATION_MEASUREMENT_CBG, (read8ByteValue >> 4) & 15);
            }
            if (i4 != 1) {
                return jSONObject;
            }
            short read16ByteValue = continuaDataAnalysis.read16ByteValue();
            jSONObject.put("low_battery", read16ByteValue & 1);
            jSONObject.put(BgProfile.CBGINFO_SENSOR_MAL_FUNCTION, (read16ByteValue >> 1) & 1);
            jSONObject.put(BgProfile.CBGINFO_INSUFFICIENT_SAMPLE, (read16ByteValue >> 2) & 1);
            jSONObject.put("strip_insertion_error", (read16ByteValue >> 3) & 1);
            jSONObject.put(BgProfile.CBGINFO_TYPE_INCORRECT, (read16ByteValue >> 4) & 1);
            jSONObject.put(BgProfile.CBGINFO_RESULT_HIGHER, (read16ByteValue >> 5) & 1);
            jSONObject.put(BgProfile.CBGINFO_RESULT_LOWER, (read16ByteValue >> 6) & 1);
            jSONObject.put(BgProfile.CBGINFO_TEMPERATURE_TOO_HIGH, (read16ByteValue >> 7) & 1);
            jSONObject.put(BgProfile.CBGINFO_TEMPERATURE_TOO_LOW, (read16ByteValue >> 8) & 1);
            jSONObject.put(BgProfile.CBGINFO_STRIP_PULL_TOO_EARLY, (read16ByteValue >> 9) & 1);
            jSONObject.put(BgProfile.CBGINFO_SENSOR_FAULT, (read16ByteValue >> 10) & 1);
            jSONObject.put(BgProfile.CBGINFO_TIME_FAULT, (read16ByteValue >> 11) & 1);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    private JSONObject m937b(byte[] bArr, int i) {
        if (i < 3) {
            Log.e("BgInsSet", "Invalidate data");
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            ContinuaDataAnalysis continuaDataAnalysis = new ContinuaDataAnalysis(bArr, i);
            byte read8ByteValue = continuaDataAnalysis.read8ByteValue();
            int i2 = read8ByteValue & 1;
            jSONObject.put(BgProfile.CONTEXT_CARBOGYDRATE_PRESNET_CBG, i2);
            int i3 = (read8ByteValue >> 1) & 1;
            jSONObject.put(BgProfile.CONTEXT_MEAL_PRESNET_CBG, i3);
            int i4 = (read8ByteValue >> 2) & 1;
            jSONObject.put(BgProfile.CONTEXT_TESTER_HEALTH_PRESNET_CBG, i4);
            int i5 = (read8ByteValue >> 3) & 1;
            jSONObject.put(BgProfile.CONTEXT_EXERCISE_PRESNET_CBG, i5);
            int i6 = (read8ByteValue >> 4) & 1;
            jSONObject.put(BgProfile.CONTEXT_MEDICATION_PRESNET_CBG, i6);
            jSONObject.put(BgProfile.CONTEXT_MEDICATION_UNIT_CBG, (read8ByteValue >> 5) & 1);
            int i7 = (read8ByteValue >> 6) & 1;
            jSONObject.put(BgProfile.CONTEXT_HBA1C_PRESNET_CBG, i7);
            int i8 = (read8ByteValue >> 7) & 1;
            jSONObject.put(BgProfile.CONTEXT_EXTENDED_FLAG_PRESNET_CBG, i8);
            jSONObject.put("sequence_number", continuaDataAnalysis.readUInt16Value());
            if (i8 == 1) {
                jSONObject.put(BgProfile.CONTEXT_EXTENDED_FLAG_CBG, continuaDataAnalysis.read8ByteValue());
            }
            if (i2 == 1) {
                jSONObject.put(BgProfile.CONTEXT_CARBOHYDRATE_ID_CBG, continuaDataAnalysis.readUInt8Value());
                jSONObject.put(BgProfile.CONTEXT_CARBOHYDRATE_CBG, (double) continuaDataAnalysis.readSFloatValue());
            }
            if (i3 == 1) {
                jSONObject.put(BgProfile.CONTEXT_MEAL_CBG, continuaDataAnalysis.readUInt8Value());
            }
            if (i4 == 1) {
                read8ByteValue = continuaDataAnalysis.readNibbleValue();
                jSONObject.put(BgProfile.CONTEXT_TESTER_CBG, (byte) (read8ByteValue & 15));
                jSONObject.put(BgProfile.CONTEXT_HEALTH_CBG, (byte) ((read8ByteValue >> 4) & 15));
            }
            if (i5 == 1) {
                jSONObject.put(BgProfile.CONTEXT_EXERCISE_DURATION_CBG, continuaDataAnalysis.readUInt16Value());
                jSONObject.put(BgProfile.CONTEXT_EXERCISE_INTENSITY_CBG, continuaDataAnalysis.readUInt8Value());
            }
            if (i6 == 1) {
                jSONObject.put(BgProfile.CONTEXT_MEDICATION_ID_CBG, continuaDataAnalysis.readUInt8Value());
                jSONObject.put(BgProfile.CONTEXT_MEDICATION_CBG, (double) continuaDataAnalysis.readSFloatValue());
            }
            if (i7 != 1) {
                return jSONObject;
            }
            jSONObject.put(BgProfile.CONTEXT_HBA1C_CBG, (double) continuaDataAnalysis.readSFloatValue());
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    private JSONObject m938c(byte[] bArr, int i) {
        if (i < 2) {
            Log.e("BgInsSet", "Invalidate data");
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            short read16ByteValue = new ContinuaDataAnalysis(bArr, i).read16ByteValue();
            jSONObject.put("low_battery", read16ByteValue & 1);
            jSONObject.put(BgProfile.FEATURE_SENSOR_MALFUNCTION_DETECTION_CBG, (read16ByteValue >> 1) & 1);
            jSONObject.put(BgProfile.FEATURE_SAMPLE_SIZE_CBG, (read16ByteValue >> 2) & 1);
            jSONObject.put("strip_insertion_error", (read16ByteValue >> 3) & 1);
            jSONObject.put(BgProfile.FEATURE_STRIP_TYPE_ERROR_CBG, (read16ByteValue >> 4) & 1);
            jSONObject.put(BgProfile.FEATURE_RESULT_HIGH_LOW_DETECTION_CBG, (read16ByteValue >> 5) & 1);
            jSONObject.put(BgProfile.FEATURE_TEMPERATURE_HIGH_LOW_DETECTION_CBG, (read16ByteValue >> 6) & 1);
            jSONObject.put(BgProfile.FEATURE_READ_INTERRUPT_CBG, (read16ByteValue >> 7) & 1);
            jSONObject.put(BgProfile.FEATURE_GENERAL_FAULT_SUPPORT_CBG, (read16ByteValue >> 8) & 1);
            jSONObject.put(BgProfile.FEATURE_TIME_FAULT_SUPPORT_CBG, (read16ByteValue >> 9) & 1);
            jSONObject.put(BgProfile.FEATURE_MULTIPLE_BOND_SUPPORTED_CBG, (read16ByteValue >> 10) & 1);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public void destroy() {
        this.f1768f = null;
        this.f1763a = null;
        if (this.f1769g != null) {
            this.f1769g.destroy();
        }
        this.f1769g = null;
    }

    public boolean getBattery() {
        if (!(this.f1764b instanceof BleComm)) {
            return false;
        }
        return ((BleComm) this.f1764b).Obtain(this.f1766d, UUID.fromString(BleConfig.UUID_BTM_BATTERY_SERVICE), UUID.fromString(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC));
    }

    public boolean getFeature() {
        if (!(this.f1764b instanceof BleComm)) {
            return false;
        }
        return ((BleComm) this.f1764b).Obtain(this.f1766d, UUID.fromString(BleConfig.UUID_BG_SERVICE), UUID.fromString(BleConfig.UUID_BG_READ));
    }

    public void getMeasurement() {
        if (!this.f1770h) {
            this.f1765c.getService(this.f1766d, BleConfig.UUID_BG_SERVICE, "00002a52-0000-1000-8000-00805f9b34fb", BleConfig.UUID_BG_RECEIVE, BleConfig.UUID_180A, false);
        }
    }

    public void getMeasurementContext() {
        if (!this.f1771i) {
            this.f1765c.getService(this.f1766d, BleConfig.UUID_BG_SERVICE, "00002a52-0000-1000-8000-00805f9b34fb", BleConfig.UUID_BG_RECEIVE_CONTENT, BleConfig.UUID_180A, false);
        }
    }

    public void getRecord() {
        if (this.f1772j) {
            this.f1764b.sendData(this.f1766d, new byte[]{(byte) 1, (byte) 1, (byte) 0, (byte) 0});
            return;
        }
        this.f1765c.getService(this.f1766d, BleConfig.UUID_BG_SERVICE, "00002a52-0000-1000-8000-00805f9b34fb", "00002a52-0000-1000-8000-00805f9b34fb", BleConfig.UUID_180A, true);
    }

    public void haveNewData(int what, int stateId, byte[] command) {
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
        JSONObject jSONObject = null;
        if (uuid.equals(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC)) {
            byte b = command[0];
            jSONObject = new JSONObject();
            try {
                jSONObject.put("battery", b);
                this.f1768f.onNotify(this.f1766d, this.f1767e, "action_battery", jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (uuid.equals(BleConfig.UUID_BG_RECEIVE)) {
            if (command == null) {
                this.f1770h = true;
                Log.i("BgInsSet", "Enable indicate success");
                return;
            }
            try {
                jSONObject = m936a(command, command.length);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (jSONObject != null) {
                this.f1768f.onNotify(this.f1766d, this.f1767e, BgProfile.ACTION_MEASUREMENT_RESULT_CBG, jSONObject.toString());
            }
        } else if (uuid.equals(BleConfig.UUID_BG_RECEIVE_CONTENT)) {
            if (command == null) {
                this.f1771i = true;
                Log.i("BgInsSet", "Enable indicate success");
                return;
            }
            try {
                jSONObject = m937b(command, command.length);
            } catch (Exception e22) {
                e22.printStackTrace();
            }
            if (jSONObject != null) {
                this.f1768f.onNotify(this.f1766d, this.f1767e, BgProfile.ACTION_MEASUREMENT_CONTEXT_CBG, jSONObject.toString());
            }
        } else if (uuid.equals("00002a52-0000-1000-8000-00805f9b34fb")) {
            if (command == null) {
                this.f1772j = true;
                Log.i("BgInsSet", "Enable indicate success for iHealth");
                this.f1764b.sendData(this.f1766d, new byte[]{(byte) 1, (byte) 1, (byte) 0, (byte) 0});
                return;
            }
            Log.i("BgInsSet", "Record:" + ByteBufferUtil.Bytes2HexString(command));
        } else if (uuid.equals(BleConfig.UUID_BG_READ)) {
            JSONObject c;
            try {
                c = m938c(command, command.length);
            } catch (Exception e222) {
                e222.printStackTrace();
                c = jSONObject;
            }
            if (c != null) {
                this.f1768f.onNotify(this.f1766d, this.f1767e, "action_feature", c.toString());
            }
        }
    }
}

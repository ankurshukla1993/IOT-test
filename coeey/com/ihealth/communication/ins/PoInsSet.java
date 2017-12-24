package com.ihealth.communication.ins;

import android.content.Context;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.ble.BleConfig;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BleCommContinueProtocol;
import com.ihealth.communication.control.PoProfile;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.ContinuaDataAnalysis;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class PoInsSet implements NewDataCallback {
    private Context f1899a;
    private BaseComm f1900b;
    private BleComm f1901c;
    private String f1902d;
    private String f1903e;
    private InsCallback f1904f;
    private BleCommContinueProtocol f1905g;
    private boolean f1906h;
    private boolean f1907i;
    private boolean f1908j;

    public PoInsSet(Context context, BaseComm com, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        Log.p("PoInsSet", Level.INFO, "PoInsSet_Constructor", new Object[]{userName, mac, type});
        this.f1899a = context;
        this.f1900b = com;
        this.f1901c = bleComm;
        this.f1902d = mac;
        this.f1903e = type;
        this.f1904f = insCallback;
        this.f1905g = new BleCommContinueProtocol(com, mac, this);
    }

    private JSONObject m1028a(byte[] bArr) {
        if (bArr.length < 5) {
            return null;
        }
        ContinuaDataAnalysis continuaDataAnalysis = new ContinuaDataAnalysis(bArr, bArr.length);
        JSONObject jSONObject = new JSONObject();
        try {
            byte read8ByteValue = continuaDataAnalysis.read8ByteValue();
            int i = read8ByteValue & 1;
            jSONObject.put(PoProfile.CPO_TIMESTAMP_FLAG, i);
            int i2 = (read8ByteValue >> 1) & 1;
            jSONObject.put(PoProfile.CPO_MEASUREMENT_STATUS_FLAG, i2);
            int i3 = (read8ByteValue >> 2) & 1;
            jSONObject.put(PoProfile.CPO_DS_STATUS_FLAG, i3);
            int i4 = (read8ByteValue >> 3) & 1;
            jSONObject.put(PoProfile.CPO_PA_INDEX_FLAG, i4);
            jSONObject.put(PoProfile.CPO_CLOCK_SET_FLAG, (read8ByteValue >> 4) & 1);
            jSONObject.put(PoProfile.CPO_SPO2_DATA, (double) continuaDataAnalysis.readSFloatValue());
            jSONObject.put(PoProfile.CPO_PR_DATA, (double) continuaDataAnalysis.readSFloatValue());
            if (i == 1) {
                jSONObject.put(PoProfile.CPO_TIMESTAMP_DATA, continuaDataAnalysis.readDateValue());
            }
            if (i2 == 1) {
                short read16ByteValue = continuaDataAnalysis.read16ByteValue();
                jSONObject.put(PoProfile.CPO_MEASUREMENT_ONGOING_DATA, (read16ByteValue >> 5) & 1);
                jSONObject.put(PoProfile.CPO_EARLY_ESTIMATED_DATA, (read16ByteValue >> 6) & 1);
                jSONObject.put(PoProfile.CPO_VALIDATED_DATA, (read16ByteValue >> 7) & 1);
                jSONObject.put(PoProfile.CPO_FULLY_QUALIFIED_DATA, (read16ByteValue >> 8) & 1);
                jSONObject.put(PoProfile.CPO_MEASUREMENT_STORAGE_DATA, (read16ByteValue >> 9) & 1);
                jSONObject.put(PoProfile.CPO_DEMONSTRATION_DATA, (read16ByteValue >> 10) & 1);
                jSONObject.put(PoProfile.CPO_TESTING_DATA, (read16ByteValue >> 11) & 1);
                jSONObject.put(PoProfile.CPO_CALIBRATION_ONGOING_DATA, (read16ByteValue >> 12) & 1);
                jSONObject.put(PoProfile.CPO_MEASUREMENT_UNAVAILABLE_DATA, (read16ByteValue >> 13) & 1);
                jSONObject.put(PoProfile.CPO_QUESTIONABLE_DETECTED_DATA, (read16ByteValue >> 14) & 1);
                jSONObject.put(PoProfile.CPO_INVALID_DETECTED_DATA, (read16ByteValue >> 15) & 1);
            }
            if (i3 == 1) {
                int read24ByteValue = continuaDataAnalysis.read24ByteValue();
                jSONObject.put(PoProfile.CPO_EXTENDED_DISPLAY_UPDATE_ONGOING_DATA, read24ByteValue & 1);
                jSONObject.put(PoProfile.CPO_EQUIPMENT_MALFUNCTION_DATA, (read24ByteValue >> 1) & 1);
                jSONObject.put(PoProfile.CPO_SIGNAL_PROCESSING_IRREGULARITY_DATA, (read24ByteValue >> 2) & 1);
                jSONObject.put(PoProfile.CPO_INADEQUATE_SIGNAL_DETECTED_DATA, (read24ByteValue >> 3) & 1);
                jSONObject.put(PoProfile.CPO_POOR_SIGNAL_DETECTED_DATA, (read24ByteValue >> 4) & 1);
                jSONObject.put(PoProfile.CPO_LOW_PERFUSION_DETECTED_DATA, (read24ByteValue >> 5) & 1);
                jSONObject.put(PoProfile.CPO_ERRATIC_SIGNAL_DETECTED_DATA, (read24ByteValue >> 6) & 1);
                jSONObject.put(PoProfile.CPO_NONPULSATILE_SIGNAL_DETECTED_DATA, (read24ByteValue >> 7) & 1);
                jSONObject.put(PoProfile.CPO_QUESTIONABLE_PULSE_DETECTED_DATA, (read24ByteValue >> 8) & 1);
                jSONObject.put(PoProfile.CPO_SIGNAL_ANALYSIS_ONGOING_DATA, (read24ByteValue >> 9) & 1);
                jSONObject.put(PoProfile.CPO_SENSOR_INTERFACE_DETECTED_DATA, (read24ByteValue >> 10) & 1);
                jSONObject.put(PoProfile.CPO_SENSOR_UNCONNECTED_TO_USER_DATA, (read24ByteValue >> 11) & 1);
                jSONObject.put(PoProfile.CPO_UNKNOWN_SENSOR_CONNECTED_DATA, (read24ByteValue >> 12) & 1);
                jSONObject.put(PoProfile.CPO_SENSOR_DISPLACED_DATA, (read24ByteValue >> 13) & 1);
                jSONObject.put(PoProfile.CPO_SENSOR_MALFUNCTION_DATA, (read24ByteValue >> 14) & 1);
                jSONObject.put(PoProfile.CPO_SENSOR_DISCONNECTED_DATA, (read24ByteValue >> 15) & 1);
            }
            if (i4 != 1) {
                return jSONObject;
            }
            jSONObject.put(PoProfile.CPO_PULSE_AMPLITUDE_INDEX_DATA, (double) continuaDataAnalysis.readSFloatValue());
            return jSONObject;
        } catch (JSONException e) {
            Log.p("PoInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            return jSONObject;
        }
    }

    private JSONObject m1029b(byte[] bArr) {
        if (bArr.length < 5) {
            return null;
        }
        ContinuaDataAnalysis continuaDataAnalysis = new ContinuaDataAnalysis(bArr, bArr.length);
        JSONObject jSONObject = new JSONObject();
        try {
            byte read8ByteValue = continuaDataAnalysis.read8ByteValue();
            int i = read8ByteValue & 1;
            jSONObject.put(PoProfile.CPO_SPO2_PR_FAST_FLAG, i);
            int i2 = (read8ByteValue >> 1) & 1;
            jSONObject.put(PoProfile.CPO_SPO2_PR_SLOW_FLAG, i2);
            int i3 = (read8ByteValue >> 2) & 1;
            jSONObject.put(PoProfile.CPO_MEASUREMENT_STATUS_FLAG, i3);
            int i4 = (read8ByteValue >> 3) & 1;
            jSONObject.put(PoProfile.CPO_DS_STATUS_FLAG, i4);
            int i5 = (read8ByteValue >> 4) & 1;
            jSONObject.put(PoProfile.CPO_PA_INDEX_FLAG, i5);
            jSONObject.put(PoProfile.CPO_SPO2_DATA, (double) continuaDataAnalysis.readSFloatValue());
            jSONObject.put(PoProfile.CPO_PR_DATA, (double) continuaDataAnalysis.readSFloatValue());
            if (i == 1) {
                jSONObject.put(PoProfile.CPO_SPO2_FAST_DATA, (double) continuaDataAnalysis.readSFloatValue());
                jSONObject.put(PoProfile.CPO_PR_FAST_DATA, (double) continuaDataAnalysis.readSFloatValue());
            }
            if (i2 == 1) {
                jSONObject.put(PoProfile.CPO_SPO2_SLOW_DATA, (double) continuaDataAnalysis.readSFloatValue());
                jSONObject.put(PoProfile.CPO_PR_SLOW_DATA, (double) continuaDataAnalysis.readSFloatValue());
            }
            if (i3 == 1) {
                short read16ByteValue = continuaDataAnalysis.read16ByteValue();
                jSONObject.put(PoProfile.CPO_MEASUREMENT_ONGOING_DATA, (read16ByteValue >> 5) & 1);
                jSONObject.put(PoProfile.CPO_EARLY_ESTIMATED_DATA, (read16ByteValue >> 6) & 1);
                jSONObject.put(PoProfile.CPO_VALIDATED_DATA, (read16ByteValue >> 7) & 1);
                jSONObject.put(PoProfile.CPO_FULLY_QUALIFIED_DATA, (read16ByteValue >> 8) & 1);
                jSONObject.put(PoProfile.CPO_MEASUREMENT_STORAGE_DATA, (read16ByteValue >> 9) & 1);
                jSONObject.put(PoProfile.CPO_DEMONSTRATION_DATA, (read16ByteValue >> 10) & 1);
                jSONObject.put(PoProfile.CPO_TESTING_DATA, (read16ByteValue >> 11) & 1);
                jSONObject.put(PoProfile.CPO_CALIBRATION_ONGOING_DATA, (read16ByteValue >> 12) & 1);
                jSONObject.put(PoProfile.CPO_MEASUREMENT_UNAVAILABLE_DATA, (read16ByteValue >> 13) & 1);
                jSONObject.put(PoProfile.CPO_QUESTIONABLE_DETECTED_DATA, (read16ByteValue >> 14) & 1);
                jSONObject.put(PoProfile.CPO_INVALID_DETECTED_DATA, (read16ByteValue >> 15) & 1);
            }
            if (i4 == 1) {
                i = continuaDataAnalysis.read24ByteValue();
                jSONObject.put(PoProfile.CPO_EXTENDED_DISPLAY_UPDATE_ONGOING_DATA, i & 1);
                jSONObject.put(PoProfile.CPO_EQUIPMENT_MALFUNCTION_DATA, (i >> 1) & 1);
                jSONObject.put(PoProfile.CPO_SIGNAL_PROCESSING_IRREGULARITY_DATA, (i >> 2) & 1);
                jSONObject.put(PoProfile.CPO_INADEQUATE_SIGNAL_DETECTED_DATA, (i >> 3) & 1);
                jSONObject.put(PoProfile.CPO_POOR_SIGNAL_DETECTED_DATA, (i >> 4) & 1);
                jSONObject.put(PoProfile.CPO_LOW_PERFUSION_DETECTED_DATA, (i >> 5) & 1);
                jSONObject.put(PoProfile.CPO_ERRATIC_SIGNAL_DETECTED_DATA, (i >> 6) & 1);
                jSONObject.put(PoProfile.CPO_NONPULSATILE_SIGNAL_DETECTED_DATA, (i >> 7) & 1);
                jSONObject.put(PoProfile.CPO_QUESTIONABLE_PULSE_DETECTED_DATA, (i >> 8) & 1);
                jSONObject.put(PoProfile.CPO_SIGNAL_ANALYSIS_ONGOING_DATA, (i >> 9) & 1);
                jSONObject.put(PoProfile.CPO_SENSOR_INTERFACE_DETECTED_DATA, (i >> 10) & 1);
                jSONObject.put(PoProfile.CPO_SENSOR_UNCONNECTED_TO_USER_DATA, (i >> 11) & 1);
                jSONObject.put(PoProfile.CPO_UNKNOWN_SENSOR_CONNECTED_DATA, (i >> 12) & 1);
                jSONObject.put(PoProfile.CPO_SENSOR_DISPLACED_DATA, (i >> 13) & 1);
                jSONObject.put(PoProfile.CPO_SENSOR_MALFUNCTION_DATA, (i >> 14) & 1);
                jSONObject.put(PoProfile.CPO_SENSOR_DISCONNECTED_DATA, (i >> 15) & 1);
            }
            if (i5 != 1) {
                return jSONObject;
            }
            jSONObject.put(PoProfile.CPO_PULSE_AMPLITUDE_INDEX_DATA, (double) continuaDataAnalysis.readSFloatValue());
            return jSONObject;
        } catch (JSONException e) {
            Log.p("PoInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
            return jSONObject;
        }
    }

    public void destroy() {
        Log.p("PoInsSet", Level.INFO, "destroy", new Object[0]);
        this.f1904f = null;
        this.f1899a = null;
        if (this.f1905g != null) {
            this.f1905g.destroy();
        }
        this.f1905g = null;
    }

    public boolean getBattery() {
        Log.p("PoInsSet", Level.INFO, "getBattery", new Object[0]);
        if (!(this.f1900b instanceof BleComm)) {
            return false;
        }
        return ((BleComm) this.f1900b).Obtain(this.f1902d, UUID.fromString(BleConfig.UUID_BTM_BATTERY_SERVICE), UUID.fromString(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC));
    }

    public void getContinuous() {
        Log.p("PoInsSet", Level.INFO, "getContinuous", new Object[0]);
        if (!this.f1907i) {
            this.f1901c.getService(this.f1902d, BleConfig.UUID_PO_SERVICE, "00002a52-0000-1000-8000-00805f9b34fb", BleConfig.UUID_PO_RECEIVE_CONTINUOUS, BleConfig.UUID_180A, false);
        }
    }

    public boolean getFeatures() {
        Log.p("PoInsSet", Level.INFO, "getFeatures", new Object[0]);
        if (!(this.f1900b instanceof BleComm)) {
            return false;
        }
        return ((BleComm) this.f1900b).Obtain(this.f1902d, UUID.fromString(BleConfig.UUID_PO_SERVICE), UUID.fromString(BleConfig.UUID_PO_READ));
    }

    public void getRecord() {
        Log.p("PoInsSet", Level.INFO, "getRecord", new Object[0]);
        if (!this.f1908j) {
            this.f1901c.getService(this.f1902d, BleConfig.UUID_PO_SERVICE, "00002a52-0000-1000-8000-00805f9b34fb", "00002a52-0000-1000-8000-00805f9b34fb", BleConfig.UUID_180A, true);
        }
    }

    public void getSpotCheck() {
        Log.p("PoInsSet", Level.INFO, "getSpotCheck", new Object[0]);
        if (!this.f1906h) {
            this.f1901c.getService(this.f1902d, BleConfig.UUID_PO_SERVICE, "00002a52-0000-1000-8000-00805f9b34fb", BleConfig.UUID_PO_RECEIVE, BleConfig.UUID_180A, true);
        }
    }

    public void haveNewData(int what, int stateId, byte[] command) {
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
        Log.p("PoInsSet", Level.DEBUG, "haveNewData", new Object[]{uuid, ByteBufferUtil.Bytes2HexString(command)});
        int i = -1;
        switch (uuid.hashCode()) {
            case -1404140830:
                if (uuid.equals("00002a52-0000-1000-8000-00805f9b34fb")) {
                    i = 3;
                    break;
                }
                break;
            case -892660755:
                if (uuid.equals(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC)) {
                    i = 0;
                    break;
                }
                break;
            case -415692651:
                if (uuid.equals(BleConfig.UUID_PO_RECEIVE)) {
                    boolean z = true;
                    break;
                }
                break;
            case 277409046:
                if (uuid.equals(BleConfig.UUID_PO_RECEIVE_CONTINUOUS)) {
                    i = 2;
                    break;
                }
                break;
        }
        JSONObject a;
        switch (i) {
            case 0:
                byte b = command[0];
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("battery", b);
                    this.f1904f.onNotify(this.f1902d, this.f1903e, PoProfile.ACTION_BATTERY_PO, jSONObject.toString());
                    return;
                } catch (JSONException e) {
                    Log.p("PoInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                    return;
                }
            case 1:
                if (command == null) {
                    this.f1906h = true;
                    return;
                }
                try {
                    a = m1028a(command);
                    if (a != null) {
                        this.f1904f.onNotify(this.f1902d, this.f1903e, PoProfile.ACTION_SPOT_CHECK_CPO, a.toString());
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    Log.p("PoInsSet", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                    return;
                }
            case 2:
                if (command == null) {
                    this.f1907i = true;
                    return;
                }
                try {
                    a = m1029b(command);
                    if (a != null) {
                        this.f1904f.onNotify(this.f1902d, this.f1903e, PoProfile.ACTION_CONTINUOUS_CPO, a.toString());
                        return;
                    }
                    return;
                } catch (Exception e22) {
                    Log.p("PoInsSet", Level.WARN, "Exception", new Object[]{e22.getMessage()});
                    return;
                }
            case 3:
                if (command == null) {
                    this.f1908j = true;
                    return;
                }
                return;
            default:
                return;
        }
    }
}

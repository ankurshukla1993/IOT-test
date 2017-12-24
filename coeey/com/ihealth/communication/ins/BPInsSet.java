package com.ihealth.communication.ins;

import android.content.Context;
import com.ihealth.communication.base.ble.BleComm;
import com.ihealth.communication.base.ble.BleConfig;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BleCommContinueProtocol;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.utils.ContinuaDataAnalysis;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class BPInsSet implements NewDataCallback {
    private Context f1722a;
    private BaseComm f1723b;
    private BleComm f1724c;
    private String f1725d;
    private String f1726e;
    private InsCallback f1727f;
    private int f1728g = 0;
    private boolean f1729h = false;
    private boolean f1730i = false;
    private BleCommContinueProtocol f1731j;

    class C21121 extends TimerTask {
        final /* synthetic */ BPInsSet f1721a;

        C21121(BPInsSet this$0) {
            this.f1721a = this$0;
        }

        public void run() {
            this.f1721a.f1727f.onNotify(this.f1721a.f1725d, this.f1721a.f1726e, "action_set_time", "");
        }
    }

    public BPInsSet(Context context, BaseComm com, BleComm bleComm, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback mBaseCommCallback) {
        this.f1722a = context;
        this.f1723b = com;
        this.f1724c = bleComm;
        this.f1725d = mac;
        this.f1726e = type;
        this.f1727f = insCallback;
        this.f1731j = new BleCommContinueProtocol(com, mac, this);
    }

    private JSONObject m909a(byte[] bArr) {
        if (bArr.length < 8) {
            Log.e("BPInsSet", "Invalidate data");
            return null;
        }
        ContinuaDataAnalysis continuaDataAnalysis = new ContinuaDataAnalysis(bArr, bArr.length);
        JSONObject jSONObject = new JSONObject();
        try {
            byte read8ByteValue = continuaDataAnalysis.read8ByteValue();
            jSONObject.put(BpProfile.CBPINFO_UNIT, read8ByteValue & 1);
            int i = (read8ByteValue >> 1) & 1;
            jSONObject.put(BpProfile.CBPINFO_TIMESTAMP_FLAG, i);
            int i2 = (read8ByteValue >> 2) & 1;
            jSONObject.put(BpProfile.CBPINFO_PULSE_RATE_FLAG, i2);
            int i3 = (read8ByteValue >> 3) & 1;
            jSONObject.put(BpProfile.CBPINFO_USER_ID_FLAG, i3);
            int i4 = (read8ByteValue >> 4) & 1;
            jSONObject.put(BpProfile.CBPINFO_MEASURE_STATUS_FLAG, i4);
            int i5 = (read8ByteValue >> 6) & 1;
            jSONObject.put(BpProfile.CBPINFO_HSD_FLAG, i5);
            jSONObject.put(BpProfile.CBPINFO_SYS, (double) continuaDataAnalysis.readSFloatValue());
            jSONObject.put(BpProfile.CBPINFO_DIA, (double) continuaDataAnalysis.readSFloatValue());
            jSONObject.put(BpProfile.CBPINFO_MAP, (double) continuaDataAnalysis.readSFloatValue());
            if (i == 1) {
                jSONObject.put(BpProfile.CBPINFO_TIMESTAMP, continuaDataAnalysis.readDateValue());
            }
            if (i2 == 1) {
                jSONObject.put(BpProfile.CBPINFO_PULSE_RATE, (double) continuaDataAnalysis.readSFloatValue());
            }
            if (i3 == 1) {
                jSONObject.put(BpProfile.CBPINFO_USER_ID, continuaDataAnalysis.readUInt8Value());
            }
            if (i4 != 1) {
                return jSONObject;
            }
            short read16ByteValue = continuaDataAnalysis.read16ByteValue();
            jSONObject.put(BpProfile.CBPINFO_BODY_MOVEMENT, read16ByteValue & 1);
            jSONObject.put(BpProfile.CBPINFO_CUF_FIT, (read16ByteValue >> 1) & 1);
            jSONObject.put(BpProfile.CBPINFO_IRREGULAR, (read16ByteValue >> 2) & 1);
            jSONObject.put(BpProfile.CBPINFO_PULSE_RATE_RANGE, (read16ByteValue >> 3) & 3);
            jSONObject.put(BpProfile.CBPINFO_POSITION, (read16ByteValue >> 4) & 1);
            if (i5 != 1) {
                return jSONObject;
            }
            jSONObject.put(BpProfile.CBPINFO_HSD, (read16ByteValue >> 6) & 1);
            return jSONObject;
        } catch (JSONException e) {
            Log.e("BPInsSet", e.toString());
            return jSONObject;
        }
    }

    private JSONObject m910a(byte[] bArr, int i) {
        if (i < 2) {
            Log.e("BPInsSet", "Invalidate data");
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            short read16ByteValue = new ContinuaDataAnalysis(bArr, i).read16ByteValue();
            jSONObject.put("body_movement", read16ByteValue & 1);
            jSONObject.put(BpProfile.FEATURE_FIT_DETECTION_CBP, (read16ByteValue >> 1) & 1);
            jSONObject.put(BpProfile.FEATURE_IRREGULAR_PULSE_DETECTION_CBP, (read16ByteValue >> 2) & 1);
            jSONObject.put(BpProfile.FEATURE_PULSE_RATE_RANGE_DETECTION_CBP, (read16ByteValue >> 3) & 1);
            jSONObject.put(BpProfile.FEATURE_MEASUREMENT_POSITION_DETECTION_CBP, (read16ByteValue >> 4) & 1);
            jSONObject.put(BpProfile.FEATURE_MULTIPLE_BOND_CBP, (read16ByteValue >> 5) & 1);
            return jSONObject;
        } catch (JSONException e) {
            e.printStackTrace();
            return jSONObject;
        }
    }

    public void commandSetUser(int UserID) {
        Log.p("BPInsSet", Level.INFO, "commandSetUser", new Object[]{Integer.valueOf(UserID)});
        this.f1728g = UserID;
        if (this.f1729h) {
            this.f1723b.sendData(this.f1725d, new byte[]{(byte) -96, (byte) this.f1728g});
            return;
        }
        this.f1724c.getService(this.f1725d, BleConfig.UUID_BP_SERVICE, "0000ff03-0000-1000-8000-00805f9b34fb", "0000ff03-0000-1000-8000-00805f9b34fb", BleConfig.UUID_180A, false);
    }

    public void destroy() {
        this.f1727f = null;
        this.f1722a = null;
        if (this.f1731j != null) {
            this.f1731j.destroy();
        }
        this.f1731j = null;
    }

    public boolean getBattery() {
        Log.p("BPInsSet", Level.INFO, "getBattery", new Object[0]);
        if (this.f1724c == null) {
            return false;
        }
        return this.f1724c.Obtain(this.f1725d, UUID.fromString(BleConfig.UUID_BTM_BATTERY_SERVICE), UUID.fromString(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC));
    }

    public void getData() {
        Log.p("BPInsSet", Level.INFO, "getData", new Object[0]);
        if (!this.f1730i) {
            this.f1724c.getService(this.f1725d, BleConfig.UUID_BP_SERVICE, "0000ff03-0000-1000-8000-00805f9b34fb", BleConfig.UUID_BP_RECEIVE, BleConfig.UUID_180A, true);
        }
    }

    public boolean getFeature() {
        if (!(this.f1723b instanceof BleComm)) {
            return false;
        }
        return ((BleComm) this.f1723b).Obtain(this.f1725d, UUID.fromString(BleConfig.UUID_BP_SERVICE), UUID.fromString(BleConfig.UUID_BP_READ));
    }

    public void getNubForUser() {
        this.f1724c.getService(this.f1725d, BleConfig.UUID_BP_SERVICE, "0000ff03-0000-1000-8000-00805f9b34fb", "0000ff03-0000-1000-8000-00805f9b34fb", BleConfig.UUID_180A, false);
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
                this.f1727f.onNotify(this.f1725d, this.f1726e, "action_battery", jSONObject.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (uuid.equals(BleConfig.UUID_BP_RECEIVE)) {
            if (command == null) {
                this.f1730i = true;
                Log.i("BPInsSet", "Enable indicate success");
                return;
            }
            Object a;
            r0 = new JSONObject();
            try {
                a = m909a(command);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (a != null) {
                try {
                    r0.put(BpProfile.HISTORY_DATA_CBP, a);
                    this.f1727f.onNotify(this.f1725d, this.f1726e, "action_history_data", r0.toString());
                } catch (JSONException e3) {
                    e3.printStackTrace();
                }
            }
        } else if (uuid.equals("0000ff03-0000-1000-8000-00805f9b34fb")) {
            if (command == null) {
                this.f1729h = true;
                Log.i("BPInsSet", "Enable indicate success for iHealth");
                this.f1723b.sendData(this.f1725d, new byte[]{(byte) -96, (byte) this.f1728g});
                return;
            }
            r0 = new JSONObject();
            try {
                r0.put(BpProfile.CHOOSE_USER_CBP, 1);
                this.f1727f.onNotify(this.f1725d, this.f1726e, BpProfile.ACTION_CONFORM_CHOOSE_USER_CBP, r0.toString());
            } catch (JSONException e32) {
                e32.printStackTrace();
            }
        } else if (uuid.equals(BleConfig.UUID_BP_READ)) {
            try {
                r0 = m910a(command, command.length);
            } catch (Exception e4) {
                e4.printStackTrace();
                r0 = jSONObject;
            }
            if (r0 != null) {
                this.f1727f.onNotify(this.f1725d, this.f1726e, "action_feature", r0.toString());
            }
        }
    }

    public boolean setTime() {
        Log.p("BPInsSet", Level.INFO, "setTime", new Object[0]);
        if (this.f1724c == null) {
            return false;
        }
        UUID fromString = UUID.fromString(BleConfig.UUID_TIME_SERVICE);
        UUID fromString2 = UUID.fromString("00002a2b-0000-1000-8000-00805f9b34fb");
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        int i = instance.get(1);
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        int i6 = instance.get(13);
        int i7 = instance.get(7) - 1;
        if (i7 == 0) {
            i7 = 7;
        }
        boolean writeDataExtra = this.f1724c.writeDataExtra(this.f1725d, fromString, fromString2, new byte[]{(byte) (i % 256), (byte) (i / 256), (byte) i2, (byte) i3, (byte) i4, (byte) i5, (byte) i6, (byte) i7, (byte) 0, (byte) 1});
        new Timer().schedule(new C21121(this), 200);
        return writeDataExtra;
    }
}
